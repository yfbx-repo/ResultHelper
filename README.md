# ResultHelper
[![](https://img.shields.io/badge/release-2.0.0-blue.svg)](https://github.com/yfbx-repo/ResultHelper/releases)   

Two ways to start activity for result and request permissions

#### Add the dependency
```
repositories {
	maven { url 'https://jitpack.io' }
}
```

```
dependencies {
	implementation 'com.github.yfbx:ResultHelper:2.0.0'
}
```    
**1. To use a Fragment as the router to handler the activity result and permission result**    

```
request(Manifest.permission.WRITE_EXTERNAL_STORAGE) { isGrant ->
    if (isGrant) {
         //todo
    }
}

startForResult(Intent()) { code, data ->
    if (code == Activity.RESULT_OK && data != null) {
       //todo
     }
}

```

**2. To Extends androidx ktx repo**     
```
'androidx.activity:activity-ktx:1.2.0-alpha05'
'androidx.fragment:fragment-ktx:1.3.0-alpha05'
```

```
launchFor<TestActivity>(params) {
    //todo
}

getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
    //todo
}
```