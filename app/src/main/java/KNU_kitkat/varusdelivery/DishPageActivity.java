package KNU_kitkat.varusdelivery;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.r0adkll.slidr.Slidr;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DishPageActivity extends AppCompatActivity {

    private ImageView img;
    private TextView name, price, desc;

    private Intent intent;
    private DishItem dishItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        getDishItem(intent);

        name = (TextView) findViewById(R.id.nameDishPage);
        price = (TextView) findViewById(R.id.priceDishPage);
        desc = (TextView) findViewById(R.id.descDishPage);
        img = (ImageView) findViewById(R.id.imageToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bind();
    }

    private void bind() {
        Picasso.with(this).load(dishItem.getImg()).into(img);
        name.setText(dishItem.getName());
        desc.setText(dishItem.getDescription());
        price.setText(dishItem.getPrice()/100 + "₴");
        Slidr.attach(this);
    }

    private void getDishItem(Intent intent) {
        dishItem = new DishItem();

        dishItem.setId(intent.getIntExtra("dishItem.id", 0));
        dishItem.setCategory(intent.getIntExtra("dishItem.category", 0));
        dishItem.setPrice(intent.getDoubleExtra("dishItem.price", 0));
        dishItem.setName(intent.getStringExtra("dishItem.name"));
        dishItem.setImg(intent.getStringExtra("dishItem.img"));
        dishItem.setDescription(intent.getStringExtra("dishItem.description"));
    }
}
