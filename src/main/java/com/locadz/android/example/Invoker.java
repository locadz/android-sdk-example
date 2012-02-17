/*
 * Copyright (c) 2012. Blue Tang Studio LLC. All rights reserved.
 */

package com.locadz.android.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.adapters.AdWhirlAdapter;
import com.adwhirl.util.AdWhirlUtil;
import com.locadz.AdUnitLayout;

import java.util.Arrays;
import java.util.HashSet;

public class Invoker extends Activity {
    // For more easily detecting memory leaks.
    // byte[] garbage = new byte[1000 * 1024];

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_main);

        if (layout == null) {
            Log.e("AdWhirl", "Layout is null!");
            return;
        }

        // These are density-independent pixel units, as defined in
        // http://developer.android.com/guide/practices/screens_support.html
        int width = 320;
        int height = 52;

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;

        width = (int) (width * density);
        height = (int) (height * density);

        AdWhirlTargeting.setAge(23);
        AdWhirlTargeting.setGender(AdWhirlTargeting.Gender.MALE);
        String keywords[] = { "online", "games", "gaming" };
        AdWhirlTargeting
            .setKeywordSet(new HashSet<String>(Arrays.asList(keywords)));
        AdWhirlTargeting.setPostalCode("94123");
        AdWhirlTargeting.setTestMode(false);

        AdWhirlAdapter.setGoogleAdSenseAppName("AdWhirl Test App");
        AdWhirlAdapter.setGoogleAdSenseCompanyName("AdWhirl");

        // Optional, will fetch new config if necessary after five minutes.
        // AdWhirlManager.setConfigExpireTimeout(1000 * 60 * 5);

        // References AdWhirlLayout defined in the layout XML.


        // Instantiates AdWhirlLayout from code.
        // Note: Showing two ads on the same screen is for illustrative purposes
        // only.
        // You should check with ad networks on their specific policies.
        AdUnitLayout adWhirlLayout2 = new AdUnitLayout(this, "46a9e26bb1f5499ab7b00c9807ae034b");

        RelativeLayout.LayoutParams adWhirlLayoutParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adWhirlLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(adWhirlLayout2, adWhirlLayoutParams);

        TextView textView = new TextView(this);
        textView.setText("Below AdWhirlLayout from code");
        layout.addView(textView, adWhirlLayoutParams);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.invalidate();
    }

    public void adWhirlGeneric() {
        Log.e(AdWhirlUtil.ADWHIRL, "In adWhirlGeneric()");
    }
}
