# ResultHelper
[![](https://img.shields.io/badge/release-2.0.0-blue.svg)](https://github.com/yfbx-repo/ResultHelper/releases)   

use a Fragment as the router to handler the activity result and permission result

### 1. dependencies
```
dependencies {
    implementation 'com.github.yfbx:ResultHelper:2.0.0'
}
```    

### 2. sample 

```
startForResult(Intent()) { code, data ->
    if (code == Activity.RESULT_OK && data != null) {
       //todo
     }
}

request(Manifest.permission.WRITE_EXTERNAL_STORAGE) { isGrant ->
    if (isGrant) {
         //todo
    }
}
```
