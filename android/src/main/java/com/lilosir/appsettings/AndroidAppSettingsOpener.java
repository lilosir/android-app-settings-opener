package com.lilosir.appsettings;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.net.Uri;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class AndroidAppSettingsOpener extends ReactContextBaseJavaModule {

  @Override
  public String getName() {
    /**
     * return the string name of the NativeModule which represents this class in JavaScript
     * In JS access this module through React.NativeModules.OpenSettings
     */
    return "AndroidAppSettingsOpener";
  }

  @ReactMethod
  public void open(Callback cb) {
    Activity currentActivity = getCurrentActivity();

    if (currentActivity == null) {
      cb.invoke(false);
      return;
    }

    try {

      Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
      Uri uri = Uri.fromParts("package", currentActivity.getPackageName(), null);
      intent.setData(uri);
      currentActivity.startActivity(intent);

      cb.invoke(true);
    } catch (Exception e) {
      cb.invoke(e.getMessage());
    }
  }

  /* constructor */
  public AndroidAppSettingsOpener(ReactApplicationContext reactContext) {
    super(reactContext);
  }
}