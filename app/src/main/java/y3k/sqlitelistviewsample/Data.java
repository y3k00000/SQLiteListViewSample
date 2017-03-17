package y3k.sqlitelistviewsample;

/**
 * Created by y3k on 2017/3/17.
 * 隨便瞎掰的資料結構。
 */
public class Data{
    public String name;
    public long longitude, latitude;
    public Data(String name, long longitude, long latitude){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String toDisplayString(){
        return "name : "+this.name+", longitude : "+this.longitude+", latitude : "+this.latitude+" .";
    }
}
