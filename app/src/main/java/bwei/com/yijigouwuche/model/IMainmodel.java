package bwei.com.yijigouwuche.model;

import bwei.com.yijigouwuche.bean.Bean;
import bwei.com.yijigouwuche.net.OnNetListener;

/**
 * Created by Yw_Ambition on 2017/11/20.
 */

public interface IMainmodel {
    void getData(String id,OnNetListener<Bean> onNetListener);
}
