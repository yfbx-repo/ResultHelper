# ResultHelper
[![](https://img.shields.io/badge/release-2.0.0-blue.svg)](https://github.com/yfbx-repo/ResultHelper/releases)   

用两种不同的方式实现 请求权限 和 startActivityForResult 的结果回调

#### 添加依赖
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
**方式一**  用一个空的Fragment作为页面路由，处理权限请求和onActivityResult结果    

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

**方式二**  基于以下两个官方库实现的扩展方法  
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