package kaiyrzhan.de.auth.data.mapper

import kaiyrzhan.de.auth.data.models.CreateUserRequest
import kaiyrzhan.de.auth.domain.models.CreateUser
import kaiyrzhan.de.core.preview.Mapper

class CreateUserMapper : Mapper<CreateUser, CreateUserRequest> {

    override fun toDomain(data: CreateUserRequest): CreateUser =
        CreateUser(
            nickname = data.nickname,
            email = data.email,
            password = data.password,
        )

    override fun toData(domain: CreateUser): CreateUserRequest =
        CreateUserRequest(
            nickname = domain.nickname,
            email = domain.email,
            password = domain.password,
        )

}