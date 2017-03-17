package y3k.sqlitelistviewsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataSQLite extends SQLiteOpenHelper {
    public DataSQLite(Context context){
        super(context,"MyData",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists data(name text,longitude integer,latitude integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Data> getAllDatas(){
        Cursor readCursor = DataSQLite.this.getReadableDatabase().rawQuery("select * from data;",null);
        ArrayList<Data> result = new ArrayList<>();
        if(readCursor.getCount()>0){
            readCursor.moveToFirst();
            while (!readCursor.isAfterLast()){
                int nameIndex = readCursor.getColumnIndex("name");
                String name = readCursor.getString(nameIndex);
                int longitudeIndex = readCursor.getColumnIndex("longitude");
                long longitude = readCursor.getLong(longitudeIndex);
                int latitudeIndex = readCursor.getColumnIndex("latitude");
                long latitude = readCursor.getLong(latitudeIndex);
                Data readData = new Data(name,longitude,latitude);
                result.add(readData);
                readCursor.moveToNext();
            }
        }
        readCursor.close();
        return result;
    }

    public void addDatas(ArrayList<Data> newData){
        SQLiteDatabase database = DataSQLite.this.getWritableDatabase();
        for(Data data : newData){
            ContentValues values = new ContentValues();
            values.put("name",data.name);
            values.put("longitude",data.longitude);
            values.put("latitude",data.latitude);
            database.insert("data",null,values);
        }
    }

    public void addDatasRawSQL(ArrayList<Data> newData){
        SQLiteDatabase database = DataSQLite.this.getWritableDatabase();
        for(Data data : newData){
            database.execSQL("insert into data (name,longitude,latitude) values ("+data.name+","+data.longitude+","+data.latitude+");");
        }
    }

    public void clearDatas(){
        this.getWritableDatabase().execSQL("delete from data");
    }
}
