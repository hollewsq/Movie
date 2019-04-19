package movie.bw.com.movie.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import movie.bw.com.movie.api.Api;
import movie.bw.com.movie.api.service.FilmService;
import movie.bw.com.movie.contract.DetailsContract;
import movie.bw.com.movie.entity.CommentBean;
import movie.bw.com.movie.entity.FilmDetailsBean;
import movie.bw.com.movie.entity.ReviewBean;
import movie.bw.com.movie.net.PMCallback;

public class DetailsModel implements DetailsContract.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void onModelDetails(HashMap<String, String> params, HashMap<String, String> heat, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getDetails(Api.DETAILS_URL, params, heat)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<FilmDetailsBean>() {
                    @Override
                    public void accept(FilmDetailsBean detailsBean) throws Exception {
                        if (detailsBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(detailsBean);
                        }else {
                            pmCallback.onFail(detailsBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("DetailsModel层有异常："+throwable);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onModelReview(HashMap<String, String> params, HashMap<String, String> head, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getReview(Api.REVIEW_URL, params, head)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ReviewBean>() {
                    @Override
                    public void accept(ReviewBean reviewBean) throws Exception {
                        if (reviewBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(reviewBean);
                        }else {
                            pmCallback.onFail(reviewBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("DetailsModel层有异常："+throwable);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onModelComment(HashMap<String, String> params, HashMap<String, String> head, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getComment(Api.WRITE_URL, params, head)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CommentBean>() {
                    @Override
                    public void accept(CommentBean commentBean) throws Exception {
                        if (commentBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(commentBean);
                        }else {
                            pmCallback.onFail(commentBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("DetailsModel层有异常:"+throwable);
                    }
                });
    }
}
