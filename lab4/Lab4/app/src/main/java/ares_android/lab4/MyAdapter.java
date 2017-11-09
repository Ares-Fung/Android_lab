package ares_android.lab4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 73454 on 2017/10/30.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<Merchandise> mData;
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter(ArrayList<Merchandise> data) {
        this.mData = data;
    }
    public void updateData(ArrayList<Merchandise> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public interface OnItemClickListener {
        void onClick(int position);
        boolean onLongClick(int position);
    }
    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.Item.setText(mData.get(position).getName());
        holder.Firstletter.setText(String.valueOf(mData.get(position).getName().charAt(0)));
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mOnItemClickListener.onClick(holder.getAdapterPosition());
               }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Firstletter;
        TextView Item;
        public ViewHolder(View itemView) {
            super(itemView);
            Firstletter = (TextView) itemView.findViewById(R.id.firstletter);
            Item = (TextView) itemView.findViewById(R.id.item);
        }
    }

}

