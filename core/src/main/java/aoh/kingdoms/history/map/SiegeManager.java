// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map;

import java.util.Collections;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_SiegeStart;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.Color;
import java.util.List;

public class SiegeManager
{
    public static List<Integer> lProvinces;
    public static int iProvincesSize;
    public static Color progressBarBG;
    public static Color progressBar;
    
    public static void buildProvinceUnderSiege_Load() {
        SiegeManager.lProvinces.clear();
        SiegeManager.iProvincesSize = 0;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvinceData4(i).isUnderSiege()) {
                    SiegeManager.lProvinces.add(i);
                }
            }
        }
        SiegeManager.iProvincesSize = SiegeManager.lProvinces.size();
    }
    
    public static final void addProvinceSiege(final int nProvinceID) {
        for (int i = 0; i < SiegeManager.iProvincesSize; ++i) {
            if (SiegeManager.lProvinces.get(i) == nProvinceID) {
                return;
            }
        }
        SiegeManager.lProvinces.add(nProvinceID);
        SiegeManager.iProvincesSize = SiegeManager.lProvinces.size();
    }
    
    public static final void removeProvinceSiege(final int nProvinceID) {
        for (int i = 0; i < SiegeManager.iProvincesSize; ++i) {
            if (SiegeManager.lProvinces.get(i) == nProvinceID) {
                SiegeManager.lProvinces.remove(i);
                SiegeManager.iProvincesSize = SiegeManager.lProvinces.size();
                Game.getProvinceData4(nProvinceID).setIsUnderSiege_Just(false);
                return;
            }
        }
    }
    
    public static final boolean isProvinceUnderSiege(final int nProvinceID) {
        for (int i = 0; i < SiegeManager.iProvincesSize; ++i) {
            if (SiegeManager.lProvinces.get(i) == nProvinceID) {
                return true;
            }
        }
        return false;
    }
    
    public static final void checkForSiege(final int nProvinceID) {
        try {
            if (Game.getProvince(nProvinceID).getSeaProvince()) {
                return;
            }
            int numOfRegiments = 0;
            if (Game.getProvince(nProvinceID).isOccupied()) {
                if (!Game.getProvinceData4(nProvinceID).isUnderSiege()) {
                    for (int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                        if (!Game.getProvince(nProvinceID).getArmy(j).inBattle && !Game.getProvince(nProvinceID).getArmy(j).inRetreat && !Game.getProvince(nProvinceID).getArmy(j).inMovement) {
                            if (Game.getProvince(nProvinceID).getArmy(j).fMorale >= GameValues.siege.SIEGE_MIN_MORALE) {
                                if (DiplomacyManager.isAtWar(Game.getProvinceData(nProvinceID).getOccupiedByCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                                    if ((numOfRegiments += Game.getProvince(nProvinceID).getArmy(j).iArmyRegimentSize) >= GameValues.siege.SIEGE_REGIMENTS_MIN) {
                                        Game.getProvinceData4(nProvinceID).setIsUnderSiege(nProvinceID, true);
                                        Game.getProvinceData4(nProvinceID).setSiegeProgress(0.0f);
                                        addProvinceSiege(nProvinceID);
                                        AI_SiegeStart.siegeStarted_Attacker(nProvinceID);
                                        AI_SiegeStart.siegeStarted_Defend(nProvinceID);
                                        return;
                                    }
                                }
                                else if (Game.getProvince(nProvinceID).getCivID() == Game.getProvince(nProvinceID).getArmy(j).civID) {
                                    Game.getProvince(nProvinceID).retakeOccupiedProvince_Peace();
                                }
                            }
                        }
                    }
                }
                else {
                    addProvinceSiege(nProvinceID);
                }
            }
            else if (!Game.getProvinceData4(nProvinceID).isUnderSiege()) {
                for (int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                    if (!Game.getProvince(nProvinceID).getArmy(j).inBattle && !Game.getProvince(nProvinceID).getArmy(j).inMovement && !Game.getProvince(nProvinceID).getArmy(j).inRetreat && Game.getProvince(nProvinceID).getArmy(j).fMorale >= GameValues.siege.SIEGE_MIN_MORALE && DiplomacyManager.isAtWar(Game.getProvince(nProvinceID).getCivID(), Game.getProvince(nProvinceID).getArmy(j).civID) && (numOfRegiments += Game.getProvince(nProvinceID).getArmy(j).iArmyRegimentSize) >= GameValues.siege.SIEGE_REGIMENTS_MIN) {
                        Game.getProvinceData4(nProvinceID).setIsUnderSiege(nProvinceID, true);
                        Game.getProvinceData4(nProvinceID).setSiegeProgress(0.0f);
                        addProvinceSiege(nProvinceID);
                        AI_SiegeStart.siegeStarted_Attacker(nProvinceID);
                        AI_SiegeStart.siegeStarted_Defend(nProvinceID);
                        if (Game.getProvince(nProvinceID).getCivID() == Game.player.iCivID) {
                            Game.player.addNotification(new Notification(Notification.Notification_Type.SIEGE, Game.getProvince(nProvinceID).getProvinceName() + ": " + Game.lang.get("UnderSiege"), Images.siege, Game_Calendar.TURN_ID, Notification.Notification_BG.NEUTRAL_BG, nProvinceID) {
                                @Override
                                public void onAction() {
                                    Game.mapCoords.centerToProvinceID(this.id);
                                }
                            });
                        }
                        return;
                    }
                }
            }
            else {
                addProvinceSiege(nProvinceID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static int getSiegeDaysLeft(final int iProvinceID) {
        return (int)(Math.ceil(Game.getProvince(iProvinceID).getFortDefense() - Game.getProvinceData4(iProvinceID).getSiegeProgress()) / getDailySiegeProgress(iProvinceID));
    }
    
    public static final float getDailySiegeProgress(final int iProvinceID) {
        float fSiegeProgress = 0.0f;
        if (Game.getProvince(iProvinceID).isOccupied()) {
            for (int j = 0; j < Game.getProvince(iProvinceID).getArmySize(); ++j) {
                if (!Game.getProvince(iProvinceID).getArmy(j).inBattle && !Game.getProvince(iProvinceID).getArmy(j).inMovement) {
                    if (DiplomacyManager.isAtWar(Game.getProvinceData(iProvinceID).getOccupiedByCivID(), Game.getProvince(iProvinceID).getArmy(j).civID)) {
                        fSiegeProgress += Game.getProvince(iProvinceID).getArmy(j).getSiegeProgressPerDay() * Math.min(Game.getProvince(iProvinceID).getArmy(j).fMorale, 1.0f);
                    }
                }
            }
        }
        else {
            for (int j = 0; j < Game.getProvince(iProvinceID).getArmySize(); ++j) {
                if (!Game.getProvince(iProvinceID).getArmy(j).inBattle && !Game.getProvince(iProvinceID).getArmy(j).inMovement) {
                    if (DiplomacyManager.isAtWar(Game.getProvince(iProvinceID).getCivID(), Game.getProvince(iProvinceID).getArmy(j).civID)) {
                        fSiegeProgress += Game.getProvince(iProvinceID).getArmy(j).getSiegeProgressPerDay() * Math.min(Game.getProvince(iProvinceID).getArmy(j).fMorale, 1.0f);
                    }
                }
            }
        }
        return Math.min(GameValues.siege.SIEGE_MAX_PROGRESS, fSiegeProgress);
    }
    
    public static boolean isStillUnderSiege(final int nProvinceID) {
        try {
            if (Game.getProvince(nProvinceID).isOccupied()) {
                for (int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                    if (DiplomacyManager.isAtWar(Game.getProvinceData(nProvinceID).getOccupiedByCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                        return true;
                    }
                }
            }
            else {
                for (int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                    if (DiplomacyManager.isAtWar(Game.getProvince(nProvinceID).getCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                        return true;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public static final void updateSieges() {
        try {
            for (int i = 0; i < SiegeManager.iProvincesSize; ++i) {
                final int provinceID = SiegeManager.lProvinces.get(i);
                if (Game.getProvinceData4(provinceID).isUnderSiege() && isStillUnderSiege(provinceID)) {
                    Game.getProvinceData4(provinceID).setSiegeProgress(Math.min(Game.getProvinceData4(provinceID).getSiegeProgress() + getDailySiegeProgress(provinceID), (float)Game.getProvince(provinceID).getFortDefense()));
                    if (Game.getProvinceData4(provinceID).getSiegeProgress() >= Game.getProvince(provinceID).getFortDefense()) {
                        if (Game.getProvince(provinceID).isOccupied()) {
                            Game.getProvince(provinceID).retakeOccupiedProvince();
                        }
                        else {
                            Game.getProvince(provinceID).occupyProvince();
                        }
                        Game.getProvinceData4(provinceID).setIsUnderSiege_Just(false);
                        removeProvinceSiege(provinceID);
                    }
                }
                else {
                    SiegeManager.lProvinces.remove(i);
                    SiegeManager.iProvincesSize = SiegeManager.lProvinces.size();
                    Game.getProvinceData4(provinceID).setIsUnderSiege_Just(false);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void draw(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            drawSieges(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
        else if (Game.mapTouchManager.selectMode) {
            drawSieges(oSB, 1.0f);
        }
        else if (ProvinceDrawArmy.drawHideAnimation && Game.mapScale.getCurrentScale() >= ProvinceDrawArmy.DRAW_ARMY_MIN_SCALE_ANIMATION) {
            drawSieges(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
    }
    
    private static final void drawSieges(final SpriteBatch oSB, final float fAlpha) {
        try {
            if (Game.FOG_OF_WAR) {
                for (int i = SiegeManager.iProvincesSize - 1; i >= 0; --i) {
                    if (Game.getProvince(SiegeManager.lProvinces.get(i)).getDrawProvince()) {
                        if (Game.getProvince(SiegeManager.lProvinces.get(i)).getFogDrawArmy()) {
                            drawSiege(oSB, fAlpha, SiegeManager.lProvinces.get(i));
                        }
                    }
                }
            }
            else {
                for (int i = SiegeManager.iProvincesSize - 1; i >= 0; --i) {
                    if (Game.getProvince(SiegeManager.lProvinces.get(i)).getDrawProvince()) {
                        drawSiege(oSB, fAlpha, SiegeManager.lProvinces.get(i));
                    }
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    private static final void drawSiege(final SpriteBatch oSB, final float fAlpha, final int iProvinceID) {
        try {
            int iPosX = getArmyPosX(iProvinceID);
            int iPosY = getArmyPosY(iProvinceID);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
            final int tCenterX = (ImageManager.getImage(Images.progressBarFrame).getWidth() - ImageManager.getImage(Images.progressBarFrameMask).getWidth()) / 2;
            final int tCenterY = (ImageManager.getImage(Images.progressBarFrame).getHeight() - ImageManager.getImage(Images.progressBarFrameMask).getHeight()) / 2;
            ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).draw(oSB, iPosX - ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getWidth() / 2, iPosY - ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2);
            oSB.setColor(new Color(SiegeManager.progressBarBG.r, SiegeManager.progressBarBG.g, SiegeManager.progressBarBG.b, fAlpha));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, (iPosX -= ImageManager.getImage(Images.progressBarFrame).getWidth() / 2) + tCenterX, (iPosY -= ImageManager.getImage(Images.progressBarFrame).getHeight()) + tCenterY);
            if (Game.hoveredSiegeID == iProvinceID) {
                oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fAlpha));
            }
            else {
                oSB.setColor(new Color(SiegeManager.progressBar.r, SiegeManager.progressBar.g, SiegeManager.progressBar.b, fAlpha));
            }
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, iPosX + tCenterX, iPosY + tCenterY, (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * Game.getProvince(iProvinceID).getSiegeProgress()), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
            ImageManager.getImage(Images.progressBarFrame).draw(oSB, iPosX, iPosY);
            oSB.setColor(Color.WHITE);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private static final void drawProvinceArmyFlag(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nCivID, final int ImageID, final boolean flipX) {
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY, ImageManager.getImage(Images.armyFlag).getWidth() + 3 + 2, ImageManager.getImage(ImageID).getHeight(), flipX, false);
        Game.getCiv(nCivID).getFlag().draw(oSB, nPosX + 3, nPosY + 2, ImageManager.getImage(Images.armyFlag).getWidth(), ImageManager.getImage(Images.armyFlag).getHeight());
        ImageManager.getImage(Images.armyFlag).draw(oSB, nPosX + 3, nPosY + 2);
    }
    
    public static final int getArmyPosX(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale());
    }
    
    public static final int getArmyPosY(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale() - 4.0f - ImageManager.getImage(Images.progressBarFrame).getHeight());
    }
    
    public static final int getWidth() {
        return ImageManager.getImage(Images.progressBarFrameMask).getWidth();
    }
    
    public static final int getHeight() {
        return ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getHeight() + ImageManager.getImage(Images.progressBarFrame).getHeight() / 2;
    }
    
    public static void clearData() {
        try {
            for (int i = SiegeManager.lProvinces.size() - 1; i >= 0; --i) {
                Game.getProvinceData4(SiegeManager.lProvinces.get(i)).setIsUnderSiege_Just(false);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        SiegeManager.lProvinces.clear();
        SiegeManager.iProvincesSize = 0;
    }
    
    static {
        SiegeManager.lProvinces = Collections.synchronizedList(new ArrayList<Integer>());
        SiegeManager.iProvincesSize = 0;
        SiegeManager.progressBarBG = new Color(0.23529412f, 0.15686275f, 0.15686275f, 1.0f);
        SiegeManager.progressBar = new Color(0.15686275f, 0.5882353f, 0.84313726f, 1.0f);
    }
}
