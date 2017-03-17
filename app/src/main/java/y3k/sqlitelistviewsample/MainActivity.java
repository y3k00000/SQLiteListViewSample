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
        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.dataSQLite.clearDatas();
                MainActivity.this.refreshListViewWithDatabaseDatas();
            }
        });
        ((ListView)findViewById(R.id.list_view)).setAdapter(MainActivity.this.dataListViewAdapter);
    }

    void refreshListViewWithDatabaseDatas(){
        ArrayList<Data> currentDatas = MainActivity.this.dataSQLite.getAllDatas();
        MainActivity.this.dataListViewAdapter.refreshDatas(currentDatas);
    }
}
