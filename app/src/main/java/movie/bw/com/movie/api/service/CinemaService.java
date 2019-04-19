package movie.bw.com.movie.api.service;

import java.util.HashMap;

import io.reactivex.Observable;
import movie.bw.com.movie.entity.RecommendBean;
import movie.bw.com.movie.entity.SchedulingdyBean;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface CinemaService {
    @GET
    Observable<RecommendBean> getRecommend(@Url String url, @QueryMap HashMap<String, Integer> params, @HeaderMap HashMap<String, String> head);

}
