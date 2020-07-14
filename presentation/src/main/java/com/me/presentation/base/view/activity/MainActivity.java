package com.me.presentation.base.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.me.presentation.R;
import com.me.presentation.base.utils.Helpers;
import com.me.presentation.base.viewmodel.ViewModelFactory;
import com.me.presentation.patient.view.viewmodel.PatientsViewModel;
import com.me.presentation.setting.view.viewmodel.SettingsViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Inject
    public ViewModelFactory<PatientsViewModel> patientsVmFactory;

    private PatientsViewModel patientsVM = null;

    private NavController navController = null;

    private int selectedNavItem = -1;

    public synchronized PatientsViewModel getPatientsVM() {
        if (patientsVM == null) {
            patientsVM = ViewModelProviders.of(this, patientsVmFactory)
                    .get(PatientsViewModel.class);
        }
        return patientsVM;
    }

    @Inject
    public ViewModelFactory<SettingsViewModel> settingsVmFactory;

    private SettingsViewModel settingsVM = null;

    public synchronized SettingsViewModel getSettingsVM() {
        if (settingsVM == null) {
            settingsVM = ViewModelProviders.of(this, settingsVmFactory)
                    .get(SettingsViewModel.class);
        }
        return settingsVM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.patientsListFragment, R.id.userSettingsFragment, R.id.navShare)
                .setDrawerLayout(drawer)

                .build();
        navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        getSettingsVM().initNumberPatients();

        setNavListener();

    }

    private void setNavListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                //it's possible to do more actions on several items, if there is a large amount of items I prefer switch(){case} instead of if()
                if (id == R.id.navShare) {
                    Helpers.shareData(getApplicationContext(), settingsVM.getPatientsList());
                }
                //This is for maintaining the behavior of the Navigation view
                NavigationUI.onNavDestinationSelected(menuItem, navController);
                //This is for closing the drawer after acting on it
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patients_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_clear) {
            getPatientsVM().clearAll();
            getSettingsVM().clearAllData();
            getSettingsVM().initNumberPatients();
            Helpers.showToast(getApplicationContext(), "All data has been cleared");

            return true;
        } else {
            return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
