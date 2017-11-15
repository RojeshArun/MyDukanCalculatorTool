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

    public Brands getMyCurrentBrand(String brandId) {
        for (int i = 0; i < mySelectedList.size(); i++) {
            if (mySelectedList.get(i).getBrandId().equalsIgnoreCase(brandId))
                return mySelectedList.get(i);
        }
        return null;
    }

    public void addBrand(Brands selectedBrand) {

        if (!checkIsMyBrandAdded(selectedBrand)) {
            myCurrentBrand = selectedBrand;
            mySelectedList.add(selectedBrand);
        }
    }

    private boolean checkIsMyBrandAdded(Brands selectedBrand) {
        if (mySelectedList != null && selectedBrand != null) {
            for (int i = 0; i < mySelectedList.size(); i++) {
                if (mySelectedList.get(i).getBrandId().equals(selectedBrand.getBrandId())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void updateBrand(List<Schemes> updatedScheme, String brandId) {

        if (mySelectedList != null && checkIsMyBrandAdded(myCurrentBrand) && updatedScheme != null) {
            int pos = getPosition(brandId);
            List<Schemes> myCurrentSChemes = mySelectedList.get(pos).getMySelectedSchemesList();
            myCurrentBrand.setMySelectedSchemesList(updatedScheme);
            // TODO postition
            int size = updatedScheme.size();
            for (int i = 0; i < size; i++) {
                if (!myCurrentSChemes.contains(updatedScheme.get(i)))
                    myCurrentSChemes.add(updatedScheme.get(i));
            }
            myCurrentBrand.setMySelectedSchemesList(myCurrentSChemes);
            mySelectedList.get(pos).setMySelectedSchemesList(myCurrentSChemes);
        }
    }

    private int getPosition(String brandId) {
        if (mySelectedList != null) {
            for (int i = 0; i < mySelectedList.size(); i++) {
                if (brandId.equals(mySelectedList.get(i).getBrandId()))
                    return i;
            }
        }
        return 0;
    }

    public Brands getCurrentBrand() {
        return myCurrentBrand;
    }

    public void reset() {
        mySelectedList = new ArrayList<>();
        myCurrentBrand = null;
    }

    public void setList(List<Brands> mySelectedList) {
        this.mySelectedList = mySelectedList;
    }
}
