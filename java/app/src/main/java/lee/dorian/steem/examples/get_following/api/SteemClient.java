package lee.dorian.steem.examples.get_following.api;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.get_following.models.FollowingParams;
import lee.dorian.steem.examples.get_following.models.FollowingResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SteemClient {

    public static final String BASE_URL = "https://api.steemit.com";

    private Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();

    private SteemService requestClient = retrofit.create(SteemService.class);

    public SteemClient() {
    }

    public Flowable<FollowingResponse> getFollowing(String account, String startingAccount, String type, int limit) {
        return requestClient.requestFollowing(new FollowingParams(account, startingAccount, type, limit));
    }
}
