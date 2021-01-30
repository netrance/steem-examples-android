package lee.dorian.steem.examples.find_vesting_delegations.models;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class VestingDelegation {
    @SerializedName("id")
    private int id;

    @SerializedName("delegator")
    private String delegator;

    @SerializedName("delegatee")
    private String delegatee;

    @SerializedName("vesting_shares")
    private VestingShares vestingShares;

    @SerializedName("min_delegation_time")
    private String minDelegationTime;

    public VestingDelegation() {
        init();
    }

    public static List<VestingDelegation> getVestingDelegationList(VestingDelegationResponse response) {
        VestingDelegationResponse.Result result = (null == response) ? null : response.getResult();
        List<VestingDelegation> vestingDelegationList = (null == result) ? null : result.getVestingDelegationList();

        if (null == vestingDelegationList) {
            return new LinkedList<>();
        }

        return vestingDelegationList;
    }

    private void init() {
        id = 0;
        delegator = delegatee = "";
        vestingShares = new VestingShares();
        minDelegationTime = "";
    }

    @Data
    public static class VestingShares {

        @SerializedName("amount")
        private String amount;

        @SerializedName("precision")
        private int precision;

        @SerializedName("nai")
        private String nai;

        public String getDelegatedSP(DynamicGlobalProperties dgp) {
            float totalVestingFundSteem = Float.parseFloat(dgp.getTotalVestingFundSteem().replace("STEEM", "").trim());
            float vestingAmount = Float.parseFloat(amount);
            float totalVestingShare = Float.parseFloat(dgp.getTotalVestingShares().replace("VESTS", "").trim());
            String result =  String.format("%.3f SP", ((totalVestingFundSteem * vestingAmount) / (totalVestingShare * Math.pow(10, precision))));
            return result;
        }

    }

}
