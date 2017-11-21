package bwei.com.yijigouwuche.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwei.com.yijigouwuche.bean.Bean;
import bwei.com.yijigouwuche.net.Api;
import bwei.com.yijigouwuche.net.HttpUtils;
import bwei.com.yijigouwuche.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by Yw_Ambition on 2017/11/20.
 */

public class Mainmodel extends BeanModel implements IMainmodel{
    @Override
    public void getData(String id, final OnNetListener<Bean> onNetListener) {
        Map<String,String> params = new HashMap<>();
        params.put("uid",id);
        HttpUtils.getHttpUtils().doPost(Api.URL, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.Onfailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final Bean fromJson = new Gson().fromJson(string, Bean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.Onsuccess(fromJson);
                    }
                });
            }
        });
    }
}
