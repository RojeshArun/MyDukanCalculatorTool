package calc.mydukan.com.calculatortool.models;

import java.util.List;

/**
 * Created by rojesharunkumar on 14/11/17.
 */

public class MySelectedModel {

    private List<Brands> mySelectedSchemes;
    private String userId;

    public MySelectedModel() {
    }

    public MySelectedModel(String uid, List<Brands> list) {
        this.userId = uid;
        mySelectedSchemes = list;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Brands> getMySelectedSchemes() {
        return mySelectedSchemes;
    }

    public void setMySelectedSchemes(List<Brands> mySelectedSchemes) {
        this.mySelectedSchemes = mySelectedSchemes;
    }
}
