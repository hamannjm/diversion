package com.hamann.inpursuitofdiversion.models

class Resource<T>(
    val data: T?,
    val status: Status,
    val message: String? = null
) {
    companion object {
        fun<T> loading(): Resource<T> = Resource(null, Status.LOADING)
        fun<T> success(data: T): Resource<T> = Resource(data, Status.SUCCESS)
        fun<T> error(message: String): Resource<T> = Resource(null, Status.ERROR, message)
    }
}
enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}