package FunctionLayer.Entity;

public abstract class MaterialDetails {

    private String description;
    private double amount;
    private Material material;
    private boolean isEnoughInStock = false;

    public MaterialDetails(Material material, double amount) {
        this.material = material;
        this.amount = amount;
        material.withdrawAmountInStock(amount);
        this.isEnoughInStock = true;
        
    }
    
    public MaterialDetails(Material material, double amount, String description)
    {
        this.material = material;
        this.amount = amount;
        this.description = description;
    }

    public boolean isEnoughInStock() {
        return this.isEnoughInStock;
//        return amount <= material.getAmountInStock();
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
   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
