package movie.bw.com.movie.acitivty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.adapter.PagingAdapter;
import movie.bw.com.movie.contract.FilmConteact;
import movie.bw.com.movie.entity.HeatBean;
import movie.bw.com.movie.entity.ShowHeatBean;
import movie.bw.com.movie.entity.SoonBean;
import movie.bw.com.movie.presenter.FilmPresenter;

public class PagingActivity extends BaseMvpActivity<FilmConteact.IModel, FilmConteact.IPresenter> implements FilmConteact.IView {

    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.but1)
    RadioButton but1;
    @BindView(R.id.but2)
    RadioButton but2;
    @BindView(R.id.but3)
    RadioButton but3;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.back)
    ImageView back;
    private PagingAdapter pagingAdapter;
    private HashMap<String, Integer> params1;
    private HashMap<String, String> params2;

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_paging;
    }

    @Override
    protected void initView() {
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
        ButterKnife.bind(this);
        rv.setLayoutManager(new LinearLayoutManager(PagingActivity.this, 1, false));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        params1 = new HashMap<>();
        params1.put("page", 1);
        params1.put("count", 10);

        SharedPreferences h = getSharedPreferences("H", Context.MODE_PRIVATE);
        String userId = h.getString("userId", null);
        String sessionId = h.getString("sessionId", null);
        params2 = new HashMap<>();
        params2.put("userId", userId);
        params2.put("sessionId", sessionId);
        presenter.jumpHeat(params1, params2);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if (id.equals("1")) {
            but1.setChecked(true);
            presenter.jumpHeat(params1, params2);

        } else if (id.equals("2")) {
            but2.setChecked(true);
            presenter.jumpShowHeat(params1, params2);


        } else if (id.equals("3")){
            but3.setChecked(true);
            presenter.jumpSoon(params1, params2);
        }

        /*but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpHeat(params1, params2);
                pagingAdapter.notifyDataSetChanged();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpShowHeat(params1, params2);
                pagingAdapter.notifyDataSetChanged();
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpSoon(params1, params2);
                pagingAdapter.notifyDataSetChanged();
            }
        });*/

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        presenter.jumpHeat(params1, params2);
                        break;
                    case R.id.but2:
                        presenter.jumpShowHeat(params1, params2);
                        break;
                    case R.id.but3:
                        presenter.jumpSoon(params1, params2);
                        break;
                }
                pagingAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(PagingActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessHeat(Object reg) {
        pagingAdapter = new PagingAdapter(PagingActivity.this);
        rv.setAdapter(pagingAdapter);

        HeatBean heatBean = (HeatBean) reg;
        List<HeatBean.ResultBean> result = heatBean.getResult();
        pagingAdapter.setResult1(result, 1);


    }

    @Override
    public void onSuccessShowHeat(Object reg) {
        pagingAdapter = new PagingAdapter(PagingActivity.this);
        rv.setAdapter(pagingAdapter);

        ShowHeatBean showHeatBean = (ShowHeatBean) reg;
        List<ShowHeatBean.ResultBean> result = showHeatBean.getResult();
        pagingAdapter.setResult2(result, 2);

    }

    @Override
    public void onSuccessSoon(Object reg) {
        pagingAdapter = new PagingAdapter(PagingActivity.this);
        rv.setAdapter(pagingAdapter);

        SoonBean soonBean = (SoonBean) reg;
        List<SoonBean.ResultBean> result = soonBean.getResult();
        pagingAdapter.setResult3(result, 3);

    }

    @Override
    public BasePresenter initPresenter() {
        return new FilmPresenter();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pagingAdapter != null) {
            pagingAdapter = null;
        }
    }
}
