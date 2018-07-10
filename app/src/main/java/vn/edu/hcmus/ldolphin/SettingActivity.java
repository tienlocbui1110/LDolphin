package vn.edu.hcmus.ldolphin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_main);

            Preference useModernLayout = findPreference(getString(R.string.key_is_modern_layout));
            bindPreferenceSummaryToValue(useModernLayout);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (newValue instanceof Boolean) {
                CheckBoxPreference tmpPreference = (CheckBoxPreference) preference;
                tmpPreference.setChecked((Boolean) newValue);
            }

            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            Boolean preferencesBoolean = preferences.getBoolean(preference.getKey(), false);
            onPreferenceChange(preference, preferencesBoolean);
        }
    }
}
