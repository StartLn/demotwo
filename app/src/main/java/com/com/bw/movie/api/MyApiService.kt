package com.com.bw.movie.api

import java.util.HashMap

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap
import retrofit2.http.Url
import rx.Observable


interface MyApiService {
    @GET
    operator fun get(@Url url: String, @QueryMap map: Map<String, Any>, @HeaderMap headmap: Map<String, Any>): Observable<ResponseBody>

    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap map: Map<String, Any>, @HeaderMap headmap: Map<String, Any>): Observable<ResponseBody>

    @PUT
    fun put(@Url url: String, @QueryMap map: Map<String, Any>, @HeaderMap headmap: Map<String, String>): Observable<ResponseBody>

    @DELETE
    fun delete(@Url url: String, @QueryMap map: Map<String, Any>, @HeaderMap headmap: Map<String, Any>): Observable<ResponseBody>

    @POST
    fun postHeader(@Url url: String, @HeaderMap header: HashMap<String, Any>, @Body body: MultipartBody): Observable<ResponseBody>

}
