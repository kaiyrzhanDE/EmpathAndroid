package kaiyrzhan.de.auth.domain.repository

import kaiyrzhan.de.auth.domain.models.*
import kaiyrzhan.de.core.network.RequestResult
import kaiyrzhan.de.core.token.models.Token

interface AuthRepository {

    suspend fun registerUser(domain: CreateUser): RequestResult<User>

    suspend fun updateUser(domain: User): RequestResult<User>

    suspend fun loginUser(domain: Login): RequestResult<Token>

    suspend fun resetPassword(domain: ResetPassword): RequestResult<User>

    suspend fun sendEmailCode(email: String): RequestResult<Any>

    suspend fun verifyEmail(domain: VerifyEmail): RequestResult<Any>

    suspend fun getUser(): RequestResult<User>

}