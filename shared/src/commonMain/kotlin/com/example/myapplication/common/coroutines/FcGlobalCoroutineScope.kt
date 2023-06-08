package co.touchlab.kampkit.coroutines

import co.apoplawski96.kti.common.coroutines.DispatcherProvider
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
