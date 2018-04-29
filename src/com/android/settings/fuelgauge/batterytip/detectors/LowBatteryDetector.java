/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.settings.fuelgauge.batterytip.detectors;

import android.text.format.DateUtils;

import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.fuelgauge.batterytip.BatteryTipPolicy;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.fuelgauge.batterytip.tips.LowBatteryTip;

/**
 * Detect whether the battery is too low
 */
public class LowBatteryDetector implements BatteryTipDetector {
    private BatteryInfo mBatteryInfo;
    private BatteryTipPolicy mPolicy;

    public LowBatteryDetector(BatteryTipPolicy policy, BatteryInfo batteryInfo) {
        mPolicy = policy;
        mBatteryInfo = batteryInfo;
    }

    @Override
    public BatteryTip detect() {
        // Show it if battery life is less than mPolicy.lowBatteryHour
        final boolean isShown = mPolicy.lowBatteryEnabled && mBatteryInfo.discharging
                && mBatteryInfo.remainingTimeUs < mPolicy.lowBatteryHour * DateUtils.HOUR_IN_MILLIS;
        return new LowBatteryTip(
                isShown ? BatteryTip.StateType.NEW : BatteryTip.StateType.INVISIBLE);
    }
}