package FunctionLayer.Entity;

public abstract class MaterialDetails {

    private double amount;
    private Material material;
    //private boolean isEnoughInStock = false;

    public MaterialDetails(Material material, double amount) {
        this.material = material;
        this.amount = amount;
        //this.isEnoughInStock = true;
        
    }

//    public boolean isEnoughInStock() {
//        return this.isEnoughInStock;
////        return amount <= material.getAmountInStock();
//    }

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

}
