package com.com.bw.movie.presenter

import com.com.bw.movie.model.ModelImpl
import com.com.bw.movie.mvp.MyContract

import java.util.HashMap

class PresenterImpl(private var iView: MyContract.IView<*>?) : MyContract.Presenter {
    override fun onGetRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostRequest(url: String, map: Map<String, Any>, head: Map<String, Any>, kind: Class<*>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var model: ModelImpl? = null

    init {
        model = ModelImpl()
    }

    fun onPresenterDestory() {
        if (iView != null) {
            iView = null
        }
        if (model != null) {
            model = null
        }
    }
}
