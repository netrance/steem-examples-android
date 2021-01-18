package lee.dorian.steem.examples.get_account_reputations.models;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class Reputation {
    @SerializedName("name")
    private String name;

    @SerializedName("reputation")
    private long reputation;

    public Reputation() {
        init();
    }

    public static List<Reputation> getReputationList(ReputationResponse response) {
        ReputationResponse.Result result = (null == response) ? null : response.getResult();
        List<Reputation> reputationList = (null == result) ? null : result.getReputationList();

        if (null == reputationList) {
            return new LinkedList<>();
        }

        return reputationList;
    }

    private void init() {
        name = "";
        reputation = 0L;
    }

    // Reference: https://steemit.com/utopian-io/@raserrano/how-to-calculate-user-reputation
    public String getReadableReputation() {
        double readableReputation = (double)reputation;
        long multi = (readableReputation < 0) ? -9 : 9;
        readableReputation = Math.log10(Math.abs(readableReputation));
        readableReputation = Math.max(readableReputation - 9, 0);
        readableReputation *= multi;
        readableReputation += 25;

        return String.format("%.2f", readableReputation);
    }
}
