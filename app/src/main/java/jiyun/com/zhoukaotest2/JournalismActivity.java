package jiyun.com.zhoukaotest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import jiyun.com.zhoukaotest2.Adapter.JournalismAdapter;
import jiyun.com.zhoukaotest2.Bean.QueryWeixin;

public class JournalismActivity extends Activity {
    private ListView mListView;
    private ArrayList<QueryWeixin.ResultBean.ListBean> mList = new ArrayList<>();
    private JournalismAdapter mAdapter;
    private PtrFrameLayout ptrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        mListView = (ListView) findViewById(R.id.ListView);
        volleyRequest();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = mList.get(position).getUrl().toString();
                Intent in = new Intent(JournalismActivity.this, WebViewActivity.class);
                in.putExtra("url", url);
                startActivity(in);
            }
        });
        ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.PtrFrameLayout_);
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(this);
        PtrClassicDefaultFooter defaultFooter = new PtrClassicDefaultFooter(this);
        ptrFrameLayout.setHeaderView(defaultHeader);
        ptrFrameLayout.setFooterView(defaultFooter);
        ptrFrameLayout.addPtrUIHandler(defaultHeader);
        ptrFrameLayout.addPtrUIHandler(defaultFooter);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ptrFrameLayout.refreshComplete();
                            }
                        });
                    }
                });
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    public void volleyRequest() {
        //1. 创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //2. 创建Request对象
        String url = "http://v.juhe.cn/weixin/query?key=25ed29d133cfc9f40e0412051a944168&pno=1";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //请求成功后，走到这个方法中
                Log.e("AAA", "请求结果：" + response.toString());
                QueryWeixin stu = new Gson().fromJson(response, QueryWeixin.class);
                mList.addAll(stu.getResult().getList());
                mAdapter = new JournalismAdapter(JournalismActivity.this, mList);
                mListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                Log.e("AAA", "解析数据" + stu.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //请求失败后，走到这个方法中
                Log.e("AAA", "请求失败：" + error.getMessage().toString());
            }
        });
        //3. 把请求对象放到请求队列中
        requestQueue.add(request);
    }

}
