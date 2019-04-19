package movie.bw.com.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.bw.com.movie.R;

public class StillsAdapter extends RecyclerView.Adapter<StillsAdapter.XHodel> {
    private Context context;
    private List<String> posterList;

    public StillsAdapter(Context context, List<String> posterList) {
        this.context = context;
        this.posterList = posterList;
    }

    @NonNull
    @Override
    public XHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_still, viewGroup, false);
        XHodel xHodel = new XHodel(view);
        return xHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull XHodel xHodel, int i) {
        Glide.with(context).load(posterList.get(i)).into(xHodel.img);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class XHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        public XHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
