package me.penguinpistol.analysisdrawing;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.penguinpistol.analysisdrawing.databinding.ActivityMainBinding;
import me.penguinpistol.analysisdrawing.drawing.DrawingOrder;
import me.penguinpistol.analysisdrawing.drawing.object.Arrow;
import me.penguinpistol.analysisdrawing.drawing.object.Circle;
import me.penguinpistol.analysisdrawing.drawing.object.DrawingObject;
import me.penguinpistol.analysisdrawing.drawing.object.Line;
import me.penguinpistol.analysisdrawing.drawing.object.JointLine;
import me.penguinpistol.analysisdrawing.drawing.object.Shape;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<DrawingOrder> orders = new ArrayList<>();

        float thickness = 1 * getResources().getDisplayMetrics().density;
        float radius = 10 * getResources().getDisplayMetrics().density;

        List<DrawingObject> order1 = new ArrayList<>();
        order1.add(new Line(100, 100, 500, 100, Color.WHITE, thickness, Line.SHARP));
        order1.add(new Line(100, 600, 500, 600, Color.BLUE, thickness, Line.DASH));
        order1.add(new Arrow(100, 800, 500, 700, Color.GREEN));
        order1.add(new Circle(Color.MAGENTA, Color.WHITE, 500, 500, radius));

        List<PointF> shape = new ArrayList<>();
        shape.add(new PointF(100, 100));
        shape.add(new PointF(800, 100));
        shape.add(new PointF(850, 150));
        shape.add(new PointF(800, 200));
        shape.add(new PointF(400, 200));
        shape.add(new PointF(400, 500));
        shape.add(new PointF(350, 550));
        shape.add(new PointF(300, 500));
        shape.add(new PointF(300, 200));
        shape.add(new PointF(100, 200));
        shape.add(new PointF(50, 150));
        order1.add(new Shape(Color.parseColor("#C0FFFFFF"), shape));

        List<PointF> path = new ArrayList<>();
        path.add(new PointF(350, 350));
        path.add(new PointF(450, 550));
        path.add(new PointF(350, 750));
        path.add(new PointF(750, 550));
//        path.add(new PointF(350, 350));

        List<DrawingObject> order2 = new ArrayList<>();
        order2.add(new Line(200, 100, 200, 600, Color.RED, thickness));
//        order2.add(new JointLine(Color.YELLOW, path, thickness, true));
        order2.add(new JointLine(Color.YELLOW, path, thickness));

        orders.add(new DrawingOrder(order1, 0, 500));
        orders.add(new DrawingOrder(order2, 500, 1000));

        binding.btnTest.setOnClickListener(v -> {
            binding.drawing.startOrders(orders);
//            binding.drawing.startOrder(orders.get(0), 500);
        });
    }
}