package vn.edu.hcmus.ldolphin.views.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import vn.edu.hcmus.ldolphin.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public static class SettingFragment extends PreferenceFragment implements SettingView {
        private SettingPresenter mPresenter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_main);
            mPresenter = new SettingPresenterImpl(this);
            Preference useModernLayout = findPreference(getString(R.string.key_is_modern_layout));
            mPresenter.bindPreferenceSummaryToValue(useModernLayout);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return mPresenter.onPreferenceChange(preference, newValue);
        }
    }
}
