package movie.bw.com.movie.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

import movie.bw.com.movie.model.DetailsModel;
import movie.bw.com.movie.model.IdDetailsModel;
import movie.bw.com.movie.net.PMCallback;

public interface IdDetailsContract {
    //V
    interface IView extends IBaseView{
        void onFailId(String msg);
        void onSuccessDetails(Object reg);
    }
    //M
    interface IModel extends IBaseModel{
        void onModelId(HashMap<String, String> params, HashMap<String, String> head, PMCallback pmCallback);
    }
    //P
    abstract class IPresenter extends BasePresenter<IModel, IView> {
        @Override
        public IModel getModule() {
            return new IdDetailsModel();
        }

        public abstract void jumpId(HashMap<String, String> params, HashMap<String, String> head);
    }
}
