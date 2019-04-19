package movie.bw.com.movie.net;

public interface PMCallback {
    void onFail(String msg);
    void onSuccess(Object reg);
}
