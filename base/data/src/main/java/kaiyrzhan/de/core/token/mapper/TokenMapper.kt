package kaiyrzhan.de.core.token.mapper

import kaiyrzhan.de.core.token.models.Token
import kaiyrzhan.de.core.token.models.TokenRequest
import kaiyrzhan.de.core.preview.Mapper
import javax.inject.Inject

class TokenMapper @Inject constructor() : Mapper<Token, TokenRequest> {

    override fun toDomain(data: TokenRequest): Token =
        Token(
            accessToken = data.accessToken,
            refreshToken = data.refreshToken,
        )

    override fun toData(domain: Token): TokenRequest =
        TokenRequest(
            accessToken = domain.accessToken,
            refreshToken = domain.refreshToken,
        )

}