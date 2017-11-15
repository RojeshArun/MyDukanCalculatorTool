package calc.mydukan.com.calculatortool.fragments.allschemes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import calc.mydukan.com.calculatortool.Helper.SchemeHelper;
import calc.mydukan.com.calculatortool.R;
import calc.mydukan.com.calculatortool.adapters.BrandsAdapter;
import calc.mydukan.com.calculatortool.models.Schemes;

/**
 * Created by rojesharunkumar on 20/10/17.
 */

public class MySchemesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Schemes> schemeList;

    public MySchemesAdapter() {
        schemeList = SchemeHelper.getInstance().getSchemeList();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.my_scheme_item, parent, false);
        return new SchemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SchemeViewHolder schemeViewHolder = (SchemeViewHolder) holder;
        Schemes scheme = schemeList.get(position);

        schemeViewHolder.txtTitle.setText(scheme.getSchemeName());


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return schemeList.size();
    }

    public void notifyDataSetChanged(List<Schemes> mSchemesList) {
        SchemeHelper.getInstance().updateList(mSchemesList);
        this.schemeList = mSchemesList;
        notifyDataSetChanged();
    }

    public static class SchemeViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public SchemeViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_scheme_title);

        }
    }
}
