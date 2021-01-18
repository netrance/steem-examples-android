package lee.dorian.steem.examples.get_account_reputations;

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
    public void loadReputationList() throws InterruptedException {
        MainViewModel mvm = new MainViewModel();

        mvm.loadReputationList("dorian", 100);
        Thread.sleep(COMMON_DELAY_MILLISECONDS);
        assertNull(mvm.liveError.getValue());
        assertNotNull(mvm.liveReputationList);
        assertNotNull(mvm.liveReputationList.getValue());
        assertTrue(mvm.liveReputationList.getValue().size() == 100);
        assertEquals("dorian", mvm.liveReputationList.getValue().get(0).getName());
    }

}
