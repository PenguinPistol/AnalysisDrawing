package me.penguinpistol.analysisdrawing;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import me.penguinpistol.analysisdrawing.databinding.ActivityMainBinding;
import me.penguinpistol.analysisdrawing.drawing.model.BaseDrawingModel;
import me.penguinpistol.analysisdrawing.drawing.model.FaceHorizontalRatio;
import me.penguinpistol.analysisdrawing.drawing.model.FaceVerticalRatio;
import me.penguinpistol.analysisdrawing.drawing.model.GoldenTriangle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Class<? extends BaseDrawingModel>[] models = new Class[] {
                FaceHorizontalRatio.class,
                FaceVerticalRatio.class,
                GoldenTriangle.class
        };

        viewModel.getMeituData(this, analysis -> {
            Glide.with(binding.drawing)
                .asBitmap()
                .centerCrop()
                .load(analysis.getImage())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        binding.drawing.setData(resource, analysis.getLandmark118(), analysis.getLandmark171(), null);
                        binding.drawing.startDrawing(models);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        });

        binding.btnTest.setOnClickListener(v -> {
//            binding.drawing.startDrawing(FaceRatio.class, FaceRatio.class);
        });
    }
}