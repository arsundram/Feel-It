package com.arsundram.sac_app

class ThoughtDatabase(val id:String,val thought:String, val username:String, val uri:String, val date:String, val email:String){
    constructor():this("","","","","","")
}
