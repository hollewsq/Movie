package movie.bw.com.movie.presenter;

import java.util.HashMap;

import movie.bw.com.movie.contract.LoginContract;
import movie.bw.com.movie.contract.RegisterContract;
import movie.bw.com.movie.net.PMCallback;

public class RegisterPresenter extends RegisterContract.IPresenter {
    @Override
    public void jumpRegister(HashMap<String, String> params) {
        modle.onModelRegister(params, new PMCallback() {
            @Override
            public void onFail(String msg) {
                view.onFailRegister(msg);
            }

            @Override
            public void onSuccess(Object reg) {
                view.onSuccessRegister(reg);
            }
        });
    }
}
