package com.example.mydictionaryapp.core.utils


//There we create the
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    //Success et Loading have data . Error has both
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(data, message)
}
