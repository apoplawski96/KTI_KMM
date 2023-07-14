package com.apoplawski96.killtheinterview.feature.questions.dependency.data

import com.example.myapplication.questions.model.DeprecatedCategory
import com.example.myapplication.questions.model.Question

val questionsAndroidFramework = listOf(
    Question(
        category = DeprecatedCategory.Android,
        question = "Principle of least privilege in Android system",
        answer = """
            Each app, by default, has access only to the components that it requires to do its work and no more.
            This creates a very secure environment in which an app cannot access parts of the system for which it is not given permission. However, there are ways for an app to share data with other apps and for an app to access system services:

            It's possible to arrange for two apps to share the same Linux user ID, in which case they are able to access each other's files. To conserve system resources, apps with the same user ID can also arrange to run in the same Linux process and share the same VM. The apps must also be signed with the same certificate.
            An app can request permission to access device data such as the device's location, camera, and Bluetooth connection. The user has to explicitly grant these permissions. For more information, see Working with System Permissions.
        """.trimIndent()
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            What languages can android apps be written in?
        """.trimIndent(),
        answer = """ 
            Android apps can be written using Kotlin, Java, and C++ languages. 
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            What is .apk file?
        """.trimIndent(),
        answer = """ 
            An Android package, which is an archive file with an .apk suffix, contains the contents of an Android app that are required at runtime and it is the file that Android-powered devices use to install the app.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            What is .aab file?
        """.trimIndent(),
        answer = """ 
            An Android App Bundle, which is an archive file with an .aab suffix, contains the contents of an Android app project including some additional metadata that is not required at runtime. An AAB is a publishing format and is not installable on Android devices, it defers APK generation and signing to a later stage. When distributing your app through Google Play for example, Google Play's servers generate optimized APKs that contain only the resources and code that are required by a particular device that is requesting installation of the app.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Describe Android operating system
        """.trimIndent(),
        answer = """ 
            The Android operating system is a multi-user Linux system in which each app is a different user.
            By default, the system assigns each app a unique Linux user ID (the ID is used only by the system and is unknown to the app). The system sets permissions for all the files in an app so that only the user ID assigned to that app can access them.
            Each process has its own virtual machine (VM), so an app's code runs in isolation from other apps.
            By default, every app runs in its own Linux process. The Android system starts the process when any of the app's components need to be executed, and then shuts down the process when it's no longer needed or when the system must recover memory for other apps.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Describe manifest file
        """.trimIndent(),
        answer = """ 
            Before the Android system can start an app component, the system must know that the component exists by reading the app's manifest file, AndroidManifest.xml. Your app must declare all its components in this file, which must be at the root of the app project directory.

            The manifest does a number of things in addition to declaring the app's components, such as the following:

            Identifies any user permissions the app requires, such as Internet access or read-access to the user's contacts.
            Declares the minimum API Level required by the app, based on which APIs the app uses.
            Declares hardware and software features used or required by the app, such as a camera, bluetooth services, or a multitouch screen.
            Declares API libraries the app needs to be linked against (other than the Android framework APIs), such as the Google Maps library.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Declaring application components in manifest
        """.trimIndent(),
        answer = """ 
            The primary task of the manifest is to inform the system about the app's components. 
            Activities, services, and content providers that you include in your source but do not declare in the manifest are not visible to the system and, consequently, can never run. However, broadcast receivers can be either declared in the manifest or created dynamically in code as BroadcastReceiver objects and registered with the system by calling registerReceiver().
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Which intent should start services?
        """.trimIndent(),
        answer = """ 
            Caution: If you use an intent to start a Service, ensure that your app is secure by using an explicit intent. Using an implicit intent to start a service is a security hazard because you cannot be certain what service will respond to the intent, and the user cannot see which service starts. Beginning with Android 5.0 (API level 21), the system throws an exception if you call bindService() with an implicit intent. Do not declare intent filters for your services.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Declaring app requirements
        """.trimIndent(),
        answer = """ 
            There are a variety of devices powered by Android and not all of them provide the same features and capabilities. To prevent your app from being installed on devices that lack features needed by your app, it's important that you clearly define a profile for the types of devices your app supports by declaring device and software requirements in your manifest file. Most of these declarations are informational only and the system does not read them, but external services such as Google Play do read them in order to provide filtering for users when they search for apps from their device.

            For example, if your app requires a camera and uses APIs introduced in Android 8.0 (API Level 26), you must declare these requirements.

            The values for minSdkVersion and targetSdkVersion are set in your app module's build.gradle file. 
            
            Note: Don't set minSdkVersion and targetSdkVersion directly in the manifest file, since they will be overwritten by Gradle during the build process. For more information, see Specify API level requirements.
            
            Declare the camera feature directly in your app's manifest file.
            With the declarations shown in these examples, devices that do not have a camera or have an Android version lower than 8.0 cannot install your app from Google Play. However, you can declare that your app uses the camera, but does not require it. In that case, your app must set the required attribute to false and check at runtime whether the device has a camera and disable any camera features as appropriate.
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Describe app resources in Android
        """.trimIndent(),
        answer = """ 
            Resources are the additional files and static content that your code uses, such as bitmaps, layout definitions, user interface strings, animation instructions, and more.

            You should always externalize app resources such as images and strings from your code, so that you can maintain them independently. You should also provide alternative resources for specific device configurations, by grouping them in specially-named resource directories. At runtime, Android uses the appropriate resource based on the current configuration. For example, you might want to provide a different UI layout depending on the screen size or different strings depending on the language setting.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            App Manifest Overview
        """.trimIndent(),
        answer = """ 
            Every app project must have an AndroidManifest.xml file (with precisely that name) at the root of the project source set. The manifest file describes essential information about your app to the Android build tools, the Android operating system, and Google Play.

            Among many other things, the manifest file is required to declare the following:

            The components of the app, which include all activities, services, broadcast receivers, and content providers. Each component must define basic properties such as the name of its Kotlin or Java class. It can also declare capabilities such as which device configurations it can handle, and intent filters that describe how the component can be started. Read more about app components.
            The permissions that the app needs in order to access protected parts of the system or other apps. It also declares any permissions that other apps must have if they want to access content from this app. Read more about permissions.
            The hardware and software features the app requires, which affects which devices can install the app from Google Play. Read more about device compatibility.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Intent filters
        """.trimIndent(),
        answer = """ 
            App activities, services, and broadcast receivers are activated by intents. An intent is a message defined by an Intent object that describes an action to perform, including the data to be acted upon, the category of component that should perform the action, and other instructions.

            When an app issues an intent to the system, the system locates an app component that can handle the intent based on intent filter declarations in each app's manifest file. The system launches an instance of the matching component and passes the Intent object to that component. If more than one app can handle the intent, then the user can select which app to use.

            An app component can have any number of intent filters (defined with the <intent-filter> element), each one describing a different capability of that component.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Manifest Permissions
        """.trimIndent(),
        answer = """ 
            Android apps must request permission to access sensitive user data (such as contacts and SMS) or certain system features (such as the camera and internet access). Each permission is identified by a unique label.
            Beginning with Android 6.0 (API level 23), the user can approve or reject some app permisions at runtime. But no matter which Android version your app supports, you must declare all permission requests with a <uses-permission> element in the manifest. If the permission is granted, the app is able to use the protected features. If not, its attempts to access those features fail.

            Your app can also protect its own components with permissions. It can use any of the permissions that are defined by Android, as listed in android.Manifest.permission, or a permission that's declared in another app. Your app can also define its own permissions. A new permission is declared with the <permission> element.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Manifest device compatibility
        """.trimIndent(),
        answer = """ 
            The manifest file is also where you can declare what types of hardware or software features your app requires, and thus, which types of devices your app is compatible with. Google Play Store does not allow your app to be installed on devices that don't provide the features or system version that your app requires.

            There are several manifest tags that define which devices your app is compatible with. The following are just a couple of the most common tags.:
            <uses-feature>, <uses-sdk>, <uses-native-library>, <uses-library>
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Should minSdkVersion and targetSdkVersion be included in app manifest?
        """.trimIndent(),
        answer = """ 
            No, as they might be overriden by the values in build.gradle file
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Device compatibility overview 
        """.trimIndent(),
        answer = """ 
            Android is designed to run on many different types of devices, from phones to tablets and televisions. As a developer, the range of devices provides a huge potential audience for your app. In order for your app to be successful on all these devices, it should tolerate some feature variability and provide a flexible user interface that adapts to different screen configurations.

            To facilitate your effort toward that goal, Android provides a dynamic app framework in which you can provide configuration-specific app resources in static files (such as different XML layouts for different screen sizes). Android then loads the appropriate resources based on the current device configuration. So with some forethought to your app design and some additional app resources, you can publish a single application package (APK) that provides an optimized user experience on a variety of devices.

            If necessary, however, you can specify your app's feature requirements and control which types of devices can install your app from Google Play Store. This page explains how you can control which devices have access to your apps, and how to prepare your apps to make sure they reach the right audience.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Android device compatibility
        """.trimIndent(),
        answer = """ 
            As you read more about Android development, you'll probably encounter the term "compatibility" in various situations. There are two types of compatibility: device compatibility and app compatibility.

            Because Android is an open source project, any hardware manufacturer can build a device that runs the Android operating system. Yet, a device is "Android compatible" only if it can correctly run apps written for the Android execution environment. The exact details of the Android execution environment are defined by the Android compatibility program and each device must pass the Compatibility Test Suite (CTS) in order to be considered compatible.
            As an app developer, you don't need to worry about whether a device is Android compatible, because only devices that are Android compatible include Google Play Store. So you can rest assured that users who install your app from Google Play Store are using an Android compatible device.
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Android app compatibility
        """.trimIndent(),
        answer = """ 
            However, you do need to consider whether your app is compatible with each potential device configuration. Because Android runs on a wide range of device configurations, some features are not available on all devices. For example, some devices may not include a compass sensor. If your app's core functionality requires the use of a compass sensor, then your app is compatible only with devices that include a compass sensor.
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Controlling your app's availability to devices
        """.trimIndent(),
        answer = """ 
            Android supports a variety of features your app can leverage through platform APIs. Some features are hardware-based (such as a compass sensor), some are software-based (such as app widgets), and some are dependent on the platform version. Not every device supports every feature, so you may need to control your app's availability to devices based on your app's required features.

            To achieve the largest user-base possible for your app, you should strive to support as many device configurations as possible using a single APK or AAB. In most situations, you can do so by disabling optional features at runtime and providing app resources with alternatives for different configurations (such as different layouts for different screen sizes). If necessary, however, you can restrict your app's availability to devices through Google Play Store based on the following device characteristics:

            Device features (sensor compass, camera, app widgets)
            Platform version 
            Screen configuration
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Device features compatibility
        """.trimIndent(),
        answer = """ 
            In order for you to manage your app’s availability based on device features, Android defines feature IDs for any hardware or software feature that may not be available on all devices. For instance, the feature ID for the compass sensor is FEATURE_SENSOR_COMPASS and the feature ID for app widgets is FEATURE_APP_WIDGETS.

            If necessary, you can prevent users from installing your app when their devices don't provide a given feature by declaring it with a <uses-feature> element in your app's manifest file.
            Google Play Store compares the features your app requires to the features available on each user's device to determine whether your app is compatible with each device. If the device does not provide all the features your app requires, the user cannot install your app.

            However, if your app's primary functionality does not require a device feature, you should set the required attribute to "false" and check for the device feature at runtime. If the app feature is not available on the current device, gracefully degrade the corresponding app feature.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Platform version compatibility
        """.trimIndent(),
        answer = """ 
            Different devices may run different versions of the Android platform, such as Android 4.0 or Android 4.4. Each successive platform version often adds new APIs not available in the previous version. To indicate which set of APIs are available, each platform version specifies an API level. For instance, Android 1.0 is API level 1 and Android 4.4 is API level 19.

            The API level allows you to declare the minimum version with which your app is compatible, using the <uses-sdk> manifest tag and its minSdkVersion attribute. For example, the Calendar Provider APIs were added in Android 4.0 (API level 14). If your app cannot function without these APIs, you should declare API level 14 as your app's minimum supported version.

            The minSdkVersion attribute declares the minimum version with which your app is compatible and the targetSdkVersion attribute declares the highest version on which you've optimized your app.

            However, beware that attributes in the <uses-sdk> element are overridden by corresponding properties in the build.gradle file.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            targetSdkVersion behaviour
        """.trimIndent(),
        answer = """ 
            The targetSdkVersion attribute does not prevent your app from being installed on platform versions that are higher than the specified value, but it is important because it indicates to the system whether your app should inherit behavior changes in newer versions. If you don't update the targetSdkVersion to the latest version, the system assumes that your app requires some backward-compatibility behaviors when running on the latest version. For example, among the behavior changes in Android 4.4, alarms created with the AlarmManager APIs are now inexact by default so the system can batch app alarms and preserve system power, but the system will retain the previous API behavior for your app if your target API level is lower than "19".
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Screen configuration compatibility
        """.trimIndent(),
        answer = """ 
            Android runs on devices of various sizes, from phones to tablets and TVs. In order to categorize devices by their screen type, Android defines two characteristics for each device: screen size (the physical size of the screen) and screen density (the physical density of the pixels on the screen, known as DPI). To simplify the different configurations, Android generalizes these variants into groups that make them easier to target:

            Four generalized sizes: small, normal, large, and xlarge.
            And several generalized densities: mdpi (medium), hdpi (high), xhdpi (extra high), xxhdpi (extra-extra high), and others.
            By default, your app is compatible with all screen sizes and densities, because the system makes the appropriate adjustments to your UI layout and image resources as necessary for each screen. You should provide optimized bitmap images for common screen densities.
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Ways to handle screen compatibility?
        """.trimIndent(),
        answer = """ 
            The screen size is the visible space provided for your app UI. The screen size as it's known to your app is not the actual size of the device screen—it takes into account the screen orientation, system decorations (such as the navigation bar), and window configuration changes (such as when the user enables multi-window mode).
            
            Flexible layouts - By default, Android resizes your app layout to fit the current screen. To ensure your layout resizes well for even small variations in screen size, you need to implement your layout with flexibility in mind. The core principle you must follow is to avoid hard-coding the position and size of your UI components. Instead, allow view sizes to stretch and specify view positions relative to the parent view or other sibling views so your intended order and relative sizes remain the same as the layout grows.
            
            Alternative layouts - A flexible layout is very important, but you should also design different layouts that optimize the user experience for the available space on different devices such as phones and tablets. So Android allows you to provide alternative layout files that the system applies at runtime based on the current device's screen size.

            Stretchable images - Because your layout should stretch to fit the current screen, so too should the bitmaps that you attach to any of the layout views. However, stretching an ordinary bitmap in arbitrary directions can result in strange scaling artifacts and skewed images.
            To solve this, Android supports nine-patch bitmaps in which you specify small pixel regions that are stretchable—the rest of the image remain unscaled.

        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Density independence, different pixel densities
        """.trimIndent(),
        answer = """ 
            Not only do Android devices come in different screen sizes (handsets, tablets, TVs, and so on), but their screens also have different pixel sizes. That is, while one device has 160 pixels per square inch, another device fits 480 pixels in the same space. If you don't consider these variations in pixel density, the system might scale your images (resulting in blurry images) or the images might appear at the completely wrong size.
            
            Your app achieves "density independence" when it preserves the physical size (from the user's point of view) of your UI design when displayed on screens with different pixel densities (as shown in figure 2). Maintaining density independence is important because, without it, a UI element (such as a button) might appear larger on a low-density screen and smaller on a high-density screen (because when the pixels are larger—as shown in figure 2—a few pixels can go a long way).

            The Android system helps you achieve density independence by providing density-independent pixels (dp or dip) as a unit of measurement that you should use instead of pixels (px).
            For texts we should use sp instead of dp.
            
            To provide good graphical qualities on devices with different pixel densities, you should provide multiple versions of each bitmap in your app—one for each density bucket, at a corresponding resolution. Otherwise, Android must scale your bitmap so it occupies the same visible space on each screen, resulting in scaling artifacts such as blurring.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Alternative bitmaps
        """.trimIndent(),
        answer = """ 
            To ensure your images appear at their best on all screens, you should provide alternative bitmaps to match each screen density. For example, if your app provides bitmaps only for medium density (mdpi) screens, Android scales them up when on a high-density screen so that the image occupies the same physical space on the screen. This can cause visible scaling artifacts in bitmaps. So your app should include alternative bitmaps at a higher resolution.
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Vector graphics
        """.trimIndent(),
        answer = """ 
            For simple types of images (usually icons), you can avoid creating separate images for each density by using vector graphics. Because vector graphics define the illustration with geometric line paths instead of pixels, they can be drawn at any size without scaling artifacts.
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            Android Auto, or Chrome OS devices, you need to do a bit more work.

            Each of these devices have their own user interaction model that your app should accommodate. In some cases, such as for Wear OS, you should re-think your app's user experience and build an app that's specialized for that device. And to support Chrome OS devices (such as the Google Pixelbook), you might need only slight modifications to your existing app to support keyboard/mouse interaction and a much larger screen.
            
        """.trimIndent(),
        answer = """ 
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            
        """.trimIndent(),
        answer = """ 
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            
        """.trimIndent(),
        answer = """ 
            
        """.trimIndent(),
    ),
    Question(
        category = DeprecatedCategory.Android,
        question = """
            
        """.trimIndent(),
        answer = """ 
            
        """.trimIndent(),
    ),
)