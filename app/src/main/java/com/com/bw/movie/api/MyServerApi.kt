package com.com.bw.movie.api

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/4/8  14:00<p>
 * <p>更改时间：2019/4/8  14:00<p>
 * <p>版本号：1<p>
 */
object MyServerApi {
    //接口类
    /*内网环境：172.17.8.100
    外网环境：mobile.bwstudent.com*/
    //内网环境
    const val BASE_URL:String ="http://172.17.8.100/movieApi/"
    //外网环境
    const val BASE_URLS:String ="http://mobile.bwstudent.com/movieApi/";
    //登录
    const val BASE_LOGIN:String ="user/v1/login";
    //注册
    const val BASE_REGISTER:String ="user/v1/registerUser";
}