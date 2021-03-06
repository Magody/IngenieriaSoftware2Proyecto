package com.software2_grupo3.ingenieriasoftware2proyecto.Modelos.actualizarUsuario;

public interface ActualizarContracts {

    interface View{
        void mostrarMensaje(String mensaje);
        void navegarMainActivity();
    }

    interface Presentador{
        void enBotonPresionado(String cedula, String correo, String direccion, String fechaNacimiento, String password, String tarjeta, String telefono, String usuario, String nombre);
        void enInsertarExitoso(String data);
        void enInsertarFallido(String error);
    }

    interface  Interactor{
        void actualizarRegistro(String cedula, String correo, String direccion, String fechaNacimiento, String password, String tarjeta, String telefono, String usuario, String nombre);
    }
}
