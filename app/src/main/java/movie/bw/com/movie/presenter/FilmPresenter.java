package movie.bw.com.movie.presenter;

import java.util.HashMap;

import movie.bw.com.movie.contract.FilmConteact;
import movie.bw.com.movie.net.PMCallback;

public class FilmPresenter extends FilmConteact.IPresenter {
    @Override
    public void jumpHeat(HashMap<String, Integer> params, HashMap<String, String> head) {
        modle.onModelHeat(params, head, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessHeat(reg);
            }
        });
    }

    @Override
    public void jumpShowHeat(HashMap<String, Integer> params1, HashMap<String, String> params2) {
        modle.onModelShowHeat(params1, params2,new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessShowHeat(reg);
            }
        });
    }

    @Override
    public void jumpSoon(HashMap<String, Integer> params1, HashMap<String, String> params2) {
        modle.onModelSoon(params1, params2,new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessSoon(reg);
            }
        });
    }
}
