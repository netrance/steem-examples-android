package lee.dorian.steem.examples.get_follow_count;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.get_follow_count.models.FollowCountResponse;
import lee.dorian.steem.examples.get_follow_count.models.FollowCountParams;
import retrofit2.Callback;
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

    public Flowable<FollowCountResponse> getFollowCount(String account) {
        return requestClient.requestFlowableFollowCount(new FollowCountParams(account));
    }
}
