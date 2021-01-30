package lee.dorian.steem.examples.find_vesting_delegations.api;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalPropertiesParams;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalPropertiesResponse;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegationParams;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegationResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SteemService {

    @POST(".")
    Flowable<VestingDelegationResponse> requestVestingDelegationList(
        @Body VestingDelegationParams reputationParams
    );

    @POST(".")
    Flowable<DynamicGlobalPropertiesResponse> requestDynamicGlobalProperties(
        @Body DynamicGlobalPropertiesParams dynamicGlobalPropertiesParams
    );

}
