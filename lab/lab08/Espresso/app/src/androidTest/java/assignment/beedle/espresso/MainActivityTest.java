package assignment.beedle.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)


public class MainActivityTest {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest1() {
        //Add no user, age

        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        //SystemClock.sleep(2000);

        onView(
                allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
    }

    @Test
    public void mainActivityTest2() {
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(
                allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
        //SystemClock.sleep(2000);
        //mainActivityTest3();
        //
    }

    @Test
    public void mainActivityTest3() {
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(
                allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
        //SystemClock.sleep(2000);
        //mainActivityTest3();
        //
    }

    @Test
    public void mainActivityTest4() {

        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());
        //SystemClock.sleep(2000);

        onView(
                allOf(withId(R.id.textNotFound), withText("Not Found"), isDisplayed())).check(matches(withText("Not Found")));
        //
    }

    @Test
    public void mainActivityTest5() {

        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        //SystemClock.sleep(2000);
        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());
        //SystemClock.sleep(2000);
        //

        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName))
                .check(matches((withText("Ying"))));

        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge))
                .check(matches((withText("20"))));

    }

    @Test
    public void mainActivityTest6() {
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName))
                .check(matches((withText("Ladarat"))));

        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge))
                .check(matches((withText("20"))));

    }

    @Test
    public void mainActivityTest7() {
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());

        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName))
                .check(matches((withText("Somkait"))));

        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge))
                .check(matches((withText("80"))));

    }

    @Test
    public void mainActivityTest8() {

        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("60"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName))
                .check(matches((withText("Prayoch"))));

        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge))
                .check(matches((withText("60"))));

    }

    @Test
    public void mainActivityTest9() {
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("60"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("50"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textName))
                .check(matches((withText("Prayoch"))));

        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textAge))
                .check(matches((withText("50"))));

    }

    @Test
    public void mainActivityTest10() {
        onView(
                allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("50"), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());
        onView(
                allOf(withId(R.id.clearList), withText("Clear List"), isDisplayed())).perform(click());

    }

}
