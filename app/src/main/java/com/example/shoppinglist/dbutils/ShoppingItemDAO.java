package com.example.shoppinglist.dbutils;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppinglist.Item.ShoppingItem;

import java.util.List;

@Dao
public interface ShoppingItemDAO {
    @Query("SELECT * FROM shoppingItems WHERE done = 1")
    List<ShoppingItem> getDoneItems();

    @Query("SELECT * FROM shoppingItems WHERE done = 0")
    List<ShoppingItem> getCurrentItems();

    @Query("SELECT * FROM shoppingItems")
    List<ShoppingItem> getAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addItem(ShoppingItem item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateItem(ShoppingItem item);

    @Query("DELETE from shoppingItems WHERE id = :id")
    int deleteItem(long id);

    @Query("SELECT * from shoppingItems WHERE id = :id")
    Cursor getItem(long id);

}
