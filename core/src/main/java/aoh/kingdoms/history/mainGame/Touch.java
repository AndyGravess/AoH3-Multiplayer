// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import java.util.List;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.map.map.Ship.Ship2;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.province.ProvinceHover;
import aoc.kingdoms.lukasz.map.map.Ship.ShipManager;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;

public class Touch
{
    public static int buttonTouch;
    public static int mousePosX;
    public static int mousePosY;
    public static boolean selectArmiesMode;
    public static final int CAPITAL_FLAG_L = 4;
    public static final int CAPITAL_FLAG_M = 1;
    
    protected static final void setMousePosXY(final int nMousePosX, final int nMousePosY) {
        Touch.mousePosX = nMousePosX;
        Touch.mousePosY = nMousePosY;
    }
    
    public static final int getMousePosX() {
        return Touch.mousePosX;
    }
    
    public static final int getMousePosY() {
        return Touch.mousePosY;
    }
    
    protected final void actionDown(final int nPosX, final int nPosY, final int nPointer, final int button) {
        if (nPointer == 0 && !Game.menuManager.actionDown(nPosX, nPosY)) {
            if (Touch.selectArmiesMode) {
                Game.mapTouchManager.iSelectBoxX = nPosX;
                Game.mapTouchManager.iSelectBoxY = nPosY;
                Game.mapTouchManager.iSelectBoxWidth = 1;
                Game.mapTouchManager.iSelectBoxHeight = 1;
                Game.mapTouchManager.selectMode = true;
                Touch.selectArmiesMode = false;
            }
            else {
                Game.mapTouchManager.actionDown(nPosX, nPosY, nPointer, button);
            }
        }
    }
    
    protected final void actionMove(final int nPosX, final int nPosY, final int nPosX2, final int nPosY2) {
        Game.mapTouchManager.actionMove(nPosX, nPosY, nPosX2, nPosY2);
    }
    
    protected final void actionMove(final int nPosX, final int nPosY, final int nPointer) {
        if (nPointer == 0 && !Game.menuManager.actionMove(nPosX, nPosY)) {
            Game.mapTouchManager.actionMove(nPosX, nPosY, nPointer);
        }
    }
    
    protected final void actionUp(final int nPosX, final int nPosY, final int nPointer, final int button) {
        if (nPointer == 0) {
            if (!Game.menuManager.actionUp(nPosX, nPosY, nPointer, button)) {
                Game.mapTouchManager.actionUp(nPosX, nPosY, nPointer, button);
            }
            resetAllModes();
        }
        else if (Game.mapScale.getScaleMode()) {
            resetAllModes();
        }
        if (!CFG.isDesktop()) {
            Game.hoverManager.resetHoverActive_Menu();
        }
    }
    
    protected final void actionMove_Hover(final int nPosX, final int nPosY) {
        if (Game.menuManager.getFromViewID() < 0) {
            if (Game.hoverManager.actionMove_Hover(nPosX, nPosY, false)) {}
            if (Game.hoverManager.hoverActiveMenuElementID < 0 && Game.hoverManager.hoverActiveSliderMenuID < 0 && Game.hoverManager.hoverActiveMenuTitleID < 0) {
                if (Renderer.drawArmyInProvince) {
                    if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
                        if (Game.menuManager.getInGame()) {
                            if (this.updateHoveredArmy(nPosX, nPosY)) {
                                Game.iHoveredProvinceID = -1;
                                Game.hoveredBattle.iProvinceID = -1;
                                Game.hoveredSiegeID = -1;
                                Game.hoveredShipID = -1;
                            }
                            else if (this.updateHoveredBattle(nPosX, nPosY)) {
                                Game.iHoveredProvinceID = -1;
                                Game.hoveredArmy.iProvinceID = -1;
                                Game.hoveredSiegeID = -1;
                                Game.hoveredShipID = -1;
                            }
                            else if (this.updateHoveredSiege(nPosX, nPosY)) {
                                Game.iHoveredProvinceID = -1;
                                Game.hoveredArmy.iProvinceID = -1;
                                Game.hoveredBattle.iProvinceID = -1;
                                Game.hoveredShipID = -1;
                            }
                            else if (this.updateHoveredCapitalProvince_Flag(nPosX, nPosY)) {
                                Game.iHoveredProvinceID = -1;
                                Game.hoveredBattle.iProvinceID = -1;
                                Game.hoveredSiegeID = -1;
                                Game.hoveredShipID = -1;
                            }
                            else if (this.updateHoveredShip(nPosX, nPosY)) {
                                Game.iHoveredProvinceID = -1;
                                Game.hoveredArmy.iProvinceID = -1;
                                Game.hoveredBattle.iProvinceID = -1;
                                Game.hoveredSiegeID = -1;
                            }
                            else {
                                Game.updateHoveredProvince_Hover(nPosX, nPosY);
                            }
                        }
                        else if ((Game.menuManager.getInNewGame() || Game.menuManager.getInGameLost()) && this.updateHoveredCapitalProvince_Flag(nPosX, nPosY)) {
                            Game.iHoveredProvinceID = -1;
                            Game.hoveredBattle.iProvinceID = -1;
                            Game.hoveredSiegeID = -1;
                            Game.hoveredShipID = -1;
                        }
                        else {
                            Game.updateHoveredProvince_Hover(nPosX, nPosY);
                        }
                    }
                    else {
                        Game.updateHoveredProvince_Hover(nPosX, nPosY);
                    }
                }
                else {
                    Game.updateHoveredProvince_HoverInMapMode(nPosX, nPosY);
                }
            }
            else {
                Game.hoveredArmy.iProvinceID = -1;
                Game.hoveredBattle.iProvinceID = -1;
                Game.hoveredSiegeID = -1;
                Game.hoveredShipID = -1;
                Game.provinceHover_Informations = null;
                Game.iHoveredProvinceID = -1;
            }
            Game.hoverManager.updateHoveredMenuElement_Hover(nPosX, nPosY);
        }
    }
    
    public final boolean updateHoveredShip(final int nPosX, final int nPosY) {
        if (Game.hoveredShipID >= 0) {
            if (isMouseOverShip(nPosX, nPosY, Game.hoveredShipID)) {
                return true;
            }
            Game.hoveredShipID = -1;
        }
        for (int i = 0; i < ShipManager.shipLinesSize; ++i) {
            if (isMouseOverShip(nPosX, nPosY, i)) {
                Game.hoveredShipID = i;
                ProvinceHover.updateShipHovered();
                return true;
            }
        }
        return false;
    }
    
    public final boolean updateHoveredCapitalProvince_Flag(final int nPosX, final int nPosY) {
        if (Game.iHoveredCapitalProvinceID >= 0) {
            if (isMouseOverCapitalProvince_Flag(nPosX, nPosY, Game.iHoveredCapitalProvinceID)) {
                return true;
            }
            Game.iHoveredCapitalProvinceID = -1;
        }
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).isCapital && Game.getProvince(Game.getProvinceInViewID(i)).getCitiesSize() > 0 && isMouseOverCapitalProvince_Flag(nPosX, nPosY, Game.getProvinceInViewID(i))) {
                Game.iHoveredCapitalProvinceID = Game.getProvinceInViewID(i);
                ProvinceHover.updateProvinceHoverCapitalFlag();
                return true;
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isCapital && Game.getProvince(Game.getExtraProvinceInViewID(i)).getCitiesSize() > 0 && isMouseOverCapitalProvince_Flag(nPosX, nPosY, Game.getExtraProvinceInViewID(i))) {
                Game.iHoveredCapitalProvinceID = Game.getExtraProvinceInViewID(i);
                ProvinceHover.updateProvinceHoverCapitalFlag();
                return true;
            }
        }
        return false;
    }
    
    public final boolean updateHoveredBattle(final int nPosX, final int nPosY) {
        try {
            if (Game.hoveredBattle.iProvinceID >= 0) {
                int i = 0;
                while (i < Game.battleManager.getBattleSize()) {
                    if (Game.battleManager.getBattle(i).provinceID == Game.hoveredBattle.iProvinceID && Game.battleManager.getBattle(i).key.equals(Game.hoveredBattle.key)) {
                        if (!isMouseOverBattle(nPosX, nPosY, i)) {
                            Game.hoveredBattle.iProvinceID = -1;
                            break;
                        }
                        return true;
                    }
                    else {
                        ++i;
                    }
                }
            }
            for (int i = 0; i < Game.battleManager.getBattleSize(); ++i) {
                if (isMouseOverBattle(nPosX, nPosY, i)) {
                    Game.hoveredBattle.iProvinceID = Game.battleManager.getBattle(i).provinceID;
                    Game.hoveredBattle.key = Game.battleManager.getBattle(i).key;
                    ProvinceHover.updateProvinceHoverBattle();
                    Game.animationHover.resetAnimationData();
                    return true;
                }
            }
        }
        catch (final Exception ex) {}
        return false;
    }
    
    public static final boolean isMouseOverBattle(final int nPosX, final int nPosY, final int nBattleID) {
        try {
            return nPosX >= Game.battleManager.getBattle(nBattleID).posX && nPosX <= Game.battleManager.getBattle(nBattleID).posX + Game.battleManager.getBattle(nBattleID).battleWidth && nPosY >= Game.battleManager.getBattle(nBattleID).posY && nPosY <= Game.battleManager.getBattle(nBattleID).posY + ImageManager.getImage(Images.army).getHeight();
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static final boolean isMouseOverSiege(final int nPosX, final int nPosY, final int nProvinceID) {
        try {
            return nPosX >= SiegeManager.getArmyPosX(nProvinceID) - SiegeManager.getWidth() / 2 && nPosX <= SiegeManager.getArmyPosX(nProvinceID) + SiegeManager.getWidth() / 2 && nPosY >= SiegeManager.getArmyPosY(nProvinceID) - SiegeManager.getHeight() && nPosY <= SiegeManager.getArmyPosY(nProvinceID);
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public final boolean updateHoveredSiege(final int nPosX, final int nPosY) {
        try {
            if (Game.hoveredSiegeID >= 0) {
                if (Game.getProvinceData4(Game.hoveredSiegeID).isUnderSiege() && isMouseOverSiege(nPosX, nPosY, Game.hoveredSiegeID)) {
                    return true;
                }
                Game.hoveredSiegeID = -1;
            }
            for (int i = 0; i < SiegeManager.iProvincesSize; ++i) {
                if (isMouseOverSiege(nPosX, nPosY, SiegeManager.lProvinces.get(i))) {
                    Game.hoveredSiegeID = SiegeManager.lProvinces.get(i);
                    ProvinceHover.updateProvinceHoverSiege();
                    Game.animationHover.resetAnimationData();
                    return true;
                }
            }
        }
        catch (final Exception ex) {}
        return false;
    }
    
    public final boolean updateHoveredArmy(final int nPosX, final int nPosY) {
        try {
            if (Game.hoveredArmy.iProvinceID >= 0) {
                if (Game.getProvince(Game.hoveredArmy.iProvinceID).getFogDrawArmy()) {
                    if (Game.hoveredArmy.iArmyID < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmySize()) {
                        if (!Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).inBattle && isMouseOverArmy(nPosX, nPosY, Game.hoveredArmy.iProvinceID, Game.hoveredArmy.iArmyID)) {
                            return true;
                        }
                        Game.hoveredArmy.iProvinceID = -1;
                        Game.hoveredArmy.iArmyID = 0;
                    }
                    else {
                        Game.hoveredArmy.iProvinceID = -1;
                        Game.hoveredArmy.iArmyID = 0;
                    }
                }
                else {
                    Game.hoveredArmy.iProvinceID = -1;
                    Game.hoveredArmy.iArmyID = 0;
                }
            }
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getFogDrawArmy()) {
                    for (int j = 0; j < Game.getProvince(Game.getProvinceInViewID(i)).getArmySize(); ++j) {
                        if (!Game.getProvince(Game.getProvinceInViewID(i)).getArmy(j).inBattle && isMouseOverArmy(nPosX, nPosY, Game.getProvinceInViewID(i), j)) {
                            Game.hoveredArmy.iProvinceID = Game.getProvinceInViewID(i);
                            Game.hoveredArmy.iArmyID = j;
                            Game.hoveredArmy.key = Game.getProvince(Game.getProvinceInViewID(i)).getArmy(j).key;
                            Game.hoveredArmy.iCivID = Game.getProvince(Game.getProvinceInViewID(i)).getArmy(j).civID;
                            ProvinceHover.updateProvinceHoverArmy();
                            Game.animationHover.resetAnimationData();
                            return true;
                        }
                    }
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getFogDrawArmy()) {
                    for (int j = 0; j < Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmySize(); ++j) {
                        if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmy(j).inBattle && isMouseOverArmy(nPosX, nPosY, Game.getExtraProvinceInViewID(i), j)) {
                            Game.hoveredArmy.iProvinceID = Game.getExtraProvinceInViewID(i);
                            Game.hoveredArmy.iArmyID = j;
                            Game.hoveredArmy.key = Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmy(j).key;
                            Game.hoveredArmy.iCivID = Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmy(j).civID;
                            ProvinceHover.updateProvinceHoverArmy();
                            Game.animationHover.resetAnimationData();
                            return true;
                        }
                    }
                }
            }
            for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getSeaProvinceInViewID(i)).getFogDrawArmy()) {
                    for (int j = 0; j < Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmySize(); ++j) {
                        if (!Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmy(j).inBattle && isMouseOverArmy(nPosX, nPosY, Game.getSeaProvinceInViewID(i), j)) {
                            Game.hoveredArmy.iProvinceID = Game.getSeaProvinceInViewID(i);
                            Game.hoveredArmy.iArmyID = j;
                            Game.hoveredArmy.key = Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmy(j).key;
                            Game.hoveredArmy.iCivID = Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmy(j).civID;
                            ProvinceHover.updateProvinceHoverArmy();
                            Game.animationHover.resetAnimationData();
                            return true;
                        }
                    }
                }
            }
            for (int i = 0; i < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getWastelandProvinceInViewID(i)).getFogDrawArmy()) {
                    for (int j = 0; j < Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmySize(); ++j) {
                        if (!Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmy(j).inBattle && isMouseOverArmy(nPosX, nPosY, Game.getWastelandProvinceInViewID(i), j)) {
                            Game.hoveredArmy.iProvinceID = Game.getWastelandProvinceInViewID(i);
                            Game.hoveredArmy.iArmyID = j;
                            Game.hoveredArmy.key = Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmy(j).key;
                            Game.hoveredArmy.iCivID = Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmy(j).civID;
                            ProvinceHover.updateProvinceHoverArmy();
                            Game.animationHover.resetAnimationData();
                            return true;
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {}
        return false;
    }
    
    public static final boolean isMouseOverArmy(final int nPosX, final int nPosY, final int nProvinceID, final int nArmyID) {
        try {
            final int nX = ProvinceDrawArmy.getArmyPosX(nProvinceID, nArmyID);
            final int nY = ProvinceDrawArmy.getArmyPosY(nProvinceID, nArmyID);
            final int nWidth = ProvinceDrawArmy.getArmyWidth(Game.getProvince(nProvinceID).getArmy(nArmyID).iArmyWidth);
            final int nHeight = ProvinceDrawArmy.getArmyHeight();
            return nPosX >= nX && nPosX <= nX + nWidth && nPosY >= nY && nPosY <= nY + nHeight;
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static final boolean isMouseOverCapitalProvince_Flag(final int nPosX, final int nPosY, final int nProvinceID) {
        try {
            if (!Game.menuManager.getInGame() || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY) {
                final int nX = Game.getProvince(nProvinceID).getCity(0).getDrawPosX(nProvinceID, Game.mapScale.getCurrentScale()) - Game.getProvince(nProvinceID).getCity(0).getDrawWidth() / 2;
                final int nY = Game.getProvince(nProvinceID).getCity(0).getDrawPosY(nProvinceID, Game.mapScale.getCurrentScale());
                final int nWidth = ImageManager.getImage(Images.capitalLeft).getWidth();
                final int nHeight = ImageManager.getImage(Images.capitalLeft).getHeight();
                return nPosX >= nX && nPosX <= nX + nWidth && nPosY >= nY && nPosY <= nY + nHeight;
            }
            if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID == 4) {
                final int nX = Game.getProvince(nProvinceID).getCity(0).getDrawPosX(nProvinceID, Game.mapScale.getCurrentScale()) - ImageManager.getImage(Images.flagCapitalOver_l).getWidth() / 2;
                final int nY = Game.getProvince(nProvinceID).getCity(0).getDrawPosY(nProvinceID, Game.mapScale.getCurrentScale());
                final int nWidth = ImageManager.getImage(Images.flagCapitalOver_l).getWidth();
                final int nHeight = ImageManager.getImage(Images.flagCapitalOver_l).getHeight();
                return nPosX >= nX && nPosX <= nX + nWidth && nPosY >= nY && nPosY <= nY + nHeight;
            }
            if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID > 1) {
                final int nX = Game.getProvince(nProvinceID).getCity(0).getDrawPosX(nProvinceID, Game.mapScale.getCurrentScale()) - ImageManager.getImage(Images.flagCapitalOver).getWidth() / 2;
                final int nY = Game.getProvince(nProvinceID).getCity(0).getDrawPosY(nProvinceID, Game.mapScale.getCurrentScale());
                final int nWidth = ImageManager.getImage(Images.flagCapitalOver).getWidth();
                final int nHeight = ImageManager.getImage(Images.flagCapitalOver).getHeight();
                return nPosX >= nX && nPosX <= nX + nWidth && nPosY >= nY && nPosY <= nY + nHeight;
            }
            final int nX = Game.getProvince(nProvinceID).getCity(0).getDrawPosX(nProvinceID, Game.mapScale.getCurrentScale()) - ImageManager.getImage(Images.flagCapitalOver_s).getWidth() / 2;
            final int nY = Game.getProvince(nProvinceID).getCity(0).getDrawPosY(nProvinceID, Game.mapScale.getCurrentScale());
            final int nWidth = ImageManager.getImage(Images.flagCapitalOver_s).getWidth();
            final int nHeight = ImageManager.getImage(Images.flagCapitalOver_s).getHeight();
            return nPosX >= nX && nPosX <= nX + nWidth && nPosY >= nY && nPosY <= nY + nHeight;
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static final boolean isMouseOverShip(final int nPosX, final int nPosY, final int shipID) {
        try {
            final int nX = (int)((ShipManager.ships.get(shipID).posX + Game.mapCoords.getPosX()) * Game.mapScale.getCurrentScale()) - ShipManager.shipImg.get(0).get(0).getWidth() / 2;
            final int nY = (int)((ShipManager.ships.get(shipID).posY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale()) - ShipManager.shipImg.get(0).get(0).getHeight() / 2;
            final int nWidth = ShipManager.shipImg.get(0).get(0).getWidth();
            final int nHeight = ShipManager.shipImg.get(0).get(0).getHeight();
            return nPosX >= nX && nPosX <= nX + nWidth && nPosY >= nY && nPosY <= nY + nHeight;
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static final void resetAllModes() {
        Game.menuManager.setActiveSliderMenuID(-1);
        Game.menuManager.setActiveMenuElementID(-1);
        Game.menuManager.setCloseMenuMode(false);
        Game.menuManager.setMenu_MoveByTitleMode(false);
        Game.menuManager.setMenu_ResizeMode(false);
        Game.menuManager.setMenu_MoveInnerElements(false);
        Game.menuManager.setPieChartMode(false);
        Game.menuManager.setTextScrollableMode(false);
        Game.menuManager.setColorPickerMode(false);
        Game.menuManager.graphVertical_ScrollMode_X = false;
        Game.menuManager.graphVertical_ScrollMode_Y = false;
        Game.mapScale.resetScaleInfo();
        Game.mapScroll.resetScrollInfo();
        Game.mapTouchManager.selectMode = false;
    }
    
    static {
        Touch.buttonTouch = 0;
        Touch.mousePosX = 0;
        Touch.mousePosY = 0;
        Touch.selectArmiesMode = false;
    }
}
