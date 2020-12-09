package lee.dorian.steem.examples.get_follow_count;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.get_follow_count.models.FollowCountResponse;
import lee.dorian.steem.examples.get_follow_count.models.FollowCountParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SteemService {

    @POST(".")
    Call<FollowCountResponse> requestFollowCount(
        @Body FollowCountParams followCountParams
    );

    @POST(".")
    Flowable<FollowCountResponse> requestFlowableFollowCount(
        @Body FollowCountParams followCountParams
    );
}
