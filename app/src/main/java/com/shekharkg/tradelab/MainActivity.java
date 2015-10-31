package com.shekharkg.tradelab;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.shekharkg.tradelab.fragment.AddNewEntryFragment;
import com.shekharkg.tradelab.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private DrawerLayout drawer;
  private MaterialSearchView searchView;
  private MenuItem item;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    searchView = (MaterialSearchView) findViewById(R.id.search_view);
    searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        //Do some magic
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        //Do some magic
        return false;
      }
    });

    searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {
        //Do some magic
      }

      @Override
      public void onSearchViewClosed() {
        //Do some magic
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    item = menu.findItem(R.id.action_search);
    searchView.setMenuItem(item);
    return true;
  }


  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    if (id == R.id.nav_manage) {
      transaction.replace(R.id.container, new HomeFragment()).commit();
      this.item.setVisible(true);
    } else if (id == R.id.add_details) {
      transaction.replace(R.id.container, new AddNewEntryFragment()).commit();
      this.item.setVisible(false);
      searchView.closeSearch();
    }
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
