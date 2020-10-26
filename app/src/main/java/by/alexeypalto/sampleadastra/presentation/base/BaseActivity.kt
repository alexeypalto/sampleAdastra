package by.alexeypalto.sampleadastra.presentation.base

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import by.alexeypalto.sampleadastra.presentation.extensions.show

abstract class BaseActivity @JvmOverloads constructor(@LayoutRes contentLayoutId: Int = 0) : AppCompatActivity(contentLayoutId), ProceedError {

    override fun proceedError(exception: Exception?, messageView: TextView?, action: (() -> Unit)?) {
        if (messageView != null) {
            if (messageView.visibility != View.VISIBLE) {
                messageView.show()
            }
            messageView.text = exception?.message
        }
    }
}