package KNU_kitkat.varusdelivery.ui.goods;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import KNU_kitkat.varusdelivery.DishActivity;
import KNU_kitkat.varusdelivery.R;
import KNU_kitkat.varusdelivery.StartScreenActivity;
import KNU_kitkat.varusdelivery.ui.Item;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.RecyclerViewHolder> {
    private Context context;
    public static int categoty;
    private ArrayList<Item> items = new ArrayList<>();

    public MenuRecyclerAdapter(Context context) {
        this.context = context;

        items = StartScreenActivity.categories;
    }

    public void addAll(List<Item> items) {
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(pos, this.items.size());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final Item item = items.get(position);

        holder.bind(item);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DishActivity.class);
                i.putExtra("category", item.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            title.setTypeface(Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf"));

            image = (ImageView) itemView.findViewById(R.id.imgMenu);
        }

        public void bind(final Item recyclerItem) {

            title.setText(recyclerItem.getName());
            // image.setImageResource(R.drawable.ic_dashboard_black_24dp);
            Picasso.with(context.getApplicationContext()).load(recyclerItem.getImg()).into(image);
        }
    }
}
