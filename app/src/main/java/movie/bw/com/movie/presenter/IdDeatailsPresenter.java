package movie.bw.com.movie.presenter;

import java.util.HashMap;

import movie.bw.com.movie.contract.IdDetailsContract;
import movie.bw.com.movie.net.PMCallback;

public class IdDeatailsPresenter extends IdDetailsContract.IPresenter {
    @Override
    public void jumpId(HashMap<String, String> params, HashMap<String, String> head) {
        modle.onModelId(params, head, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailId(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessDetails(reg);
            }
        });
    }
}
