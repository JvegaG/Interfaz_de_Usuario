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

public class FragmentInstagram extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    String[] tabs = {"Explorar", "Publicar", "Favoritos"};
    int[] icons = {R.drawable.ic_search, R.drawable.ic_camera, R.drawable.ic_favorite};
    int[] iconsTab = {R.drawable.ic_search_white, R.drawable.ic_camera_white, R.drawable.ic_favorite_white};

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
        viewPager.setAdapter(new InstagramAdapter(getActivity().getSupportFragmentManager(),tabs,icons));

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorInstagram));
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
            activity.CustomToolbar("Mis Redes Sociales", getResources().getString(R.string.instagram), R.color.colorInstagram);
            activity.navigationView.setCheckedItem(R.id.action_instagram);

            Window window = activity.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorInstagram));

            View headerView = activity.navigationView.getHeaderView(0);
            LinearLayout linearLayout = ((View) headerView).findViewById(R.id.header_linearLayout);
            Drawable drawable = ContextCompat.getDrawable(activity.getApplicationContext(), R.color.colorInstagram);
            linearLayout.setBackground(drawable);
        }
    }

    private class InstagramAdapter extends TabAdapert {

        public InstagramAdapter(FragmentManager manager, String[] titlesPestanas, int[] iconos) {
            super(manager, titlesPestanas, iconos);
        }
    }
}
