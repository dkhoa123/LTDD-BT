package com.example.btvn_tuan5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.btvn_tuan5.ui.theme.BTVN_tuan5Theme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {

    companion object{
        const val RC_SIGN_IN = 100
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            BTVN_tuan5Theme {
                if(mAuth.currentUser == null){
                    ScreenHome(){
                        signIn()
                    }
                }else
                {
                    val user : FirebaseUser = mAuth.currentUser!!
                    ScreenProfile(
                        profileImage = user.photoUrl!!,
                        name = user.displayName!!,
                        email = user.email!!,
                        signOutClicked={
                            signOut()
                        }
                    )
                }
            }
        }
    }

    private fun signOut() {
    val googleSignInClient: GoogleSignInClient

    val gso =GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth.signOut()
        googleSignInClient.signOut()
            .addOnCompleteListener{
                Toast.makeText(this, "Sign Out Successful", Toast.LENGTH_SHORT).show()
                setContent {
                    BTVN_tuan5Theme{
                        ScreenHome(){
                            signIn()
                        }
                    }
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Sign Out Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // result returned from launching the intent from GoogleSignInApi.getSignInIntent()
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google SignIn was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: Exception) {
                    // Google SignIn Failed
                    Log.d("SignIn", "Google SignIn Failed")
                }
            } else {
                Log.d("SignIn", exception.toString())
            }
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful) {
                    Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    setContent {
                        BTVN_tuan5Theme {
                            val user: FirebaseUser = mAuth.currentUser!!
                                ScreenProfile(

                                    profileImage = user.photoUrl!!,
                                    name = user.displayName!!,
                                    email = user.email!!,
                                    signOutClicked = {
                                        signOut()
                                    }) }
                        }
                    }
                else
                {
                    Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
@Composable
fun ScreenHome(signInClicked: ()->Unit) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painterResource(id = R.drawable.galaxy),
            contentDescription = "galaxy",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth())
        Column(modifier = Modifier.padding(start = 92.dp, top = 106.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(id = R.drawable.logouth),
                contentDescription = "logoUTH",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(text ="SmartTasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(text = "A simple and efficient to-do app",
                fontSize = 12.sp,
                color = Color(0xFF2196F3)
            )
        }
        Column(modifier = Modifier.fillMaxWidth().padding(top = 468.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Ready to explore? Log in to get started.",
                fontSize = 14.sp)
            Button(onClick = {signInClicked()}, modifier = Modifier
                .width(264.dp)
                .height(50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(0xFFD5EDFF))
            ){
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    ){
                    Image(painterResource(id =R.drawable.logo_google),
                        contentDescription = "logo google",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(20.dp)
                        )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Sign in with Google",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

    }
}

@Composable
fun ScreenProfile(
    profileImage: Uri,
    name: String,
    email: String,
    signOutClicked: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically){
            Image(painterResource(id = R.drawable.backxanh),
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(40.dp)
                    .clickable { signOutClicked() }
            )
            Text(text = "Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.weight(1f).padding(end = 50.dp),
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.fillMaxWidth()){
            AsyncImage(model = profileImage,
                contentDescription = "Imageprofile",
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(125.dp))
                    .align(Alignment.Center)
            )
            Image(painterResource(id = R.drawable.camera),
                contentDescription = "camera",
                modifier = Modifier
                    .padding(start = 120.dp)
                    .size(26.dp)
                    .align(Alignment.BottomCenter)
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Text(
                    text = "Name",
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(text = "Name")
                    },
                )
            }
            Column {
                Text(
                    text = "Email",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 20.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(text = "Email")
                    },
                )
            }
            Column {
                Text(
                    text = "BirthDay",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 20.dp)
                )
                OutlinedTextField(
                    value = "31/05/2005",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(text = "BirthDay")
                    },
                )
            }

                Button(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 10.dp),
                    onClick = { signOutClicked()}
                ) {
                    Text(text = "Back")
                }
            }
        }

    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BTVN_tuan5Theme {
//        ScreenHome()
//        ScreenProfile()
    }
}