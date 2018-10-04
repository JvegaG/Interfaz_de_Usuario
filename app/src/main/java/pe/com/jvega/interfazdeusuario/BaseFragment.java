package pe.com.jvega.interfazdeusuario;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseFragment extends Fragment {

    private String text;
    private int imagen;

    private final static String KEY_TITLE = "title";
    private final static String KEY_IMAGEN = "imagen";

    public static BaseFragment getInstance(String title, int imagen){
        BaseFragment fragment = new BaseFragment();

        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putInt(KEY_IMAGEN, imagen);

        fragment.setArguments(args);
        return fragment;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_contenido, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle argumentArrived = getArguments();
        if (argumentArrived != null) {
            text = argumentArrived.getString(KEY_TITLE);
            imagen = argumentArrived.getInt(KEY_IMAGEN);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.texto);
        textView.setText(this.text);

        ImageView imageView = view.findViewById(R.id.imagen);
        imageView.setImageResource(this.imagen);
    }
}
