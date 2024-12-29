// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Battle;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Army;
import aoh.kingdoms.history.mainGame.RomanNumber;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegiment;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.button.ButtonBattleRegimentEmpty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Battle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonArmyIcon_Special;
import aoh.kingdoms.history.menu_element.button.ButtonArmyGeneral2_Armies;
import aoh.kingdoms.history.menu_element.button.ButtonArmyNoGeneral2_Armies;
import aoh.kingdoms.history.menu_element.button.ButtonArmyBattle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.battles.Battle;
import aoh.kingdoms.history.map.battles.BattleManager;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.map.battles.BattleRegiment;
import java.util.List;
import aoh.kingdoms.history.map.army.ArmyDivision;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Battlefield extends Menu
{
    public static ArmyDivision armyDivision;
    public static List<BattleRegiment> firstLine;
    public static List<BattleRegiment> secondLineReg;
    public static int boxH;
    
    public InGame_Battlefield() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        InGame_Battlefield.firstLine.clear();
        InGame_Battlefield.secondLineReg.clear();
        final int menuWidth = (ImageManager.getImage(Images.battleArmy0).getWidth() + 2) * GameValues.battle.BATTLE_MAX_BATTLE_WIDTH + CFG.PADDING * 4;
        int menuHeight = (ImageManager.getImage(Images.battleArmy0).getHeight() + 2) * 2 + CFG.PADDING * 4;
        final int menuX = CFG.GAME_WIDTH / 2 - menuWidth / 2;
        final int menuY = CFG.GAME_HEIGHT / 5 - menuHeight / 2;
        final int paddingLeft = CFG.PADDING * 2;
        final int buttonYPadding = CFG.PADDING;
        int buttonX = paddingLeft;
        int buttonY = 0;
        for (int i = 0; i < GameValues.battle.BATTLE_MAX_BATTLE_WIDTH; ++i) {
            InGame_Battlefield.firstLine.add(null);
            InGame_Battlefield.secondLineReg.add(null);
        }
        final List<BattleRegiment> tempLine0 = new ArrayList<BattleRegiment>();
        final List<BattleRegiment> tempLine2 = new ArrayList<BattleRegiment>();
        final List<BattleRegiment> tempLine3 = new ArrayList<BattleRegiment>();
        for (int j = 0; j < InGame_Battlefield.armyDivision.iArmyRegimentSize; ++j) {
            switch (ArmyManager.lUnitsTypes.get(InGame_Battlefield.armyDivision.lArmyRegiment.get(j).uID).Line) {
                case 0: {
                    tempLine0.add(new BattleRegiment(InGame_Battlefield.armyDivision.civID, InGame_Battlefield.armyDivision.lArmyRegiment.get(j), InGame_Battlefield.armyDivision.key));
                    break;
                }
                case 1: {
                    tempLine2.add(new BattleRegiment(InGame_Battlefield.armyDivision.civID, InGame_Battlefield.armyDivision.lArmyRegiment.get(j), InGame_Battlefield.armyDivision.key));
                    break;
                }
                default: {
                    tempLine3.add(new BattleRegiment(InGame_Battlefield.armyDivision.civID, InGame_Battlefield.armyDivision.lArmyRegiment.get(j), InGame_Battlefield.armyDivision.key));
                    break;
                }
            }
        }
        final int maxWidth = BattleManager.getBattleWidth(InGame_Battlefield.armyDivision.civID);
        final List<ArmyDivision> sideArmyList = new ArrayList<ArmyDivision>();
        sideArmyList.add(InGame_Battlefield.armyDivision);
        final int sideArmyMin = Battle.getArmyInFirstLine(sideArmyList);
        sideArmyList.clear();
        int firstAdded = 0;
        final int sideArmyWidth = Math.min(maxWidth - (int)(maxWidth * GameValues.battle.BATTLE_SIDES_RATIO), sideArmyMin);
        while (firstAdded < maxWidth && (tempLine0.size() > 0 || tempLine2.size() > 0)) {
            final boolean sideArmy = firstAdded >= sideArmyWidth;
            if (!sideArmy && tempLine0.size() > 0) {
                InGame_Battlefield.firstLine.set(BattleManager.FILL_ORDER[firstAdded++], tempLine0.get(0));
                tempLine0.remove(0);
            }
            else if (tempLine2.size() > 0) {
                InGame_Battlefield.firstLine.set(BattleManager.FILL_ORDER[firstAdded++], tempLine2.get(0));
                tempLine2.remove(0);
            }
            else {
                if (tempLine0.size() <= 0) {
                    continue;
                }
                InGame_Battlefield.firstLine.set(BattleManager.FILL_ORDER[firstAdded++], tempLine0.get(0));
                tempLine0.remove(0);
            }
        }
        int secondAdded = 0;
        while (secondAdded < maxWidth && tempLine3.size() > 0) {
            if (tempLine3.size() > 0) {
                if (InGame_Battlefield.firstLine.get(BattleManager.FILL_ORDER[secondAdded]) == null) {
                    InGame_Battlefield.firstLine.set(BattleManager.FILL_ORDER[secondAdded], tempLine3.get(0));
                }
                else {
                    InGame_Battlefield.secondLineReg.set(BattleManager.FILL_ORDER[secondAdded++], tempLine3.get(0));
                }
                tempLine3.remove(0);
            }
        }
        final int maxIconW = ImageManager.getImage(Images.battleWidth).getWidth();
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("ArmyDeployment"), CFG.BUTTON_WIDTH / 4, 0, buttonY, menuWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "" + Game.lang.get("BattleWidth") + ": " + BattleManager.getBattleWidth(InGame_Battlefield.armyDivision.civID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Battlefield.armyDivision.civID, Game.lang.get("BattleWidth") + ": " + BattleManager.getBattleWidth(InGame_Battlefield.armyDivision.civID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleWidthRefersToTheNumberOfUnitsThatCanBeDeployedInAFormationOnTheBattlefield"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Close"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        buttonY += CFG.PADDING;
        try {
            final int extraX = 0;
            final int tTitleY = buttonY;
            final int pinH = (ButtonArmyBattle.getButtonHeight() - CFG.PADDING) / 2;
            buttonX += CFG.PADDING;
            if (InGame_Battlefield.armyDivision.armyGeneral == null) {
                menuElements.add(new ButtonArmyNoGeneral2_Armies(Game.lang.get("NoGeneral"), Game.player.iCivID, buttonX + extraX, buttonY, InGame_Battlefield.armyDivision.key, InGame_Battlefield.armyDivision.civID, InGame_Battlefield.armyDivision.provinceID) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                        super.actionElement();
                    }
                });
            }
            else {
                menuElements.add(new ButtonArmyGeneral2_Armies(InGame_Battlefield.armyDivision.armyGeneral.n, InGame_Battlefield.armyDivision.civID, InGame_Battlefield.armyDivision.armyGeneral.getAttack(), InGame_Battlefield.armyDivision.armyGeneral.getDefense(), buttonX + extraX, buttonY, InGame_Battlefield.armyDivision.armyGeneral.g, InGame_Battlefield.armyDivision.armyGeneral.d, InGame_Battlefield.armyDivision.armyGeneral.m, InGame_Battlefield.armyDivision.armyGeneral.y, InGame_Battlefield.armyDivision.key, InGame_Battlefield.armyDivision.civID, InGame_Battlefield.armyDivision.provinceID, InGame_Battlefield.armyDivision.armyGeneral.sI, InGame_Battlefield.armyDivision.armyGeneral.getCombatExperience()) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                        super.actionElement();
                    }
                });
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            if (InGame_Battlefield.armyDivision.civID == Game.player.iCivID) {
                menuElements.add(new ButtonArmyIcon_Special(Images.pin, buttonX, buttonY, Game.player.isPinned(InGame_Battlefield.armyDivision.key), InGame_Battlefield.armyDivision.key, pinH) {
                    @Override
                    public int getImageID() {
                        return Images.pin;
                    }
                    
                    @Override
                    public void actionElement() {
                        this.checkbox = !this.checkbox;
                        Game.player.actionPinArmy(this.armyKey);
                        Game.menuManager.rebuildInGame_Right();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Pin"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.pin, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    
                    @Override
                    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                        if (!this.checkbox) {
                            if (this.getIsHovered()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                            }
                            else {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
                            }
                        }
                        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                        oSB.setColor(Color.WHITE);
                    }
                });
                menuElements.add(new ButtonArmyIcon_Special(Images.center, buttonX, buttonY + CFG.PADDING + pinH, Game.player.isPinned(InGame_Battlefield.armyDivision.key), InGame_Battlefield.armyDivision.key, pinH) {
                    @Override
                    public int getImageID() {
                        return Images.center;
                    }
                    
                    @Override
                    public void actionElement() {
                        final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(Game.player.iCivID, this.armyKey);
                        if (nArmyPos != null) {
                            Game.mapCoords.centerToProvinceID(nArmyPos.iProvinceID);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CenterToProvince"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.center, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            }
            for (int k = 0; k < InGame_Battlefield.armyDivision.iArmyRegimentSize; ++k) {
                int tUnits = InGame_Battlefield.armyDivision.lArmyRegiment.get(k).num;
                int numOfRegiments = 1;
                for (int o = k + 1; o < InGame_Battlefield.armyDivision.iArmyRegimentSize && InGame_Battlefield.armyDivision.lArmyRegiment.get(k).uID == InGame_Battlefield.armyDivision.lArmyRegiment.get(o).uID && InGame_Battlefield.armyDivision.lArmyRegiment.get(k).aID == InGame_Battlefield.armyDivision.lArmyRegiment.get(o).aID; ++k, ++numOfRegiments, tUnits += InGame_Battlefield.armyDivision.lArmyRegiment.get(k).num, ++o) {}
                menuElements.add(new ButtonArmyBattle("" + tUnits, numOfRegiments, InGame_Battlefield.armyDivision.civID, buttonX + extraX, buttonY, InGame_Battlefield.armyDivision.lArmyRegiment.get(k).uID, InGame_Battlefield.armyDivision.lArmyRegiment.get(k).aID, false) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            }
            buttonY = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            buttonX = paddingLeft;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        InGame_Battlefield.boxH = buttonY;
        buttonY += CFG.PADDING;
        menuElements.add(new Text_StaticBG_Battle(Game.lang.get("Enemies") + ": " + Game.lang.get("SecondLine"), CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Close"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_StaticBG_Battle(Game.lang.get("Enemies") + ": " + Game.lang.get("FirstLine"), CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Close"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        buttonY += CFG.PADDING;
        final int startBattleWidth = GameValues.battle.BATTLE_MAX_BATTLE_WIDTH / 2 - maxWidth / 2;
        for (int l = 0; l < InGame_Battlefield.firstLine.size(); ++l) {
            if (InGame_Battlefield.firstLine.get(l) == null) {
                if (l >= startBattleWidth && l < startBattleWidth + maxWidth) {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {
                        @Override
                        public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                            oSB.setColor(Game.getCiv(InGame_Battlefield.armyDivision.civID).getRGB(0.25f));
                            Images.pix.draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, ImageManager.getImage(this.imageID).getWidth(), ImageManager.getImage(this.imageID).getHeight());
                            oSB.setColor(Color.WHITE);
                            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.getIsHovered() ? 0.4f : 0.25f));
                            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, ImageManager.getImage(this.imageID).getWidth(), ImageManager.getImage(this.imageID).getHeight());
                            oSB.setColor(Color.WHITE);
                            super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                        }
                        
                        @Override
                        public float getImageAlpha(final boolean isActive) {
                            return (this.getIsHovered() || isActive) ? 0.8f : 0.4f;
                        }
                        
                        @Override
                        public void actionElement() {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoUnits"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                }
                else {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {
                        @Override
                        public void actionElement() {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NotAvailable"), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Battlefield.armyDivision.civID, 0, CFG.PADDING));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Battlefield.armyDivision.civID).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattleWidth") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + BattleManager.getBattleWidth(InGame_Battlefield.armyDivision.civID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.battleWidth, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("DeploymentPhase2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                }
            }
            else {
                menuElements.add(new ButtonBattleRegiment(InGame_Battlefield.armyDivision.civID, InGame_Battle.getImageRegimentID(ArmyManager.lUnitsTypes.get(InGame_Battlefield.firstLine.get(l).aR.uID).Line), buttonX, buttonY, l, 0, ArmyManager.lArmy.get(InGame_Battlefield.firstLine.get(l).aR.uID).get(InGame_Battlefield.firstLine.get(l).aR.aID).AttackRange, false, true) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_Battlefield.this.getHover(InGame_Battlefield.firstLine.get(this.id));
                    }
                });
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        buttonX = paddingLeft;
        for (int l = 0; l < InGame_Battlefield.secondLineReg.size(); ++l) {
            if (InGame_Battlefield.secondLineReg.get(l) == null) {
                if (l >= startBattleWidth && l < startBattleWidth + maxWidth) {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {
                        @Override
                        public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                            oSB.setColor(Game.getCiv(InGame_Battlefield.armyDivision.civID).getRGB(0.25f));
                            Images.pix.draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, ImageManager.getImage(this.imageID).getWidth(), ImageManager.getImage(this.imageID).getHeight());
                            oSB.setColor(Color.WHITE);
                            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, this.getIsHovered() ? 0.4f : 0.25f));
                            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, ImageManager.getImage(this.imageID).getWidth(), ImageManager.getImage(this.imageID).getHeight());
                            oSB.setColor(Color.WHITE);
                            super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                        }
                        
                        @Override
                        public float getImageAlpha(final boolean isActive) {
                            return (this.getIsHovered() || isActive) ? 0.8f : 0.4f;
                        }
                        
                        @Override
                        public void actionElement() {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoUnits"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                }
                else {
                    menuElements.add(new ButtonBattleRegimentEmpty(Images.battleArmy2, buttonX, buttonY) {
                        @Override
                        public void actionElement() {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NotAvailable"), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Battlefield.armyDivision.civID, 0, CFG.PADDING));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Battlefield.armyDivision.civID).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattleWidth") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + BattleManager.getBattleWidth(InGame_Battlefield.armyDivision.civID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.battleWidth, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("DeploymentPhase2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                }
            }
            else {
                menuElements.add(new ButtonBattleRegiment(InGame_Battlefield.armyDivision.civID, InGame_Battle.getImageRegimentID(ArmyManager.lUnitsTypes.get(InGame_Battlefield.secondLineReg.get(l).aR.uID).Line), buttonX, buttonY, l, 0, ArmyManager.lArmy.get(InGame_Battlefield.secondLineReg.get(l).aR.uID).get(InGame_Battlefield.secondLineReg.get(l).aR.aID).AttackRange, true, true) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_Battlefield.this.getHover(InGame_Battlefield.secondLineReg.get(this.id));
                    }
                });
            }
            buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        tempLine0.clear();
        tempLine2.clear();
        tempLine3.clear();
        buttonY = 0;
        for (int l = 0, iSize = menuElements.size(); l < iSize; ++l) {
            if (buttonY < menuElements.get(l).getPosY() + menuElements.get(l).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(l).getPosY() + menuElements.get(l).getHeight() + CFG.PADDING * 3;
            }
        }
        menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.get(menuElements.size() - 1).setClickable(true);
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.85f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, menuIsActive);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.battleOver).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.battleOver).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.battleOver).getHeight() / 2 + iTranslateY);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.7f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), InGame_Battlefield.boxH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.7f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), InGame_Battlefield.boxH, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + InGame_Battlefield.boxH - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2, CFG.PADDING, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, menuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    public MenuElement_Hover getHover(final BattleRegiment battleRegiment) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(battleRegiment.c, RomanNumber.getRoman(battleRegiment.rn) + "" + Game.lang.get("Regiment")));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Army("" + battleRegiment.aR.num, battleRegiment.aR.uID, battleRegiment.aR.aID, battleRegiment.c));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AttackRange") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ArmyManager.lArmy.get(battleRegiment.aR.uID).get(battleRegiment.aR.aID).AttackRange, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SiegeAbility") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.lArmy.get(battleRegiment.aR.uID).get(battleRegiment.aR.aID).SiegeProgress, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.siege, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    static {
        InGame_Battlefield.armyDivision = new ArmyDivision();
        InGame_Battlefield.firstLine = new ArrayList<BattleRegiment>();
        InGame_Battlefield.secondLineReg = new ArrayList<BattleRegiment>();
    }
}
