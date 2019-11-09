package com.example.shoppinglist.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.Item.ShoppingItem;
import com.example.shoppinglist.R;
import com.example.shoppinglist.dbutils.AppDatabase;

import java.util.List;

public class RecyclerShoppingAdapter extends RecyclerView.Adapter<RecyclerShoppingAdapter.MyViewHolder> {

    private List<ShoppingItem> mDataset;
    private Context mContext;

    public RecyclerShoppingAdapter(List<ShoppingItem> myDataset, Context ctx){
        this.mContext = ctx;
        mDataset = myDataset;
    }

    private class shoppingItemUpdateTask extends AsyncTask<Object, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            notifyDataSetChanged();
            if (mContext instanceof IMethodCaller){
                ((IMethodCaller) mContext).updateViews();
                Log.d("RECYCLERADAPTER", "Update Views called!");
            }
            else{
                Log.d("RECYCLERADAPTER", "Base Activity does not implement IMethod");
            }

        }
        @Override
        protected Void doInBackground(Object... shoppingItems) {
            Context done = (Context) shoppingItems[0];
            ShoppingItem item = (ShoppingItem) shoppingItems[1];
            boolean isChecked = (boolean) shoppingItems[2];
            AppDatabase.getInstance(done).shoppingItemDAO().updateItem(item);
            if(isChecked){
                mDataset = AppDatabase.getInstance(done).shoppingItemDAO().getCurrentItems();
            }
            else{
                mDataset = AppDatabase.getInstance(done).shoppingItemDAO().getDoneItems();
            }
            return null;
        }
    }

    private class shoppingItemDeleteTask extends AsyncTask<Object, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            notifyDataSetChanged();
            if (mContext instanceof IMethodCaller){
                ((IMethodCaller) mContext).updateViews();
                Log.d("RECYCLERADAPTER", "Update Views called!");
            }
            else{
                Log.d("RECYCLERADAPTER", "Base Activity does not implement IMethod");
            }

        }
        @Override
        protected Void doInBackground(Object... shoppingItems) {
            Context done = (Context) shoppingItems[0];
            ShoppingItem item = (ShoppingItem) shoppingItems[1];
            boolean isChecked = (boolean) shoppingItems[2];
            AppDatabase.getInstance(done).shoppingItemDAO().deleteItem(item.getId());
            if(isChecked){
                mDataset = AppDatabase.getInstance(done).shoppingItemDAO().getCurrentItems();
            }
            else{
                mDataset = AppDatabase.getInstance(done).shoppingItemDAO().getDoneItems();
            }
            return null;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemName;
        public TextView itemDescription;
        public Switch done;
        public TextView quantity;
        public Button button;
        public MyViewHolder(LinearLayout v) {
            super(v);
            itemName = v.findViewById(R.id.item_number);
            itemDescription = v.findViewById(R.id.content);
            quantity = v.findViewById(R.id.quantity);
            done = v.findViewById(R.id.done);
            button = v.findViewById(R.id.button2);
        }

    }

    @Override
    public RecyclerShoppingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType){
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shopping_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ShoppingItem item = mDataset.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemDescription.setText(item.getItemDescription());
        holder.done.setChecked(item.isDone());
        holder.quantity.setText(String.format("%.2f", item.getAmount()) + " " + item.getUnit());
        holder.done.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton done, boolean isChecked){
                if(done.isPressed()){
                    if((item.isDone() == true && isChecked == false) || (item.isDone() == false && isChecked == true)){
                        item.setDone(isChecked);
                        shoppingItemUpdateTask task  = new shoppingItemUpdateTask();
                        task.execute(done.getContext(), item, isChecked);
                    }
                }
            }
        });

        holder.button.setOnClickListener((v)->{
            if(v.isPressed()){
                shoppingItemDeleteTask t = new shoppingItemDeleteTask();
                t.execute(v.getContext(), item, item.isDone());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset == null){
            return 0;
        }
        return mDataset.size();
    }
}
