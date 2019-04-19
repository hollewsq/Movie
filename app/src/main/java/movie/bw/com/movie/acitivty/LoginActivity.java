package movie.bw.com.movie.acitivty;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.app.MyApp;
import movie.bw.com.movie.contract.LoginContract;
import movie.bw.com.movie.entity.LoginBean;
import movie.bw.com.movie.presenter.LoginPresenter;
import movie.bw.com.movie.utils.EncryptUtil;

public class LoginActivity extends BaseMvpActivity<LoginContract.IModel, LoginContract.IPresenter> implements LoginContract.IView {
    @BindView(R.id.imgphone)
    ImageView imgphone;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.imglock)
    ImageView imglock;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.remember)
    CheckBox remember;
    @BindView(R.id.automatic)
    CheckBox automatic;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.weixinLogin)
    TextView weixinLogin;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.weixin)
    ImageView weixin;

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
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
        //快速注册
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences ID = getSharedPreferences("ID", MODE_PRIVATE);
        boolean rem = ID.getBoolean("rem", false);
        if (rem){
            phone.setText(ID.getString("phone", null));
            pwd.setText(ID.getString("pwd", null));
        }
        //记住密码
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences ID = getSharedPreferences("ID", MODE_PRIVATE);
                SharedPreferences.Editor edit = ID.edit();
                edit.putString("phone", phone.getText().toString());
                edit.putString("pwd", pwd.getText().toString());
                edit.putBoolean("rem", true);
                edit.commit();
            }
        });

        //微信登录
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "还没集成呢", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        final HashMap<String, String> params = new HashMap<>();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("phone", phone.getText().toString());
                String pwds = pwd.getText().toString();
                String pwdEncrypt = EncryptUtil.encrypt(pwds);
                params.put("pwd", pwdEncrypt);
                presenter.jumpLogin(params);
            }
        });
    }

    @Override
    public void onFailLogin(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSuccessLogin(Object reg) {
        LoginBean loginBean = (LoginBean) reg;
        LoginBean.ResultBean result = loginBean.getResult();

        SharedPreferences h = getSharedPreferences("H", MODE_PRIVATE);
        SharedPreferences.Editor edit = h.edit();
        edit.putString("userId", result.getUserId());
        edit.putString("sessionId", result.getSessionId());
        edit.commit();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        finish();
    }

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter();
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
