package lee.dorian.steem.examples.get_account_reputations;

import org.junit.Test;

import java.util.List;

import lee.dorian.steem.examples.get_account_reputations.api.SteemClient;
import lee.dorian.steem.examples.get_account_reputations.models.Reputation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SteemClientTest {

    SteemClient steemClient = new SteemClient();

    @Test
    public void getReputationList() {
        steemClient.getReputationList("dorian", 10).subscribe(
            (reputationResponse) -> {
                List<Reputation> reputationList = reputationResponse.getReputationList();
                assertNotNull(reputationList);
                assertTrue(reputationList.size() >= 0);
                assertTrue(reputationList.size() <= 10);
                for (Reputation r : reputationList) {
                    assertTrue(r.getName().startsWith("dorian"));
                }
            },
            (error) -> {
                fail("error: " + error.getMessage());
            },
            () -> {
            }
        );
    }

}
