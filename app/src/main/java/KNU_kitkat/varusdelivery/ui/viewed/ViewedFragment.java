package KNU_kitkat.varusdelivery.ui.viewed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import KNU_kitkat.varusdelivery.ui.goods.MenuRecyclerAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import KNU_kitkat.varusdelivery.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewedFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ViewedRecyclerAdapter adapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_goods, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.menuRecView);
        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        adapter = new ViewedRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}