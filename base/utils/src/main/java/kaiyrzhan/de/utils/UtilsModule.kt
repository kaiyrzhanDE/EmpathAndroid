package kaiyrzhan.de.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kaiyrzhan.de.utils.logger.androidLogger
import kaiyrzhan.de.utils.logger.Logger

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun provideLogger(): Logger = androidLogger()
}