
# react-native-nova-alipay

React Native Module for alipay.com

iOS Module 为 react-native-yunpeng-alipay 的 iOS Module，测试使用没有问题，故暂时没有修改，当无法使用时，会即时更新。

在这里感谢yunpeng的iOSModule。

## changes
* ### 2017-09-05
替换Android依赖包为最新的alipaySdk-20170725.jar，支付方法修改为当前官网推荐的payV2方法。

## install

```
npm install react-native-nova-alipay --save
```

## Android

### With react-native link

```
react-native link
```

### Manually

* android/settings.gradle

```
include ':react-native-nova-alipay'
project(':react-native-nova-alipay').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-nova-alipay/android')
```

* android/app/build.gradle

```
dependencies {
    compile project(':react-native-nova-alipay')
}
```

* register module (in MainActivity.java)

```java
...

import cn.idealsense.reactnative.alipay.AlipayPackage; // <--- IMPORT

public class MainActivity extends ReactActivity {

    ...

    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new AlipayPackage() // <--- ADD HERE
        );
    }
}
```

## iOS

### With rnpm

```
rnpm link react-native-nova-alipay
```

### Manually

> Link `AlipayModule` library from your `node_modules/react-native-nova-alipay/ios` folder like its [described here](http://facebook.github.io/react-native/docs/linking-libraries-ios.html). Don't forget to add it to "Build Phases" of project.

### Config

* Added the following libraries to your "Link Binary With Libraries":
  * [x] CoreMotion.framework
  * [x] CoreTelephony.framework
  * [x] libc++
  * [x] libz

* add `URL Schema` as your app id for `URL type` in `Targets - info`
* Make sure you have these code in AppDelegate.m to enable callback

```objective_c
#import "AlipayModule.h"
```

```objective_c
- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url
  sourceApplication:(NSString *)sourceApplication annotation:(id)annotation
{
  [AlipayModule handleCallback:url];
  return YES;
}
```

### General Usage

```javascript
import Alipay from 'react-native-nova-alipay';

```

```javascript
Alipay.pay("signed pay info string").then(function(data){
                    console.log(data);
                }, function (err) {
                    console.log(err);
                });

```
