package kaiyrzhan.de.auth.data.mapper

import kaiyrzhan.de.auth.data.models.LoginRequest
import kaiyrzhan.de.auth.domain.models.Login
import kaiyrzhan.de.core.preview.Mapper

class LoginMapper : Mapper<Login, LoginRequest> {

    override fun toData(domain: Login): LoginRequest {
        return LoginRequest(
            username = domain.email,
            password = domain.password,
        )
    }

    override fun toDomain(data: LoginRequest): Login {
        return Login(
            email = data.username,
            password = data.password,
        )
    }

}