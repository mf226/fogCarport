package FunctionLayer;

public class Material {

    private int itemNumber;
    private String name, unit;
    private double pricePerUnit;
    private int amountInStock;

    public Material(int itemNumber, String name, String unit, double price) {
        this.itemNumber = itemNumber;
        this.name = name;
        this.unit = unit;
        this.pricePerUnit = price;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
