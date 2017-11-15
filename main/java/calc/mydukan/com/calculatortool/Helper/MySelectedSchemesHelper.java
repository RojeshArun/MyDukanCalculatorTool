package calc.mydukan.com.calculatortool.Helper;

import java.util.ArrayList;
import java.util.List;

import calc.mydukan.com.calculatortool.models.Brands;
import calc.mydukan.com.calculatortool.models.Schemes;

/**
 * Created by rojesharunkumar on 14/11/17.
 */

public class MySelectedSchemesHelper {

    private List<Brands> mySelectedList;
    private Brands myCurrentBrand;

    private static MySelectedSchemesHelper instance;

    private MySelectedSchemesHelper() {
        mySelectedList = new ArrayList<>();
    }

    public static MySelectedSchemesHelper getInstance() {
        if (instance == null) {
            instance = new MySelectedSchemesHelper();
        }
        return instance;
    }

    public List<Brands> getList() {
        return mySelectedList;
    }

    public void addBrand(Brands selectedBrand) {
        if (!mySelectedList.contains(selectedBrand)) {
            myCurrentBrand = selectedBrand;
            mySelectedList.add(selectedBrand);
        }
    }

    public void updateBrand(List<Schemes> updatedScheme) {
        if (mySelectedList.contains(myCurrentBrand) && updatedScheme != null) {
            mySelectedList.remove(myCurrentBrand);
            myCurrentBrand.setMySelectedSchemesList(updatedScheme);
            mySelectedList.add(myCurrentBrand);
        }
    }

    public Brands getCurrentBrand() {
        return myCurrentBrand;
    }

    public void reset() {
        mySelectedList = new ArrayList<>();
        myCurrentBrand = null;
    }
}
