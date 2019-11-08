package com.example.shoppinglist.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.Adapters.RecyclerShoppingAdapter;
import com.example.shoppinglist.AddShoppingItem;
import com.example.shoppinglist.Item.ShoppingItem;
import com.example.shoppinglist.R;
import com.example.shoppinglist.dbutils.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RecyclerFragment extends Fragment {
    private RecyclerView todolist;
    private RecyclerView.Adapter mtodolist;
    private RecyclerView.LayoutManager layoutManager;
    private List<ShoppingItem> data;

    @Override
    public View onCreateView(LayoutInflater inflater,
                                ViewGroup container,
                                Bundle savedInstanceState){
        return inflater.inflate(R.layout.recycler_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        todolist = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(view.getContext());
        todolist.setLayoutManager(layoutManager);

        int tabnum = savedInstanceState.getInt("TAB_NUM");
        switch (tabnum){
            case 1: data = AppDatabase.getInstance(getContext()).shoppingItemDAO().getCurrentItems(); break;
            case 2: data = AppDatabase.getInstance(getContext()).shoppingItemDAO().getDoneItems(); break;
        }

        mtodolist = new RecyclerShoppingAdapter(data);
        todolist.setAdapter(mtodolist);
    }

}
