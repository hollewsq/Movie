package movie.bw.com.movie.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

import movie.bw.com.movie.model.DetailsModel;
import movie.bw.com.movie.net.PMCallback;

public interface DetailsContract {
    //V
    interface IView extends IBaseView{
        void onFailDetails(String msg);
        void onSuccessDetails(Object reg);
        void onSuccessReview(Object reg);
        void onSuccessComment(Object reg);
    }
    //M
    interface IModel extends IBaseModel{
        void onModelDetails(HashMap<String, String> params, HashMap<String, String> head, PMCallback pmCallback);
        void onModelReview(HashMap<String, String> params, HashMap<String, String> head, PMCallback pmCallback);
        void onModelComment(HashMap<String, String> params, HashMap<String, String> head, PMCallback pmCallback);
    }
    //P
    abstract class IPresenter extends BasePresenter<IModel, IView> {
        @Override
        public IModel getModule() {
            return new DetailsModel();
        }

        public abstract void jumpDetails(HashMap<String, String> params, HashMap<String, String> head);
        public abstract void jumpReview(HashMap<String, String> params, HashMap<String, String> head);
        public abstract void jumpComment(HashMap<String, String> params, HashMap<String, String> head);
    }
}
