## BottomNavigationViewEx ##
一个增强BottomNavigationView的安卓库。

![MIT License](https://img.shields.io/github/license/mashape/apistatus.svg) ![api 9+](https://img.shields.io/badge/API-9%2B-green.svg)


## 紧急求助 ##

各位大佬好，我是18届毕业生，因家里变故，无力还债，不得不厚着脸皮来求助。（本库已创建2年，目前2.3k star，期间从未要过打赏，但这一次我真的走投无路了）

2019-02-15 晚上还在加班，突然接到家里电话，跟我说我爸欠了几十万，在我详细追问下，大概欠了银行三十多万，欠了个人十几万，再找别人借钱解燃眉之急明显是不可能的。欠债的原因是创业投资失败，跟去年"毛衣战"有关，强依赖出口的实体行业。虽然我也怀疑有可能是借钱炒股/菠菜导致的负债，但我选择相信我爸。

目前银行的债分别是多张信用卡和创业贷款，分了6期和12期，还有24期的。
从账单来看，大概一个月要还3w，然而我工资也就应届生中等水平，远远负担不起，幸好大学时接外包和工作半年存了一些钱，但也只够撑几个月的，现在外面各种人员优化，我第一次对失业感到害怕。我曾经以为，只要省吃俭用不负债，还是可以熬过这个冬天，现在看来我大概是熬不过去了。

迫于绝望，来此求助，希望好心人给捐点钱，付款时备注昵称和联系方式(比如邮箱)，我会在当天晚上下班后，把捐赠者信息和金额更新到 <a href="#donate">本页面</a>。无以为报，后面我打算写一套项目开发实战教程(包括前后端和app)，具体项目选取可以有各位大佬决定，预计今年内完成后给各位大佬免费推送到联系方式。

paypal: admin@ittianyu.com

![](/read_me_images/donate_alipay.png) ![](/read_me_images/donate_wechat.png)


另外前段时间看到有人转让 git 仓库，如果有大佬看上这个仓库，还请联系。

![](/read_me_images/help.jpg)

## 功能 ##

|methods|description
|---|---|
|enableAnimation|开启或关闭点击动画(文字放大效果和图片移动效果)。 默认为 true.
|enableItemShiftingMode|开始或关闭子菜单位移模式。 如果为 true，除了当前选中项，其他项的文本将会隐藏。 当菜单数大于3时，默认为 true。
|enableShiftingMode|开始或关闭导航条位移模式。如果为 true，选中项和其他项的宽度不一样。当菜单数大于3时，默认为 true。
|getBottomNavigationItemView|获取位于 position 的私有成员变量 mButton。
|getBottomNavigationItemViews|获取私有成员变量 mButtons。
|getCurrentItem|获取当前选中项的索引。
|getIconAt|获取位于 position 的图片。
|getItemCount|获取子项个数。
|getItemHeight|获取菜单高度。
|getLargeLabelAt|获取位于 position 的大标签. 每个子项包含两个标签，一个大的，一个小的。
|getSmallLabelAt|获取位于 position 的小标签. 每个子项包含两个标签，一个大的，一个小的。
|getMenuItemPosition|获取子菜单的索引。如果找不到，返回 -1。
|getOnNavigationItemSelectedListener|获取 OnNavigationItemSelectedListener。
|setCurrentItem|设置当前选中项。
|setIconMarginTop|设置 icon 的 MarginTop，用于调节图标垂直位置。
|setIconSize|设置所有的子项图标大小。
|setIconSizeAt|设置位于 position 的图标的大小。
|setIconsMarginTop|设置所有 icon 的 MarginTop，用于调节图标垂直位置。
|setIconTintList| 设置图片的渲染颜色列表(Selector)
|setIconVisibility|设置图片可见性。
|setItemBackground| 设置子项的背景。
|setItemHeight|设置子项高度。
|setLargeTextSize|设置所有子项的大标签文本大小。每个子项有两个标签，一个大的，一个小的。当子项未选中时，显示小标签；选中时，显示大标签。
|setSmallTextSize|设置所有子项的小标签文本大小。每个子项有两个标签，一个大的，一个小的。当子项未选中时，显示小标签；选中时，显示大标签。
|setTextSize|设置所有子项的大和小标签文本大小。
|setTextTintList|设置子项 TextView 的颜色。
|setTextVisibility|设置文本可见性。
|setTypeface|设置所有子项的 TextView 字体
|setupWithViewPager|和 ViewPager 绑定，当 任何一个选中项改变时，都会自动改变另一项。


## 例子 ##

**样式**

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

**注意：这个 style 在安卓 4.x 上有 bug**

![](/read_me_images/with_padding.jpg)

![](/read_me_images/center_icon_only.jpg)

![](/read_me_images/smaller_text.jpg)

![](/read_me_images/bigger_icon.jpg)

![](/read_me_images/custom_typeface.jpg)

![](/read_me_images/icon_selector_1.jpg) ![](/read_me_images/icon_selector_2.jpg)

![](/read_me_images/icon_margin_top.jpg)

![](/read_me_images/unchecked_first_time.jpg)

**和 ViewPager 一起使用**

![](/read_me_images/with_view_pager.gif)

**带数字的小红圈**

![](/read_me_images/view_badger.gif)


**中间悬浮按钮**

![](/read_me_images/center_fab.jpg)


## 加入工程 ##

### Sdk 版本 ###
`compileSdkVersion` >= 25

### 导入本库(选择其中一种) ###

#### Gradle例子: ####

步骤 1. 在工程根目录的 `build.gradle` 最后添加如下代码:
```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
		maven { url "https://maven.google.com" }
	}
}
```

步骤 2. 添加依赖

1. 旧版本 support lib 25 或 26
```groovy
compile 'com.github.ittianyu:BottomNavigationViewEx:1.2.4'
compile "com.android.support:design:26.+"
```
2. 新版本 support lib = 28
```groovy
implementation 'com.github.ittianyu:BottomNavigationViewEx:2.0.2'
implementation "com.android.support:design:28.0.0"
```
3. AndroidX
使用新版本，然后在 gradle.properties 中添加如下配置
```
android.useAndroidX=true
android.enableJetifier=true
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
    android:background="@color/colorPrimary"
    app:itemIconTint="@color/selector_item_color"
    app:itemTextColor="@color/selector_item_color"
    app:menu="@menu/menu_navigation_with_view_pager" />
```

在 `Activity` 中绑定控件:
```java
BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
```

#### 禁止所有动画效果 ####
```java
bnve.enableAnimation(false);
bnve.enableShiftingMode(false);
bnve.enableItemShiftingMode(false);
```

#### 自定义图标和文本大小 ####
```java
bnve.setIconSize(widthDp, heightDp);
bnve.setTextSize(sp);
```

#### 和 ViewPager 绑定####
```java
// set adapter
adapter = new VpAdapter(getSupportFragmentManager(), fragments);
bind.vp.setAdapter(adapter);

// binding with ViewPager
bind.bnve.setupWithViewPager(bind.vp);
```


#### 添加带数字的小红点 ####

1. Gradle 中加入 badge 库的依赖
	```
	compile 'q.rorbin:badgeview:1.1.0'
	```
2. 和底部控件绑定
	```
    // add badge
    addBadgeAt(2, 1);

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bind.bnve.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
                    }
                });
    }
	```

#### 其他 BottomNavigationViewEx 的用法 ####
请参考demo。

#### 其他 BottomNavigationView 的用法 ####
其他用法和官方 `BottomNavigationView` 一样。
详情[点击这里](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)


## 混淆 ##

如果你启用了 ProGuard，那你应该加上以下混淆代码:

#### 非 Android X 版本 ####
```
-keep public class android.support.design.widget.BottomNavigationView { *; }
-keep public class android.support.design.internal.BottomNavigationMenuView { *; }
-keep public class android.support.design.internal.BottomNavigationPresenter { *; }
-keep public class android.support.design.internal.BottomNavigationItemView { *; }
```

#### Android X 版本 ####
```
-keep public class com.google.android.material.bottomnavigation.BottomNavigationView { *; }
-keep public class com.google.android.material.bottomnavigation.BottomNavigationMenuView { *; }
-keep public class com.google.android.material.bottomnavigation.BottomNavigationPresenter { *; }
-keep public class com.google.android.material.bottomnavigation.BottomNavigationItemView { *; }
```

## 来源 ##

本库修改自安卓官方 `Support Library 25 design` 中的 `BottomNavigationView`。

我在尝试使用官方的库时，发现缺少灵活性。比如官方并没有提供切换当前选中项的方法。所以我在此基础上包装了一层，对外公开了一些方法。

你完全没有必要担心库的稳定性，因为我是使用反射对父类进行最小限度的修改。

## 致谢 ##

感谢 [Adrián Mouly](https://github.com/amouly) | [liaolintao](https://github.com/liaolintao) | [Luong Vo](https://github.com/luongvo).


## 授权 ##

	MIT License
	
	Copyright (c) 2017 ittianyu
	
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


## <a name="donate">捐赠者名单</a> ##

| 捐赠者 | 金额 |
|-------|------|
| xxx xxxxxx@xx.xx | xx元 |
