package com.prashant.demowipro.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.prashant.demowipro.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class ToolBarTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity
            .class);

    @Test
    public void mainActivityTest2() {

        getInstrumentation().waitForIdleSync();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withText("About Canada"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget
                                                        .LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("About Canada")));

        ViewInteraction textView2 = onView(
                allOf(withText("Beavers"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card_view),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Beavers")));

        ViewInteraction textView3 = onView(
                allOf(withText("Housing"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card_view),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Housing")));


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
