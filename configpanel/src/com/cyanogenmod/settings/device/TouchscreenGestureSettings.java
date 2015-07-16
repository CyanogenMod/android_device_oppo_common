/*
 * Copyright (C) 2015 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyanogenmod.settings.device;

import com.android.internal.util.cm.ScreenType;

import com.cyanogenmod.settings.device.utils.NodePreferenceActivity;

import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.Preference;
import android.preference.SwitchPreference;

public class TouchscreenGestureSettings extends NodePreferenceActivity {

    private static final String KEY_HAPTIC_FEEDBACK = "touchscreen_haptic_feedback";

    private static final String PROP_HAPTIC_FEEDBACK = "persist.gestures.haptic";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.touchscreen_panel);

        final SwitchPreference hapticFeedback =
                (SwitchPreference) findPreference(KEY_HAPTIC_FEEDBACK);
        hapticFeedback.setChecked(SystemProperties.getBoolean(PROP_HAPTIC_FEEDBACK, true));
        hapticFeedback.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        if (KEY_HAPTIC_FEEDBACK.equals(key)) {
            final boolean value = (Boolean) newValue;
            return true;
        }

        return super.onPreferenceChange(preference, newValue);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // If running on a phone, remove padding around the listview
        if (!ScreenType.isTablet(this)) {
            getListView().setPadding(0, 0, 0, 0);
        }
    }

}
