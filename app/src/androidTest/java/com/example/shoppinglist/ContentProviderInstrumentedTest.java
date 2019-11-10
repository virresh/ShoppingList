package com.example.shoppinglist;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.shoppinglist.dbutils.AppDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

// Reference: https://openclassrooms.com/en/courses/4561586-manage-your-data-to-have-a-100-offline-android-app/5770976-expose-your-data-with-a-contentprovider

@RunWith(AndroidJUnit4.class)
public class ContentProviderInstrumentedTest {

    // FOR DATA
    private ContentResolver mContentResolver;

    // DATA SET FOR TEST
    private static long USER_ID = 9999;

    @Before
    public void setUp() {
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        mContentResolver = InstrumentationRegistry.getContext().getContentResolver();
    }

    @Test
    public void getItemsWhenNoItemInserted() {
        final Cursor cursor = mContentResolver.query(
                ContentUris.withAppendedId(ShoppingItemProvider.URI_ITEM, USER_ID),
                null, null, null, null);
        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(0));
        cursor.close();
    }

    @Test
    public void insertAndGetItem() {
        // BEFORE : Adding demo item
        final Uri userUri = mContentResolver.insert(ShoppingItemProvider.URI_ITEM, generateItem());
        // TEST
        final Cursor cursor = mContentResolver.query(
                ContentUris.withAppendedId(ShoppingItemProvider.URI_ITEM, USER_ID),
                null, null, null, null);
        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(1));
        assertThat(cursor.moveToFirst(), is(true));
        assertThat(cursor.getString(
                cursor.getColumnIndexOrThrow("itemName")),
                is("iBanana!!!")
        );
    }


    private ContentValues generateItem(){
        final ContentValues values = new ContentValues();
        values.put("itemName", "iBanana!!!");
        values.put("itemDescription", "via Instrumented Test");
        values.put("amount", "3");
        values.put("unit", "dozen");
        values.put("done", false);
        values.put("id", USER_ID);
        return values;
    }
}
