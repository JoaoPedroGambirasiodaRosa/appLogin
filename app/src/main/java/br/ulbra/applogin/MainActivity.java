package br.ulbra.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edLogin, edPass;
    Button btLogin, btRegister;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        edLogin = (EditText)findViewById(R.id.login);
        edPass = (EditText)findViewById(R.id.password);
        btLogin = (Button)findViewById(R.id.acess);
        btRegister = (Button)findViewById(R.id.registrar);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edLogin.getText().toString();
                String password=edPass.getText().toString();
                if(username.equals("")){
                    Toast.makeText(MainActivity.this,"USUÁRIO NÃO INSERIDO, TENTE NOVAMENTE",
                            Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(MainActivity.this,"SENHA NÃO INSERIDA, TENTE NOVAMENTE",
                            Toast.LENGTH_SHORT).show();
                }else{
                    String res = db.validarLogin(username,password);
                    if(res.equals("OK")){
                        Toast.makeText(MainActivity.this,"LOGIN OK",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"LOGIN OU SENHA ERRADO(S)",
                                Toast.LENGTH_SHORT).show();
                    }}}});

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(i);
            }});
    }
}