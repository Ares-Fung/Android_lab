package ares_android.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 73454 on 2017/10/30.
 */

public class MyAdapter2 extends BaseAdapter{

    private Context context;
    private ArrayList<Merchandise> mData;

    public MyAdapter2(Context context, ArrayList<Merchandise> data) {
        this.context = context;
        this.mData = data;
    }

    public void updateData(ArrayList<Merchandise> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return  i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder viewHolder;
        convertView = LayoutInflater.from(context).inflate(R.layout.view_lv_item, null);
        viewHolder = new ViewHolder();
        viewHolder.firstletter = (TextView) convertView.findViewById(R.id.lv_firstletter);
        viewHolder.item = (TextView) convertView.findViewById(R.id.lv_item);
        viewHolder.price = (TextView) convertView.findViewById(R.id.price);

        if(i>0)
            viewHolder.firstletter.setText(String.valueOf(mData.get(i).getName().charAt(0)));
        else
            viewHolder.firstletter.setText("*");
        viewHolder.item.setText(mData.get(i).getName());
        if(i>0)
            viewHolder.price.setText("¥"+Double.toString(mData.get(i).getPrice()));
        else
            viewHolder.price.setText("价格");
        return convertView;
    }

    public class ViewHolder {
        public TextView firstletter;
        public TextView item;
        public TextView price;
    }
}

