package by.alexeypalto.sampleadastra.presentation.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.di.FragmentFactory
import by.alexeypalto.sampleadastra.di.Injectable
import by.alexeypalto.sampleadastra.presentation.extensions.getDrawableById
import by.alexeypalto.sampleadastra.presentation.extensions.show
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity @JvmOverloads constructor(@LayoutRes contentLayoutId: Int = 0) : AppCompatActivity(contentLayoutId), ProceedError, HasAndroidInjector, Injectable {

    var toolbar: Toolbar? = null

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            AndroidInjection.inject(this)
            supportFragmentManager.fragmentFactory = fragmentFactory
        } catch (e: Exception) {
            Timber.e(e)
        }
        super.onCreate(savedInstanceState)
    }

    protected fun setToolbar(toolbar: Toolbar,
                             displayShowTitleEnabled: Boolean = false,
                             setDisplayHomeAsUpEnabled: Boolean = false) {
        this.toolbar = toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(displayShowTitleEnabled)
        supportActionBar?.setHomeAsUpIndicator(getDrawableById(R.drawable.ic_back))
        supportActionBar?.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled)
    }

    override fun proceedError(exception: Exception?, messageView: TextView?, action: (() -> Unit)?) {
        if (messageView != null) {
            if (messageView.visibility != View.VISIBLE) {
                messageView.show()
            }
            messageView.text = exception?.message
        }
    }
}