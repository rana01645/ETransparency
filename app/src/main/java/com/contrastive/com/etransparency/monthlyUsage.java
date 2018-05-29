package com.contrastive.com.etransparency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class monthlyUsage extends AppCompatActivity {
    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_usage);

        graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1, 50),
                new DataPoint(2, 45),
                new DataPoint(3, 47),
                new DataPoint(4, 50),
                new DataPoint(5, 40),
                new DataPoint(6, 39),
                new DataPoint(7, 39),
                new DataPoint(8, 38),
                new DataPoint(9, 35),
                new DataPoint(10, 36),
        });

        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(10);
//        graph.getViewport().setScrollable(true);
        graph.addSeries(series);


    }
}
