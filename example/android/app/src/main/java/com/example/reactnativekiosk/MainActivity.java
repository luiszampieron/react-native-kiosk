package com.example.reactnativekiosk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.reactnativekiosk.Immersive;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {
  @Override
  protected String getMainComponentName() {
    return "KioskExample";
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(null);
  }

  @Override
  public void onBackPressed() {
    if( !Immersive.getActive() ) super.onBackPressed();
    else Immersive.fullscreen(getWindow());
  }

  @Override
  protected void onPause() {
    super.onPause();
    if(Immersive.getActive()) Immersive.moveToFront(getTaskId(), (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
  }

  @Override
  protected void onResume() {
    super.onResume();
    if(Immersive.getActive()) Immersive.fullscreen(getWindow());
  }

  @Override
  protected void onUserLeaveHint() {
    super.onUserLeaveHint();
    if(Immersive.getActive()) {
      Immersive.moveToFront(getTaskId(), (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
      Immersive.fullscreen(getWindow());
    }
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if(Immersive.getActive()) Immersive.fullscreen(getWindow());
    if (!hasFocus) {
      Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
      sendBroadcast(closeDialog);
    }
  }

}
