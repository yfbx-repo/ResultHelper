# ResultHelper

从博客中学到的点，并进行实践：    
1.使用Fragment作为路由 进行权限请求 以及ActivityResult返回逻辑处理    
2.使用ContentProvider 获取Application 上下文    
3.使用LifeCycleCallback监听Activity生命周期，从而获得栈顶Activity    

思考：    
大部分情况下 startActivityForResult 或 申请权限都是在Activity中完成的，即使不是在Activity中(如:在MVP的Presenter中)也很容易获得
Activity引用，这样的话，就没有必要使用ContentProvider和LifeCycleCallback去获得Activity引用，但多传一个参数依然是不好的体验，因此
可以使用Kotlin扩展函数，在Activity中扩展这这两个方法，这样使用更简洁。

在Activity和Fragment中：

```

   //申请权限
   request(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
       if (it) {
           //TODO
       }
   }

   //Activity for Result
   startForResult(Intent()) { code, data ->
       //TODO
   }


```





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
