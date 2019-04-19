package movie.bw.com.movie.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import movie.bw.com.movie.api.Api;
import movie.bw.com.movie.api.service.FilmService;
import movie.bw.com.movie.contract.FilmConteact;
import movie.bw.com.movie.entity.HeatBean;
import movie.bw.com.movie.entity.ShowHeatBean;
import movie.bw.com.movie.entity.SoonBean;
import movie.bw.com.movie.net.PMCallback;

public class FilmModel implements FilmConteact.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void onModelHeat(HashMap<String, Integer> params, HashMap<String, String> head, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getHeat(Api.HEAT_URL, params, head)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<HeatBean>() {
                    @Override
                    public void accept(HeatBean heatBean) throws Exception {
                        if (heatBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(heatBean);
                        }else {
                            pmCallback.onFail(heatBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("FilmModel层有异常："+throwable);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onModelShowHeat(HashMap<String, Integer> params1, HashMap<String, String> params2, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getShowHeat(Api.SHOWHOAT_URL, params1, params2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ShowHeatBean>() {
                    @Override
                    public void accept(ShowHeatBean showHeat) throws Exception {
                        if (showHeat.getStatus().equals("0000")){
                            pmCallback.onSuccess(showHeat);
                        }else {
                            pmCallback.onFail(showHeat.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("FilmModel层有异常："+throwable);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onModelSoon(HashMap<String, Integer> params1, HashMap<String, String> params2, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(FilmService.class)
                .getSoon(Api.SOON_URL, params1, params2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<SoonBean>() {
                    @Override
                    public void accept(SoonBean soonBean) throws Exception {
                        if (soonBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(soonBean);
                        }else {
                            pmCallback.onFail(soonBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("FilmModel层有异常："+throwable);
                    }
                });
    }
}
