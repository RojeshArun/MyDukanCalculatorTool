package calc.mydukan.com.calculatortool.fragments.allschemes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import calc.mydukan.com.calculatortool.Utils.FireBaseUtils;
import calc.mydukan.com.calculatortool.Utils.Utils;
import calc.mydukan.com.calculatortool.adapters.BrandsAdapter;
import calc.mydukan.com.calculatortool.adapters.GridSpacingItemDecoration;
import calc.mydukan.com.calculatortool.models.Brands;
import calc.mydukan.com.calculatortool.models.MySelectedModel;

/**
 * Created by rojesharunkumar on 06/11/17.
 */

public class AllBrandsFragments extends Fragment implements
        BrandsAdapter.IBrandsItemHolderClick, View.OnClickListener {

    private DatabaseReference brandsRef, mySchemesRef;
    private RecyclerView mBrandsRecycleView;
    private MainActivity mActivity;
    private BrandsAdapter mAdapter;
    private List<Brands> mBrandsList;
    private Button btnAdd;


    public static AllBrandsFragments newInstance() {

        Bundle args = new Bundle();
        AllBrandsFragments fragment = new AllBrandsFragments();
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
        mySchemesRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://otsystem-64e61.firebaseio.com/MySchemes/"
                        + FireBaseUtils.getUid());
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
                if (mActivity != null) {

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (mActivity != null) {
                    mActivity.hideProgressBar();
                }
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
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mAdapter = new BrandsAdapter(this);
        mBrandsRecycleView.setLayoutManager(layoutManager);
        mBrandsRecycleView.addItemDecoration(
                new GridSpacingItemDecoration(2, Utils.dpToPx(getActivity(), 10), true));
        mBrandsRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged(mBrandsList);

        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setText("Add to My Schemes");
        btnAdd.setOnClickListener(this);
        btnAdd.setVisibility(View.VISIBLE);
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
        if (MySelectedSchemesHelper.getInstance().getList() != null && MySelectedSchemesHelper.getInstance().getList().size() > 0) {
            MySelectedModel selectedModel = new MySelectedModel(FireBaseUtils.getUid(), MySelectedSchemesHelper.getInstance().getList());
            mySchemesRef.setValue(selectedModel)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(mActivity, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(mActivity, "Failed to add", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(mActivity, "Please add schemes", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mActivity != null) {
            MySelectedSchemesHelper.getInstance().reset();
            mActivity.hideProgressBar();
        }
    }
}
