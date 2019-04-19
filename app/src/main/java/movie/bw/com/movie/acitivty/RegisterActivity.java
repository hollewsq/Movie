package movie.bw.com.movie.acitivty;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.contract.RegisterContract;
import movie.bw.com.movie.entity.RegisterBean;
import movie.bw.com.movie.presenter.RegisterPresenter;
import movie.bw.com.movie.utils.EncryptUtil;

public class RegisterActivity extends BaseMvpActivity<RegisterContract.IModel, RegisterContract.IPresenter> implements RegisterContract.IView {

    @BindView(R.id.imgName)
    ImageView imgName;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.imgSex)
    ImageView imgSex;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.imgBirthday)
    ImageView imgBirthday;
    @BindView(R.id.birthday)
    EditText birthday;
    @BindView(R.id.imgPhone)
    ImageView imgPhone;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.imgPwd)
    ImageView imgPwd;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.imgMailbox)
    ImageView imgMailbox;
    @BindView(R.id.mailbox)
    EditText mailbox;
    @BindView(R.id.register)
    Button register;

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        //隐藏标题栏
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void initData() {
        super.initData();
        final HashMap<String, String> params = new HashMap<>();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("nickName", name.getText().toString());
                String gender = sex.getText().toString();
                if (gender.equals("男")){
                    params.put("sex", 1+"");
                }else {
                    params.put("sex", 2+"");
                }
                params.put("birthday", birthday.getText().toString());
                params.put("phone", phone.getText().toString());

                //密码加密
                String passWord = pwd.getText().toString();
                String encrypt = EncryptUtil.encrypt(passWord);
                params.put("pwd", encrypt);
                params.put("pwd2", encrypt);
                params.put("imei", "123456");
                params.put("ua", "小米");
                params.put("screenSize", "5.0");
                params.put("os", "android");
                params.put("email", mailbox.getText().toString());

                presenter.jumpRegister(params);
            }
        });
    }

    @Override
    public void onFailRegister(String msg) {
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRegister(Object reg) {
        RegisterBean registerBean = (RegisterBean) reg;
        Toast.makeText(RegisterActivity.this, ((RegisterBean) reg).getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void failLoding(String msg) {

    }
}
