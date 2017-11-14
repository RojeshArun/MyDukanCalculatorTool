package calc.mydukan.com.calculatortool.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calc.mydukan.com.calculatortool.R;
import calc.mydukan.com.calculatortool.fragments.AllSchemesFragments;
import calc.mydukan.com.calculatortool.models.Brands;

/**
 * Created by rojesharunkumar on 20/10/17.
 */

public class BrandsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Brands> brandsList;
    private IBrandsItemHolderClick onClick;


    public BrandsAdapter(AllSchemesFragments allSchemesFragments) {
        brandsList = new ArrayList<>();
        onClick = allSchemesFragments;
    }

    public interface IBrandsItemHolderClick {
        void onItemClick(int pos);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.brands_item, parent, false);
        return new BrandsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BrandsViewHolder brandsViewHolder = (BrandsViewHolder) holder;
        Brands brands = brandsList.get(position);
        brandsViewHolder.txtBrandTitle.setText(brands.getBrandTitle());
        brandsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }

    public void notifyDataSetChanged(List<Brands> mBrandsList) {
        if (mBrandsList != null) {
            this.brandsList = mBrandsList;
        }
        notifyDataSetChanged();
    }

    public static class BrandsViewHolder extends RecyclerView.ViewHolder {
        TextView txtBrandTitle;

        public BrandsViewHolder(View itemView) {
            super(itemView);
            txtBrandTitle = itemView.findViewById(R.id.txt_brand_title);
        }
    }

}
