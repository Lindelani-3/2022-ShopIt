package com.developerdiindy.shopit;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Test
    public void isActivityLaunched() {
        ActivityScenario mActivityRule = ActivityScenario.launch(MainActivity.class);
        Espresso.onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));
    }

}