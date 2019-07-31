package com.hfad.databuddy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AppInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void bitCalculationTest() throws Exception {
        onView(withId(R.id.bit_text)).perform(clearText());
        onView(withId(R.id.bit_text)).perform(typeText("300"));
        onView(withId(R.id.byte_text)).check(matches(withText("37.5")));
        onView(withId(R.id.kilo_text)).check(matches(withText("0.0375")));
        onView(withId(R.id.mega_text)).check(matches(withText("3.75E-5")));
        onView(withId(R.id.giga_text)).check(matches(withText("3.75E-8")));
        onView(withId(R.id.tera_text)).check(matches(withText("3.75E-11")));
        onView(withId(R.id.peta_text)).check(matches(withText("3.75E-14")));

    }

    @Test
    public void byteCalculationTest() throws Exception {
        onView(withId(R.id.byte_text)).perform(clearText());
        onView(withId(R.id.byte_text)).perform(typeText("45"));
        onView(withId(R.id.bit_text)).check(matches(withText("360.0")));
        onView(withId(R.id.kilo_text)).check(matches(withText("0.045")));
        onView(withId(R.id.mega_text)).check(matches(withText("4.5E-5")));
        onView(withId(R.id.giga_text)).check(matches(withText("4.5E-8")));
        onView(withId(R.id.tera_text)).check(matches(withText("4.5E-11")));
        onView(withId(R.id.peta_text)).check(matches(withText("4.5E-14")));
    }

    @Test
    public void kiloCalculationTest() throws Exception {
        onView(withId(R.id.kilo_text)).perform(clearText());
        onView(withId(R.id.kilo_text)).perform(typeText("1054"));
        onView(withId(R.id.bit_text)).check(matches(withText("8432000.0")));
        onView(withId(R.id.byte_text)).check(matches(withText("1054000.0")));
        onView(withId(R.id.mega_text)).check(matches(withText("1.054")));
        onView(withId(R.id.giga_text)).check(matches(withText("0.001054")));
        onView(withId(R.id.tera_text)).check(matches(withText("1.054E-6")));
        onView(withId(R.id.peta_text)).check(matches(withText("1.054E-9")));
    }

    @Test
    public void megaCalculationTest() throws Exception {
        onView(withId(R.id.mega_text)).perform(clearText());
        onView(withId(R.id.mega_text)).perform(typeText("610"));
        onView(withId(R.id.bit_text)).check(matches(withText("4.88E9")));
        onView(withId(R.id.byte_text)).check(matches(withText("6.1E8")));
        onView(withId(R.id.kilo_text)).check(matches(withText("610000.0")));
        onView(withId(R.id.giga_text)).check(matches(withText("0.61")));
        onView(withId(R.id.tera_text)).check(matches(withText("6.1E-4")));
        onView(withId(R.id.peta_text)).check(matches(withText("6.1E-7")));
    }

    @Test
    public void gigaCalculationTest() throws Exception {
        onView(withId(R.id.giga_text)).perform(clearText());
        onView(withId(R.id.giga_text)).perform(typeText("23"));
        onView(withId(R.id.bit_text)).check(matches(withText("1.84E11")));
        onView(withId(R.id.byte_text)).check(matches(withText("2.3E10")));
        onView(withId(R.id.kilo_text)).check(matches(withText("2.3E7")));
        onView(withId(R.id.mega_text)).check(matches(withText("23000.0")));
        onView(withId(R.id.tera_text)).check(matches(withText("0.023")));
        onView(withId(R.id.peta_text)).check(matches(withText("2.3E-5")));
    }

    @Test
    public void teraCalculationTest() throws Exception {
        onView(withId(R.id.tera_text)).perform(clearText());
        onView(withId(R.id.tera_text)).perform(typeText("5"));
        onView(withId(R.id.bit_text)).check(matches(withText("4.0E13")));
        onView(withId(R.id.byte_text)).check(matches(withText("5.0E12")));
        onView(withId(R.id.kilo_text)).check(matches(withText("5.0E9")));
        onView(withId(R.id.mega_text)).check(matches(withText("5000000.0")));
        onView(withId(R.id.giga_text)).check(matches(withText("5000.0")));
        onView(withId(R.id.peta_text)).check(matches(withText("0.005")));
    }

    @Test
    public void petaCalculationTest() throws Exception {
        onView(withId(R.id.peta_text)).perform(clearText());
        onView(withId(R.id.peta_text)).perform(typeText("20"));
        onView(withId(R.id.bit_text)).check(matches(withText("1.6E17")));
        onView(withId(R.id.byte_text)).check(matches(withText("2.0E16")));
        onView(withId(R.id.kilo_text)).check(matches(withText("2.0E13")));
        onView(withId(R.id.mega_text)).check(matches(withText("2.0E10")));
        onView(withId(R.id.giga_text)).check(matches(withText("2.0E7")));
        onView(withId(R.id.tera_text)).check(matches(withText("20000.0")));
    }
}
