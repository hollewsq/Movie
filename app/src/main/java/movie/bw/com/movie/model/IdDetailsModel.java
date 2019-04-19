package movie.bw.com.movie.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import movie.bw.com.movie.api.Api;
import movie.bw.com.movie.api.service.FilmService;
import movie.bw.com.movie.contract.IdDetailsContract;
import movie.bw.com.movie.entity.IdDetailsBean;
import movie.bw.com.movie.net.PMCallback;

public class IdDetailsModel implements IdDetailsContract.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void onModelId(HashMap<String, String> params, HashMap<String, String> head, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getDetailsId(Api.IDDETAILS_URL, params, head)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<IdDetailsBean>() {
                    @Override
                    public void accept(IdDetailsBean idDetailsBean) throws Exception {
                        if (idDetailsBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(idDetailsBean);
                        }else {
                            pmCallback.onFail(idDetailsBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("IdDetailsModel层有异常："+throwable);
                    }
                });
    }
}
