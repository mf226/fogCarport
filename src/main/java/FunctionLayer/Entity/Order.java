package FunctionLayer.Entity;

import FunctionLayer.Calculators;
import FunctionLayer.Exceptions.LogicException;
import java.sql.Date;
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
    private int orderID;

    private int shedWidth;

    private double price;

    private HashMap<String, WoodDetails> carportWoodMaterials;
    private HashMap<String, MetalDetails> carportMetalMaterials;
    private HashMap<String, WoodDetails> shedWoodMaterials;
    private HashMap<String, MetalDetails> shedMetalMaterials;

    private String shedPlacement;
    private String status;
    private Date orderDate;
    private int roofType;
    private int wallType;
    private boolean shedExists;

    public Order(int length, int width, int height, int angle, int roofType) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.roofType = roofType;
        this.wallType = 0;
        this.carportWoodMaterials = new HashMap();
        this.carportMetalMaterials = new HashMap();
        this.shedWoodMaterials = null;
        this.shedMetalMaterials = null;
        this.shedPlacement = null;
        this.shedLength = 0;
        this.shedWidth = 0;
        this.shedExists = false;
        this.price = 0;
        this.userID = 0;
        this.orderID = 0;
        this.orderDate = null;
        this.status = "pending";
    }

    public void createShed(String placement, int shedLength, int shedWidth, boolean shedExists, int wallType) {
        this.shedPlacement = placement;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.shedExists = shedExists;
        this.shedWoodMaterials = new HashMap<String, WoodDetails>();
        this.shedMetalMaterials = new HashMap<String, MetalDetails>();
        this.wallType = wallType;

    }

    public int getRoofType() {
        return roofType;
    }

    public void setRoofType(int roofType) {
        this.roofType = roofType;
    }

    public int getWallType() {
        return wallType;
    }

    public void setWallType(int wallType) {
        this.wallType = wallType;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
    
    /**
     * Calculates totalPrice of Order
     * @return totalOrderPrice
     */
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getAmountOfRoofRafters() throws LogicException
    {
        return Calculators.flatRoofRafterAmountCalc(this.getLength());
    }
    
    public double getPostsAmount() throws LogicException
    {
        return Calculators.postsAmountCalc(this.getLength(), this.getWidth());
    }

}
