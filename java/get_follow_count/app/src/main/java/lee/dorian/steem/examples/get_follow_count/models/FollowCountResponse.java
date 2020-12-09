package lee.dorian.steem.examples.get_follow_count.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FollowCountResponse {

    @SerializedName("jsonrpc")
    private String jsonrpc;

    @SerializedName("result")
    private FollowCount followCount;

    @SerializedName("error")
    private Error error;

    @SerializedName("id")
    private int id;

}
