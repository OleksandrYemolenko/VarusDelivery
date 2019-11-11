package KNU_kitkat.varusdelivery;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DishRecyclerAdapter extends RecyclerView.Adapter<DishRecyclerAdapter.RecyclerViewHolder> {
    private int category;
    private Context context;
    ArrayList<DishItem> items = new ArrayList<>();

    public DishRecyclerAdapter(Context context, int category) {
        this.context = context;
        this.category = category;

        items = StartScreenActivity.products.get(category);
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
                Intent i = new Intent(context, DishPageActivity.class);
                i.putExtra("dishItem.id", dishItem.getId());
                i.putExtra("dishItem.category", dishItem.getCategory());
                i.putExtra("dishItem.price", dishItem.getPrice());
                i.putExtra("dishItem.name", dishItem.getName());
                i.putExtra("dishItem.img", dishItem.getImg());
                i.putExtra("dishItem.description", dishItem.getDescription());

                StartScreenActivity.viewed.add(dishItem);

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        try{
            return items.size();
        } catch (Exception e) {
            return 0;
        }
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
