package lee.dorian.steem.examples.find_vesting_delegations;

import org.junit.Test;

import java.util.List;

import lee.dorian.steem.examples.find_vesting_delegations.api.SteemClient;
import lee.dorian.steem.examples.find_vesting_delegations.models.DynamicGlobalProperties;
import lee.dorian.steem.examples.find_vesting_delegations.models.VestingDelegation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SteemClientTest {

    SteemClient steemClient = new SteemClient();

    @Test
    public void getVestingDelegationList() {
        steemClient.getVestingDelegationList("dorian-lee").subscribe(
            (reputationResponse) -> {
                List<VestingDelegation> vestingDelegationList = reputationResponse.getVestingDelegationList();
                assertNotNull(vestingDelegationList);
                assertTrue(vestingDelegationList.size() >= 0);
                for (VestingDelegation r : vestingDelegationList) {
                    assertTrue(r.getDelegator().equals("dorian-lee"));
                }
            },
            (error) -> {
                fail("error: " + error.getMessage());
            },
            () -> {
            }
        );
    }

    @Test
    public void getDynamicGlogalProperties() {
        steemClient.getDynamicGlogalProperties().subscribe(
            (dynamicGlobalPropertiesResponse) -> {
                DynamicGlobalProperties dgp = dynamicGlobalPropertiesResponse.getResult();
                assertNotNull(dgp);
            },
            (error) -> {
                fail("error: " + error.getMessage());
            },
            () -> {
            }
        );
    }
}
