package movie.bw.com.movie.acitivty;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.RowId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayer;
import movie.bw.com.movie.R;
import movie.bw.com.movie.adapter.LineupAdapter;
import movie.bw.com.movie.adapter.NoticeAdapter;
import movie.bw.com.movie.adapter.ReviewAdapter;
import movie.bw.com.movie.adapter.StillsAdapter;
import movie.bw.com.movie.contract.DetailsContract;
import movie.bw.com.movie.entity.CommentBean;
import movie.bw.com.movie.entity.FilmDetailsBean;
import movie.bw.com.movie.entity.ReviewBean;
import movie.bw.com.movie.entity.UserBean;
import movie.bw.com.movie.presenter.DeatilsPresenter;

public class DetailsActivity extends BaseMvpActivity<DetailsContract.IModel, DetailsContract.IPresenter> implements DetailsContract.IView {

    @BindView(R.id.dea)
    ImageView dea;
    @BindView(R.id.dy)
    TextView dy;
    @BindView(R.id.collection)
    CheckBox collection;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.picture)
    SimpleDraweeView picture;
    @BindView(R.id.details)
    TextView details;
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.stills)
    TextView stills;
    @BindView(R.id.review)
    TextView review;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.purchase)
    Button purchase;
    @BindView(R.id.img)
    ImageView img;
    private String movieId;
    private RecyclerView display;
    private HashMap<String, String> heat;
    private HashMap<String, String> params;
    private ReviewAdapter reviewAdapter;
    private FilmDetailsBean.ResultBean result = new FilmDetailsBean.ResultBean();

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_details;
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

        //影片ID
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId");

        //返回 FilmActivity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailsActivity.this, PurchaseActivity.class);
                intent1.putExtra("cinemaId", result.getId()+"");
                intent1.putExtra("name", result.getName()+"");
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void initData() {
        super.initData();
        HashMap<String, String> params1 = new HashMap<>();
        params1.put("movieId", movieId);

        SharedPreferences h = getSharedPreferences("H", Context.MODE_PRIVATE);
        String userId = h.getString("userId", null);
        String sessionId = h.getString("sessionId", null);
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("userId", userId);
        params2.put("sessionId", sessionId);

        presenter.jumpDetails(params1, params2);
    }

    @Override
    public BasePresenter initPresenter() {
        return new DeatilsPresenter();
    }

    @Override
    public void onFailDetails(String msg) {
        Toast.makeText(DetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessDetails(final Object reg) {
        FilmDetailsBean detailsBean = (FilmDetailsBean) reg;
        result = detailsBean.getResult();
        name.setText(result.getName());
        picture.setImageURI(Uri.parse(result.getImageUrl()));
        dy.setText(result.getName());
        Glide.with(DetailsActivity.this).load(result.getImageUrl()).into(img);
        //详情
        details.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                //一个自定义的布局，作为显示的内容
                View view = ConstraintLayout.inflate(DetailsActivity.this, R.layout.pop_iddetails, null);

                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(DetailsActivity.this.getResources(), (Bitmap)null));
                popupWindow.setAnimationStyle(R.style.pop);
                popupWindow.getContentView().setFocusableInTouchMode(true);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

                ImageView down = view.findViewById(R.id.down);
                SimpleDraweeView dy = view.findViewById(R.id.dy);
                TextView type = view.findViewById(R.id.type);
                TextView director = view.findViewById(R.id.director);
                TextView duration = view.findViewById(R.id.duration);
                TextView land = view.findViewById(R.id.land);
                TextView plot = view.findViewById(R.id.plot);
                RecyclerView rv = view.findViewById(R.id.rv);
                TextView refresh = view.findViewById(R.id.refresh);

                //隐藏PopupWindow
                down.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                dy.setImageURI(Uri.parse(result.getImageUrl()));
                type.setText("类型:"+ result.getMovieTypes());
                director.setText("导演:"+ result.getDirector());
                duration.setText("时长:"+ result.getDuration());
                land.setText("产地:"+ result.getPlaceOrigin());
                plot.setText(result.getSummary());

                String starring = result.getStarring();
                String[] split = starring.split("\\,");
                List<UserBean> list = new ArrayList<>();
                for (String s : split) {
                    list.add(new UserBean(s));
                }
                rv.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, 1, false));
                LineupAdapter lineupAdapter = new LineupAdapter(DetailsActivity.this, list);
                rv.setAdapter(lineupAdapter);
            }
        });

        //预告片
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.pop_notice, null);
                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(DetailsActivity.this.getResources(), (Bitmap)null));
                popupWindow.setAnimationStyle(R.style.pop);
                popupWindow.getContentView().setFocusableInTouchMode(true);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

                ImageView down = view.findViewById(R.id.down);
                RecyclerView rv = view.findViewById(R.id.rv);
                down.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                rv.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, 1, false));
                List<FilmDetailsBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
                NoticeAdapter noticeAdapter = new NoticeAdapter(DetailsActivity.this, shortFilmList);
                rv.setAdapter(noticeAdapter);
            }
        });

        //剧照
        stills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ConstraintLayout.inflate(DetailsActivity.this, R.layout.pop_stills, null);
                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(DetailsActivity.this.getResources(), (Bitmap)null));
                popupWindow.setAnimationStyle(R.style.pop);
                popupWindow.getContentView().setFocusableInTouchMode(true);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

                ImageView down = view.findViewById(R.id.down);
                RecyclerView xv = view.findViewById(R.id.xv);
                xv.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
                down.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                List<String> posterList = result.getPosterList();
                //创建适配器 剧照
                StillsAdapter stillsAdapter = new StillsAdapter(DetailsActivity.this, posterList);
                xv.setAdapter(stillsAdapter);
            }
        });

        //影评
        review.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                params = new HashMap<>();
                params.put("movieId", result.getId()+"");
                params.put("page", 1+"");
                params.put("count", 10+"");


                SharedPreferences h = getSharedPreferences("H", Context.MODE_PRIVATE);
                String userId = h.getString("userId", null);
                String sessionId = h.getString("sessionId", null);
                heat = new HashMap<>();
                heat.put("userId", userId);
                heat.put("sessionId", sessionId);

                final View view = ConstraintLayout.inflate(DetailsActivity.this, R.layout.pop_review, null);
                final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(DetailsActivity.this.getResources(), (Bitmap)null));
                popupWindow.setAnimationStyle(R.style.pop);
                popupWindow.getContentView().setFocusableInTouchMode(true);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

                ImageView filmreview = view.findViewById(R.id.filmreview_sdv);
                display = view.findViewById(R.id.filmreview_recycleview);
                Button backImg = view.findViewById(R.id.back_img);
                Button commentadd = view.findViewById(R.id.commentaddbutton);

                display.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, 1, false));
                //关闭popupWindow
                filmreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                presenter.jumpReview(params, heat);

                //关闭popupWindow
                backImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                //写评论
                commentadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final View ping = ConstraintLayout.inflate(DetailsActivity.this, R.layout.commend, null);
                        final PopupWindow popupWindow = new PopupWindow(ping, WindowManager.LayoutParams.MATCH_PARENT, 430, true);
                        popupWindow.setTouchable(true);
                        popupWindow.setOutsideTouchable(true);
                        popupWindow.setBackgroundDrawable(new BitmapDrawable(DetailsActivity.this.getResources(), (Bitmap)null));
                        popupWindow.setAnimationStyle(R.style.pop);
                        popupWindow.getContentView().setFocusableInTouchMode(true);
                        popupWindow.setFocusable(true);
                        popupWindow.showAtLocation(ping, Gravity.BOTTOM, 0, 0);
                        final EditText input = ping.findViewById(R.id.et_input_message);
                        TextView confrim = ping.findViewById(R.id.confrim_btn);

                        confrim.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(ping.getWindowToken(), 0);
                                popupWindow.dismiss();

                                String in = input.getText().toString();
                                HashMap<String, String> params = new HashMap<>();
                                params.put("movieId", result.getId()+"");
                                params.put("commentContent", in);

                                if (in != null){
                                    presenter.jumpComment(params, heat);
                                }
                            }
                        });

                    }
                });
            }
        });
    }

    @Override
    public void onSuccessReview(Object reg) {
        ReviewBean reviewBean = (ReviewBean) reg;
        List<ReviewBean.ResultBean> result = reviewBean.getResult();
        reviewAdapter = new ReviewAdapter(DetailsActivity.this, result);
        display.setAdapter(reviewAdapter);
    }

    @Override
    public void onSuccessComment(Object reg) {
        CommentBean commentBean = (CommentBean) reg;
        Toast.makeText(DetailsActivity.this, commentBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (commentBean.getStatus().equals("0000")){
            presenter.jumpReview(params, heat);
        }
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
        if (presenter != null){
            presenter = null;
        }else if (reviewAdapter != null){
            reviewAdapter = null;
        }else if (params != null){
            params = null;
            heat = null;
        }else if (result != null){
            result = null;
        }
    }
}
