package movie.bw.com.movie.presenter;

import java.util.HashMap;

import movie.bw.com.movie.contract.RecommendContract;
import movie.bw.com.movie.net.PMCallback;

public class RecommendPresenter extends RecommendContract.IPresenter {
    @Override
    public void jumpRecommend(HashMap<String, Integer> params, HashMap<String, String> head) {
        modle.onModelRecommend(params, head, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailRecommend(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessRecommend(reg);
            }
        });
    }
}
