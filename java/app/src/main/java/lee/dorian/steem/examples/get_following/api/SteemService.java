package lee.dorian.steem.examples.get_following.api;

import io.reactivex.Flowable;
import lee.dorian.steem.examples.get_following.models.FollowingParams;
import lee.dorian.steem.examples.get_following.models.FollowingResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SteemService {

    @POST(".")
    Flowable<FollowingResponse> requestFollowing(
        @Body FollowingParams followingParams
    );

}
