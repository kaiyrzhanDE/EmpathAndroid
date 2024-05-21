package kaiyrzhan.de.auth.data.mapper

import kaiyrzhan.de.auth.data.models.UserRequest
import kaiyrzhan.de.auth.domain.models.User
import kaiyrzhan.de.core.preview.Mapper
import javax.inject.Inject


class UserMapper @Inject constructor() : Mapper<User, UserRequest> {

    override fun toDomain(data: UserRequest): User =
        User(
            nickname = data.nickname,
            email = data.email,
            id = data.id,
            password = data.password,
            sex = data.sex,
            name = data.name,
            lastname = data.lastname,
            patronymic = data.patronymic,
            dateOfBirth = data.dateOfBirth,
            image = data.image,
        )

    override fun toData(domain: User): UserRequest =
        UserRequest(
            nickname = domain.nickname,
            email = domain.email,
            id = domain.id,
            password = domain.password,
            sex = domain.sex,
            name = domain.name,
            lastname = domain.lastname,
            patronymic = domain.patronymic,
            dateOfBirth = domain.dateOfBirth,
            image = domain.image,
        )
}