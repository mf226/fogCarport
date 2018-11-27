package FunctionLayer;

import java.util.ArrayList;
import java.util.HashMap;
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

    private static HashMap<String, WoodDetails> woodMaterials;
    private static HashMap<String, MetalDetails> metalMaterials;
    private int userID;

    public Order(int length, int width, int height, int angle) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.woodMaterials = new HashMap();
        this.metalMaterials = new HashMap();
    }

    public double getTotalOrderPrice() {
        double totalOrderPrice = 0;
        for (WoodDetails wd : woodMaterials.values()) {
            totalOrderPrice += wd.getTotalItemPrice();
        }
        for (MetalDetails md : metalMaterials.values()) {
            totalOrderPrice += md.getTotalItemPrice();
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

    public HashMap<String, WoodDetails> getWoodMaterials() {
        return woodMaterials;
    }
    
    public HashMap<String, MetalDetails> getMetalMaterials() {
        return metalMaterials;
    }

}
