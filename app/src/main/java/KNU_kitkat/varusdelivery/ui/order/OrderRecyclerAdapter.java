package KNU_kitkat.varusdelivery.ui.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import KNU_kitkat.varusdelivery.DishActivity;
import KNU_kitkat.varusdelivery.OrderItem;
import KNU_kitkat.varusdelivery.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.RecyclerViewHolder>{
    private Context context;
    public static int categoty;
    private ArrayList<OrderItem> items = new ArrayList<>();

    public OrderRecyclerAdapter(Context context) {
        this.context = context;

        items.add(new OrderItem(0, -1, new Date(), 500, 0, 0, 0, null));
        items.add(new OrderItem(0, 0, new Date(), 500, 0, 0, 0, null));
        items.add(new OrderItem(1, 1, new Date(), 500, 0, 0, 0, null));
    }

    public void addAll(List<OrderItem> items) {
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(pos, this.items.size());
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new OrderRecyclerAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderRecyclerAdapter.RecyclerViewHolder holder, final int position) {
        final OrderItem item = items.get(position);

        holder.bind(item);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DishActivity.class);
                i.putExtra("itemInfo", item);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView date, price;
        private ImageView color;
        private CardView cardView;
        private int status;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.dateOrder);
            price = (TextView) itemView.findViewById(R.id.priceOrder);
            cardView = (CardView) itemView.findViewById(R.id.cardOrder);
            color = (ImageView) itemView.findViewById(R.id.colorOrder);
        }

        public void bind(final OrderItem recyclerItem) {
            date.setText(recyclerItem.getDate().toString());
            price.setText(Double.toString(recyclerItem.getPrice()) + '₴');

            status = recyclerItem.getStatus();

            setStatus(status);
        }

        void setStatus(int status) {
            if(status == -1)
                color.setBackgroundColor(context.getResources().getColor(R.color.cardRed));
            else if(status == 0)
                color.setBackgroundColor(context.getResources().getColor(R.color.cardGrey));
            else if(status == 1)
                color.setBackgroundColor(context.getResources().getColor(R.color.cardGreen));
        }
    }
}
