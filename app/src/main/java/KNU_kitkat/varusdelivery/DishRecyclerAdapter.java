package KNU_kitkat.varusdelivery;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DishRecyclerAdapter extends RecyclerView.Adapter<DishRecyclerAdapter.RecyclerViewHolder> {
    private int category;
    private Context context;
    ArrayList<DishItem> items = new ArrayList<>();

    public DishRecyclerAdapter(Context context) {
        this.context = context;
        category = 0;

        this.items = DishActivity.items_1;

    }

    public void addAll(List<DishItem> items) {
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(pos, this.items.size());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final DishItem dishItem = items.get(position);

        holder.bind(dishItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title, price;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);

            title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));
            price.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));


            image = (ImageView) itemView.findViewById(R.id.imgDish);

            //image.setImageResource(R.drawable.no_img); //
        }

        public void bind(final DishItem recyclerItem) {
            title.setText(recyclerItem.getName());
            price.setText(Double.toString(recyclerItem.getPrice() / 100) + '₴');
            Picasso.with(context).load(recyclerItem.getImg()).into(image);
        }
    }
}
