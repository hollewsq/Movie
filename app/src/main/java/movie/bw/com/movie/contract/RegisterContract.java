package movie.bw.com.movie.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

import movie.bw.com.movie.model.RegisterModel;
import movie.bw.com.movie.net.PMCallback;

public interface RegisterContract {
    //V
    interface IView extends IBaseView {
        void onFailRegister(String msg);
        void onSuccessRegister(Object reg);
    }
    //M
    interface IModel extends IBaseModel{
        void onModelRegister(HashMap<String, String> params, PMCallback pmCallback);
    }
    //P
    abstract class IPresenter extends BasePresenter<IModel, IView>{
        @Override
        public IModel getModule() {
            return new RegisterModel();
        }

        public abstract void jumpRegister(HashMap<String, String> params);
    }
}
