package pya.marlon.com.rxjavaproject.domain.ro;

import java.util.ArrayList;

import pya.marlon.com.rxjavaproject.domain.model.UserBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by marlonpya on 20/05/17.
 */

public interface RetrofitService {
    @GET("users/{user}/starred")
    Observable<ArrayList<UserBean>> getStarredRepositories(@Path("user") String userName);

}
