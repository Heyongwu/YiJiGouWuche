package bwei.com.yijigouwuche.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bwei.com.yijigouwuche.Onmessage.OnMessong;
import bwei.com.yijigouwuche.Onmessage.PriceAndCount;
import bwei.com.yijigouwuche.R;
import bwei.com.yijigouwuche.bean.Bean;

/**
 * Created by Yw_Ambition on 2017/11/21.
 */

public class RvAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private List<Bean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;

    public RvAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(XRecyclerView.ViewHolder holder, int position) {
        final Bean.DataBean dataBean = list.get(position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.cb.setChecked(dataBean.isChecked());
        myViewHolder.price.setText(dataBean.getPrice()+"");
        myViewHolder.time.setText(dataBean.getCreatetime());
        myViewHolder.title.setText(dataBean.getTitle());
        myViewHolder.sum.setText(dataBean.getNum()+"");
        myViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(myViewHolder.cb.isChecked());
                PriceAndCount suanqian = suanqian();
                EventBus.getDefault().post(suanqian);
                if(myViewHolder.cb.isChecked()){
                    if(isAllchecked()){
                        //上面都选了  那么吧底下的那个全选框勾选了
                        onMessage(true);
                    }

                }else{
                    onMessage(false);
                }
                notifyDataSetChanged();
            }
        });
        myViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = dataBean.getNum();
                num++;
                dataBean.setNum(num);
                if(myViewHolder.cb.isChecked()){
                    EventBus.getDefault().post(suanqian());
                }
                notifyDataSetChanged();
            }
        });
        myViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = dataBean.getNum();
                if(num == 1 ){
                    return;
                }
                num--;
                dataBean.setNum(num);
                if(myViewHolder.cb.isChecked()){
                    EventBus.getDefault().post(suanqian());
                }
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public boolean isAllchecked() {
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBean = list.get(i);
            if(!dataBean.isChecked()){
                return false;
            }
        }
        return true;
    }

    class MyViewHolder extends XRecyclerView.ViewHolder {

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
    public void onMessage(boolean isChecked){
        OnMessong onMessong = new OnMessong();
        onMessong.setChecked(isChecked);
        EventBus.getDefault().post(onMessong);
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBean = list.get(i);
            dataBean.setChecked(isChecked);
        }
        EventBus.getDefault().post(suanqian());
        notifyDataSetChanged();
    }
    private PriceAndCount suanqian(){
        int price = 0 ;
        int count = 0 ;
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBean = list.get(i);
            if(dataBean.isChecked()){
                price += dataBean.getPrice() * dataBean.getNum();
                count += dataBean.getNum();
            }
        }
        PriceAndCount priceAndCount = new PriceAndCount();
        priceAndCount.setPrice(price);
        priceAndCount.setSum(count);
        notifyDataSetChanged();
        return priceAndCount;
    }

}
