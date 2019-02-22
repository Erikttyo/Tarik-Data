package com.erik.recycleview;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

import com.erik.recycleview.model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Firman on 2/8/2019.
 */
public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    public static final String ITEM_ID_KEY = "KEY_ITEM_ID";
    private List<DataItem> mItems;
    private Context mContext;

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataItem item = mItems.get(position);

        try {
            holder.itemName.setText(item.getItemName());
            holder.itemName.setText(item.getItemName());
            holder.price.setText(Utils.formatIDR("Rp.", (long) item.getPrice()));
            holder.description.setText(item.getDescription());
            String imageFile = item.getImage();
            InputStream inputStream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.gambar.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Anda click: "+item.getItemName(), Toast.LENGTH_SHORT).show();

                String itemId = item.getItemId();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(ITEM_ID_KEY, itemId);
                mContext.startActivity(intent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "Anda click long: "+item.getItemName(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView price;
        TextView description;
        ImageView gambar;

        View mView;

        ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;

            itemName = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);

            gambar = itemView.findViewById(R.id.gambar);
        }
    }
}