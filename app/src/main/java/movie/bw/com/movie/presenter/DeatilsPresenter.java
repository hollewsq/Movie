package movie.bw.com.movie.presenter;

import java.util.HashMap;

import movie.bw.com.movie.contract.DetailsContract;
import movie.bw.com.movie.net.PMCallback;

public class DeatilsPresenter extends DetailsContract.IPresenter {
    @Override
    public void jumpDetails(HashMap<String, String> params, HashMap<String, String> head) {
        modle.onModelDetails(params, head, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailDetails(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessDetails(reg);
            }
        });
    }

    @Override
    public void jumpReview(HashMap<String, String> params, HashMap<String, String> head) {
        modle.onModelReview(params, head, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailDetails(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessReview(reg);
            }
        });
    }

    @Override
    public void jumpComment(HashMap<String, String> params, HashMap<String, String> head) {
        modle.onModelComment(params, head, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailDetails(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessComment(reg);
            }
        });
    }
}
