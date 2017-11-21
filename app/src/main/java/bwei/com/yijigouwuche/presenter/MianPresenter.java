package bwei.com.yijigouwuche.presenter;
import java.util.List;
import bwei.com.yijigouwuche.bean.Bean;
import bwei.com.yijigouwuche.model.IMainmodel;
import bwei.com.yijigouwuche.model.Mainmodel;
import bwei.com.yijigouwuche.net.OnNetListener;
import bwei.com.yijigouwuche.view.IMainActivity;
/**
 * Created by Yw_Ambition on 2017/11/20.
 */
public class MianPresenter {
    private IMainActivity iMainActivity;
    private final IMainmodel imainmodel;
    public MianPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        imainmodel = new Mainmodel();
    }
    public void getData(final boolean flg){
        String id = iMainActivity.showId();
        imainmodel.getData(id, new OnNetListener<Bean>() {
            @Override
            public void Onsuccess(Bean bean) {
                List<Bean.DataBean> beanData = bean.getData();
                iMainActivity.showData(flg,beanData);
            }
            @Override
            public void Onfailure(Exception e) {
            }
        });
    }
}
