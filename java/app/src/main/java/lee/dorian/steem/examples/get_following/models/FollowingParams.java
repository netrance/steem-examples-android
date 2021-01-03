package lee.dorian.steem.examples.get_following.models;

import lombok.Data;

/**
 * Refer to https://developers.steem.io/apidefinitions/condenser-api#condenser_api.get_following
 */
@Data
public class FollowingParams {

    //-----------------------------------------------------------------------------------------

    private String jsonrpc;
    private String method;
    private Object[] params;
    private int id;

    //-----------------------------------------------------------------------------------------

    public static final String TYPE_BLOG = "blog";
    public static final String TYPE_IGNORE = "ignore";

    //-----------------------------------------------------------------------------------------

    public FollowingParams(String account, String startingAccount, String type, int limit) {
        jsonrpc = "2.0";
        method = "follow_api.get_following";
        params = new Object[4];
        params[0] = account;
        params[1] = startingAccount;
        params[2] = type;
        params[3] = limit;
        id = 1;
    }

}
