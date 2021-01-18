package lee.dorian.steem.examples.get_account_reputations.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Refer to https://developers.steem.io/apidefinitions/condenser-api#condenser_api.get_account_reputations
 */
@Data
public class ReputationParams {

    //-----------------------------------------------------------------------------------------

    private String jsonrpc;
    private String method;
    private Map<String, Object> params;
    private int id;

    //-----------------------------------------------------------------------------------------

    public ReputationParams(String account, int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit parameter must be greater than or equal to 0.");
        }

        jsonrpc = "2.0";
        method = "follow_api.get_account_reputations";
        params = new HashMap<>();
        params.put("account_lower_bound", account);
        params.put("limit", limit);
        id = 1;
    }

}
