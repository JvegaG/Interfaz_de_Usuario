package pe.com.jvega.interfazdeusuario.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pe.com.jvega.interfazdeusuario.BaseFragment;

public class TabAdapert extends FragmentStatePagerAdapter {

    private String[] titlesPestanas;
    private int[] iconos;


    public TabAdapert(FragmentManager fm, String[] titlesPestanas, int[] iconos) {
        super(fm);
        this.titlesPestanas = titlesPestanas;
        this.iconos = iconos;
    }

    @Override
    public Fragment getItem(int position) {
        return BaseFragment.getInstance(titlesPestanas[position], iconos[position]);
    }

    @Override
    public int getCount() {
        return titlesPestanas.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlesPestanas[position];
    }
}
