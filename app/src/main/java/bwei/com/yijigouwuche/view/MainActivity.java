package bwei.com.yijigouwuche.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import bwei.com.yijigouwuche.Onmessage.OnMessong;
import bwei.com.yijigouwuche.Onmessage.PriceAndCount;
import bwei.com.yijigouwuche.R;
import bwei.com.yijigouwuche.adapter.RvAdapter;
import bwei.com.yijigouwuche.bean.Bean;
import bwei.com.yijigouwuche.presenter.MianPresenter;


public class MainActivity extends AppCompatActivity implements IMainActivity {

    private XRecyclerView mRcv;
    private List<Bean.DataBean> list = new ArrayList<>();
    private RvAdapter rvAdapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    private CheckBox mCb;
    /**
     * 结算(0)
     */
    private TextView mSum;
    /**
     * 0
     */
    private TextView mMainPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        final MianPresenter mianPresenter = new MianPresenter(this);
        mianPresenter.getData(true);
        mCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvAdapter.onMessage(mCb.isChecked());
            }
        });

        rvAdapter = new RvAdapter(list, this);
        mRcv.setAdapter(rvAdapter);

        mRcv.setLayoutManager(new LinearLayoutManager(this));

        mRcv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mianPresenter.getData(true);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                        mianPresenter.getData(false);
                        rvAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public String showId() {
        return "71";
    }
    @Override
    public void showData(boolean flg, List<Bean.DataBean> lists) {
//        MyAdapter adapter = new MyAdapter(list,this);
//        mRcv.setAdapter(adapter);
        if (flg) {
            list.clear();
            list.addAll(lists);

            mRcv.refreshComplete();
        } else {
            list.addAll(lists);
        }
        rvAdapter.notifyDataSetChanged();
    }
    private void initView() {
        mRcv = (XRecyclerView) findViewById(R.id.rcv);
        mCb = (CheckBox) findViewById(R.id.cb);
        mSum = (TextView) findViewById(R.id.sum);
        mMainPrice = (TextView) findViewById(R.id.main_price);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onmessage(OnMessong onMessong){
        mCb.setChecked(onMessong.isChecked());
    }
    @Subscribe
    public void onmessage(PriceAndCount event){
        mMainPrice.setText(event.getSum()+"");
        mSum.setText("结算（"+event.getPrice()+")");
    }

}
