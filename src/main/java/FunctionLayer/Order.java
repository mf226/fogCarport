package FunctionLayer;

import java.util.ArrayList;

/**
 *
 * @author Fen
 */
public class Order {
    private int length;
    private int width;
    private int height;
    private String description;
    private ArrayList<Material> materials;

    public Order(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.description = "Carport længde*bredde*højde: "+length+"*"+width+"*"+height;
        this.materials = new ArrayList();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }
    
    
}
