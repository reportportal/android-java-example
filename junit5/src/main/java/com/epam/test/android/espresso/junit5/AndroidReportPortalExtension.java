package com.epam.test.android.espresso.junit5;

import com.epam.reportportal.junit5.ReportPortalExtension;

public class AndroidReportPortalExtension extends ReportPortalExtension {

    public AndroidReportPortalExtension() {
        super();
        AndroidReportPortalRunListener.addLaunchFinisher(this);
    }
}
