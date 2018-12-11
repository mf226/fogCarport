/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.WoodDetails;
import FunctionLayer.Entity.WoodMaterial;
import FunctionLayer.RulesAndConstants;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jonab
 */
public class SVGGenerator {

    private static final int outerCanvasW = 700;
    private static final int outerCanvasL = 700;
    private static final int innerCanvasW = 600;
    private static final int innerCanvasl = 600;

    public static String addShedBirdsEye(final int innerX, final int innerY, String style, int length, int width, Order order) {
        String shed = "";
        if (length == 0 || width == 0) {
            return shed;
        }
        double wallWidth = 0.25;
        int x = innerX;
        int y = innerY + RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        //Upperleft
        if (order.getShedPlacement().equals("UL")) {
            //Length - N
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        //Upperright
        if (order.getShedPlacement().equals("UR")) {
            //Length - N
            x += order.getLength() - length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        //Lowerright
        if (order.getShedPlacement().equals("LR")) {
            //Length - N
            x += order.getLength() - length;
            y += order.getWidth() - width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        //Lowerleft
        if (order.getShedPlacement().equals("LL")) {
            //Length - N
            y += order.getWidth() - width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Length - S
            y += width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + length + "\" height=\"" + wallWidth + "\"" + style;
            //Width - W
            y -= width;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            //Width - E
            x += length;
            shed += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + wallWidth + "\" height=\"" + width + "\"" + style;
            return shed;
        }
        return shed;
    }

    public static String addShedSideView(final int innerX, final int innerY, Order order) {
        String shed = "";
        if (order.getShedLength() == 0) {
            return shed;
        }
        String style = "style=\"\n"
                + "                  fill:lightgray;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";

        double plankWidth = 15.0;
        int x = innerX;
        int y = innerY;
        //Upperleft
        if (order.getShedPlacement().equals("UL") || order.getShedPlacement().equals("LL")) {
            //Length - N
            for (int i = 0; i < order.getShedLength(); i += plankWidth) {
                shed += "<!--Shed--><rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + plankWidth + "\" height=\"" + order.getHeight() + "\"" + style;
                x += plankWidth;
            }
            return shed;
        }
        //Upperright
        if (order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("LR")) {
            //Length - N
            x += order.getLength();
            for (int i = 0; i < order.getShedLength(); i += plankWidth) {
                shed += "<!--Shed--><rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + plankWidth + "\" height=\"" + order.getHeight() + "\"" + style;
                x -= plankWidth;
            }
            return shed;
        }
        return shed;
    }

    public static String shedPlacement(Order order) {
        int innerX = 50;
        int innerY = 50;
        String sketch = "<svg class=\"outerCanvas\" width=\"" + 1000 + "\" height=\"" + 1000 + "\" scale>\n";

        //Inner Canvas
        sketch += "<svg class=\"innerCanvas\" width=\"" + 800 + "\" height=\"" + 800 + "\" scale>\n";
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";

        sketch += "<rect x=\"" + innerX + "\" y=\"" + innerY + "\" width=\"" + order.getLength() + "\" height=\"" + order.getWidth() + "\"" + style;
        //Upper Left - NW
        sketch += "<rect x=\"" + innerX + "\" y=\"" + innerY + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        //Upper Right - NE
        sketch += "<rect x=\"" + (order.getLength() / 2 + innerX) + "\" y=\"" + innerY + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        //Lower Left - SW
        sketch += "<rect x=\"" + innerX + "\" y=\"" + (order.getWidth() / 2 + innerY) + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        //Lower Right - SE
        sketch += "<rect x=\"" + (order.getLength() / 2 + innerX) + "\" y=\"" + (order.getWidth() / 2 + innerY) + "\" width=\"" + order.getLength() / 2 + "\" height=\"" + order.getWidth() / 2 + "\"" + style;
        sketch += "</svg>";

        //Outer Canvas
        sketch += "<line x1=\"" + innerX + "\" y1=\"10\" x2=\"" + (order.getLength() + innerX) + "\" y2=\"10\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"20\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + order.getLength() + " cm</text>";
        sketch += "<line x1=\"" + 10 + "\" y1=\"" + innerY + "\" x2=\"" + 10 + "\" y2=\"" + (order.getWidth() + innerY) + "\"" + style;
        sketch += "<text x=\"" + 20 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">bredde: " + order.getWidth() + " cm</text>";

        sketch += "</svg>";

        return sketch;
    }

    public static String createSketchSideViewAngled(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        final int innerX = 75;
        final int innerY = 100;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        WoodMaterial pole = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
        WoodMaterial rem = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        WoodMaterial rafter = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getMaterial();

        int outerLength = order.getLength() + RulesAndConstants.ROOF_LENGTH_EXTRA;
        int innerLength = order.getLength();
        double innerHeight = order.getHeight();
        double outerHeight = order.getHeight() + (rem.getTopsideWidth() * 2);
        String sketch = "<svg width=\"" + outerCanvasW + "\" height=\"" + outerCanvasL + "\"scale>\n";
        //OuterLength
        sketch += "<line x1=\"" + (innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2) + "\" y1=\"" + 0 + "\" x2=\"" + (innerX + (outerLength - RulesAndConstants.ROOF_WIDTH_EXTRA / 2)) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + outerLength + " cm</text>";
        //InnerLength
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 22 + "\" x2=\"" + (innerLength + innerX) + "\" y2=\"" + 22 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: " + innerLength + " cm</text>";
        //OuterHeight
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY - (rafter.getTopsideWidth() * 2)) + "\" x2=\"" + 0 + "\" y2=\"" + (innerY + innerHeight) + "\"" + style;
        sketch += "<text x=\"" + 10 + "\" y=\"" + order.getHeight() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Højde: " + outerHeight + " cm</text>";
        //InnerHeight
        sketch += "<line x1=\"" + 22 + "\" y1=\"" + innerY + "\" x2=\"" + 22 + "\" y2=\"" + (order.getHeight() + innerY) + "\"" + style;
        sketch += "<text x=\"" + 32 + "\" y=\"" + order.getHeight() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre højde: " + innerHeight + " cm</text>";
        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount() / 2;
        WoodDetails poles = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION);
        double xSpacing = order.getLength() / (amount - 1);
        int x = innerX;
        int y = innerY;
        //adding shed before poles
        if (order.isShedExists()) {

            if (order.getShedWidth() < order.getWidth() || order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("UL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        for (int i = 0; i < amount; i++) {

            sketch += "<<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + pole.getTopsideLength() + "\" height=\"" + (poles.getCmLengthEach() - RulesAndConstants.LENGTH_UNDER_GROUND) + "\"" + style;
            x += xSpacing;
        }
        //adding Shed after poles
        if (order.isShedExists()) {
            if (order.getShedWidth() == order.getWidth() || order.getShedPlacement().equals("LR") || order.getShedPlacement().equals("LL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();

        y = innerY - rem.getTopsideWidth();
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() + "\" height=\"" + mat.getTopsideWidth() + "\"" + style;

        //Spær - Horizontal
        amount = materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getAmount();
        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y -= rafter.getTopsideWidth();
        for (int i = 0; i < amount; i++) {
            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"4.5\" height=\"" + rafter.getTopsideWidth() + "\"" + style;
            x += xSpacing;
        }
        sketch += "</svg>";
        return sketch;
    }

    public static String createSketchBirdsEyeView(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        final int innerX = 50;
        final int innerY = 50;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        WoodMaterial pole = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
        int outerLength = order.getLength() + RulesAndConstants.ROOF_LENGTH_EXTRA;
        int innerLength = order.getLength();
        int outerWidth = order.getWidth();
        int innerWidth = order.getWidth() - pole.getTopsideWidth() * 2;
        String sketch = "<svg width=\"" + outerCanvasW + "\" height=\"" + outerCanvasL + "\" scale>\n";
        //OuterLength
        sketch += "<line x1=\"" + RulesAndConstants.ROOF_LENGTH_EXTRA / 2 + "\" y1=\"" + 0 + "\" x2=\"" + (outerLength + RulesAndConstants.ROOF_LENGTH_EXTRA / 2) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + outerLength + " cm</text>";
        //InnerLength
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 22 + "\" x2=\"" + outerLength + "\" y2=\"" + 22 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: " + innerLength + " cm</text>";
        //OuterWidth
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY) + "\" x2=\"" + 0 + "\" y2=\"" + (order.getWidth() + innerY + RulesAndConstants.ROOF_WIDTH_EXTRA) + "\"" + style;
        sketch += "<text x=\"" + 10 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Bredde: " + outerWidth + " cm</text>";
        //InnerWidth
        sketch += "<line x1=\"" + 22 + "\" y1=\"" + ((innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) + pole.getTopsideWidth()) + "\" x2=\"" + 22 + "\" y2=\"" + ((order.getWidth() + innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) - pole.getTopsideWidth()) + "\"" + style;
        sketch += "<text x=\"" + 32 + "\" y=\"" + order.getWidth() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre bredde: " + innerWidth + " cm</text>";

        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount();

        double xSpacing = order.getLength() / (amount / 2 - 1);
        int x = innerX;
        int x1 = innerX;
        int y = innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2;
        for (int i = 0; i < amount; i++) {

            //Horizontal-North
            if (i < amount / 2) {
                sketch += "<--! horizontal N-->\n<rect x=\"" + x + "\" y=\"" + y + "\" width=\"9.7\" height=\"9.7\"" + style;
                x += xSpacing;
            }
            WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
            //Horizontal-South
            int yPolePlacement = innerY + order.getWidth() + 25 - mat.getTopsideLength();
            if (i >= amount / 2) {
                sketch += "<--! horizontal S-->\n<rect x=\"" + x1 + "\"y=\"" + yPolePlacement + "\" width=\"9.7\" height=\"9.7\"" + style;
                x1 += xSpacing;
            }
        }

        //Spær
        if (order.getAngle() != 0) {
            amount = materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getAmount();
        } else {
            amount = materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getAmount();
        }

        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y = innerY;
        for (int i = 0; i < amount; i++) {
            if (order.getAngle() != 0) {
                sketch += "<rect x=\"" + x + "\" y=\"" + innerY + "\" width=\"4.5\" height=\"" + materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getCmLengthEach() + "\"" + style;
                x += xSpacing;
            } else {
                sketch += "<rect x=\"" + x + "\" y=\"" + innerY + "\" width=\"4.5\" height=\"" + materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getCmLengthEach() + "\"" + style;
                x += xSpacing;
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();
        y = innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2;
        int ySpacing = order.getWidth() + y;
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;

        WoodMaterial mat = (WoodMaterial) materials.get("Remme").getMaterial();

        for (int i = 0; i < amount; i++) {

            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() + "\" height=\"" + mat.getTopsideLength() + "\"" + style;
            y = ySpacing - mat.getTopsideLength();
        }
        //adding shed
        sketch += addShedBirdsEye(innerX, innerY, style, order.getShedLength(), order.getShedWidth(), order);
        sketch += "</svg>";
        return sketch;
    }
    
    public static String createSketchSideViewFlat(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        final int innerX = 75;
        final int innerY = 100;
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";
        //Outer Measurements
        WoodMaterial pole = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
        WoodMaterial rem = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        WoodMaterial rafter = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getMaterial();

        int outerLength = order.getLength() + RulesAndConstants.ROOF_LENGTH_EXTRA;
        int innerLength = order.getLength();
        double innerHeight = order.getHeight();
        double outerHeight = order.getHeight() + (rem.getTopsideWidth() * 2);
        String sketch = "<svg width=\"" + outerCanvasW + "\" height=\"" + outerCanvasL + "\"scale>\n";
        //OuterLength
        sketch += "<line x1=\"" + (innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2) + "\" y1=\"" + 0 + "\" x2=\"" + (innerX + (outerLength - RulesAndConstants.ROOF_WIDTH_EXTRA / 2)) + "\" y2=\"" + 0 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: " + outerLength + " cm</text>";
        //InnerLength
        sketch += "<line x1=\"" + innerX + "\" y1=\"" + 22 + "\" x2=\"" + (innerLength + innerX) + "\" y2=\"" + 22 + "\"" + style;
        sketch += "<text x=\"" + order.getLength() / 2 + "\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: " + innerLength + " cm</text>";
        //OuterHeight
        sketch += "<line x1=\"" + 0 + "\" y1=\"" + (innerY - (rafter.getTopsideWidth() * 2)) + "\" x2=\"" + 0 + "\" y2=\"" + (innerY + innerHeight) + "\"" + style;
        sketch += "<text x=\"" + 10 + "\" y=\"" + order.getHeight() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Højde: " + outerHeight + " cm</text>";
        //InnerHeight
        sketch += "<line x1=\"" + 22 + "\" y1=\"" + innerY + "\" x2=\"" + 22 + "\" y2=\"" + (order.getHeight() + innerY) + "\"" + style;
        sketch += "<text x=\"" + 32 + "\" y=\"" + order.getHeight() / 2 + "\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre højde: " + innerHeight + " cm</text>";
        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount() / 2;
        WoodDetails poles = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION);
        double xSpacing = order.getLength() / (amount - 1);
        int x = innerX;
        int y = innerY;
        //adding shed before poles
        if (order.isShedExists()) {
            if (order.getShedWidth() < order.getWidth() || order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("UL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        for (int i = 0; i < amount; i++) {

            sketch += "<<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + pole.getTopsideLength() + "\" height=\"" + (poles.getCmLengthEach() - RulesAndConstants.LENGTH_UNDER_GROUND) + "\"" + style;
            x += xSpacing;
        }
        //adding Shed after poles
        if (order.isShedExists()) {
            if (order.getShedWidth() == order.getWidth() || order.getShedPlacement().equals("LR") || order.getShedPlacement().equals("LL")) {
                sketch += addShedSideView(innerX, innerY, order);
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();

        y = innerY - rem.getTopsideWidth();
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach() + "\" height=\"" + mat.getTopsideWidth() + "\"" + style;

        //Spær - Horizontal
        amount = materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getAmount();
        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y -= rafter.getTopsideWidth();
        for (int i = 0; i < amount; i++) {
            sketch += "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"4.5\" height=\"" + rafter.getTopsideWidth() + "\"" + style;
            x += xSpacing;
        }
        sketch += "</svg>";
        return sketch;
    }


}
