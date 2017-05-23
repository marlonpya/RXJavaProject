package pya.marlon.com.rxjavaproject.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import pya.marlon.com.rxjavaproject.MyApplication;
import pya.marlon.com.rxjavaproject.R;
import pya.marlon.com.rxjavaproject.domain.model.UserBean;
import pya.marlon.com.rxjavaproject.view.adapter.UserAdapter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.rvUser) RecyclerView recyclerView;
    private Subscription subscription;
    private double time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        business();
    }

    private void business() {
        time = System.currentTimeMillis();
        subscription = MyApplication
                .getInstance()
                .getService()
                .getStarredRepositories("arriolac")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<UserBean>>() {
                    @Override
                    public void onCompleted() {
                        time = System.currentTimeMillis() - time;
                        Log.d(TAG, "time-> " + (time / 1000));
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(ArrayList<UserBean> userBeen) {
                        UserAdapter adapter = new UserAdapter(MainActivity.this, userBeen);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(adapter);
                        Observable.from(userBeen)
                                .forEach(user -> Log.d(TAG, user.toString()));
                    }
                });
    }

    @OnClick(R.id.btnFacebook)
    public void onFacebook() {
        Log.d(TAG, "click");
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        ShareDialog.show(this, content);
    }

    @OnClick(R.id.btnTwitter)
    public void onTwitter() {
        TweetComposer.Builder tweetComposer = new TweetComposer.Builder(this).text("fasfa");
        tweetComposer.show();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        super.onDestroy();
    }
}
