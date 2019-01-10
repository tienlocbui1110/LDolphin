package vn.edu.hcmus.ldolphin.views.setting;

import android.preference.Preference;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface SettingPresenter extends Presenter<SettingView> {
    boolean onPreferenceChange(Preference preference, Object newValue);

    void bindPreferenceSummaryToValue(Preference preference);
}
