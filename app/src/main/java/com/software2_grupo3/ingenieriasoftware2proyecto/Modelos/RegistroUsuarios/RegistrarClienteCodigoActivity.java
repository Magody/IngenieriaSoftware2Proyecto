package com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.RegistroUsuarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


import com.software2_grupo3.ingenieriasoftware2proyecto.MainActivity;
import com.software2_grupo3.ingenieriasoftware2proyecto.R;

public class RegistrarClienteCodigoActivity extends Activity implements RegistrarClienteCodigoContracts.View {

    EditText codigotxt;
    Button buttonAceptar;
    RegistrarClienteCodigoContracts.Presentador registrarClienteCodigoPresentador;
    Bundle datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_cliente_codigo);
        registrarClienteCodigoPresentador = new RegistrarClienteCodigoPresenter(this, this);
        codigotxt = findViewById(R.id.codigotxt);
        buttonAceptar = findViewById(R.id.buttonAceptar);
        datos = getIntent().getExtras();
        //String cedula = datos.getString("cedula");

        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarClienteCodigoPresentador.botonAceptarCodigo(codigotxt.getText().toString(), datos.getString("cedula"));
            }
        });
    }


    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navegarMainActivity() {
        startActivity(new Intent(RegistrarClienteCodigoActivity.this, MainActivity.class));
    }
}
