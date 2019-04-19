package movie.bw.com.movie.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;

import java.util.HashMap;

import movie.bw.com.movie.model.FilmModel;
import movie.bw.com.movie.net.PMCallback;

public interface FilmConteact {
    //V
    interface IView extends IBaseView {
        void onFail(String msg);
        void onSuccessHeat(Object reg);
        void onSuccessShowHeat(Object reg);
        void onSuccessSoon(Object reg);
    }
    //M
    interface IModel extends IBaseModel{
        void onModelHeat(HashMap<String, Integer> params, HashMap<String, String> head, PMCallback pmCallback);
        void onModelShowHeat(HashMap<String, Integer> params, HashMap<String, String> head, PMCallback pmCallback);
        void onModelSoon(HashMap<String, Integer> params, HashMap<String, String> head, PMCallback pmCallback);
    }
    //P
    abstract class IPresenter extends BasePresenter<IModel, IView>{
        @Override
        public IModel getModule() {
            return new FilmModel();
        }

        public abstract void jumpHeat(HashMap<String, Integer> params, HashMap<String, String> head);
        public abstract void jumpShowHeat(HashMap<String, Integer> params, HashMap<String, String> head);
        public abstract void jumpSoon(HashMap<String, Integer> params, HashMap<String, String> head);
    }
}
