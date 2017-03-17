package y3k.sqlitelistviewsample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by y3k on 2017/3/17.
 */

public class DataListViewAdapter extends BaseAdapter {
    final ArrayList<Data> currentDatas = new ArrayList<>();

    @Override
    public int getCount() {
        return DataListViewAdapter.this.currentDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return DataListViewAdapter.this.currentDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        Data dataToDisplay = DataListViewAdapter.this.currentDatas.get(position);
        textView.setText(dataToDisplay.toDisplayString());
        return textView;
    }

    /**
     * 實作這個function，直接把塞進新資料、更新綁定的ListView的動作一氣呵成。
     * @param newDatas 新的資料，設計成直接取代舊的那筆。
     */
    public void refreshDatas(ArrayList<Data> newDatas){
        DataListViewAdapter.this.currentDatas.clear();
        DataListViewAdapter.this.currentDatas.addAll(newDatas);
        DataListViewAdapter.this.notifyDataSetChanged();
    }
}
