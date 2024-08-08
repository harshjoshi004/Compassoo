package com.molog.compassoo_project.parentModule.signIn

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.molog.compassoo_project.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val oneTapClient: SignInClient,
    private val context:Context
) {
    private val auth = Firebase.auth
    suspend fun signIn(): IntentSender? {
        val result = try{
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e:Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            Toast.makeText(context, "Error in One Tap Sign In: ${e.message}", Toast.LENGTH_SHORT).show()
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    MyUserData(
                        userId = uid,
                        userName = displayName ?: "",
                        userEmail = email ?: "",
                        userPhotoUrl = photoUrl?.toString(),
                        userPhoneNumber = phoneNumber ?: ""
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e:Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            Toast.makeText(context, "Error in Sign Out: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun getSignedInUser() : MyUserData? = auth.currentUser?.run {
        MyUserData(
            uid,
            displayName,
            email,
            photoUrl.toString(),
            phoneNumber,
        )
    }

    data class SignInResult(
        val data: MyUserData?,
        val errorMessage: String?
    )

    data class MyUserData(
        val userId: String,
        val userName: String?,
        val userEmail: String?,
        val userPhotoUrl: String?,
        val userPhoneNumber: String?
    )

    private fun buildSignInRequest(): BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(ContextCompat.getString(context, R.string.webClientId))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}