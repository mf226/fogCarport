package FunctionLayer.Entity;

public class MetalDetails extends MaterialDetails {

    public MetalDetails(Material material, double amount) {
        super(material, amount);
    }

    public MetalDetails(Material material, double amount, String description) {
        super(material, amount, description);
    }

    @Override
    public double getTotalItemPrice() {
        double numberOfUnits = Math.ceil(super.getAmount() / ((MetalMaterial) super.getMaterial()).getSoldInPacksOf());
        return super.getMaterial().getPricePerUnit() * numberOfUnits; 
    }
    
    @Override
    public double getAmount() {
        return Math.ceil(super.getAmount() / ((MetalMaterial) super.getMaterial()).getSoldInPacksOf());
    }
}
