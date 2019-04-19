package movie.bw.com.movie.api.service;

import java.util.HashMap;

import io.reactivex.Observable;
import movie.bw.com.movie.entity.LoginBean;
import movie.bw.com.movie.entity.RegisterBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UserService {
    //登录
    @POST
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Url String url, @FieldMap HashMap<String, String> params);

    //注册
    @POST
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Url String url, @FieldMap HashMap<String, String> params);
}
