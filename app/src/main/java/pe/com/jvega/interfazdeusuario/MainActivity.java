package pe.com.jvega.interfazdeusuario;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import pe.com.jvega.interfazdeusuario.Dialogos.DialogConfirmacion;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Para que al correr la aplicación el primer fragmento en abrir sea el Fragmento Inicio
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new FragmentInicio())
                .commit();

        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView = findViewById(R.id.navigationView);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    /**
     * Método para la personalizacion del toolbar
     *
     * @param title        Titulo del toolbar
     * @param subtitle     Subtitulo del toolvar
     * @param colorToolBar Background color del toolbar
     */
    public void CustomToolbar(String title, String subtitle, int colorToolBar) {

        toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle(title);
            toolbar.setSubtitle(subtitle);
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), colorToolBar));
        }
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
    }

    /**
     * Método que leda accion al submenú seleccionado
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final String urlFacebook = "http://facebook.com";
        final String urlInstagram = "http://instagram.com";
        final String urlGoogle = "http://plus.google.com";
        final String urlTwitter = "http://twitter.com";

        SubMenu subMenu = menu.addSubMenu(R.string.version_web);

        subMenu.add(0, 1, 1, R.string.facebook)
                .setIcon(R.drawable.ic_facebook).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                CustomTab(urlFacebook, R.color.colorFacebook);
                return false;
            }
        });
        subMenu.add(0, 2, 2, R.string.instagram)
                .setIcon(R.drawable.ic_instagram).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                CustomTab(urlInstagram, R.color.colorInstagram);
                return false;
            }
        });
        subMenu.add(0, 3, 3, R.string.google_plus)
                .setIcon(R.drawable.ic_google_plus).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                CustomTab(urlGoogle, R.color.colorGooglePlus);
                return false;
            }
        });
        subMenu.add(0, 4, 4, R.string.twitter)
                .setIcon(R.drawable.ic_twitter).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                CustomTab(urlTwitter, R.color.colorTwitter);
                return false;
            }
        });

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;

    }

    /**
     * Método que lanza la vista de internet asociada a un url
     *
     * @param url   String del url ejemplo: http://website.com
     * @param color Color de los recursos
     */
    public void CustomTab(final String url, int color) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(getApplicationContext(), color));
        builder.setStartAnimations(getApplicationContext(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        builder.setExitAnimations(getApplicationContext(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
    }

    /**
     * Método que le da acción al las opciones del menu en el toolbar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_compartir:
                multiChoice().show();       // muestra el dialogo
                break;
            case R.id.action_configuracion:
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public AlertDialog multiChoice() {

        // variables
        final String[] items = {"Facebook", "Twitter", "Instagram", "Google Plus", "Whatsaap", "Messenger", "SMS"};
        final boolean[] checkedItems = {false, false, false, false, false, false, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogsTheme);
        builder.setTitle("Selecciona donde quieres compartir esta aplicación:");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });
        builder.setPositiveButton("Compartir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String seleccionados = "";
                for (int i = 0; i < checkedItems.length; i++) {
                    if(checkedItems[i])
                        seleccionados += items[i] + "\n";
                }
                String textoFinal = "Compartiste esta aplicación a través de:\n" + seleccionados;
                DialogConfirmacion dialogo = new DialogConfirmacion();
                dialogo.setText(textoFinal);
                dialogo.show(getSupportFragmentManager(),"confirmacion");
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(),"Acción Cancelada", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }


    /**
     * Método que da acción al eleccionar un item en el NavigationView
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        Fragment fragment = null;

        switch (id) {
            case R.id.action_inicio:
                fragment = new FragmentInicio();
                break;
            case R.id.action_facebook:
                fragment = new FragmentFacebook();
                break;
            case R.id.action_instagram:
                fragment = new FragmentInstagram();
                break;
            case R.id.action_google:
                fragment = new FragmentGoogle();
                break;
            case R.id.action_twitter:
                fragment = new FragmentTwitter();
                break;
            default:
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
