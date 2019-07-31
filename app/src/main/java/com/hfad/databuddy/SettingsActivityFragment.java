package com.hfad.databuddy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Set;

public class SettingsActivityFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Set<String> dataTypes = sharedPreferences.getStringSet("dataSelection", null);
        if(dataTypes == null)
            return;
        if(dataTypes.size() == 0) {
            Toast.makeText(getContext(),
                    "Please select at least one data type. Defaulting to Bits.",
                    Toast.LENGTH_LONG).show();
            String defaultDataType = "Bits";
            dataTypes.add(defaultDataType);
            sharedPreferences.edit().putStringSet("dataSelection", dataTypes).apply();
            MultiSelectListPreference pref = (MultiSelectListPreference)
                    findPreference("dataSelection");
            pref.setValues(dataTypes);
        }
    }
}
