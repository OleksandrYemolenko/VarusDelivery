package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import KNU_kitkat.varusdelivery.rest.*;
import KNU_kitkat.varusdelivery.ui.Item;
import KNU_kitkat.varusdelivery.ui.goods.GoodsFragment;
import KNU_kitkat.varusdelivery.ui.viewed.ViewedFragment;
import KNU_kitkat.varusdelivery.ui.order.OrderFragment;
import KNU_kitkat.varusdelivery.ui.delivery.DeliveryFragment;
import KNU_kitkat.varusdelivery.ui.cart.CartFragment;



import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StartScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String STORAGE_NAME = "STORAGE";
    public static final String base = "https://varus-delivery.herokuapp.com/";
    public static final MediaType JSON = MediaType.parse("application/json");
    public static ArrayList<DishItem> viewed = new ArrayList<>();
    public static ArrayList<Item> categories = new ArrayList<>();
    public static HashMap<Integer, ArrayList<DishItem>> products = new HashMap<Integer, ArrayList<DishItem>>();
    

    private AppBarConfiguration mAppBarConfiguration;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private ActionBarDrawerToggle toggle;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //It works cause of this line
        navigationView.bringToFront();
        //Start page
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_goods));


        fab = findViewById(R.id.fabCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getCategories();
        getAllProducts();
    }

    private void getAllProducts() {
        JSONObject result = new JSONObject();

        MyRequest request = new MyRequest(new JSONObject(), MyRequest.COMMON + MyRequest.GET_ALL_PRODUCTS, new JSONObject());

        GetRequestController controller = new GetRequestController();
        controller.execute(request);
        try {
            result = controller.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray array = result.getJSONObject("information").getJSONArray("products");
            print(array.length() + "", this);
            for(int i = 0; i < array.length(); ++i) {
                JSONObject item = array.getJSONObject(i);

                String name = item.getString("name");
                String img = item.getString("img");
                String desc = item.getString("description");
                int id = item.getInt("id");
                int category = item.getInt("category");
                double price = item.getDouble("price");


                products.get(category).add(new DishItem(name, img, id, category, price, desc));
            }
        } catch (JSONException e) {
            print("made",  this);
            e.printStackTrace();
        }
    }

    private void getCategories() {
        JSONObject result = new JSONObject();

        MyRequest request = new MyRequest(new JSONObject(), MyRequest.COMMON + MyRequest.GET_CATEGORIES, new JSONObject());

        GetRequestController controller = new GetRequestController();
        controller.execute(request);
        try {
            result = controller.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray array = result.getJSONObject("information").getJSONArray("categories");
            print(array.length() + "", this);
            for(int i = 0; i < array.length(); ++i) {
                JSONObject item = array.getJSONObject(i);

                String name = item.getString("name");
                String img = item.getString("img");
                int id = item.getInt("id");

                ArrayList<DishItem> items = new ArrayList<>();

                products.put(id, items);

                categories.add(new Item(id, name, img));
            }
        } catch (JSONException e) {
            print("made",  this);
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_goods) {
            fragmentClass = GoodsFragment.class;
        } else if (id == R.id.nav_viewed) {
            fragmentClass = ViewedFragment.class;
        } else if (id == R.id.nav_orders) {
            fragmentClass = OrderFragment.class;
        } else if (id == R.id.nav_delivery) {
            fragmentClass = DeliveryFragment.class;
        } else if (id == R.id.nav_cart) {
            fragmentClass = CartFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void print(String mMessage, Context context) {
        Toast.makeText(context, mMessage, Toast.LENGTH_LONG).show();
        System.out.println(mMessage);
    }
}
