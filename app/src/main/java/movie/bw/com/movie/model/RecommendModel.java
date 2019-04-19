package movie.bw.com.movie.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import movie.bw.com.movie.api.Api;
import movie.bw.com.movie.api.service.CinemaService;
import movie.bw.com.movie.contract.RecommendContract;
import movie.bw.com.movie.entity.RecommendBean;
import movie.bw.com.movie.net.PMCallback;

public class RecommendModel implements RecommendContract.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void onModelRecommend(HashMap<String, Integer> params, HashMap<String, String> head, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(CinemaService.class)
                .getRecommend(Api.RECOMMEND_URL, params, head)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RecommendBean>() {
                    @Override
                    public void accept(RecommendBean recommendBean) throws Exception {
                        if (recommendBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(recommendBean);
                        }else {
                            pmCallback.onFail(recommendBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("RecommendModel层有异常："+throwable);
                    }
                });
    }
}
