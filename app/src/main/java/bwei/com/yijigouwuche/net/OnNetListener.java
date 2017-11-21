package bwei.com.yijigouwuche.net;

/**
 * Created by Yw_Ambition on 2017/11/20.
 */

public interface OnNetListener<T> {
    void Onsuccess(T t);
    void Onfailure(Exception e);
}
