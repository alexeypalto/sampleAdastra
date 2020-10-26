package by.alexeypalto.sampleadastra.presentation.ui.items

import android.view.View
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.presentation.adapter.BaseListItem
import by.alexeypalto.sampleadastra.presentation.extensions.convertToString
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_meme.view.*

class MemeItem(viewState: MemeViewState): BaseListItem<MemeViewState>(viewState) {

    override fun getViewId(): Int = R.layout.item_meme

    override fun renderView(view: View, positionInAdapter: Int) {
        with(view) {
            Glide.with(this)
                .load(viewState.url)
                .placeholder(R.drawable.ic_placeholder_fallback)
                .fallback(R.drawable.ic_placeholder_fallback)
                .into(im_img)
            im_date.text = context.getString(R.string.meme_created_text, viewState.createdAt.convertToString())
        }
    }

    override fun recycleView(view: View?) {
        view?.let { Glide.with(it).clear(view.im_img) }
    }
}