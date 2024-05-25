package kaiyrzhan.de.auth

import kaiyrzhan.de.auth.data.mapper.CreateUserMapper
import kaiyrzhan.de.auth.data.mapper.LoginMapper
import kaiyrzhan.de.auth.data.mapper.ResetPasswordMapper
import kaiyrzhan.de.auth.data.mapper.UserMapper
import kaiyrzhan.de.auth.data.mapper.VerifyEmailMapper
import kaiyrzhan.de.auth.data.network.AuthApi
import kaiyrzhan.de.auth.data.repository.AuthRepositoryImpl
import kaiyrzhan.de.auth.domain.repository.AuthRepository
import kaiyrzhan.de.auth.domain.usecase.LoginUseCase
import kaiyrzhan.de.auth.domain.usecase.RegisterUserUseCase
import kaiyrzhan.de.core.token.mapper.TokenMapper
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val authApiModule = module(createdAtStart = true) {
    factory { CreateUserMapper()  } bind CreateUserMapper::class
    factory { LoginMapper()  } bind LoginMapper::class
    factory { ResetPasswordMapper()  } bind ResetPasswordMapper::class
    factory { UserMapper()  } bind UserMapper::class
    factory { VerifyEmailMapper()  } bind VerifyEmailMapper::class

    single { get<Retrofit>().create(AuthApi::class.java) } bind AuthApi::class

    single{
        AuthRepositoryImpl(
            api = get<AuthApi>(),
            createUserMapper = get<CreateUserMapper>(),
            userMapper = get<UserMapper>(),
            tokenMapper = get<TokenMapper>(),
            resetPasswordMapper = get<ResetPasswordMapper>(),
            verifyEmailMapper = get<VerifyEmailMapper>()
        )
    } bind AuthRepository::class

    single { LoginUseCase(repository = get()) }
    single { RegisterUserUseCase(repository = get()) }
}