package calc.mydukan.com.calculatortool.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rojesharunkumar on 05/11/17.
 */

public class Brands implements Serializable {
    String brandId;
    String brandTitle;

    String brandImageUrl;

    public List<Schemes> getMySelectedSchemesList() {
        if(mySelectedSchemesList == null){
            mySelectedSchemesList = new ArrayList<>();
        }
        return mySelectedSchemesList;
    }

    public void setMySelectedSchemesList(List<Schemes> mySelectedSchemesList) {
        this.mySelectedSchemesList = mySelectedSchemesList;
    }

    List<Schemes> mySelectedSchemesList;

    public String getBrandImageUrl() {
        return brandImageUrl;
    }

    public void setBrandImageUrl(String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
    }

    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Brands(String id, String title) {
        this.brandId = id;
        this.brandTitle = title;
    }

    public Brands() {
        this.brandId = "";
        this.brandTitle = "";
    }
}
