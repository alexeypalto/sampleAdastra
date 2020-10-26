package by.alexeypalto.sampleadastra.presentation.base

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import by.alexeypalto.sampleadastra.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment @JvmOverloads constructor(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId), FragmentNavigation, ProceedError, HasAndroidInjector {

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }

    private var fragmentNavigation: FragmentNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (this is Injectable) {
            AndroidSupportInjection.inject(this)
        }
        if (context is FragmentNavigation) {
            fragmentNavigation = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentNavigation = null
    }

    override fun pushFragment(fragment: Fragment, sharedElements: List<Pair<View, String>>) {
        fragmentNavigation?.pushFragment(fragment, sharedElements)
    }

    override fun popFragment(popDepth: Int) {
        fragmentNavigation?.popFragment(popDepth)
    }

    protected fun setTitle(titleResId: Int) {
        activity?.setTitle(titleResId)
    }

    override fun proceedError(exception: Exception?, messageView: TextView?, action: (() -> Unit)?) {
        (activity as? ProceedError)?.proceedError(exception, messageView, action)
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}