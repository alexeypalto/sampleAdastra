package by.alexeypalto.sampleadastra.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.presentation.base.BaseActivity
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.base.FragmentNavigation
import by.alexeypalto.sampleadastra.presentation.ui.allmemes.AllMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.favoritememes.FavoriteMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.meme.MemeFragment
import by.alexeypalto.sampleadastra.presentation.ui.views.BottomNavigation
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KClass

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
            BottomNavigation.NavigationMenu.HOME.ordinal ->
                supportFragmentManager.fragmentFactory.instantiate(classLoader, AllMemesFragment::class.java.name)
            BottomNavigation.NavigationMenu.FAVORITE.ordinal ->
                supportFragmentManager.fragmentFactory.instantiate(classLoader, AllMemesFragment::class.java.name)
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

    override fun pushFragment(kClass: KClass<out Fragment>, args: Bundle, sharedElements: List<Pair<View, String>>) {
        classLoader?.let {
            pushFragment(supportFragmentManager.fragmentFactory.instantiate(it, kClass.java.name).apply {
                arguments = args
            }, sharedElements)
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

}