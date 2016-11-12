## [中文版点我 或 滑动到后面](#zh) ##

## BottomNavigationViewEx ##
An android lib for enhancing BottomNavigationView.

![MIT License](https://img.shields.io/github/license/mashape/apistatus.svg) ![api 15+](https://img.shields.io/badge/API-11%2B-green.svg)



## About Us ##
[![天宇工作室](https://github.com/ittianyu/MobileGuard/blob/master/read_me_images/logo-transparent.png?raw=true)](http://www.ittianyu.com)

## Feature ##

- ### setCurrentItem ###
	Set current checked item

- ### enableShiftingMode ###
	Open or close shifting mode for navigation bar

- ### enableItemShiftingMode ###
	Open or close shifting mode for menu item

- ### enableAnimation ###
	Open or close animation for menu item

- ### setTextVisibility ###
	Set the visibility of text

- ### setIconVisibility ###
	Set the visibility of icon


## Example ##

![](/read_me_images/normal.gif)

![](/read_me_images/no_animation.gif)

![](/read_me_images/no_shifting_mode.gif)

![](/read_me_images/no_item_shifting_mode.gif)

![](/read_me_images/no_text.gif)

![](/read_me_images/no_icon.gif)

![](/read_me_images/no_animation_shifting_mode.gif)

![](/read_me_images/no_animation_item_shifting_mode.gif)

![](/read_me_images/no_animation_shifting_mode_item_shifting_mode.gif)

![](/read_me_images/no_shifting_mode_item_shifting_mode_text.gif)

![](/read_me_images/no_animation_shifting_mode_item_shifting_mode_text.gif)

![](/read_me_images/no_shifting_mode_item_shifting_mode_and_icon.gif)

![](/read_me_images/no_item_shifting_mode_icon.gif)

![](/read_me_images/no_animation_shifting_mode_item_shifting_mode_icon.gif)

**With ViewPager**

![](/read_me_images/with_view_pager.gif)


## Adding to project ##

### Dependency ###
`compileSdkVersion` >= 25 and add `design` :

```
compile 'com.android.support:design:25.0.0'
```

### Importing to project ###

#### Example for Gradle: ####

Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Step 2. Add the dependency
```
compile 'com.github.ittianyu:BottomNavigationViewEx:1.0.0'
```

#### and for Maven: ####

Step 1. Add it in your root build.gradle at the end of repositories:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

Step 2. Add the dependency
```
<dependency>
    <groupId>com.github.ittianyu</groupId>
    <artifactId>BottomNavigationViewEx</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Manual: ####

Downloading [BottomNavigationViewEx.java](https://raw.githubusercontent.com/ittianyu/BottomNavigationViewEx/master/widget/src/main/java/com/ittianyu/bottomnavigationviewex/BottomNavigationViewEx.java) and copying it to you project。


## Getting started ##

Adding a custom widget in `xml` :

```xml
<com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
    android:id="@+id/bnve"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    app:itemBackground="@color/colorPrimary"
    app:itemIconTint="@color/item_text_color"
    app:itemTextColor="@color/item_text_color"
    app:menu="@menu/menu_navigation_with_view_pager" />
```

Binding view in `Activity` and setting style:

```java
BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);

bnve.enableAnimation(false);

bnve.enableShiftingMode(false);

bnve.enableItemShiftingMode(false);

bnve.setTextVisibility(false);

bnve.setIconVisibility(false);

bnve.setCurrentItem(1);
```

Other usage is the same as official `BottomNavigationView`.
You can [click here](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html) for detail.

## References ##

The lib is based on `BottomNavigationView` in `Support Library 25 design`.

I found it was inflexible when I try in demo. For example, I can't change the current checked item by code. So I write a class extends it to provide some useful method.

You no need to worry about stability. Because I minimise modifying by reflecting.


## License ##

	MIT License
	
	Copyright (c) 2016 ittianyu
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.




<br/>
<br/>
----------

<a id="zh"> </a>
## BottomNavigationViewEx ##
一个增强BottomNavigationView的安卓库。

![MIT License](https://img.shields.io/github/license/mashape/apistatus.svg) ![api 15+](https://img.shields.io/badge/API-11%2B-green.svg)

## 关于我们 ##
[![天宇工作室](https://github.com/ittianyu/MobileGuard/blob/master/read_me_images/logo-transparent.png?raw=true)](http://www.ittianyu.com)


## 功能 ##

- ### setCurrentItem ###
	设置当前选中的项

- ### enableShiftingMode ###
	开启或关闭导航条的位移模式

- ### enableItemShiftingMode ###
	开启或关闭子菜单的位移模式

- ### enableAnimation ###
	开启或关闭子菜单的文字和图片动画

- ### setTextVisibility ###
	设置子菜单文本可见性

- ### setIconVisibility ###
	设置子菜单图片可见性


## 例子 ##

![](/read_me_images/normal.gif)

![](/read_me_images/no_animation.gif)

![](/read_me_images/no_shifting_mode.gif)

![](/read_me_images/no_item_shifting_mode.gif)

![](/read_me_images/no_text.gif)

![](/read_me_images/no_icon.gif)

![](/read_me_images/no_animation_shifting_mode.gif)

![](/read_me_images/no_animation_item_shifting_mode.gif)

![](/read_me_images/no_animation_shifting_mode_item_shifting_mode.gif)

![](/read_me_images/no_shifting_mode_item_shifting_mode_text.gif)

![](/read_me_images/no_animation_shifting_mode_item_shifting_mode_text.gif)

![](/read_me_images/no_shifting_mode_item_shifting_mode_and_icon.gif)

![](/read_me_images/no_item_shifting_mode_icon.gif)

![](/read_me_images/no_animation_shifting_mode_item_shifting_mode_icon.gif)

**With ViewPager**

![](/read_me_images/with_view_pager.gif)


## 加入工程 ##

### 依赖 ###
`compileSdkVersion` >= 25 且添加 `design` 依赖包:

```
compile 'com.android.support:design:25.0.0'
```

### 导入本库 ###

#### Gradle例子: ####

步骤 1. 在工程根目录的 `build.gradle` 最后添加如下代码:
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

步骤 2. 添加依赖
```
compile 'com.github.ittianyu:BottomNavigationViewEx:1.0.0'
```

#### Maven例子: ####

步骤 1. 在工程根目录的 `build.gradle` 最后添加如下代码:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

步骤 2. 添加依赖
```
<dependency>
    <groupId>com.github.ittianyu</groupId>
    <artifactId>BottomNavigationViewEx</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 手动导入: ####

下载文件 [BottomNavigationViewEx.java](https://raw.githubusercontent.com/ittianyu/BottomNavigationViewEx/master/widget/src/main/java/com/ittianyu/bottomnavigationviewex/BottomNavigationViewEx.java) 并复制到你的工程中。


## 开始使用 ##

在 `xml` 布局中添加自定义控件:

```xml
<com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
    android:id="@+id/bnve"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    app:itemBackground="@color/colorPrimary"
    app:itemIconTint="@color/item_text_color"
    app:itemTextColor="@color/item_text_color"
    app:menu="@menu/menu_navigation_with_view_pager" />
```

在 `Activity` 中绑定控件，并设置样式:

```java
BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);

bnve.enableAnimation(false);

bnve.enableShiftingMode(false);

bnve.enableItemShiftingMode(false);

bnve.setTextVisibility(false);

bnve.setIconVisibility(false);

bnve.setCurrentItem(1);
```

其他用法和官方 `BottomNavigationView` 一样。
详情[点击这里](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)


## 来源 ##

本库修改自安卓官方 `Support Library 25 design` 中的 `BottomNavigationView`。

我在尝试使用官方的库时，发现缺少灵活性。比如官方并没有提供切换当前选中项的方法。所以我在此基础上包装了一层，对外公开了一些方法。

你完全没有必要担心库的稳定性，因为我是使用反射对父类进行最小限度的修改。


## 授权 ##

	MIT License
	
	Copyright (c) 2016 ittianyu
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
