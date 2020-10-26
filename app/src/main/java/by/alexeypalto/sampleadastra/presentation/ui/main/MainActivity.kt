package by.alexeypalto.sampleadastra.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.presentation.base.BaseActivity
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.base.FragmentNavigation
import by.alexeypalto.sampleadastra.presentation.ui.allmemes.AllMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.favoritememes.FavoriteMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.views.BottomNavigation
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavTransactionOptions
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

private const val KEY_IS_ROOT = "KEY_IS_ROOT"

class MainActivity : BaseActivity(),
    FragNavController.TransactionListener,
    FragNavController.RootFragmentListener,
    FragmentNavigation {

    private val fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.am_container)
    private var options = FragNavTransactionOptions.newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation(savedInstanceState)
    }

    override val numberOfRootFragments: Int = 2

    override fun onBackPressed() {
        if (fragNavController.currentStack?.size!! > 1) {
            val fragment = fragNavController.currentFrag as? BaseFragment
            if (fragment?.onBackPressed() != true) {
                fragNavController.popFragment(options)
            }
        } else {
            finishAffinity()
        }
    }

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            BottomNavigation.NavigationMenu.HOME.ordinal -> AllMemesFragment.newInstance()
            BottomNavigation.NavigationMenu.FAVORITE.ordinal -> FavoriteMemesFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        supportActionBar?.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())
    }

    override fun pushFragment(fragment: Fragment, sharedElements: List<Pair<View, String>>) {
        options = FragNavTransactionOptions.newBuilder()
            .sharedElements(sharedElements.toMutableList())
            .allowReordering(true)
            .build()
        if (!fragNavController.isStateSaved) {
            fragNavController.pushFragment(fragment, options)
        }
    }

    override fun popFragment(popDepth: Int) {
        fragNavController.popFragments(popDepth)
    }

    private fun initNavigation(savedInstanceState: Bundle?) {
        fragNavController.apply {
            transactionListener = this@MainActivity
            rootFragmentListener = this@MainActivity
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                    Timber.e(throwable, message)
                }
            }
            fragmentHideStrategy = FragNavController.DETACH
        }

        fragNavController.initialize(BottomNavigation.NavigationMenu.HOME.ordinal, savedInstanceState)

        if (savedInstanceState == null) {
            am_navigation.selectTabAtPosition(BottomNavigation.NavigationMenu.HOME.ordinal)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(!savedInstanceState.getBoolean(KEY_IS_ROOT, false))
        }

        fragNavController.executePendingTransactions()

        am_navigation.onTabClickListener = {
            fragNavController.switchTab(it.ordinal)
        }
        am_navigation.onTabReselectListener = {
            fragNavController.clearStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

}