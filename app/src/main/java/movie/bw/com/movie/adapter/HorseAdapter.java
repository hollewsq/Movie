package movie.bw.com.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import recycler.coverflow.RecyclerCoverFlow;

public class HorseAdapter extends RecyclerCoverFlow.Adapter<HorseAdapter.RHodel> {
    private Context context;
    private List<HeatBean.ResultBean> result;

    public HorseAdapter(Context context) {
        this.context = context;
        this.result = new ArrayList<>();
    }

    public void setResult(List<HeatBean.ResultBean> result) {
        if (result != null) {
            this.result.addAll(result);
        }
    }

    @NonNull
    @Override
    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_horse, viewGroup, false);
        RHodel rHodel = new RHodel(view);
        return rHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull RHodel rHodel, final int i) {
        if (result.size() != 0) {
            Uri parse = Uri.parse(result.get(i % result.size()).getImageUrl());
            rHodel.img1.setImageURI(parse);
            rHodel.name.setText(result.get(i % result.size()).getName());
            rHodel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieId", result.get(i % result.size()).getId()+"");
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class RHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.img1)
        SimpleDraweeView img1;
        @BindView(R.id.bg)
        ImageView bg;
        @BindView(R.id.name)
        TextView name;

        public RHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
