package vn.edu.hcmus.ldolphin.application;

import android.app.Application;

import vn.edu.hcmus.ldolphin.data.ThemeConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: Get theme from config
        ThemeConfig.setTheme(ThemeConfig.DEFAULT, this);
    }
}
