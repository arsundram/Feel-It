package com.arsundram.sac_app

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.share_post.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.schedule

class ShareActivity:AppCompatActivity(){

    lateinit var textArea: EditText
    lateinit var shareButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_post)

        val bundle:Bundle?= intent.extras        //no concept of getter and setter so we will use intent instead of getIntent
        // null pointer safety implemented above
       val urival = bundle?.getString("user_image")
         val username=bundle?.getString("user_name") //gives error at  "."  so us not null assertion operator as we are using nullable bundle
       //val username=bundle!!.getString("user_name")
        //logo_center.setImageURI(Uri.parse(urival))
        val emailid=bundle?.getString("emailid")
        usernametextview.text=username


        val picasso = Picasso.get()


        picasso.load(urival)
                .fit()
                .into(logo_center)

        textArea=findViewById(R.id.textarea)
        shareButton=findViewById(R.id.sharebutton)

        shareButton.setOnClickListener{
            saveThought()

        }

    }



    private fun saveThought()
    {

        val bundle:Bundle?= intent.extras        //no concept of getter and setter so we will use intent instead of getIntent
        // null pointer safety implemented above
        val urival = bundle?.getString("user_image")
        val username=bundle?.getString("user_name")
        val emailid=bundle?.getString("emailid")
        var date="30/10/2018"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
           date =  current.format(formatter)
        } else {
            var date1 = Date();
            val formatter = SimpleDateFormat("MMM dd yyyy")
            date = formatter.format(date1)

        }

        val ref=FirebaseDatabase.getInstance().getReference("sacapp-9f278")   //create a node in firebase database


        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                val children = snapshot!!.children
                var flag=1
                val data=textArea.text.toString().trim()
                if(data.isEmpty())
                {
                    textArea.error="Please Enter a thought to share..!"
                    return
                }
                children.forEach {
                    val data1 = it.getValue(ThoughtDatabase::class.java)!!
                    if(data1.email.equals(emailid) && data1.date==date){
                       textArea.error = "Sorry, you have already shared a thought today!!"
                        flag=0
                        val intent=Intent(this@ShareActivity,DisplayActivity::class.java)
                        intent.putExtra("user_image",urival.toString())

                        intent.putExtra("user_name",username.toString())
                        intent.putExtra("emailid",emailid.toString())
                        Timer().schedule(1000){startActivity(intent); this@ShareActivity.finish()}
                    }
                }
                if(flag==1)
                {

                    val thoughtId=ref.push().key   // to create a unique node in the thoughts node

                    val thought=ThoughtDatabase(thoughtId,data,username.toString(), urival.toString(),date,emailid.toString())

                    ref.child(thoughtId)
                            .setValue(thought)
                            .addOnCompleteListener {
                                Toast.makeText(applicationContext,"Thought shared successfully..!",Toast.LENGTH_LONG).show()
                            }
                    val intent=Intent(this@ShareActivity,DisplayActivity::class.java)
                    intent.putExtra("user_image",urival.toString())

                    intent.putExtra("user_name",username.toString())
                    intent.putExtra("emailid",emailid.toString())
                    Timer().schedule(3000){startActivity(intent); this@ShareActivity.finish()}
                } }
        })
    }


}