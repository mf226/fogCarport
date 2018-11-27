package FunctionLayer;

public class WoodDetails extends MaterialDetails {

    private static final int CM_TO_METER = 100;

    private double cmLengthEach;

    public WoodDetails(WoodMaterial material, double amount, double cmLengthEach) {
        super(material, amount);
        this.cmLengthEach = cmLengthEach;
    }

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
