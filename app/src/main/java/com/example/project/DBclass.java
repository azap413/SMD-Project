package com.example.project;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBclass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AppDB";
    private static final String USERS_TABLE = "UserList";
    private static final String HOTELS_TABLE = "HotelList";
    private static final String PLACES_TABLE = "PlaceList";
    private static final String REVIEWS_TABLE = "ReviewList";
    private String create_hotels = "CREATE TABLE " + HOTELS_TABLE
             + "(HOTEL_NAME TEXT PRIMARY KEY,LOCATION TEXT,FOREIGN KEY (LOCATION) REFERENCES "+PLACES_TABLE+"(PLACE_NAME))";
    private String create_users = "CREATE TABLE " + USERS_TABLE + " ("
            + "USER_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "USER_NAME" + " TEXT," + "PASSWORD"+ " TEXT, "+ "STAY_HOTEL"+ " INTEGER, "
            +" FOREIGN KEY (STAY_HOTEL) REFERENCES "+HOTELS_TABLE+"(HOTEL_ID))";
    private String create_places = "CREATE TABLE " + PLACES_TABLE + " (PLACE_NAME TEXT PRIMARY KEY,URL TEXT)";
    private String create_reviews = "CREATE TABLE " + REVIEWS_TABLE + " (REVIEW_ID INTEGER PRIMARY KEY AUTOINCREMENT,REVIEW TEXT, USER TEXT,LOCATION TEXT,FOREIGN KEY (LOCATION) REFERENCES "+PLACES_TABLE+"(PLACE_NAME))";

    public DBclass(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION); //creates DB
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_places);
        db.execSQL(create_hotels);
        db.execSQL(create_users);
        db.execSQL(create_reviews);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HOTELS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLACES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REVIEWS_TABLE);
        onCreate(db);
    }

}
