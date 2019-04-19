package movie.bw.com.movie.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

import movie.bw.com.movie.model.RecommendModel;
import movie.bw.com.movie.net.PMCallback;

public interface RecommendContract {
    //V
    interface IView extends IBaseView{
        void onFailRecommend(String msg);
        void onSuccessRecommend(Object reg);
    }
    //M
    interface IModel extends IBaseModel{
        void onModelRecommend(HashMap<String, Integer> params, HashMap<String, String> head, PMCallback pmCallback);
    }
    //P
    abstract class IPresenter extends BasePresenter<IModel, IView>{
        @Override
        public IModel getModule() {
            return new RecommendModel();
        }

        public abstract void jumpRecommend(HashMap<String, Integer> params, HashMap<String, String> head);
    }
}
