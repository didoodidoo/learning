package cn.code.zeus.user.pojo

class UserInfo constructor() {
    lateinit var id: String
    lateinit var name: String
    lateinit var tel: String
    lateinit var mail: String

    constructor(id: String, name: String, tel: String, mail: String) : this() {
        this.id = id
        this.name = name
        this.tel = tel
        this.mail = mail
    }
}