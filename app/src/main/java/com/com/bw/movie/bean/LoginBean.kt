package com.com.bw.movie.bean

/**
 *
 * 文件描述：
 *
 *
 *
 * 作者：${Ln}
 *
 *
 *
 * 创建时间：2019/4/9  18:50
 *
 *
 *
 * 更改时间：2019/4/9  18:50
 *
 *
 *
 * 版本号：1
 *
 *
 */
class LoginBean {

    /**
     * result : {"sessionId":"15320592619803","userId":3,"userInfo":{"birthday":320256000000,"id":3,"lastLoginTime":1532059192000,"nickName":"你的益达","phone":"18600151568","sex":1,"headPic":"http://172.17.8.100/images/head_pic/bwjy.jpg"}}
     * message : 登陆成功
     * status : 0000
     */

    var result: ResultBean? = null
    var message: String? = null
    var status: String? = null

    class ResultBean {

        var sessionId: String? = null
        var userId: Int = 0
        var userInfo: UserInfoBean? = null

        class UserInfoBean {

            var birthday: Long = 0
            var id: Int = 0
            var lastLoginTime: Long = 0
            var nickName: String? = null
            var phone: String? = null
            var sex: Int = 0
            var headPic: String? = null
        }
    }
}

