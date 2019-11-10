package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.os.Bundle;

import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DishActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private DishRecyclerAdapter adapter;
    private Context context;
    public static ArrayList<DishItem> items_1 = new ArrayList<>();
    public static ArrayList<DishItem> items_2 = new ArrayList<>();
    public static ArrayList<DishItem> items_3 = new ArrayList<>();
    public static ArrayList<DishItem> items_4 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        context = this;

        if(true) {
            items_1.add(new DishItem("Апельсиновый", "https://healthynibblesandbits.com/wp-content/uploads/2016/11/How-to-Cut-a-Pomegranate-FF.jpg", 0, 1, 50, "asd"));

        }
        recyclerView = (RecyclerView) findViewById(R.id.dishRecView);

        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        adapter = new DishRecyclerAdapter(context);
        recyclerView.setAdapter(adapter);

        Slidr.attach(this);
    }
}
