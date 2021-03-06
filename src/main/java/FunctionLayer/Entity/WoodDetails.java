package FunctionLayer.Entity;

public class WoodDetails extends MaterialDetails {

    private static final int CM_TO_METER = 100;

    private double cmLengthEach;

    public WoodDetails(WoodMaterial material, double amount, double cmLengthEach) {
        super(material, amount);
        this.cmLengthEach = cmLengthEach;
    }

    public WoodDetails(Material material, double amount, double cmLengthEach, String description) {
        super(material, amount, description);
        this.cmLengthEach = cmLengthEach;
    }
     /**
     * Calculates totalItemPrice depending on price per unit
     * @return double
     */
    @Override
    public double getTotalItemPrice() {
        return super.getMaterial().getPricePerUnit() * cmLengthEach * super.getAmount() / CM_TO_METER;
    }
    

    public double getCmLengthEach() {
        return cmLengthEach;
    }

    public void setCmLengthEach(double cmLengthEach) {
        this.cmLengthEach = cmLengthEach;
    }

}
