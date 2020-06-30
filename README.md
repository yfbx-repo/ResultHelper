# ResultHelper
[![](https://img.shields.io/badge/release-2.0.1-ktx-blue.svg)](https://github.com/yfbx-repo/ResultHelper/releases)   

extensions to start activity for result and request permissions,besed on:
```
'androidx.activity:activity-ktx:1.2.0-alpha06'
'androidx.fragment:fragment-ktx:1.3.0-alpha06'
```

### 1. dependencies

```
dependencies {
    implementation 'com.github.yfbx:ResultHelper:2.0.1-ktx'
}
```    

### 2. sample

```
launchFor<TestActivity>(params) {
    //todo
}

permitFor(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
    //todo
}
```
