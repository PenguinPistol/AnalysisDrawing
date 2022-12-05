package me.penguinpistol.analysisdrawing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class JsonUtils {

    /**
     * 하위 JsonElement 검색
     * @param json 검색을 시작 할 JsonElement
     * @param path .으로 구분된 하위 element 경로
     * @return 검색된 JsonElement
     */
    @Nullable
    public static JsonElement findChild(@NonNull JsonObject json, @NonNull String path) {
        final String[] locations = path.split("\\.");
        if(locations.length == 0) {
            return null;
        }

        JsonElement node = json.get(locations[0]);
        for (int i = 1; i < locations.length; i++) {
            if(node == null || !node.isJsonObject()) {
                return null;
            }
            node = node.getAsJsonObject().get(locations[i]);
        }

        return node;
    }

    /**
     * target json 에 item 을 합친다.
     * @param origin 합칠 대상
     * @param items 합쳐질 데이터
     * @return 합쳐진 JsonObject
     */
    public static JsonObject merge(JsonObject origin, JsonObject items) {
        if(items != null) {
            for (Map.Entry<String, JsonElement> entry : items.entrySet()) {
                final String key = entry.getKey();
                // 중복 키가 있는 경우 덮어쓴다
                if (origin.keySet().contains(key)) {
                    origin.remove(key);
                }
                origin.add(key, entry.getValue());
            }
        }
        return origin;
    }
}
