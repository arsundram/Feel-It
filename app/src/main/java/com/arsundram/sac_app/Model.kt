package com.arsundram.sac_app

data class thought(var username:String, var data:String, var date:String, var image:String)

/*
class Model{
    var username:String?=null



    var userimage:String?=null


    var date1:String?=null

    constructor(username: String?, userimage: String?, date1: String?) {
        this.username = username
        this.userimage = userimage
        this.date1 = date1
    }
}

 */
object Supplier{

    val thoughts= listOf<thought>(
            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),

            thought("Aman","Ho raha hai","26-10-2018",""),

            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),
            thought("Aman","Ho raha hai","26-10-2018",""),
            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),

            thought("Aman","Ho raha hai","26-10-2018",""),

            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),
            thought("Aman","Ho raha hai","26-10-2018",""),
            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),
            thought("Aman","Ho raha hai","26-10-2018",""),

            thought("Aman","Ho raha hai","26-10-2018",""),

            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),
            thought("Aman","Ho raha hai","26-10-2018",""),
            thought("Archna Bharti","Nahi ho raha hai","26-10-2018",""),
            thought("Aman","Ho raha hai","26-10-2018",""),
            thought("Aman","Ho raha hai","26-10-2018",""),
            thought("Archna Bharti","Nahi ho raha hai","26-10-2018","")

    )
}