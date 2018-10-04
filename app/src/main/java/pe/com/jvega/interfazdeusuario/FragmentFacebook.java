package pe.com.jvega.interfazdeusuario;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import pe.com.jvega.interfazdeusuario.Adapters.TabAdapert;

public class FragmentFacebook extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    String[] tabs = {"Noticias", "Solicitudes", "Notificaciones"};
    int[] icons = {R.drawable.ic_newspaper, R.drawable.ic_solicitud, R.drawable.ic_notification};
    int[] iconsTab = {R.drawable.ic_newspaper_white, R.drawable.ic_solicitud_white, R.drawable.ic_notification_white};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appbar_with_tabs, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FacebookAdapter(getActivity().getSupportFragmentManager(),this.tabs, this.icons));

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorFacebook));
        TabImagenes(tabLayout, iconsTab);

    }

    public void TabImagenes(TabLayout tabLayout, int[] imagenes){
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable icon = ContextCompat.getDrawable(getContext(), imagenes[i]);
            if (tab != null) {
                tab.setIcon(icon);
                tab.setText(null); // si quieres obviar los textos habilita esta parte
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() instanceof MainActivity){
            MainActivity activity = (MainActivity) getActivity();
            activity.CustomToolbar("Mis Redes Sociales", getResources().getString(R.string.facebook), R.color.colorFacebook);
            activity.navigationView.setCheckedItem(R.id.action_facebook);

            Window window = activity.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorFacebookDark));

            View headerView = activity.navigationView.getHeaderView(0);
            LinearLayout linearLayout = ((View) headerView).findViewById(R.id.header_linearLayout);
            Drawable drawable = ContextCompat.getDrawable(activity.getApplicationContext(), R.color.colorFacebook);
            linearLayout.setBackground(drawable);
        }
    }

    private class FacebookAdapter extends TabAdapert {

        public FacebookAdapter(FragmentManager manager, String[] titlesPestanas, int[] iconos) {
            super(manager, titlesPestanas, iconos);
        }
    }
}
