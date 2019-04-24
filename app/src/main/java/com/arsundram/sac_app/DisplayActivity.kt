package com.arsundram.sac_app

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.arsundram.googlesignin2.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.display_list.*
import kotlinx.android.synthetic.main.share_post.*
import java.util.*
import kotlin.concurrent.schedule

class DisplayActivity:AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    private lateinit var toggle: ActionBarDrawerToggle

    lateinit var ref : DatabaseReference
    lateinit var listView:ListView
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso: GoogleSignInOptions
    lateinit var thlist:MutableList<ThoughtDatabase>
    override fun onCreate(savedInstanceState: Bundle?) {

         ref=FirebaseDatabase.getInstance().getReference("sacapp-9f278")   //create a node in firebase database

        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_list)
        thlist= mutableListOf()
        listView=findViewById(R.id.listview)


        ref.addValueEventListener(object : ValueEventListener{
     override fun onCancelled(p0: DatabaseError?) {

     }

     override fun onDataChange(p0: DataSnapshot?) {
         if (p0!!.exists()) {
             for (h in p0.children) {
                 val hero = h.getValue(ThoughtDatabase::class.java) //as getvalue method takes a java class
                 thlist.add(hero!!)

             }
             val adapter = ListAdapter(applicationContext, R.layout.thoughtsday, thlist)
             listView.adapter = adapter
         }

     }
 }
     )

        setSupportActionBar(toolbar)
        init()

        }


    private fun init(){
        toggle=ActionBarDrawerToggle(Activity(),drawerlayout,toolbar,R.string.nav_open,R.string.nav_close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationview.setNavigationItemSelectedListener(this)
        navigationview.bringToFront()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.sharethought ->{
                val bundle:Bundle?= intent.extras

                val urival = bundle?.getString("user_image")
                val username=bundle?.getString("user_name")
                val emailid=bundle?.getString("emailid")
val intent=Intent(this,ShareActivity::class.java)
                intent.putExtra("user_image",urival.toString())

                intent.putExtra("user_name",username.toString())
                intent.putExtra("emailid",emailid.toString())

                startActivity(intent)
              // this@DisplayActivity.finish()
            }
            R.id.logout_drawer ->{

                Toast.makeText(this,"Successfully Logged out!",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,LoginActivity::class.java)
                intent.putExtra("code","sgnout")
                startActivity(intent)

            }
            R.id.about ->{
                val builder = AlertDialog.Builder(this)


                builder.setTitle("About")


                builder.setMessage("This app was created by Aman Raj Sundram and Archna Bharti")

                builder.setPositiveButton("Ok"){dialog, which ->



                }

                val dialog: AlertDialog = builder.create()

                dialog.show()

            }
            else->{



            }
        }
        drawerlayout.closeDrawer(GravityCompat.START)

        return true
    }


    }


