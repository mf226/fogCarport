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
    private int shedLength;
    private int userID;

    private int shedWidth;

    private double finalizedPrice;

    private HashMap<String, WoodDetails> carportWoodMaterials;
    private HashMap<String, MetalDetails> carportMetalMaterials;
    private HashMap<String, WoodDetails> shedWoodMaterials;
    private HashMap<String, MetalDetails> shedMetalMaterials;

    private String shedPlacement;
    private Status status;

    //private int roofType;
    private boolean shedExists;

    public Order(int length, int width, int height, int angle) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.angle = angle;
        //this.roofType = roofType;
        this.carportWoodMaterials = new HashMap();
        this.carportMetalMaterials = new HashMap();
        this.shedWoodMaterials = null;
        this.shedMetalMaterials = null;
        this.shedPlacement = null;
        this.shedLength = 0;
        this.shedWidth = 0;
        this.shedExists = false;
        this.finalizedPrice = 0;
        this.userID = 0;
        this.status = Status.pending;
    }

    public void createShed(String placement, int shedLength, int shedWidth, boolean shedExists) {
        this.shedPlacement = placement;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.shedExists = shedExists;
        this.shedWoodMaterials = new HashMap<String, WoodDetails>();
        this.shedMetalMaterials = new HashMap<String, MetalDetails>();

    }

    public Order(int orderID, int length, int width, int height, int angle, double finalizedPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getShedPlacement() {
        return shedPlacement;
    }

    public void setShedPlacement(String shedPlacement) {
        this.shedPlacement = shedPlacement;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public boolean isShedExists() {
        return shedExists;
    }

    public void setShedExists(boolean exists) {
        this.shedExists = exists;
    }

    public HashMap<String, WoodDetails> getShedWoodMaterials() {
        return shedWoodMaterials;
    }

    public void setShedWoodMaterials(HashMap<String, WoodDetails> shedWoodMaterials) {
        this.shedWoodMaterials = shedWoodMaterials;
    }

    public HashMap<String, MetalDetails> getShedMetalMaterials() {
        return shedMetalMaterials;
    }

    public void setShedMetalMaterials(HashMap<String, MetalDetails> shedMetalMaterials) {
        this.shedMetalMaterials = shedMetalMaterials;
    }

    public double getTotalOrderPrice() {
        double totalOrderPrice = 0;
        for (WoodDetails wd : carportWoodMaterials.values()) {
            totalOrderPrice += wd.getTotalItemPrice();
        }
        for (MetalDetails md : carportMetalMaterials.values()) {
            totalOrderPrice += md.getTotalItemPrice();
        }
        if (shedWoodMaterials != null) {
            for (WoodDetails wd : shedWoodMaterials.values()) {
                totalOrderPrice += wd.getTotalItemPrice();
            }
        }
        if (shedMetalMaterials != null) {
            for (MetalDetails md : shedMetalMaterials.values()) {
                totalOrderPrice += md.getTotalItemPrice();
            }
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

    public HashMap<String, WoodDetails> getCarportWoodMaterials() {
        return carportWoodMaterials;
    }

    public HashMap<String, MetalDetails> getCarportMetalMaterials() {
        return carportMetalMaterials;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getFinalizedPrice() {
        return finalizedPrice;
    }

    public void setFinalizedPrice(double finalizedPrice) {
        this.finalizedPrice = finalizedPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
