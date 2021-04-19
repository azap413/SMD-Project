package com.example.project;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    public DBclass dbhelper;
    private SQLiteDatabase db;
    private static final String AUTHORITY = "com.example.project.MyContentProvider";
    public static final Uri CONTENT_URI1 = Uri.parse("content://" + AUTHORITY + "/"+"UserList");
    public static final Uri CONTENT_URI2 = Uri.parse("content://" + AUTHORITY + "/"+"HotelList");
    public static final Uri CONTENT_URI3 = Uri.parse("content://" + AUTHORITY + "/"+"PlaceList");
    public static final Uri CONTENT_URI4 = Uri.parse("content://" + AUTHORITY + "/"+"ReviewList");


    public static final UriMatcher uriMATCHER  = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMATCHER.addURI(AUTHORITY,"UserList",1);
        uriMATCHER.addURI(AUTHORITY,"HotelList",2);
        uriMATCHER.addURI(AUTHORITY,"PlaceList",3);
        uriMATCHER.addURI(AUTHORITY,"ReviewList",4);
    }
    @Override
    public boolean onCreate() {
        dbhelper = new DBclass(getContext());
        db = dbhelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        if (uriMATCHER.match(uri) == 1){
            cursor = db.query("UserList", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        }
        else if(uriMATCHER.match(uri) == 2){
            cursor = db.query("HotelList", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        }
        else if(uriMATCHER.match(uri) == 3){
            cursor = db.query("PlaceList", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        }
        else if(uriMATCHER.match(uri) == 4){
            cursor = db.query("ReviewList", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        }
        else throw new SQLException("Failed to query: "+ uri);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if(uriMATCHER.match(uri) == 1)
            return "vnd.andriod.cursor.dir/vnd.com.example.project.contentprovider.UserList";
        else if(uriMATCHER.match(uri) == 2)
            return "vnd.andriod.cursor.dir/vnd.com.example.project.contentprovider.HotelList";
        else if(uriMATCHER.match(uri) == 3)
            return "vnd.andriod.cursor.dir/vnd.com.example.project.contentprovider.PlaceList";
        else if(uriMATCHER.match(uri) == 4)
            return "vnd.andriod.cursor.dir/vnd.com.example.project.contentprovider.ReviewList";
        else throw new SQLException("Error in gettype()");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id;
        if(uriMATCHER.match(uri) == 1){
            id = db.insert("UserList",null,values);
            if(id > 0) {
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI1, id);// appends row ID to URI
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }
            else throw new UnsupportedOperationException("Row not inserted");
        }
        else if(uriMATCHER.match(uri) == 2){
            id = db.insert("HotelList",null,values);
            if(id>0) {
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI2, id);// appends row ID to URI
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }
            else throw new UnsupportedOperationException("Row not inserted");
        }
        else if(uriMATCHER.match(uri) == 3){
            id = db.insert("PlaceList",null,values);
            if(id >0) {
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI3, id);// appends row ID to URI
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }
            else throw new UnsupportedOperationException("Row not inserted");
        }
        else if(uriMATCHER.match(uri) == 4){
            id = db.insert("ReviewList",null,values);
            if(id >0) {
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI4, id);// appends row ID to URI
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }
            else throw new UnsupportedOperationException("Row not inserted");
        }
        else throw new UnsupportedOperationException("No matching table");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int delcount = 0;
        if(uriMATCHER.match(uri) == 1){
            delcount = db.delete("UserList",selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return delcount;
        }
        else if(uriMATCHER.match(uri) == 2){
            delcount = db.delete("HotelList",selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return delcount;
        }
        else if(uriMATCHER.match(uri) == 3){
            delcount = db.delete("PlaceList",selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return delcount;
        }
        else if(uriMATCHER.match(uri) == 4){
            delcount = db.delete("ReviewList",selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return delcount;
        }
        else throw new UnsupportedOperationException("No matching table");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updatecount = 0;
        if(uriMATCHER.match(uri) == 1){
            updatecount = db.update("UserList",values,selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return updatecount;
        }
        else if(uriMATCHER.match(uri) == 2){
            updatecount = db.update("HotelList",values,selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return updatecount;
        }
        else if(uriMATCHER.match(uri) == 3){
            updatecount = db.update("PlaceList",values,selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return updatecount;
        }
        else if(uriMATCHER.match(uri) == 4){
            updatecount = db.update("ReviewList",values,selection,selectionArgs);
            getContext().getContentResolver().notifyChange(uri,null);
            return updatecount;
        }
        else throw new UnsupportedOperationException("No matching table");
    }
}
