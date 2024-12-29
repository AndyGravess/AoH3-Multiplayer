// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapContinents;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWasteland;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceInfo;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceSiege;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_Battle;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.jakowski.Touch;
import aoc.kingdoms.lukasz.map.province.ProvinceTouchExtraAction;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapTouchManager
{
    public int iStartMovePosX;
    public int iStartMovePosY;
    protected static boolean reverseDirectionX;
    protected static boolean reverseDirectionY;
    public int actionDownPosX;
    public int actionDownPosY;
    public boolean updateStartMovePosX;
    public boolean updateStartMovePosY;
    private long lActionDownTime;
    public boolean disableMovingMap;
    public boolean enableSelectMode;
    public boolean selectMode;
    public int iSelectBoxX;
    public int iSelectBoxY;
    public int iSelectBoxWidth;
    public int iSelectBoxHeight;
    public ReverseDirection mapMoveDirectionX;
    public ReverseDirection mapMoveDirectionY;
    public ReverseDirection2 mapMoveDirectionX2;
    public ReverseDirection2 mapMoveDirectionY2;
    
    public MapTouchManager() {
        this.actionDownPosX = 0;
        this.actionDownPosY = 0;
        this.lActionDownTime = 0L;
        this.disableMovingMap = false;
        this.enableSelectMode = true;
        this.selectMode = false;
        this.buildReversePos();
    }
    
    public final void drawSelectMode(final SpriteBatch oSB) {
        if (this.selectMode) {
            try {
                int nX = this.iSelectBoxX;
                if (this.iSelectBoxWidth == 0) {
                    this.iSelectBoxWidth = 1;
                }
                else if (this.iSelectBoxWidth < 0) {
                    nX += this.iSelectBoxWidth;
                }
                int nY = this.iSelectBoxY;
                if (this.iSelectBoxHeight == 0) {
                    this.iSelectBoxHeight = 1;
                }
                else if (this.iSelectBoxHeight < 0) {
                    nY += this.iSelectBoxHeight;
                }
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
                Images.pix.draw(oSB, nX, nY, Math.abs(this.iSelectBoxWidth), Math.abs(this.iSelectBoxHeight));
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.275f));
                Renderer.drawBox2(oSB, nX, nY, Math.abs(this.iSelectBoxWidth), Math.abs(this.iSelectBoxHeight), 1.0f);
                oSB.setColor(Color.WHITE);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public final void actionDown(final int nPosX, final int nPosY, final int nPointer, final int button) {
        try {
            this.setActionDownPosXY(nPosX, nPosY);
            if (this.enableSelectMode && button == Game.settingsManager.MAP_SELECT_ARMY_BUTTON) {
                this.iSelectBoxX = nPosX;
                this.iSelectBoxY = nPosY;
                this.iSelectBoxWidth = 1;
                this.iSelectBoxHeight = 1;
                this.selectMode = true;
                Game.setCursorDrag();
                return;
            }
            this.selectMode = false;
            if (Game.mapScroll.getScrollingTheMap()) {
                Game.mapScroll.stopScrollingTheMap();
            }
            try {
                ProvinceTouchExtraAction.actionDown_ExtraAction.extraAction(nPosX, nPosY, nPointer, button);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void actionMove(final int nPosX, final int nPosY, final int nPointer) {
        try {
            if (CFG.brushTool && Touch.buttonTouch == 0) {
                this.actionDownPosX = nPosX;
                this.actionDownPosY = nPosY;
                Game.gameActiveProvince.actionUp_setActiveProvinceID(nPosX, nPosY, nPointer, 0);
                return;
            }
            if (this.selectMode) {
                this.iSelectBoxWidth = nPosX - this.iSelectBoxX;
                this.iSelectBoxHeight = nPosY - this.iSelectBoxY;
                return;
            }
            this.actionMoveMap(nPosX, nPosY, nPointer);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    protected final void actionMoveMap(final int nPosX, final int nPosY, final int nPointer) {
        try {
            if (!Game.mapTouchManager.disableMovingMap) {
                if (this.updateStartMovePosX || this.updateStartMovePosY) {
                    this.updateStartMovePosXY(nPosX, nPosY);
                    this.updateStartMovePosX = false;
                    this.updateStartMovePosY = false;
                }
                Game.mapCoords.setNewPosX(this.mapMoveDirectionX2.getNewPos(this.iStartMovePosX, (int)(nPosX / Game.mapScale.getCurrentScale())));
                Game.mapCoords.setNewPosY(this.mapMoveDirectionY2.getNewPos(this.iStartMovePosY, (int)(nPosY / Game.mapScale.getCurrentScale())));
            }
            else {
                try {
                    ProvinceTouchExtraAction.actionMove_ExtraAction.extraAction(nPosX, nPosY, nPointer, 0);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void actionMove(final int nPosX, final int nPosY, final int nPosX2, final int nPosY2) {
        try {
            if (!Game.mapCoords.disableMovingMap && Game.mapScale.getEnableScaling()) {
                if (Game.mapScale.getStartScalePosY() <= 0) {
                    Game.mapScale.startScaleTheMap(nPosX, nPosX2, nPosY, nPosY2);
                }
                else {
                    Game.mapScale.scaleTheMap(nPosX, nPosX2, nPosY, nPosY2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void actionUp(final int nPosX, final int nPosY, final int nPointer, final int button) {
        try {
            if (Game.mapScale.getEnableScaling() && (button == Game.settingsManager.MAP_MOVE || button == 1)) {
                Game.mapScale.resetScaleOfMap(CFG.currentTimeMillis);
            }
            if (this.selectMode && (button == Game.settingsManager.MAP_SELECT_ARMY_BUTTON || !CFG.isDesktop())) {
                this.selectMode = false;
                Game.setCursorDefault();
                this.actionUpSelectMode(nPosX, nPosY);
                return;
            }
            if (Game.menuManager.getInGame() && !Game.chooseProvinceMode && Math.abs(nPosX - this.actionDownPosX) < CFG.PADDING / 2) {
                if (button == 1) {
                    if (Game.activeArmySize > 0) {
                        Game.gameActiveProvince.setProvinceID_PPM(nPosX, nPosY);
                        return;
                    }
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_RECRUIT_ARMY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_BUILDING && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_WARS && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_MERCENARIES_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_PEACE_VIEW) {
                        if (Game.iHoveredProvinceID >= 0) {
                            Game.setActiveProvinceID(Game.iHoveredProvinceID);
                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        }
                        if (Game.menuManager.getVisibleInGame_Civ()) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == InGame_Civ.iActiveCivID) {
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                            }
                            else {
                                Game.menuManager.rebuildInGame_Civ();
                                InGame_Civ.lTime = 0L;
                                Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                if (Game.menuManager.getVisibleInGame_CivBonuses() && InGame_Civ.iActiveCivID != 0 && InGame_CivBonuses.iCivID != InGame_Civ.iActiveCivID) {
                                    InGame_CivBonuses.iCivID = InGame_Civ.iActiveCivID;
                                    Game.menuManager.rebuildInGame_CivBonuses();
                                    Game.menuManager.setVisibleInGame_CivBonuses(true);
                                    InGame_CivBonuses.lTime = 0L;
                                }
                            }
                        }
                        else {
                            Game.menuManager.rebuildInGame_Civ();
                            Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                            if (Game.menuManager.getVisibleInGame_CivBonuses() && InGame_Civ.iActiveCivID != 0 && InGame_CivBonuses.iCivID != InGame_Civ.iActiveCivID) {
                                InGame_CivBonuses.iCivID = InGame_Civ.iActiveCivID;
                                Game.menuManager.rebuildInGame_CivBonuses();
                                Game.menuManager.setVisibleInGame_CivBonuses(true);
                                InGame_CivBonuses.lTime = 0L;
                            }
                        }
                        return;
                    }
                }
                else if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_RECRUIT_ARMY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_MERCENARIES_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INVEST_IN_ECONOMY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_MANPOWER && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_MOVE_CAPITAL && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_GROWTH_RATE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_BUILDING && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CONVERT_RELIGION && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CORE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_PEACE_VIEW && Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE && Renderer.drawArmyInProvince) {
                    if (CFG.isDesktop()) {
                        if (Game.hoveredBattle.iProvinceID >= 0) {
                            final int tBattleID = Game.battleManager.getBattleID(Game.hoveredBattle.iProvinceID, Game.hoveredBattle.key);
                            if (Touch.isMouseOverBattle(nPosX, nPosY, tBattleID)) {
                                InGame_Battle.iProvinceID = Game.hoveredBattle.iProvinceID;
                                InGame_Battle.key = Game.hoveredBattle.key;
                                if (Game.menuManager.getVisibleInGame_Siege()) {
                                    Game.menuManager.setVisibleInGame_Siege(false);
                                }
                                if (Game.menuManager.getVisibleInGame_War()) {
                                    Game.menuManager.setVisibleInGame_War(false);
                                }
                                Game.menuManager.showInGame_Battle_HideMenus();
                                Game.menuManager.rebuildInGame_Battle();
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.clearActiveArmy();
                                return;
                            }
                        }
                        else if (Game.hoveredSiegeID >= 0) {
                            if (Touch.isMouseOverSiege(nPosX, nPosY, Game.hoveredSiegeID)) {
                                if (Game.menuManager.getVisibleInGame_Siege() && InGame_ProvinceSiege.iProvinceID == Game.hoveredSiegeID) {
                                    Game.menuManager.setVisibleInGame_Siege(false);
                                }
                                else {
                                    InGame_ProvinceSiege.iProvinceID = Game.hoveredSiegeID;
                                    if (Game.menuManager.getVisibleInGame_Battle()) {
                                        Game.menuManager.setVisibleInGame_Battle(false);
                                    }
                                    if (Game.menuManager.getVisibleInGame_War()) {
                                        Game.menuManager.setVisibleInGame_War(false);
                                    }
                                    Game.menuManager.showInGame_Battle_HideMenus();
                                    Game.menuManager.rebuildInGame_Siege();
                                    Game.soundsManager.playSound(SoundsManager.SIEGE);
                                    Game.gameActiveProvince.resetLastActiveProvince();
                                    Game.setActiveProvinceID(-1);
                                    Game.clearActiveArmy();
                                }
                                return;
                            }
                        }
                        else if (Game.hoveredArmy.iProvinceID >= 0) {
                            if (Touch.isMouseOverArmy(nPosX, nPosY, Game.hoveredArmy.iProvinceID, Game.hoveredArmy.iArmyID)) {
                                boolean selectAllArmiesOfCivInProvince = false;
                                if (Game.activeArmy.size() == 1 && Game.getProvince(Game.hoveredArmy.iProvinceID).getArmySize() > 1 && Game.activeArmy.get(0).key.equals(Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).key)) {
                                    selectAllArmiesOfCivInProvince = true;
                                }
                                if (AA_KeyManager.CTRL_HOLD) {
                                    final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                    nHA.key = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).key;
                                    nHA.iCivID = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID;
                                    nHA.iProvinceID = Game.hoveredArmy.iProvinceID;
                                    nHA.iArmyID = Game.hoveredArmy.iArmyID;
                                    if (Game.isAlreadyActiveArmy(nHA.key)) {
                                        Game.removeActiveArmy(nHA.key);
                                    }
                                    else {
                                        Game.addActiveArmy(nHA);
                                    }
                                    ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                }
                                else {
                                    Game.clearActiveArmy();
                                    if (selectAllArmiesOfCivInProvince) {
                                        for (int i = 0; i < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmySize(); ++i) {
                                            if (Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(i).civID == Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID) {
                                                final Game.HoveredArmy nHA2 = new Game.HoveredArmy();
                                                nHA2.key = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(i).key;
                                                nHA2.iCivID = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(i).civID;
                                                nHA2.iProvinceID = Game.hoveredArmy.iProvinceID;
                                                nHA2.iArmyID = i;
                                                Game.addActiveArmy(nHA2);
                                                ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                            }
                                        }
                                    }
                                    else {
                                        final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                        nHA.key = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).key;
                                        nHA.iCivID = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID;
                                        nHA.iProvinceID = Game.hoveredArmy.iProvinceID;
                                        nHA.iArmyID = Game.hoveredArmy.iArmyID;
                                        Game.addActiveArmy(nHA);
                                        ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                    }
                                }
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.animationHover.resetAnimationData();
                                return;
                            }
                        }
                        else if (Game.iHoveredCapitalProvinceID >= 0 && Touch.isMouseOverCapitalProvince_Flag(nPosX, nPosY, Game.iHoveredCapitalProvinceID)) {
                            Game.gameActiveProvince.resetLastActiveProvince();
                            Game.setActiveProvinceID(Game.iHoveredCapitalProvinceID);
                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            Game.animationHover.resetAnimationData();
                            if (Game.menuManager.getVisibleInGame_Civ()) {
                                if (InGame_Civ.iActiveCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                    Game.menuManager.rebuildInGame_Civ();
                                    InGame_Civ.lTime = 0L;
                                    if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                        InGame_CivBonuses.iCivID = Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID();
                                        Game.menuManager.rebuildInGame_CivBonuses();
                                        Game.menuManager.setVisibleInGame_CivBonuses(true);
                                        InGame_CivBonuses.lTime = 0L;
                                    }
                                    Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                }
                                else {
                                    Game.menuManager.setVisibleInGame_Civ(false);
                                    Game.soundsManager.playSound(SoundsManager.SOUND_CLICK_TOP);
                                }
                            }
                            else {
                                Game.menuManager.rebuildInGame_Civ();
                                if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                    InGame_CivBonuses.iCivID = Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID();
                                    Game.menuManager.rebuildInGame_CivBonuses();
                                    Game.menuManager.setVisibleInGame_CivBonuses(true);
                                    InGame_CivBonuses.lTime = 0L;
                                }
                                Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                            }
                            Game.gameActiveProvince.resetLastActiveProvince();
                            Game.setActiveProvinceID(-1);
                            return;
                        }
                    }
                    else {
                        for (int j = 0; j < Game.battleManager.getBattleSize(); ++j) {
                            if (Touch.isMouseOverBattle(nPosX, nPosY, j)) {
                                InGame_Battle.iProvinceID = Game.battleManager.getBattle(j).provinceID;
                                InGame_Battle.key = Game.battleManager.getBattle(j).key;
                                if (Game.menuManager.getVisibleInGame_Siege()) {
                                    Game.menuManager.setVisibleInGame_Siege(false);
                                }
                                if (Game.menuManager.getVisibleInGame_War()) {
                                    Game.menuManager.setVisibleInGame_War(false);
                                }
                                Game.menuManager.showInGame_Battle_HideMenus();
                                Game.menuManager.rebuildInGame_Battle();
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.clearActiveArmy();
                                return;
                            }
                        }
                        for (int j = 0; j < SiegeManager.iProvincesSize; ++j) {
                            if (Touch.isMouseOverSiege(nPosX, nPosY, SiegeManager.lProvinces.get(j))) {
                                if (Game.menuManager.getVisibleInGame_Siege() && InGame_ProvinceSiege.iProvinceID == SiegeManager.lProvinces.get(j)) {
                                    Game.menuManager.setVisibleInGame_Siege(false);
                                }
                                else {
                                    InGame_ProvinceSiege.iProvinceID = SiegeManager.lProvinces.get(j);
                                    if (Game.menuManager.getVisibleInGame_Battle()) {
                                        Game.menuManager.setVisibleInGame_Battle(false);
                                    }
                                    if (Game.menuManager.getVisibleInGame_War()) {
                                        Game.menuManager.setVisibleInGame_War(false);
                                    }
                                    Game.menuManager.showInGame_Battle_HideMenus();
                                    Game.menuManager.rebuildInGame_Siege();
                                    Game.soundsManager.playSound(SoundsManager.SIEGE);
                                    Game.gameActiveProvince.resetLastActiveProvince();
                                    Game.setActiveProvinceID(-1);
                                    Game.clearActiveArmy();
                                }
                                return;
                            }
                        }
                        for (int j = 0; j < Game.NUM_OF_PROVINCES_IN_VIEW; ++j) {
                            if (Game.getProvince(Game.getProvinceInViewID(j)).getFogDrawArmy()) {
                                for (int k = 0; k < Game.getProvince(Game.getProvinceInViewID(j)).getArmySize(); ++k) {
                                    if (!Game.getProvince(Game.getProvinceInViewID(j)).getArmy(k).inBattle && Touch.isMouseOverArmy(nPosX, nPosY, Game.getProvinceInViewID(j), k)) {
                                        Game.clearActiveArmy();
                                        final Game.HoveredArmy nHA2 = new Game.HoveredArmy();
                                        nHA2.key = Game.getProvince(Game.getProvinceInViewID(j)).getArmy(k).key;
                                        nHA2.iCivID = Game.getProvince(Game.getProvinceInViewID(j)).getArmy(k).civID;
                                        nHA2.iProvinceID = Game.getProvinceInViewID(j);
                                        nHA2.iArmyID = k;
                                        Game.addActiveArmy(nHA2);
                                        ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                        return;
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++j) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(j)).getFogDrawArmy()) {
                                for (int k = 0; k < Game.getProvince(Game.getExtraProvinceInViewID(j)).getArmySize(); ++k) {
                                    if (!Game.getProvince(Game.getExtraProvinceInViewID(j)).getArmy(k).inBattle && Touch.isMouseOverArmy(nPosX, nPosY, Game.getExtraProvinceInViewID(j), k)) {
                                        Game.clearActiveArmy();
                                        final Game.HoveredArmy nHA2 = new Game.HoveredArmy();
                                        nHA2.key = Game.getProvince(Game.getExtraProvinceInViewID(j)).getArmy(k).key;
                                        nHA2.iCivID = Game.getProvince(Game.getExtraProvinceInViewID(j)).getArmy(k).civID;
                                        nHA2.iProvinceID = Game.getExtraProvinceInViewID(j);
                                        nHA2.iArmyID = k;
                                        Game.addActiveArmy(nHA2);
                                        ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                        return;
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++j) {
                            if (Game.getProvince(Game.getSeaProvinceInViewID(j)).getFogDrawArmy()) {
                                for (int k = 0; k < Game.getProvince(Game.getSeaProvinceInViewID(j)).getArmySize(); ++k) {
                                    if (!Game.getProvince(Game.getSeaProvinceInViewID(j)).getArmy(k).inBattle && Touch.isMouseOverArmy(nPosX, nPosY, Game.getSeaProvinceInViewID(j), k)) {
                                        Game.clearActiveArmy();
                                        final Game.HoveredArmy nHA2 = new Game.HoveredArmy();
                                        nHA2.key = Game.getProvince(Game.getSeaProvinceInViewID(j)).getArmy(k).key;
                                        nHA2.iCivID = Game.getProvince(Game.getSeaProvinceInViewID(j)).getArmy(k).civID;
                                        nHA2.iProvinceID = Game.getSeaProvinceInViewID(j);
                                        nHA2.iArmyID = k;
                                        Game.addActiveArmy(nHA2);
                                        ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                        return;
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++j) {
                            if (Game.getProvince(Game.getWastelandProvinceInViewID(j)).getFogDrawArmy()) {
                                for (int k = 0; k < Game.getProvince(Game.getWastelandProvinceInViewID(j)).getArmySize(); ++k) {
                                    if (!Game.getProvince(Game.getWastelandProvinceInViewID(j)).getArmy(k).inBattle && Touch.isMouseOverArmy(nPosX, nPosY, Game.getWastelandProvinceInViewID(j), k)) {
                                        Game.clearActiveArmy();
                                        final Game.HoveredArmy nHA2 = new Game.HoveredArmy();
                                        nHA2.key = Game.getProvince(Game.getWastelandProvinceInViewID(j)).getArmy(k).key;
                                        nHA2.iCivID = Game.getProvince(Game.getWastelandProvinceInViewID(j)).getArmy(k).civID;
                                        nHA2.iProvinceID = Game.getWastelandProvinceInViewID(j);
                                        nHA2.iArmyID = k;
                                        Game.addActiveArmy(nHA2);
                                        ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                        return;
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < Game.NUM_OF_PROVINCES_IN_VIEW; ++j) {
                            if (Game.getProvince(Game.getProvinceInViewID(j)).isCapital && Game.getProvince(Game.getProvinceInViewID(j)).getCitiesSize() > 0 && Touch.isMouseOverCapitalProvince_Flag(nPosX, nPosY, Game.getProvinceInViewID(j))) {
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(Game.iHoveredCapitalProvinceID = Game.getProvinceInViewID(j));
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                                Game.animationHover.resetAnimationData();
                                if (Game.menuManager.getVisibleInGame_Civ()) {
                                    if (InGame_Civ.iActiveCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                        Game.menuManager.rebuildInGame_Civ();
                                        InGame_Civ.lTime = 0L;
                                        if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                            InGame_CivBonuses.iCivID = Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID();
                                            Game.menuManager.rebuildInGame_CivBonuses();
                                            Game.menuManager.setVisibleInGame_CivBonuses(true);
                                            InGame_CivBonuses.lTime = 0L;
                                        }
                                        Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                    }
                                    else {
                                        Game.menuManager.setVisibleInGame_Civ(false);
                                        Game.soundsManager.playSound(SoundsManager.SOUND_CLICK_TOP);
                                    }
                                }
                                else {
                                    Game.menuManager.rebuildInGame_Civ();
                                    if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                        InGame_CivBonuses.iCivID = Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID();
                                        Game.menuManager.rebuildInGame_CivBonuses();
                                        Game.menuManager.setVisibleInGame_CivBonuses(true);
                                        InGame_CivBonuses.lTime = 0L;
                                    }
                                    Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                }
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                return;
                            }
                        }
                        for (int j = 0; j < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++j) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(j)).isCapital && Game.getProvince(Game.getExtraProvinceInViewID(j)).getCitiesSize() > 0 && Touch.isMouseOverCapitalProvince_Flag(nPosX, nPosY, Game.getExtraProvinceInViewID(j))) {
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(Game.iHoveredCapitalProvinceID = Game.getExtraProvinceInViewID(j));
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                                Game.animationHover.resetAnimationData();
                                if (Game.menuManager.getVisibleInGame_Civ()) {
                                    if (InGame_Civ.iActiveCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                        Game.menuManager.rebuildInGame_Civ();
                                        InGame_Civ.lTime = 0L;
                                        if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                            InGame_CivBonuses.iCivID = Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID();
                                            Game.menuManager.rebuildInGame_CivBonuses();
                                            Game.menuManager.setVisibleInGame_CivBonuses(true);
                                            InGame_CivBonuses.lTime = 0L;
                                        }
                                        Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                    }
                                    else {
                                        Game.menuManager.setVisibleInGame_Civ(false);
                                        Game.soundsManager.playSound(SoundsManager.SOUND_CLICK_TOP);
                                    }
                                }
                                else {
                                    Game.menuManager.rebuildInGame_Civ();
                                    if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()) {
                                        InGame_CivBonuses.iCivID = Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID();
                                        Game.menuManager.rebuildInGame_CivBonuses();
                                        Game.menuManager.setVisibleInGame_CivBonuses(true);
                                        InGame_CivBonuses.lTime = 0L;
                                    }
                                    Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                }
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                return;
                            }
                        }
                    }
                }
            }
            if (button == Game.settingsManager.MAP_MOVE || (button == 1 && this.enableRightClick())) {
                if (Game.menuManager.getInGame()) {
                    if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE || Game.iHoveredProvinceID < 0 || (Game.iHoveredProvinceID >= 0 && Game.getProvince(Game.iHoveredProvinceID).getCivID() == 0)) {
                        Game.gameActiveProvince.actionUp_setActiveProvinceID(nPosX, nPosY, nPointer, button);
                    }
                    else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        if (Math.abs(nPosX - this.actionDownPosX) < CFG.PADDING / 2) {
                            if (Game.regroupArmyMode || Game.chooseProvinceMode) {
                                Game.gameActiveProvince.actionUp_setActiveProvinceID(nPosX, nPosY, nPointer, button);
                            }
                            else if (Game.invasionArmyMode) {
                                Game.gameActiveProvince.actionUp_setActiveProvinceID(nPosX, nPosY, nPointer, button);
                            }
                            else {
                                Game.setActiveProvinceID(Game.iHoveredProvinceID);
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                                if (Game.menuManager.getVisibleInGame_Civ()) {
                                    final boolean tEnabledByScaleOut = InGame_Civ.enabledByScaleOut;
                                    Game.menuManager.rebuildInGame_Civ();
                                    InGame_Civ.lTime = 0L;
                                    Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                    InGame_Civ.enabledByScaleOut = tEnabledByScaleOut;
                                }
                                else {
                                    Game.menuManager.rebuildInGame_Civ();
                                    InGame_Civ.enabledByScaleOut = true;
                                    Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                }
                                if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iActiveProvince).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iActiveProvince).getCivID()) {
                                    InGame_CivBonuses.iCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                                    Game.menuManager.rebuildInGame_CivBonuses();
                                    Game.menuManager.setVisibleInGame_CivBonuses(true);
                                    InGame_CivBonuses.lTime = 0L;
                                }
                            }
                        }
                    }
                    else {
                        Game.gameActiveProvince.actionUp_setActiveProvinceID(nPosX, nPosY, nPointer, button);
                    }
                }
                else {
                    Game.gameActiveProvince.actionUp_setActiveProvinceID(nPosX, nPosY, nPointer, button);
                }
            }
            if (!Game.mapScale.getScaleMode() && !Game.mapCoords.disableMovingMap) {
                Game.mapScroll.startScrollingTheMap();
            }
            try {
                ProvinceTouchExtraAction.actionUp_ExtraAction.extraAction(nPosX, nPosY, nPointer, button);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public boolean enableRightClick() {
        return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING;
    }
    
    public final void actionUpSelectMode(int nMaxX, int nMaxY) {
        try {
            if (this.iSelectBoxX == nMaxX || this.iSelectBoxY == nMaxY) {
                return;
            }
            if (this.iSelectBoxX > nMaxX) {
                final int tX = this.iSelectBoxX;
                this.iSelectBoxX = nMaxX;
                nMaxX = tX;
            }
            if (this.iSelectBoxY > nMaxY) {
                final int tY = this.iSelectBoxY;
                this.iSelectBoxY = nMaxY;
                nMaxY = tY;
            }
            if (Game.menuManager.getInGame()) {
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.canInvestInEconomy(Game.getProvinceInViewID(i))) {
                                Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(Game.getProvinceInViewID(i)).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(Game.getProvinceInViewID(i)), 10), Game_Calendar.IMG_ECONOMY);
                            }
                            else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.getProvinceInViewID(i))) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(Game.getProvinceInViewID(i)), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(Game.getProvinceInViewID(i)).addInvestInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(Game.getProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_Economy(Game.getProvinceInViewID(i));
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.canInvestInEconomy(Game.getExtraProvinceInViewID(i))) {
                                Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(Game.getExtraProvinceInViewID(i)).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(Game.getExtraProvinceInViewID(i)), 10), Game_Calendar.IMG_ECONOMY);
                            }
                            else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.getExtraProvinceInViewID(i))) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(Game.getExtraProvinceInViewID(i)), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).addInvestInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(Game.getExtraProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_Economy(Game.getExtraProvinceInViewID(i));
                            }
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PEACE_VIEW) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            ProvinceTouchExtraAction.actionPeaceView(Game.getProvinceInViewID(i), true);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            ProvinceTouchExtraAction.actionPeaceView(Game.getExtraProvinceInViewID(i), true);
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RELEASE_VASSAL) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            ProvinceTouchExtraAction.actionReleaseVassal(Game.getProvinceInViewID(i), true);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            ProvinceTouchExtraAction.actionReleaseVassal(Game.getExtraProvinceInViewID(i), true);
                        }
                    }
                    Game.menuManager.rebuildInGame_ReleaseAVassal_SavePos();
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            ProvinceTouchExtraAction.actionBuilding(Game.getProvinceInViewID(i));
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            ProvinceTouchExtraAction.actionBuilding(Game.getExtraProvinceInViewID(i));
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(Game.getProvinceInViewID(i))) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(Game.getProvinceInViewID(i)), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(Game.getProvinceInViewID(i)).addIncreaseManpowerInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCost(Game.getProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_Manpower(Game.getProvinceInViewID(i));
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(Game.getExtraProvinceInViewID(i))) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(Game.getExtraProvinceInViewID(i)), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).addIncreaseManpowerInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCost(Game.getExtraProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_Manpower(Game.getExtraProvinceInViewID(i));
                            }
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (!Game.getProvince(Game.getProvinceInViewID(i)).addIncreaseGrowthRateInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(Game.getProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_GrowthRate(Game.getProvinceInViewID(i));
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).addIncreaseGrowthRateInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(Game.getExtraProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_GrowthRate(Game.getExtraProvinceInViewID(i));
                            }
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY) && InGame_ProvinceInfo.addDevelopInfrastructureCost(Game.getProvinceInViewID(i))) {
                            ProvinceDraw.addProvinceDot_Infrastructure(Game.getProvinceInViewID(i));
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY) && InGame_ProvinceInfo.addDevelopInfrastructureCost(Game.getExtraProvinceInViewID(i))) {
                            ProvinceDraw.addProvinceDot_Infrastructure(Game.getExtraProvinceInViewID(i));
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(Game.getProvinceInViewID(i))) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(Game.getProvinceInViewID(i)), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(Game.getProvinceInViewID(i)).addIncreaseTaxEfficiencyInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(Game.getProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_TaxEfficiency(Game.getProvinceInViewID(i));
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(Game.getExtraProvinceInViewID(i))) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(Game.getExtraProvinceInViewID(i)), 100), Images.legacy);
                            }
                            else if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).addIncreaseTaxEfficiencyInProvince()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(Game.getExtraProvinceInViewID(i)), 100), Images.gold);
                            }
                            else {
                                ProvinceDraw.addProvinceDot_TaxEfficiency(Game.getExtraProvinceInViewID(i));
                            }
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getReligionID()) {
                                if (!Game.getProvince(Game.getProvinceInViewID(i)).addReligionConversion()) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(Game.getProvinceInViewID(i)), 100), Images.gold);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getReligionID()) {
                                if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).addReligionConversion()) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(Game.getExtraProvinceInViewID(i)), 100), Images.gold);
                                }
                            }
                        }
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY) && !Game.getProvince(Game.getProvinceInViewID(i)).haveACore(Game.player.iCivID)) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).coreCreation == null) {
                                if (!Game.getProvince(Game.getProvinceInViewID(i)).addCoreCreation()) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getCoreCreationCost(Game.getProvinceInViewID(i)), 100), Images.gold);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY) && !Game.getProvince(Game.getExtraProvinceInViewID(i)).haveACore(Game.player.iCivID)) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).coreCreation == null) {
                                if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).addCoreCreation()) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getCoreCreationCost(Game.getExtraProvinceInViewID(i)), 100), Images.gold);
                                }
                            }
                        }
                    }
                }
                else if (Game.invasionArmyMode) {
                    if (AA_KeyManager.CTRL_HOLD || AA_KeyManager.SHIFT_HOLD) {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                Game.invasionArmy_RemoveProvince(Game.getProvinceInViewID(i));
                            }
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                Game.invasionArmy_RemoveProvince(Game.getExtraProvinceInViewID(i));
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                Game.invasionArmy_AddProvince(Game.getProvinceInViewID(i));
                            }
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(i), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                Game.invasionArmy_AddProvince(Game.getExtraProvinceInViewID(i));
                            }
                        }
                    }
                }
                else {
                    if (nMaxX - this.iSelectBoxX > CFG.BUTTON_HEIGHT2) {
                        Game.menuManager.hideInGameMenus();
                    }
                    if (!AA_KeyManager.SHIFT_HOLD) {
                        Game.clearActiveArmy();
                    }
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getFogDrawArmy()) {
                            for (int j = 0; j < Game.getProvince(Game.getProvinceInViewID(i)).getArmySize(); ++j) {
                                if (Game.getProvince(Game.getProvinceInViewID(i)).getArmy(j).civID == Game.player.iCivID) {
                                    if (actionUpSelectModeIsInBox(Game.getProvinceInViewID(i), j, this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                        final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                        nHA.key = Game.getProvince(Game.getProvinceInViewID(i)).getArmy(j).key;
                                        nHA.iCivID = Game.getProvince(Game.getProvinceInViewID(i)).getArmy(j).civID;
                                        nHA.iProvinceID = Game.getProvinceInViewID(i);
                                        nHA.iArmyID = j;
                                        Game.addActiveArmy(nHA);
                                        Game.animationHover.resetAnimationData();
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getFogDrawArmy()) {
                            for (int j = 0; j < Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmySize(); ++j) {
                                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmy(j).civID == Game.player.iCivID) {
                                    if (actionUpSelectModeIsInBox(Game.getExtraProvinceInViewID(i), j, this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                        final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                        nHA.key = Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmy(j).key;
                                        nHA.iCivID = Game.getProvince(Game.getExtraProvinceInViewID(i)).getArmy(j).civID;
                                        nHA.iProvinceID = Game.getExtraProvinceInViewID(i);
                                        nHA.iArmyID = j;
                                        Game.addActiveArmy(nHA);
                                        Game.animationHover.resetAnimationData();
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getSeaProvinceInViewID(i)).getFogDrawArmy()) {
                            for (int j = 0; j < Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmySize(); ++j) {
                                if (Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmy(j).civID == Game.player.iCivID) {
                                    if (actionUpSelectModeIsInBox(Game.getSeaProvinceInViewID(i), j, this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                        final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                        nHA.key = Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmy(j).key;
                                        nHA.iCivID = Game.getProvince(Game.getSeaProvinceInViewID(i)).getArmy(j).civID;
                                        nHA.iProvinceID = Game.getSeaProvinceInViewID(i);
                                        nHA.iArmyID = j;
                                        Game.addActiveArmy(nHA);
                                        Game.animationHover.resetAnimationData();
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getWastelandProvinceInViewID(i)).getFogDrawArmy()) {
                            for (int j = 0; j < Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmySize(); ++j) {
                                if (Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmy(j).civID == Game.player.iCivID) {
                                    if (actionUpSelectModeIsInBox(Game.getWastelandProvinceInViewID(i), j, this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                                        final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                        nHA.key = Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmy(j).key;
                                        nHA.iCivID = Game.getProvince(Game.getWastelandProvinceInViewID(i)).getArmy(j).civID;
                                        nHA.iProvinceID = Game.getWastelandProvinceInViewID(i);
                                        nHA.iArmyID = j;
                                        Game.addActiveArmy(nHA);
                                        Game.animationHover.resetAnimationData();
                                    }
                                }
                            }
                        }
                    }
                    ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                    if (Game.activeArmySize > 0) {
                        Game.gameActiveProvince.resetLastActiveProvince();
                        Game.setActiveProvinceID(-1);
                    }
                }
            }
            else if (Game.menuManager.getInScenarioAssign()) {
                final int tActive = Game.iActiveProvince;
                for (int k = 0; k < Game.NUM_OF_PROVINCES_IN_VIEW; ++k) {
                    if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(k), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                        Game.iActiveProvince = Game.getProvinceInViewID(k);
                        ScenarioAssign.actionUpdateData(true);
                    }
                }
                for (int k = 0; k < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++k) {
                    if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(k), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                        Game.iActiveProvince = Game.getExtraProvinceInViewID(k);
                        ScenarioAssign.actionUpdateData(true);
                    }
                }
                Game.iActiveProvince = tActive;
            }
            else if (Game.menuManager.getInScenarioWasteland()) {
                final int tActive = Game.iActiveProvince;
                for (int k = 0; k < Game.NUM_OF_PROVINCES_IN_VIEW; ++k) {
                    if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(k), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                        Game.iActiveProvince = Game.getProvinceInViewID(k);
                        ScenarioWasteland.actionUpdateData(true);
                    }
                }
                for (int k = 0; k < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++k) {
                    if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(k), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                        Game.iActiveProvince = Game.getExtraProvinceInViewID(k);
                        ScenarioWasteland.actionUpdateData(true);
                    }
                }
                Game.iActiveProvince = tActive;
            }
            else if (Game.menuManager.getInMapEditorContinents()) {
                final int tActive = Game.iActiveProvince;
                for (int k = 0; k < Game.NUM_OF_PROVINCES_IN_VIEW; ++k) {
                    if (actionUpSelectModeIsInBox_Center(Game.getProvinceInViewID(k), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                        Game.getProvince(Game.getProvinceInViewID(k)).setContinent(EditorMapContinents.currentContinentID);
                    }
                }
                for (int k = 0; k < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++k) {
                    if (actionUpSelectModeIsInBox_Center(Game.getExtraProvinceInViewID(k), this.iSelectBoxX, this.iSelectBoxY, nMaxX, nMaxY)) {
                        Game.getProvince(Game.getExtraProvinceInViewID(k)).setContinent(EditorMapContinents.currentContinentID);
                    }
                }
                Game.iActiveProvince = tActive;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean actionUpSelectModeIsInBox(final int nProvinceID, final int nArmyID, final int nMinX, final int nMinY, final int nMaxX, final int nMaxY) {
        try {
            final int nX = ProvinceDrawArmy.getArmyPosX(nProvinceID, nArmyID);
            final int nY = ProvinceDrawArmy.getArmyPosY(nProvinceID, nArmyID);
            final int nWidth = ProvinceDrawArmy.getArmyWidth(Game.getProvince(nProvinceID).getArmy(nArmyID).iArmyWidth);
            final int nHeight = ProvinceDrawArmy.getArmyHeight();
            if (((nX < nMaxX && nX > nMinX) || (nX + nWidth < nMaxX && nX + nWidth > nMinX)) && ((nY > nMinY && nY < nMaxY) || (nY + nHeight > nMinY && nY + nHeight < nMaxY))) {
                return true;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public static final boolean actionUpSelectModeIsInBox_Center(final int nProvinceID, final int nMinX, final int nMinY, final int nMaxX, final int nMaxY) {
        try {
            final int nX = ProvinceDrawArmy.getDetailsPosX_2(nProvinceID);
            final int nY = ProvinceDrawArmy.getDetailsPosY_2(nProvinceID);
            final int nWidth = 1;
            final int nHeight = 1;
            if (((nX < nMaxX && nX > nMinX) || (nX + nWidth < nMaxX && nX + nWidth > nMinX)) && ((nY > nMinY && nY < nMaxY) || (nY + nHeight > nMinY && nY + nHeight < nMaxY))) {
                return true;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final void Scroll(final int amount) {
        Game.mapScale.scrollScale(amount);
    }
    
    public final void setActionDownPosXY(final int actionDownPosX, final int actionDownPosY) {
        try {
            this.updateStartMovePosXY(actionDownPosX, actionDownPosY);
            this.actionDownPosX = actionDownPosX;
            this.actionDownPosY = actionDownPosY;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    protected final void updateStartMovePosXY(final int nPosX, final int nPosY) {
        this.iStartMovePosX = this.mapMoveDirectionX.getStartMovePos((int)(nPosX / Game.mapScale.getCurrentScale()));
        this.iStartMovePosY = this.mapMoveDirectionY.getStartMovePos((int)(nPosY / Game.mapScale.getCurrentScale()));
    }
    
    public final void buildReversePos() {
        this.buildReversePosX();
        this.buildReversePosY();
        this.buildReversePosX2();
        this.buildReversePosY2();
    }
    
    protected final void buildReversePosX() {
        if (MapTouchManager.reverseDirectionX) {
            this.mapMoveDirectionX = new ReverseDirection() {
                @Override
                public int getStartMovePos(final int nPos) {
                    return Game.mapCoords.getPosX() - nPos;
                }
            };
        }
        else {
            this.mapMoveDirectionX = new ReverseDirection() {
                @Override
                public int getStartMovePos(final int nPos) {
                    return Game.mapCoords.getPosX() + nPos;
                }
            };
        }
    }
    
    protected final void buildReversePosY() {
        if (MapTouchManager.reverseDirectionY) {
            this.mapMoveDirectionY = new ReverseDirection() {
                @Override
                public int getStartMovePos(final int nPos) {
                    return Game.mapCoords.getPosY() - nPos;
                }
            };
        }
        else {
            this.mapMoveDirectionY = new ReverseDirection() {
                @Override
                public int getStartMovePos(final int nPos) {
                    return Game.mapCoords.getPosY() + nPos;
                }
            };
        }
    }
    
    protected final void buildReversePosX2() {
        if (MapTouchManager.reverseDirectionX) {
            this.mapMoveDirectionX2 = new ReverseDirection2() {
                @Override
                public int getNewPos(final int iStartMovePos, final int nPos) {
                    return iStartMovePos + nPos;
                }
            };
        }
        else {
            this.mapMoveDirectionX2 = new ReverseDirection2() {
                @Override
                public int getNewPos(final int iStartMovePos, final int nPos) {
                    return iStartMovePos - nPos;
                }
            };
        }
    }
    
    protected final void buildReversePosY2() {
        if (MapTouchManager.reverseDirectionY) {
            this.mapMoveDirectionY2 = new ReverseDirection2() {
                @Override
                public int getNewPos(final int iStartMovePos, final int nPos) {
                    return iStartMovePos + nPos;
                }
            };
        }
        else {
            this.mapMoveDirectionY2 = new ReverseDirection2() {
                @Override
                public int getNewPos(final int iStartMovePos, final int nPos) {
                    return iStartMovePos - nPos;
                }
            };
        }
    }
    
    public final long getActionDownTime() {
        return this.lActionDownTime;
    }
    
    public final void setActionDownTime(final long lActionDownTime) {
        this.lActionDownTime = lActionDownTime;
    }
    
    public final void setUpdateStartMovePosX(final boolean updateStartMovePosX) {
        this.updateStartMovePosX = updateStartMovePosX;
    }
    
    public final void setUpdateStartMovePosY(final boolean updateStartMovePosY) {
        this.updateStartMovePosY = updateStartMovePosY;
    }
    
    static {
        MapTouchManager.reverseDirectionX = true;
        MapTouchManager.reverseDirectionY = true;
    }
    
    public interface ReverseDirection2
    {
        int getNewPos(final int p0, final int p1);
    }
    
    public interface ReverseDirection
    {
        int getStartMovePos(final int p0);
    }
}
