package movie.bw.com.movie.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import movie.bw.com.movie.api.Api;
import movie.bw.com.movie.api.service.UserService;
import movie.bw.com.movie.contract.LoginContract;
import movie.bw.com.movie.contract.RegisterContract;
import movie.bw.com.movie.entity.LoginBean;
import movie.bw.com.movie.entity.RegisterBean;
import movie.bw.com.movie.net.PMCallback;

public class RegisterModel implements RegisterContract.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void onModelRegister(HashMap<String, String> params, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(UserService.class)
                .getRegister(Api.REG_URL, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(registerBean);
                        }else {
                            pmCallback.onFail(registerBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("RegisterModel层有异常："+throwable);
                    }
                });
    }
}
