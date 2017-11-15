package calc.mydukan.com.calculatortool.fragments.allschemes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import calc.mydukan.com.calculatortool.Helper.MySelectedSchemesHelper;
import calc.mydukan.com.calculatortool.Helper.SchemeHelper;
import calc.mydukan.com.calculatortool.MainActivity;
import calc.mydukan.com.calculatortool.R;
import calc.mydukan.com.calculatortool.Utils.Utils;
import calc.mydukan.com.calculatortool.adapters.GridSpacingItemDecoration;
import calc.mydukan.com.calculatortool.adapters.SchemesAdapter;
import calc.mydukan.com.calculatortool.models.Schemes;

/**
 * Created by rojesharunkumar on 06/11/17.
 */

public class SchemesListingFragment extends Fragment
        implements View.OnClickListener,SchemesAdapter.ISchemessItemHolderClick {

    private DatabaseReference brandsRef;
    private RecyclerView mBrandsRecycleView;
    private MainActivity mActivity;
    private SchemesAdapter mAdapter;
    private List<Schemes> mSchemesList;
    private String brandId;
    private int brandPos =0;
    ValueEventListener brandsListener;


    public static SchemesListingFragment newInstance(String brandId) {

        Bundle args = new Bundle();
        args.putString("brand_id", brandId);
     //   args.putInt("pos",pos);
        SchemesListingFragment fragment = new SchemesListingFragment();
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
        mActivity.showProgressBar();
        if (getArguments() != null) {
            brandId = getArguments().getString("brand_id");
        //    brandPos = getArguments().getInt("pos");

        }
        brandsRef = FirebaseDatabase.getInstance().getReference("schemes").child(brandId);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSchemesList = new ArrayList<>();
        SchemeHelper.getInstance().resetScheme();
       brandsListener =  brandsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Schemes brandItem;

                for (DataSnapshot brand : dataSnapshot.getChildren()
                        ) {
                    brandItem = brand.getValue(Schemes.class);
                    mSchemesList.add(brandItem);
                }
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged(mSchemesList,brandId);
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
        super.onViewCreated(view, savedInstanceState);/*
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setText("Select My Schemes");
        btnAdd.setOnClickListener(this);*/
        mBrandsRecycleView = view.findViewById(R.id.lst_schemes);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mAdapter = new SchemesAdapter(this);
        mBrandsRecycleView.setLayoutManager(layoutManager);
        mBrandsRecycleView.addItemDecoration(
                new GridSpacingItemDecoration(2, Utils.dpToPx(getActivity(), 10), true));
        mBrandsRecycleView.setAdapter(mAdapter);
        if (mSchemesList != null) {
            mAdapter.notifyDataSetChanged(mSchemesList,"");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mActivity != null) {
            mActivity.hideProgressBar();
            brandsRef.removeEventListener(brandsListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:

                break;
        }
    }

    private void saveMySelectedSchemes(int pos) {

        List<Schemes> updatedScheme = SchemeHelper.getInstance().getMySelectedList();
        MySelectedSchemesHelper.getInstance().updateBrand(updatedScheme,brandId);

    }


    @Override
    public void onItemClick(int pos) {
        saveMySelectedSchemes(brandPos);
    }

}
