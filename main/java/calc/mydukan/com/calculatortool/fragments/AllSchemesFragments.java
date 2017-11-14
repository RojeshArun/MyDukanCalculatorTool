package calc.mydukan.com.calculatortool.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import calc.mydukan.com.calculatortool.Helper.MySelectedSchemesHelper;
import calc.mydukan.com.calculatortool.MainActivity;
import calc.mydukan.com.calculatortool.R;
import calc.mydukan.com.calculatortool.Utils.Utils;
import calc.mydukan.com.calculatortool.adapters.BrandsAdapter;
import calc.mydukan.com.calculatortool.adapters.GridSpacingItemDecoration;
import calc.mydukan.com.calculatortool.models.Brands;

/**
 * Created by rojesharunkumar on 06/11/17.
 */

public class AllSchemesFragments extends Fragment implements
        BrandsAdapter.IBrandsItemHolderClick, View.OnClickListener {

    private DatabaseReference brandsRef;
    private RecyclerView mBrandsRecycleView;
    private MainActivity mActivity;
    private BrandsAdapter mAdapter;
    private List<Brands> mBrandsList;
    private Button btnAdd;


    public static AllSchemesFragments newInstance() {

        Bundle args = new Bundle();
        AllSchemesFragments fragment = new AllSchemesFragments();
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
        brandsRef = FirebaseDatabase.getInstance().getReference("brands");
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity.showProgressBar();
        mBrandsList = new ArrayList<>();
        brandsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Brands brandItem;

                for (DataSnapshot brand : dataSnapshot.getChildren()
                        ) {
                    brandItem = brand.getValue(Brands.class);
                    mBrandsList.add(brandItem);
                }
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged(mBrandsList);
                }
                mActivity.hideProgressBar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mActivity.hideProgressBar();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schemes_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBrandsRecycleView = view.findViewById(R.id.lst_schemes);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setText("Add to My Schemes");
        btnAdd.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mAdapter = new BrandsAdapter(this);
        mBrandsRecycleView.setLayoutManager(layoutManager);
        mBrandsRecycleView.addItemDecoration(
                new GridSpacingItemDecoration(2, Utils.dpToPx(getActivity(), 10), true));
        mBrandsRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged(mBrandsList);

    }


    private void gotoSchemesFragment(String brandId) {
        SchemesListingFragment fragment = SchemesListingFragment.newInstance(brandId);
        mActivity.addFragment(fragment, true);
    }

    @Override
    public void onItemClick(int pos) {
        Brands selectedBrand = mBrandsList.get(pos);
        MySelectedSchemesHelper.getInstance().addBrand(selectedBrand);
        gotoSchemesFragment(selectedBrand.getBrandId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                AddToMySchemes();
                break;
        }

    }

    private void AddToMySchemes() {
        MySelectedSchemesHelper.getInstance().getList();
    }


}
