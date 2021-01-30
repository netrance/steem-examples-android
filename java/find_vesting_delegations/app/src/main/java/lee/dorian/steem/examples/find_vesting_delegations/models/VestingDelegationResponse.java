package lee.dorian.steem.examples.find_vesting_delegations.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class VestingDelegationResponse {

    @SerializedName("jsonrpc")
    private String jsonrpc;

    @SerializedName("result")
    private Result result;

    @SerializedName("error")
    private Error error;

    @SerializedName("id")
    private int id;

    public List<VestingDelegation> getVestingDelegationList() {
        return (null == result) ? null : result.vestingDelegationList;
    }

    @Data
    static class Result {
        @SerializedName("delegations")
        private List<VestingDelegation> vestingDelegationList;
    }
}
