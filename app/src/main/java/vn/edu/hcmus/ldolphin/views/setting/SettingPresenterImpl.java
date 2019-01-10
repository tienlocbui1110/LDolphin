package vn.edu.hcmus.ldolphin.views.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class SettingPresenterImpl implements SettingPresenter {
    private SettingView mView;
    private Context mContext;

    public SettingPresenterImpl(SettingView view) {
        setView(view);
    }

    @Override
    public void setView(SettingView view) {
        mView = view;
        mContext = mView.getContext();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (newValue instanceof Boolean) {
            CheckBoxPreference tmpPreference = (CheckBoxPreference) preference;
            tmpPreference.setChecked((Boolean) newValue);
        }
        return true;
    }

    @Override
    public void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(mView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        Boolean preferencesBoolean = preferences.getBoolean(preference.getKey(), false);
        onPreferenceChange(preference, preferencesBoolean);
    }
}
