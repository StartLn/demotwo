package com.com.bw.movie.model

import com.com.bw.movie.mvp.MyContract
import com.com.bw.movie.utils.RetrofitUtils
import com.google.gson.Gson

import java.util.HashMap

class ModelImpl : MyContract.Model {

    override fun onGetRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>, callBack: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>, callBack: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
