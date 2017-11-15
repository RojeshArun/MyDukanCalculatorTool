package calc.mydukan.com.calculatortool.models;

/**
 * Created by rojesharunkumar on 06/11/17.
 */

public class Model {

    public Model() {

    }

    public Model(String id, String name,String price) {
        this.modelId = id;
        this.modelName = name;
        this.modelPrice = price;
    }

    private String modelPrice;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    String modelId;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    String modelName;

    public String getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(String modelPrice) {
        this.modelPrice = modelPrice;
    }
}
