package com.example.myapplication.common.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface FcGlobalCoroutineScope : CoroutineScope

internal class FcGlobalCoroutineScopeImpl internal constructor(
    dispatcherProvider: DispatcherProvider,
) : FcGlobalCoroutineScope {

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + dispatcherProvider.default + CoroutineExceptionHandler { _, _ -> }
}
