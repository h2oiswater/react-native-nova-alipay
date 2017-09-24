package cn.idealsense.reactnative.alipay;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gongjiangpeng on 05/09/2017.
 */

public final class AlipayPackage implements ReactPackage {
  @Override
  public List<NativeModule> createNativeModules(
      final ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();
    modules.add(new AlipayMoudle(reactContext));
    return modules;
  }

  // Deprecated RN 0.47
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(
      final ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
}
