package calc.mydukan.com.calculatortool.fragments.myselectedschemes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rojesharunkumar on 15/11/17.
 */

public class CreateSchemeFragment extends Fragment {

    public static CreateSchemeFragment newInstance() {

        Bundle args = new Bundle();

        CreateSchemeFragment fragment = new CreateSchemeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
