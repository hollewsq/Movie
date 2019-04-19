package movie.bw.com.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import movie.bw.com.movie.adapter.RecommendAdapter;
import movie.bw.com.movie.contract.RecommendContract;
import movie.bw.com.movie.entity.RecommendBean;
import movie.bw.com.movie.presenter.RecommendPresenter;

public class CinemaFragment extends BaseMvpFragment<RecommendContract.IModel, RecommendContract.IPresenter> implements RecommendContract.IView {

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
    @BindView(R.id.but1)
    RadioButton but1;
    @BindView(R.id.but2)
    RadioButton but2;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.rv)
    RecyclerView rv;
    private RecommendAdapter recommendAdapter;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
    }

    @Override
    protected void init() {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("page", 1);
        params.put("count", 10);

        SharedPreferences h = getActivity().getSharedPreferences("H", Context.MODE_PRIVATE);
        String userId = h.getString("userId", null);
        String sessionId = h.getString("sessionId", null);
        HashMap<String, String> head = new HashMap<>();
        head.put("userId", userId);
        head.put("sessionId", sessionId);
        presenter.jumpRecommend(params, head);
    }

    @Override
    public void onFailRecommend(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRecommend(Object reg) {
        RecommendBean recommendBean = (RecommendBean) reg;
        List<RecommendBean.ResultBean> result = recommendBean.getResult();
        //创建适配器
        recommendAdapter = new RecommendAdapter(getActivity(), result);
        rv.setAdapter(recommendAdapter);
    }

    @Override
    public BasePresenter initPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected void loadData() {

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
    public void onDestroyView() {
        super.onDestroyView();
        if (recommendAdapter != null){
            recommendAdapter = null;
        }
    }
}
