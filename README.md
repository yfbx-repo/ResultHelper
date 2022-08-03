# [Deprecated] ResultHelper
[![License](https://img.shields.io/badge/license-GPL%203.0-4EB1BA.svg)](https://fossa.com/blog/open-source-software-licenses-101-gpl-v3)
[![](https://img.shields.io/badge/release-2.0.2-blue.svg)](https://github.com/yfbx-repo/ResultHelper/releases)

use a Fragment as the router to handler the activity result and permission result

### dependencies
```
dependencies {
    implementation 'com.github.yfbx-repo:ResultHelper:2.0.2'
}
```    

### sample

- start activity for result
```
startForResult(Intent()) { code, data ->
    if (code == Activity.RESULT_OK && data != null) {
       //todo
     }
}
```

- request permission
```
request(Manifest.permission.WRITE_EXTERNAL_STORAGE) { isGrant ->
    if (isGrant) {
         //todo
    }
}
```
