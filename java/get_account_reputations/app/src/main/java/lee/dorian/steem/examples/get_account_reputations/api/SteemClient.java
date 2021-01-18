package lee.dorian.steem.examples.get_account_reputations.api;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.get_account_reputations.models.ReputationParams;
import lee.dorian.steem.examples.get_account_reputations.models.ReputationResponse;
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

    public Flowable<ReputationResponse> getReputationList(String account, int limit) {
        return requestClient.requestReputationList(new ReputationParams(account, limit));
    }
}
