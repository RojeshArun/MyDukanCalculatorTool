package calc.mydukan.com.calculatortool.fragments.myselectedschemes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.List;

import calc.mydukan.com.calculatortool.MainActivity;
import calc.mydukan.com.calculatortool.R;
import calc.mydukan.com.calculatortool.Utils.Utils;
import calc.mydukan.com.calculatortool.adapters.GridSpacingItemDecoration;
import calc.mydukan.com.calculatortool.fragments.allschemes.adapter.MySchemesAdapter;
import calc.mydukan.com.calculatortool.fragments.calculator.AddSchemeFrag;
import calc.mydukan.com.calculatortool.models.Brands;
import calc.mydukan.com.calculatortool.models.Schemes;

/**
 * Created by rojesharunkumar on 06/11/17.
 */

public class MySchemesListingFragment extends Fragment
        implements AdapterView.OnItemClickListener, View.OnClickListener {

    private RecyclerView mBrandsRecycleView;
    private MainActivity mActivity;
    private MySchemesAdapter mAdapter;
    private List<Schemes> mSchemesList;
    private Brands currentBrand;
    private Button btnAdd;


    public static MySchemesListingFragment newInstance(Brands brand) {

        Bundle args = new Bundle();
        args.putSerializable("brand", brand);
        MySchemesListingFragment fragment = new MySchemesListingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity.showProgressBar();
        if (getArguments() != null) {
            currentBrand = (Brands) getArguments().getSerializable("brand");
            mSchemesList = currentBrand.getMySelectedSchemesList();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schemes_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setText("Select My Schemes");
        btnAdd.setOnClickListener(this);
        mBrandsRecycleView = view.findViewById(R.id.lst_schemes);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mAdapter = new MySchemesAdapter();
        mBrandsRecycleView.setLayoutManager(layoutManager);
        mBrandsRecycleView.addItemDecoration(
                new GridSpacingItemDecoration(2, Utils.dpToPx(getActivity(), 10), true));
        mBrandsRecycleView.setAdapter(mAdapter);
        if (mSchemesList != null) {
            mAdapter.notifyDataSetChanged(mSchemesList);
        }

        mActivity.hideProgressBar();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mActivity != null) {
            mActivity.hideProgressBar();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
              //  saveMySelectedSchemes();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        gotoAddSchemeFragment();
        return super.onOptionsItemSelected(item);

    }

    private void gotoAddSchemeFragment() {
      //  AddSchemeFrag frag =AddSchemeFrag.newInstance();
    }
}
