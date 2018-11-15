package FunctionLayer;

public class MaterialDetails {
    
    private static int CM_TO_METER = 100;
    
    private Material material;
    private int cmLengthEach;
    private int amount;
    private double totalItemPrice;

    public MaterialDetails(Material material, int cmLengthEach, int amount) {
        this.material = material;
        this.cmLengthEach = cmLengthEach;
        this.amount = amount;
        this.totalItemPrice = this.material.getPrice() * cmLengthEach * amount / CM_TO_METER;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCmLengthEach() {
        return cmLengthEach;
    }

    public void setCmLengthEach(int cmLengthEach) {
        this.cmLengthEach = cmLengthEach;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
   
      
}
