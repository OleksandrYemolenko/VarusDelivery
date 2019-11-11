package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.content.Intent;
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
    private Intent intent;
    public static int category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.dishRecView);

        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        adapter = new DishRecyclerAdapter(context);
        recyclerView.setAdapter(adapter);

        intent = getIntent();
        category = intent.getIntExtra("category", 0);

        Slidr.attach(this);
    }
}
