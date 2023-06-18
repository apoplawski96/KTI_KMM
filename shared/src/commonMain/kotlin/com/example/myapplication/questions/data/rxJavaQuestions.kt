package co.apoplawski96.kti.questions.data

import com.example.myapplication.questions.model.DeprecatedCategory
import co.apoplawski96.kti.questions.model.Question

val questionsRxJava = listOf(
    Question(
        question = "What does subscribeOn(scheduler) do?.",
        answer = "It is used on Observables, it is telling that everybody who wants to see the results of the observable to subscribe on this thread. \n" +
                "Asynchronously subscribes Observers to this ObservableSource on the specified Scheduler.",
        category = DeprecatedCategory.Rx,
    ),
    Question(
        question = "RxJava Operators - Defer",
        answer = "do not create the Observable until the observer subscribes, and create a fresh Observable for each observer",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Interval ",
        answer = "Interval — create an Observable that emits a sequence of integers spaced by a particular time interval",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Timer ",
        answer = "Timer — create an Observable that emits a single item after a given delay",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Buffer",
        answer = "Buffer — periodically gather items from an Observable into bundles and emit these bundles rather than emitting the items one at a time",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - FlatMap",
        answer = "FlatMap — transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable. " +
                "\n FlatMap does not maintain the order.",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Scan",
        answer = "Scan — apply a function to each item emitted by an Observable, sequentially, and emit each successive value\n",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Debounce ",
        answer = "Debounce — only emit an item from an Observable if a particular timespan has passed without it emitting another item",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Distinct",
        answer = "Distinct — suppress duplicate items emitted by an Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - IgnoreElements ",
        answer = "IgnoreElements — do not emit any items from an Observable but mirror its termination notification",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Last",
        answer = "Last — emit only the last item emitted by an Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Sample",
        answer = "Sample — emit the most recent item emitted by an Observable within periodic time intervals",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Take, TakeLast",
        answer = "Take — emit only the first n items emitted by an Observable\n" +
                "TakeLast — emit only the last n items emitted by an Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - CombineLatest",
        answer = "CombineLatest — when an item is emitted by either of two Observables, combine the latest item emitted by each Observable via a specified function and emit items based on the results of this function\n" +
                "",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Join",
        answer = "Join — combine items emitted by two Observables whenever an item from one Observable is emitted during a time window defined according to an item emitted by the other Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Merge",
        answer = "Merge — combine multiple Observables into one by merging their emissions \n",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - StartWith",
        answer = "StartWith — emit a specified sequence of items before beginning to emit the items from the source Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Switch",
        answer = "Switch — convert an Observable that emits Observables into a single Observable that emits the items emitted by the most-recently-emitted of those Observables",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Zip",
        answer = "Zip — combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - ObserveOn",
        answer = "ObserveOn — specify the scheduler on which an observer will observe this Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Subscribe",
        answer = "Subscribe — operate upon the emissions and notifications from an Observable",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Map",
        answer = "transform the items emitted by an Observable by applying a function to each item",
        category = DeprecatedCategory.Rx
    ),
    Question(
        question = "RxJava Operators - Window",
        answer = "Window — periodically subdivide items from an Observable into Observable windows and emit these windows rather than emitting the items one at a time",
        category = DeprecatedCategory.Rx
    ),
)