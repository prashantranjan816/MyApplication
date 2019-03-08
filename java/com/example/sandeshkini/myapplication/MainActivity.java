package com.example.sandeshkini.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
//    FreshGitBarnch Test with no error

    //    Below two line for backGround Animation
//    private KenBurnsView kbv;
//    private boolean moving = true;

    private static final String TAG = MainActivity.class.getName();
    String message;
    String user;
    String WorningMSG = "Not implemented";
    private FirebaseAuth mAuth;
    private EditText usernameEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        usernameEt = findViewById( R.id.user_name );
        passwordEt = findViewById( R.id.user_password );

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


////========================BackGround Animation================================
//        kbv = findViewById(R.id.kbv);
//
//        AccelerateDecelerateInterpolator adi = new AccelerateDecelerateInterpolator();
//        RandomTransitionGenerator generator = new RandomTransitionGenerator(2000, adi);
//        kbv.setTransitionGenerator(generator);
//
//        kbv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (moving) {
//                    kbv.pause();
//                    moving = false;
//                } else {
//                    kbv.resume();
//                    moving = true;
//                }
//            }
//        });
//
//
////====================================================================================




    }


    private boolean validateEmail(String email) {
        return (!TextUtils.isEmpty( email ) && Patterns.EMAIL_ADDRESS.matcher( email ).matches());
    }

    private boolean validatePassword(String password) {
        return !TextUtils.isEmpty( password ) && password.length() > 6;
    }

    private void signupUser(String email, String password) {
        if (validateEmail( email ) && validatePassword( password )) {
            mAuth.createUserWithEmailAndPassword( email, password )
                    .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d( TAG, "createUserWithEmail:success" );
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText( MainActivity.this, "Authentication success.",
                                        Toast.LENGTH_SHORT ).show();
                                updateUI();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w( TAG, "createUserWithEmail:failure", task.getException() );
                                Toast.makeText( MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT ).show();
                            }

                            // ...
                        }
                    } );
        } else {
            Toast.makeText( MainActivity.this, "invalid email .",
                    Toast.LENGTH_SHORT ).show();
        }
    }

    public void loginUser(View view) {
        String user = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        login( user, password );
    }

    private void login(String email, String password) {
        if (validateEmail( email ) && validatePassword( password )) {

            mAuth.signInWithEmailAndPassword( email, password )
                    .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d( TAG, "signInWithEmail:success" );
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w( TAG, "signInWithEmail:failure", task.getException() );
                                Toast.makeText( MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT ).show();
                            }

                            // ...
                        }
                    } );
        } else {
            Toast.makeText( MainActivity.this, "password email",
                    Toast.LENGTH_SHORT ).show();
        }
    }


    private void updateUI() {
        Intent intent = new Intent( this, Main2Activity.class );

//       Below one line will send extra message to next activity.
        intent.putExtra( "EXTRA_MESSAGE1", user );

        startActivity( intent );
    }

    public void createUser(View view) {

        user = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        signupUser( user, password );


//        Below two line will get value of user_name field & get in string to pass forword
//        EditText editText = findViewById(R.id.user_name);
//        message = editText.getText().toString();


//        Intent intent = new Intent(this,Main2Activity.class);
//
////        Below one line will send extra message to next activity.
//        intent.putExtra("EXTRA_MESSAGE1",message);
//
//        startActivity(intent);


    }

    public void guest_submit(View view) {

        Intent intent = new Intent( this, Main3Activity.class );

        startActivity( intent );

    }


    public void google_login(View view) {


        Toast.makeText( this, WorningMSG, Toast.LENGTH_SHORT ).show();
    }

    public void facebook_login(View view) {
        Toast.makeText( this, WorningMSG, Toast.LENGTH_SHORT ).show();

    }

    public void login_guest(View view) {
        Toast.makeText( this, "Guest Login", Toast.LENGTH_SHORT ).show();
        Intent intent = new Intent( this, Main3Activity.class );

        startActivity( intent );

    }

// Initialize Facebook Login button

    //    private void setFacebookSDK() {
//
//        Callbac  mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = findViewById(R.id.buttonFacebookLogin);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//                // ...
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//                // ...
//            }
//        });
//    }
    // ...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        // Pass the activity result back to the Facebook SDK
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
