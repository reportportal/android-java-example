package com.epam.test.android.espresso.junit5;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.properties.PropertiesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class AndroidReportPortalExtension extends ReportPortalExtension {
    @Override
    public ReportPortal getReporter() {
        Context ctx = InstrumentationRegistry.getInstrumentation().getContext();
        try {
            InputStream propertyFile = ctx.getAssets().open("reportportal.properties");
            Properties props = new Properties();
            props.load(new InputStreamReader(propertyFile, PropertiesLoader.STANDARD_CHARSET));
            PropertiesLoader loader = PropertiesLoader.load();
            loader.overrideWith(props);
            return ReportPortal.builder().withParameters(new ListenerParameters(loader)).build();
        } catch (IOException e) {
            throw new IllegalStateException("Asset read error", e);
        }
    }
}
