package movie.bw.com.movie.acitivty;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.fragment.CinemaFragment;
import movie.bw.com.movie.fragment.FilmFragment;
import movie.bw.com.movie.fragment.MyFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.but1)
    RadioButton but1;
    @BindView(R.id.but2)
    RadioButton but2;
    @BindView(R.id.but3)
    RadioButton but3;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.container)
    ConstraintLayout container;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode().setDuration(2000));
        getWindow().setExitTransition(new Explode().setDuration(2000));
        setContentView(R.layout.activity_main);
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

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new FilmFragment();
                    case 1:
                        return new CinemaFragment();
                    case 2:
                        return new MyFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        radiogroup.check(R.id.but1);
                        break;
                    case 1:
                        radiogroup.check(R.id.but2);
                        break;
                    case 2:
                        radiogroup.check(R.id.but3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setMagnify(but1);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        pager.setCurrentItem(0);
                        setMagnify(but1);
                        setShrink(but2);
                        setShrink(but3);
                        break;
                    case R.id.but2:
                        pager.setCurrentItem(1);
                        setMagnify(but2);
                        setShrink(but1);
                        setShrink(but3);
                        break;
                    case R.id.but3:
                        pager.setCurrentItem(2);
                        setMagnify(but3);
                        setShrink(but1);
                        setShrink(but2);
                        break;
                }
            }
        });
    }

    private void setMagnify(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f);
        animatorSet.setDuration(0);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(objectAnimator).with(objectAnimator1);
        animatorSet.start();
    }

    private void setShrink(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1f);
        animatorSet.setDuration(0);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(objectAnimator).with(objectAnimator1);
        animatorSet.start();

    }


}
