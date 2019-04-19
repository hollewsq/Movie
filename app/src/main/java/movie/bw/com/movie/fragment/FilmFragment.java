package movie.bw.com.movie.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import movie.bw.com.movie.R;
import movie.bw.com.movie.acitivty.PagingActivity;
import movie.bw.com.movie.adapter.HeatAdapter;
import movie.bw.com.movie.adapter.HorseAdapter;
import movie.bw.com.movie.adapter.ShowHeatAdapter;
import movie.bw.com.movie.adapter.SoonAdapter;
import movie.bw.com.movie.contract.FilmConteact;
import movie.bw.com.movie.entity.HeatBean;
import movie.bw.com.movie.entity.ShowHeatBean;
import movie.bw.com.movie.entity.SoonBean;
import movie.bw.com.movie.presenter.FilmPresenter;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class FilmFragment extends BaseMvpFragment<FilmConteact.IModel, FilmConteact.IPresenter> implements FilmConteact.IView {

    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.seek)
    ImageView seek;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.clickSearch)
    TextView clickSearch;
    @BindView(R.id.wood)
    RecyclerCoverFlow wood;
    @BindView(R.id.hot_move)
    TextView hotMove;
    @BindView(R.id.xian)
    TextView xian;
    @BindView(R.id.hot_move_jiantou)
    ImageView hotMoveJiantou;
    @BindView(R.id.heat)
    RecyclerView heat;
    @BindView(R.id.hot_now)
    TextView hotNow;
    @BindView(R.id.xian1)
    TextView xian1;
    @BindView(R.id.hot_now_jiantou)
    ImageView hotNowJiantou;
    @BindView(R.id.showheat)
    RecyclerView showheat;
    @BindView(R.id.coming_move)
    TextView comingMove;
    @BindView(R.id.xian2)
    TextView xian2;
    @BindView(R.id.coming_move_jiantou)
    ImageView comingMoveJiantou;
    @BindView(R.id.soon)
    RecyclerView soon;
    @BindView(R.id.proBar)
    SeekBar proBar;
    private HorseAdapter horseAdapter;
    private HeatAdapter heatAdapter;
    private ShowHeatAdapter showHeatAdapter;
    private SoonAdapter soonAdapter;
    private int mCoverFlowPosition = 0;
    private List<SoonBean.ResultBean> result;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_film;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        wood.setAlphaItem(true);
        heat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false));
        showheat.setLayoutManager(new LinearLayoutManager(getActivity(),  LinearLayout.HORIZONTAL, false));
        soon.setLayoutManager(new LinearLayoutManager(getActivity(),  LinearLayout.HORIZONTAL, false));

        hotMoveJiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PagingActivity.class);
                intent.putExtra("id",  1+"");
                startActivity(intent);
            }
        });

        hotNowJiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PagingActivity.class);
                intent.putExtra("id", 2+"");
                startActivity(intent);
            }
        });

        comingMoveJiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PagingActivity.class);
                intent.putExtra("id", 3+"");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void init() {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("page", 1);
        params.put("count", 10);

        SharedPreferences h = getActivity().getSharedPreferences("H", Context.MODE_PRIVATE);
        String userId = h.getString("userId", null);
        String sessionId = h.getString("sessionId", null);
        HashMap<String, String> heat = new HashMap<>();
        heat.put("userId", userId);
        heat.put("sessionId", sessionId);

        presenter.jumpHeat(params, heat);
        presenter.jumpShowHeat(params, heat);
        presenter.jumpSoon(params, heat);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessHeat(Object reg) {
        //创建适配器 旋转木马 热销
        horseAdapter = new HorseAdapter(getActivity());
        wood.setAdapter(horseAdapter);

        heatAdapter = new HeatAdapter(getActivity());
        heat.setAdapter(heatAdapter);

        HeatBean heatBean = (HeatBean) reg;
        final List<HeatBean.ResultBean> result = heatBean.getResult();
        horseAdapter.setResult(result);
        heatAdapter.setResult(result);
        wood.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                mCoverFlowPosition = position;
                proBar.setProgress(position % result.size() );
            }
        });
        proBar.setMax(result.size() - 1);
        proBar.setFocusable(false);
        wood.scrollToPosition(mCoverFlowPosition);
    }

    @Override
    public void onSuccessShowHeat(Object reg) {
        showHeatAdapter = new ShowHeatAdapter(getActivity());
        showheat.setAdapter(showHeatAdapter);

        ShowHeatBean showHeatBean = (ShowHeatBean) reg;
        List<ShowHeatBean.ResultBean> result = showHeatBean.getResult();
        showHeatAdapter.setResult(result);
    }

    @Override
    public void onSuccessSoon(Object reg) {
        SoonBean soonBean = (SoonBean) reg;
        result = soonBean.getResult();
        soonAdapter = new SoonAdapter(R.layout.adapter_soon,result);
        soon.setAdapter(soonAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        soon.setLayoutManager(linearLayoutManager);
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
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (heat != null){
            heat = null;
        }else if (showheat != null){
            showheat = null;
        }else if (soonAdapter != null){
            soonAdapter = null;
        }
    }
}
