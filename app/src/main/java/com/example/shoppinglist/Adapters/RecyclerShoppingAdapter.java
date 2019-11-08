package com.example.shoppinglist.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.Item.ShoppingItem;
import com.example.shoppinglist.R;

import java.util.List;

public class RecyclerShoppingAdapter extends RecyclerView.Adapter<RecyclerShoppingAdapter.MyViewHolder> {

    private List<ShoppingItem> mDataset;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemName;
        public TextView itemDescription;
        public Switch done;
        public TextView quantity;
        public MyViewHolder(LinearLayout v) {
            super(v);
            itemName = v.findViewById(R.id.item_number);
            itemDescription = v.findViewById(R.id.content);
            quantity = v.findViewById(R.id.quantity);
            done = v.findViewById(R.id.done);
        }
    }

    public RecyclerShoppingAdapter(List<ShoppingItem> myDataset){
        mDataset = myDataset;
    }

    @Override
    public RecyclerShoppingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType){
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shopping_item, parent, false);
        // ...
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ShoppingItem item = mDataset.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemDescription.setText(item.getItemDescription());
        holder.done.setChecked(item.isDone());
        holder.quantity.setText(String.format("%0.2f", item.getAmount()) + " " + item.getUnit());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
