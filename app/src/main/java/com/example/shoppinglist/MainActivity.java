package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppinglist.Adapters.IMethodCaller;
import com.example.shoppinglist.Fragments.DashboardTabsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements IMethodCaller {

    static final int ADD_ITEM = 128;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DashboardTabsFragment fragment = new DashboardTabsFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        FloatingActionButton fab = this.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddShoppingItem.class);
                startActivityForResult(i, ADD_ITEM);
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ITEM){
            if(resultCode == RESULT_OK){
                updateViews();
                Toast.makeText(getApplicationContext(), "Item successfully added!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Item not added!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void updateViews(){
        DashboardTabsFragment dfrag = (DashboardTabsFragment) getSupportFragmentManager().getFragments().get(0);
        dfrag.dataUpdated();
    }
}
