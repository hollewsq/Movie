package movie.bw.com.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.entity.RecommendBean;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RHodel> {
    private Context context;
    private List<RecommendBean.ResultBean> result;

    public RecommendAdapter(Context context, List<RecommendBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recommend, viewGroup, false);
        RHodel rHodel = new RHodel(view);
        return rHodel;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RHodel rHodel, int i) {
        rHodel.recommendItemImage.setImageURI(result.get(i).getLogo());
        rHodel.recommendItemTitle.setText(result.get(i).getName());
        rHodel.recommendItemContent.setText(result.get(i).getAddress());
        rHodel.address.setText(result.get(i).getDistance()+"");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class RHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.recommend_item_image)
        SimpleDraweeView recommendItemImage;
        @BindView(R.id.recommend_item_title)
        TextView recommendItemTitle;
        @BindView(R.id.recommend_item_content)
        TextView recommendItemContent;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.recommend_item_mind)
        CheckBox recommendItemMind;
        public RHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
