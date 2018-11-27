package FunctionLayer;

public class MetalDetails extends MaterialDetails {

    public MetalDetails(Material material, double amount) {
        super(material, amount);
    }

    @Override
    public double getTotalItemPrice() {
        return super.getMaterial().getPricePerUnit() * super.getAmount();
    }
    
}
