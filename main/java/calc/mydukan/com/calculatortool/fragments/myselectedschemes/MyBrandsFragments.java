package calc.mydukan.com.calculatortool.fragments.myselectedschemes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

public class MyBrandsFragments extends Fragment implements BrandsAdapter.IBrandsItemHolderClick {

    FirebaseAuth auth;
    DatabaseReference mySchemsRef;
    List<Brands> mySelectedList;
    private MainActivity mActivity;
    private BrandsAdapter mAdapter;
    private RecyclerView mBrandsRecycleView;
    ValueEventListener listener;


    public static MyBrandsFragments newInstance() {

        Bundle args = new Bundle();

        MyBrandsFragments fragment = new MyBrandsFragments();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        mActivity.showProgressBar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();

        mySchemsRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://otsystem-64e61.firebaseio.com/MySchemes/" + FireBaseUtils.getUid());

       listener = mySchemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mActivity != null && isAdded()) {
                    if (dataSnapshot.getValue() != null) {
                        MySelectedModel mySelectedModel =dataSnapshot.getValue(MySelectedModel.class);
                        mySelectedList = mySelectedModel.getMySelectedSchemes();
                        if(mAdapter != null){
                            mAdapter.notifyDataSetChanged(mySelectedList);
                        }
                    } else {
                        Toast.makeText(mActivity, "No Schemes Selected", Toast.LENGTH_SHORT).show();
                        mActivity.clearStack();
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
        mAdapter.notifyDataSetChanged(mySelectedList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mActivity != null) {
            mActivity.hideProgressBar();
            mySchemsRef.removeEventListener(listener);
        }
    }


    @Override
    public void onItemClick(int pos) {
        gotoMySchemesFragment(mySelectedList.get(pos));
    }

    private void gotoMySchemesFragment(Brands brand) {
        MySchemesListingFragment frag = MySchemesListingFragment.newInstance(brand);
        mActivity.addFragment(frag,true);
    }
}
