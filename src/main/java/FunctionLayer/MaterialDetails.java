package FunctionLayer;

public abstract class MaterialDetails {

    private double amount;
    private Material material;

    public MaterialDetails(Material material, double amount) {
        this.material = material;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    abstract public double getTotalItemPrice();

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

//    public double getTotalItemPriceXX() {
//        double totalItemPrice;
//
//        if (this.material.getUnit().equals("m")) {
//            totalItemPrice = this.material.getPricePerUnit() * cmLengthEach * amount / CM_TO_METER;
//        }
//        else {
//            totalItemPrice = this.material.getPricePerUnit() * amount;
//        }
//        
//        return totalItemPrice;
//    }
}
