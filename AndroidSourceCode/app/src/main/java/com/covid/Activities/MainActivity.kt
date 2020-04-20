package com.covid.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.covid.Adapters.DrawerListAdapter
import com.covid.Fragments.AboutFragment
import com.covid.Fragments.HistoryFragment
import com.covid.Fragments.HomeFragment
import com.covid.Model.NavigationItems
import com.covid.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener, View.OnClickListener {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var imgHeaderImage: ImageView? = null
    private var tvHeaderName: TextView? = null
    private var tvHeaderDescription: TextView? = null
    private var rlNavigationDrawer: RelativeLayout? = null
    private var drawerLayout: DrawerLayout? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private val navigationItemsList = ArrayList<NavigationItems>()
    private var rvNavigationItems: RecyclerView? = null
    private var toolbar: Toolbar? = null
    private var adapter: DrawerListAdapter? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var flag: Int = 0
    private var fragmentPosition: Int = 0
    private var firstTimeClick = true
    private var imgShoppingCart: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flag = 0
        init()
        showUI()
        onClickListener()
        fragmentPosition = 1
        bottomNavigationView!!.setOnNavigationItemReselectedListener(this)
        bottomNavigationView!!.setOnNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            bottomNavigationView!!.selectedItemId = R.id.bottom_nav_home // change to whichever id should be default
        }
    }

    private fun init() {
        imgHeaderImage = findViewById(R.id.img_profile) as ImageView
        tvHeaderName = findViewById(R.id.tv_name) as TextView
        tvHeaderDescription = findViewById(R.id.tv_description) as TextView
        rlNavigationDrawer = findViewById(R.id.rl_drawer) as RelativeLayout
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        rvNavigationItems = findViewById(R.id.rv_navlist) as RecyclerView
        toolbar = findViewById(R.id.toolbar) as Toolbar
        bottomNavigationView = findViewById(R.id.bottom_navigation) as BottomNavigationView
        imgShoppingCart = findViewById(R.id.img_shopping_cart) as ImageView
    }

    private fun showUI() {
        setUpToolbar()
        val layoutManager1 = LinearLayoutManager(this)
        rvNavigationItems!!.layoutManager = layoutManager1
        navigationItemsList.add(NavigationItems("Home", "Meetup destination", R.drawable.bottom_nav_home))
        navigationItemsList.add(NavigationItems("Preferences", "Change your preferences", R.drawable.bottom_nav_home))
        navigationItemsList.add(NavigationItems("About", "Get to know about us", R.drawable.bottom_nav_home))
        adapter = DrawerListAdapter(this, navigationItemsList)
        rvNavigationItems!!.adapter = adapter
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout!!.addDrawerListener(mDrawerToggle!!)
        mDrawerToggle!!.syncState()
        supportActionBar!!.setHomeButtonEnabled(true)

    }

    private fun onClickListener() {
        adapter!!.setOnItemClickListener(object : DrawerListAdapter.ItemClickListener {
            override fun onItemClick(position: Int, v: View) {
                selectItemFromDrawer(position)
            }
        })
        imgShoppingCart!!.setOnClickListener(this)


    }

    private fun selectItemFromDrawer(position: Int) {
        val fragment: Fragment
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> {
                drawerLayout!!.closeDrawers()
                handler = Handler()
                runnable = Runnable {
                    handler?.removeCallbacks(runnable)
                    val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                    startActivity(intent)
                }
                handler?.postDelayed(runnable, 200)
            }
            2 -> fragment = HistoryFragment()
        }//loadFragment(fragment);
        //loadFragment(fragment);
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.bottom_nav_home -> {
                fragment = HomeFragment()
                loadFragment(fragment, getString(R.string.home_fragment), 1)
                return true
            }
            R.id.bottom_nav_history -> {
                fragment = HistoryFragment()
                loadFragment(fragment, getString(R.string.history_fragment), 2)
                return true
            }
            R.id.bottom_nav_about -> {
                fragment = AboutFragment()
                loadFragment(fragment, getString(R.string.about_fragment), 3)
                return true
            }
        }
        return false
    }

    private fun loadFragment(fragment: Fragment, fragmentName: String, position: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (fragmentPosition < position) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_right)
        }
        fragmentPosition = position
        fragmentTransaction.replace(R.id.rl_main_content, fragment)
        val count = fragmentManager.backStackEntryCount
        if (!fragmentName.equals(getString(R.string.home_fragment), ignoreCase = true)) {
            fragmentTransaction.addToBackStack(fragmentName)
        }
        fragmentTransaction.commit()
        drawerLayout!!.closeDrawer(rlNavigationDrawer!!)
    }


    override fun onBackPressed() {
        if (fragmentPosition == 1) {
            if (firstTimeClick) {
                firstTimeClick = false
                Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_LONG).show()
            } else {
                finish()
            }
        } else {
            firstTimeClick = true
            val fragmentManager = supportFragmentManager
            for (i in 0 until fragmentManager.backStackEntryCount) {
                fragmentManager.popBackStack()
            }
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_right)
            fragmentPosition = 1
            fragmentTransaction.replace(R.id.rl_main_content, HomeFragment())
            bottomNavigationView!!.menu.getItem(0).isChecked = true
            fragmentTransaction.commit()
        }
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        if (item.itemId == R.id.bottom_nav_home && flag == 0) {
            val fragment = HomeFragment()
            flag++
            loadHomeFragment(fragment)
        }
    }

    private fun loadHomeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.rl_main_content, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_shopping_cart -> {
                val intent = Intent(this@MainActivity, CartActivity::class.java)
                startActivity(intent)
            }
        }
    }

    /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainsearch, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        View v = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        v.setBackground(null);

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }*/

}
