package kaiyrzhan.de.core.preview

interface Mapper <DOMAIN, DATA> {

    fun toDomain(data: DATA): DOMAIN

    fun toData(domain: DOMAIN): DATA
}