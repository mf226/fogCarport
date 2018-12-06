
package FunctionLayer;


public class MetalMaterial extends Material {
    
    private int soldInPacksOf;
    
    public MetalMaterial(int itemNumber, String name, String unit, double price, int soldInPacksOf, int amountInStock) {
        super(itemNumber, name, unit, price, amountInStock);
        this.soldInPacksOf = soldInPacksOf;
    }

    public int getSoldInPacksOf() {
        return soldInPacksOf;
    }
    
    
    
}
