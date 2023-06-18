package co.apoplawski96.kti.questions.data

import com.example.myapplication.questions.model.DeprecatedCategory
import co.apoplawski96.kti.questions.model.Question

val questions: List<Question> = listOf(
    Question(
        question = "Describe StateFlow.",
        answer = """
            State flow is a special-purpose, high-performance, and efficient implementation of SharedFlow for the narrow, but widely used case of sharing a state. State flow always has an initial value, replays one most recent value to new subscribers, does not buffer any more values, but keeps the last emitted one, and does not support resetReplayCache. // MutableStateFlow(initialValue) is a shared flow with the following parameters:
                val shared = MutableSharedFlow(
                    replay = 1,
                    onBufferOverflow = BufferOverflow.DROP_OLDEST
                )
                shared.tryEmit(initialValue) // emit the initial value
                val state = shared.distinctUntilChanged() // get StateFlow-like behavior. A SharedFlow that represents a read-only state with a single updatable data value that emits updates to the value to its collectors. A state flow is a hot flow because its active instance exists independently of the presence of collectors. Its current value can be retrieved via the value property.When a new consumer starts collecting from the flow, it receives the last state in the stream and any subsequent states.
                """,
        category = DeprecatedCategory.StateAndSharedFlow,
    ),
    Question(
        question = "Describe the differences and similarities between State Flow & Shared Flow.",
        answer = """StateFlow is a specialized configuration of SharedFlow optimized for sharing state: the last emitted item is replayed to new collectors, and items are conflated using Any.equals.Similarities: 
- StateFlow & SharedFlow are both hot flows 
- They are both Flows 
Differences: - SharedFlows are used for one-time-events, such as displaying Snackbar or Toasts 
- StateFlow will automatically emit the value after for example device rotates 
- StateFlow would not re-emit the same value, so i.e. displaying same Snackbar multiple times would be impossible 
""",
        category = DeprecatedCategory.StateAndSharedFlow,
    ),
    Question(
        question = "Describe SharedFlow.",
        answer = """- A hot Flow that shares emitted values among all its collectors in a broadcast fashion, so that all collectors get all emitted values. A shared flow is called hot because its active instance exists independently of the presence of collectors. This is opposed to a regular Flow, such as defined by the flow { ... } function, which is cold and is started separately for each collector. \n SharedFlow is useful for broadcasting events that happen inside an application to subscribers that can come and go. \m A shared flow keeps a specific number of the most recent values in its replay cache. Every new subscriber first gets the values from the replay cache and then gets new emitted values. The maximum size of the replay cache is specified when the shared flow is created by the replay parameter. A snapshot of the current replay cache is available via the replayCache property and it can be reset with the MutableSharedFlow.resetReplayCache function. Use SharedFlow when you need a StateFlow with tweaks in its behavior such as extra buffering, replaying more values, or omitting the initial value.""",
        category = DeprecatedCategory.StateAndSharedFlow,
    ),
    Question(
        question = "What is a hot flow? What is a cold flow?",
        answer = "A cold stream does not start producing values until one starts to collect them. " +
                "Cold flow is what you need if the flow needs a collector. Each collector has its own instance of the underlying cold flow. It gets collected and it's done & gone." +
                "A hot stream on the other hand starts producing values immediately. " +
                " It's always around (in memory) and publishes application events emitted from possibly multiple coroutines. When some parties interested in, they get subscribed by collect, and all will get the same event (or set of events) emitted from that hot flow. Much like an event bus." +
                "Unlike cold flows, hot flows are always in memory even tho when there's no subscribers/collectors and they're active by default. Cold flows are lazy.. However, hot flows are better for sharing a state across multiple observers." +
                "",
        category = DeprecatedCategory.Flow,
    ),
    Question(
        question = "LiveData vs StateFlow",
        answer = "LiveData is Android only class, therefore it is unfit for domain & data layer. StateFlow is a Flow, which enables using powerful Flow operators.",
        category = DeprecatedCategory.Flow,
    ),
    Question(
        question = "launchWhenStarted{ }, repeatOnLifecycle {}",
        answer = "Launches and runs the given block when the Lifecycle controlling this LifecycleCoroutineScope is at least in Lifecycle.State.STARTED state.\n\nThe returned Job will be cancelled when the Lifecycle is destroyed.\n\nCaution: This API is not recommended to use as it can lead to wasted resources in some cases. Please, use the Lifecycle.repeatOnLifecycle API instead. This API will be removed in a future release.",
        category = DeprecatedCategory.Flow,
    ),
    Question(
        question = "repeatOnLifecycle() {}",
        answer = "repeatOnLifecycle launches the block in a new coroutine every time the lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED",
        category = DeprecatedCategory.Flow,
    ),
    Question(
        question = "Flow vs StateFlow & LiveData",
        answer = "Flow does not hold a state.",
        category = DeprecatedCategory.StateAndSharedFlow,
    ),
    Question(
        question = "shareIn(), stateIn()",
        answer = "The Flow.shareIn and Flow.stateIn operators convert cold flows into hot flows: they can multicast the information that comes from a cold upstream flow to multiple collectors." +
                "Note: Cold flows are created on-demand and emit data when they’re being observed. Hot flows are always active and can emit data regardless of whether or not they’re being observed." +
                "",
        category = DeprecatedCategory.StateAndSharedFlow,
    ),
    Question(
        question = "Imperative vs Declarative",
        answer = "In imperative you write orders. Set this, take that, change this. In declarative you describe how the UI have too look and let it react to different state.",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Compose",
        answer = "Compose characteristics:\n" +
                "- Less code \n" +
                "- Intuitive: just describe your UI and Compose takes care of the rest. As app state changes, your UI automatically updates. \n" +
                "- Compose intelligently chooses which parts of the UI need to be redrawn at any given time. \n" +
                "- Declarative programming paradigm: it allows to render UI without imperatively mutating views. Manipulating views manually increases the likelihood of errors. If a piece of data is rendered in multiple places, it’s easy to forget to update one of the views that shows it. It’s also easy to create illegal states, when two updates conflict in an unexpected way. For example, an update might try to set a value of a node that was just removed from the UI. In general, the software maintenance complexity grows with the number of views that require updating." +
                "Over the last several years, the entire industry has started shifting to a declarative UI model, which greatly simplifies the engineering associated with building and updating user interfaces. The technique works by conceptually regenerating the entire screen from scratch, then applying only the necessary changes. This approach avoids the complexity of manually updating a stateful view hierarchy. Compose is a declarative UI framework.\n" +
                "\n" +
                "One challenge with regenerating the entire screen is that it is potentially expensive, in terms of time, computing power, and battery usage. To mitigate this cost, Compose intelligently chooses which parts of the UI need to be redrawn at any given time. This does have some implications for how you design your UI components, as discussed in Recomposition.",
        category = DeprecatedCategory.Compose,
    ),
    Question(
        question = "Describe Singleton design pattern",
        answer = "A singleton is a class that allows only a single instance of itself to be created and gives access to that created instance. It contains static variables that can accommodate unique and private instances of itself. It is used in scenarios when a user wants to restrict the instantiation of a class to only one object. This is helpful usually when a single object is required to coordinate actions across a system." +
                "",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "What are the coroutines?",
        answer = "A coroutine in Kotlin programming language is the feature that helps to converts asynchronous background processes to the sequential code.\n" +
                "\n" +
                "Managing background threads.\n" +
                "It helps in thread concurrency efficiently.\n" +
                "Replace traditional callbacks.\n" +
                "Maps the async code into the sequential code.\n" +
                "Main thread safety.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Tell some advantages of Kotlin over Java? or What are the Kotlin functionalities that we can't achieve in java?",
        answer = "- Null safety\n" +
                "- Kotlin coroutines\n" +
                "- Triple and Pair structures\n" +
                "- Destructuring Initialization\n" +
                "- Inline functions\n" +
                "- Infix functions\n" +
                "- Scope functions\n" +
                "- We can write functions at the file level.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Inline functions",
        answer = "Inline keywords is useful when working with higher-order functions. It prevents from creating a function object. Compiler copies the content of the inline function to the call site avoiding creating a new function object." +
                "\nInline should only be used with short functions, because it may cause the generated code to grow a lot. Also only with higher-order functions, since it is pointless for different ones." +
                "\n \n Inline functions are used to save us memory overhead by preventing object allocations for the anonymous functions/lambda expressions called. Instead, it provides that functions body to the function that calls it at runtime. This increases the bytecode size slightly but saves us a lot of memory.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Inline classes",
        answer = "Sometimes it is necessary for business logic to create a wrapper around some type. However, it introduces runtime overhead due to additional heap allocations. Moreover, if the wrapped type is primitive, the performance hit is terrible, because primitive types are usually heavily optimized by the runtime, while their wrappers don't get any special treatment.\n" +
                "\n" +
                "To solve such issues, Kotlin introduces a special kind of class called an inline class. Inline classes are a subset of value-based classes. They don't have an identity and can only hold values.\n" +
                "\n" +
                "To declare an inline class, use the value modifier before the name of the class: value class Password(private val s: String)\n" +
                "An inline class must have a single property initialized in the primary constructor. At runtime, instances of the inline class will be represented using this single property\n" +
                "This is the main feature of inline classes, which inspired the name inline: data of the class is inlined into its usages (similar to how content of inline functions is inlined to call sites).\n" +
                "\n",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Infix functions",
        answer = "Functions marked with the infix keyword can also be called using the infix notation (omitting the dot and the parentheses for the call). Infix functions must meet the following requirements:\n" +
                "\n" +
                "They must be member functions or extension functions.\n" +
                "\n" +
                "They must have a single parameter.\n" +
                "\n" +
                "The parameter must not accept variable number of arguments and must have no default value.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Scope functions",
        answer = "The Kotlin standard library contains several functions whose sole purpose is to execute a block of code within the context of an object. When you call such a function on an object with a lambda expression provided, it forms a temporary scope. In this scope, you can access the object without its name. Such functions are called scope functions. There are five of them: let, run, with, apply, and also.\n" +
                "\n" +
                "Basically, these functions do the same: execute a block of code on an object. What's different is how this object becomes available inside the block and what is the result of the whole expression.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is the difference between ‘Val’ and ‘const Val’ in kotlin?",
        answer = "Val is rum time constant and const Val are compile-time constant.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is the difference between statically typed and dynamically typed language? What Kotlin do?",
        answer = "Statically typed languages are those in which types checking is done at compile-time and while dynamic typed are those which check types at run time. Kotlin is a statically typed language.",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "What is the lazy property in Kotlin?",
        answer = "Through lazy we can delegate our property getter to behave lazily.\n" +
                "It will take care of thread safety while initializing the property.\n" +
                "If the initialization of a value throws an exception, it will attempt to reinitialize the value at the next access.\n" +
                "\n" +
                "What does it mean by lazy, it means on the first call it evaluates the value of the property, and on each next access, it will just return the same value from the first evaluation.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Explain the philosophy of the primary constructor and init block.",
        answer = "In Kotlin a class can have a primary constructor with optional parameters and the primary constructor is the part of the class header. It just goes after the class name.\n" +
                "\n" +
                "The primary constructor cannot contain any code. Initialization code can be placed in initializer blocks, which are prefixed with the init keyword.\n" +
                "\n" +
                "A class can have more than one init{} blocks and During an instance initialization, the initializer blocks are executed in the same order as they appear in the class body",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "If we made a call to the secondary constructor, either secondary constructor block executed first, or the init block?",
        answer = "Logically the code in initializer blocks effectively becomes part of the primary constructor. Delegation to the primary constructor happens as the first statement of a secondary constructor, so the code in all initializer blocks and property initializers is executed before the secondary constructor body. Even\n" +
                "\n" +
                "if the class has no primary constructor, the delegation still happens implicitly, and the initializer blocks are still executed:",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is 'Any' class in Kotlin?",
        answer = "All classes in Kotlin have a common superclass Any, that is the default superclass for a class with no supertypes declared:",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is a suspended function in Kotlin?",
        answer = "By syntax, a function that has a keyword suspend is a suspended function.\n" +
                "A suspended function is a part of Kotlin coroutine, that is callable from a coroutine or other suspended functions.\n" +
                "It is used to pause-resume the coroutine execution and it helps in main -thread safety.\n" +
                "Suspend keyword is similar to async. However, in Kotlin, await() is implicit when calling a suspend function. For example",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is the difference between CoroutineScope & ViewModelScope?\n",
        answer = "The difference: viewModelScope takes care of the cancellation, it lives as long as the hosting view model. In terms of generic CoroutineScope - we need to take care of the cancelation by ourselves." +
                "CoroutineScope:\n" +
                "CoroutineScope is the API available in Kotlin Coroutine to create a coroutine and all coroutines run inside a CoroutineScope. A scope controls the lifetime of coroutines through its job. When you cancel the job of scope, it cancels all coroutines started in that scope.\n" +
                "\n" +
                "ViewModelScope:\n" +
                "It’s available in the below library implementation and it si specific t to Android.\n" +
                "\n" +
                "“androidx.lifecycle:lifecycle-ViewModel-ktx:x.x.x”\n" +
                "\n" +
                "This library has added it as viewModelScope as an extension function of the ViewModel class.\n" +
                "This scope is bound to Dispatchers.Main and will automatically be canceled when the ViewModel is cleared.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Describe the optimistic usages for different Dispatchers in Kotlin coroutines. What are dispatchers?",
        answer = "An important functionality offered by the Kotlin Coroutines library is letting us decide on which thread (or pool of threads) a coroutine should be running (starting and resuming). This is done using dispatchers.\n" +
                "\n" +
                "In the English dictionary, a dispatcher is defined as \"a person who is responsible for sending people or vehicles to where they are needed, especially emergency vehicles\". In Kotlin coroutines, CoroutineContext determines on which thread a certain coroutine will run.\n" +
                "\n" +
                "Dispatchers in Kotlin Coroutines are a similar concept to RxJava Schedulers." +
                "Main:\n" +
                "Main is used to perform UI kind operations like Main-thread in Android.\n" +
                "\n" +
                "IO:\n" +
                "The IO dispatcher is optimized for IO work like reading from the network or disk.\n" +
                "\n" +
                "Default:\n" +
                "The Default dispatcher is optimized for CPU intensive tasks.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "How coroutines provide Main-safety?\n",
        answer = "Previously, It was a headache to make sure the threads-safety while calling a network request or database operations. Then after operation completion, we should make sure either we are on Main-thread to update our UI.\n" +
                "\n" +
                "Coroutines can easily switch threads at any time and pass results back to the original thread, it's a good idea to start UI-related coroutines on the Main thread.\n" +
                "A coroutine started on Dispatchers.Main won’t block the main thread while suspended.\n" +
                "Libraries like Room and Retrofit offer main-safety out of the box when using coroutines, so you don’t need to manage threads to make network or database calls. This can often lead to a substantially simpler code.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Data class - expectations",
        answer = "The primary constructor needs to have at least one parameter;\n" +
                "All primary constructor parameters need to be marked as val or var;\n" +
                "Data classes cannot be abstract, open, sealed or inner;",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What are coroutines in Kotlin?",
        answer = "Unlike many other languages with similar capabilities, async and await are not keywords in Kotlin and are not even part of its standard library.\n" +
                "\n" +
                "kotlinx.coroutines is a rich library for coroutines developed by JetBrains. It contains a number of high-level coroutine-enabled primitives, including launch, async and others. Kotlin Coroutines give you an API to write your asynchronous code sequentially.\n" +
                "\n" +
                "The documentation says Kotlin Coroutines are like lightweight threads. They are lightweight because creating coroutines doesn’t allocate new threads. Instead, they use predefined thread pools, and smart scheduling. Scheduling is the process of determining which piece of work you will execute next.\n" +
                "\n" +
                "Additionally, coroutines can be suspended and resumed mid-execution. This means you can have a long-running task, which you can execute little-by-little. You can pause it any number of times, and resume it when you’re ready again.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is lateinit in Kotlin and when would you use it?",
        answer = "lateinit means late initialization. If you do not want to initialize a variable in the constructor instead you want to initialize it later on and if you can guarantee the initialization before using it, then declare that variable with lateinit keyword. It will not allocate memory until initialized. You cannot use lateinit for primitive type properties like Int, Long etc." +
                "There are a handful of use cases where this is extremely helpful, for example:\n" +
                "\n" +
                "Android: variables that get initialized in lifecycle methods;\n" +
                "Using Dagger for DI: injected class variables are initialized outside and independently from the constructor;\n" +
                "Setup for unit tests: test environment variables are initialized in a @Before - annotated method;\n" +
                "Spring Boot annotations (eg. @Autowired).",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is the difference between suspending vs. blocking?",
        answer = "A blocking call to a function means that a call to any other function, from the same thread, will halt the parent’s execution. Following up, this means that if you make a blocking call on the main thread’s execution, you effectively freeze the UI. Until that blocking calls finishes, the user will see a static screen, which is not a good thing.\n" +
                "\n" +
                "Suspending doesn’t necessarily block your parent function’s execution. If you call a suspending function in some thread, you can easily push that function to a different thread. In case it is a heavy operation, it won’t block the main thread. If the suspending function has to suspend, it will simply pause its execution. This way you free up its thread for other work. Once it’s done suspending, it will get the next free thread from the pool, to finish its work.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "What is the difference between open and public in Kotlin?",
        answer = "The open keyword means “open for extension“. The open annotation on a class is the opposite of Java's final: it allows others to inherit from this class.\n" +
                "If you do not specify any visibility modifier, public is used by default, which means that your declarations will be visible everywhere. public is the default if nothing else is specified explicitly.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is Application?",
        answer = "The Application class in Android is the base class within an Android app that contains all other components such as activities and services. The Application class, or any subclass of the Application class, is instantiated before any other class when the process for your application/package is created.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is Context?",
        answer = "A Context is a handle to the system; it provides services like resolving resources, obtaining access to databases and preferences, and so on. An Android app has activities. Context is like a handle to the environment your application is currently running in.\n" +
                "Application Context: This context is tied to the lifecycle of an application. The application context can be used where you need a context whose lifecycle is separate from the current context or when you are passing a context beyond the scope of an activity.\n" +
                "Activity Context: This context is available in an activity. This context is tied to the lifecycle of an activity. The activity context should be used when you are passing the context in the scope of an activity or you need the context whose lifecycle is attached to the current context.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is a BuildType in Gradle? And what can you use it for?",
        answer = "Build types define properties that Gradle uses when building and packaging your Android app.\n" +
                "\n" +
                "A build type defines how a module is built, for example whether ProGuard is run.\n" +
                "A product flavour defines what is built, such as which resources are included in the build.\n" +
                "Gradle creates a build variant for every possible combination of your project’s product flavours and build types.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "9. Lifecycle of an Activity",
        answer = "OnCreate(): This is when the view is first created. This is normally where we create views, get data from bundles etc.\n" +
                "OnStart(): Called when the activity is becoming visible to the user. Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.\n" +
                "OnResume(): Called when the activity will start interacting with the user. At this point your activity is at the top of the activity stack, with user input going to it.\n" +
                "OnPause(): Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed.\n" +
                "OnStop(): Called when you are no longer visible to the user.\n" +
                "OnDestroy(): Called when the activity is finishing\n" +
                "OnRestart(): Called after your activity has been stopped, prior to it being started again",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "How to prevent the data from reloading and resetting when the screen is rotated?",
        answer = "The most basic approach would be to use a combination of ViewModels and onSaveInstanceState() . So how we do we that?\n" +
                "Basics of ViewModel: A ViewModel is LifeCycle-Aware. In other words, a ViewModel will not be destroyed if its owner is destroyed for a configuration change (e.g. rotation). The new instance of the owner will just re-connected to the existing ViewModel. So if you rotate an Activity three times, you have just created three different Activity instances, but you only have one ViewModel.\n" +
                "So the common practice is to store data in the ViewModel class (since it persists data during configuration changes) and use OnSaveInstanceState to store small amounts of UI data.\n" +
                "For instance, let’s say we have a search screen and the user has entered a query in the Edittext. This results in a list of items being displayed in the RecyclerView. Now if the screen is rotated, the ideal way to prevent resetting of data would be to store the list of search items in the ViewModel and the query text user has entered in the OnSaveInstanceState method of the activity.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Describe content providers",
        answer = "A ContentProvider provides data from one application to another, when requested. It manages access to a structured set of data. It provides mechanisms for defining data security. ContentProvider is the standard interface that connects data in one process with code running in another process.\n" +
                "\n" +
                "When you want to access data in a ContentProvider, you must instead use the ContentResolver object in your application’s Context to communicate with the provider as a client. The provider object receives data requests from clients, performs the requested action, and returns the results.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Difference between Serializable and Parcelable?",
        answer = "Serialization is the process of converting an object into a stream of bytes in order to store an object into memory, so that it can be recreated at a later time, while still keeping the object’s original state and data.\n" +
                "\n" +
                "How to disallow serialization? We can declare the variable as transient.\n" +
                "\n" +
                "Serializable is a standard Java interface. Parcelable is an Android specific interface where you implement the serialization yourself. It was created to be far more efficient than Serializable (The problem with this approach is that reflection is used and it is a slow process. This mechanism also tends to create a lot of temporary objects and cause quite a bit of garbage collection.).",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is the difference between fragments & activities. Explain the relationship between the two.",
        answer = "An Activity is an application component that provides a screen, with which users can interact in order to do something whereas a Fragment represents a behavior or a portion of user interface in an Activity (with its own lifecycle and input events, and which can be added or removed at will).",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "How to support different screen sizes?",
        answer = "Create a flexible layout — The best way to create a responsive layout for different screen sizes is to use ConstraintLayout as the base layout in your UI. ConstraintLayout allows you to specify the position and size for each view according to spatial relationships with other views in the layout. This way, all the views can move and stretch together as the screen size changes.\n" +
                "Create stretchable nine-patch bitmaps\n" +
                "Avoid hard-coded layout sizes — Use wrap_content or match_parent. Create alternative layouts — The app should provide alternative layouts to optimise the UI design for certain screen sizes. For eg: different UI for tablets\n" +
                "Use the smallest width qualifier — For example, you can create a layout named main_activity that’s optimised for handsets and tablets by creating different versions of the file in directories as follows:\n" +
                "res/layout/main_activity.xml — For handsets (smaller than 600dp available width)\n" +
                "res/layout-sw600dp/main_activity.xml — For 7” tablets (600dp wide and bigger).\n" +
                "The smallest width qualifier specifies the smallest of the screen’s two sides, regardless of the device’s current orientation, so it’s a simple way to specify the overall screen size available for your layout.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "S.O.L.I.D principles in software development?",
        answer = "The Single Responsibility Principle (SRP)\n" +
                "The Open-Closed Principle (OCP)\n" +
                "The Liskov Substitution Principle (LSP)\n" +
                "The Interface Segregation Principle (ISP)\n" +
                "The Dependency Inversion Principle (DIP)",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "What are memory leaks?",
        answer = "A memory leak happens when memory is allocated but never freed. This means the garbage collector is not able to take out the trash once we are done with the takeout. Initially, this might not be a problem. But imagine if you don’t take the trash out for 2 weeks! The house starts to smell right?\n" +
                "\n" +
                "Similarly, as the user keeps on using our app, the memory also keeps on increasing and if the memory leaks are not plugged, then the unused memory cannot be freed up by the Garbage Collection. So the memory of our app will constantly increase until no more memory can be allocated to our app, leading to OutOfMemoryError which ultimately crashes the app.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is a Higher-order function",
        answer = "A higher-order function is a function that takes functions as parameters, or returns a function.",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "When does Observable Start to Emit Items?",
        answer = "In Observable, there are two types: Cold and Hot Observables. Cold Observables will perform work and subsequently emit items only once is someone has subscribed, whereas Hot Observables will perform work and emit items regardless of observers or not.",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "What is API?",
        answer = "API - Application Programming Interface \n" +
                "An application programming interface (API) is a way for two or more computer programs to communicate with each other. It is a type of software interface, offering a service to other pieces of software.\n" +
                "One purpose of APIs is to hide the internal details of how a system works, exposing only those parts a programmer will find useful and keeping them consistent even if the internal details later change. An API may be custom-built for a particular pair of systems, or it may be a shared standard allowing interoperability among many systems.\n" +
                "\n" +
                "The term API is often used to refer to web APIs,[2] which allow communication between computers that are joined by the internet. There are also APIs for programming languages, software libraries, computer operating systems, and computer hardware. APIs originated in the 1940s, though the term did not emerge until the 1960s and 1970s. Recent developments in utilizing APIs have led to the rise in popularity of microservices, which are ultimately loosely coupled services accessed through public APIs.[3]\n" +
                "",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Clean Code",
        answer = "- Rule of Three/DRY \n" +
                "- Don't comment what happens \n" +
                "- Don't be afraid of long names \n" +
                "- YAGNI, SOLID \n" +
                "- Functions should be small \n" +
                "- No side effects:  Clean Code tells us to reduce side-effects. We should not make unexpected and hidden changes that are not obvious when looking at the function name.\n" +
                "\n" +
                "But what is the problem with side-effects at all? Code with side-effects is error-prone, hard to understand, hard to test, hard to parallelize (not thread-safe), not cacheable and can’t be evaluated lazily. We can avoid side-effects with concepts from functional programming. This basically means to write pure functions (= functions without side-effects)." +
                "\n" +
                "Prefer immutable properties \n",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "What are different design patterns used in Android?",
        answer = "Builder, Observer, Facade, Singleton, Factory, Adapter, Slot API.",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "What are design patterns?",
        answer = "Design patterns are tried and tested programming philosophies that provide logical and elegant approach to solving common development problems. Design Patterns are reusable solutions to common software problems. \n" +
                "",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "What types of design patterns are there?",
        answer = "Creational patterns: How you create objects.\n" +
                "Structural patterns: How you compose objects.\n" +
                "Behavioral patterns: How you coordinate object interactions. \n" +
                "Creational Patterns\n" +
                "\n" +
                "Builder\n" +
                "Dependency Injection\n" +
                "Singleton\n" +
                "Factory\n" +
                "Structural Patterns\n" +
                "\n" +
                "Adapter\n" +
                "Facade\n" +
                "Decorator\n" +
                "Composite\n" +
                "Behavioral Patterns\n" +
                "\n" +
                "Command\n" +
                "Observer\n" +
                "Strategy\n" +
                "Stat",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "Advantages of design patterns?",
        answer = "1. They create a common language between developers, 2. The added layers of abstraction that patterns provide make modifications and alterations to code that is already under development much easier, 3. Reduce the documentation as patterns describe themselves. " +
                "4. Code is overall cleaner and easier to understand, since the patterns are commonly used. \n" +
                "",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "Define Scheduler Explain Why RxJava Uses Schedulers?",
        answer = "Schedulers are used to switch execution to a different thread. RxJava is single-threaded by default, i.e., all operations are executed on a single thread. Also used as an abstraction overtime concept for time-sensitive operations such as delay(), buffer(), timeout(), window(), etc.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "How many times can onNext(), onError() and onComplete() be called?",
        answer = "onNext() can be called from zero to an infinite number of times, onError() can be called at maximum once per stream; similarly onComplete() is called at maximum once per stream.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Explain the Difference between Reactive Programming and Imperative Programming",
        answer = "In Reactive, observables will emit data and send it to subscribers, which means data streams are being pushed, whereas in Imperative, data streams are being pulled, i.e., the user explicitly requests data from the collection or any Database, etc.",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "List out some error handling operators in RxJava",
        answer = "We have two categories of such operators, one for side effects only and the other for handle error and continue. doOnError(…), onErrorReturn(…), onErrorResumeNext(…) are some of the error handling operators in RxJava.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Define Backpressure. How To Deal with Backpressure?",
        answer = "Backpressure is the inability of a subscriber to handle incoming events in time. Backpressure can occur when the producer of events is much faster than consumers; if not will error stream.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "What is Subject? List out 4 Types of Subject in RxJava",
        answer = "Subject means both subscriber and observer at the same time. With subjects in RxJava, users can transform cold observables to hot ones. They are also used to introduce some type of local and temporary caching of the stream. Also, help in transforming non-reactive code to reactive if the user does not find any operator for use case creation.\n" +
                "\n" +
                "Types of Subjects in RxJava:\n" +
                "\n" +
                "PublishSubject: It passes incoming events to all subscribers. New subscribers will receive events only from the point of subscription.\n" +
                "BehaviourSubject: Similar to publish subject, but each new subscriber will receive the latest value of the stream, i.e., the default value. Here, the default value of the stream provides a good user experience.\n" +
                "AsyncSubject: It emits only the last value of Observable, and that too only after the source observable completes emitting.\n" +
                "ReplaySubject: Each subscriber will receive all the events emitted by the source, regardless of at which point it is subscribed. If Observable emits too many items, they need to be in-memory cache.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Is RxJava following the “push” or “pull” pattern?",
        answer = "In RxJava new data is being “pushed” to observers.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "What’s the difference between a COLD and HOT observables?",
        answer = "Cold observables are created multiple times and each instance can be triggered on it’s own. Hot observables are like a “stream” of ongoing events – observers can come and go, but the stream is created ones and just goes on.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Can you transform a COLD observable to a HOT one and vice-versa?",
        answer = "One way to make a Cold observable Hot is by using publish().connect(). publish() converts the Cold observable to a ConnectableObservable, which pretty much behaves like a Hot one. Once triggered with the .connect() operator, it’ll publish events regardless if there are any subscribers.\n" +
                "\n" +
                "Another way to transform a Cold observable to a Hot one is by wrapping it with a Subject. The Subject subscribes to the Cold observable immediately and exposes itself as an Observable to future subscribers. Again, the work is performed regardless whether there are any subscribers … and on the other hand multiple subscribers to the Subject won’t trigger the initial work multiple times.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = " … and a HOT into to a COLD one?",
        answer = "The first way of transforming (or rather “masking”) a Hot observable to Cold is by using the defer() operator. It defers the creation of the Hot observable altogether, so each new subscriber will trigger the work again (feature of a Cold observable).\n" +
                "\n" +
                "Depending on the use-case the pattern mentioned above might be quite wasteful, so another strategy is using the replay().autoConnect(0) paradigm. The replay() operator will cache the values emitted by the Hot observable and re-emit them to future subscribers. autoConnect(0) returns an observable that can be triggered even when there are no subscribers to the underlaying Hot observable. The combination of both just replays cached values from the Hot observable as a Cold one.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "What’s the difference between observeOn() and subscribeOn()?",
        answer = "subscribeOn() denotes the Scheduler on which the source work will be performed on. Since there’s only one initial source of an Observable chain, it makes sense to only have one subscribeOn() operator.\n" +
                "\n" +
                "observeOn() denotes the Scheduler on which all downstream operations will be performed. In other words it changes the Scheduler for all operators after it. Since there can be many such operators, having multiple observeOn() operators in a single chain makes sense and works as expected.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Handling multiple errors",
        answer = "What will happen (in terms of error handling) if getAllUsers() emits a sequence of 10 userIds and getUserProfile(userId) emits error for every userId? Is the behaviour different between RxJava1 and RxJava2?" +
                "In RxJava1 the first propagated error will terminate the stream. All other started parallel streams (that’ll error out as well) are the so-called “undelivered exceptions”, which are just “swallowed” (printed in console by default). Difference with RxJava2 comes from the handling of these undeliverable exceptions – they’ll be sent to a global error handler (set via RxJavaPlugins.setErrorHandler()), where further handing can occur. If such error handler isn’t set, the exceptions are propagated upstream to the calling thread (e.g. will cause a crash of the app).",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Which RxJava construct you’ll use to represent an API call that needs to be called at some point in the future?",
        answer = "Usually API calls either return a response (onSuccess()) or error out (onError()), so Single is a great fit here.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "What if the API call is a “fire-and-forget” one?",
        answer = "Since we’re not interested in the response of the API call, a Completable is a good it. If we’re not interested in errors either, adding onErrorComplete() operator will achieve the “fire-and-forget” nature.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Do you know any RxJava constructs other than Observable? Describe them?",
        answer = "The main constructs are:\n" +
                "\n" +
                "Completable – maps an operation that either completes without returning a value (onComplete()) or errors out (onError(throwable)).\n" +
                "\n" +
                "Single – either returns a value (onSuccess(value)) or errors out (onError(throwable)).\n" +
                "\n" +
                "Maybe – has 3 options – returns a value successfully (onSuccess(value)), completes successfully without any value (onComplete()) or errors out (onError(throwable)).\n" +
                "\n" +
                "Observable – represents a stream of events that emits zero to many events (onNext(value)), then either completes (onComplete()) or errors out (onError(throwable)). It does NOT support backpressure (see #32).\n" +
                "\n" +
                "Flowable – like an Observable, however it DOES support backpressure.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "What’s a Subject in RxJava and what’s it used for?",
        answer = "A Subject is both a Subscriber and an Observer at the same time. My favourite analogy is a pipe – it can receive events from one end (because it’s a Subscriber) and let them through (“emit them”) via the other end because it’s an Observable. With Subjects one can transform Cold observables to Hot ones (see #5). Subjects also are one of the easiest ways to introduce some type of local, temporary caching of a stream. Last but not least – Subjects can help you transform your non-reactive code to reactive if you don’t find any of the Observable.create() operators useful for your use-case.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Are memory leaks an issue when using RxJava? How would you protect from such?",
        answer = "As a general good practice in programming, one must clean-up the used resources after they’re no longer needed. In the case of RxJava this means disposing your Disposables correctly. A common pattern is to keep adding all long-running operations from a screen in a CompositeDisposable and ensuring that’s clean-up when the screen is gone.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "Default Dispatcher - Coroutines.",
        answer = "If you don't set any dispatcher, the one chosen by default is Dispatchers.Default, which is designed to run CPU-intensive operations. It has a pool of threads with a size equal to the number of cores on the machine your code is running on (but not less than two). At least theoretically, this is the optimal number of threads, assuming you are using these threads efficiently, i.e., performing CPU-intensive calculations and not blocking them." +
                "",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Main Dispatcher - Coroutines.",
        answer = "Android and many other application frameworks have a concept of a main or UI thread, which is generally the most important thread. On Android, it is the only one that can be used to interact with the UI. Therefore, it needs to be used very often but also with great care. When the Main thread is blocked, the whole application is frozen. To run a coroutine on the Main thread, we use Dispatchers.Main." +
                "On Android, we typically use the Main dispatcher as the default one. If you use libraries that are suspending instead of blocking, and you don't do any complex calculations, in practice you can often use only Dispatchers.Main. If you do some CPU-intensive operations, you should run them on Dispatchers.Default. These two are enough for many applications, but what if you need to block the thread? For instance, if you need to perform long I/O operations (e.g., read big files) or if you need to use a library with blocking functions. You cannot block the Main thread, because your application would freeze. If you block your default dispatcher, you risk blocking all the threads in the thread pool, in which case you wouldn't be able to do any calculations. This is why we need a dispatcher for such a situation, and it is Dispatchers.IO." +
                "",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "IO Dispatcher - Coroutines.",
        answer = "Dispatchers.IO is designed to be used when we block threads with I/O operations, for instance when we read/write files, use Android shared preferences, or call blocking functions. The code below takes around 1 second because Dispatchers.IO allows more than 50 active threads at the same time." +
                "How does it work? Imagine an unlimited pool of threads. Initially it is empty, but as we need more threads, they are created and kept active until they are not used for some time. Such a pool exists, but it wouldn't be safe to use it directly. With too many active threads, the performance degrades in a slow but unlimited manner, eventually causing out-of-memory errors. This is why we create dispatchers that have a limited number of threads they can use at the same time. Dispatchers.Default is limited by the number of cores in your processor. The limit of Dispatchers.IO is 64 (or the number of cores if there are more)." +
                "",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Unconfined Dispatcher - Coroutines.",
        answer = "The last dispatcher we need to discuss is Dispatchers.Unconfined. This dispatcher is different from the previous one as it does not change any threads. When it is started, it runs on the thread on which it was started. If it is resumed, it runs on the thread that resumed it." +
                "",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Dispatchers - summary",
        answer = "Summary\n" +
                "Dispatchers determine on which thread or thread pool a coroutine will be running (starting and resuming). The most important options are:\n" +
                "\n" +
                "Dispatchers.Default, which we use for CPU-intensive operations;\n" +
                "Dispatchers.Main, which we use to access the Main thread on Android, Swing, or JavaFX;\n" +
                "Dispatchers.Main.immediate, which runs on the same thread as Dispatchers.Main but is not re-dispatched if it is not necessary;\n" +
                "Dispatchers.IO, which we use when we need to do some blocking operations;\n" +
                "Dispatchers.IO with limited parallelism or a custom dispatcher with a pool of threads, which we use when we might have a big number of blocking calls;\n" +
                "Dispatchers.Default or Dispatchers.IO with parallelism limited to 1, or a custom dispatcher with a single thread, which is used when we need to secure shared state modifications;\n" +
                "Dispatchers.Unconfined, which we use when we do not care where the coroutine will be executed.\n",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "lifecycleScope & viewModelScope.",
        answer = "These scopes take care of the cancellation themselves, and we don't have to think about it. lifecycleScope will live as long as hosting activity/fragment, viewModelScope will live as long as hosting view model.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Clean Architecture - what it is?",
        answer = "- Testability \n" +
                "- Separation of concenrs \n" +
                "- Creating abstractions \n" +
                "- 3 layers: data, domain, presentation",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "What’s init block in Kotlin?.",
        answer = "init is the initialiser block in Kotlin. It’s executed once the primary constructor is instantiated. If you invoke a secondary constructor, then it works after the primary one as it is composed in the chain.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Disposable Effect in Compose.",
        answer = "It is a side effect handler for the cases, where we need to do some kind of cleanup. It requires implementing onDispose at the end of the block.",
        category = DeprecatedCategory.Compose,
    ),
    Question(
        question = "Dependency Inversion Principle.",
        answer = "- High-level modules should not depend on low-level modules. Both should depend on abstractions.\n" +
                "- Abstractions should not depend on details, like Android libraries. Details should depend on abstractions.\n",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Composition over Inheritance.",
        answer = "- Universally accepted concept \n" +
                "- Kotlin encourages Composition \n" +
                "- Kotlin makes inheritance one extra step more verbose, which is enough to cause us to think twice \n" +
                "- Example: We have Dog and Cat. Dog barks and eats, Cat meows and eats. We see duplication, so we create open class Animal that eats and inherit from it. \n" +
                "- Then we create CleaningRobot and FeedingRobot. Both do drive, so we create open class Robot and inherit from it. \n" +
                "- And then manager comes and says: we need a CleaningRobotDog, that needs to bark, clean and drive, but should not eat. \n" +
                "We cannot inherit from two classes. We made a classification error. There is no way to know how the relationship of classes will evolve in a system. We need to keep our options open, and inheritance is a very rigid patern, hard to change. \n" +
                "It acts as a contract that is hard to break. The more we add features, the more we get tied to our initial inheritance tree. \n" +
                "Composition is the concept of a class referencing instances of other objects. \n" +
                "class A { val b = B() }. This is Composition. Class A is composed by blass B. class A : B() {} is Inheritance. \n" +
                "If you cen describe the relationship between 2 classes as IS-a relationsip, then this is a hint of inheritance. \n" +
                "If you can describe the relationship betweeen 2 classes as HAS-A RELATIONSHIP, then this sohuld be a composition. \n" +
                "However this rule is is often unreliable, as we are not always in the position to discern the proper relationship, especially early in development.",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Liskov Substitution Principle",
        answer = "- Rule of subtype substitution/replaceability. \n" +
                "- Just because we subclass a class, does not mean that our subclass is compatible. \n" +
                "- Subtypes must be substitutable for their base types. \n\n" +
                "Example: Rectangle and Square relationship. If we create rectangle class with changeX and changeY methods, then create square that inherits from it - it makes no sense! \n " +
                "There is no way of changingX and changingY independently in square. Even if you could implement it properly underneath, the method names would be misleading. So rectangle-square inheritance is a violation of the LSP. \n" +
                "Rules of LSP: \n" +
                "- Signature rules, properties rules, methods rules \n" +
                "- Statically typed languages help us in some of these rules by imposing compile time errors when broken. ",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Interface Segregation Principle",
        answer = "Definition: Client should not be forced to depend upon interfaces they do not use. \n" +
                "Example can be a Scoreboard interface, that also includes showGameStarted(), showGoalScored() method. If we create FootballScoreBoard that is fine, but what if we create TennisScoreBoard? \n" +
                "To avoid violating the principle, we can segregate the interface. We remove showGoalScored() from Scoreboard interface and create FootballScoreboardInteraface. Then for FootballScoreboard, we inherit both. \n" +
                "Rules: \n" +
                "Split interfaces into more specific interfaces. \n" +
                "Clients pick the parts they neeed, essentially composing functionality. \n" +
                "Classes should know as little as possible. \n" +
                "Clearer communication of what client does and doesn't. \n",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Functional (SAM) interfaces.",
        answer = "An interface with only one abstract method is called a functional interface, or a Single Abstract Method (SAM) interface. The functional interface can have several non-abstract members but only one abstract member.\n" +
                "\n" +
                "To declare a functional interface in Kotlin, use the fun modifier.\n" +
                "For functional interfaces, you can use SAM conversions that help make your code more concise and readable by using lambda expressions.\n" +
                "\n" +
                "Instead of creating a class that implements a functional interface manually, you can use a lambda expression. With a SAM conversion, Kotlin can convert any lambda expression whose signature matches the signature of the interface's single method into the code, which dynamically instantiates the interface implementation.\n" +
                "",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Type aliases.",
        answer = "Type aliases provide alternative names for existing types. If the type name is too long you can introduce a different shorter name and use the new one instead.\n" +
                "\n" +
                "It's useful to shorten long generic types. \n" +
                "Type aliases do not introduce new types. They are equivalent to the corresponding underlying types. When you add typealias Predicate<T> and use Predicate<Int> in your code, the Kotlin compiler always expands it to (Int) -> Boolean. Thus you can pass a variable of your type whenever a general function type is required and vice versa:",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "How to stay up-to-date with Android Development?",
        answer = "- Subscribe Android related channels (Phillip Lackner, Coding With Mitch, AndroidDevelopers) \n" +
                "- Medium Articles \n" +
                "- AndroidDevelopers official Medium page \n" +
                "- Libraries websites, like Koin. \n" +
                "- Android Developers Reddit. \n",
        category = DeprecatedCategory.Other,
    ),
    Question(
        question = "What is the difference between Unit and Nothing in Kotlin?.",
        answer = "Unit\n" +
                "\n" +
                "“It is the Java counterpart of void”.\n" +
                "\n" +
                "If a function does not return anything useful or it has nothing to give us back that interests us, we say it is implicitly returning Unit. And such functions may be performing something with a side effect. Either it is going to log/print something or do a manipulation without a return value.\n" +
                "\n" +
                "Nothing\n" +
                "\n" +
                "Nothing literally, means “no return to life, the game ends there ;)”. That is your function is never going to return from here, it will either throw an exception or go into an infinite loop.\n" +
                "\n" +
                "Also, any code that you write after a call to a function with return type Nothing will be marked as unreachable by the compiler.\n" +
                "\n" +
                "So to conclude :\n" +
                "\n" +
                "Unit says, “I will return but with no value that is of your interest”. And Nothing says “I will never return ”. Thus it helps you separately mention the nature of your function with more clarity.\n",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "What is a side effect?",
        answer = "It is something that escapes the context of Compose function. ",
        category = DeprecatedCategory.Compose,
    ),
    Question(
        question = "SharedFlow vs Channel.",
        answer = "- SharedFlow can cause events missing, because it keeps emitting them even if there are no observers. \n" +
                "- Channel can be collected as cold flow, so it would not emit anything unless there are subscribers. \n" +
                "- Channel can have only single observer, while SharedFlow can be shared.",
        category = DeprecatedCategory.StateAndSharedFlow,
    ),
    Question(
        question = "Open - Closed Principle.",
        answer = "Open for extension, close for modification. \n" +
                "- Write code so that you will be able to add new functionality without changing the existing code. \n" +
                "- In the current state, the rule is about polymorphism and abstractions. \n" +
                "- Our modules should depend on fixed abstractions that can be implemented by an infinite number of implementations. \n" +
                "- Closed for modification since they only depend on the fixed abstraction, but open for extension as we can always replace the current derivative with a new one. \n" +
                "Example: \n" +
                "- Player class with has his var health: Int and fun getPowerUp(powerUp: PowerUp).",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "What is Context?",
        answer = "Context is an interface to global information about application environment. This is an abstract class whose implementation is provided by the Android system. It allows access to application-specific resources and classes, as well as up-calls for application" +
                " level operations such as launching activities, broadcasting and receiving intents. \n" +
                "In simple words, Context is a bridge between the app and the system. \n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Dependency Injection",
        answer = "Dependency injection is like moving into a furnished apartment. Everything you need is already there. \n" +
                "In software terms, dependency injection has you provide any required objects to instantiate a new object. This new object doesn’t need to construct or customize the objects themselves. \n" +
                "In Android, you might find you need to access the same complex objects from various points in your app, such as a network client, image loader or SharedPreferences for local storage. You can inject these objects into your activities and fragments and access them right away. 'n" +
                "",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Facade.",
        answer = "The Facade pattern provides a higher-level interface that makes a set of other interfaces easier to use.",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "Decorator",
        answer = "The Decorator pattern dynamically attaches additional responsibilities to an object to extended its functionality at runtime.",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "Strategy.",
        answer = "You use a Strategy pattern when you have multiple objects of the same nature with different functionalities. \n" +
                "A TransportTypeStrategy interface has a common type for other strategies so it can be interchanged at runtime.\n" +
                "All the concrete classes conform to TransportTypeStrategy.\n" +
                "TravellingClient composes strategy and uses its functionalities inside the functions exposed to the client side.",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "State.",
        answer = "In the State pattern, the state of an object alters its behavior accordingly when the internal state of the object changes. \n" +
                "- So this is basically Compose and MVVM",
        category = DeprecatedCategory.DesignPatterns,
    ),
    Question(
        question = "Compose - Performance.",
        answer = "- Use remember{} block for calculations \n" +
                "- Defer reading state values as long as possible \n" +
                "- Use lazy layout keys \n",
        category = DeprecatedCategory.Compose,
    ),
    Question(
        question = "Inner class vs Nested class.",
        answer = "They both exist within other classes. The difference is that for the Inner class, it is able to access members of the outer class even if it is private.",
        category = DeprecatedCategory.Kotlin,
    ),
    Question(
        question = "Favourite Android 13 features.",
        answer = "- Apps will have to ask for permission to send notifications. Previously they could do it right after we installed them. \n" +
                "- Long press on notification and drag will open the app in a split-screen mode. \n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "runBlocking{}.",
        answer = "Runs a new coroutine and blocks the current thread interruptibly until its completion. This function should not be used from a coroutine. It is designed to bridge regular blocking code to libraries that are written in suspending style, to be used in main functions and in tests. \n" +
                "- Essentially is a CoroutineScope (we can call suspending functions from there), however it will block the entire thread until that job is complete. \n" +
                "- In a standard CoroutineScope we can launch many different coroutines on this thread and they can all execute simultanously. Blocking blocks everything else.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "Android application components.",
        answer = "App components are the essential building blocks of an Android app. Each component is an entry point through which the system or a user can enter your app. Some components depend on others. \n" +
                "There are four different types of app components:\n" +
                "\n" +
                "Activities\n" +
                "Services\n" +
                "Broadcast receivers\n" +
                "Content providers\n" +
                "Each type serves a distinct purpose and has a distinct lifecycle that defines how the component is created and destroyed. The following sections describe the four types of app components. \n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Activities.",
        answer = "An activity is the entry point for interacting with the user. It represents a single screen with a user interface. For example, an email app might have one activity that shows a list of new emails, another activity to compose an email, and another activity for reading emails. Although the activities work together to form a cohesive user experience in the email app, each one is independent of the others. As such, a different app can start any one of these activities if the email app allows it. For example, a camera app can start the activity in the email app that composes new mail to allow the user to share a picture. An activity facilitates the following key interactions between system and app:\n" +
                "Keeping track of what the user currently cares about (what is on screen) to ensure that the system keeps running the process that is hosting the activity.\n" +
                "Knowing that previously used processes contain things the user may return to (stopped activities), and thus more highly prioritize keeping those processes around.\n" +
                "Helping the app handle having its process killed so the user can return to activities with their previous state restored.\n" +
                "Providing a way for apps to implement user flows between each other, and for the system to coordinate these flows. (The most classic example here being share.)\n" +
                "You implement an activity as a subclass of the Activity class. For more information about the Activity class, see the Activities developer guide. \n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Services.",
        answer = "Service is an application component that facilitates an application to run in the background in order to perform long-running operations without user interaction. A service can run continuously in the background even if the application is closed or even after the user switches to another application.\n" +
                "\n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Broadcast receivers.",
        answer = "A broadcast receiver is a component that enables the system to deliver events to the app outside of a regular user flow, allowing the app to respond to system-wide broadcast announcements. Because broadcast receivers are another well-defined entry into the app, the system can deliver broadcasts even to apps that aren't currently running. So, for example, an app can schedule an alarm to post a notification to tell the user about an upcoming event... and by delivering that alarm to a BroadcastReceiver of the app, there is no need for the app to remain running until the alarm goes off. Many broadcasts originate from the system—for example, a broadcast announcing that the screen has turned off, the battery is low, or a picture was captured. Apps can also initiate broadcasts—for example, to let other apps know that some data has been downloaded to the device and is available for them to use. Although broadcast receivers don't display a user interface, they may create a status bar notification to alert the user when a broadcast event occurs. More commonly, though, a broadcast receiver is just a gateway to other components and is intended to do a very minimal amount of work. For instance, it might schedule a JobService to perform some work based on the event with JobScheduler\n" +
                "A broadcast receiver is implemented as a subclass of BroadcastReceiver and each broadcast is delivered as an Intent object. For more information, see the BroadcastReceiver class. \n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Content providers.",
        answer = "A content provider manages a shared set of app data that you can store in the file system, in a SQLite database, on the web, or on any other persistent storage location that your app can access. Through the content provider, other apps can query or modify the data if the content provider allows it. For example, the Android system provides a content provider that manages the user's contact information. As such, any app with the proper permissions can query the content provider, such as ContactsContract.Data, to read and write information about a particular person. It is tempting to think of a content provider as an abstraction on a database, because there is a lot of API and support built in to them for that common case. However, they have a different core purpose from a system-design perspective. To the system, a content provider is an entry point into an app for publishing named data items, identified by a URI scheme. Thus an app can decide how it wants to map the data it contains to a URI namespace, handing out those URIs to other entities which can in turn use them to access the data. There are a few particular things this allows the system to do in managing an app:\n" +
                "Assigning a URI doesn't require that the app remain running, so URIs can persist after their owning apps have exited. The system only needs to make sure that an owning app is still running when it has to retrieve the app's data from the corresponding URI.\n" +
                "These URIs also provide an important fine-grained security model. For example, an app can place the URI for an image it has on the clipboard, but leave its content provider locked up so that other apps cannot freely access it. When a second app attempts to access that URI on the clipboard, the system can allow that app to access the data via a temporary URI permission grant so that it is allowed to access the data only behind that URI, but nothing else in the second app.\n" +
                "Content providers are also useful for reading and writing data that is private to your app and not shared.\n" +
                "\n" +
                "A content provider is implemented as a subclass of ContentProvider and must implement a standard set of APIs that enable other apps to perform transactions. For more information, see the Content Providers developer guide.\n" +
                "\n" +
                "A unique aspect of the Android system design is that any app can start another app’s component. For example, if you want the user to capture a photo with the device camera, there's probably another app that does that and your app can use it instead of developing an activity to capture a photo yourself. You don't need to incorporate or even link to the code from the camera app. Instead, you can simply start the activity in the camera app that captures a photo. When complete, the photo is even returned to your app so you can use it. To the user, it seems as if the camera is actually a part of your app.\n" +
                "\n" +
                "When the system starts a component, it starts the process for that app if it's not already running and instantiates the classes needed for the component. For example, if your app starts the activity in the camera app that captures a photo, that activity runs in the process that belongs to the camera app, not in your app's process. Therefore, unlike apps on most other systems, Android apps don't have a single entry point (there's no main() function).\n" +
                "\n" +
                "Because the system runs each app in a separate process with file permissions that restrict access to other apps, your app cannot directly activate a component from another app. However, the Android system can. To activate a component in another app, deliver a message to the system that specifies your intent to start a particular component. The system then activates the component for you.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "AndroidManifest file.",
        answer = "Before the Android system can start an app component, the system must know that the component exists by reading the app's manifest file, AndroidManifest.xml. Your app must declare all its components in this file, which must be at the root of the app project directory.\n" +
                "\n" +
                "The manifest does a number of things in addition to declaring the app's components, such as the following:\n" +
                "\n" +
                "Identifies any user permissions the app requires, such as Internet access or read-access to the user's contacts.\n" +
                "Declares the minimum API Level required by the app, based on which APIs the app uses.\n" +
                "Declares hardware and software features used or required by the app, such as a camera, bluetooth services, or a multitouch screen.\n" +
                "Declares API libraries the app needs to be linked against (other than the Android framework APIs), such as the Google Maps library.\n" +
                "The AndroidManifest.xml file contains information regarding the application that the Android system must know before the codes can be executed.\n" +
                "This file is essential in every Android application.\n" +
                "It is declared in the root directory.\n" +
                "This file performs several tasks such as:\n" +
                "Providing a unique name to the java package.\n" +
                "Describing various components of the application such as activity, services, and many more.\n" +
                "Defining the classes which will implement these components.\n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Android application components.",
        answer = "Services − It will be used to perform background functionalities.\n" +
                "Activities - It is a single screen that represents GUI(Graphical User Interface) with which users can interact in order to do something like dial the phone, view email, etc.\n" +
                "Broadcast receivers - Broadcast receiver is a mechanism used for listening to system-level events like listening for incoming calls, SMS, etc. by the host application.\n" +
                "Content providers − This will share the data between various applications.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Data and file storage overview.",
        answer = "Android uses a file system that's similar to disk-based file systems on other platforms. The system provides several options for you to save your app data:\n" +
                "\n" +
                "App-specific storage: Store files that are meant for your app's use only, either in dedicated directories within an internal storage volume or different dedicated directories within external storage. Use the directories within internal storage to save sensitive information that other apps shouldn't access.\n" +
                "Shared storage: Store files that your app intends to share with other apps, including media, documents, and other files.\n" +
                "Preferences: Store private, primitive data in key-value pairs.\n" +
                "Databases: Store structured data in a private database using the Room persistence library.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Explain the difference between Implicit and Explicit Intent.",
        answer = "An Explicit Intent is where you inform the system about which activity should handle this intent. Here target component is defined directly in the intent. \n" +
                "An Implicit Intent permits you to declare the action you want to carry out. Further, the Android system will check which components are registered to handle that specific action based on intent data. Here target component is not defined in the intent. \n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What are broadcast receivers? How is it implemented?.",
        answer = "A broadcast receiver is a mechanism used for listening to system-level events like listening for incoming calls, SMS, etc. by the host application. It is implemented as a subclass of BroadcastReceiver class and each message is broadcasted as an intent object.\n" +
                "\n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is the content provider? How it is implemented?",
        answer = "Content provider is one of the primary building blocks of Android applications, which manages access to a central repository of data. It acts as a standard interface that connects data in one process with code running in another process. So it can be used to share the data between different applications.\n" +
                "\n" +
                "They are responsible for encapsulating the data and providing mechanisms for defining data security. It is implemented as a subclass of ContentProviderclass and must implement a set of APIs that will enable other applications to perform transactions.",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is the difference between compileSdkVersion and targetSdkVersion?",
        answer = "compileSdkVersion:\n" +
                "\n" +
                "The compileSdkVersion is the version of API the application is compiled against. You can use Android API features involved in that version of the API (as well as all previous versions).\n" +
                "For example, if you try and use API 15 features but set compileSdkVersion to 14, you will get a compilation error. If you set compileSdkVersion to 15 you can still run the app on an API 14 device as long as your app’s execution paths do not attempt to invoke any APIs specific to API 15.\n" +
                "targetSdkVersion:\n" +
                "\n" +
                "The targetSdkVersion indicates that you have tested your app on (presumably up to and including) the version you specify. This is like a certification or sign-off you are giving the Android OS as a hint to how it should handle your application in terms of OS features.\n" +
                "For example, setting the targetSdkVersion value to “11” or higher permits the system to apply a new default theme (Holo) to the application when running on Android 3.0 or higher. It also disables screen compatibility mode when running on larger screens (because support for API level 11 implicitly supports larger screens).\n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is onSavedInstanceState() and onRestoreInstanceState() in activity?",
        answer = "onSavedInstanceState() - This method is used to store data before pausing the activity.\n" +
                "onRestoreInstanceState() - This method is used to recover the saved state of an activity when the activity is recreated after destruction. So, the onRestoreInstanceState() receive the bundle that contains the instance state information.\n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is an Intent?.",
        answer = "An Intent is a messaging object you can use to request an action from another app component.\n" +
                "Let’s look upon the informal way of defining Intents. You can think of Intents as a messaging service that is used to communicate between various components of the Android application. For example, if you want to send some message from Delhi to Mumbai using the Post Office facility then you can do so by buying an Envelope and then pass the message in the Envelope and send the message to the desired location." +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Use cases of Intents.",
        answer = "We know that Intents are used to have communication between various components of the Android Application. These communications can be done in various ways but in general, there are three use cases of the Intents:\n" +
                "\n" +
                "1. Starting an Activity\n" +
                "\n" +
                "You can use the Intents to start a particular activity by using Intents. For example, if you are having two activities namely LoginActivity and MainActivity then, you can start the MainActivity by clicking the login button present on the LoginActivity. By using the startActivity(), you can start the desired Activity using the Intents.\n" +
                "\n" +
                "2. Starting a Service\n" +
                "\n" +
                "You can think of service as a component that will perform a particular task in the background. So, you can use Intents to start a service also. For API level 21 or higher, you can use JobScheduler to start a service. For API level lower than 21, you can use the Service class to achieve the same.\n" +
                "\n" +
                "3. Delivering a broadcast\n" +
                "\n" +
                "A broadcast is a message that is received by the application from the system. A very common example of a broadcast can be Device Charging message. So, you can use a broadcast to send some kind of message to the applications present in the device. This is done by passing an Intent to sendBroadcast() or to sendOrderedBroadcast().",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Information present in Intent",
        answer = "So, we have seen that an Activity or an action can be called by sing Explicit Intents or by using some Implicit Intents. But the question that arises here is that how the Android System come to know that a particular Activity or Action is to be called? This is done by reading the information that is present in the Intent. The Android System reads the information present in the Intent and based on this information, the Android System decides which Activity is to be launched. So, some of the basic information that an Intent contains are:\n" +
                "\n" +
                "Action: An action is a string that specifies the action to be performed by a particular Activity. For example, you can use the ACTION_VIEW with startActivity() when your application contains some information like images that can be shown to the user. Another action that can be performed is ACTION_SEND, which is used to share some data with another application like in Email applications.\n" +
                "Data: While creating an Intent, you can pass the data and the type of data on which the action is to be performed by the Android System with the help of Intents. The URI object is used to reference the data that will be used to perform some action on it. For example, if you want to edit some data then you have to pass the URI of the data in your ACTION_EDIT action.\n" +
                "Category: Category is used in case of Explicit Intents where you need to specify the type of application that will be used to perform a particular action. For example, if you want to send some data then only data sending applications should be made available for choice to the users. You can specify a category with the help of addCategory(). Any number of categories can be added to the Intent.\n" +
                "Component Name: The component name is the name of the component that is to be started. You can set the component name by using setComponent() or setClass() or with the Intent Constructor.\n" +
                "Extras: You can add extra data to an Intent in the form of key-value pairs and this extra information can be passed from one Activity to the other. putExtra() is used to add some extra data to the Intents and this method accepts two parameters i.e. the key and its corresponding value. \n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What’s Android KTX?",
        answer = "KTX library is the only one among the foundation components which was introduced for the first time with the release of the Jetpack. Android KTX is a collection of Kotlin extensions that are designed to facilitate developers to remove boilerplate code as well as to write concise code while developing android applications with Kotlin language. Here KTX in the name stands for Kotlin Extensions. \n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = " ViewModel in Android.",
        answer = "ViewModel is one of the most critical classes of the Android Jetpack Architecture Component that support data for UI components. Its purpose is to hold and manage the UI-related data. Moreover, its main function is to maintain the integrity and allows data to be serviced during configuration changes like screen rotations. Any kind of configuration change in Android devices tends to recreate the whole activity of the application. It means the data will be lost if it has not been saved and restored properly from the activity which was destroyed. To avoid these issues, it is recommended to store all UI data in the ViewModel instead of an activity. \n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "What is Kotlin Coroutine on Android?.",
        answer = "The Kotlin team defines coroutines as “lightweight threads”. They are sort of tasks that the actual threads can execute. Kotlin coroutines introduce a new style of concurrency that can be used on Android to simplify async code. The official documentation says that coroutines are lightweight threads. By lightweight, it means that creating coroutines doesn’t allocate new threads. Instead, they use predefined thread pools and smart scheduling for the purpose of which task to execute next and which tasks later. \n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "onTrimMemory()",
        answer = "As described in Overview of Android Memory Management, Android can reclaim memory from your app in several ways or kill your app entirely if necessary to free up memory for critical tasks. To further help balance the system memory and avoid the system's need to kill your app process, you can implement the ComponentCallbacks2 interface in your Activity classes. The provided onTrimMemory() callback method allows your app to listen for memory related events when your app is in either the foreground or the background, and then release objects in response to app lifecycle or system events that indicate the system needs to reclaim memory.\n" +
                "\n" +
                "For example, you can implement the onTrimMemory() callback to respond to different memory-related events as shown here: \n",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "App memory management - good/bad practices.",
        answer = "Some Android features, Java classes, and code constructs tend to use more memory than others. You can minimize how much memory your app uses by choosing more efficient alternatives in your code. \n" +
                "- Use services sparingly\n" +
                "Leaving a service running when it’s not needed is one of the worst memory-management mistakes an Android app can make. If your app needs a service to perform work in the background, do not keep it running unless it needs to run a job. Remember to stop your service when it has completed its task. Otherwise, you can inadvertently cause a memory leak. \n" +
                "- Use optimized data containers\n" +
                "Some of the classes provided by the programming language are not optimized for use on mobile devices. For example, the generic HashMap implementation can be quite memory inefficient because it needs a separate entry object for every mapping. \n" +
                "- Be careful with code abstractions\n" +
                "Developers often use abstractions simply as a good programming practice, because abstractions can improve code flexibility and maintenance. However, abstractions come at a significant cost: generally they require a fair amount more code that needs to be executed, requiring more time and more RAM for that code to be mapped into memory. So if your abstractions aren't supplying a significant benefit, you should avoid them. \n" +
                "- Remove memory-intensive resources and libraries\n" +
                "Some resources and libraries within your code can gobble up memory without you knowing it. Overall size of your app, including third-party libraries or embedded resources, can affect how much memory your app consumes. You can improve your app's memory consumption by removing any redundant, unnecessary, or bloated components, resources, or libraries from your code. \n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "Improving battery usage in an android application:\n",
        answer = "Reduce network calls as much as you can: Cache your data and retrieve it from the cache when required next time.\n" +
                "Avoid wake lock as much as possible: A wake lock is a mechanism to indicate that your application needs to have the device stay on.\n" +
                "Use AlarmManager carefully: Wrong use of AlarmManager can easily drain the battery.\n" +
                "Batch the network calls: You should batch the network calls if possible so that you can prevent the device from waking every second.\n" +
                "A different logic for Mobile Data and Wifi: You should write different logic for mobile data and wifi as one logic may be optimized for mobile data and others may be optimized for wifi.\n" +
                "Check all background processes: You should check all the background processes.\n" +
                "Use GPS carefully: Do not use it frequently, use it only when actually required.\n" +
                "Use WorkManager: As the official documentation says, WorkManager is an API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or the device restarts. The WorkManager API is a suitable and recommended replacement for all previous Android background scheduling APIs, including FirebaseJobDispatcher, GcmNetworkManager, and Job Scheduler. WorkManager incorporates the features of its predecessors in a modern, consistent API that works back to API level 14 while also being conscious of battery life.\n" +
                "App Standby Buckets: In earlier versions of Android, Google introduced features like Doze and App Standby modes which saves users’ battery. Android Pie (Version 9 API level 28) introduced a new feature for better battery(power) management called App Standby Buckets. Each android application is now placed into one of the priority buckets based on the app’s usage patterns like how recently & how frequently the user has used the application. The android system then limits the app’s resources based on the bucket app is currently residing in. Learn more about it here.\n" +
                "",
        category = DeprecatedCategory.Android,
    ),
    Question(
        question = "How can you fix a broken commit?",
        answer = "In order to fix any broken commit, use the command “git commit --amend”. When you run this command, you can fix the broken commit message in the editor. \n",
        category = DeprecatedCategory.Git,
    ),
    Question(
        question = " In Git how do you revert a commit that has already been pushed and made public?",
        answer = "There can be two approaches to tackle this question and make sure that you include both because any of the below options can be used depending on the situation:\n" +
                "\n" +
                "Remove or fix the bad file in a new commit and then push it to the remote repository. This is the most obvious way to fix an error. Once you have made necessary changes to the file, then commit it to the remote repository using the command: git commit -m “commit message”\n" +
                "\n" +
                "Also, you can create a new commit that undoes all changes that were made in the bad commit. To do this use the command\n" +
                "\n" +
                "git revert <name of bad commit>",
        category = DeprecatedCategory.Git,
    ),
    Question(
        question = "Compose support for different screen sizes.",
        answer = "Avoid using physical, hardware values for making layout decisions. It might be tempting to make decisions based on a fixed tangible value (Is the device a tablet? Does the physical screen have a certain aspect ratio?), but the answers to these questions may not be useful for determining the space your UI can work with. \n" +
                "\n Instead, you should make decisions based on the actual portion of the screen that is allocated to your app, such as the current window metrics provided by the Jetpack WindowManager library. \n" +
                "Once you are observing the relevant space available for your app, it is helpful to convert the raw size into a meaningful size class, as described in Window Size Classes. This groups sizes into standard size buckets, which are breakpoints that are designed to balance simplicity with the flexibility to optimize your app for most unique cases. \n" +
                "If you want to change where or how content is displayed, you can use a collection of modifiers or a custom layout to make the layout responsive. This could be as simple as having some child fill all of the available space, or laying out children with multiple columns if there is enough room.\n" +
                "\n" +
                "If you want to change what you show, you can use BoxWithConstraints as a more powerful alternative. This composable provides measurement constraints that you can use to call different composables based on the space that is available. However, this comes at some expense, as BoxWithConstraints defers composition until the Layout phase, when these constraints are known, causing more work to be performed during layout. \n" +
                "",
        category = DeprecatedCategory.Compose,
    ),
    Question(
        question = "Scope Cancellation vs Scope's Children Cancellation",
        answer = "When we cancel children, we can go back and start new coroutines from the same scope again. When we cancel the whole scope it is not possible.",
        category = DeprecatedCategory.Coroutines,
    ),
    Question(
        question = "REST API principles",
        answer = "In order for an API to be considered RESTful, it has to conform to these criteria:\n" +
                "\n" +
                "A client-server architecture made up of clients, servers, and resources, with requests managed through HTTP.\n" +
                "Stateless client-server communication, meaning no client information is stored between get requests and each request is separate and unconnected.\n" +
                "Cacheable data that streamlines client-server interactions.\n" +
                "A uniform interface between components so that information is transferred in a standard form. This requires that:\n" +
                "resources requested are identifiable and separate from the representations sent to the client.\n" +
                "resources can be manipulated by the client via the representation they receive because the representation contains enough information to do so.\n" +
                "self-descriptive messages returned to the client have enough information to describe how the client should process it.\n" +
                "hypertext/hypermedia is available, meaning that after accessing a resource the client should be able to use hyperlinks to find all other currently available actions they can take.\n" +
                "A layered system that organizes each type of server (those responsible for security, load-balancing, etc.) involved the retrieval of requested information into hierarchies, invisible to the client.\n" +
                "Code-on-demand (optional): the ability to send executable code from the server to the client when requested, extending client functionality. ",
        category = DeprecatedCategory.ProgrammingParadigms,
    ),
    Question(
        question = "Using CoroutineExceptionHandler, SupervisorScope, SupervisorJob",
        answer = """If inside one CoroutineScope there would be two coroutines and one would be failing, the whole scope would fail and be cancelled after the first coroutine fails. The second one would not finish 
However if we launch both coroutines in a supervisorScope, we can catch the exception without cancelling the whole scope and the second one can finish. 
""",
        category = DeprecatedCategory.Coroutines,
    ),
)