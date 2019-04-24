package com.arsundram.googlesignin2

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import android.widget.TextView
import android.widget.Toast
import com.arsundram.sac_app.DisplayActivity
import com.arsundram.sac_app.R
import com.arsundram.sac_app.ShareActivity
import com.arsundram.sac_app.ThoughtsActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_page.*


class LoginActivity : AppCompatActivity() {

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 1
    lateinit var signOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val intent=Intent(this,DisplayActivity::class.java)
        disp_lit_button.setOnClickListener {
            startActivity(intent)
        }
        val signIn = findViewById<View>(R.id.signInBtn) as SignInButton
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        signOut = findViewById<View>(R.id.signOutBtn) as Button
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signIn.setOnClickListener {
            view: View? -> signIn()
        }
    }
    private fun signIn () {
        val bundle:Bundle?= intent.extras

        val Code:String? = bundle?.getString("code")
        if(Code.equals("sgnout"))
        {
            signOut.performClick()
        }
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult (task)

        }else {
            Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
        }
    }
    private fun handleResult (completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            updateUI (account)
            val personPhoto = account?.photoUrl

            val intent=Intent(this,DisplayActivity::class.java)
            intent.putExtra("user_image",personPhoto.toString())
            val username=account?.displayName
            val emailid=account?.email
            intent.putExtra("user_name",username.toString())
            intent.putExtra("emailid",emailid.toString())

            startActivity(intent)

        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
    private fun updateUI (account: GoogleSignInAccount?) {
        val dispTxt = findViewById<View>(R.id.dispTxt) as TextView

        signOut.visibility = View.VISIBLE

        signOut.setOnClickListener {
            view: View? ->  mGoogleSignInClient.signOut().addOnCompleteListener {
            task: Task<Void> -> if (task.isSuccessful) {
            dispTxt.text = " "
            signOut.visibility = View.INVISIBLE
            dispTxt.visibility=View.INVISIBLE
            signOut.isClickable = false
        }
        }
        }
    }
    private fun signout(){
       mGoogleSignInClient.signOut()
    }


}