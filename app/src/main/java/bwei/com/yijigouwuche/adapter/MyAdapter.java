package bwei.com.yijigouwuche.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import bwei.com.yijigouwuche.R;
import bwei.com.yijigouwuche.bean.Bean;

/**
 * Created by Yw_Ambition on 2017/11/20.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Bean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Bean.DataBean dataBean = list.get(position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.cb.setChecked(dataBean.isChecked());
        myViewHolder.price.setText(dataBean.getPrice()+"");
        myViewHolder.time.setText(dataBean.getCreatetime());
        myViewHolder.title.setText(dataBean.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView time;
        private final CheckBox cb;
        private final TextView title;
        private final TextView price;
        private final TextView add;
        private final TextView del;
        private final TextView sum;
        public MyViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.item_price);
            title = (TextView) itemView.findViewById(R.id.item_title);
            cb = (CheckBox) itemView.findViewById(R.id.item_cb);
            time = (TextView) itemView.findViewById(R.id.item_time);
            add = (TextView) itemView.findViewById(R.id.add);
            del = (TextView) itemView.findViewById(R.id.del);
            sum = (TextView) itemView.findViewById(R.id.sum);

        }
    }
}
