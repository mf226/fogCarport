package FunctionLayer.Entity;

public class WoodMaterial extends Material {

    private int lengthInStock;

    public WoodMaterial(int itemNumber, String name, String unit, double price, int lengthInStock, int amountInStock) {
        super(itemNumber, name, unit, price, amountInStock);
        this.lengthInStock = lengthInStock;
    }

    public int getTopsideLength() {
        String size1 = getName().split("x")[0];
        return Integer.parseInt(size1) / 10;
    }

    public int getTopsideWidth() {
        String size2 = getName().split("x")[1];
        return Integer.parseInt(size2) / 10;
    }

}
