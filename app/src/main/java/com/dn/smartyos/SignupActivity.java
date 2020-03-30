package com.dn.smartyos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// import com.example.aguss.perpusqu.news.model.NewsListResponse;
//import com.example.aguss.perpusqu.sekolah.adapter.SekolahAdapter;
//import com.example.aguss.perpusqu.sekolah.api.SekolahApi;
//import com.example.aguss.perpusqu.sekolah.model.SekolahModel;
//import com.example.aguss.perpusqu.sekolah.model.SekolahResponse;
import com.dn.smartyos.api.SignupApi;
import  com.dn.smartyos.model.SignupResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class SignupActivity extends AppCompatActivity {
    private SignupApi apiRegister = null;

    View rootview;
    private EditText fullname,username,password,konfirmasipassword,email;
    private TextView gotoLogin;
    private TextView toLogin;
    // private Spinner sekolah;
    private Button btnSignup;
    //spiner
    private RecyclerView.Adapter mAdapter;
    //private SekolahApi apiSekolah = null;
    //private String sekolah;
    //private Spinner spinner;
    //@BindView(R.id.spinnerlogin)
    Spinner spinerlogin;
    ProgressDialog loading;
    Context mContext;
    //private List<SekolahModel> sekolahModelList;

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
        setContentView(R.layout.activity_signup);

        apiRegister = ServiceGenerator.createService(SignupApi.class);
       // apiSekolah = ServiceGenerator.createService(SekolahApi.class);
        bindView();
        Signup();
        toLogin();
        //initSpinnerSekolah();


    }

    private void bindView(){
        fullname=(EditText)findViewById(R.id.fullnamesignup);
        username=(EditText)findViewById(R.id.usernamelogin);
        password=(EditText)findViewById(R.id.passwordsignup);
        konfirmasipassword=(EditText)findViewById(R.id.konfpasswordsignup);
        email=(EditText)findViewById(R.id.email);
        //spinner=(Spinner)findViewById(R.id.spinnerlogin);
        btnSignup=(Button)findViewById(R.id.btntoSignup);
        gotoLogin=(TextView)findViewById(R.id.toLogin);
    }

    private void Signup(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname=(EditText)findViewById(R.id.fullnamesignup);
                username=(EditText)findViewById(R.id.usernamelogin);
                password=(EditText)findViewById(R.id.passwordsignup);
                konfirmasipassword=(EditText)findViewById(R.id.konfpasswordsignup);
                email=(EditText)findViewById(R.id.email);

               // spinner=(Spinner)findViewById(R.id.spinner_sekolah);

                if(fullname.getText().toString().isEmpty()){
                    fullname.setError("fullname tidak boleh kosong !");
                }
                if(username.getText().toString().isEmpty()){
                    username.setError("NIS tidak boleh kosong !");
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("password tidak boleh kosong !");
                }
                if(konfirmasipassword.getText().toString().isEmpty()){
                    konfirmasipassword.setError("konfirmasi password tidak boleh kosong !");
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("email tidak boleh kosong !");
                }



                else if(password.getText().toString().equals(konfirmasipassword.getText().toString())){
                    String name=fullname.getText().toString();
                    String user_name=username.getText().toString();
                    String pass=password.getText().toString();
                    String  mail=email.getText().toString();
                    signUp_User(name,user_name,pass,mail);

                }
                else {
                    password.setError("password dan konfirmasi password tidak cocok !");
                    konfirmasipassword.setError("password dan konfirmasi password tidak cocok !");
                }
            }

        });
    }





    private void signUp_User(String fullname, String username, String password, String email){
        final ProgressDialog dialog = new ProgressDialog(SignupActivity.this);
        dialog.setMessage("Registering");
        dialog.setCancelable(false);

        dialog.show();

        Call<SignupResponse> call = null;
        call = apiRegister.postRegister(fullname,username,password,email);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Response<SignupResponse> response, Retrofit retrofit) {
                dialog.dismiss();
                if (2 == response.code() / 100) {
                    if (response.body().getSuccess() == true) {
                        gotoLogin();
                    }
                    else if(response.body().getSuccess()==false){
                        if(response.body().getInfo().toString().equals("NIM sudah tersedia")){
                            Toast.makeText(getApplicationContext(),"NIM sudah tersedia",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email sudah tersedia",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Pendaftaran gagal", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah",
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


    private void gotoLogin(){
        fullname.setText("");
        username.setText("");
        email.setText("");
        password.setText("");
        konfirmasipassword.setText("");
        Toast.makeText(getApplicationContext(), "Pendaftaran berhasil, silahkan login !",
                Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void toLogin(){
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }



    //private void initSpinnerSekolah() {
    //    loading = ProgressDialog.show(this, null, "harap tunggu...",
    //            true, false);
    //    Call<SekolahResponse> call = null;
   //     call= apiSekolah.getResultAsJSON();
   //     call.enqueue(new Callback<SekolahResponse>() {
   //         @Override
   //         public void onResponse(Response<SekolahResponse> response, Retrofit retrofit) {
   //             if (2 == response.code() / 100 && response.isSuccess()) {
   //                 loading.dismiss();
   //                 List<SekolahModel> KontakList = response.body().getListSekolah();
   //
   //                 mAdapter = new SekolahAdapter(KontakList);

   //             } else {
   //                 loading.dismiss();
   //                 Toast.makeText(getApplicationContext(), "Gagal mengambil data dosen",
   //                         Toast.LENGTH_SHORT).show();
   //             }
   //         }

}