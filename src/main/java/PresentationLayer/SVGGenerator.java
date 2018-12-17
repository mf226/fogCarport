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
        StringBuilder sb = new StringBuilder();
        if (length == 0 || width == 0) {
            return sb.toString();
        }
        double wallWidth = 0.25;
        int x = innerX;
        int y = innerY + RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        //Upperleft
        if (order.getShedPlacement().equals("UL")) {
            //Length - N
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Length - S
            y += width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Width - W
            y -= width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            //Width - E
            x += length;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            return sb.toString();
        }
        //Upperright
        if (order.getShedPlacement().equals("UR")) {
            //Length - N
            x += order.getLength() - length;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Length - S
            y += width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Width - W
            y -= width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            //Width - E
            x += length;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            return sb.toString();
        }
        //Lowerright
        if (order.getShedPlacement().equals("LR")) {
            //Length - N
            x += order.getLength() - length;
            y += order.getWidth() - width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Length - S
            y += width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Width - W
            y -= width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            //Width - E
            x += length;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            return sb.toString();
        }
        //Lowerleft
        if (order.getShedPlacement().equals("LL")) {
            //Length - N
            y += order.getWidth() - width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Length - S
            y += width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(length).append("\" height=\"").append(wallWidth).append("\"").append(style);
            //Width - W
            y -= width;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            //Width - E
            x += length;
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(wallWidth).append("\" height=\"").append(width).append("\"").append(style);
            return sb.toString();
        }
        return sb.toString();
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
        StringBuilder sb = new StringBuilder();
        int innerX = 50;
        int innerY = 50;
        sb.append("<svg class=\"outerCanvas\" width=\"" + 1000 + "\" height=\"" + 1000 + "\" scale>\n");

        //Inner Canvas
        sb.append("<svg class=\"innerCanvas\" width=\"" + 800 + "\" height=\"" + 800 + "\" scale>\n");
        String style = "style=\"\n"
                + "                  fill:white;\n"
                + "                  stroke-width:1;\n"
                + "                  stroke:rgb(0,0,0)\" />\n";

        sb.append("<rect x=\"").append(innerX).append("\" y=\"").append(innerY).append("\" width=\"").append(order.getLength()).append("\" height=\"").append(order.getWidth()).append("\"").append(style);
        //Upper Left - NW
        sb.append("<rect x=\"").append(innerX).append("\" y=\"").append(innerY).append("\" width=\"").append(order.getLength() / 2).append("\" height=\"").append(order.getWidth() / 2).append("\"").append(style);
        //Upper Right - NE
        sb.append("<rect x=\"").append(order.getLength() / 2).append(innerX).append("\" y=\"").append(innerY).append("\" width=\"").append(order.getLength() / 2).append("\" height=\"").append(order.getWidth() / 2).append("\"").append(style);
        //Lower Left - SW
        sb.append("<rect x=\"").append(innerX).append("\" y=\"").append(order.getWidth() / 2).append(innerY).append("\" width=\"").append(order.getLength() / 2).append("\" height=\"").append(order.getWidth() / 2).append("\"").append(style);
        //Lower Right - SE
        sb.append("<rect x=\"").append(order.getLength() / 2).append(innerX).append("\" y=\"").append(order.getWidth() / 2).append(innerY).append("\" width=\"").append(order.getLength() / 2).append("\" height=\"").append(order.getWidth() / 2).append("\"").append(style);
        sb.append("</svg>");

        //Outer Canvas
        sb.append("<line x1=\"").append(innerX).append("\" y1=\"10\" x2=\"").append(order.getLength()).append(innerX).append("\" y2=\"10\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"20\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: ").append(order.getLength()).append(" cm</text>");
        sb.append("<line x1=\"").append(10).append("\" y1=\"").append(innerY).append("\" x2=\"").append(10).append("\" y2=\"").append(order.getWidth()).append(innerY).append("\"").append(style);
        sb.append("<text x=\"").append(20).append("\" y=\"").append(order.getWidth() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">bredde: ").append(order.getWidth()).append(" cm</text>");

        sb.append("</svg>");

        return sb.toString();
    }

    public static String createSketchSideViewAngled(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        StringBuilder sb = new StringBuilder();
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
        sb.append("<svg width=\"").append(outerCanvasW).append("\" height=\"").append(outerCanvasL).append("\"scale>\n");
        //OuterLength
        sb.append("<line x1=\"").append(innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2).append("\" y1=\"").append(0).append("\" x2=\"").append(innerX).append(outerLength - RulesAndConstants.ROOF_WIDTH_EXTRA / 2).append("\" y2=\"").append(0).append("\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: ").append(outerLength).append(" cm</text>");
        //InnerLength
        sb.append("<line x1=\"").append(innerX).append("\" y1=\"").append(22).append("\" x2=\"").append(innerLength).append(innerX).append("\" y2=\"").append(22).append("\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: ").append(innerLength).append(" cm</text>");
        //OuterHeight
        sb.append("<line x1=\"").append(0).append("\" y1=\"").append(innerY - (rafter.getTopsideWidth() * 2)).append("\" x2=\"").append(0).append("\" y2=\"").append(innerY).append(innerHeight).append("\"").append(style);
        sb.append("<text x=\"").append(10).append("\" y=\"").append(order.getHeight() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Højde: ").append(outerHeight).append(" cm</text>");
        //InnerHeight
        sb.append("<line x1=\"").append(22).append("\" y1=\"").append(innerY).append("\" x2=\"").append(22).append("\" y2=\"").append(order.getHeight()).append(innerY).append("\"").append(style);
        sb.append("<text x=\"").append(32).append("\" y=\"").append(order.getHeight() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre højde: ").append(innerHeight).append(" cm</text>");
        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount() / 2;
        WoodDetails poles = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION);
        double xSpacing = order.getLength() / (amount - 1);
        int x = innerX;
        int y = innerY;
        //adding shed before poles
        if (order.isShedExists()) {

            if (order.getShedWidth() < order.getWidth() || order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("UL")) {
                sb.append(addShedSideView(innerX, innerY, order));
            }
        }
        for (int i = 0; i < amount; i++) {

            sb.append("<<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(pole.getTopsideLength()).append("\" height=\"").append(poles.getCmLengthEach() - RulesAndConstants.LENGTH_UNDER_GROUND).append("\"").append(style);
            x += xSpacing;
        }
        //adding Shed after poles
        if (order.isShedExists()) {
            if (order.getShedWidth() == order.getWidth() || order.getShedPlacement().equals("LR") || order.getShedPlacement().equals("LL")) {
                sb.append(addShedSideView(innerX, innerY, order));
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();

        y = innerY - rem.getTopsideWidth();
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach()).append("\" height=\"").append(mat.getTopsideWidth()).append("\"").append(style);

        //Spær - Horizontal
        amount = materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getAmount();
        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y -= rafter.getTopsideWidth();
        for (int i = 0; i < amount; i++) {
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"4.5\" height=\"").append(rafter.getTopsideWidth()).append("\"").append(style);
            x += xSpacing;
        }
        sb.append("</svg>");
        return sb.toString();
    }

    public static String createSketchBirdsEyeView(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        StringBuilder sb = new StringBuilder();
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
        sb.append("<svg width=\"").append(outerCanvasW).append("\" height=\"").append(outerCanvasL).append("\" scale>\n");
        //OuterLength
        sb.append("<line x1=\"").append(RulesAndConstants.ROOF_LENGTH_EXTRA / 2).append("\" y1=\"").append(0).append("\" x2=\"").append(outerLength).append(RulesAndConstants.ROOF_LENGTH_EXTRA / 2).append("\" y2=\"").append(0).append("\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: ").append(outerLength).append(" cm</text>");
        //InnerLength
        sb.append("<line x1=\"").append(innerX).append("\" y1=\"").append(22).append("\" x2=\"").append(outerLength).append("\" y2=\"").append(22).append("\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: ").append(innerLength).append(" cm</text>");
        //OuterWidth
        sb.append("<line x1=\"").append(0).append("\" y1=\"").append(innerY).append("\" x2=\"").append(0).append("\" y2=\"").append(order.getWidth()).append(innerY).append(RulesAndConstants.ROOF_WIDTH_EXTRA).append("\"").append(style);
        sb.append("<text x=\"").append(10).append("\" y=\"").append(order.getWidth() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Bredde: ").append(outerWidth).append(" cm</text>");
        //InnerWidth
        sb.append("<line x1=\"").append(22).append("\" y1=\"").append(innerY).append(RulesAndConstants.ROOF_WIDTH_EXTRA / 2).append(pole.getTopsideWidth()).append("\" x2=\"").append(22).append("\" y2=\"").append((order.getWidth() + innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2) - pole.getTopsideWidth()).append("\"").append(style);
        sb.append("<text x=\"").append(32).append("\" y=\"").append(order.getWidth() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre bredde: ").append(innerWidth).append(" cm</text>");

        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount();

        double xSpacing = order.getLength() / (amount / 2 - 1);
        int x = innerX;
        int x1 = innerX;
        int y = innerY + RulesAndConstants.ROOF_WIDTH_EXTRA / 2;
        for (int i = 0; i < amount; i++) {

            //Horizontal-North
            if (i < amount / 2) {
                sb.append("<--! horizontal N-->\n<rect x=\"" + x + "\" y=\"" + y + "\" width=\"9.7\" height=\"9.7\"" + style);
                x += xSpacing;
            }
            WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getMaterial();
            //Horizontal-South
            int yPolePlacement = innerY + order.getWidth() + 25 - mat.getTopsideLength();
            if (i >= amount / 2) {
                sb.append("<--! horizontal S-->\n<rect x=\"" + x1 + "\"y=\"" + yPolePlacement + "\" width=\"9.7\" height=\"9.7\"" + style);
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
                sb.append("<rect x=\"").append(x).append("\" y=\"").append(innerY).append("\" width=\"4.5\" height=\"").append(materials.get(RulesAndConstants.CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION).getCmLengthEach()).append("\"").append(style);
                x += xSpacing;
            } else {
                sb.append("<rect x=\"").append(x).append("\" y=\"").append(innerY).append("\" width=\"4.5\" height=\"").append(materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getCmLengthEach()).append("\"").append(style);
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

            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach()).append("\" height=\"").append(mat.getTopsideLength()).append("\"").append(style);
            y = ySpacing - mat.getTopsideLength();
        }
        //adding shed
        sb.append(addShedBirdsEye(innerX, innerY, style, order.getShedLength(), order.getShedWidth(), order));
        sb.append("</svg>");
        return sb.toString();
    }

    public static String createSketchSideViewFlat(Order order) {
        HashMap<String, WoodDetails> materials = order.getCarportWoodMaterials();
        StringBuilder sb = new StringBuilder();
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
        sb.append("<svg width=\"").append(outerCanvasW).append("\" height=\"").append(outerCanvasL).append("\"scale>\n");
        //OuterLength
        sb.append("<line x1=\"").append(innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2).append("\" y1=\"").append(0).append("\" x2=\"").append(innerX).append(outerLength - RulesAndConstants.ROOF_WIDTH_EXTRA / 2).append("\" y2=\"").append(0).append("\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"15\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Længde: ").append(outerLength).append(" cm</text>");
        //InnerLength
        sb.append("<line x1=\"").append(innerX).append("\" y1=\"").append(22).append("\" x2=\"").append(innerLength).append(innerX).append("\" y2=\"").append(22).append("\"").append(style);
        sb.append("<text x=\"").append(order.getLength() / 2).append("\" y=\"32\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre længde: ").append(innerLength).append(" cm</text>");
        //OuterHeight
        sb.append("<line x1=\"").append(0).append("\" y1=\"").append(innerY - (rafter.getTopsideWidth() * 2)).append("\" x2=\"").append(0).append("\" y2=\"").append(innerY).append(innerHeight).append("\"").append(style);
        sb.append("<text x=\"").append(10).append("\" y=\"").append(order.getHeight() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Højde: ").append(outerHeight).append(" cm</text>");
        //InnerHeight
        sb.append("<line x1=\"").append(22).append("\" y1=\"").append(innerY).append("\" x2=\"").append(22).append("\" y2=\"").append(order.getHeight()).append(innerY).append("\"").append(style);
        sb.append("<text x=\"").append(32).append("\" y=\"").append(order.getHeight() / 2).append("\" writing-mode=\"tb-rl\" glyph-orientation-vertical=\"0\" font-family=\"sans-serif\" font-size=\"12px\" fill=\"black\">Indre højde: ").append(innerHeight).append(" cm</text>");
        //Stolper
        double amount = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION).getAmount() / 2;
        WoodDetails poles = materials.get(RulesAndConstants.CARPORT_POSTS_DESCRIPTION);
        double xSpacing = order.getLength() / (amount - 1);
        int x = innerX;
        int y = innerY;
        //adding shed before poles
        if (order.isShedExists()) {
            if (order.getShedWidth() < order.getWidth() || order.getShedPlacement().equals("UR") || order.getShedPlacement().equals("UL")) {
                sb.append(addShedSideView(innerX, innerY, order));
            }
        }
        for (int i = 0; i < amount; i++) {

            sb.append("<<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(pole.getTopsideLength()).append("\" height=\"").append(poles.getCmLengthEach() - RulesAndConstants.LENGTH_UNDER_GROUND).append("\"").append(style);
            x += xSpacing;
        }
        //adding Shed after poles
        if (order.isShedExists()) {
            if (order.getShedWidth() == order.getWidth() || order.getShedPlacement().equals("LR") || order.getShedPlacement().equals("LL")) {
                sb.append(addShedSideView(innerX, innerY, order));
            }
        }
        //Remme
        amount = materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getAmount();

        y = innerY - rem.getTopsideWidth();
        x = innerX - RulesAndConstants.ROOF_LENGTH_EXTRA / 2;
        WoodMaterial mat = (WoodMaterial) materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getMaterial();
        sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"").append(materials.get(RulesAndConstants.CARPORT_REM_DESCRIPTION).getCmLengthEach()).append("\" height=\"").append(mat.getTopsideWidth()).append("\"").append(style);

        //Spær - Horizontal
        amount = materials.get(RulesAndConstants.CARPORT_RAFTER_FLATROOF_DESCRIPTON).getAmount();
        xSpacing = order.getLength() / (amount - 1);
        x = innerX;
        y -= rafter.getTopsideWidth();
        for (int i = 0; i < amount; i++) {
            sb.append("<rect x=\"").append(x).append("\" y=\"").append(y).append("\" width=\"4.5\" height=\"").append(rafter.getTopsideWidth()).append("\"").append(style);
            x += xSpacing;
        }
        sb.append("</svg>");
        return sb.toString();
    }

}
