package lee.dorian.steem.examples.find_vesting_delegations.api;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalProperties;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalPropertiesParams;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalPropertiesResponse;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegationParams;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegationResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SteemClient {

    public static final String BASE_URL = "https://api.steemit.com";

    public static final int RETRY_COUNT = 3;

    private Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();

    private SteemService requestClient = retrofit.create(SteemService.class);

    public SteemClient() {
    }

    public Flowable<VestingDelegationResponse> getVestingDelegationList(String account) {
        return requestClient.requestVestingDelegationList(new VestingDelegationParams(account));
    }

    public Flowable<DynamicGlobalPropertiesResponse> getDynamicGlogalProperties() {
        return requestClient.requestDynamicGlobalProperties(new DynamicGlobalPropertiesParams());
    }

}
