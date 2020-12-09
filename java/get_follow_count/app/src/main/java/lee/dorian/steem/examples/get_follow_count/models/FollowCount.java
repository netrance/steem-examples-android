package lee.dorian.steem.examples.get_follow_count.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class FollowCount {
    @SerializedName("account")
    private String account;

    @SerializedName("following_count")
    private int followingCount;

    @SerializedName("follower_count")
    private int followerCount;

    public FollowCount() {
        init();
    }

    public FollowCount(FollowCountResponse response) {
        if ((null == response) || (null == response.getFollowCount())) {
            init();
            account = "(invalid account)";
            return;
        }

        account = response.getFollowCount().account;
        followingCount = response.getFollowCount().followingCount;
        followerCount = response.getFollowCount().followerCount;
    }

    private void init() {
        account = "";
        followingCount = followerCount = 0;
    }
}
