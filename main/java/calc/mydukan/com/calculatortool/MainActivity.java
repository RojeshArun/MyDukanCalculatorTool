package calc.mydukan.com.calculatortool;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import calc.mydukan.com.calculatortool.fragments.CalculatorForm;
import calc.mydukan.com.calculatortool.fragments.DashBoardFragment;

public class MainActivity extends AppCompatActivity implements
        CalculatorForm.OnFragmentInteractionListener, DateListener {


    // Fragment Variablse
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    LinearLayout mProgressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTheFragment();

    }

    private void setTheFragment() {
        mProgressBarLayout = (LinearLayout) findViewById(R.id.lyt_progressbar);

        fragment = DashBoardFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
        addFragment(fragment, false);

    }

    public void addFragment(Fragment fragment, boolean b) {
        fragmentTransaction = fragmentManager.beginTransaction();
        if (b) {
            fragmentTransaction.add(R.id.container, fragment)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        } else {
            fragmentTransaction.add(R.id.container, fragment)
                    .commit();
        }
    }

    public void hideProgressBar() {
     mProgressBarLayout.setVisibility(View.GONE);
    }

    public void showProgressBar() {
        mProgressBarLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setListener(this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void getMonthYear(int month, int year) {
        if (fragment != null && fragment instanceof CalculatorForm) {
            ((CalculatorForm) fragment).setMonthYear(month, year);
        }
    }

    public void clearCurrentFragment() {
        fragmentManager.popBackStack();
        fragmentManager.executePendingTransactions();
    }

    public void notifyListDataChanged() {
        fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment != null && fragment instanceof  CalculatorForm) {
            ((CalculatorForm) fragment).notifyData();
        }
    }
}
