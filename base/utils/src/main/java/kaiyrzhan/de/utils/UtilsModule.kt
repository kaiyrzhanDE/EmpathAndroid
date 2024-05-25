package kaiyrzhan.de.utils

import kaiyrzhan.de.utils.logger.androidLogger
import kaiyrzhan.de.utils.logger.Logger
import org.koin.dsl.bind
import org.koin.dsl.module

val utilsModule = module {
    factory { androidLogger() } bind Logger::class
}