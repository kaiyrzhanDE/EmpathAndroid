package kaiyrzhan.de.utils

fun String?.isNotNullAndEmpty(): Boolean{
    return !this.isNullOrEmpty()
}

fun String?.isNotBlankAndNull(): Boolean{
    return !this.isNullOrBlank()
}