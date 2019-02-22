package com.erik.recycleview;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erik.recycleview.contoh.SampleDataProvider;
import com.erik.recycleview.model.DataItem;

import java.io.IOException;
import java.io.InputStream;


public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView namaP , hargaP , deskripsiP , kategoriP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.gambar);
        namaP = findViewById(R.id.namap);
        hargaP = findViewById(R.id.hargap);
        deskripsiP = findViewById(R.id.deskripsip);
        kategoriP = findViewById(R.id.kategorip);

        String itemId = getIntent().getStringExtra(DataItemAdapter.ITEM_ID_KEY);

        DataItem item = SampleDataProvider.dataItemMap.get(itemId);

        Toast.makeText(this, "Anda memilih: " + item.getItemName(), Toast.LENGTH_SHORT).show();
        String imageFile = item.getImage();

        try {
            namaP.setText(item.getItemName());
            kategoriP.setText(item.getDescription());
            hargaP.setText(Utils.formatIDR("Rp.", (long) item.getPrice()));
            deskripsiP.setText(item.getCategory());
            InputStream inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream,null);
            imageView.setImageDrawable(d);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}