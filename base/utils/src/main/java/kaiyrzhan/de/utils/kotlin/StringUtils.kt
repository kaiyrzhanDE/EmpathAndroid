package kaiyrzhan.de.utils.kotlin

fun String?.isNotNullAndEmpty(): Boolean{
    return !this.isNullOrEmpty()
}

fun String?.isNotBlankAndNull(): Boolean{
    return !this.isNullOrBlank()
}