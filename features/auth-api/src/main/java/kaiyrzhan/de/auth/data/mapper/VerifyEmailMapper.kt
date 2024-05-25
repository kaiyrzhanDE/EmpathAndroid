package kaiyrzhan.de.auth.data.mapper

import kaiyrzhan.de.auth.data.models.VerifyEmailRequest
import kaiyrzhan.de.auth.domain.models.VerifyEmail
import kaiyrzhan.de.core.preview.Mapper

class VerifyEmailMapper : Mapper<VerifyEmail, VerifyEmailRequest> {

    override fun toDomain(data: VerifyEmailRequest): VerifyEmail =
        VerifyEmail(
            email = data.email,
            code = data.code,
        )

    override fun toData(domain: VerifyEmail): VerifyEmailRequest =
        VerifyEmailRequest(
            email = domain.email,
            code = domain.code,
        )

}