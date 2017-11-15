package calc.mydukan.com.calculatortool.models;

/**
 * Created by rojesharunkumar on 05/11/17.
 */

public class Schemes {

    private String schemeId;
    private String schemeName;
    private boolean isSelected = false;

    public Schemes() {

    }

    public Schemes(String id, String schemeName) {
        this.schemeId = id;
        this.schemeName = schemeName;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

}
