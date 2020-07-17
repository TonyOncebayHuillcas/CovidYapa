package com.connecttix.covidyapa.views.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.connecttix.covidyapa.R;
import com.connecttix.covidyapa.database.SqliteClass;
import com.connecttix.covidyapa.models.ProductModel;
import com.connecttix.covidyapa.models.TiendaModel;
import com.connecttix.covidyapa.utils.ConnectionDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private  final int REQUEST_ACCESS_FINE = 0;
    EditText et_user, et_pass;
    Button btLogin,btnRegister;
    Context context;
    Activity activity;
    ProgressDialog dialog;
    //Protocol protocol;
    String username,password;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=getApplicationContext();
        activity= LoginActivity.this;
        //protocol=new Protocol();
        cd=new ConnectionDetector(context);

        /** Mantener sesión activa **/
        SharedPreferences sharedPref = getSharedPreferences("login_preferences",Context.MODE_PRIVATE);
        String logueado=sharedPref.getString("logueado","inactive");
        if(logueado.equals("active")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }

        et_user=(EditText) findViewById(R.id.et_username);
        et_pass=(EditText) findViewById(R.id.et_password);
        et_user.setText("admin");
        et_pass.setText("P4ssw0rd!");

        /** Permisos de la app **/
        int accessFinePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int accessCoarsePermission = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION);
        int accessWritePermission = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(accessFinePermission!= PackageManager.PERMISSION_GRANTED &&
                accessCoarsePermission!=PackageManager.PERMISSION_GRANTED &&
                accessWritePermission!=PackageManager.PERMISSION_GRANTED

        ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            //Manifest.permission.CALL_PHONE,
                            //Manifest.permission.SEND_SMS
                    }, REQUEST_ACCESS_FINE);}
        /**Fin Permisos de la app **/

        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_user.getText().length()>0 && et_pass.getText().length()>0){

                    if (cd.isConnectingToInternet()) {
                        new loginTask().execute(true);
                    }else{
                        Toast.makeText(context,"Tranki : Su dispositivo no cuenta con conexión a internet.",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(context,"Tranki : Ingrese un usario y/o contraseña",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister=(Button) findViewById(R.id.bt_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //startActivity(intent);
                //finish();
            }
        });

    }

    class loginTask extends AsyncTask<Boolean, Void, String> {

        @Override
        protected void onPreExecute() {
            username=et_user.getText().toString();
            password=et_pass.getText().toString();
            dialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.action_loading), true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            /** Activar el estado de sessión main **/
            SharedPreferences sharedPref = getSharedPreferences("login_preferences",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("logueado", "active");
            editor.apply();

            /** Redireccionar a clase main **/
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Boolean... booleans) {
            ProductModel productModel1 = new ProductModel(1,"Ace","producto de limpieza",39,"aseo");
            ProductModel productModel2 = new ProductModel(1,"Leche","Gloria",46,"primer neces");
            ProductModel productModel3 = new ProductModel(1,"Atun","gloria",28,"primera nececisad");
            ProductModel productModel4 = new ProductModel(1,"Cafe","producto con cafeina",39,"primera neceidad");
            ProductModel productModel5 = new ProductModel(1,"Pollo"," comida de carne",46,"aseo");

            SqliteClass.getInstance(context).databasehelp.productSql.addProduct(productModel1);
            SqliteClass.getInstance(context).databasehelp.productSql.addProduct(productModel2);
            SqliteClass.getInstance(context).databasehelp.productSql.addProduct(productModel3);
            SqliteClass.getInstance(context).databasehelp.productSql.addProduct(productModel4);
            SqliteClass.getInstance(context).databasehelp.productSql.addProduct(productModel5);

            TiendaModel tiendaModel1 = new TiendaModel(1,"La tienda de donde pepe","234567898","pepe@comercienta.com","abarrote");
            TiendaModel tiendaModel2 = new TiendaModel(1,"Mas barato","123456789","varato@gmail.com","abarrote");
            TiendaModel tiendaModel3 = new TiendaModel(1,"Tarro","34683565","josemercante@comercienta.com","abarrote");

            SqliteClass.getInstance(context).databasehelp.storeSql.addStore(tiendaModel1);
            SqliteClass.getInstance(context).databasehelp.storeSql.addStore(tiendaModel2);
            SqliteClass.getInstance(context).databasehelp.storeSql.addStore(tiendaModel3);


            return null;
        }

    }
}
