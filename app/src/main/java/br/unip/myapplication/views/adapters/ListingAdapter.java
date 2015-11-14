package br.unip.myapplication.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.unip.myapplication.R;
import br.unip.myapplication.models.Location;
import br.unip.myapplication.utils.AppUtil;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

    private List<Location> mItens;

    private OnLocationClickListener mOnLocationClickListener;
    private OnLongLocationClickListener mOnLongLocationClickListener;

    public ListingAdapter(List<Location> itens, OnLocationClickListener mOnLocationClickListener,
                          OnLongLocationClickListener mOnLongLocationClickListener) {
        mItens = itens;
        this.mOnLocationClickListener = mOnLocationClickListener;
        this.mOnLongLocationClickListener = mOnLongLocationClickListener;
    }

    public void setItens(List<Location> itens) {
        this.mItens = itens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.listing_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Location location = mItens.get(position);

        holder.mTextViewDescription.setText(location.getDescription());
        holder.mTextViewDate.setText(AppUtil.formatDate(location.getData()));
        holder.mTextViewDate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_calendar, 0, 0, 0);

        holder.mImageViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLocationClickListener != null) {
                    mOnLocationClickListener.onLocationClick();
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongLocationClickListener != null) {
                    mOnLongLocationClickListener.onCardClick();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItens == null ? 0 : mItens.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.mImageViewLocation.setOnClickListener(null);
        super.onViewRecycled(holder);
    }

    public interface OnLocationClickListener {
        public void onLocationClick();
    }

    public interface OnLongLocationClickListener {
        public void onCardClick();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewDescription;
        private final TextView mTextViewDate;
        private final ImageView mImageViewLocation;

        public ViewHolder(final View view) {
            super(view);
            mTextViewDescription = AppUtil.get(view.findViewById(R.id.textViewDescription));
            mTextViewDate = AppUtil.get(view.findViewById(R.id.textViewDate));
            mImageViewLocation = AppUtil.get(view.findViewById(R.id.imageViewLocation));
        }
    }

}
