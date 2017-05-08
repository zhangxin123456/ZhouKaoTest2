package jiyun.com.zhoukaotest2;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import News.db.DaoMaster;
import News.db.DaoSession;
import News.db.Journalism;
import News.db.JournalismDao;
import jiyun.com.zhoukaotest2.Adapter.OfflineAdapter;

/**
 * Created by lx on 2017/5/3.
 */

public class OfflineActivity extends Activity {
    private ListView mListView;
    private ArrayList<Journalism> mList = new ArrayList<>();
    private OfflineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        mListView = (ListView) findViewById(R.id.Offline_ListView);
        mAdapter = new OfflineAdapter(OfflineActivity.this, mList);
        mListView.setAdapter(mAdapter);
    }
}
