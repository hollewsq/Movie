package movie.bw.com.movie.presenter;

import java.util.HashMap;

import movie.bw.com.movie.contract.LoginContract;
import movie.bw.com.movie.net.PMCallback;

public class LoginPresenter extends LoginContract.IPresenter {
    @Override
    public void jumpLogin(HashMap<String, String> params) {
        modle.onModelLogin(params, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailLogin(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessLogin(reg);
            }
        });
    }
}
