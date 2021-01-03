package lee.dorian.steem.examples.get_following;

import org.junit.Test;

import java.util.List;

import lee.dorian.steem.examples.get_following.api.SteemClient;
import lee.dorian.steem.examples.get_following.models.Following;
import lee.dorian.steem.examples.get_following.models.FollowingParams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SteemClientTest {

    SteemClient steemClient = new SteemClient();

    @Test
    public void getFollowing() {
        steemClient.getFollowing("dorian-lee", "", FollowingParams.TYPE_BLOG, 1000).subscribe(
            (followingResponse) -> {
                List<Following> followingList = followingResponse.getFollowingList();
                assertNotNull(followingList);
                assertTrue(followingList.size() > 0);
                for (Following f : followingList) {
                    assertEquals("dorian-lee", f.getFollower());
                    assertNotNull(f.getWhat());
                    assertEquals(1, f.getWhat().size());
                    assertEquals(FollowingParams.TYPE_BLOG, f.getWhat().get(0));
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
    public void getFollowing_fromInvalidAccount() {
        steemClient.getFollowing("dorian-lee-1", "", FollowingParams.TYPE_BLOG, 1000).subscribe(
            (followingResponse) -> {
                List<Following> followingList = followingResponse.getFollowingList();
                lee.dorian.steem.examples.get_following.models.Error error = followingResponse.getError();
                assertNull(followingList);
                assertNotNull(error);
                assertEquals(-32602, error.code);
            },
            (error) -> {
                fail("error: " + error.getMessage());
            },
            () -> {
            }
        );
    }
}
