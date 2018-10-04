package pe.com.jvega.interfazdeusuario.Dialogos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import pe.com.jvega.interfazdeusuario.R;

public class DialogConfirmacion extends DialogFragment {

    private String texto;

    public void setText(String texto){
        this.texto = texto;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogsTheme);

        builder.setTitle("Confirmación");
        builder.setMessage("¿Compartir esta aplicación a través de los medios seleccionados?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), texto, Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(),"Acción Cancelada", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
