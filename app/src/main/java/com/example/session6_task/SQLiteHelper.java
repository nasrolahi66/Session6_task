package com.example.session6_task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper  extends SQLiteOpenHelper {
    public SQLiteHelper(@Nullable Context context,@Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
      String tableName="movie";
    public class Movie
    {
        String title;
        String year;
        String type;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY="CREATE TABLE " + tableName + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "title TEXT ," +
                "year TEXT ," +
                "type TEXT" +
                "" +
                ")";
        db.execSQL(CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void  INSERT_MOVIE(String title,String year,String type)
    {
        String INSERT_QUERY="INSERT INTO " +tableName+
                "(title,year,type) VALUES ("
                +"'" +title+"'" +","
                +"'"+year+"'" + ","
                +"'" +type+"')";

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(INSERT_QUERY);
        db.close();
    }
    public List<Movie> GET_MOVIES( )
    {
        List<Movie> movies=new ArrayList<Movie>();

        String GET_MOVIE_QUERY="SELECT title, year ,type FROM "
                +tableName;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery(GET_MOVIE_QUERY,null);
        while (cursor.moveToNext())
        {
            Movie movie=new Movie();
            movie.title=cursor.getString(0);
            movie.year=cursor.getString(1);
            movie.type=cursor.getString(2);
            movies.add(movie);

        }
        db.close();
        return movies;


    }



    public void DELETE_MOVIE(String title,String year)
    {
        String DELETE_MOVIE_QUERY="DELETE FROM " +tableName+
        " WHERE "+"'" +title +"'" +"=title"
                + " AND " +"'" + year + "'" + "=year";
        SQLiteDatabase db=getReadableDatabase();
        db.execSQL(DELETE_MOVIE_QUERY);
        db.close();


    }




}
