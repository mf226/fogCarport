package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fen
 */
public class Order {

    private int length;
    private int width;
    private int height;
    private int angle;

    private String description;
    private ArrayList<MaterialDetails> materials;
    private int userID;

    public Order(int length, int width, int height, int angle) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.description = "Carport længde*bredde*højde: " + length + "*" + width + "*" + height;
        this.materials = new ArrayList();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalOrderPrice() {
        double totalOrderPrice = 0;
        for (int i = 0; i < materials.size(); i++) {
            totalOrderPrice += materials.get(i).getTotalItemPrice();
        }
        return totalOrderPrice;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
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

    public List<MaterialDetails> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<MaterialDetails> materials) {
        this.materials = materials;
    }

}
