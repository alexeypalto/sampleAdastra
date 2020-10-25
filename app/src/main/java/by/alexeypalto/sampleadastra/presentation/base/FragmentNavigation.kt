package by.alexeypalto.sampleadastra.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

interface FragmentNavigation {
    fun pushFragment(fragment: Fragment, sharedElements: List<Pair<View, String>> = emptyList())
    fun pushFragment(kClass: KClass<out Fragment>, args: Bundle = Bundle(), sharedElements: List<Pair<View, String>> = emptyList())
    fun popFragment(popDepth: Int)
}