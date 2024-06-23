package kaiyrzhan.de.auth.data.repository

import kaiyrzhan.de.auth.data.mapper.*
import kaiyrzhan.de.auth.data.network.AuthApi
import kaiyrzhan.de.auth.domain.models.*
import kaiyrzhan.de.auth.domain.repository.AuthRepository
import kaiyrzhan.de.core.network.RequestResult
import kaiyrzhan.de.core.network.map
import kaiyrzhan.de.core.network.toRequestResult
import kaiyrzhan.de.core.token.mapper.TokenMapper
import kaiyrzhan.de.core.token.models.Token
import okhttp3.MultipartBody

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val createUserMapper: CreateUserMapper,
    private val userMapper: UserMapper,
    private val tokenMapper: TokenMapper,
    private val resetPasswordMapper: ResetPasswordMapper,
    private val verifyEmailMapper: VerifyEmailMapper,
) : AuthRepository {

    override suspend fun registerUser(domain: CreateUser): RequestResult<User> {
        val request = api.registerUser(createUserMapper.toData(domain)).toRequestResult()
        return request.map { userMapper.toDomain(it) }
    }

    override suspend fun updateUser(domain: User): RequestResult<User> {
        val request = api.updateUser(userMapper.toData(domain)).toRequestResult()
        return request.map { userMapper.toDomain(it) }
    }

    override suspend fun loginUser(domain: Login): RequestResult<Token> {
        val body = mutableListOf<MultipartBody.Part>()
        body.add(MultipartBody.Part.createFormData("email", domain.email))
        body.add(MultipartBody.Part.createFormData("password", domain.password))
        val request = api.loginUser(body).toRequestResult()
        return request.map { tokenMapper.toDomain(it) }
    }

    override suspend fun resetPassword(domain: ResetPassword): RequestResult<User> {
        val request = api.resetPassword(resetPasswordMapper.toData(domain)).toRequestResult()
        return request.map { userMapper.toDomain(it) }
    }

    override suspend fun sendEmailCode(email: String): RequestResult<Any> {
        return api.sendEmailCode(email).toRequestResult()
    }

    override suspend fun verifyEmail(domain: VerifyEmail): RequestResult<Any> {
        return api.verifyEmail(verifyEmailMapper.toData(domain)).toRequestResult()
    }

    override suspend fun getUser(): RequestResult<User> {
        val request = api.getUser().toRequestResult()
        return request.map { userMapper.toDomain(it) }
    }

}