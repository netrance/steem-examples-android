package lee.dorian.steem.examples.get_following;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MainViewModelTest {

    private static final int COMMON_DELAY_MILLISECONDS = 10000;

    // Refer to: https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void loadFollowingList() throws InterruptedException {
        MainViewModel mvm = new MainViewModel();

        mvm.loadFollowingList("dorian-lee", 100);
        Thread.sleep(COMMON_DELAY_MILLISECONDS);
        assertNull(mvm.liveError.getValue());
        assertNotNull(mvm.liveFollowingList);
        assertNotNull(mvm.liveFollowingList.getValue());
        assertTrue(mvm.liveFollowingList.getValue().size() == 100);
        assertEquals("dorian-lee", mvm.liveFollowingList.getValue().get(0).getFollower());
    }

    @Test
    public void loadFollowingList_FailCase() throws InterruptedException {
        MainViewModel mvm = new MainViewModel();
        String invalidAccount = "dorian-leeeee";

        mvm.loadFollowingList(invalidAccount, 100);
        Thread.sleep(COMMON_DELAY_MILLISECONDS);
        assertNotNull(mvm.liveError);
        assertNotNull(mvm.liveFollowingList);
        assertNotNull(mvm.liveFollowingList.getValue());
        assertTrue(mvm.liveFollowingList.getValue().size() == 0);
        assertTrue(mvm.liveError.getValue().data.contains("account not found"));
        assertTrue(mvm.liveError.getValue().data.contains(invalidAccount));
    }

    @Test
    public void loadMoreFollowings() throws InterruptedException {
        MainViewModel mvm = new MainViewModel();

        mvm.loadFollowingList("dorian-lee", 100);
        Thread.sleep(COMMON_DELAY_MILLISECONDS);
        mvm.loadMoreFollowings("dorian-lee", 100);
        Thread.sleep(COMMON_DELAY_MILLISECONDS);

        assertNull(mvm.liveError.getValue());
        assertNotNull(mvm.liveFollowingList);
        assertNotNull(mvm.liveFollowingList.getValue());
        assertTrue(mvm.liveFollowingList.getValue().size() == 200);
        assertEquals("dorian-lee", mvm.liveFollowingList.getValue().get(0).getFollower());
    }
}
