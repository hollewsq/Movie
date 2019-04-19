package movie.bw.com.movie.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

import movie.bw.com.movie.model.LoginModel;
import movie.bw.com.movie.net.PMCallback;

public interface LoginContract {
    //V
    interface IView extends IBaseView {
        void onFailLogin(String msg);
        void onSuccessLogin(Object reg);
    }
    //M
    interface IModel extends IBaseModel{
        void onModelLogin(HashMap<String, String> params, PMCallback pmCallback);
    }
    //P
    abstract class IPresenter extends BasePresenter<IModel, IView>{
        @Override
        public IModel getModule() {
            return new LoginModel();
        }

        public abstract void jumpLogin(HashMap<String, String> params);
    }
}
