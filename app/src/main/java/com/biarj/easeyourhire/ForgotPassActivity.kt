package com.biarj.easeyourhire

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_forgot_pass.*
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class ForgotPassActivity : AppCompatActivity() {
    lateinit var etmobile: EditText

    lateinit var next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        title = "Forgot Password"
        etmobile = findViewById(R.id.mobile)
        println("in forgotact")
        next = findViewById(R.id.next)

        next.setOnClickListener {

            val mobile = etmobile.text.toString()
            println("************")
            println(mobile)

           val intent = Intent(this@ForgotPassActivity, OtpVerifyActivity::class.java)
            intent.putExtra("mobile_num",mobile)
            println(mobile)
            startActivity(intent)

        }

    }

}


