package com.epam.test.android.espresso.junit5;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.epam.test.R;
import com.epam.test.ui.login.LoginActivity;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import de.mannodermaus.junit5.ActivityScenarioExtension;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

@ExtendWith(AndroidReportPortalExtension.class)
public class UiTest {
    @RegisterExtension
    final ActivityScenarioExtension<LoginActivity> scenarioExtension = ActivityScenarioExtension.launch(LoginActivity.class);

    @Test
    public void test_short_password_error() {
        ActivityScenario<LoginActivity> scenario = scenarioExtension.getScenario();
        Espresso.onView(withId(R.id.username)).perform(ViewActions.typeText("Steve"));
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText("test"));
        Espresso.onView(withId(R.id.login)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.password)).check(ViewAssertions.matches(ViewMatchers.hasErrorText(Matchers.equalTo("Password must be >5 characters"))));
    }
}
