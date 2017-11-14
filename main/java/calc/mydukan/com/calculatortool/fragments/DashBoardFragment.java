package calc.mydukan.com.calculatortool.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import calc.mydukan.com.calculatortool.MainActivity;
import calc.mydukan.com.calculatortool.R;

/**
 * Created by rojesharunkumar on 06/11/17.
 */

public class DashBoardFragment extends Fragment implements View.OnClickListener {

    Button btnCalculator, btnAllSchemes, btnMySchemes, btnEarnings;
    private MainActivity mActivity;
    private Fragment fragment;
    private FirebaseAuth auth;

    public static DashBoardFragment newInstance() {

        Bundle args = new Bundle();
        DashBoardFragment fragment = new DashBoardFragment();
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        btnCalculator = view.findViewById(R.id.btn_calculator);
        btnCalculator.setOnClickListener(this);

        btnAllSchemes = view.findViewById(R.id.btn_all_schemes);
        btnAllSchemes.setOnClickListener(this);


        btnMySchemes = view.findViewById(R.id.btn_my_schemes);
        btnMySchemes.setOnClickListener(this);


        btnEarnings = view.findViewById(R.id.btn_earnings);
        btnEarnings.setOnClickListener(this);

        // TODO id: rojesh@gmail.com / arunkumar
        // tempAutoLogin();

        loginUser("rojesh@gmail.com", "arunkumar");

    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });

    }

    private void tempAutoLogin() {

        auth.createUserWithEmailAndPassword("rojesh1@gmail.com", "arunkumar")
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_calculator:
                loadCalculatorFragment();
                break;
            case R.id.btn_all_schemes:
                loadSchemesFragment();
                break;
            case R.id.btn_my_schemes:
                loadMySchemesFragment();
                break;
            case R.id.btn_earnings:
                loadMyEarnings();
                break;
            default:
                break;
        }
    }

    private void loadMyEarnings() {
        fragment = EarningsFragments.newInstance();
        mActivity.addFragment(fragment, true);

    }

    private void loadMySchemesFragment() {
        fragment = MySchemesFragments.newInstance();
        mActivity.addFragment(fragment, true);
    }

    private void loadSchemesFragment() {
        fragment = AllSchemesFragments.newInstance();
        mActivity.addFragment(fragment, true);

    }

    private void loadCalculatorFragment() {
        CalculatorForm calculatorForm = CalculatorForm.newInstance();
        mActivity.addFragment(calculatorForm, true);
    }
}
