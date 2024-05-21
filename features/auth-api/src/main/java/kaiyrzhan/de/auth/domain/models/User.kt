package kaiyrzhan.de.auth.domain.models


data class User(
     val nickname: String,
     val email: String,
     val id: String,
     val password: String,
     val sex: String?,
     val name: String?,
     val lastname: String?,
     val patronymic: String?,
     val dateOfBirth: String?,
     val image: String?,
)