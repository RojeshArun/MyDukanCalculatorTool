package calc.mydukan.com.calculatortool.Helper;

import java.util.ArrayList;
import java.util.List;

import calc.mydukan.com.calculatortool.models.Schemes;

/**
 * Created by rojesharunkumar on 20/10/17.
 */

public class SchemeHelper {
    private List<Schemes> schemeList;
    private List<Schemes> mySelectedList;
    private int size = 0;

    static SchemeHelper mInstance;

    private SchemeHelper() {
        schemeList = new ArrayList<>();
        mySelectedList = new ArrayList<>();
    }

    public static SchemeHelper getInstance() {
        if (mInstance == null) {
            mInstance = new SchemeHelper();
        }
        return mInstance;
    }


    public List<Schemes> getSchemeList() {
        return schemeList;
    }

    public List<Schemes> getMySelectedList() {
        return mySelectedList;
    }

    public boolean addScheme(Schemes Scheme) {
        if (schemeList == null) {
            schemeList = new ArrayList<>();
        }
        schemeList.add(Scheme);
        return true;
    }

    public int getListSize() {
        if (schemeList != null) {
            size = schemeList.size();
        }
        return size;
    }

    public void resetScheme() {
        schemeList = new ArrayList<>();
        mySelectedList = new ArrayList<>();
    }


    public void setChecked(boolean isChecked, int position) {
        schemeList.get(position).setSelected(isChecked);
        if (isChecked) {
            mySelectedList.add(schemeList.get(position));
        } else {
            mySelectedList.remove(schemeList.get(position));
        }

    }

    public void updateList(List<Schemes> mSchemesList) {
        schemeList = mSchemesList;
    }


}
