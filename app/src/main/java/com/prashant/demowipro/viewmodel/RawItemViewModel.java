package com.prashant.demowipro.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prashant.demowipro.R;
import com.prashant.demowipro.model.bean.RowsItem;

/**
 * The type Raw item view model.
 */
public class RawItemViewModel extends BaseObservable {

    /**
     * The Rows item.
     */
    private RowsItem rowsItem;

    /**
     * Instantiates a new Raw item view model.
     *
     * @param rowsItem the rows item
     */
    public RawItemViewModel(RowsItem rowsItem) {
        this.rowsItem = rowsItem;
    }


    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return rowsItem.getImageHref();
    }

    /**
     * Load image.
     *
     * @param imageView the image view
     * @param url       the url
     */
    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(imageView.getContext()).load(url)
                .apply(options)

                .into(imageView);

    }


    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return rowsItem.getTitle();
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return rowsItem.getDescription();
    }

}
