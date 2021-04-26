package com.epam.test.android.espresso.junit5;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.screenshot.ScreenCapture;
import androidx.test.runner.screenshot.Screenshot;

import com.epam.test.R;
import com.epam.test.ui.login.LoginActivity;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import de.mannodermaus.junit5.ActivityScenarioExtension;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UiTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UiTest.class);

    @RegisterExtension
    final ActivityScenarioExtension<LoginActivity> scenarioExtension = ActivityScenarioExtension.launch(LoginActivity.class);

    @Test
    public void test_short_password_error() {
        ActivityScenario<LoginActivity> scenario = scenarioExtension.getScenario();
        LOGGER.info("Starting UI test");
        Espresso.onView(withId(R.id.username)).perform(ViewActions.typeText("Steve"));
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText("test"));
        Espresso.onView(withId(R.id.login)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.password)).check(ViewAssertions.matches(ViewMatchers.hasErrorText(Matchers.equalTo("Password must be >5 characters"))));
        LOGGER.info("UI test finished");

        // Take a screenshot
        ScreenCapture capture = Screenshot.capture();
        ByteArrayOutputStream screenshotBytes = new ByteArrayOutputStream();
        capture.getBitmap().compress(capture.getFormat(), 100, screenshotBytes);
        LOGGER.info(
                "RP_MESSAGE#BASE64#{}#{}",
                Base64.getEncoder().encodeToString(screenshotBytes.toByteArray()),
                "Screenshot"
        );
    }
}
