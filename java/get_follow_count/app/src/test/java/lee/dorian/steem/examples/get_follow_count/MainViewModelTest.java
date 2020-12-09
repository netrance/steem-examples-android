package lee.dorian.steem.examples.get_follow_count;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import lee.dorian.steem.examples.get_follow_count.models.FollowCount;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainViewModelTest {
    // Refer to: https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void loadFollowCount() throws InterruptedException {
        MainViewModel mvm = new MainViewModel();

        mvm.loadFollowCount("dorian-lee");
        Thread.sleep(10000);
        assertNotNull(mvm.liveFollowCount);
        assertNotNull(mvm.liveFollowCount.getValue());
        assertEquals("dorian-lee", mvm.liveFollowCount.getValue().getAccount());
        assertTrue(mvm.liveFollowCount.getValue().getFollowingCount() > 0);
        assertTrue(mvm.liveFollowCount.getValue().getFollowerCount() > 0);
    }

    @Test
    public void loadFollowCount_FailCase() throws InterruptedException {
        MainViewModel mvm = new MainViewModel();

        mvm.loadFollowCount("dorian-leee");
        Thread.sleep(10000);
        assertNotNull(mvm.liveFollowCount);
        assertNotNull(mvm.liveFollowCount.getValue());
        assertEquals("(invalid account)", mvm.liveFollowCount.getValue().getAccount());
        assertTrue(mvm.liveFollowCount.getValue().getFollowingCount() == 0);
        assertTrue(mvm.liveFollowCount.getValue().getFollowerCount() == 0);
    }
}