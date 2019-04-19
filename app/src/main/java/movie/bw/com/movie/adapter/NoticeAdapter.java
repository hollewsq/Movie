package movie.bw.com.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import movie.bw.com.movie.R;
import movie.bw.com.movie.entity.FilmDetailsBean;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.RHodel> {
    private Context context;
    private List<FilmDetailsBean.ResultBean.ShortFilmListBean> shortFilmList;

    public NoticeAdapter(Context context, List<FilmDetailsBean.ResultBean.ShortFilmListBean> shortFilmList) {
        this.context = context;
        this.shortFilmList = shortFilmList;
    }

    @NonNull
    @Override
    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_play, viewGroup, false);
        RHodel rHodel = new RHodel(view);
        return rHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull RHodel rHodel, int i) {
        rHodel.mJC.setUp(shortFilmList.get(i).getVideoUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);

        Glide.with(context).load(shortFilmList.get(i).getImageUrl()).into(rHodel.mJC.thumbImageView);

        //rHodel.mJC.release();
    }

    @Override
    public int getItemCount() {
        return shortFilmList.size();
    }

    public class RHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.mJC)
        JZVideoPlayerStandard mJC;
        public RHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
