package co.apoplawski96.kti.questions.data

import co.apoplawski96.kti.questions.model.Category
import co.apoplawski96.kti.questions.model.Question

val questionsNew = listOf(
    Question(
        question = "Fragment lifecycle",
        answer = "onAttach(), onCreate(), onCreateView(), onActivityCreated(), onStart(), onResume(), onPause(), onStop(), onDestroyView(), onDestroy(), onDetach()",
        category = Category.Android
    ),
    Question(
        question = "Activity launch modes",
        answer = "There are four types of launch modes in Android:\n" +
                "\n" +
                "Standard\n" +
                "SingleTop\n" +
                "SingleTask\n" +
                "SingleInstance\n" +
                "Each launch mode has its importance and a unique way of launching the Activities to achieve every possible form of navigation. \n \n" +
                "Standard\n" +
                "This is the default launch mode for Android Activities. It’ll create a new instance of the Activity every time in the target task.\n" +
                "\n" +
                "A common use case is to show the details of a component. For example, consider a movie application. Each time a user clicks on a movie item, we need to show the details of the movie in the details Activity.\n" +
                "\n \n" +
                "SingleTop\n" +
                "If an instance of the Activity is already present in the task and it’s at the top of the task, then the Android OS will pass the intent data to the onNewIntent function of the Activity instead of creating a new instance.\n" +
                "\n" +
                "If there is no instance on the Activity in the task or the already existing instance of the task is not at the top of the task, then a new instance of the Activity will be created. \n \n" +
                "SingleTask\n" +
                "If the tasks don’t have an existing instance of the Activity, then a new instance is created that is similar to singleTop.\n" +
                "\n" +
                "If the tasks have an existing instance of the Activity and it’s at the top of the Activity, then the system will pass the intent data to the onNewIntent function.\n" +
                "\n" +
                "If the tasks have an existing instance but not at the top, then it’ll roll back to that Activity and destroy the Activities on top of the SingleTask Activity. \n \n" +
                "SingleInstance\n" +
                "When you invoke the Activity with SingleInstance, then the system will create a new special task that will only have one SingleInstance Activity in it. If you trigger any default Activity from the SingleInstance Activity, it’ll reroute to the previous task and create a new instance of the default Activity.\n" +
                "\n" +
                "If the instance of the Activity is already created, then the system will route to that task and use the onNewIntent function to pass the data. \n \n" +
                "",
        category = Category.Android
    ),
    Question(
        question = "ConcatMap",
        answer = "It does almost the same as flatMap, but it does maintain the order. \n" +
                "It transforms the items emitted by an Observable into Observables, then flattens the emissions from those into a single Observable. It emits the objects while maintaining the order. It is slower though, cause it works kind of sequentially.",
        category = Category.Rx
    ),
    Question(
        question = "Throttle vs Debounce",
        answer = "Both debounce and throttle are used to filter items emitted by an observable over time.\n" +
                "\n" +
                "throttle emits only the first item emitted by the source observable in the time window.\n" +
                "\n" +
                "debounce only emits an item after the specified time period has passed without another item being emitted by the source observable.",
        category = Category.Rx
    ),
    Question(
        question = "MVP vs MVVM",
        answer = """
            MVP: 
            - easy to learn
            - 1:1 relationship between the presenter and view
            - good unit testing performance
            - view has reference to the presenter
            
            MVVM:
            - Unit testing is more straightforward because you’re not addicted to the View. It is sufficient to verify that the observable variables are properly positioned as the Model changes when testing.
            - ViewModels are even friendlier to unit testing as they simply expose state and, therefore, can be independently tested without testing how the data will be consumed. In short, there is no dependence on the view.
            - Only the View contains a reference to the ViewModel, not the other way around. This solves the tight coupling problem. A single View can reference multiple ViewModels.
        """.trimIndent(),
        category = Category.DesignPatterns
    ),
    Question(
        question = "What is the difference between replace() and add() in Android fragments?",
        answer = "replace() removes the existing fragment and adds a new fragment..\n" +
                "\n" +
                "add() retains the currently displayed fragment and adds a new fragment onto the fragment stack. This means existing fragment will still be active and will be in the ‘paused’ state as such when a back button is pressed onCreateView() will not be called for the existing fragment(the fragment which was there before new fragment was added)." +
                "",
        category = Category.Android
    ),
    Question(
        question = "What is the Broadcasts feature in Android?",
        answer = """
            Android apps can send or receive broadcast messages from the Android system and other Android apps, similar to the publish-subscribe design pattern.
            Apps can receive broadcasts in two ways: through manifest-declared receivers and context-registered receivers.
            App targets API level 26 or higher, cannot use the manifest to declare a receiver for implicit broadcasts except for a few implicit broadcasts that are exempted from that restriction. In most cases, you can use scheduled jobs instead.
            onReceive function in BroadcastReceiver is called when the BroadcastReceiver is receiving an Intent broadcast. This method is always called within the main thread of its process, unless you explicitly asked for it to be scheduled on a different thread.
            Three ways for apps to send broadcast:
            sendOrderedBroadcast which sends broadcasts to one receiver at a time.

            sendBroadcast(Intent) which sends broadcasts to all receivers in an undefined order.

            LocalBroadcastManager.sendBroadcast which sends broadcasts to receivers that are in the same app as the sender.
        """.trimIndent(),
        category = Category.Android
    ),
    Question(
        question = "What is Android MultiDex?",
        answer = "Android MultiDex allows you to generate more than one DEX file to get past the limit required by VM.",
        category = Category.Android
    ),
    Question(
        question = "Kotlin sealed class vs abstract class",
        answer = "A sealed class is abstract by itself, it cannot be instantiated directly and can have abstract members.\n" +
                "\n" +
                "Sealed classes are used for representing restricted class hierarchies, when a value can have one of the types from a limited set, but cannot have any other type.",
        category = Category.Kotlin
    ),
    Question(
        question = "Why should avoid reflection in Android?\n",
        answer = "" +
                "Reflection can lead to run-time errors." +
                "Reflection is to be avoided if possible. It's very hacky, very brittle (breaks easily on updates), hard to maintain and often gets you into bad situations by skipping important validation/setup steps. \n" +
                "The big problem with reflection is that the classes you use might change and you won't notice because the compiler does not know. This is especially bad, when you use it to access third party code that is not under your control or internal apis that are not meant for public consumption. As such it is considered bad practice in general but even more so on Android because of the performance implications. As a rule I would never do anything with reflection that can be done without it.",
        category = Category.Android
    ),
    Question(
        question = "What is the Fragment?",
        answer = "A Fragment is a piece of an activity which enables more modular activity design. A Fragment represents a reusable portion of your app's UI. A fragment defines and manages its own layout, has its own lifecycle, and can handle its own input events. Fragments cannot live on their own, they must be hosted by an activity or another fragment.",
        category = Category.Android
    ),
    Question(
        question = "Name the latest version of Android?",
        answer = "Android 13, Tiramisu in Beta, Stable Android 12",
        category = Category.Android
    ),
    Question(
        question = "What is ANR? Why it’s happen?",
        answer = "ANR- Application Not Responding 'n" +
                "From Google documentation We can assume that when the app UI is stuck for 5 seconds for any functionality then ANR is occurred.\n" +
                "\n" +
                "An ANR will be triggered for your app when one of the following conditions occur:\n" +
                "\n" +
                "While your activity is in the foreground, your app has not responded to an input event or BroadcastReceiver (such as key press or screen touch events) within 5 seconds.\n" +
                "While you do not have an activity in the foreground, your BroadcastReceiver hasn't finished executing within a considerable amount of time.",
        category = Category.Android
    ),
    Question(
        question = "Types of services:",
        answer = "Foreground service\n" +
                "Background service\n" +
                "Bound service\n" +
                "Foreground service:\n" +
                "\n" +
                "If we need to perform operations which should have user interaction we can use foreground service. Music player application is the best example for it.\n" +
                "\n" +
                "Background service:\n" +
                "\n" +
                "If we need to run a operation without user acknowledgement then we can use background service.\n" +
                "\n" +
                "Bound service:\n" +
                "\n" +
                "This type of android service allows the components of the application like activity to bound themselves with it. Bound services perform their task as long as any application component is bound to it. More than one component is allowed to bind themselves with a service at a time. In order to bind an application component with a service bindService() method is used.\n" +
                "\n",
        category = Category.Android
    ),
    Question(
        question = "Activity launch modes." +
                "\n",
        answer = "There are four types of launch modes in Android:\n" +
                "\n" +
                "Standard\n" +
                "SingleTop\n" +
                "SingleTask\n" +
                "SingleInstance\n \n" +
                "Standard\n" +
                "This is the default launch mode for Android Activities. It’ll create a new instance of the Activity every time in the target task.\n" +
                "\n" +
                "A common use case is to show the details of a component. For example, consider a movie application. Each time a user clicks on a movie item, we need to show the details of the movie in the details Activity." +
                "\n \n" +
                "SingleTop\n" +
                "If an instance of the Activity is already present in the task and it’s at the top of the task, then the Android OS will pass the intent data to the onNewIntent function of the Activity instead of creating a new instance.\n" +
                "\n" +
                "If there is no instance on the Activity in the task or the already existing instance of the task is not at the top of the task, then a new instance of the Activity will be created. \n" +
                "\n \n" +
                "SingleTask\n" +
                "If the tasks don’t have an existing instance of the Activity, then a new instance is created that is similar to singleTop.\n" +
                "\n" +
                "If the tasks have an existing instance of the Activity and it’s at the top of the Activity, then the system will pass the intent data to the onNewIntent function.\n" +
                "\n" +
                "If the tasks have an existing instance but not at the top, then it’ll roll back to that Activity and destroy the Activities on top of the SingleTask Activity. \n \n" +
                "SingleInstance\n" +
                "When you invoke the Activity with SingleInstance, then the system will create a new special task that will only have one SingleInstance Activity in it. If you trigger any default Activity from the SingleInstance Activity, it’ll reroute to the previous task and create a new instance of the default Activity.\n" +
                "\n" +
                "If the instance of the Activity is already created, then the system will route to that task and use the onNewIntent function to pass the data.",
        category = Category.Android
    ),
    Question(
        question = "Proguard & R8",
        answer = "When you build your project using Android Gradle plugin 3.4.0 or higher, the plugin no longer uses ProGuard to perform compile-time code optimization. Instead, the plugin works with the R8 compiler to handle the following compile-time tasks \n" +
                "When building the release version of your app, by default, R8 automatically performs the compile-time tasks described above for you. However, you can disable certain tasks or customize R8’s behavior through ProGuard rules files. In fact, R8 works with all of your existing ProGuard rules files, so updating the Android Gradle plugin to use R8 should not require you to change your existing rules. \n" +
                "",
        category = Category.Android
    ),
    Question(
        question = "Difference between HashMap and HashSet in Java",
        answer = "HashSet does not allow duplicate value. In HashMap duplicate keys are not allowed but duplicate values are allowed. HashMap allows multiple null values and single null key. In HashSet single null value is allowed.\n" +
                "\n HashMap is faster than HashSet. In HashSet dummy values are allowed but in HashMap dummy values are not allowed.",
        category = Category.Kotlin
    ),
    Question(
        question = "Code shrinking",
        answer = "Code shrinking (or tree-shaking): detects and safely removes unused classes, fields, methods, and attributes from your app and its library dependencies (making it a valuable tool for working around the 64k reference limit). For example, if you use only a few APIs of a library dependency, shrinking can identify library code that your app is not using and remove only that code from your app. To learn more, go to the section about how to shrink your code. \n" +
                "",
        category = Category.Android
    ),
    Question(
        question = "Resource shrinking",
        answer = "Resource shrinking: removes unused resources from your packaged app, including unused resources in your app’s library dependencies. It works in conjunction with code shrinking such that once unused code has been removed, any resources no longer referenced can be safely removed as well. To learn more, go to the section about how to shrink your resources.",
        category = Category.Android
    ),
    Question(
        question = "Obfuscation",
        answer = "Obfuscation: shortens the name of classes and members, which results in reduced DEX file sizes. To learn more, go to the section about how to obfuscate your code.",
        category = Category.Android
    ),
    Question(
        question = "Optimization:",
        answer = "Optimization: inspects and rewrites your code to further reduce the size of your app’s DEX files. For example, if R8 detects that the else {} branch for a given if/else statement is never taken, R8 removes the code for the else {} branch. To learn more, go to the section about code optimization." +
                "",
        category = Category.Android
    ),
    Question(
        question = "Permissions on Android",
        answer = "App permissions help support user privacy by protecting access to the following:\n" +
                "\n" +
                "Restricted data, such as system state and a user's contact information.\n" +
                "Restricted actions, such as connecting to a paired device and recording audio.",
        category = Category.Android
    ),
    Question(
        question = "Workflow for using permissions",
        answer = "If your app offers functionality that might require access to restricted data or restricted actions, determine whether you can get the information or perform the actions without needing to declare permissions. You can fulfill many use cases in your app, such as taking photos, pausing media playback, and displaying relevant ads, without needing to declare any permissions.\n" +
                "\n" +
                "If you decide that your app must access restricted data or perform restricted actions to fulfill a use case, declare the appropriate permissions. Some permissions, known as install-time permissions, are automatically granted when your app is installed. Other permissions, known as runtime permissions, require your app to go a step further and request the permission at runtime.\n" +
                "\n",
        category = Category.Android
    ),
    Question(
        question = "Types of permissions",
        answer = "Android categorizes permissions into different types, including install-time permissions, runtime permissions, and special permissions. Each permission's type indicates the scope of restricted data that your app can access, and the scope of restricted actions that your app can perform, when the system grants your app that permission.\n" +
                "\n",
        category = Category.Android
    ),
    Question(
        question = "Install-time permissions\n",
        answer = "Install-time permissions give your app limited access to restricted data, and they allow your app to perform restricted actions that minimally affect the system or other apps. When you declare install-time permissions in your app, the system automatically grants your app the permissions when the user installs your app. An app store presents an install-time permission notice to the user when they view an app's details page" +
                "\n" +
                "Android includes several sub-types of install-time permissions, including normal permissions and signature permissions.\n" +
                "\n" +
                "Normal permissions\n" +
                "These permissions allow access to data and actions that extend beyond your app's sandbox. However, the data and actions present very little risk to the user's privacy, and the operation of other apps.\n" +
                "\n" +
                "The system assigns the \"normal\" protection level to normal permissions, as shown on the permissions API reference page. \n" +
                "Signature permissions\n" +
                "If the app declares a signature permission that another app has defined, and if the two apps are signed by the same certificate, then the system grants the permission to the first app at install time. Otherwise, that first app cannot be granted the permission. \n",
        category = Category.Android
    ),
    Question(
        question = "Runtime permissions",
        answer = "Runtime permissions, also known as dangerous permissions, give your app additional access to restricted data, and they allow your app to perform restricted actions that more substantially affect the system and other apps. Therefore, you need to request runtime permissions in your app before you can access the restricted data or perform restricted actions. When your app requests a runtime permission, the system presents a runtime permission prompt, as shown in Figure 3.\n" +
                "\n" +
                "Many runtime permissions access private user data, a special type of restricted data that includes potentially sensitive information. Examples of private user data include location and contact information.\n" +
                "\n" +
                "The microphone and camera provide access to particularly sensitive information. Therefore, the system helps you explain why your app accesses this information." +
                "The system assigns the \"dangerous\" protection level to runtime permissions, as shown on the permissions API reference page.",
        category = Category.Android
    ),
    Question(
        question = "Special permissions",
        answer = "Special permissions correspond to particular app operations. Only the platform and OEMs can define special permissions. Additionally, the platform and OEMs usually define special permissions when they want to protect access to particularly powerful actions, such as drawing over other apps.\n" +
                "\n" +
                "The Special app access page in system settings contains a set of user-toggleable operations. Many of these operations are implemented as special permissions.\n" +
                "\n" +
                "Each special permission has its own implementation details. The instructions for using each special permission appear on the permissions API reference page. The system assigns the \"appop\" protection level to special permissions.",
        category = Category.Android
    ),
    Question(
        question = "Best practices - Permissions",
        answer = "Control: The user has control over the data that they share with apps.\n" +
                "Transparency: The user understands what data an app uses, and why the app accesses this data.\n" +
                "Data minimization: An app accesses and uses only the data that's required for a specific task or action that the user invokes. \n" +
                "\n \n " +
                "Request a minimal number of permissions\n" +
                "When the user requests a particular action in your app, your app should request only the permissions that it needs to complete that action. Depending on how you are using the permissions, there might be an alternative way to fulfill your app's use case without relying on access to sensitive information.\n" +
                "\n \n" +
                "Associate runtime permissions with specific actions\n" +
                "Request permissions as late into the flow of your app's use cases as possible. For example, if your app allows users to send audio messages to others, wait until the user has navigated to the messaging screen and has pressed the Send audio message button. After the user presses the button, your app can then request access to the microphone. \n \n" +
                "Consider your app's dependencies\n" +
                "When you include a library, you also inherit its permission requirements. Be aware of the permissions that each dependency requires, and what those permissions are used for.\n" +
                "\n\n" +
                "Be transparent\n" +
                "When you make a permissions request, be clear about what you're accessing, and why, so users can make informed decisions.\n" +
                "\n\n" +
                "Make system accesses explicit\n" +
                "When you access sensitive data or hardware, such as the camera or microphone, provide a continuous indication in your app if the system doesn't already provide these indicators. This reminder helps users understand exactly when your app accesses restricted data or performs restricted actions.",
        category = Category.Android
    ),
    Question(
        question = "Declaring permissions alternatives",
        answer = "Before you declare permissions in your app, consider whether you need to do so. Every time the user tries an app feature that requires a runtime permission, your app has to interrupt the user's work with a permission request. The user then must make a decision. If the user doesn't understand why your app requests a particular permission, they could deny the permission or even uninstall your app.\n" +
                "\n" +
                "Consider whether another installed app might be able to perform the functionality on your app's behalf. In these cases, you should delegate the task to another app using an intent. In doing so, you don't need to declare the necessary permissions because the other app declares the permission instead. \n" +
                "",
        category = Category.Android
    ),
    Question(
        question = "ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION",
        answer = "Your app might need to know the user's approximate location. This is useful for showing location-aware information, such as nearby restaurants.\n" +
                "\n" +
                "Some use cases require a rough estimate of a device's location. In these situations, do one of the following, depending on how often your app needs location-aware information:\n" +
                "\n" +
                "If your app needs location more often, declare the ACCESS_COARSE_LOCATION permission. The permission provides a device location estimate from location services, as described in the documentation about approximate location accuracy.\n" +
                "If your app needs location less often, or only once, consider asking the user to enter an address or a postal code instead.\n" +
                "Other use cases require a more precise estimate of a device's location. Only in those situations, it's OK to declare the ACCESS_FINE_LOCATION permission.\n" +
                "\n",
        category = Category.Android
    ),
    Question(
        question = "Using camera without asking for permission",
        answer = "Take a photo\n" +
                "Users might take pictures in your app, using the pre-installed system camera app.\n" +
                "\n" +
                "In this situation, don't declare the CAMERA permission. Instead, invoke the ACTION_IMAGE_CAPTURE intent action.\n" +
                "\n" +
                "Record a video\n" +
                "Users might record videos in your app, using the pre-installed system camera app.\n" +
                "\n" +
                "In this situation, don't declare the CAMERA permission. Instead, invoke the ACTION_VIDEO_CAPTURE intent action.\n" +
                "\n" +
                "\n",
        category = Category.Android
    ),
    Question(
        question = "External storage permissions",
        answer = "Your app might show media content, such as photos or videos, that the user created while in your app. In this situation, you don't need to use the READ_EXTERNAL_STORAGE permission on devices that run Android 10 (API level 29) or higher, as long as your app targets Android 10 or higher. If your app targets Android 10, opt out of scoped storage.\n" +
                "\n" +
                "For compatibility with older devices, declare the READ_EXTERNAL_STORAGE permission, and set the android:maxSdkVersion to 28.\n" +
                "\n" +
                "Look for the file in one of the following collections, which are well-known to the media store:\n" +
                "\n" +
                "MediaStore.Images\n" +
                "MediaStore.Video\n" +
                "MediaStore.Audio\n" +
                "Use ContentResolver to query media content directly from the media store, rather than attempting to discover media content on your own.",
        category = Category.Android
    ),
    Question(
        question = "Opening documents permissions",
        answer = "Open documents\n" +
                "Your app might show documents that the user created, either in your app or in another app. A common example is a text file.\n" +
                "\n" +
                "In this situation, you don't need to use the READ_EXTERNAL_STORAGE permission on devices that run Android 10 or higher, as long as your app targets Android 10 or higher. If your app targets Android 10, opt out of scoped storage.\n" +
                "\n" +
                "For compatibility with older devices, declare the READ_EXTERNAL_STORAGE permission, and set the android:maxSdkVersion to 28.\n" +
                "\n" +
                "Depending on which app created the document, do one of the following:\n" +
                "\n" +
                "If the user created the document in your app, access it directly.\n" +
                "If the user created the document in another app, use the Storage Access Framework.",
        category = Category.Android
    ),
    Question(
        question = "Requesting permissions basic principles",
        answer = "The basic principles for requesting permissions at runtime are as follows:\n" +
                "\n" +
                "Ask for permissions in context, when the user starts to interact with the feature that requires it.\n" +
                "Don't block the user. Always provide the option to cancel an educational UI flow related to permissions.\n" +
                "If the user denies or revokes a permission that a feature needs, gracefully degrade your app so that the user can continue using your app, possibly by disabling the feature that requires the permission.\n" +
                "Don't assume any system behavior. For example, don't assume that permissions appear in the same permission group. A permission group merely helps the system minimize the number of system dialogs that are presented to the user when an app requests closely-related permissions. \n" +
                "",
        category = Category.Android
    ),
    Question(
        question = "Workflow for requesting permissions",
        answer = """
            Before you declare and request runtime permissions in your app, evaluate whether your app needs to do so. You can fulfill many use cases in your app, such as taking photos, pausing media playback, and displaying relevant ads, without needing to declare any permissions.

            If you conclude that your app needs to declare and request runtime permissions, complete these steps:

            In your app's manifest file, declare the permissions that your app might need to request.
            Design your app's UX so that specific actions in your app are associated with specific runtime permissions. Users should know which actions might require them to grant permission for your app to access private user data.
            Wait for the user to invoke the task or action in your app that requires access to specific private user data. At that time, your app can request the runtime permission that's required for accessing that data.
            Check whether the user has already granted the runtime permission that your app requires. If so, your app can access the private user data. If not, continue to the next step.

            You must check whether you have that permission every time you perform an operation that requires that permission.

            Check whether your app should show a rationale to the user, explaining why your app needs the user to grant a particular runtime permission. If the system determines that your app shouldn't show a rationale, continue to the next step directly, without showing a UI element.

            If the system determines that your app should show a rationale, however, present the rationale to the user in a UI element. This rationale should clearly explain what data your app is trying to access, and what benefits the app can provide to the user if they grant the runtime permission. After the user acknowledges the rationale, continue to the next step.

            Request the runtime permission that your app requires in order to access the private user data. The system displays a runtime permission prompt, such as the one shown on the permissions overview page.

            Check the user's response, whether they chose to grant or deny the runtime permission.

            If the user granted the permission to your app, you can access the private user data. If the user denied the permission instead, gracefully degrade your app experience so that it provides functionality to the user, even without the information that's protected by that permission.
        """.trimIndent(),
        category = Category.Android
    ),
    Question(
        question = "Merge vs Rebase",
        answer = "Both of these commands are designed to integrate changes from one branch into another branch—they just do it in very different ways.\n" +
                "\n Merge would create a new merge commit in the feature branch that ties together the histories of both branches. Merging is a non-destructive operation. " +
                "The existing branches are not changed in any way. This avoids all potential pitfalls of rebasing. On the other hand, this also means that the feature branch will have an extraneous merge commit every time you need to incorporate upstream changes. If main is very active, this can pollute your feature branch’s history quite a bit. \n" +
                "\n \n " +
                "Rebase moves the entire feature branch to begin on the tip of the main branch, effectively incorporating all of the new commits in main. But, instead of using a merge commit, rebasing re-writes the project history by creating brand new commits for each commit in the original branch.\n" +
                "\n The major benefit of rebasing is that you get a much cleaner project history. First, it eliminates the unnecessary merge commits required by git merge. Second, as you can see in the above diagram, rebasing also results in a perfectly linear project history—you can follow the tip of feature all the way to the beginning of the project without any forks. This makes it easier to navigate your project with commands like git log, git bisect, and gitk. \n" +
                "Rebase pretends that the work done on the feature branch was always done on the main branch. It takes commits from the feature branch and places them on the main branch. \n \n" +
                "merge: git checkout master, git merge feature \n \n" +
                "rebase: git checkout feature, git rebase master, git checkout feature",
        category = Category.Git
    ),
    Question(
        question = "Handling permissions in Compose",
        answer = "rememberPermissionState(), rememberMultiplePermissionsState()",
        category = Category.Compose
    ),
)