package kaiyrzhan.de.auth.data.mapper

import kaiyrzhan.de.auth.data.models.ResetPasswordRequest
import kaiyrzhan.de.auth.domain.models.ResetPassword
import kaiyrzhan.de.core.preview.Mapper

class ResetPasswordMapper : Mapper<ResetPassword, ResetPasswordRequest> {

    override fun toDomain(data: ResetPasswordRequest): ResetPassword =
        ResetPassword(
            email = data.email,
            password = data.password,
        )

    override fun toData(domain: ResetPassword): ResetPasswordRequest =
        ResetPasswordRequest(
            email = domain.email,
            password = domain.password,
        )

}