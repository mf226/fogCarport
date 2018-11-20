/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

public class Material {
    private int itemNumber;
    private String name, unit;
    private double price;

    public Material(int itemNumber, String name, String unit, double price) {
        this.itemNumber = itemNumber;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }
    
    public int getTopsideLength() {
        String size1 = name.split("x")[0];
        return Integer.parseInt(size1);
    }
    
    public int getTopsideWidth() {
        String size2 = name.split("x")[1];
        return Integer.parseInt(size2);
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
     
    
}
