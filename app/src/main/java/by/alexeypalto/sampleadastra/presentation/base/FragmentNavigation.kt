package by.alexeypalto.sampleadastra.presentation.base

import android.view.View
import androidx.fragment.app.Fragment

interface FragmentNavigation {
    fun pushFragment(fragment: Fragment, sharedElements: List<Pair<View, String>> = emptyList())
    fun popFragment(popDepth: Int)
}