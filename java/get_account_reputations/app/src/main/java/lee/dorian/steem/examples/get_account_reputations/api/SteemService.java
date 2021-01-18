package lee.dorian.steem.examples.get_account_reputations.api;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.get_account_reputations.models.ReputationParams;
import lee.dorian.steem.examples.get_account_reputations.models.ReputationResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SteemService {

    @POST(".")
    Flowable<ReputationResponse> requestReputationList(
        @Body ReputationParams reputationParams
    );

}
