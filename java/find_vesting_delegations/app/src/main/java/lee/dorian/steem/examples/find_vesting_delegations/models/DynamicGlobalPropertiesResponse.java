package lee.dorian.steem.examples.find_vesting_delegations.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class DynamicGlobalPropertiesResponse {

    @SerializedName("jsonrpc")
    private String jsonrpc;

    @SerializedName("result")
    private DynamicGlobalProperties result;

    @SerializedName("error")
    private Error error;

    @SerializedName("id")
    private int id;

}
