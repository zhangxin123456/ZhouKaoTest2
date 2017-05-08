package jiyun.com.zhoukaotest2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import News.db.Journalism;
import jiyun.com.zhoukaotest2.DBManager;
import jiyun.com.zhoukaotest2.R;

/**
 * Created by lx on 2017/5/3.
 */

public class OfflineAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Journalism> mList;

    public OfflineAdapter(Context mContext, ArrayList<Journalism> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return 10;
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
            holder.mText = (TextView) convertView.findViewById(R.id.Title);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        DBManager dbmanager = new DBManager(mContext);
        mList = (ArrayList<Journalism>) dbmanager.queryUserList();
        Journalism journalism = dbmanager.queryUserList().get(position);
        holder.mText.setText(journalism.getTitle() + "");
        holder.mImage.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }

    class Holder {
        private ImageView mImage;
        private TextView mText;
    }
}
