package lee.dorian.steem.examples.find_vesting_delegations.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Refer to https://developers.steem.io/apidefinitions/#condenser_api.get_dynamic_global_properties
 */
public class DynamicGlobalPropertiesParams {

    //-----------------------------------------------------------------------------------------

    private String jsonrpc;
    private String method;
    private List<Object> params;
    private int id;

    //-----------------------------------------------------------------------------------------

    public DynamicGlobalPropertiesParams() {
        jsonrpc = "2.0";
        method = "condenser_api.get_dynamic_global_properties";
        params = new LinkedList<>();
        id = 1;
    }

}
