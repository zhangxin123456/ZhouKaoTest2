package jiyun.com.zhoukaotest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mInter_Get, mDao_Get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initeViews();
    }

    private void initeViews() {
        mInter_Get = (Button) findViewById(R.id.Inter_get);
        mDao_Get = (Button) findViewById(R.id.Dao_get);
        mInter_Get.setOnClickListener(this);
        mDao_Get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Inter_get:
                Intent in = new Intent(MainActivity.this, JournalismActivity.class);
                startActivity(in);
                break;
            case R.id.Dao_get:
                Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
                startActivity(intent);
                break;
        }
    }
}
