package com.prashant.demowipro.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.prashant.demowipro.R;
import com.prashant.demowipro.databinding.AboutAdapterBinding;
import com.prashant.demowipro.model.bean.RowsItem;
import com.prashant.demowipro.viewmodel.RawItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The type About adapter.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.BindingHolder>  {
    private List<RowsItem> rowsItems;

    /**
     * Instantiates a new About adapter.
     */
    public AboutAdapter() {
        rowsItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        AboutAdapterBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent
                .getContext()), R.layout.about_adapter, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        RawItemViewModel movieViewModel = new RawItemViewModel(rowsItems.get(position));
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    public int getItemCount() {
        return rowsItems.size();
    }

    /**
     * Add item.
     *
     * @param rowsItem the rows item
     */
    public void addItem(List<RowsItem> rowsItem) {
        rowsItems.addAll(rowsItem);
        notifyItemInserted(rowsItems.size() - 1);
    }

    /**
     * Clear items.
     */
    public void clearItems() {
        rowsItems.clear();
        notifyDataSetChanged();
    }

    /**
     * The type Binding holder.
     */
    public static class BindingHolder extends RecyclerView.ViewHolder {
        private AboutAdapterBinding itemBinding;

        /**
         * Instantiates a new Binding holder.
         *
         * @param itemBinding the item binding
         */
        public BindingHolder(AboutAdapterBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}
