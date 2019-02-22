package com.erik.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.erik.recycleview.contoh.SampleDataProvider;
import com.erik.recycleview.model.DataItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DataItem> dataItemList = SampleDataProvider.dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DataItem dataItem = new DataItem(null, "Iphone 10",
//                "Iphone dapat dibuka dengan wajah.", "99",
//                0, 200000.5, "iphone.jpg");
//
//        tvOut.setText(dataItem.toString());

        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);
        RecyclerView recyclerView = findViewById(R.id.rvList);
        recyclerView.setAdapter(adapter);
    }
}
