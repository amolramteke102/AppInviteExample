package com.encureit.appinviteexample;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AppInvitesActivity extends AppCompatActivity {
    ArrayList<Uri> arrayListapkFilepath; // define global
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_invites);

        startTest();



      /*  //put this code when you wants to share apk
        arrayListapkFilepath = new ArrayList<Uri>();

        shareAPK(getPackageName());
        // you can pass bundle id of installed app in your device instead of getPackageName()
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("application/vnd.android.package-archive");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,
                arrayListapkFilepath);
        startActivity(Intent.createChooser(intent, "Share " +
                arrayListapkFilepath.size() + " Files Via"));*/

    }

  /*  private void startTest() {
        // create an intent before calling another app
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,
                "App Invites");

        // check if another app is available
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> apps =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        boolean intentExists = apps.size() > 0;

        // call another app if it exists
        // otherwise, alert the user
        if (intentExists)
            startActivity(intent);
        else
            (new AlertDialog.Builder(AppInvitesActivity.this)
                    .setMessage("Intent '" + intent.toString()
                            + "' does not exist")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                }
                            }).create()).show();
    }
*/

    private void startTest() { //working code
        // specify our test image location
       // Uri url = Uri.parse("/data/apps/ "+getApplicationContext().getPackageName() + ".apk");

        PackageManager pm = getPackageManager();
        String uri = null;
        for (ApplicationInfo app : pm.getInstalledApplications(0)) {
            if(!((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1))
                if(!((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1)){
                    uri=app.sourceDir;
                    if(uri.contains("com.encureit.appinviteexample"))
                        break;
                }
        }


       // Uri url = Uri.parse(getApplication().getPackageCodePath());
        // set up an intent to share the image
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("application/*");
        share_intent.putExtra(Intent.EXTRA_STREAM,
                Uri.fromFile(new File(uri)));
        share_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share_intent.putExtra(Intent.EXTRA_SUBJECT,
                "Invite Onfees");
        share_intent.putExtra(Intent.EXTRA_TEXT,
                "Here is an onfees app to share with you");

        // start the intent
        try {
            startActivity(Intent.createChooser(share_intent,
                    "Share Via"));
        } catch (android.content.ActivityNotFoundException ex) {
            (new AlertDialog.Builder(AppInvitesActivity .this)
                    .setMessage("Share failed")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                }
                            }).create()).show();
        }
    }

  /*  public void shareAPK(String bundle_id) {
        File f1;
        File f2 = null;

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);
        int z = 0;
        for (Object object : pkgAppsList) {

            ResolveInfo info = (ResolveInfo) object;
            if (info.activityInfo.packageName.equals(bundle_id)) {

                f1 = new File(info.activityInfo.applicationInfo.publicSourceDir);

                Log.v("file--",
                        " " + f1.getName().toString() + "----" + info.loadLabel(getPackageManager()));
                try {

                    String file_name = info.loadLabel(getPackageManager()).toString();
                    Log.d("file_name--", " " + file_name);

                    f2 = new File(Environment.getExternalStorageDirectory().toString() + "/Folder");
                    f2.mkdirs();
                    f2 = new File(f2.getPath() + "/" + file_name + ".apk");
                    f2.createNewFile();

                    InputStream in = new FileInputStream(f1);

                    OutputStream out = new FileOutputStream(f2);

                    // byte[] buf = new byte[1024];
                    byte[] buf = new byte[4096];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();
                    System.out.println("File copied.");
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage() + " in the specified directory.");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        arrayListapkFilepath.add(Uri.fromFile(new File(f2.getAbsolutePath())));

    }*/




}
