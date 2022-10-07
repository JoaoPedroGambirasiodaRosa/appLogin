package br.ulbra.applogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {
    EditText edNome, edUser, edPas1, edPas2;
    Button btSalvar, btVoltar;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBHelper(this);
        edNome = (EditText)findViewById(R.id.edtnome);
        edUser = (EditText)findViewById(R.id.edtlogin);
        edPas1 = (EditText)findViewById(R.id.edtsenha);
        edPas2 = (EditText)findViewById(R.id.etdsenha2);
        btSalvar = (Button)findViewById(R.id.btnRegistrar);
        btVoltar = (Button)findViewById(R.id.btnVoltar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUser.getText().toString();
                String pas1 = edPas1.getText().toString();
                String pas2 = edPas2.getText().toString();
                if (userName.equals("")) {
                    Toast.makeText(RegistrarActivity.this, "INSIRA O LOGIN DO USUÁRIO",
                            Toast.LENGTH_SHORT).show();
                } else if (pas1.equals("") || pas2.equals("")){
                    Toast.makeText(RegistrarActivity.this, "INSIRA A SENHA DO USUÁRIO",
                            Toast.LENGTH_SHORT).show();
                }else if(!pas1.equals(pas2)){
                    Toast.makeText(RegistrarActivity.this, "AS SENHAS NÃO CORRESPONDEM",
                            Toast.LENGTH_SHORT).show();
                }else{
                    long res = db.criarUtilizador(userName,pas1);
                    if(res>0){
                        Toast.makeText(RegistrarActivity.this,
                                "REGISTRO OK", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegistrarActivity.this, "SENHA INVALIDA",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
