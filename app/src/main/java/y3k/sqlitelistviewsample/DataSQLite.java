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

    /**
     * 針對自己設計的資料結構實作這樣的function。
     * @param newData 要插入的資料。
     */
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

    /**
     * addDatas這個function也可以用這種寫法，看個人習慣怎麼寫。
     * @param newData 要插入的資料。
     */
    public void addDatasRawSQL(ArrayList<Data> newData){
        SQLiteDatabase database = DataSQLite.this.getWritableDatabase();
        for(Data data : newData){
            database.execSQL("insert into data (name,longitude,latitude) values ("+data.name+","+data.longitude+","+data.latitude+");");
        }
    }

    /**
     * 也建議設計這樣的function直接呼叫用來清除資料或其他動作。
     */
    public void clearDatas(){
        this.getWritableDatabase().execSQL("delete from data");
    }

    /**
     * 針對自己設計的資料結構實作全部倒出來的function。
     * @return
     */
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

    /**
     * 設計這個function直接取得在指定範圍內的資料。
     * @param minLongitude 指定區間的最小Longitude
     * @param minLatitude 指定區間的最小Latitude
     * @param maxLongitude 指定區間的最大Longitude
     * @param maxLatitude 指定區間的的最大Latitude
     * @return
     */
    public ArrayList<Data> findDataFitsSquare(long minLongitude, long minLatitude, long maxLongitude, long maxLatitude){
        ArrayList<Data> results = new ArrayList<>();
        // TODO : 用SQL的where語法去搜尋。
        return results;
    }
}
