package lee.dorian.steem.examples.get_follow_count.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FollowCountParams {

    private String jsonrpc;
    private String method;
    private Params params;
    private int id;

    public FollowCountParams(String account) {
        jsonrpc = "2.0";
        method = "follow_api.get_follow_count";
        params = Params.of(account);
        id = 1;
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class Params {
        private String account;
    }

}
