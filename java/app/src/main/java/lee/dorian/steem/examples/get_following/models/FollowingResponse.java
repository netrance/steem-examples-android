package lee.dorian.steem.examples.get_following.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class FollowingResponse {

    @SerializedName("jsonrpc")
    private String jsonrpc;

    @SerializedName("result")
    private List<Following> followingList;

    @SerializedName("error")
    private Error error;

    @SerializedName("id")
    private int id;

}
