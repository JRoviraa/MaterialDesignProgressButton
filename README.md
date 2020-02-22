# MaterialDesignProgressButton

[![](https://jitpack.io/v/jrovira037/MaterialDesignProgressButton.svg)](https://jitpack.io/#jrovira037/MaterialDesignProgressButton)
[![Known Vulnerabilities](https://snyk.io/test/github/JRoviraa/MaterialDesignProgressButton/badge.svg?targetFile=materialdesignprogressbutton/build.gradle)](https://snyk.io/test/github/JRoviraa/MaterialDesignProgressButton?targetFile=materialdesignprogressbutton/build.gradle)

This project was based on LoadingButton by koushikcse (https://github.com/koushikcse/LoadingButton)

The point of this project is to provide a MaterialButton that actually follows the loading guidelines (https://material.io/components/progress-indicators/) without losing `MaterialButton`'s benefits. The library just provides a class `MaterialDesignProgressButton` which extends MaterialButton and adds two methods: `showLoading()` and `hideLoading()`.


# Download


1. Add the JitPack repository to your build file 

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency

```groovy
dependencies {
  implementation 'com.github.jrovira037:MaterialDesignProgressButton:0.1.2'
}
```

# Usage

1. Replace your current MaterialButton tags from your .xml with MaterialDesignProgressButton: tag

```xml
<MaterialButton
  ...
/>
```

  

```xml
<com.jroviraa.android.materialdesignprogressbutton.MaterialDesignProgressButton
  ...
/>
```

2. Replace your `MaterialButton` objects with `MaterialDesignProgressButton` objects:

```kotlin
private lateinit var button: MaterialButton
```

```kotlin
private lateinit var button: MaterialDesignProgressButton
```

3. Use progress functions from your activity or fragment:
```kotlin
button.showLoading()
button.hideLoading()
```

_For an example check the app module of this project_
  


