package lee.dorian.steem.examples.find_vesting_delegations;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
        new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals(
            "lee.dorian.steem.examples.find_vesting_delegations",
            appContext.getPackageName()
        );
    }

    @Test
    public void testMainActivity() throws InterruptedException {
        onView(withId(R.id.etAccount)).perform(typeText("dorian-lee"));
        Thread.sleep(10000);

        // Tests if the vesting delegation list contains these accounts.
        onView(allOf(withId(R.id.tvAccount), withText("gotogether"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tvAccount), withText("minigame"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tvAccount), withText("reportup"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tvAccount), withText("support-kr"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tvAccount), withText("upvu"))).check(matches(isDisplayed()));
    }
}