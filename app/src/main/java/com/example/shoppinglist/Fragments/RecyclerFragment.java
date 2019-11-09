package com.example.shoppinglist.Fragments;

import android.content.Context;
import android.os.AsyncTask;
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
import com.example.shoppinglist.Item.ShoppingItem;
import com.example.shoppinglist.R;
import com.example.shoppinglist.dbutils.AppDatabase;

import java.util.List;

public class RecyclerFragment extends Fragment {
    private RecyclerView todolist;
    private RecyclerView.Adapter mtodolist;
    private RecyclerView.LayoutManager layoutManager;
    private List<ShoppingItem> data;

    private class dataTask extends AsyncTask<Object, Void, List<ShoppingItem> >{

        @Override
        protected void onPostExecute(List<ShoppingItem> shoppingItems) {
            super.onPostExecute(shoppingItems);
            data = shoppingItems;
            Log.d("RECYCLER FRAGMENT", ""+data.size() + " " + getArguments().getInt("TAB_NUM"));
            mtodolist = new RecyclerShoppingAdapter(data, getActivity());
            todolist.setAdapter(mtodolist);
        }

        @Override
        protected List<ShoppingItem> doInBackground(Object... objects) {
            Context ctx = (Context) objects[0];
            int tabnum = (int) objects[1];
            List<ShoppingItem> items = null;
            switch (tabnum){
                case 1: items = AppDatabase.getInstance(ctx).shoppingItemDAO().getCurrentItems(); break;
                case 2: items = AppDatabase.getInstance(ctx).shoppingItemDAO().getDoneItems(); break;
            }
            return items;
        }
    }

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
        if(getArguments() != null) {
            Log.d("RECYCLER FRAGMENT", "I'm here");
            int tabnum = getArguments().getInt("TAB_NUM");
            AsyncTask inittask = new dataTask();
            inittask.execute(getContext(), tabnum);
        }
        else{
            Log.d("RECYCLER FRAGMENT", "No arguments recieved!");
        }
    }

}
