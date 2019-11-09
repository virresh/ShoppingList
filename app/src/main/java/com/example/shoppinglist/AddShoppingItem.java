package com.example.shoppinglist;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppinglist.Item.ShoppingItem;
import com.example.shoppinglist.dbutils.AppDatabase;

public class AddShoppingItem extends AppCompatActivity {
    private class freshItemAddTask extends AsyncTask<Object, Void, Void>{
        Context appCtx;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//             if(appCtx instanceof IMethodCaller){
//                ((IMethodCaller) appCtx).updateViews();
                setResult(RESULT_OK);
//            }
            finish();
        }

        @Override
        protected Void doInBackground(Object... contexts) {
            appCtx = (Context) contexts[0];
            ShoppingItem nitem = (ShoppingItem) contexts[1];
            AppDatabase.getInstance(appCtx).shoppingItemDAO().addItem(nitem);
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_item);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String itemName = ((EditText) findViewById(R.id.itemName)).getText().toString();
                    String itemDescription = ((EditText) findViewById(R.id.itemDesc)).getText().toString();
                    Float itemAmt = Float.parseFloat(((EditText) findViewById(R.id.itemAmt)).getText().toString());
                    String itemUnit = ((EditText) findViewById(R.id.itemUnit)).getText().toString();
                    boolean done = ((Switch) findViewById(R.id.switch1)).isChecked();

                    ShoppingItem nitem = new ShoppingItem(itemName, itemDescription, itemAmt, itemUnit, done);
                    AsyncTask t = new freshItemAddTask();
                    t.execute(getApplicationContext(), nitem);
                }
                catch (Exception ex){
                    Log.d("AddShoppingItem", "Stupid User! Trying to put empty items");
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        });
    }
}
