package com.example.shoppinglist;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppinglist.Item.ShoppingItem;
import com.example.shoppinglist.dbutils.AppDatabase;

public class ShoppingItemProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.shoppinglist.items";
    public static final String TABLE_NAME = "shoppingItems";
    public static final Uri URI_ITEM = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

    public ShoppingItemProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (getContext() != null){
            final int count = AppDatabase.getInstance(getContext()).shoppingItemDAO().deleteItem(ContentUris.parseId(uri));
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        throw new IllegalArgumentException("Failed to delete row into " + uri);
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.item/" + AUTHORITY + "." + TABLE_NAME;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (getContext() != null){
            final long id = AppDatabase.getInstance(getContext()).shoppingItemDAO().addItem(ShoppingItem.fromContentValues(values));
            if (id != 0){
                // more than one rows got affected
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
        }

        throw new IllegalArgumentException("Failed to insert row into " + uri);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (getContext() != null){
            long itemId = ContentUris.parseId(uri);
            final Cursor cursor = AppDatabase.getInstance(getContext()).shoppingItemDAO().getItem(itemId);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }

        throw new IllegalArgumentException("Failed to query row for uri " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        if (getContext() != null){
            final int count = AppDatabase.getInstance(getContext()).shoppingItemDAO().updateItem(ShoppingItem.fromContentValues(values));
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        throw new IllegalArgumentException("Failed to update row into " + uri);
    }


}
