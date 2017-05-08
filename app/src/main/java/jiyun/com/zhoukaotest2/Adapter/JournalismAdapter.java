package jiyun.com.zhoukaotest2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import News.db.Journalism;
import jiyun.com.zhoukaotest2.Bean.QueryWeixin;
import jiyun.com.zhoukaotest2.DBManager;
import jiyun.com.zhoukaotest2.R;

/**
 * Created by lx on 2017/5/3.
 */

public class JournalismAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<QueryWeixin.ResultBean.ListBean> mList;

    public JournalismAdapter(Context mContext, ArrayList<QueryWeixin.ResultBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listviewmoban, null);
            holder = new Holder();
            holder.mImage = (ImageView) convertView.findViewById(R.id.imageView);
            holder.mTitle = (TextView) convertView.findViewById(R.id.Title);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        QueryWeixin.ResultBean.ListBean bean = mList.get(position);
        String title = bean.getTitle();
        Journalism jour = new Journalism();
        jour.setTitle(title);
        DBManager manager = new DBManager(mContext);
        manager.insertUser(jour);
        Log.i("=================数据", jour.getTitle());
        holder.mTitle.setText(bean.getTitle() + "");
        Glide.with(mContext).load(bean.getFirstImg()).into(holder.mImage);
        return convertView;
    }

    class Holder {
        private ImageView mImage;
        private TextView mTitle;
    }
}
