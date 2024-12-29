// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map.Ship;

import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class ShipManager
{
    public static List<ShipLine> shipLines;
    public static int shipLinesSize;
    public static List<Ship2> ships;
    public static List<List<Image>> shipImg;
    public static int limitOfShipsAtSea;
    public static List<Integer> shipsAtSea;
    public static int shipsAtSeaSize;
    public static List<Integer> shipsInPort;
    public static int shipsInPortSize;
    
    public static final void update() {
        if (ShipManager.shipsAtSeaSize < ShipManager.limitOfShipsAtSea) {
            addShipAtSea();
        }
    }
    
    public static final void addShipAtSea() {
        if (ShipManager.shipsInPortSize > 0) {
            final int tID = Game.oR.nextInt(ShipManager.shipsInPortSize);
            ShipManager.ships.get(ShipManager.shipsInPort.get(tID)).remove = false;
            ShipManager.shipsAtSea.add(ShipManager.shipsInPort.get(tID));
            ShipManager.shipsInPort.remove(tID);
            ShipManager.shipsAtSeaSize = ShipManager.shipsAtSea.size();
            ShipManager.shipsInPortSize = ShipManager.shipsInPort.size();
        }
    }
    
    public static final void drawCurrentScale(final SpriteBatch oSB) {
        try {
            if (Game.settingsManager.SHIPS_ON_MAP > 0) {
                if (GameValues.ships.PAUSE_MOVE_SHIPS || Game.gameThread.play) {
                    update();
                }
                final int ageGroup = Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).SHIP_GROUP;
                oSB.setColor(Color.WHITE);
                for (int i = ShipManager.shipsAtSeaSize - 1; i >= 0; --i) {
                    if (GameValues.ships.PAUSE_MOVE_SHIPS || Game.gameThread.play) {
                        ShipManager.ships.get(ShipManager.shipsAtSea.get(i)).update();
                    }
                    if (ShipManager.ships.get(ShipManager.shipsAtSea.get(i)).remove) {
                        ShipManager.shipsInPort.add(ShipManager.shipsAtSea.get(i));
                        ShipManager.shipsAtSea.remove(i);
                        ShipManager.shipsAtSeaSize = ShipManager.shipsAtSea.size();
                        ShipManager.shipsInPortSize = ShipManager.shipsInPort.size();
                    }
                    else {
                        ShipManager.ships.get(ShipManager.shipsAtSea.get(i)).drawCurrentScale(oSB, ageGroup);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void draw(final SpriteBatch oSB) {
        try {
            if (Game.settingsManager.SHIPS_ON_MAP > 0) {
                if (GameValues.ships.PAUSE_MOVE_SHIPS || Game.gameThread.play) {
                    update();
                }
                final int ageGroup = Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).SHIP_GROUP;
                oSB.setColor(Color.WHITE);
                for (int i = ShipManager.shipsAtSeaSize - 1; i >= 0; --i) {
                    if (GameValues.ships.PAUSE_MOVE_SHIPS || Game.gameThread.play) {
                        ShipManager.ships.get(ShipManager.shipsAtSea.get(i)).update();
                    }
                    if (ShipManager.ships.get(ShipManager.shipsAtSea.get(i)).remove) {
                        ShipManager.shipsInPort.add(ShipManager.shipsAtSea.get(i));
                        ShipManager.shipsAtSea.remove(i);
                        ShipManager.shipsAtSeaSize = ShipManager.shipsAtSea.size();
                        ShipManager.shipsInPortSize = ShipManager.shipsInPort.size();
                    }
                    else {
                        ShipManager.ships.get(ShipManager.shipsAtSea.get(i)).draw(oSB, ageGroup);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadShipLines() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "Lines_Sea.txt").exists()) {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "Lines_Sea.txt");
                final String text = file.readString();
                final String[] allLines = text.split("\n");
                try {
                    if (allLines.length > 0) {
                        for (int i = (allLines[0].length() <= 0) ? 1 : 0; i < allLines.length; i += 2) {
                            final String[] lineX = allLines[i].split(";");
                            final String[] lineY = allLines[i + 1].split(";");
                            final ShipLine nShipLine = new ShipLine();
                            for (int j = 0; j < lineX.length; ++j) {
                                nShipLine.addNewPoint_Just(Integer.parseInt(lineX[j]) * Game.mapBG.iMapScale, Integer.parseInt(lineY[j]) * Game.mapBG.iMapScale);
                            }
                            nShipLine.buildData();
                            addShipLine(nShipLine);
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        try {
            for (int a = 0; a < GameValues.ships.SHIP_AGES; ++a) {
                final List<Image> tImages = new ArrayList<Image>();
                for (int k = 0; k < GameValues.ships.SHIP_IMAGES; ++k) {
                    tImages.add(new Image(ImageManager.loadTexture("gfx/map/ship_" + a + "_" + k + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat));
                }
                ShipManager.shipImg.add(tImages);
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        for (int l = 0; l < ShipManager.shipLinesSize; ++l) {
            ShipManager.ships.add(new Ship2(l));
            ShipManager.shipsInPort.add(l);
        }
        ShipManager.shipsInPortSize = ShipManager.shipsInPort.size();
        updateLimitOfShipsAtSea();
    }
    
    public static final void loadShipLines_Provinces() {
        final int paddingCheck = 2 * Game.mapBG.iMapScale;
        for (int i = 0; i < ShipManager.shipLinesSize; ++i) {
            for (int j = 0; j < Game.getProvincesSize(); ++j) {
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(0).getPosX() && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(0).getPosX() && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(0).getPosY() && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(0).getPosY() && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(0).getPosX(), ShipManager.shipLines.get(i).points.get(0).getPosY())) {
                    ShipManager.shipLines.get(i).fromProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(0).getPosX() + paddingCheck && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(0).getPosX() + paddingCheck && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(0).getPosY() && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(0).getPosY() && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(0).getPosX() + paddingCheck, ShipManager.shipLines.get(i).points.get(0).getPosY())) {
                    ShipManager.shipLines.get(i).fromProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(0).getPosX() - paddingCheck && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(0).getPosX() - paddingCheck && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(0).getPosY() && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(0).getPosY() && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(0).getPosX() - paddingCheck, ShipManager.shipLines.get(i).points.get(0).getPosY())) {
                    ShipManager.shipLines.get(i).fromProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(0).getPosX() && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(0).getPosX() && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(0).getPosY() + paddingCheck && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(0).getPosY() + paddingCheck && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(0).getPosX(), ShipManager.shipLines.get(i).points.get(0).getPosY() + paddingCheck)) {
                    ShipManager.shipLines.get(i).fromProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(0).getPosX() && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(0).getPosX() && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(0).getPosY() - paddingCheck && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(0).getPosY() - paddingCheck && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(0).getPosX(), ShipManager.shipLines.get(i).points.get(0).getPosY() - paddingCheck)) {
                    ShipManager.shipLines.get(i).fromProvinceID = j;
                    break;
                }
            }
        }
        for (int i = 0; i < ShipManager.shipLinesSize; ++i) {
            for (int j = 0; j < Game.getProvincesSize(); ++j) {
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX(), ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY())) {
                    ShipManager.shipLines.get(i).toProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() + paddingCheck && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() + paddingCheck && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() + paddingCheck, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY())) {
                    ShipManager.shipLines.get(i).toProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() - paddingCheck && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() - paddingCheck && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() - paddingCheck, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY())) {
                    ShipManager.shipLines.get(i).toProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() + paddingCheck && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() + paddingCheck && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX(), ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() + paddingCheck)) {
                    ShipManager.shipLines.get(i).toProvinceID = j;
                    break;
                }
                if (Game.getProvince(j).getMinX() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() && Game.getProvince(j).getMaxX() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX() && Game.getProvince(j).getMinY() <= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() - paddingCheck && Game.getProvince(j).getMaxY() >= ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() - paddingCheck && Game.pathContains(j, ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosX(), ShipManager.shipLines.get(i).points.get(ShipManager.shipLines.get(i).pointsSize - 1).getPosY() - paddingCheck)) {
                    ShipManager.shipLines.get(i).toProvinceID = j;
                    break;
                }
            }
        }
    }
    
    public static final void addShipLine(final ShipLine nShipLine) {
        ShipManager.shipLines.add(nShipLine);
        ShipManager.shipLinesSize = ShipManager.shipLines.size();
    }
    
    public static final void updateLimitOfShipsAtSea() {
        ShipManager.limitOfShipsAtSea = (int)Math.min((float)ShipManager.shipLinesSize, Game.settingsManager.SHIPS_ON_MAP / 100.0f * ShipManager.shipLinesSize);
    }
    
    static {
        ShipManager.shipLines = new ArrayList<ShipLine>();
        ShipManager.shipLinesSize = 0;
        ShipManager.ships = new ArrayList<Ship2>();
        ShipManager.shipImg = new ArrayList<List<Image>>();
        ShipManager.limitOfShipsAtSea = 0;
        ShipManager.shipsAtSea = new ArrayList<Integer>();
        ShipManager.shipsAtSeaSize = 0;
        ShipManager.shipsInPort = new ArrayList<Integer>();
        ShipManager.shipsInPortSize = 0;
    }
}
