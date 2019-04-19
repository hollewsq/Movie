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
import movie.bw.com.movie.entity.LoginBean;
import movie.bw.com.movie.net.PMCallback;

public class LoginModel implements LoginContract.IModel {
    @SuppressLint("CheckResult")
    @Override
    public void onModelLogin(HashMap<String, String> params, final PMCallback pmCallback) {
        RetrofitUtils.getInstance().createService(UserService.class)
                .getLogin(Api.LOGIN_URL, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        if (loginBean.getStatus().equals("0000")){
                            pmCallback.onSuccess(loginBean);
                        }else {
                            pmCallback.onFail(loginBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("LoginModel层有异常："+throwable);
                    }
                });
    }
}
