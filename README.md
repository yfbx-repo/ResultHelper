# ResultHelper

To request permission and startActivityForResult with a Fragment without UI

#### Add the dependency
```
repositories {
	maven { url 'https://jitpack.io' }
}
```

```
dependencies {
	implementation 'com.github.yfbx:ResultHelper:1.0.1'
}
```

#### Request Permissions
```
   request(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
       if (it) {
           //todo
       }
   }

```

#### startActivityForResult
```
  startForResult(Intent()) { code, data ->
       if (code == Activity.RESULT_OK && data != null) {
           //todo
       }
  }
```
