package lee.dorian.steem.examples.find_vesting_delegations.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class DynamicGlobalProperties {

    @SerializedName("head_block_number")
    private int headBlockNumber;

    @SerializedName("head_block_id")
    private String headBlockId;

    @SerializedName("time")
    private String time;

    @SerializedName("current_witness")
    private String currentWitness;

    @SerializedName("total_pow")
    private int totalPow;

    @SerializedName("num_pow_witnesses")
    private int numPowWitness;

    @SerializedName("virtual_supply")
    private String visualSupply;

    @SerializedName("current_supply")
    private String currentSupply;

    @SerializedName("confidential_supply")
    private String confidentialSupply;

    @SerializedName("init_sbd_supply")
    private String initSBDSupply;

    @SerializedName("current_sbd_supply")
    private String currentSBDSupply;

    @SerializedName("confidential_sbd_supply")
    private String confidentialSBDSupply;

    @SerializedName("total_vesting_fund_steem")
    private String totalVestingFundSteem;

    @SerializedName("total_vesting_shares")
    private String totalVestingShares;

    @SerializedName("total_reward_fund_steem")
    private String totalRewardFundSteem;

    @SerializedName("total_reward_shares2")
    private String totalRewardShares2;

    @SerializedName("pending_rewarded_vesting_shares")
    private String pendingRewardedVestingShares;

    @SerializedName("pending_rewarded_vesting_steem")
    private String pendingRewardedVestingSteem;

    @SerializedName("sbd_interest_rate")
    private int sbdInterestRate;

    @SerializedName("sbd_print_rate")
    private int sbdPrintRate;

    @SerializedName("maximum_block_size")
    private int maximumBlockSize;

    @SerializedName("required_actions_partition_percent")
    private int requiredActionsPartitionPercent;

    @SerializedName("current_aslot")
    private int currentAslot;

    @SerializedName("recent_slots_filled")
    private String recentSlotsFilled;

    @SerializedName("participation_count")
    private int participationCount;

    @SerializedName("last_irreversible_block_num")
    private int lastIrreversibleBlockNum;

    @SerializedName("vote_power_reserve_rate")
    private int votePowerReserveRate;

    @SerializedName("delegation_return_period")
    private int delegationReturnPeriod;

    @SerializedName("reverse_auction_seconds")
    private int reverseAuctionSeconds;

    @SerializedName("available_account_subsidies")
    private int availableAccountSubsidies;

    @SerializedName("sbd_stop_percent")
    private int sbdStopPercent;

    @SerializedName("sbd_start_percent")
    private int sbdStartPercent;

    @SerializedName("next_maintenance_time")
    private String nextMaintenanceTime;

    @SerializedName("last_budget_time")
    private  String lastBudgetItem;

    @SerializedName("content_reward_percent")
    private int contentRewardPercent;

    @SerializedName("vesting_reward_percent")
    private int vestingRewardPercent;

    @SerializedName("sps_fund_percent")
    private int spsFundPercent;

    @SerializedName("sps_interval_ledger")
    private String spsIntervalLedger;

    @SerializedName("downvote_pool_percent")
    private int downvotePoolPercent;

    public static DynamicGlobalProperties shared = null;

}
