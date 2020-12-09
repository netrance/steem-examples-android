package lee.dorian.steem.examples.get_follow_count;

import org.junit.Test;

import lee.dorian.steem.examples.get_follow_count.models.FollowCount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SteemClientTest {


    @Test
    public void loadFollowCount() {
        SteemClient steemClient = new SteemClient();

        steemClient.getFollowCount("dorian-lee").subscribe(
            (followCountResponse) -> {
                FollowCount followCount = new FollowCount(followCountResponse);
                assertEquals("dorian-lee", followCount.getAccount());
                assertTrue(followCount.getFollowingCount() > 0);
                assertTrue(followCount.getFollowerCount() > 0);
            },
            (error) -> {
                fail("error: " + error.getMessage());
            },
            () -> {
            }
        );
    }
}
