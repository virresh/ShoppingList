package com.example.shoppinglist.Item;

import android.content.ContentValues;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoppingItems")
public class ShoppingItem {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String itemName;
    private String itemDescription;
    private float amount;
    private String unit;
    private boolean done;

    public ShoppingItem(){

    }

    public ShoppingItem(long id, String itemName, String itemDescription, float amount, String unit, boolean done) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.amount = amount;
        this.unit = unit;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static ShoppingItem fromContentValues(ContentValues values) {
        final ShoppingItem item = new ShoppingItem();
        if (values.containsKey("itemName")) {
            item.setItemName(values.getAsString("itemName"));
        }
        if (values.containsKey("itemDescription")) {
            item.setItemDescription(values.getAsString("itemDescription"));
        }
        if (values.containsKey("amount")) {
            item.setAmount(values.getAsFloat("amount"));
        }
        if (values.containsKey("unit")) {
            item.setUnit(values.getAsString("unit"));
        }
        if (values.containsKey("done")) {
            item.setDone(values.getAsBoolean("done"));
        }
        if (values.containsKey("id")) {
            item.setId(values.getAsInteger("itemName"));
        }
        return item;
    }

}
