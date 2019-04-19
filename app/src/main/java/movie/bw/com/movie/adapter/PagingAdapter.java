package movie.bw.com.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.acitivty.DetailsActivity;
import movie.bw.com.movie.entity.HeatBean;
import movie.bw.com.movie.entity.ShowHeatBean;
import movie.bw.com.movie.entity.SoonBean;

public class PagingAdapter extends RecyclerView.Adapter<PagingAdapter.RHodel> {
    private Context context;
    private List<HeatBean.ResultBean> heat;
    private List<ShowHeatBean.ResultBean> start;
    private List<SoonBean.ResultBean> soon;
    int type = 1;

    public PagingAdapter(Context context) {
        this.context = context;
        this.heat = new ArrayList<>();
        this.start = new ArrayList<>();
        this.soon = new ArrayList<>();
    }

    public void setResult1(List<HeatBean.ResultBean> result1, int type) {
        this.heat.clear();
        if (result1 != null) {
            this.heat.addAll(result1);
            this.type = type;
        }
    }

    public void setResult2(List<ShowHeatBean.ResultBean> result2, int type) {
        this.start.clear();
        if (result2 != null) {
            this.start.addAll(result2);
            this.type = type;
        }
    }

    public void setResult3(List<SoonBean.ResultBean> result3, int type) {
        this.soon.clear();
        if (result3 != null) {
            this.soon.addAll(result3);
            this.type = type;
        }
    }

    @NonNull
    @Override
    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotmovie_item_layout, viewGroup, false);
        RHodel rHodel = new RHodel(view);
        return rHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull RHodel rHodel, final int i) {
        if (type == 1){
            rHodel.name.setText(heat.get(i).getName());
            rHodel.content.setText(heat.get(i).getSummary());
            rHodel.hotItemImage.setImageURI(heat.get(i).getImageUrl());

            rHodel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieId", heat.get(i).getId()+"");
                    context.startActivity(intent);
                }
            });

       }else if (type == 2){
            rHodel.name.setText(start.get(i).getName());
            rHodel.content.setText(start.get(i).getSummary());
            rHodel.hotItemImage.setImageURI(start.get(i).getImageUrl());

            rHodel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieId", start.get(i).getId()+"");
                    context.startActivity(intent);
                }
            });

        }else if (type == 3){
            rHodel.name.setText(soon.get(i).getName());
            rHodel.content.setText(soon.get(i).getSummary());
            rHodel.hotItemImage.setImageURI(soon.get(i).getImageUrl());

            rHodel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieId", soon.get(i).getId()+"");
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (type == 1){
            return heat.size();
        }else if (type == 2){
            return start.size();
        }
        else if(type == 3){
            return soon.size();
        }else {
            return heat.size();
        }

    }

    public class RHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.attention)
        CheckBox attention;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.aaaaaaaaa)
        RelativeLayout aaaaaaaaa;
        @BindView(R.id.hot_item_image)
        SimpleDraweeView hotItemImage;
        public RHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
