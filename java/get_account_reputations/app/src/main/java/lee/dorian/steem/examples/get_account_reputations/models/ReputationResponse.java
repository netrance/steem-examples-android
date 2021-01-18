package lee.dorian.steem.examples.get_account_reputations.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ReputationResponse {

    @SerializedName("jsonrpc")
    private String jsonrpc;

    @SerializedName("result")
    private Result result;

    @SerializedName("error")
    private Error error;

    @SerializedName("id")
    private int id;

    public List<Reputation> getReputationList() {
        return (null == result) ? null : result.reputationList;
    }

    @Data
    static class Result {
        @SerializedName("reputations")
        private List<Reputation> reputationList;
    }
}
