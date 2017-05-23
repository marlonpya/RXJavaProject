package pya.marlon.com.rxjavaproject;

import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.appevents.AppEventsLogger;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import pya.marlon.com.rxjavaproject.domain.model.UserBean;
import pya.marlon.com.rxjavaproject.domain.ro.RetrofitService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by marlonpya on 20/05/17.
 */

public class MyApplication extends Application {
    public static final String BASE_URL = "https://api.github.com/";

    private static MyApplication instance;
    private RetrofitService service;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppEventsLogger.activateApp(this);
    }

    public RetrofitService getService() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return service = retrofit.create(RetrofitService.class);
    }

    public static MyApplication getInstance() {
        return instance == null ? new MyApplication() : instance;
    }

    public Observable<ArrayList<UserBean>> getStarredRepos(@NonNull String userName) {
        return service.getStarredRepositories(userName);
    }

}
