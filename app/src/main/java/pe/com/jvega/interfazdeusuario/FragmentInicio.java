package pe.com.jvega.interfazdeusuario;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentInicio extends Fragment {
    private ImageView imageView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appbar_without_tabs,container,false);

        imageView = view.findViewById(R.id.imagen);
        textView = view.findViewById(R.id.texto);

        imageView.setImageResource(R.drawable.ic_home);
        textView.setText(R.string.inicio);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() instanceof MainActivity){
            MainActivity activity = (MainActivity) getActivity();
            activity.CustomToolbar("Mis Redes Sociales", "Inicio", R.color.colorInicio);
            activity.navigationView.setCheckedItem(R.id.action_inicio);

            Window window = activity.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorInicioDark));

            View headerView = activity.navigationView.getHeaderView(0);
            LinearLayout linearLayout = ((View) headerView).findViewById(R.id.header_linearLayout);
            Drawable drawable = ContextCompat.getDrawable(activity.getApplicationContext(), R.color.colorInicio);
            linearLayout.setBackground(drawable);
        }
    }
}
