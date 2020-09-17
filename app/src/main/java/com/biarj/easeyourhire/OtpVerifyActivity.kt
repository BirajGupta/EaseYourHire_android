package com.biarj.easeyourhire

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class OtpVerifyActivity : AppCompatActivity() {
    lateinit var etOtp: EditText
    lateinit var etNewPassword: EditText
    lateinit var etConfirmNewPassword: EditText
    lateinit var btnSubmit: Button
    lateinit var sp: SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    var auth = FirebaseAuth.getInstance()
    val TAG = "forgotclass"

    var storedVerificationId: String? = null
    var resendToken: PhoneAuthProvider.ForceResendingToken? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword)
        btnSubmit = findViewById(R.id.btnSubmit)
        sp = getSharedPreferences("eyh", Context.MODE_PRIVATE)

        sharedPreferences = getSharedPreferences("eyh", Context.MODE_PRIVATE)

        println("in forgotact")

        val phnNum = intent.getStringExtra("mobile_num")

      //  verifyPhoneNumber(phnNum)

        println("verify func called")

        val queue = Volley.newRequestQueue(this)

        val url = "https://7bf224560da6.ngrok.io/eyhdb/forgotpass.php"

        val jsonParams = JSONObject()


        btnSubmit.setOnClickListener {

           // val verified = sharedPreferences.getBoolean("verified",false)

            if(true){

            val pass = etNewPassword.text.toString()
            val conpass = etConfirmNewPassword.text.toString()

            jsonParams.put("phone", phnNum)
            jsonParams.put("password", pass)

            if (pass != null) {
                if (Validations.matchPassword(pass, conpass)) {
                    if (ConnectionManager().checkconnectivity(this)) {
                        val jsonRequest =
                            object : JsonObjectRequest(
                                Method.POST, url, jsonParams, Response.Listener {
                                    try {

                                        val success = it.getBoolean("success")

                                        if (success) {
                                            val msg = it.getString("message")
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                                            val i = Intent(this, MainActivity::class.java)
                                            startActivity(i)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Wrong Login Credentials",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } catch (e: JSONException) {
                                        Toast.makeText(this, "Error1212", Toast.LENGTH_SHORT).show()

                                    }
                                },
                                Response.ErrorListener {
                                    Toast.makeText(
                                        this@OtpVerifyActivity,
                                        "Volley Error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            ) {
                                override fun getHeaders(): MutableMap<String, String> {
                                    val headers = HashMap<String, String>()
                                    headers["Content-Type"] = "application/json"

                                    return headers
                                }


                            }
                        queue.add(jsonRequest)
                    } else {
                        val alert = AlertDialog.Builder(this)
                        alert.setTitle("Error")
                        alert.setMessage("INTERNET connection not found")
                        alert.setPositiveButton("open settings") { text, listener ->
                            val i = Intent(Settings.ACTION_WIFI_SETTINGS)
                            startActivity(i)
                            this.finish()


                        }
                        alert.setNegativeButton("exit") { text, listener ->
                            ActivityCompat.finishAffinity(this)

                        }
                        alert.create().show()


                    }
                } else {
                    etNewPassword.error = "Passwords dont match"
                    etConfirmNewPassword.error = "Passwords dont match"
                    Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                etOtp.error = "Password can't be null"
            }

            }else {
                Toast.makeText(this@OtpVerifyActivity,"Mobile Number not Verified",Toast.LENGTH_SHORT).show()
            }

        }
    }

/*
private fun verifyCode(code:String){                                                               //auto verification na ho uske liye but abhi pta ni kaise krna hai isliye
    val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)                 //keep it as it is
    signInWithPhoneAuthCredential(credential)
}
*/

private fun verifyPhoneNumber(mobile : String) {
    println("inverifyPhonenumber")
    PhoneAuthProvider.getInstance().verifyPhoneNumber(
        mobile,
        60,
        TimeUnit.SECONDS,
        this,
        callbacks
    )
}
    @Suppress("UNREACHABLE_CODE")
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        sharedPreferences = getSharedPreferences("eyh", Context.MODE_PRIVATE)
        println("insignin")
        auth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener {

                if(it.isSuccessful){

                    val dialog = AlertDialog.Builder(this@OtpVerifyActivity)
                    dialog.setTitle("Verification Success")
                    dialog.setMessage("Enter the New Password and click on reset")
                    dialog.setPositiveButton("OK"){text,listener->

                    }
                    dialog.create()
                    dialog.show()

                    sharedPreferences.edit().putBoolean("verified",true).apply()


                }else {
                    Toast.makeText(this@OtpVerifyActivity,it.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                    sharedPreferences.edit().putBoolean("verified",false).apply()
                }

            })
    }


val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        // This callback will be invoked in two situations:
        // 1 - Instant verification. In some cases the phone number can be instantly
        //     verified without needing to send or enter a verification code.
        // 2 - Auto-retrieval. On some devices Google Play services can automatically
        //     detect the incoming verification SMS and perform verification without
        //     user action.
        println("incompleted")
        Log.d(TAG, "onVerificationCompleted:$credential")

        signInWithPhoneAuthCredential(credential)

    }

    override fun onVerificationFailed(e: FirebaseException) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w(TAG, "onVerificationFailed", e)
        println("inexception")

        if (e is FirebaseAuthInvalidCredentialsException) {
            // Invalid request
            print(e)
            Toast.makeText(this@OtpVerifyActivity,"Invalid",Toast.LENGTH_SHORT).show()
            // ...
        } else if (e is FirebaseTooManyRequestsException) {
            // The SMS quota for the project has been exceeded
            Toast.makeText(this@OtpVerifyActivity,"SMS quota exceeded",Toast.LENGTH_SHORT).show()
            val dialog = AlertDialog.Builder(this@OtpVerifyActivity)
            dialog.setTitle("Verification Failed")
            dialog.setMessage("Could not Verify")
            dialog.setPositiveButton("OK"){text,listener->

            }
            dialog.create()
            dialog.show()
            // ...
        }

        // Show a message and update the UI
        // ...
    }

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        // The SMS verification code has been sent to the provided phone number, we
        Toast.makeText(this@OtpVerifyActivity,"SMS OTP sent",Toast.LENGTH_SHORT).show()
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        Log.d(TAG, "onCodeSent:$verificationId")
        println("incodesent")

        // Save verification ID and resending token so we can use them late
        storedVerificationId = verificationId
        resendToken = token
        // ...
    }


}
}