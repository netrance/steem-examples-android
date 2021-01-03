package lee.dorian.steem.examples.get_following.models;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class Following {
    @SerializedName("follower")
    private String follower;

    @SerializedName("following")
    private String following;

    @SerializedName("what")
    private List<String> what;

    public Following() {
        init();
    }

    public static List<Following> getFollowingList(FollowingResponse response) {
        List<Following> followingList = (null == response) ? null : response.getFollowingList();
        if (null == response.getFollowingList()) {
            return new LinkedList<>();
        }
        return followingList;
    }

    private void init() {
        following = "";
        follower = "";
        what = new LinkedList<>();
    }
}
