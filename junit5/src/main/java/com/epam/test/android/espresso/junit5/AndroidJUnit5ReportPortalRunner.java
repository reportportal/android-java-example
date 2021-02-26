package com.epam.test.android.espresso.junit5;

import android.os.Bundle;

import androidx.test.runner.AndroidJUnitRunner;

public class AndroidJUnit5ReportPortalRunner extends AndroidJUnitRunner {

    @Override
    public void onCreate(Bundle arguments) {

        arguments.putString("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder");
        arguments.putString("listener", "com.epam.test.android.espresso.junit5.AndroidReportPortalRunListener");
        super.onCreate(arguments);
    }
}
