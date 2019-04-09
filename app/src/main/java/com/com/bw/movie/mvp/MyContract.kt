package com.com.bw.movie.mvp

import java.util.HashMap

interface MyContract {
    interface Model {
        fun onGetRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>, callBack: Any)
        fun onPostRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>, callBack: Any)

    }

    interface Presenter {
        fun onGetRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>)
        fun onPostRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>)


    }

    interface MyCallBack<T> {
        fun setSuccess(success: T)
        fun setError(error: T)
    }

    interface IView<T> {
        fun success(success: T)
        fun error(error: T)

    }
}
