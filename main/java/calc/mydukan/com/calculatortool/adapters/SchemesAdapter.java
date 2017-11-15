package calc.mydukan.com.calculatortool.adapters;

import android.support.v4.app.Fragment;
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
import calc.mydukan.com.calculatortool.fragments.allschemes.adapter.MySchemesAdapter;
import calc.mydukan.com.calculatortool.models.Schemes;

/**
 * Created by rojesharunkumar on 20/10/17.
 */

public class SchemesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Schemes> schemeList;
    private SchemesAdapter.ISchemessItemHolderClick onClick;

    public SchemesAdapter(Fragment frag) {
        schemeList = SchemeHelper.getInstance().getSchemeList();
        onClick = (ISchemessItemHolderClick) frag;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.scheme_item, parent, false);
        return new SchemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SchemeViewHolder schemeViewHolder = (SchemeViewHolder) holder;
        Schemes scheme = schemeList.get(position);

        schemeViewHolder.txtTitle.setText(scheme.getSchemeName());
        schemeViewHolder.chkScheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                SchemeHelper.getInstance().setChecked(isChecked, position);
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
        return schemeList.size();
    }

    public void notifyDataSetChanged(List<Schemes> mSchemesList) {
        SchemeHelper.getInstance().updateList(mSchemesList);
        this.schemeList = mSchemesList;
        notifyDataSetChanged();
    }

    public static class SchemeViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        CheckBox chkScheme;

        public SchemeViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_scheme_title);
            chkScheme = itemView.findViewById(R.id.chk_scheme);

        }
    }

    public interface ISchemessItemHolderClick {
        void onItemClick(int pos);
    }
}
