// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.map.army.ArmyDivision;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioMilitaryAccessList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioGuaranteeList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioNonAggressionList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDefensiveList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioAlliancesList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioRelationsList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioVassalsList;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioTrucesList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDeclareWarList;
import aoh.kingdoms.history.map.province.ProvinceDrawDetails;
import aoh.kingdoms.history.menusMapEditor.EditorSelectProvinces;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDiplomacy;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.civilization.Civilization;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Government;
import aoh.kingdoms.history.map.province.ProvinceDrawArmy;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Sandbox extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_Sandbox() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = 0;
        final int buttonX = paddingLeft;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Player"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Player") + ": " + Game.lang.get("SelectCivilization"), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                if (Game.iActiveProvince >= 0) {
                    if (Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        InGame_Sandbox.setPlayerCiv(Game.getProvince(Game.iActiveProvince).getCivID());
                    }
                    else {
                        Game.menuManager.addToast_Error(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Images.x);
                    }
                }
                else {
                    Game.menuManager.addToast_Error(Game.lang.get("SelectProvince"), Images.provinces);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectCivilization"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.iActiveProvince >= 0) {
                    if (Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iActiveProvince).getCivID(), CFG.PADDING, 0));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.v, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iActiveProvince).getCivID(), CFG.PADDING, 0));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.x, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SelectProvinces"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.SPECTATOR_MODE) {
            menuElements.add(new ButtonStatsBudget(Game.lang.get("Player") + ": " + Game.lang.get("Neutral"), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                @Override
                public void actionElement() {
                    Game.player.iCivID = 0;
                    ProvinceDrawArmy.updateArmyImgID();
                    if (Game.FOG_OF_WAR) {
                        Game.player.fog.buildFogOfWar(Game.player.iCivID);
                    }
                    Game.player.clearPinnedArmy();
                    Game.player.loadFormableCivs();
                    InGame_Court_Government.reloadFlags = true;
                    Game.addSimpleTask(new Game.SimpleTask("rebuildSelectCivilization") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Right();
                            Game.menuManager.rebuildInGame_Messages();
                            Game.menuManager.rebuildInGame_Wars();
                        }
                    });
                    Game.menuManager.addToastPositive(Game.lang.get("Civilization") + ": ", Game.getCiv(0).getCivName(), Images.v);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectCivilization"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(0).getCivName(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Flag(0, CFG.PADDING, 0));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.v, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Civilization"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        final int buttonH = CFG.BUTTON_HEIGHT4;
        final int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 4;
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.GOLD_1, Images.gold, paddingLeft, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addGold((float)(-GameValues.sandbox.GOLD_1));
                Game.menuManager.addToastNegative(Game.lang.get("Gold") + ": ", "-" + GameValues.sandbox.GOLD_1, Images.gold);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.GOLD_2, Images.gold, paddingLeft + (buttonW + CFG.PADDING), buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addGold((float)(-GameValues.sandbox.GOLD_2));
                Game.menuManager.addToastNegative(Game.lang.get("Gold") + ": ", "-" + GameValues.sandbox.GOLD_2, Images.gold);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.GOLD_2, Images.gold, paddingLeft + (buttonW + CFG.PADDING) * 2, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                final Civilization civ = Game.getCiv(Game.player.iCivID);
                civ.fGold += GameValues.sandbox.GOLD_2;
                Game.menuManager.addToastPositive(Game.lang.get("Gold") + ": ", "+" + GameValues.sandbox.GOLD_2, Images.gold);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.GOLD_1, Images.gold, paddingLeft + (buttonW + CFG.PADDING) * 3, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                final Civilization civ = Game.getCiv(Game.player.iCivID);
                civ.fGold += GameValues.sandbox.GOLD_1;
                Game.menuManager.addToastPositive(Game.lang.get("Gold") + ": ", "+" + GameValues.sandbox.GOLD_1, Images.gold);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.MANPOWER_1, Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).setManpower(Game.getCiv(Game.player.iCivID).fManpower - GameValues.sandbox.MANPOWER_1);
                Game.menuManager.addToastNegative(Game.lang.get("Manpower") + ": ", "-" + GameValues.sandbox.MANPOWER_1, Game_Calendar.IMG_MANPOWER);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.MANPOWER_2, Game_Calendar.IMG_MANPOWER, paddingLeft + (buttonW + CFG.PADDING), buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).setManpower(Game.getCiv(Game.player.iCivID).fManpower - GameValues.sandbox.MANPOWER_2);
                Game.menuManager.addToastNegative(Game.lang.get("Manpower") + ": ", "-" + GameValues.sandbox.MANPOWER_2, Game_Calendar.IMG_MANPOWER);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.MANPOWER_2, Game_Calendar.IMG_MANPOWER, paddingLeft + (buttonW + CFG.PADDING) * 2, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                final Civilization civ = Game.getCiv(Game.player.iCivID);
                civ.fManpower += GameValues.sandbox.MANPOWER_2;
                Game.menuManager.addToastPositive(Game.lang.get("Manpower") + ": ", "+" + GameValues.sandbox.MANPOWER_2, Game_Calendar.IMG_MANPOWER);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.MANPOWER_1, Game_Calendar.IMG_MANPOWER, paddingLeft + (buttonW + CFG.PADDING) * 3, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                final Civilization civ = Game.getCiv(Game.player.iCivID);
                civ.fManpower += GameValues.sandbox.MANPOWER_1;
                Game.menuManager.addToastPositive(Game.lang.get("Manpower") + ": ", "+" + GameValues.sandbox.MANPOWER_1, Game_Calendar.IMG_MANPOWER);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.LEGACY_1, Images.legacy, paddingLeft, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addLegacy((float)(-GameValues.sandbox.LEGACY_1));
                Game.menuManager.addToastNegative(Game.lang.get("Legacy") + ": ", "-" + GameValues.sandbox.LEGACY_1, Images.legacy);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.LEGACY_2, Images.legacy, paddingLeft + (buttonW + CFG.PADDING), buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addLegacy((float)(-GameValues.sandbox.LEGACY_2));
                Game.menuManager.addToastNegative(Game.lang.get("Legacy") + ": ", "-" + GameValues.sandbox.LEGACY_2, Images.legacy);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.LEGACY_2, Images.legacy, paddingLeft + (buttonW + CFG.PADDING) * 2, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addLegacy((float)GameValues.sandbox.LEGACY_2);
                Game.menuManager.addToastPositive(Game.lang.get("Legacy") + ": ", "+" + GameValues.sandbox.LEGACY_2, Images.legacy);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.LEGACY_1, Images.legacy, paddingLeft + (buttonW + CFG.PADDING) * 3, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addLegacy((float)GameValues.sandbox.LEGACY_1);
                Game.menuManager.addToastPositive(Game.lang.get("Legacy") + ": ", "+" + GameValues.sandbox.LEGACY_1, Images.legacy);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.RESEARCH_1, Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addResearchProgress((float)(-GameValues.sandbox.RESEARCH_1));
                Game.menuManager.addToastNegative(Game.lang.get("Research") + ": ", "-" + GameValues.sandbox.RESEARCH_1, Game_Calendar.IMG_TECHNOLOGY);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "-" + GameValues.sandbox.RESEARCH_2, Game_Calendar.IMG_TECHNOLOGY, paddingLeft + (buttonW + CFG.PADDING), buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addResearchProgress((float)(-GameValues.sandbox.RESEARCH_2));
                Game.menuManager.addToastNegative(Game.lang.get("Research") + ": ", "-" + GameValues.sandbox.RESEARCH_2, Game_Calendar.IMG_TECHNOLOGY);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.RESEARCH_2, Game_Calendar.IMG_TECHNOLOGY, paddingLeft + (buttonW + CFG.PADDING) * 2, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addResearchProgress((float)GameValues.sandbox.RESEARCH_2);
                Game.menuManager.addToastPositive(Game.lang.get("Research") + ": ", "+" + GameValues.sandbox.RESEARCH_2, Game_Calendar.IMG_TECHNOLOGY);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses("", "+" + GameValues.sandbox.RESEARCH_1, Game_Calendar.IMG_TECHNOLOGY, paddingLeft + (buttonW + CFG.PADDING) * 3, buttonY, buttonW, buttonH, maxIconW, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
            }
            
            @Override
            public void actionElement() {
                Game.getCiv(Game.player.iCivID).addResearchProgress((float)GameValues.sandbox.RESEARCH_1);
                Game.menuManager.addToastPositive(Game.lang.get("Research") + ": ", "+" + GameValues.sandbox.RESEARCH_1, Game_Calendar.IMG_TECHNOLOGY);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Armies"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                Renderer.drawArmyInProvince = true;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_ARMIES);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Armies"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Buildings"), Images.buildings, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_BUILDINGS);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Buildings"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Religion"), Images.religion, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                CFG.brushTool = false;
                CFG.selectMode = true;
                EditorSelectProvinces.selectedProvinces.clear();
                Renderer.drawArmyInProvince = false;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                ProvinceDrawDetails.updateDrawProvinceDetails_ScenarioReligion();
                Game.menuManager.setViewID(View.SCENARIO_RELIGION);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Religion"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("AssignProvinces"), Images.provinces, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                CFG.iCreateScenario_AssignProvinces_Civ = 0;
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(View.SCENARIO_ASSIGN_IN_GAME);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AssignProvinces"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Diplomacy"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("DeclareWar"), Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioDeclareWarList.activeCivID = 0;
                ScenarioDeclareWarList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_DECLARE_WAR);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DeclareWar"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Truces"), Images.truce, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioTrucesList.activeCivID = 0;
                ScenarioTrucesList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_TRUCES);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Truces"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.truce, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get(Game_Ages.getVassals()), Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioVassalsList.activeCivID = 0;
                ScenarioVassalsList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_VASSALS);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getVassals()), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Relations"), Images.relations, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioRelationsList.activeCivID = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_RELATIONS);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Relations"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.relations, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Alliances"), Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioAlliancesList.activeCivID = 0;
                ScenarioAlliancesList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_ALLIANCES);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Alliances"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("DefensivePacts"), Images.defensivePact, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioDefensiveList.activeCivID = 0;
                ScenarioDefensiveList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_DEFENSIVE);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DefensivePact"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.defensivePact, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("NonAggressionPacts"), Images.nonAggression, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioNonAggressionList.activeCivID = 0;
                ScenarioNonAggressionList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_NON_AGGRESSION);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("NonAggressionPacts"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nonAggression, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("GuaranteeIndependence"), Images.guaranteeIndependence, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioGuaranteeList.activeCivID = 0;
                ScenarioGuaranteeList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_GUARANTEE);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("GuaranteeIndependence"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.guaranteeIndependence, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("MilitaryAccess"), Images.militaryAccess, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                ScenarioMilitaryAccessList.activeCivID = 0;
                ScenarioMilitaryAccessList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.IN_GAME;
                Game.menuManager.setViewID(View.SCENARIO_MILITARY_ACCESS);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MilitaryAccess"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.militaryAccess, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Sandbox"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Sandbox.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Sandbox.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Sandbox.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Sandbox.lTime = CFG.currentTimeMillis;
    }
    
    public static void setPlayerCiv(final int civID) {
        Game.player.iCivID = civID;
        ProvinceDrawArmy.updateArmyImgID();
        if (Game.FOG_OF_WAR) {
            Game.player.fog.buildFogOfWar(Game.player.iCivID);
        }
        Game.player.clearPinnedArmy();
        Game.player.loadFormableCivs();
        InGame_Court_Government.reloadFlags = true;
        if (Game.FOG_OF_WAR) {
            try {
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                    final ArmyDivision armyDivision = Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(Game.getCiv(Game.player.iCivID).getArmyPositionKey(i));
                    if (armyDivision != null) {
                        armyDivision.updateArmy(false);
                    }
                }
            }
            catch (final Exception ex) {}
        }
        Game.addSimpleTask(new Game.SimpleTask("rebuildSelectCivilization") {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Right();
                Game.menuManager.rebuildInGame_Messages();
                Game.menuManager.rebuildInGame_Wars();
            }
        });
        Game.menuManager.addToastPositive(Game.lang.get("Civilization") + ": ", Game.getCiv(Game.player.iCivID).getCivName(), Images.v);
    }
    
    static {
        InGame_Sandbox.lTime = 0L;
    }
}
