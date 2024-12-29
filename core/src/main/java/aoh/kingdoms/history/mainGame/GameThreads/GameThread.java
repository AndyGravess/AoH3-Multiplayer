// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.GameThreads;

import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.jakowski.AI.Technology.AI_SelectTechnology;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.jakowski.AI.Laws.AI_Laws;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.Iterator;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_BattleEnd;
import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import java.util.List;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopDate;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.jakowski.Stats.Stats;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.map.diplomacy.LibertyDesireManager;
import aoc.kingdoms.lukasz.map.CoalitionManager;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.concurrent.ConcurrentLinkedDeque;

public class GameThread extends Thread
{
    public boolean running;
    public ConcurrentLinkedDeque<Integer> civsUpdateLegacyPerMonth;
    public ConcurrentLinkedDeque<Integer> civsUpdateResearchPerMonth;
    public ConcurrentLinkedDeque<Integer> civsUpdateLoans;
    public ConcurrentLinkedDeque<Integer> civsUpdateArmyMaintenance;
    public ConcurrentLinkedDeque<Integer> civsUpdateTotalIncomePerMonth;
    public ConcurrentLinkedDeque<Game.SimpleTask> aiTask;
    public boolean play;
    public int playSpeed;
    public int playMaxSpeed;
    public int playSpeedTIME;
    public static long calculationsTime;
    
    public GameThread() {
        this.running = true;
        this.civsUpdateLegacyPerMonth = new ConcurrentLinkedDeque<Integer>();
        this.civsUpdateResearchPerMonth = new ConcurrentLinkedDeque<Integer>();
        this.civsUpdateLoans = new ConcurrentLinkedDeque<Integer>();
        this.civsUpdateArmyMaintenance = new ConcurrentLinkedDeque<Integer>();
        this.civsUpdateTotalIncomePerMonth = new ConcurrentLinkedDeque<Integer>();
        this.aiTask = new ConcurrentLinkedDeque<Game.SimpleTask>();
        this.play = false;
        this.playSpeed = 3;
        this.playMaxSpeed = 4;
        this.playSpeedTIME = 100;
    }
    
    public final void updateAI_SimpleTask() {
        try {
            for (int i = this.aiTask.size() - 1; i >= 0; --i) {
                try {
                    this.aiTask.remove().update();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final void addAI_SimpleTask(final Game.SimpleTask nSimpleTask) {
        if (this.aiTask.contains(nSimpleTask)) {
            return;
        }
        this.aiTask.add(nSimpleTask);
    }
    
    public void clearData() {
        this.civsUpdateLegacyPerMonth.clear();
        this.civsUpdateResearchPerMonth.clear();
        this.civsUpdateLoans.clear();
        this.civsUpdateArmyMaintenance.clear();
        this.civsUpdateTotalIncomePerMonth.clear();
        this.aiTask.clear();
    }
    
    public final int getPlaySpeed() {
        switch (this.playSpeed) {
            case 1: {
                this.playSpeedTIME = GameValues.gameSpeed.GAME_SPEED_1;
                break;
            }
            case 2: {
                this.playSpeedTIME = GameValues.gameSpeed.GAME_SPEED_2;
                break;
            }
            case 3: {
                this.playSpeedTIME = GameValues.gameSpeed.GAME_SPEED_3;
                break;
            }
            case 4: {
                this.playSpeedTIME = GameValues.gameSpeed.GAME_SPEED_4;
                break;
            }
            default: {
                this.playSpeedTIME = (Game.SPECTATOR_MODE ? GameValues.gameSpeed.GAME_SPEED_SPECTATOR_MODE : GameValues.gameSpeed.GAME_SPEED_5);
                break;
            }
        }
        return this.playSpeedTIME;
    }
    
    public final void updateNewMonth() {
        if (Game_Calendar.TURN_ID % 31 == 0) {
            try {
                WarManager.updateWars_TickingWarScore();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            Game.gameThreadUpdate.addSimpleTask(new Game.SimpleTask("buildCivilizationRanking") {
                @Override
                public void update() {
                    try {
                        CivilizationRanking.buildCivilizationRanking();
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            });
            Game.player.playerStats2.updateData();
            Game.player.playerStats3.updateData();
        }
    }
    
    public final void updateNewYear() {
        if (Game_Calendar.TURN_ID % 365 == 0) {
            Game.gameThreadUpdate.addSimpleTask(new Game.SimpleTask("buildProsperity_AverageEconomy") {
                @Override
                public void update() {
                    Game.buildProsperity_AverageEconomy();
                }
            });
            Game.gameThreadUpdate.addSimpleTask(new Game.SimpleTask("updateWorldResourcesProduced_NewYear") {
                @Override
                public void update() {
                    try {
                        ResourcesManager.updateWorldResourcesProduced_NewYear();
                        ResourcesManager.updateUniqueCivsGoods();
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            });
        }
    }
    
    public void checkGameOver() {
        if (!Game.SPECTATOR_MODE && Game.getCiv(Game.player.iCivID).getNumOfProvinces() <= 0) {
            Game.menuManager.setViewIDWithoutAnimation(View.GAME_LOST);
        }
    }
    
    public void updateMinimap() {
        if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_MINIMAP == 0) {
            Game.mapBG.requestToDisposeMinimap = true;
        }
    }
    
    public final void autoSaveData() {
        if (Game_Calendar.TURN_ID % GameValues.value.AUTO_SAVE_STATS_DAYS == 2) {
            Game.addSimpleTask(new Game.SimpleTask("saveStats") {
                @Override
                public void update() {
                    Game.stats.saveStats();
                }
            });
        }
    }
    
    @Override
    public void run() {
        while (this.running) {
            try {
                GameThread.calculationsTime = System.currentTimeMillis();
                if (Game.menuManager.getInGame() && !Game.menuManager.getVisibleInGame_Escape()) {
                    try {
                        if (Game_Calendar.getDay() % 30 == 0 && DesktopLauncher.host && Game.gameThread.play && Game_Calendar.HOUR == 0) {
                            DesktopLauncher.SMS("Data: Year = " + Game.gameAges.getYear(Game_Calendar.getYear()));
                            DesktopLauncher.SMS("Data: Day = " + Game_Calendar.getDay());
                            DesktopLauncher.SMS("Data: Month = " + Game_Calendar.getMonth());
                            DesktopLauncher.SMS("Data: Hours = " + Game_Calendar.HOUR);
                            final ArrayList<String> list = new ArrayList<String>();
                            for (int i = 1; i < Game.getCivsSize(); ++i) {
                                list.add(i + " " + (int)Game.getCiv(i).fGold);
                            }
                            final String listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
                            DesktopLauncher.SMS("Money: " + listString);
                        }
                        if (this.play && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_PEACE_VIEW) {
                            Game_Calendar.HOUR += Game.HOURS_PER_TURN;
                            if (Game_Calendar.HOUR >= 24) {
                                Game_Calendar.HOUR %= 24;
                                ++Game_Calendar.TURN_ID;
                                final Stats civStats = Game.stats.civStats;
                                ++civStats.tr;
                                this.autoSaveData();
                                Game_Calendar.nextDays(Game.gameAges.getAge_TurnDays(Game_Calendar.CURRENT_AGEID));
                                this.updateMinimap();
                                if (DesktopLauncher.host) {
                                    this.updateNewMonth();
                                    this.updateNewYear();
                                    DesktopLauncher.SMS("DATE!");
                                }
                                this.updateAutoAssimilation();
                                this.updatePopulation();
                                ResourcesManager.updatePriceChanges();
                                this.updateLoans();
                                this.updateResearchPerMonth();
                                this.updateLegacyPerMonth();
                                this.updateArmyMaintenance();
                                this.updateTotalIncomePerMonth();
                                this.updateGold_Manpower_Legacy_Diplomacy();
                                this.updateResearch();
                                CoalitionManager.updateCreateCoalition();
                                Game.aiManager.updateAll();
                                Game.revolutionManager.update();
                                this.updateAggressiveExpansion();
                                this.updateWarWeariness();
                                this.updateWars_Peace();
                                this.updateWars_WhitePeace();
                                LibertyDesireManager.updateLibertyDesire();
                                this.updateCivilizationBonuses();
                                this.updateCivs_Laws();
                                updateRelations();
                                this.returnOccupiedProvinces_NotAtWar();
                                SaveGameManager.autosave();
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                try {
                    Thread.sleep(Math.max(GameValues.gameSpeed.MIN_THREAD_SLEEP, this.getPlaySpeed() - (System.currentTimeMillis() - GameThread.calculationsTime)));
                }
                catch (final InterruptedException e) {
                    CFG.exceptionStack(e);
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public final void updateMoveUnits_Client() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                try {
                    for (int j = 0; j < Game.getCiv(i).getMoveUnitsSize(); ++j) {
                        try {
                            final MoveUnits moveUnits = Game.getCiv(i).getMoveUnits(j);
                            if (!moveUnits.inBattle) {
                                moveUnits.fCurrentMovingPercentage = 0.0f;
                                moveUnits.doneMovementProgressWidth = moveUnits.currentMovementProgressWidth;
                                final MoveUnits moveUnits2 = moveUnits;
                                moveUnits2.currentMovementProgressWidth += moveUnits.fSpeed * (Game.terrainManager.terrains.get(Game.getProvince(moveUnits.getToProvinceID()).getTerrainID()).MovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).provBonuses.ArmyMovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_ARMY_MOVEMENT_PER_LVL);
                                if (moveUnits.currentMovementProgressWidth > moveUnits.iWidth.get(0)) {
                                    moveUnits.movementProgressOverWidth = moveUnits.currentMovementProgressWidth - moveUnits.iWidth.get(0);
                                    moveUnits.currentMovementProgressWidth = moveUnits.iWidth.get(0);
                                }
                                moveUnits.updateLittleAnimationMovingArmy();
                                if (moveUnits.currentMovementProgressWidth >= moveUnits.iWidth.get(0)) {
                                    final ArmyDivision armyDivision = Game.getProvince(moveUnits.getFromProvinceID()).getArmy(moveUnits.key);
                                    if (armyDivision != null) {
                                        armyDivision.iShiftX_Scaled = 0;
                                        armyDivision.iShiftY_Scaled = 0;
                                        armyDivision.provinceID = moveUnits.getToProvinceID();
                                        armyDivision.setInMovement(moveUnits.iRouteSize > 2);
                                        Game.getProvince(moveUnits.getFromProvinceID()).removeArmy(moveUnits.key);
                                        Game.getProvince(moveUnits.getToProvinceID()).addArmy(armyDivision);
                                        Game.updateActiveArmy_MoveUnits(moveUnits.key, moveUnits.getFromProvinceID(), moveUnits.getToProvinceID());
                                        if (Game.getCiv(i).isPlayerAlly) {
                                            Game.player.fog.updateFogOfWar_All(moveUnits.getFromProvinceID());
                                            Game.player.fog.updateFogOfWar_All(moveUnits.getToProvinceID());
                                        }
                                        if (moveUnits.iRouteSize <= 2) {
                                            armyDivision.setInMovement(false);
                                            final int inProvinceID = moveUnits.getToProvinceID();
                                            if (i == Game.player.iCivID) {
                                                Game.getCiv(i).removeMove(j);
                                                --j;
                                                try {
                                                    if (Game.getProvince(armyDivision.provinceID).isOccupied()) {
                                                        Game.getProvince(armyDivision.provinceID).invasionMoveArmies();
                                                    }
                                                }
                                                catch (final Exception ex) {
                                                    CFG.exceptionStack(ex);
                                                }
                                            }
                                            else {
                                                Game.getCiv(i).aiMerge.checkMerge(moveUnits.getToProvinceID(), moveUnits.key);
                                                Game.getCiv(i).removeMove(j);
                                                --j;
                                            }
                                            SiegeManager.checkForSiege(inProvinceID);
                                        }
                                        else {
                                            moveUnits.updateToNextProvince(i);
                                            moveUnits.doneMovementProgressWidth = 0.0f;
                                            moveUnits.currentMovementProgressWidth = Math.max(0.0f, moveUnits.fSpeed * (Game.terrainManager.terrains.get(Game.getProvince(moveUnits.getToProvinceID()).getTerrainID()).MovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).provBonuses.ArmyMovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_ARMY_MOVEMENT_PER_LVL) + moveUnits.movementProgressOverWidth);
                                            moveUnits.movementProgressOverWidth = 0.0f;
                                            try {
                                                if (moveUnits.currentMovementProgressWidth > moveUnits.iWidth.get(0)) {
                                                    moveUnits.movementProgressOverWidth = Math.max(0.0f, moveUnits.currentMovementProgressWidth - moveUnits.iWidth.get(0));
                                                    moveUnits.currentMovementProgressWidth = moveUnits.iWidth.get(0);
                                                }
                                            }
                                            catch (final Exception exr) {
                                                CFG.exceptionStack(exr);
                                            }
                                        }
                                    }
                                    else {
                                        Game.getCiv(i).aiMerge.checkMerge(moveUnits.getToProvinceID(), moveUnits.key);
                                        Game.getCiv(i).removeMove(j);
                                        --j;
                                    }
                                }
                            }
                        }
                        catch (final Exception ex2) {
                            CFG.exceptionStack(ex2);
                        }
                    }
                }
                catch (final Exception ex3) {
                    CFG.exceptionStack(ex3);
                }
            }
        }
        catch (final Exception ex4) {
            CFG.exceptionStack(ex4);
        }
    }
    
    public final void updateRecruitArmy() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                for (int j = Game.getCiv(i).getArmyRecruitSize() - 1; j >= 0; --j) {
                    Game.getCiv(i).updateRecruitArmy(j, 0);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateRecruitArmy_Client() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                for (int j = Game.getCiv(i).getArmyRecruitSize() - 1; j >= 0; --j) {
                    Game.getCiv(i).updateRecruitArmy(j, 0);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final void updateMoveUnits() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                try {
                    for (int j = 0; j < Game.getCiv(i).getMoveUnitsSize(); ++j) {
                        try {
                            final MoveUnits moveUnits = Game.getCiv(i).getMoveUnits(j);
                            if (!moveUnits.inBattle) {
                                moveUnits.fCurrentMovingPercentage = 0.0f;
                                moveUnits.doneMovementProgressWidth = moveUnits.currentMovementProgressWidth;
                                final MoveUnits moveUnits2 = moveUnits;
                                moveUnits2.currentMovementProgressWidth += moveUnits.fSpeed * (Game.terrainManager.terrains.get(Game.getProvince(moveUnits.getToProvinceID()).getTerrainID()).MovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).provBonuses.ArmyMovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_ARMY_MOVEMENT_PER_LVL);
                                if (moveUnits.currentMovementProgressWidth > moveUnits.iWidth.get(0)) {
                                    moveUnits.movementProgressOverWidth = moveUnits.currentMovementProgressWidth - moveUnits.iWidth.get(0);
                                    moveUnits.currentMovementProgressWidth = moveUnits.iWidth.get(0);
                                }
                                moveUnits.updateLittleAnimationMovingArmy();
                                if (moveUnits.currentMovementProgressWidth >= moveUnits.iWidth.get(0)) {
                                    final ArmyDivision armyDivision = Game.getProvince(moveUnits.getFromProvinceID()).getArmy(moveUnits.key);
                                    if (armyDivision != null) {
                                        armyDivision.iShiftX_Scaled = 0;
                                        armyDivision.iShiftY_Scaled = 0;
                                        armyDivision.provinceID = moveUnits.getToProvinceID();
                                        armyDivision.setInMovement(moveUnits.iRouteSize > 2);
                                        Game.getProvince(moveUnits.getFromProvinceID()).removeArmy(moveUnits.key);
                                        Game.getProvince(moveUnits.getToProvinceID()).addArmy(armyDivision);
                                        Game.updateActiveArmy_MoveUnits(moveUnits.key, moveUnits.getFromProvinceID(), moveUnits.getToProvinceID());
                                        if (Game.getCiv(i).isPlayerAlly) {
                                            Game.player.fog.updateFogOfWar_All(moveUnits.getFromProvinceID());
                                            Game.player.fog.updateFogOfWar_All(moveUnits.getToProvinceID());
                                        }
                                        if (moveUnits.iRouteSize <= 2) {
                                            armyDivision.setInMovement(false);
                                            final int inProvinceID = moveUnits.getToProvinceID();
                                            if (i == Game.player.iCivID) {
                                                Game.getCiv(i).removeMove(j);
                                                --j;
                                                try {
                                                    if (Game.getProvince(armyDivision.provinceID).isOccupied()) {
                                                        Game.getProvince(armyDivision.provinceID).invasionMoveArmies();
                                                    }
                                                }
                                                catch (final Exception ex) {
                                                    CFG.exceptionStack(ex);
                                                }
                                            }
                                            else {
                                                Game.getCiv(i).aiMerge.checkMerge(moveUnits.getToProvinceID(), moveUnits.key);
                                                Game.getCiv(i).removeMove(j);
                                                --j;
                                            }
                                            SiegeManager.checkForSiege(inProvinceID);
                                        }
                                        else {
                                            moveUnits.updateToNextProvince(i);
                                            moveUnits.doneMovementProgressWidth = 0.0f;
                                            moveUnits.currentMovementProgressWidth = Math.max(0.0f, moveUnits.fSpeed * (Game.terrainManager.terrains.get(Game.getProvince(moveUnits.getToProvinceID()).getTerrainID()).MovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).provBonuses.ArmyMovementSpeed + Game.getProvince(moveUnits.getFromProvinceID()).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_ARMY_MOVEMENT_PER_LVL) + moveUnits.movementProgressOverWidth);
                                            moveUnits.movementProgressOverWidth = 0.0f;
                                            try {
                                                if (moveUnits.currentMovementProgressWidth > moveUnits.iWidth.get(0)) {
                                                    moveUnits.movementProgressOverWidth = Math.max(0.0f, moveUnits.currentMovementProgressWidth - moveUnits.iWidth.get(0));
                                                    moveUnits.currentMovementProgressWidth = moveUnits.iWidth.get(0);
                                                }
                                            }
                                            catch (final Exception exr) {
                                                CFG.exceptionStack(exr);
                                            }
                                        }
                                    }
                                    else {
                                        Game.getCiv(i).aiMerge.checkMerge(moveUnits.getToProvinceID(), moveUnits.key);
                                        Game.getCiv(i).removeMove(j);
                                        --j;
                                    }
                                }
                            }
                        }
                        catch (final Exception ex2) {
                            CFG.exceptionStack(ex2);
                        }
                    }
                }
                catch (final Exception ex3) {
                    CFG.exceptionStack(ex3);
                }
            }
        }
        catch (final Exception ex4) {
            CFG.exceptionStack(ex4);
        }
    }
    
    private final void updateMoveUnits_Rebels() {
    }
    
    public final void updateSpeed(final int speed) {
        if (DesktopLauncher.host) {
            Game.gameThread.playSpeed = Math.max(1, Math.min(Game.gameThread.playMaxSpeed, speed));
            ButtonTopDate.ANIMATION_TIME = CFG.currentTimeMillis;
            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Speed") + ": ", CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.gameThread.playSpeed + "/" + Game.gameThread.playMaxSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            Game.menuManager.addToast(new Toast(nElements, 0, 1000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
            DesktopLauncher.SMS("Time: Speed = " + Game.gameThread.playSpeed);
        }
        else {
            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Text("Only the host can adjust the game speed!", CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            Game.menuManager.addToast(new Toast(nElements, 0, 10000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
        }
    }
    
    public final void updateSpeedClient(final int speed) {
        Game.gameThread.playSpeed = Math.max(1, Math.min(Game.gameThread.playMaxSpeed, speed));
        ButtonTopDate.ANIMATION_TIME = CFG.currentTimeMillis;
        final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Speed") + ": ", CFG.FONT_BOLD_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.gameThread.playSpeed + "/" + Game.gameThread.playMaxSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        Game.menuManager.addToast(new Toast(nElements, 0, 1000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
    }
    
    public final void updateSpeedMinus() {
        if (DesktopLauncher.host) {
            final GameThread gameThread = Game.gameThread;
            --gameThread.playSpeed;
            if (Game.gameThread.playSpeed < 1) {
                Game.gameThread.playSpeed = 1;
            }
            ButtonTopDate.ANIMATION_TIME = CFG.currentTimeMillis;
            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Speed") + ": ", CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.gameThread.playSpeed + "/" + Game.gameThread.playMaxSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            Game.menuManager.addToast(new Toast(nElements, 0, 1000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
            DesktopLauncher.SMS("Time: Speed = " + Game.gameThread.playSpeed);
        }
        else {
            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Text("Only the host can adjust the game speed!", CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            Game.menuManager.addToast(new Toast(nElements, 0, 10000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
        }
    }
    
    public final void updateSpeedPlus() {
        if (DesktopLauncher.host) {
            final GameThread gameThread = Game.gameThread;
            ++gameThread.playSpeed;
            if (Game.gameThread.playSpeed > Game.gameThread.playMaxSpeed) {
                Game.gameThread.playSpeed = Game.gameThread.playMaxSpeed;
            }
            ButtonTopDate.ANIMATION_TIME = CFG.currentTimeMillis;
            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Speed") + ": ", CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.gameThread.playSpeed + "/" + Game.gameThread.playMaxSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            Game.menuManager.addToast(new Toast(nElements, 0, 1000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
            DesktopLauncher.SMS("Time: Speed = " + Game.gameThread.playSpeed);
        }
        else {
            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_Text("Only the host can adjust the game speed!", CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            Game.menuManager.addToast(new Toast(nElements, 0, 10000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
        }
    }
    
    public final void updateSieges() {
        try {
            SiegeManager.updateSieges();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateBattles() {
        for (int i = Game.battleManager.getBattleSize() - 1; i >= 0; --i) {
            try {
                Game.battleManager.getBattle(i).updateBattle();
                if (Game.battleManager.getBattle(i).endOfBattle()) {
                    Game.battleManager.getBattle(i).updateBattle_Summary(true);
                    try {
                        Game.getProvince(Game.battleManager.getBattle(i).provinceID).updateIsUnderSiege();
                        SiegeManager.checkForSiege(Game.battleManager.getBattle(i).provinceID);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    final int inProvinceID = Game.battleManager.getBattle(i).provinceID;
                    Game.battleManager.removeBattle(i);
                    AI_BattleEnd.battleEnded(inProvinceID);
                }
                else if (Game.battleManager.getBattle(i).endOfBattle_NoAttacks()) {
                    Game.battleManager.getBattle(i).updateBattle_Summary(true);
                    try {
                        Game.getProvince(Game.battleManager.getBattle(i).provinceID).updateIsUnderSiege();
                        SiegeManager.checkForSiege(Game.battleManager.getBattle(i).provinceID);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    Game.battleManager.removeBattle(i);
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public final void updateWars_WhitePeace() {
        try {
            if (Game_Calendar.TURN_ID % GameValues.war.GAME_UPDATE_WAR_AUTO_WHITE_PEACE == 0) {
                WarManager.updateWars_WhitePeace();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateWars_Peace() {
        try {
            if (Game_Calendar.TURN_ID % GameValues.war.GAME_UPDATE_WAR_AI_PEACE == 0) {
                WarManager.updateWars_Peace();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addCivUpdateTotalIncomePerMonth(final int iCivID) {
        try {
            if (this.civsUpdateTotalIncomePerMonth.contains(iCivID)) {
                return;
            }
        }
        catch (final Exception ex) {
            CFG.LOG("addCivUpdateTotalIncomePerMonth: " + iCivID);
            CFG.exceptionStack(ex);
        }
        if (iCivID < 1) {
            return;
        }
        try {
            this.civsUpdateTotalIncomePerMonth.add(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateTotalIncomePerMonth() {
        try {
            for (int i = this.civsUpdateTotalIncomePerMonth.size() - 1; i >= 0; --i) {
                final int nCivID = this.civsUpdateTotalIncomePerMonth.remove();
                Game.getCiv(nCivID).updateProvincesIncomeAndExpenses();
                Game.getCiv(nCivID).updateTotalIncomePerMonth();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addCivUpdateArmyMaintenance(final int iCivID) {
        try {
            if (this.civsUpdateArmyMaintenance.contains(iCivID)) {
                return;
            }
            this.civsUpdateArmyMaintenance.add(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateArmyMaintenance() {
        try {
            for (int i = this.civsUpdateArmyMaintenance.size() - 1; i >= 0; --i) {
                final int nCivID = this.civsUpdateArmyMaintenance.remove();
                Game.getCiv(nCivID).updateArmyMaintenance();
                Game.getCiv(nCivID).updateArmyRegimentSize();
                this.addCivUpdateTotalIncomePerMonth(nCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addCivUpdateLoans(final int iCivID) {
        try {
            if (this.civsUpdateLoans.contains(iCivID)) {
                return;
            }
            this.civsUpdateLoans.add(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeCivUpdateLoans(final int iCivID) {
        try {
            this.civsUpdateLoans.remove(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateLoans() {
        try {
            for (final Integer civID : this.civsUpdateLoans) {
                Game.getCiv(civID).updateLoans();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateGold() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                try {
                    if (Game.getCiv(i).getNumOfProvinces() > 0) {
                        Game.getCiv(i).addGold((Game.getCiv(i).fTotalIncomePerMonth + Game.getCiv(i).civBonuses.MonthlyIncome - Game.getCiv(i).fTotalExpensesPerMonth) / Game_Calendar.UPDATE_NUM_OF_DAYS);
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
    }
    
    public final void updateGold_2() {
        try {
            if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                Game.getCiv(Game.player.iCivID).addGold((Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth) / Game_Calendar.UPDATE_NUM_OF_DAYS);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % Game_Calendar.UPDATE_NUM_OF_DAYS_INT; i < Game.player.iCivID; i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).addGold(Game.getCiv(i).fTotalIncomePerMonth + Game.getCiv(i).civBonuses.MonthlyIncome - Game.getCiv(i).fTotalExpensesPerMonth);
                }
            }
            if (i == Game.player.iCivID) {
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).addGold(Game.getCiv(i).fTotalIncomePerMonth + Game.getCiv(i).civBonuses.MonthlyIncome - Game.getCiv(i).fTotalExpensesPerMonth);
                }
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateGold_Manpower_Legacy_Diplomacy() {
        try {
            final Civilization civPlayer = Game.getCiv(Game.player.iCivID);
            if (civPlayer.getNumOfProvinces() > 0) {
                civPlayer.addGold((civPlayer.fTotalIncomePerMonth + civPlayer.civBonuses.MonthlyIncome - civPlayer.fTotalExpensesPerMonth) / Game_Calendar.UPDATE_NUM_OF_DAYS);
                civPlayer.setManpower(civPlayer.fManpower + civPlayer.fManpowerPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
                civPlayer.addLegacy(civPlayer.getLegacyPerMonth() / Game_Calendar.UPDATE_NUM_OF_DAYS);
                civPlayer.fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(civPlayer.fDiplomacyMax, civPlayer.fDiplomacy + civPlayer.getDiplomacyPerMonth() / Game_Calendar.UPDATE_NUM_OF_DAYS));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % Game_Calendar.UPDATE_NUM_OF_DAYS_INT; i < Game.player.iCivID; i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT) {
                final Civilization civ = Game.getCiv(i);
                if (civ.getNumOfProvinces() > 0) {
                    civ.addGold(civ.fTotalIncomePerMonth + civ.civBonuses.MonthlyIncome - civ.fTotalExpensesPerMonth);
                    civ.setManpower(civ.fManpower + civ.fManpowerPerMonth);
                    civ.addLegacy(civ.getLegacyPerMonth());
                    civ.fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(civ.fDiplomacyMax, civ.fDiplomacy + civ.getDiplomacyPerMonth()));
                }
            }
            if (i == Game.player.iCivID) {
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
            while (i < Game.getCivsSize()) {
                final Civilization civ = Game.getCiv(i);
                if (civ.getNumOfProvinces() > 0) {
                    civ.addGold(civ.fTotalIncomePerMonth + civ.civBonuses.MonthlyIncome - civ.fTotalExpensesPerMonth);
                    civ.setManpower(civ.fManpower + civ.fManpowerPerMonth);
                    civ.addLegacy(civ.getLegacyPerMonth());
                    civ.fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(civ.fDiplomacyMax, civ.fDiplomacy + civ.getDiplomacyPerMonth()));
                }
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateCivs_Advantages() {
    }
    
    public final void updateCivs_Laws() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_LAWS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_LAWS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    AI_Laws.adoptNewLaws(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_LAWS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    AI_Laws.adoptNewLaws(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_LAWS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateRelations() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_RELATIONS_TO_NEUTRAL + 1; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_RELATIONS_TO_NEUTRAL) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateRelations(i);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void updateRelations(final int civID) {
        Game.getCiv(civID).diplomacy.updateRelations_ToNeutral(civID);
    }
    
    public final void updateCivs_UpgradeUnits() {
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_UPGRADE_UNITS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_UPGRADE_UNITS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    ArmyManager.upgradeAllArmies(i);
                }
            }
            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_UPGRADE_UNITS;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    ArmyManager.upgradeAllArmies(i);
                }
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_UPGRADE_UNITS;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addCivUpdateResearchPerMonth(final int iCivID) {
        try {
            if (this.civsUpdateResearchPerMonth.contains(iCivID)) {
                return;
            }
            this.civsUpdateResearchPerMonth.add(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateResearchPerMonth() {
        try {
            for (int i = this.civsUpdateResearchPerMonth.size() - 1; i >= 0; --i) {
                Game.getCiv(this.civsUpdateResearchPerMonth.remove()).updateResearchPerMonth();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateResearch() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    if (Game.getCiv(i).getActiveTechResearch() >= 0) {
                        Game.getCiv(i).addResearchProgress(Game.getCiv(i).getActiveTechResearch(), Game.getCiv(i).fResearchPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
                    }
                    else {
                        AI_SelectTechnology.selectTechnology(i);
                        if (Game.getCiv(i).getActiveTechResearch() >= 0) {
                            Game.getCiv(i).addResearchProgress(Game.getCiv(i).getActiveTechResearch(), Game.getCiv(i).fResearchPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
                        }
                        else {
                            try {
                                if (Game.getCiv(i).getAlternativeTechResearch() >= 0) {
                                    Game.getCiv(i).addResearchProgress(Game.getCiv(i).getAlternativeTechResearch(), Game.getCiv(i).fResearchPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
                                }
                                else {
                                    for (int j = 0; j < TechnologyTree.iTechnologySize; ++j) {
                                        if (Game.getCiv(i).getAvailableToResearch(j)) {
                                            Game.getCiv(i).setAlternativeTechResearch(j);
                                            Game.getCiv(i).addResearchProgress(Game.getCiv(i).getAlternativeTechResearch(), Game.getCiv(i).fResearchPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
                                            break;
                                        }
                                    }
                                }
                            }
                            catch (final Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final void addCivUpdateLegacyPerMonth(final int iCivID) {
        try {
            if (this.civsUpdateLegacyPerMonth.contains(iCivID)) {
                return;
            }
            this.civsUpdateLegacyPerMonth.add(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateLegacyPerMonth() {
        try {
            for (int i = this.civsUpdateLegacyPerMonth.size() - 1; i >= 0; --i) {
                Game.getCiv(this.civsUpdateLegacyPerMonth.remove()).updateLegacyPerMonth();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateLegacy() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).addLegacy(Game.getCiv(i).getLegacyPerMonth() / Game_Calendar.UPDATE_NUM_OF_DAYS);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateLegacy_2() {
        try {
            if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                Game.getCiv(Game.player.iCivID).addLegacy(Game.getCiv(Game.player.iCivID).getLegacyPerMonth() / Game_Calendar.UPDATE_NUM_OF_DAYS);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % Game_Calendar.UPDATE_NUM_OF_DAYS_INT; i < Game.player.iCivID; i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).addLegacy(Game.getCiv(i).getLegacyPerMonth());
                }
            }
            if (i == Game.player.iCivID) {
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).addLegacy(Game.getCiv(i).getLegacyPerMonth());
                }
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateManpower() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).setManpower(Game.getCiv(i).fManpower + Game.getCiv(i).fManpowerPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateManpower_2() {
        try {
            if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                Game.getCiv(Game.player.iCivID).setManpower(Game.getCiv(Game.player.iCivID).fManpower + Game.getCiv(Game.player.iCivID).fManpowerPerMonth / Game_Calendar.UPDATE_NUM_OF_DAYS);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % Game_Calendar.UPDATE_NUM_OF_DAYS_INT; i < Game.player.iCivID; i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).setManpower(Game.getCiv(i).fManpower + Game.getCiv(i).fManpowerPerMonth);
                }
            }
            if (i == Game.player.iCivID) {
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).setManpower(Game.getCiv(i).fManpower + Game.getCiv(i).fManpowerPerMonth);
                }
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateDiplomacyPoints() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(Game.getCiv(i).fDiplomacyMax, Game.getCiv(i).fDiplomacy + Game.getCiv(i).getDiplomacyPerMonth() / Game_Calendar.UPDATE_NUM_OF_DAYS));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateDiplomacyPoints_2() {
        try {
            if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                Game.getCiv(Game.player.iCivID).fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(Game.getCiv(Game.player.iCivID).fDiplomacyMax, Game.getCiv(Game.player.iCivID).fDiplomacy + Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth() / Game_Calendar.UPDATE_NUM_OF_DAYS));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            int i;
            for (i = 1 + Game_Calendar.TURN_ID % Game_Calendar.UPDATE_NUM_OF_DAYS_INT; i < Game.player.iCivID; i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(Game.getCiv(i).fDiplomacyMax, Game.getCiv(i).fDiplomacy + Game.getCiv(i).getDiplomacyPerMonth()));
                }
            }
            if (i == Game.player.iCivID) {
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
            while (i < Game.getCivsSize()) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    Game.getCiv(i).fDiplomacy = Math.max((float)GameValues.diplomacy.DIPLOMACY_POINTS_MIN, Math.min(Game.getCiv(i).fDiplomacyMax, Game.getCiv(i).fDiplomacy + Game.getCiv(i).getDiplomacyPerMonth()));
                }
                i += Game_Calendar.UPDATE_NUM_OF_DAYS_INT;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateAggressiveExpansion() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.aggressiveExpansion.GAME_UPDATE_AE_DECAY_TURNS; i < Game.getCivsSize(); i += GameValues.aggressiveExpansion.GAME_UPDATE_AE_DECAY_TURNS) {
                if (Game.getCiv(i).getAggressiveExpansion() > 0.0f) {
                    Game.getCiv(i).decayAggressiveExpansion();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateWarWeariness() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.warWeariness.GAME_UPDATE_WAR_WEARINESS_TICK_TURNS; i < Game.getCivsSize(); i += GameValues.warWeariness.GAME_UPDATE_WAR_WEARINESS_TICK_TURNS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    if (Game.getCiv(i).diplomacy.isAtWar()) {
                        boolean isAttacker = false;
                        for (int a = Game.getCiv(i).diplomacy.iWarsSize - 1; a >= 0; --a) {
                            if (WarManager.lWars.containsKey(Game.getCiv(i).diplomacy.lWars.get(a)) && WarManager.lWars.get(Game.getCiv(i).diplomacy.lWars.get(a)).isAggressor(i)) {
                                isAttacker = true;
                                break;
                            }
                        }
                        if (isAttacker) {
                            Game.getCiv(i).updateWarWeariness(GameValues.warWeariness.WAR_WEARINESS_PER_TICK_AT_WAR);
                        }
                        else {
                            Game.getCiv(i).updateWarWeariness(GameValues.warWeariness.WAR_WEARINESS_PER_TICK_AT_WAR_DEFENDER);
                        }
                    }
                    else {
                        Game.getCiv(i).updateWarWeariness(GameValues.warWeariness.WAR_WEARINESS_PER_TICK_AT_PEACE);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateAutoAssimilation() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.province.AUTO_ASSIMILATION_UPDATE_STEPS; i < Game.getProvincesSize(); i += GameValues.province.AUTO_ASSIMILATION_UPDATE_STEPS) {
                if (Game.getProvince(i).getCivID() > 0) {
                    Game.getProvince(i).autoAssimilate();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updatePopulation() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_POPULATION_STEPS; i < Game.getProvincesSize(); i += GameValues.gameUpdate.GAME_UPDATE_POPULATION_STEPS) {
                if (Game.getProvince(i).getCivID() > 0) {
                    Game.getProvince(i).updatePopulationGrowth();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateArmyMorale_Reinforce() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i).getNumOfProvinces() > 0) {
                        Game.getCiv(i).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i).getNumOfProvinces() > 0) {
                        Game.getCiv(i).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i2 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i2 < Game.getCivsSize(); i2 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i2).getNumOfProvinces() > 0) {
                        Game.getCiv(i2).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i3 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i3 < Game.getCivsSize(); i3 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i3).getNumOfProvinces() > 0) {
                        Game.getCiv(i3).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i4 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i4 < Game.getCivsSize(); i4 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i4).getNumOfProvinces() > 0) {
                        Game.getCiv(i4).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i5 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i5 < Game.getCivsSize(); i5 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i5).getNumOfProvinces() > 0) {
                        Game.getCiv(i5).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i6 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i6 < Game.getCivsSize(); i6 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i6).getNumOfProvinces() > 0) {
                        Game.getCiv(i6).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i7 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i7 < Game.getCivsSize(); i7 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i7).getNumOfProvinces() > 0) {
                        Game.getCiv(i7).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i8 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i8 < Game.getCivsSize(); i8 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i8).getNumOfProvinces() > 0) {
                        Game.getCiv(i8).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i9 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i9 < Game.getCivsSize(); i9 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i9).getNumOfProvinces() > 0) {
                        Game.getCiv(i9).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i10 = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS + 1; i10 < Game.getCivsSize(); i10 += GameValues.gameUpdate.GAME_UPDATE_ARMIES_MORALE_REINFORCE_STEPS) {
                try {
                    if (Game.getCiv(i10).getNumOfProvinces() > 0) {
                        Game.getCiv(i10).updateMorale_Reinforce();
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateCivilizationBonuses() {
        try {
            for (int i = Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_CIV_BONUSES + 1; i < Game.getCivsSize(); i += GameValues.gameUpdate.GAME_UPDATE_CIV_BONUSES) {
                Game.getCiv(i).updateCivilizationBonuses_Temporary();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void returnOccupiedProvinces_NotAtWar() {
        if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_RETURN_OCCUPIED_PROVINCES_NOT_AT_WAR == 0) {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (Game.getProvince(i).isOccupied() && Game.getProvinceData(i).getOccupiedByCivID() >= 0) {
                    if (!DiplomacyManager.isAtWar(Game.getProvince(i).getCivID(), Game.getProvinceData(i).getOccupiedByCivID())) {
                        Game.getProvince(i).retakeOccupiedProvince_Peace();
                    }
                }
            }
        }
    }
    
    static {
        GameThread.calculationsTime = 0L;
    }
}
