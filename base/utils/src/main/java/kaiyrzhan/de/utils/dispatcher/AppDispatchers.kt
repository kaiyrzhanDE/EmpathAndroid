package kaiyrzhan.de.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.bind
import org.koin.dsl.module

data class AppDispatchers (
    val main: CoroutineDispatcher = Dispatchers.Main,
    val io: CoroutineDispatcher = Dispatchers.IO,
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
    val default: CoroutineDispatcher = Dispatchers.Default,
)

val dispatchersModule = module {
    single { AppDispatchers() } bind AppDispatchers::class
}