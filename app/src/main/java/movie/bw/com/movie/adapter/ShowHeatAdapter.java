package movie.bw.com.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class ShowHeatAdapter extends RecyclerView.Adapter<ShowHeatAdapter.RHodel> {
    private Context context;
    private List<ShowHeatBean.ResultBean> result;

    public ShowHeatAdapter(Context context) {
        this.context = context;
        this.result = new ArrayList<>();
    }

    public void setResult(List<ShowHeatBean.ResultBean> result) {
        if (result != null) {
            this.result = result;
        }
    }

    @NonNull
    @Override
    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_showheat, viewGroup, false);
        RHodel rHodel = new RHodel(view);
        return rHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull RHodel rHodel, int i) {
        final ShowHeatBean.ResultBean resultBean = result.get(i);
        rHodel.img.setImageURI(resultBean.getImageUrl());
        rHodel.name.setText(resultBean.getName());
        rHodel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("movieId", resultBean.getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class RHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        SimpleDraweeView img;
        @BindView(R.id.bg)
        ImageView bg;
        @BindView(R.id.v1)
        View v1;
        @BindView(R.id.name)
        TextView name;
        public RHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
