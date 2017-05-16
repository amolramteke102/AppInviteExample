package com.encureit.appinviteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteReferral;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if (AppInviteReferral.hasReferral(intent)) {
            // Extract the information from the Intent
            String deepLink = AppInviteReferral.getDeepLink(intent);
            Log.d(getString(R.string.app_name),
                    "Found Referral: " + AppInviteReferral.getInvitationId(intent) + ":" + deepLink);

            String[] array = deepLink.split("/");

            if (array.length > 0) {
                TextView tv = (TextView) findViewById(R.id.discount);
                tv.setText(String.format(tv.getText().toString(), array[array.length-1]));
            }
        }
    }

}
