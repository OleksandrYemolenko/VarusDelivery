package KNU_kitkat.varusdelivery.ui.order;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import KNU_kitkat.varusdelivery.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private OrderRecyclerAdapter adapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.orderRecView);
        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        adapter = new OrderRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}