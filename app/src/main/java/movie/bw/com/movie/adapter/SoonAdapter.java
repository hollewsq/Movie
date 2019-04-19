package movie.bw.com.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.acitivty.DetailsActivity;
import movie.bw.com.movie.entity.ShowHeatBean;
import movie.bw.com.movie.entity.SoonBean;

public class SoonAdapter extends BaseQuickAdapter<SoonBean.ResultBean,BaseViewHolder> {


    public SoonAdapter(int layoutResId, @Nullable List<SoonBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SoonBean.ResultBean item) {
        helper.setText(R.id.name,item.getName());
        SimpleDraweeView img = helper.getView(R.id.img);
        img.setImageURI(item.getImageUrl());

    }
//    private Context context;
//    private List<SoonBean.ResultBean> result;
//
//    public SoonAdapter(Context context) {
//        this.context = context;
//        this.result = new ArrayList<>();
//    }
//
//    public void setResult(List<SoonBean.ResultBean> result) {
//        if (result != null) {
//            this.result.addAll(result);
//        }
//    }
//
//    @NonNull
//    @Override
//    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(context).inflate(R.layout.adapter_soon, viewGroup, false);
//        RHodel rHodel = new RHodel(view);
//        return rHodel;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RHodel rHodel, int i) {
//        final SoonBean.ResultBean resultBean = result.get(i);
//        rHodel.img.setImageURI(resultBean.getImageUrl());
//        rHodel.name.setText(resultBean.getName());
//        rHodel.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailsActivity.class);
//                intent.putExtra("movieId", resultBean.getId()+"");
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return result.size();
//    }
//
//    public class RHodel extends RecyclerView.ViewHolder {
//        @BindView(R.id.img)
//        SimpleDraweeView img;
//        @BindView(R.id.bg)
//        ImageView bg;
//        @BindView(R.id.v1)
//        View v1;
//        @BindView(R.id.name)
//        TextView name;
//        public RHodel(@NonNull View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
}
