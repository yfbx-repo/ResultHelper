# ResultHelper

使用Fragment作为路由 进行权限请求 以及ActivityResult返回逻辑处理.  
使用Kotlin扩展函数,在frrament 和 FragmentActivity中扩展了startForResult()方法和request(permission)方法

#### Add the dependency
```
repositories {
	maven { url 'https://jitpack.io' }
}
```

```
dependencies {
	implementation 'com.github.Edward-yfbx:ResultHelper:1.0.0'
}
```

#### Request Permissions
```
   ResultHelper.request(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
      if(it){
          //TODO Permission Granted
      }else{

      }

   }

```

#### startActivityForResult
```
  ResultHelper.startActivity(Intent()) { resultCode, data ->
       //TODO Activity Result
  }
```
