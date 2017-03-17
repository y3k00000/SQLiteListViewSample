package y3k.sqlitelistviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DataSQLite dataSQLite;
    DataListViewAdapter dataListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.dataSQLite = new DataSQLite(MainActivity.this);
        MainActivity.this.dataListViewAdapter = new DataListViewAdapter();
        // ADD Button點擊會新增一筆隨機資料並把列表更新。
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Data newData = new Data("Point "+random.nextInt(),random.nextLong(),random.nextLong());
                ArrayList<Data> newDatas = new ArrayList<>();
                newDatas.add(newData);
                MainActivity.this.dataSQLite.addDatas(newDatas);

                MainActivity.this.refreshListViewWithDatabaseDatas();
            }
        });
        // CLEAR Button點擊會清除資料庫中的資料並把列表更新。
        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.dataSQLite.clearDatas();
                MainActivity.this.refreshListViewWithDatabaseDatas();
            }
        });
        ((ListView)findViewById(R.id.list_view)).setAdapter(MainActivity.this.dataListViewAdapter);
    }

    /**
     * 這種可以被包裹成一個步驟的動作，就要寫成這樣的一個function，方便很多也少記很多事情。(抽象化)
     * 當然名稱不一定要這麼囉嗦，但是建議要三個月內看到都可以反射性的知道是幹嘛為標準。
     */
    void refreshListViewWithDatabaseDatas(){
        ArrayList<Data> currentDatas = MainActivity.this.dataSQLite.getAllDatas();
        MainActivity.this.dataListViewAdapter.refreshDatas(currentDatas);
    }
}
