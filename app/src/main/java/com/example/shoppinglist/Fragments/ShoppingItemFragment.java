package com.example.shoppinglist.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppinglist.R;


public class ShoppingItemFragment extends Fragment {
    // No longer in use
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String ARG_OBJECT = "object";
    private TextView helloText;

    public ShoppingItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_item, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Bundle args = getArguments();
        helloText = ((TextView) view.findViewById(R.id.content));
        helloText.setText("Page " + Integer.toString(args.getInt(ARG_OBJECT)) + " Content");
    }
}
