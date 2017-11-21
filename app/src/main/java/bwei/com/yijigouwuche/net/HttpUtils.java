package bwei.com.yijigouwuche.net;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Yw_Ambition on 2017/11/20.
 */
public class HttpUtils {
    private static HttpUtils httpUtils;
    private final OkHttpClient client;

    private HttpUtils() {
        client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
    }
    public static HttpUtils getHttpUtils(){
        if(httpUtils == null){
            synchronized (HttpUtils.class){
                if(httpUtils == null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
    public void doGet(String url, Callback callback){
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        client.newCall(request).enqueue(callback);
    }
    public void doPost(String url, Map<String,String> params,Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue()).build();
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(callback);
    }
}
