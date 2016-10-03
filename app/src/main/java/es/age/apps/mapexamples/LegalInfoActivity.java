/*
 * Copyright (C) 2012 The Android Open Source Project
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

package es.age.apps.mapexamples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Activity to show legal information.
 */
public class LegalInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_info);

        TextView legalInfoTextView = (TextView) findViewById(R.id.legal_info);
        String openSourceSoftwareLicenseInfo =
                GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this);
        if (openSourceSoftwareLicenseInfo != null) {
            legalInfoTextView.setText(openSourceSoftwareLicenseInfo);
        } else {
            legalInfoTextView.setText(R.string.error_device_not_supported);
        }
    }

}