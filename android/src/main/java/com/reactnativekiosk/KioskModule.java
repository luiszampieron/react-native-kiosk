package com.reactnativekiosk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;

import android.app.ActivityManager;
import android.content.Context;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.IllegalViewOperationException;


@ReactModule(name = KioskModule.NAME)
public class KioskModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Kiosk";

  public KioskModule(ReactApplicationContext reactContext) {
      super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
      return NAME;
  }

  @ReactMethod
  public void fullscreen(Promise promise) {
    try {
      UiThreadUtil.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          Immersive.fullscreen(getCurrentActivity().getWindow());
        }
      });
    } catch (IllegalViewOperationException e) {
      WritableMap map = Arguments.createMap();
      map.putBoolean("success", false);
      promise.reject("error", e);
    }
  }

  @ReactMethod
  public void exitFullscreen(Promise promise) {
    try {
      UiThreadUtil.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          Immersive.exitFullscreen(getCurrentActivity().getWindow());
        }
      });
    } catch (IllegalViewOperationException e) {
      WritableMap map = Arguments.createMap();
      map.putBoolean("success", false);
      promise.reject("error", e);
    }
  }

  @ReactMethod
  public void moveToFront() {
    Immersive.moveToFront(getCurrentActivity().getTaskId(), (ActivityManager) getReactApplicationContext().getSystemService(Context.ACTIVITY_SERVICE));
  }

}
