package movie.bw.com.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;
import movie.bw.com.movie.entity.ReviewBean;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.RHodel> {
    private Context context;
    private List<ReviewBean.ResultBean> result;

    public ReviewAdapter(Context context, List<ReviewBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_review, viewGroup, false);
        RHodel rHodel = new RHodel(view);
        return rHodel;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RHodel rHodel, int i) {
        rHodel.filmreviewHeard.setImageURI(result.get(i).getCommentHeadPic());
        rHodel.filmreviewName.setText(result.get(i).getCommentUserName());

        rHodel.filmreviewPinglun.setText(result.get(i).getCommentContent());
        rHodel.filmreviewTime.setText(result.get(i).getCommentTime()+"");
        rHodel.filmTextNumber.setText(result.get(i).getGreatNum()+"");
        rHodel.filmreviewReply.setText(result.get(i).getReplyNum()+"");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class RHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.filmreview_heard)
        SimpleDraweeView filmreviewHeard;
        @BindView(R.id.filmreview_name)
        TextView filmreviewName;
        @BindView(R.id.one)
        RelativeLayout one;
        @BindView(R.id.filmreview_pinglun)
        TextView filmreviewPinglun;
        @BindView(R.id.chaxunpinglun)
        RecyclerView chaxunpinglun;
        @BindView(R.id.filmreview_time)
        TextView filmreviewTime;
        @BindView(R.id.dianzancheckbox)
        CheckBox dianzancheckbox;
        @BindView(R.id.film_text_number)
        TextView filmTextNumber;
        @BindView(R.id.two)
        RelativeLayout two;
        @BindView(R.id.pingluncheckbox)
        ImageView pingluncheckbox;
        @BindView(R.id.filmreview_reply)
        TextView filmreviewReply;
        @BindView(R.id.three)
        RelativeLayout three;

        public RHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
