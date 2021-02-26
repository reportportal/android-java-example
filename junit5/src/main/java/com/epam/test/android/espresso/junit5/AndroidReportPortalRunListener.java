package com.epam.test.android.espresso.junit5;

import com.epam.reportportal.junit5.ReportPortalExtension;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AndroidReportPortalRunListener extends RunListener {

    private static final Queue<AndroidReportPortalExtension> EXTENSIONS = new ConcurrentLinkedQueue<>();

    @Override
    public void testRunFinished(Result result) {
        EXTENSIONS.iterator().forEachRemaining(ReportPortalExtension::finish);
    }

    public static void addLaunchFinisher(AndroidReportPortalExtension extension) {
        EXTENSIONS.add(extension);
    }
}
