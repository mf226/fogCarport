package FunctionLayer;

public class MaterialDetails {

    private static final int CM_TO_METER = 100;

    private Material material;
    private double cmLengthEach;
    private double amount;
    //private double totalItemPrice;
    private String useDescription;

    public MaterialDetails(Material material, double cmLengthEach, double amount, String useDescription) {
        this.material = material;
        this.cmLengthEach = cmLengthEach;
        this.amount = amount;
        this.useDescription = useDescription;

    }

    public String getUseDescription() {
        return useDescription;
    }

    public void setUseDescription(String useDescription) {
        this.useDescription = useDescription;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getCmLengthEach() {
        return cmLengthEach;
    }

    public void setCmLengthEach(double cmLengthEach) {
        this.cmLengthEach = cmLengthEach;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalItemPrice() {
        double totalItemPrice;

        if (this.material.getUnit().equals("m")) {
            totalItemPrice = this.material.getPrice() * cmLengthEach * amount / CM_TO_METER;
        }
        else {
            totalItemPrice = this.material.getPrice() * amount;
        }
        
        return totalItemPrice;
    }

}
