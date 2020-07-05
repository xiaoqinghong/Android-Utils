# Android-Utils  
[![](https://jitpack.io/v/xiaoqinghong/Android-Utils.svg)](https://jitpack.io/#xiaoqinghong/Android-Utils)
**Android开发中一些常用的工具类(Androidx & Kotlin)，各种工具类持续集成中**
### 使用方式
```
1.编辑项目build.gradle中
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

2.引入依赖
dependencies {
    implementation 'com.github.xiaoqinghong:Android-Utils:latest_version'
}
```
### Utils列表
- SharedPrefrenceManager 操作SharedPrefrence
- ArrayUtil 关于列表
- ClipUtil 剪切板管理，复制到剪切板、从剪切板读取等
- DataTransUtil 当页面传值数据了过大，使用object来页面传值
- DebugLog 调试信息
- ScreenUtil 布局截图、像素单位转换等
- StatusBarUtil 状态栏工具
- StringUtil 字符串操作工具
- ThreadExUtil 判断是否UI线程等
### 开源协议（MIT License）
