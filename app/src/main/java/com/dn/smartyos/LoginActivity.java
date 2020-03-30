package com.dn.smartyos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//import id.wwf.wwfpocket.divisi.userinterface.DivisiListActivity;
import com.dn.smartyos.api.LoginApi;

import com.dn.smartyos.model.LoginModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

//import id.wwf.wwfpocket.signup.userinterface.SignupActivity;


public class LoginActivity extends AppCompatActivity {
    private LoginApi apiLogin = null;

    private Button btnLogin;
    private TextView gotoSignup;
    private EditText username,password;
    ProgressDialog progressDialog;
    private SharedPreferences sp;
    private final String name="myShared";
    public static final int mode= Activity.MODE_PRIVATE;

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiLogin = ServiceGenerator.createService(LoginApi.class);// ServiceGenerator.createService(LoginApi.class);
        bindView();
       gotoSignup();
        login();


    }


    private void bindView(){
        username=(EditText)findViewById(R.id.usernamelogin);
        password=(EditText)findViewById(R.id.passwordsignup);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        gotoSignup=(TextView) findViewById(R.id.btntoSignup);

    }


    private void gotoSignup(){
        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), SignupActivity.class);
                  startActivity(i);

            }
        });
    }

    private void login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if(cekInput()){
                    if(progressDialog==null)
                    {
                        progressDialog=new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("Mencoba masuk...");
                        progressDialog.setIndeterminate(false);
                        progressDialog.setCancelable(false);
                    }

                    progressDialog.show();
                    functionlogin(username.getText().toString(),password.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Isi semua data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean cekInput(){
        boolean cek=true;
        if(username.getText().toString().isEmpty()){
            username.setError("username tidak boleh kosong");
            cek=false;
        }
        else {
            username.setError(null);
        }

        if (password.getText().toString().isEmpty()){
            password.setError("password tidak boleh kosong");
            cek=false;
        }
        else {
            password.setError(null);
        }
        return cek;
    }

    public void clearError(){
        username.setError(null);
        password.setError(null);
    }
    public void savePreferences(LoginModel login){



        Toast.makeText(getApplicationContext(), "login sukses", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(i);


    }
    public void functionlogin(String username,String password){

        Call<LoginModel> call = null;
        call=apiLogin.getDataLogin(username,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Response<LoginModel> response, Retrofit retrofit) {
                LoginModel login=response.body();
                if (2 == response.code() / 100) {
                    if ((progressDialog != null) && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    if(login.getStatus().equalsIgnoreCase("sukses"))
                    {
                        savePreferences(login);
                    }
                    else if(login.getStatus().equalsIgnoreCase("gagal"))
                    {
                        Toast.makeText(getApplicationContext(), "login gagal",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.code() + " nomor error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
                public void onFailure(Throwable t) {
                    if ((progressDialog != null) && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    clientBuilder.addInterceptor(loggingInterceptor);


                    Toast.makeText(getApplicationContext(), "Koneksi internet anda tidak stabil", Toast.LENGTH_LONG).show();

                }
            });




    }
}
