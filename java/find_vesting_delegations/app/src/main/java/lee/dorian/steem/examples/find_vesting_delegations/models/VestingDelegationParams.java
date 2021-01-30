package lee.dorian.steem.examples.find_vesting_delegations.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Refer to https://developers.steem.io/apidefinitions/#database_api.find_vesting_delegations
 */
@Data
public class VestingDelegationParams {

    //-----------------------------------------------------------------------------------------

    private String jsonrpc;
    private String method;
    private Map<String, Object> params;
    private int id;

    //-----------------------------------------------------------------------------------------

    public VestingDelegationParams(String account) {
        jsonrpc = "2.0";
        method = "database_api.find_vesting_delegations";
        params = new HashMap<>();
        params.put("account", account);
        id = 1;
    }

}
