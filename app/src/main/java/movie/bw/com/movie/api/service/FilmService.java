package movie.bw.com.movie.api.service;

import java.util.HashMap;

import io.reactivex.Observable;
import movie.bw.com.movie.entity.CommentBean;
import movie.bw.com.movie.entity.FilmDetailsBean;
import movie.bw.com.movie.entity.HeatBean;
import movie.bw.com.movie.entity.IdDetailsBean;
import movie.bw.com.movie.entity.PurchaseBean;
import movie.bw.com.movie.entity.ReviewBean;
import movie.bw.com.movie.entity.SchedulingdyBean;
import movie.bw.com.movie.entity.ShowHeatBean;
import movie.bw.com.movie.entity.SoonBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface FilmService {
    @GET
    Observable<HeatBean> getHeat(@Url String url, @QueryMap HashMap<String, Integer> params, @HeaderMap HashMap<String, String> head);

    @GET
    Observable<ShowHeatBean> getShowHeat(@Url String url, @QueryMap HashMap<String, Integer> params, @HeaderMap HashMap<String, String> head);

    @GET
    Observable<SoonBean> getSoon(@Url String url, @QueryMap HashMap<String, Integer> params, @HeaderMap HashMap<String, String> head);

    //查询即将上映电影列表
    @GET
    Observable<FilmDetailsBean> getDetails(@Url String url, @QueryMap HashMap<String, String> params, @HeaderMap HashMap<String, String> head);

    //根据电影ID查询电影信息
    @GET
    Observable<IdDetailsBean> getDetailsId(@Url String url, @QueryMap HashMap<String, String> params, @HeaderMap HashMap<String, String> head);

    //查询影片列表
    @GET
    Observable<ReviewBean> getReview(@Url String url, @QueryMap HashMap<String, String> params, @HeaderMap HashMap<String, String> head);

    //写评论
    @POST
    @FormUrlEncoded
    Observable<CommentBean> getComment(@Url String url, @FieldMap HashMap<String, String> params, @HeaderMap HashMap<String, String> head);

    //根据电影ID查询当前排片该电影的影院列表
    @GET
    Observable<PurchaseBean> getPurchase(@Url String url, @Query("movieId") String id);

    //根据电影ID和影院ID查询电影排期列表
    @GET
    Observable<SchedulingdyBean> getSchedulingdy(@Url String url, @QueryMap HashMap<String, Integer> params, @HeaderMap HashMap<String, String> head);
}
