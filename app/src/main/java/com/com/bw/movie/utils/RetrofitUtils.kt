package com.com.bw.movie.utils

import android.util.Log

import com.com.bw.movie.api.MyApiService
import com.com.bw.movie.api.MyServerApi

import java.io.File
import java.io.IOException
import java.util.HashMap
import java.util.concurrent.TimeUnit

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RetrofitUtils private constructor() {
    private val myApiService: MyApiService
    //子类使用
    private val subscriber = object : Subscriber<ResponseBody>() {
        override fun onCompleted() {

        }

        override fun onError(e: Throwable) {

        }

        override fun onNext(responseBody: ResponseBody) {

        }
    }
    //重写一个观察者对象
    private val observer = object : Observer<ResponseBody> {

        override fun onCompleted() {

        }

        //网络处理失败
        override fun onError(e: Throwable) {
            if (httpListener != null) {
                httpListener!!.onError(e.message!!)
            }
        }

        //网络处理成功
        override fun onNext(responseBody: ResponseBody) {
            if (httpListener != null) {
                try {
                    httpListener!!.onSuccess(responseBody.string())
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        }
    }

    private var httpListener: HttpListener? = null

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                //配置此客户端，以便在遇到连接问题时重试或不重试。默认情况下，
                //*该客户端从以下问题中悄悄恢复
                .retryOnConnectionFailure(true)
                .build()
        //初始化Retrofit 并结合各种操作
        val retrofit = Retrofit.Builder()
                //结合Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                //结合Rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(MyServerApi.BASE_URLS)
                .client(okHttpClient)
                .build()
        //通过Retrofit创建完 这个ApiService 就可以调用方法了
        myApiService = retrofit.create(MyApiService::class.java)
    }

    private object RetroHolder {
        val retro = RetrofitUtils()
    }

    //封装Get方式  这里面采用构造者模式  就是调用这个方法有返回自己本身这个对象
    operator fun get(url: String, map: Map<String, Any>?, headMap: Map<String, Any>?): RetrofitUtils {
        var map = map
        var headMap = headMap
        //这个订阅事件（处理网络请求）放生那个线程
        //Schedulers 线程调度器
        if (map == null) {
            map = HashMap()
        }
        if (headMap == null) {
            headMap = HashMap()
        }
        myApiService.get(url, map, headMap).subscribeOn(Schedulers.io())//io就是子线程
                //在主线程调用
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        return RetrofitUtils.instance
    }


    /**
     * 表单post请求
     */
    fun post(url: String, map: Map<String, Any>?, headMap: Map<String, Any>?): RetrofitUtils {
        var map = map
        var headMap = headMap
        if (map == null) {
            map = HashMap()
        }
        if (headMap == null) {
            headMap = HashMap()
        }

        myApiService.post(url, map, headMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        return RetrofitUtils.instance
    }

    fun put(url: String, map: Map<String, Any>?, headMap: Map<String, String>?): RetrofitUtils {
        var map = map
        var headMap = headMap
        if (map == null) {
            map = HashMap()
        }
        if (headMap == null) {
            headMap = HashMap()
        }
        for (key in map.keys) {
            Log.e("put: zzz", key + "," + map[key])
        }
        for (key in headMap.keys) {
            Log.e("put: zzz", key + "," + headMap[key])
        }
        myApiService.put(url, map, headMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        return RetrofitUtils.instance

    }

    fun delete(url: String, map: Map<String, Any>?, headmap: Map<String, Any>?): RetrofitUtils {
        var map = map
        var headmap = headmap
        if (map == null) {
            map = HashMap()
        }
        if (headmap == null) {
            headmap = HashMap()
        }
        myApiService.delete(url, map, headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        return RetrofitUtils.instance
    }

    fun postHeader(url: String, head: HashMap<String, Any>, path: String): RetrofitUtils {
        val file = File(path)
        val requestBody = RequestBody.create(MediaType.parse("multipart/octet-stream"), file)
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart("image", file.name, requestBody)
        myApiService.postHeader(url, head, builder.build()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        return RetrofitUtils.instance
    }

    interface HttpListener {
        fun onSuccess(jsonStr: String)

        fun onError(error: String)
    }

    fun setHttpListener(listener: HttpListener) {
        this.httpListener = listener
    }

    companion object {

        val instance: RetrofitUtils
            get() = RetroHolder.retro
    }
}
