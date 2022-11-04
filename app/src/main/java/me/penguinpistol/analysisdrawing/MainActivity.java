package me.penguinpistol.analysisdrawing;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.databinding.ActivityMainBinding;
import me.penguinpistol.analysisdrawing.drawing.DrawingOrder;
import me.penguinpistol.analysisdrawing.drawing.object.DrawingObject;
import me.penguinpistol.analysisdrawing.drawing.object.Line;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<DrawingOrder> orders = new ArrayList<>();

        float thickness = 2 * getResources().getDisplayMetrics().density;
        List<DrawingObject> order1 = new ArrayList<>();
        order1.add(new Line(100, 100, 500, 100, Color.WHITE, thickness, Line.SHARP));
        order1.add(new Line(100, 600, 500, 600, Color.BLUE, thickness, Line.DASH));

        List<DrawingObject> order2 = new ArrayList<>();
        order2.add(new Line(200, 100, 200, 600, Color.RED, thickness));

        orders.add(new DrawingOrder(order1, 0, 500));
        orders.add(new DrawingOrder(order2, 1500, 2000));

        binding.btnTest.setOnClickListener(v -> {
            binding.drawing.startOrders(orders);
//            binding.drawing.startOrder(orders.get(0), 500);
        });
    }
}