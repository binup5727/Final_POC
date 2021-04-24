package com.example.final_poc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 777;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private FirebaseAuth mAuth;

    private DrawerLayout drawer;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //FirebaseAuth.getInstance().signOut();
        drawer = findViewById(R.id.activity_main);
        mAuth = FirebaseAuth.getInstance();
        requestGoogleSignin();

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , new home_fragment()).commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
            {
                case R.id.home:
                    System.out.println("home");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , new home_fragment()).commit();
                    break;
                case R.id.favorite:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                            , new favorite_fragment()).commit();
                     break;
                case R.id.search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                            , new search_fragment()).commit();


            }
            drawer.closeDrawer(GravityCompat.START);
            return true;


    }


    private void requestGoogleSignin(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void onSignIn(View V){
        System.out.println("sign in called");
        Intent signinIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signinIntent, RC_SIGN_IN);

    }
    public void signOut(View v){
        FirebaseAuth.getInstance().signOut();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv);
        View headerview = navigationView.getHeaderView(0);
        TextView navuser = (TextView) headerview.findViewById(R.id.Username);
        TextView navemail = (TextView) headerview.findViewById(R.id.email);

        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                .edit();
        editor.putString("Username", "").commit();
        editor.putString("Useremail", "").commit();

        navuser.setText("");
        navemail.setText("");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);


                firebaseAuthWithGoogle(account.getIdToken());

                //SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                ///        .edit();
                //editor.putString("Username", account.getDisplayName()).commit();
                //editor.putString("Useremail", account.getEmail()).commit();



                //SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
                //String user = preferences.getString("Username", "nope still");
                //String name = preferences.getString("Useremail", "nope still");

                User userAccount = new User(-1, account.getDisplayName(), account.getEmail(), "");

                DBHelper dbHelper = new DBHelper(MainActivity.this);
                if(dbHelper.check(account.getDisplayName())) {
                    dbHelper.addUser(userAccount);
                    System.out.println("there is no user in database");
                }else {
                    System.out.println("there is a user in database");
                }


                NavigationView navigationView = (NavigationView) findViewById(R.id.nv);
                View headerview = navigationView.getHeaderView(0);
                TextView navuser = (TextView) headerview.findViewById(R.id.Username);
                TextView navemail = (TextView) headerview.findViewById(R.id.email);


                User user = dbHelper.getfav(account.getDisplayName());
                String name = user.getName();
                String email = user.getEmail();
                navuser.setText(name);
                navemail.setText(email);








            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(MainActivity.this, "Authorization fail", Toast.LENGTH_LONG).show();


            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information



                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authorization fail", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


}


