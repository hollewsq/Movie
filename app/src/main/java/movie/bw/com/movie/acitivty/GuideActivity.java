package movie.bw.com.movie.acitivty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.guidefragment.Frag1;
import movie.bw.com.movie.guidefragment.Frag2;
import movie.bw.com.movie.guidefragment.Frag3;
import movie.bw.com.movie.guidefragment.Frag4;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private RadioGroup radioGroup;
    private boolean misScrolled;
    private List<Fragment> list;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
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
        viewpager = findViewById(R.id.viewpager);
        radioGroup = findViewById(R.id.radiogroup);

        list = new ArrayList<>();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        list.add(new Frag1());
        list.add(new Frag2());
        list.add(new Frag3());
        list.add(new Frag4());

        transaction.commit();

        radioGroup.check(R.id.but1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.but2:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.but3:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.but4:
                        viewpager.setCurrentItem(3);
                        break;

                }
            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radioGroup.check(R.id.but1);
                        break;
                    case 1:
                        radioGroup.check(R.id.but2);
                        break;
                    case 2:
                        radioGroup.check(R.id.but3);
                        break;
                    case 3:
                        radioGroup.check(R.id.but4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

                switch (i) {

                    case ViewPager.SCROLL_STATE_DRAGGING:
                        misScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        misScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (viewpager.getCurrentItem() == viewpager.getAdapter().getCount() - 1 && !misScrolled) {
                            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                            GuideActivity.this.finish();
                        }
                        misScrolled = true;
                        break;
                }
            }

        });

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())

        {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }
}
