// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Battle;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_Terrain;
import aoh.kingdoms.history.map.terrain.Terrain;
import aoh.kingdoms.history.map.battles.Battle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegimentDefeated;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegimentEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegiment;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.menu_element.textStatic.TextFlagsCasualties;
import aoh.kingdoms.history.menu_element.textStatic.TextFlags;
import aoh.kingdoms.history.menu_element.button.ButtonArmyNoGeneral2;
import aoh.kingdoms.history.menu_element.textStatic.TextIconScaled;
import aoh.kingdoms.history.menu_element.button.ButtonArmyGeneral2;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.battles.BattleManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonFlag2;
import aoh.kingdoms.history.map.battles.BattleRegiment;
import aoh.kingdoms.history.mainGame.GameValues;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Battle extends Menu
{
    public static int nTranslateX;
    public static int nTranslateY;
    public static int HOVER_POSX;
    public static int HOVER_POSY;
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static String key;
    public static int iProvinceID;
    public int regimentElementID;
    public static boolean enableAnimation;
    public static List<Integer> battlePosY;
    public static List<Integer> offsetDefY;
    public static List<Integer> offsetAttY;
    public int topY;
    public int bottomY;
    public static int battleID;
    public static int TURN_ID;
    public static String sDay;
    public static int iDayWidth;
    
    public InGame_Battle() {
        this.regimentElementID = 0;
        this.topY = 0;
        this.bottomY = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title630).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title630).getWidth() - Images.boxTitleBORDERWIDTH;
        final int menuMinHeight = ImageManager.getImage(Images.battleOver).getHeight();
        int menuHeight = 240;
        final int menuX = 0;
        int menuY = CFG.GAME_HEIGHT - menuHeight;
        final int buttonYPadding = CFG.PADDING * 2;
        final int generalPadding = CFG.PADDING * 3;
        int buttonX = 0;
        int buttonY = buttonYPadding;
        InGame_Battle.battleID = Game.battleManager.getBattleID(InGame_Battle.key);
        InGame_Battle.TURN_ID = Game_Calendar.TURN_ID;
        if (InGame_Battle.battleID >= 0) {
            InGame_Battle.sDay = Game_Calendar.getNumOfDates_ByTurnID(Game_Calendar.TURN_ID - Game.battleManager.getBattle(InGame_Battle.battleID).roundID - 1) + ((Game.battleManager.getBattle(InGame_Battle.battleID).roundID == 0) ? (" - " + Game.lang.get("ArmyDeployment")) : "");
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), InGame_Battle.sDay);
            InGame_Battle.iDayWidth = (int)Renderer.glyphLayout.width;
            if (InGame_Battle.offsetDefY.size() == 0) {
                for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
                    InGame_Battle.offsetDefY.add(0);
                    InGame_Battle.offsetAttY.add(0);
                }
            }
            InGame_Battle.battlePosY.clear();
            for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i) != null && Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i) != null) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.num > Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.num) {
                        InGame_Battle.battlePosY.add((int)(getMiddleHeight() / 2.0f + getMiddleHeight() / 2.0f * (1.0f - Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.num / (float)Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.num)));
                    }
                    else {
                        InGame_Battle.battlePosY.add((int)(getMiddleHeight() / 2 + getMiddleHeight() / 2.0f * -(1.0f - Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(i).aR.num / (float)Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(i).aR.num)));
                    }
                }
                else {
                    InGame_Battle.battlePosY.add((int)(getMiddleHeight() * 0.5f));
                }
            }
            buttonX = generalPadding;
            menuElements.add(new ButtonFlag2(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iCivID, buttonX, buttonY, true) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    if (this.getFlagCivID() < 0) {
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, 0, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Rebels"), Colors.HOVER_GOLD));
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Flag(this.getFlagCivID(), 0, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(this.getFlagCivID()).getCivName(), Colors.HOVER_GOLD));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattleWidth") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + BattleManager.getBattleWidth(this.getFlagCivID()), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.battleWidth, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new TextIconScaled(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.numOfUnits), Game_Calendar.IMG_MANPOWER, buttonX, buttonY + CFG.PADDING + ImageManager.getImage(Images.flagOverDefault).getHeight(), ImageManager.getImage(Images.flagOverDefault).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - ImageManager.getImage(Images.flagOverDefault).getHeight() - CFG.PADDING) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NumberOfUnits") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral == null) {
                menuElements.add(new ButtonArmyNoGeneral2(Game.lang.get("NoGeneral"), Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iCivID, buttonX, buttonY, false) {
                    @Override
                    public void actionElement() {
                        InGame_Battle.this.battleFull();
                    }
                });
            }
            else {
                menuElements.add(new ButtonArmyGeneral2(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.n, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iCivID, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.getAttack(), Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.getDefense(), buttonX, buttonY, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.g, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.d, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.m, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.y, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.sI, Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.armyGeneral.getCombatExperience()) {
                    @Override
                    public void buildElementHover() {
                        this.buildElementHover2();
                    }
                    
                    @Override
                    public void actionElement() {
                        InGame_Battle.this.battleFull();
                    }
                });
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIconScaled("" + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.diceRollGeneral, Images.dice, buttonX, buttonY, ImageManager.getImage(Images.unitsFrameBattle).getWidth(), (ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - CFG.PADDING) / 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiceRoll") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Text(" / " + GameValues.battle.BATTLE_MAX_DICE_ROLL, CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.dice, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new TextIconScaled(CFG.getPrecision2(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.fMorale * 100.0f, 1) + "%", Images.morale, buttonX, buttonY + CFG.PADDING + (ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - CFG.PADDING) / 2, ImageManager.getImage(Images.unitsFrameBattle).getWidth(), (ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - CFG.PADDING) / 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMoraleOnTheBattlefield") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.morale, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            buttonX = generalPadding;
            final List<Integer> tAttackingCivs = new ArrayList<Integer>();
            for (int j = 0, jSize = Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.lCivs.size(); j < jSize; ++j) {
                tAttackingCivs.add(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.lCivs.get(j));
            }
            menuElements.add(new TextFlags(Game.lang.get("Attackers") + ":", tAttackingCivs, buttonX, buttonY, 50, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                @Override
                public void setWidth(final int iWidth) {
                    super.setWidth(Math.max(ImageManager.getImage(Images.unitsFrameBattle).getWidth() + ImageManager.getImage(Images.generalFrameBattle).getWidth() + CFG.PADDING * 2 + ImageManager.getImage(Images.flagOverDefault).getWidth(), iWidth));
                }
            });
            int nXExtra = menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextFlagsCasualties(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.numOfUnitsOnBattlefield), CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.inReserve), CFG.getNumberWithSpaces("" + (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iCasualties + Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.iRetreated)), buttonX + nXExtra, buttonY, menuWidth - buttonX * 2 - nXExtra, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false));
            this.topY = buttonY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3;
            buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
            for (int k = 0; k < Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.size(); ++k) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(k) != null) {
                    menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(k).c, getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(k).aR.uID).Line), buttonX, buttonY, k, 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(k).aR.uID).get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.secondLine.get(k).aR.aID).AttackRange, true, false) {
                        @Override
                        public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                            return Game.battleManager.getBattle(battleID).attackingArmy.secondLine.get(id);
                        }
                        
                        @Override
                        public void actionElement() {
                            InGame_Battle.this.updateAnimationStatus();
                        }
                    });
                }
                else {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {
                        @Override
                        public void actionElement() {
                            InGame_Battle.this.updateAnimationStatus();
                        }
                    });
                }
                buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
            }
            buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
            buttonY += ImageManager.getImage(Images.battleArmy0).getHeight() + 2;
            this.regimentElementID = menuElements.size();
            for (int k = 0; k < Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.size(); ++k) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k) != null) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).aR.num > 0) {
                        menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).c, getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).aR.uID).Line), buttonX, buttonY, k, InGame_Battle.enableAnimation ? InGame_Battle.offsetAttY.get(k) : 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).aR.uID).get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).aR.aID).AttackRange, false, false) {
                            @Override
                            public void actionElement() {
                                InGame_Battle.this.updateAnimationStatus();
                            }
                        });
                    }
                    else {
                        menuElements.add(new ButtonBattleRegimentDefeated(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).c, getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle.battleID).attackingArmy.firstLine.get(k).aR.uID).Line), buttonX, buttonY, k, InGame_Battle.enableAnimation ? InGame_Battle.offsetAttY.get(k) : 0) {
                            @Override
                            public void actionElement() {
                                InGame_Battle.this.updateAnimationStatus();
                            }
                        });
                    }
                }
                else {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy0, buttonX, buttonY));
                }
                buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
            }
            buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
            buttonY += getMiddleHeight() + ImageManager.getImage(Images.battleArmy0).getHeight() + 1;
            for (int k = 0; k < Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.size(); ++k) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k) != null) {
                    if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).aR.num > 0) {
                        menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).c, getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).aR.uID).Line), buttonX, buttonY, k, InGame_Battle.enableAnimation ? InGame_Battle.offsetDefY.get(k) : 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).aR.uID).get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).aR.aID).AttackRange, false, true) {
                            @Override
                            public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                                return Game.battleManager.getBattle(battleID).defendingArmy.firstLine.get(id);
                            }
                            
                            @Override
                            public void actionElement() {
                                InGame_Battle.this.updateAnimationStatus();
                            }
                        });
                    }
                    else {
                        menuElements.add(new ButtonBattleRegimentDefeated(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).c, getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.firstLine.get(k).aR.uID).Line), buttonX, buttonY, k, InGame_Battle.enableAnimation ? InGame_Battle.offsetDefY.get(k) : 0) {
                            @Override
                            public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                                return Game.battleManager.getBattle(battleID).defendingArmy.firstLine.get(id);
                            }
                            
                            @Override
                            public void actionElement() {
                                InGame_Battle.this.updateAnimationStatus();
                            }
                        });
                    }
                }
                else {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy0, buttonX, buttonY));
                }
                buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
            }
            buttonX = menuWidth / 2 - (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH * ImageManager.getImage(Images.battleArmy0).getWidth() + 1 * (GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + 1)) / 2 + 1;
            buttonY += ImageManager.getImage(Images.battleArmy0).getHeight() + 2;
            for (int k = 0; k < Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.size(); ++k) {
                if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(k) != null) {
                    menuElements.add(new ButtonBattleRegiment(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(k).c, getImageRegimentID(ArmyManager.lUnitsTypes.get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(k).aR.uID).Line), buttonX, buttonY, k, 0, ArmyManager.lArmy.get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(k).aR.uID).get(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.secondLine.get(k).aR.aID).AttackRange, true, true) {
                        @Override
                        public BattleRegiment getBattleRegiment(final int battleID, final int id) {
                            return Game.battleManager.getBattle(battleID).defendingArmy.secondLine.get(id);
                        }
                        
                        @Override
                        public void actionElement() {
                            InGame_Battle.this.updateAnimationStatus();
                        }
                    });
                }
                else {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {
                        @Override
                        public void actionElement() {
                            InGame_Battle.this.updateAnimationStatus();
                        }
                    });
                }
                buttonX += ImageManager.getImage(Images.battleArmy0).getWidth() + 1;
            }
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3;
            buttonX = generalPadding;
            final List<Integer> tDefendingCivs = new ArrayList<Integer>();
            for (int l = 0, jSize2 = Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.lCivs.size(); l < jSize2; ++l) {
                tDefendingCivs.add(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.lCivs.get(l));
            }
            menuElements.add(new TextFlags(Game.lang.get("Defenders") + ":", tDefendingCivs, buttonX, buttonY, 50, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                @Override
                public void setWidth(final int iWidth) {
                    super.setWidth(Math.max(ImageManager.getImage(Images.unitsFrameBattle).getWidth() + ImageManager.getImage(Images.generalFrameBattle).getWidth() + CFG.PADDING * 2 + ImageManager.getImage(Images.flagOverDefault).getWidth(), iWidth));
                }
            });
            nXExtra = menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextFlagsCasualties(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.numOfUnitsOnBattlefield), CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.inReserve), CFG.getNumberWithSpaces("" + (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iCasualties + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iRetreated)), buttonX + nXExtra, buttonY, menuWidth - buttonX * 2 - nXExtra, CFG.TEXT_HEIGHT + CFG.PADDING * 2, true));
            this.bottomY = buttonY - CFG.PADDING;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            buttonX = generalPadding;
            menuElements.add(new ButtonFlag2(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iCivID, buttonX, buttonY, true) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    if (this.getFlagCivID() < 0) {
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, 0, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Rebels"), Colors.HOVER_GOLD));
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Flag(this.getFlagCivID(), 0, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(this.getFlagCivID()).getCivName(), Colors.HOVER_GOLD));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattleWidth") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + BattleManager.getBattleWidth(this.getFlagCivID()), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.battleWidth, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new TextIconScaled(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.numOfUnits), Game_Calendar.IMG_MANPOWER, buttonX, buttonY + CFG.PADDING + ImageManager.getImage(Images.flagOverDefault).getHeight(), ImageManager.getImage(Images.flagOverDefault).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - ImageManager.getImage(Images.flagOverDefault).getHeight() - CFG.PADDING) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NumberOfUnits") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            if (Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral == null) {
                menuElements.add(new ButtonArmyNoGeneral2(Game.lang.get("NoGeneral"), Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iCivID, buttonX, buttonY, false) {
                    @Override
                    public void actionElement() {
                        InGame_Battle.this.battleFull();
                    }
                });
            }
            else {
                menuElements.add(new ButtonArmyGeneral2(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.n, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.iCivID, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.getAttack(), Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.getDefense(), buttonX, buttonY, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.g, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.d, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.m, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.y, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.sI, Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.armyGeneral.getCombatExperience()) {
                    @Override
                    public void buildElementHover() {
                        this.buildElementHover2();
                    }
                    
                    @Override
                    public void actionElement() {
                        InGame_Battle.this.battleFull();
                    }
                });
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIconScaled("" + Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.diceRollGeneral, Images.dice, buttonX, buttonY, ImageManager.getImage(Images.unitsFrameBattle).getWidth(), (ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - CFG.PADDING) / 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiceRoll") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Text(" / " + GameValues.battle.BATTLE_MAX_DICE_ROLL, CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.dice, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new TextIconScaled(CFG.getPrecision2(Game.battleManager.getBattle(InGame_Battle.battleID).defendingArmy.fMorale * 100.0f, 1) + "%", Images.morale, buttonX, buttonY + CFG.PADDING + (ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - CFG.PADDING) / 2, ImageManager.getImage(Images.unitsFrameBattle).getWidth(), (ImageManager.getImage(Images.generalFrameBattle).getHeight() + ButtonArmyGeneral2.getStatsHeight() - CFG.PADDING) / 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMoraleOnTheBattlefield") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.morale, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX = (InGame_BattleArmy.mPosX = buttonX + (menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING));
            InGame_BattleArmy.mPosY = buttonY;
            InGame_BattleArmy.mWidth = menuWidth - buttonX - generalPadding;
            InGame_BattleArmyDefenders.mPosX = buttonX;
            InGame_BattleArmyDefenders.mPosY = CFG.PADDING;
            InGame_BattleArmyDefenders.mWidth = menuWidth - buttonX - generalPadding;
        }
        else if (Game.activeArmySize > 0) {
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_ProvinceArmy") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_ProvinceArmy();
                }
            });
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        menuHeight = Math.max(menuMinHeight, buttonY);
        menuY = CFG.GAME_HEIGHT - menuHeight;
        InGame_BattleArmy.mPosY += menuY;
        InGame_BattleArmyDefenders.mPosY += menuY;
        InGame_Battle.HOVER_POSX = menuX + menuWidth + Images.boxTitleBORDERWIDTH + CFG.PADDING;
        InGame_Battle.HOVER_POSY = menuY;
        menuElements.add(new Empty(0, 0, menuWidth, menuHeight));
        this.initMenu(new MenuTitleIMG_Terrain(Game.lang.get("BattleOf", Game.getProvince(InGame_Battle.iProvinceID).getProvinceName()), (Battle.getDefendersProvinceBonuses(InGame_Battle.iProvinceID) != 0) ? (Game.lang.get("Defense") + ": +" + Battle.getDefendersProvinceBonuses(InGame_Battle.iProvinceID)) : Game.terrainManager.terrains.get(Game.getProvince(InGame_Battle.iProvinceID).getTerrainID()).Name, false, false, Images.title630, Game.getProvince(InGame_Battle.iProvinceID).getTerrainID(), InGame_Battle.iProvinceID) {
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
                super.draw(oSB, nPosX, nPosY, nWidth + Images.boxTitleBORDERWIDTH, titleStatus);
            }
            
            @Override
            public long getTime() {
                return InGame_Battle.lTime;
            }
            
            @Override
            public void onHovered() {
                Game.menuManager.setOrderOfMenu_InGameBattle();
            }
            
            @Override
            public void action() {
                Game.menuManager.setOrderOfMenu_InGameBattle();
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Battle.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Battle.lTime) / 60.0f));
            iTranslateY += (int)(CFG.BUTTON_HEIGHT / 2.0f - CFG.BUTTON_HEIGHT / 2 * ((CFG.currentTimeMillis - InGame_Battle.lTime) / 60.0f));
            if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).BATTLE_SOUND_2) {
                Game.soundsManager.playSound(SoundsManager.SOUND_BATTLE2, Game.soundsManager.getSoundsVolumeMaster());
            }
            else {
                Game.soundsManager.playSound(SoundsManager.SOUND_BATTLE, Game.soundsManager.getSoundsVolumeMaster());
            }
        }
        InGame_Battle.nTranslateX = iTranslateX;
        InGame_Battle.nTranslateY = iTranslateY;
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH, this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH, this.getHeight() + CFG.PADDING, false, Images.insideTop630, Images.insideBot630);
        ImageManager.getImage(Game.terrainManager.getBattleTerrain(Game.getProvince(InGame_Battle.iProvinceID).getTerrainID())).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.battleOver).getHeight() / 2 + iTranslateY);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.topY, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.bottomY + iTranslateY, this.getWidth(), this.getHeight() - this.bottomY);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.topY - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.bottomY + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        Renderer.drawLine(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.bottomY + iTranslateY, this.getWidth());
        Renderer.drawLine(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.topY - 2 + iTranslateY, this.getWidth());
        if (InGame_Battle.enableAnimation) {
            this.drawAnimation();
        }
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        if (InGame_Battle.TURN_ID != Game_Calendar.TURN_ID) {
            InGame_Battle.TURN_ID = Game_Calendar.TURN_ID;
            Game.addSimpleTask(new Game.SimpleTask("rebuildBattleView") {
                @Override
                public void update() {
                    if (Game.menuManager.getInGame() && Game.menuManager.getVisibleInGame_Battle()) {
                        Game.menuManager.rebuildInGame_Battle();
                    }
                }
            });
        }
    }
    
    public final void drawAnimation() {
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            InGame_Battle.offsetAttY.set(i, InGame_Battle.offsetAttY.get(i) + ((InGame_Battle.offsetAttY.get(i) > InGame_Battle.battlePosY.get(i)) ? -1 : 1));
            InGame_Battle.offsetDefY.set(i, InGame_Battle.offsetDefY.get(i) + ((Math.abs(InGame_Battle.offsetDefY.get(i)) > getMiddleHeight() - InGame_Battle.battlePosY.get(i)) ? 1 : -1));
            if (this.getMenuElement(this.regimentElementID + i).getCurrent() != InGame_Battle.battlePosY.get(i)) {
                this.getMenuElement(this.regimentElementID + i).setCurrent(InGame_Battle.offsetAttY.get(i));
            }
            if (Math.abs(this.getMenuElement(this.regimentElementID + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + i).getCurrent()) != getMiddleHeight() - InGame_Battle.battlePosY.get(i)) {
                this.getMenuElement(this.regimentElementID + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + i).setCurrent(InGame_Battle.offsetDefY.get(i));
            }
        }
    }
    
    @Override
    public void setVisible(final boolean visible) {
        if (!this.getVisible()) {
            InGame_Battle.lTime = CFG.currentTimeMillis;
        }
        super.setVisible(visible);
    }
    
    public static int getImageRegimentID(final int iLine) {
        switch (iLine) {
            case 1: {
                return Images.battleArmy1;
            }
            case 2: {
                return Images.battleArmy2;
            }
            default: {
                return Images.battleArmy0;
            }
        }
    }
    
    public static int getMiddleHeight() {
        return ImageManager.getImage(Images.battleArmy0).getHeight();
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameBattle();
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_Battle(false);
    }
    
    public void updateAnimationStatus() {
        if (!(InGame_Battle.enableAnimation = !InGame_Battle.enableAnimation)) {
            for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
                this.getMenuElement(this.regimentElementID + i).setCurrent(0);
                this.getMenuElement(this.regimentElementID + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + i).setCurrent(0);
            }
        }
        else {
            for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
                InGame_Battle.offsetDefY.set(i, 0);
                InGame_Battle.offsetAttY.set(i, 0);
            }
        }
    }
    
    public void battleFull() {
    }
    
    static {
        InGame_Battle.HOVER_POSX = 0;
        InGame_Battle.HOVER_POSY = 0;
        InGame_Battle.lTime = 0L;
        InGame_Battle.key = "";
        InGame_Battle.iProvinceID = 0;
        InGame_Battle.enableAnimation = true;
        InGame_Battle.battlePosY = new ArrayList<Integer>();
        InGame_Battle.offsetDefY = new ArrayList<Integer>();
        InGame_Battle.offsetAttY = new ArrayList<Integer>();
        InGame_Battle.battleID = -1;
        InGame_Battle.TURN_ID = 0;
        InGame_Battle.sDay = "";
    }
}
