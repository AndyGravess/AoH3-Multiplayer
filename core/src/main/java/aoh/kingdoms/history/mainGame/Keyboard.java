// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.menu.ColorPicker;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEdit;
import aoc.kingdoms.lukasz.menusEditor.CreateCiv;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioPopulation_List;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioSettings;
import aoc.kingdoms.lukasz.menusScenarioEditor.Scenario_Calendar;
import aoc.kingdoms.lukasz.menusInGame.InGame_SaveGame;
import aoc.kingdoms.lukasz.menusInGame.InGame_Console;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCreateAllianceList;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Provinces;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvinceNames;
import aoc.kingdoms.lukasz.map.province.ProvinceNamesManager;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceInfo;
import aoc.kingdoms.lukasz.menusInGame.InGame_Ranking;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldSearch;
import aoc.kingdoms.lukasz.menusInGame.InGame_Encyclopedia;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldCivs;
import aoc.kingdoms.lukasz.menusEditor.GameCivs;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class Keyboard
{
    public static boolean keyboardMode;
    public static String keyboardMessage;
    public static boolean keyboardVerticalLine;
    public static int VERTICAL_LINE_UPDATE_TIME;
    public static long verticalLineTime;
    public static Keyboard_Action keyboardAction;
    public static KeyboardActionType keyboardActionType;
    
    public static void updateKeyboardVerticalLine() {
        if (Keyboard.keyboardMode && Keyboard.verticalLineTime + Keyboard.VERTICAL_LINE_UPDATE_TIME < CFG.currentTimeMillis) {
            Keyboard.keyboardVerticalLine = !Keyboard.keyboardVerticalLine;
            Keyboard.verticalLineTime = CFG.currentTimeMillis;
        }
    }
    
    public static String getKeyboardVerticalLine() {
        return Keyboard.keyboardVerticalLine ? "|" : "";
    }
    
    public final void showKeyboard(final KeyboardActionType actionType, final String nMessage) {
        Keyboard.keyboardMode = true;
        Keyboard.keyboardMessage = nMessage;
        Keyboard.verticalLineTime = CFG.currentTimeMillis;
        Keyboard.keyboardVerticalLine = true;
        Keyboard.keyboardActionType = actionType;
        if (!CFG.isDesktop()) {
            try {
                Gdx.input.setOnscreenKeyboardVisible(true, Input.OnscreenKeyboardType.Default);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        switch (actionType.ordinal()) {
            case 1: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivs.sSearch = (Keyboard.keyboardMessage += nChar);
                        GameCivs.chosen_AlphabetCharachter = "";
                        Game.menuManager.rebuildEditorGameCivs();
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildEditorGameCivs();
                    }
                    
                    @Override
                    public void save() {
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildEditorGameCivs();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 16: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Court_WorldCivs.sSearch = (Keyboard.keyboardMessage += nChar);
                        Game.menuManager.rebuildInGame_CourtSearchCivs();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_Court_WorldCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildInGame_CourtSearchCivs();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void save() {
                        InGame_Court_WorldCivs.sSearch = Keyboard.keyboardMessage;
                        if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.rebuildInGame_CourtSearchCivs();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 17: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Encyclopedia.sSearch = (Keyboard.keyboardMessage += nChar);
                        Game.menuManager.rebuildInGame_Encyclopedia();
                        InGame_Encyclopedia.lTime = 0L;
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_Encyclopedia.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildInGame_Encyclopedia();
                        InGame_Encyclopedia.lTime = 0L;
                    }
                    
                    @Override
                    public void save() {
                        InGame_Court_WorldSearch.sSearch = Keyboard.keyboardMessage;
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 15) {
                            InGame_Encyclopedia.sSearch = Keyboard.keyboardMessage;
                            Game.menuManager.rebuildInGame_Encyclopedia();
                            InGame_Encyclopedia.lTime = 0L;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 18: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Ranking.sSearch = (Keyboard.keyboardMessage += nChar);
                        Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                        InGame_Ranking.lTime = 0L;
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_Ranking.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                        InGame_Ranking.lTime = 0L;
                    }
                    
                    @Override
                    public void save() {
                        InGame_Court_WorldSearch.sSearch = Keyboard.keyboardMessage;
                        if (Game.menuManager.getVisibleInGame_CurrentSituation_Ranking()) {
                            InGame_Ranking.sSearch = Keyboard.keyboardMessage;
                            Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
                            InGame_Ranking.lTime = 0L;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 15: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Court_WorldSearch.sSearch = (Keyboard.keyboardMessage += nChar);
                        Game.menuManager.rebuildInGame_CourtSearch();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_Court_WorldSearch.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildInGame_CourtSearch();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void save() {
                        InGame_Court_WorldSearch.sSearch = Keyboard.keyboardMessage;
                        if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.rebuildInGame_CourtSearch();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 20: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_ProvinceInfo.renameProvince = (Keyboard.keyboardMessage += nChar);
                        if (InGame_ProvinceInfo.renameProvince.length() > 2) {
                            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCitiesSize() > 0) {
                                Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCity(0).setCityName(InGame_ProvinceInfo.iProvinceID, InGame_ProvinceInfo.renameProvince);
                            }
                            else {
                                Game.getProvince(InGame_ProvinceInfo.iProvinceID).setProvinceName(InGame_ProvinceInfo.renameProvince);
                                Game.addSimpleTask(new Game.SimpleTask("rebuildProvNameData" + InGame_ProvinceInfo.iProvinceID, InGame_ProvinceInfo.iProvinceID) {
                                    @Override
                                    public void update() {
                                        ProvinceNamesManager.buildProvNameData(this.id, true);
                                    }
                                });
                            }
                        }
                        Game.menuManager.getInGame_ProvinceInfo_updateLanguage();
                    }
                    
                    @Override
                    public void delete() {
                        InGame_ProvinceInfo.renameProvince = (Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : ""));
                        if (InGame_ProvinceInfo.renameProvince.length() > 2) {
                            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCitiesSize() > 0) {
                                Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCity(0).setCityName(InGame_ProvinceInfo.iProvinceID, InGame_ProvinceInfo.renameProvince);
                            }
                            else {
                                Game.getProvince(InGame_ProvinceInfo.iProvinceID).setProvinceName(InGame_ProvinceInfo.renameProvince);
                                Game.addSimpleTask(new Game.SimpleTask("rebuildProvNameData" + InGame_ProvinceInfo.iProvinceID, InGame_ProvinceInfo.iProvinceID) {
                                    @Override
                                    public void update() {
                                        ProvinceNamesManager.buildProvNameData(this.id, true);
                                    }
                                });
                            }
                        }
                        Game.menuManager.getInGame_ProvinceInfo_updateLanguage();
                    }
                    
                    @Override
                    public void save() {
                        InGame_ProvinceInfo.renameProvince = Keyboard.keyboardMessage;
                        if (InGame_ProvinceInfo.renameProvince.length() > 2) {
                            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCitiesSize() > 0) {
                                Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCity(0).setCityName(InGame_ProvinceInfo.iProvinceID, InGame_ProvinceInfo.renameProvince);
                            }
                            else {
                                Game.getProvince(InGame_ProvinceInfo.iProvinceID).setProvinceName(InGame_ProvinceInfo.renameProvince);
                                Game.addSimpleTask(new Game.SimpleTask("rebuildProvNameData" + InGame_ProvinceInfo.iProvinceID, InGame_ProvinceInfo.iProvinceID) {
                                    @Override
                                    public void update() {
                                        ProvinceNamesManager.buildProvNameData(this.id, true);
                                    }
                                });
                            }
                        }
                        FBOProvinceNames.redrawnProvinceNames();
                        Game.menuManager.getInGame_ProvinceInfo_updateLanguage();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 19: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Civ.renameCiv = (Keyboard.keyboardMessage += nChar);
                        if (InGame_Civ.renameCiv.length() > 2) {
                            Game.getCiv(InGame_Civ.iActiveCivID).setCivName(InGame_Civ.renameCiv);
                        }
                        Game.menuManager.getInGame_Civ_updateLanguage();
                    }
                    
                    @Override
                    public void delete() {
                        InGame_Civ.renameCiv = (Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : ""));
                        if (InGame_Civ.renameCiv.length() > 2) {
                            Game.getCiv(InGame_Civ.iActiveCivID).setCivName(InGame_Civ.renameCiv);
                        }
                        Game.menuManager.getInGame_Civ_updateLanguage();
                    }
                    
                    @Override
                    public void save() {
                        InGame_Civ.renameCiv = Keyboard.keyboardMessage;
                        if (InGame_Civ.renameCiv.length() > 2) {
                            Game.getCiv(InGame_Civ.iActiveCivID).setCivName(InGame_Civ.renameCiv);
                            Renderer.addSimpleTaskCivsNames(new Game.SimpleTask("" + InGame_Civ.iActiveCivID, InGame_Civ.iActiveCivID) {
                                @Override
                                public void update() {
                                    CivilizationRegionsManager.buildCivilizationsRegions_TextOver(this.id);
                                }
                            });
                        }
                        Game.menuManager.getInGame_Civ_updateLanguage();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 14: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Court_Provinces.sSearch = (Keyboard.keyboardMessage += nChar);
                        Game.menuManager.rebuildInGame_CourtProvinces();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_Court_Provinces.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildInGame_CourtProvinces();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }
                    
                    @Override
                    public void save() {
                        InGame_Court_Provinces.sSearch = Keyboard.keyboardMessage;
                        if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.rebuildInGame_CourtProvinces();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 46: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioCreateAllianceList.sMessageRest = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioCreateAllianceList.sMessageRest = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioCreateAllianceList.editAlliance.Name_Rest = (ScenarioCreateAllianceList.sMessageRest = Keyboard.keyboardMessage);
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 45: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioCreateAllianceList.sMessageFirstTier = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioCreateAllianceList.sMessageFirstTier = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioCreateAllianceList.editAlliance.Name_FirstTier = (ScenarioCreateAllianceList.sMessageFirstTier = Keyboard.keyboardMessage);
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 47: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioCreateAllianceList.sMessageFlag = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioCreateAllianceList.sMessageFlag = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioCreateAllianceList.editAlliance.FlagTag = (ScenarioCreateAllianceList.sMessageFlag = Keyboard.keyboardMessage);
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 44: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioCreateAllianceList.sMessageLeader = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioCreateAllianceList.sMessageLeader = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioCreateAllianceList.editAlliance.Name_Leader = (ScenarioCreateAllianceList.sMessageLeader = Keyboard.keyboardMessage);
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 43: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioCreateAllianceList.sMessageName = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioCreateAllianceList.sMessageName = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioCreateAllianceList.editAlliance.Name_Alliance = (ScenarioCreateAllianceList.sMessageName = Keyboard.keyboardMessage);
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 11: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_Console.sMessage = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_Console.sMessage = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        InGame_Console.execute(InGame_Console.sMessage = Keyboard.keyboardMessage);
                        InGame_Console.sMessage = "";
                        Game.menuManager.rebuildInGame_Console();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 12: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame_SaveGame.sSaveName = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame_SaveGame.sSaveName = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        InGame_SaveGame.sSaveName = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 13: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        Scenario_Calendar.sYear = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        Scenario_Calendar.sYear = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        Scenario_Calendar.sYear = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game_Calendar.currentYear = Integer.parseInt(Scenario_Calendar.sYear);
                        }
                        catch (final Exception ex) {
                            Scenario_Calendar.sYear = "" + Game_Calendar.currentYear;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 26: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sGold = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sGold = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sGold = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.CivDefault_Gold = Integer.parseInt(ScenarioSettings.sGold);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sGold = "" + MapScenarios.scenarioEditorDetails.CivDefault_Gold;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 27: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sGoldRandom = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sGoldRandom = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sGoldRandom = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.CivDefault_GoldRandom = Integer.parseInt(ScenarioSettings.sGoldRandom);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sGoldRandom = "" + MapScenarios.scenarioEditorDetails.CivDefault_GoldRandom;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 28: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sLegacy = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sLegacy = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sLegacy = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.CivDefault_Legacy = Integer.parseInt(ScenarioSettings.sLegacy);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sLegacy = "" + MapScenarios.scenarioEditorDetails.CivDefault_Legacy;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 29: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sLegacyRandom = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sLegacyRandom = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sLegacyRandom = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.CivDefault_LegacyRandom = Integer.parseInt(ScenarioSettings.sLegacyRandom);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sLegacyRandom = "" + MapScenarios.scenarioEditorDetails.CivDefault_LegacyRandom;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 30: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sManpower = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sManpower = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sManpower = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.CivDefault_ManpowerPercentage = Integer.parseInt(ScenarioSettings.sManpower);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sManpower = "" + MapScenarios.scenarioEditorDetails.CivDefault_ManpowerPercentage;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 42: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Nukes = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Nukes = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Nukes = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 40: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Legacy = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Legacy = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Legacy = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 41: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.v = Integer.parseInt(ScenarioPopulation_List.sPopulation);
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.v = Integer.parseInt(ScenarioPopulation_List.sPopulation);
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.v = Integer.parseInt(ScenarioPopulation_List.sPopulation);
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 39: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Gold = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Gold = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Gold = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 38: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Manpower = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Manpower = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Manpower = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 37: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.TaxEff = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.TaxEff = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.TaxEff = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 36: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Economy = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Economy = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Economy = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 35: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioPopulation_List.sPopulation = (Keyboard.keyboardMessage += nChar);
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Population = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Population = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        ScenarioPopulation_List.sPopulation = Keyboard.keyboardMessage;
                        try {
                            final int tYear = Game.getCiv(ScenarioPopulation_List.iCivID).scenarioEditorData.Population = Math.max(0, Integer.parseInt(ScenarioPopulation_List.sPopulation));
                        }
                        catch (final Exception ex) {
                            ScenarioPopulation_List.sPopulation = "0";
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 31: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sProvinceDefault_Population = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sProvinceDefault_Population = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sProvinceDefault_Population = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.ProvinceDefault_Population = Integer.parseInt(ScenarioSettings.sProvinceDefault_Population);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sProvinceDefault_Population = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_Population;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 32: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sProvinceDefault_Economy = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sProvinceDefault_Economy = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sProvinceDefault_Economy = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.ProvinceDefault_Economy = Integer.parseInt(ScenarioSettings.sProvinceDefault_Economy);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sProvinceDefault_Economy = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_Economy;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 33: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sProvinceDefault_TaxEfficiency = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sProvinceDefault_TaxEfficiency = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sProvinceDefault_TaxEfficiency = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.ProvinceDefault_TaxEfficiency = Integer.parseInt(ScenarioSettings.sProvinceDefault_TaxEfficiency);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sProvinceDefault_TaxEfficiency = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_TaxEfficiency;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 34: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sProvinceDefault_Manpower = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sProvinceDefault_Manpower = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sProvinceDefault_Manpower = Keyboard.keyboardMessage;
                        try {
                            final int tYear = MapScenarios.scenarioEditorDetails.ProvinceDefault_Manpower = Integer.parseInt(ScenarioSettings.sProvinceDefault_Manpower);
                        }
                        catch (final Exception ex) {
                            ScenarioSettings.sProvinceDefault_Manpower = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_Manpower;
                        }
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 5: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        CreateCiv.ROOMNAME = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        CreateCiv.ROOMNAME = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        CreateCiv.ROOMNAME = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 2: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivsEdit.nCiv.Tag = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivsEdit.nCiv.Tag = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        GameCivsEdit.nCiv.Tag = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 3: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivsEdit.nCiv.Name = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivsEdit.nCiv.Name = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        GameCivsEdit.nCiv.Name = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 6: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        CreateCiv.nCiv.Name = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        CreateCiv.nCiv.Name = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        CreateCiv.nCiv.Name = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 4: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivsEdit.nCiv.Wiki = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivsEdit.nCiv.Wiki = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        GameCivsEdit.nCiv.Wiki = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 7: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        CreateCiv.nCiv.Wiki = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        CreateCiv.nCiv.Wiki = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        CreateCiv.nCiv.Wiki = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 8: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivs.sSearch = (Keyboard.keyboardMessage += nChar);
                        GameCivs.chosen_AlphabetCharachter = "";
                        Game.menuManager.rebuildScenarioCivilizationsList();
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildScenarioCivilizationsList();
                    }
                    
                    @Override
                    public void save() {
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildScenarioCivilizationsList();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 9: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivs.sSearch = (Keyboard.keyboardMessage += nChar);
                        GameCivs.chosen_AlphabetCharachter = "";
                        Game.menuManager.rebuildScenarioCoresList();
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildScenarioCoresList();
                    }
                    
                    @Override
                    public void save() {
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildScenarioCoresList();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 10: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        GameCivs.sSearch = (Keyboard.keyboardMessage += nChar);
                        GameCivs.chosen_AlphabetCharachter = "";
                        Game.menuManager.rebuildEditorCivSelect();
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildEditorCivSelect();
                    }
                    
                    @Override
                    public void save() {
                        GameCivs.sSearch = Keyboard.keyboardMessage;
                        Game.menuManager.rebuildEditorCivSelect();
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 48: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sName = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sName = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sName = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 21: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        Game.getCiv(InGame_Court.iActiveCivID).ruler.Name = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        Game.getCiv(InGame_Court.iActiveCivID).ruler.Name = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        Game.getCiv(InGame_Court.iActiveCivID).ruler.Name = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 22: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        Keyboard.keyboardMessage += nChar;
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 23: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        Keyboard.keyboardMessage += nChar;
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 24: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        Keyboard.keyboardMessage += nChar;
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 25: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        Keyboard.keyboardMessage += nChar;
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                    }
                    
                    @Override
                    public void save() {
                        try {
                            Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName = Keyboard.keyboardMessage;
                        }
                        catch (final Exception ex) {}
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 49: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sAuthor = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sAuthor = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sAuthor = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 51: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sWiki = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sWiki = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sWiki = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 50: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        ScenarioSettings.sTag = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        ScenarioSettings.sTag = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        ScenarioSettings.sTag = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 53: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        InGame.sMessage = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        InGame.sMessage = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        InGame.sMessage = Keyboard.keyboardMessage;
                        DesktopLauncher.SMS("Message: " + InGame.sMessage);
                        InGame.sMessage = "";
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 54: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        CreateCiv.PASSWORD = (Keyboard.keyboardMessage += nChar);
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "");
                        CreateCiv.PASSWORD = Keyboard.keyboardMessage;
                    }
                    
                    @Override
                    public void save() {
                        CreateCiv.PASSWORD = Keyboard.keyboardMessage;
                        Keyboard.this.hideKeyboard();
                    }
                };
                return;
            }
            case 52: {
                Keyboard.keyboardAction = new Keyboard_Action() {
                    @Override
                    public void actionType(final String nChar) {
                        try {
                            if (nChar.charAt(0) >= '0' && nChar.charAt(0) <= '9') {
                                Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.charAt(0) == '0') ? nChar : (Keyboard.keyboardMessage + nChar));
                                try {
                                    if (Integer.parseInt(Keyboard.keyboardMessage) > 255) {
                                        Keyboard.keyboardMessage = "255";
                                    }
                                }
                                catch (final IllegalArgumentException e) {
                                    Keyboard.keyboardMessage = "255";
                                }
                                Game.menuManager.getColorPicker();
                                switch (ColorPicker.activeRGB) {
                                    case 0: {
                                        Game.menuManager.getColorPicker().getActiveColor().r = Integer.parseInt(Keyboard.keyboardMessage) / 255.0f;
                                        break;
                                    }
                                    case 1: {
                                        Game.menuManager.getColorPicker().getActiveColor().g = Integer.parseInt(Keyboard.keyboardMessage) / 255.0f;
                                        break;
                                    }
                                    case 2: {
                                        Game.menuManager.getColorPicker().getActiveColor().b = Integer.parseInt(Keyboard.keyboardMessage) / 255.0f;
                                        break;
                                    }
                                }
                                Game.menuManager.getColorPicker().RGBtoHSV((int)(Game.menuManager.getColorPicker().getActiveColor().r * 255.0f), (int)(Game.menuManager.getColorPicker().getActiveColor().g * 255.0f), (int)(Game.menuManager.getColorPicker().getActiveColor().b * 255.0f));
                                Game.menuManager.getColorPicker().getColorPickerAction().update();
                                Game.menuManager.getColorPicker().updateRGBWidth();
                            }
                        }
                        catch (final IndexOutOfBoundsException ex) {}
                    }
                    
                    @Override
                    public void delete() {
                        Keyboard.keyboardMessage = ((Keyboard.keyboardMessage.length() > 1) ? Keyboard.keyboardMessage.substring(0, Keyboard.keyboardMessage.length() - 1) : "0");
                        Game.menuManager.getColorPicker();
                        switch (ColorPicker.activeRGB) {
                            case 0: {
                                Game.menuManager.getColorPicker().getActiveColor().r = Integer.parseInt(Keyboard.keyboardMessage) / 255.0f;
                                break;
                            }
                            case 1: {
                                Game.menuManager.getColorPicker().getActiveColor().g = Integer.parseInt(Keyboard.keyboardMessage) / 255.0f;
                                break;
                            }
                            case 2: {
                                Game.menuManager.getColorPicker().getActiveColor().b = Integer.parseInt(Keyboard.keyboardMessage) / 255.0f;
                                break;
                            }
                        }
                        Game.menuManager.getColorPicker().RGBtoHSV((int)(Game.menuManager.getColorPicker().getActiveColor().r * 255.0f), (int)(Game.menuManager.getColorPicker().getActiveColor().g * 255.0f), (int)(Game.menuManager.getColorPicker().getActiveColor().b * 255.0f));
                        Game.menuManager.getColorPicker().getColorPickerAction().update();
                        Game.menuManager.getColorPicker().updateRGBWidth();
                    }
                    
                    @Override
                    public void save() {
                        Keyboard.this.hideKeyboard();
                    }
                };
            }
            default: {}
        }
    }
    
    public final void hideKeyboard() {
        if (Keyboard.keyboardMode) {
            try {
                if (Keyboard.keyboardAction != null && Keyboard.keyboardActionType != KeyboardActionType.NONE) {
                    final KeyboardActionType tempAction = Keyboard.keyboardActionType;
                    Keyboard.keyboardActionType = KeyboardActionType.NONE;
                    if (tempAction != KeyboardActionType.CONSOLE) {
                        Keyboard.keyboardAction.save();
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            Keyboard.keyboardMode = false;
            Keyboard.keyboardMessage = "";
            Keyboard.keyboardVerticalLine = false;
            Keyboard.keyboardActionType = KeyboardActionType.NONE;
            Keyboard.keyboardAction = new Keyboard_Action() {
                @Override
                public void actionType(final String nChar) {
                }
                
                @Override
                public void delete() {
                }
                
                @Override
                public void save() {
                }
            };
            if (!CFG.isDesktop()) {
                try {
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
    }
    
    static {
        Keyboard.keyboardMode = false;
        Keyboard.keyboardMessage = "";
        Keyboard.keyboardVerticalLine = false;
        Keyboard.VERTICAL_LINE_UPDATE_TIME = 375;
        Keyboard.verticalLineTime = CFG.currentTimeMillis;
        Keyboard.keyboardActionType = KeyboardActionType.NONE;
    }
    
    public enum KeyboardActionType
    {
        NONE, 
        SEARCH_GAMECIVS, 
        GAMECIVS_EDIT, 
        GAMECIVS_EDIT_NAME, 
        GAMECIVS_EDIT_WIKI, 
        CREATE_CIV_EDIT, 
        CREATE_CIV_EDIT_NAME, 
        CREATE_CIV_EDIT_WIKI, 
        SEARCH_SCENARIO_CIVS, 
        SEARCH_SCENARIO_CORES_CIVS, 
        SEARCH_CIV_SELECT, 
        CONSOLE, 
        SAVE_NAME, 
        SCENARIO_YEAR, 
        SEARCH_COURT_PROVINCES, 
        SEARCH_COURT_ALL_PROVINCES, 
        SEARCH_COURT_ALL_CIVS, 
        SEARCH_ENCYCLOPEDIA, 
        SEARCH_RANKING, 
        INGAME_RENAME_CIV, 
        INGAME_RENAME_PROVINCE, 
        INGAME_RULER_NAME, 
        INGAME_ADVISOR_ADMINISTRATIVE_NAME, 
        INGAME_ADVISOR_ECONOMIC_NAME, 
        INGAME_ADVISOR_INNOVATION_NAME, 
        INGAME_ADVISOR_MILITARY_NAME, 
        SCENARIO_GOLD, 
        SCENARIO_GOLD_RANDOM, 
        SCENARIO_LEGACY, 
        SCENARIO_LEGACY_RANDOM, 
        SCENARIO_MANPOWER_PERC, 
        SCENARIO_PROVINCE_POPULATION, 
        SCENARIO_PROVINCE_ECONOMY, 
        SCENARIO_PROVINCE_TAX_EFFICIENCY, 
        SCENARIO_PROVINCE_MANPOWER, 
        SCENARIO_CIV_POPULATION, 
        SCENARIO_CIV_ECONOMY, 
        SCENARIO_CIV_TAX_EFFICIENCY, 
        SCENARIO_CIV_MANPOWER, 
        SCENARIO_CIV_GOLD, 
        SCENARIO_CIV_LEGACY, 
        SCENARIO_CIV_AI_AGGRESSIVENESS, 
        SCENARIO_CIV_NUKES, 
        SCENARIO_CREATE_ALLIANCE_NAME, 
        SCENARIO_CREATE_ALLIANCE_LEADER, 
        SCENARIO_CREATE_ALLIANCE_FIRST_TIER, 
        SCENARIO_CREATE_ALLIANCE_REST, 
        SCENARIO_CREATE_ALLIANCE_FLAG, 
        SCENARIO_NAME, 
        SCENARIO_AUTHOR, 
        SCENARIO_TAG, 
        SCENARIO_WIKI, 
        COLORPICKER, 
        CHAT, 
        PASSWORD;
    }
    
    public interface Keyboard_Action
    {
        void actionType(final String p0);
        
        void delete();
        
        void save();
    }
}
