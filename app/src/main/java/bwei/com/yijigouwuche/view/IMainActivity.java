package bwei.com.yijigouwuche.view;

import java.util.List;

import bwei.com.yijigouwuche.bean.Bean;

/**
 * Created by Yw_Ambition on 2017/11/20.
 */

public interface IMainActivity {
    String showId();
    void showData(boolean flg,List<Bean.DataBean> list);
}
