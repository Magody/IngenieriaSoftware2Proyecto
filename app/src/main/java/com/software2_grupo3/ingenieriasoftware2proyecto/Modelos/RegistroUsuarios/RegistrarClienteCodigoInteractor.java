package com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.RegistroUsuarios;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.Cliente;
import com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.ConexionBD.ApiClient;
import com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.ConexionBD.ApiInterface;
import com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.Respuesta;
import com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.Validacion;
import com.software2_grupo3.ingenieriasoftware2proyecto.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.util.Log.d;

public class RegistrarClienteCodigoInteractor implements RegistrarClienteCodigoContracts.Interactor{

    public static final String TAG = "RCCodigoInteractor";
    Context context;

    RegistrarClienteCodigoContracts.Presentador callbackRegistrarClienteCodigoPresentador;

    public RegistrarClienteCodigoInteractor(RegistrarClienteCodigoContracts.Presentador callbackRegistrarClienteCodigoPresentador, Context context ){
        this.context = context;
        this.callbackRegistrarClienteCodigoPresentador = callbackRegistrarClienteCodigoPresentador;

    }


    @Override
    public void aceptarCodigo(String codigoVerificacion, String cedula) {

        String[]campos = new String[]{codigoVerificacion};
        if(!Validacion.camposLlenos(campos)){
            callbackRegistrarClienteCodigoPresentador.aceptarFallido(context.getString(R.string.msgExistenCamposVacios));
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        final int[] resultado = new int[1]; //probar
        Call<Respuesta> call;
        call = apiInterface.crearCodigoCliente(codigoVerificacion, cedula);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {

                d(TAG, "onResponse: " + response.body());

                if (response.isSuccessful() && response.body() != null) {

                    final Respuesta cliente = response.body();

                    callbackRegistrarClienteCodigoPresentador.aceptarExitoso("Correo Verificado");

                } else {
                    callbackRegistrarClienteCodigoPresentador.aceptarFallido("Datos nulos");
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                callbackRegistrarClienteCodigoPresentador.aceptarFallido(t.toString());
                Log.e(TAG, "RegistrarClienteCodigoInteractor: Onfailure" + t.toString());
            }
        });

    }
}
