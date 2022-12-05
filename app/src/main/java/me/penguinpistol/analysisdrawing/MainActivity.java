package me.penguinpistol.analysisdrawing;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import me.penguinpistol.analysisdrawing.databinding.ActivityMainBinding;
import me.penguinpistol.analysisdrawing.databinding.ItemPartsBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.test();

//        viewModel.getMeituData(this, analysis -> {
//            Glide.with(mBinding.drawing)
//                .asBitmap()
//                .centerCrop()
//                .load(analysis.getImage())
//                .into(new CustomTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        mBinding.drawing.setData(resource, analysis.getLandmark118(), analysis.getLandmark171(), null);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                    }
//                });
//        });

        mBinding.tabType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        mBinding.listModel.setAdapter(new PartsAdapter(viewModel.getFaceParts()));
                        break;
                    case 1:
                        mBinding.listModel.setAdapter(new PartsAdapter(viewModel.getSkinParts()));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mBinding.listModel.setAdapter(new PartsAdapter(viewModel.getFaceParts()));
    }

    public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.ViewHolder> {
        private final List<Pair<String, Parts[]>> items;

        public PartsAdapter(List<Pair<String, Parts[]>> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(ItemPartsBinding.inflate(getLayoutInflater(), mBinding.getRoot(), false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final int realPosition = holder.getAdapterPosition();
            Pair<String, Parts[]> item = items.get(realPosition);
            holder.binding.setLabel(item.first);
            holder.binding.listParts.setAdapter(new ItemAdapter(item.second));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private final ItemPartsBinding binding;
            public ViewHolder(@NonNull ItemPartsBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
        private final Parts[] items;

        public ItemAdapter(Parts[] items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Button button = new Button(MainActivity.this);
            button.setLayoutParams(new FlexboxLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setTextSize(11);
            return new ViewHolder(button);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
            final int realPosition = holder.getAdapterPosition();
            Parts parts = items[realPosition];
            holder.itemView.setText(parts.name);
            holder.itemView.setOnClickListener(v -> {
                mBinding.drawing.startDrawing(parts.modelClass);
            });
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private final Button itemView;
            public ViewHolder(@NonNull Button itemView) {
                super(itemView);
                this.itemView = itemView;
            }
        }
    }
}