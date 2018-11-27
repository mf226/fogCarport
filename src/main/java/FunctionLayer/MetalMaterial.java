
package FunctionLayer;


public class MetalMaterial extends Material {
    
    private int soldInPacksOf;
    
    public MetalMaterial(int itemNumber, String name, String unit, double price, int soldInPacksOf) {
        super(itemNumber, name, unit, price);
        this.soldInPacksOf = soldInPacksOf;
    }
    
}
