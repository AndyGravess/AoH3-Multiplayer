// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.AllianceSpecial;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter_HRE;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTree;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu_element.button.Button_HRE_Reform_Red;
import aoh.kingdoms.history.map.allianceHRE.HREManager;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.button.Button_HRE_Reform;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_AllianceSpecialReformHRE extends Menu
{
    public static int allianceID;
    public static int reformID;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_AllianceSpecialReformHRE(final int nAllianceID, final int nReformID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        InGame_AllianceSpecialReformHRE.allianceID = nAllianceID;
        InGame_AllianceSpecialReformHRE.reformID = nReformID;
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = CFG.PADDING * 2;
        final int buttonX = paddingLeft;
        if (InGame_AllianceSpecialReformHRE.reformID == Game.alliancesSpecial.get(InGame_AllianceSpecialReformHRE.allianceID).iReformsPassed) {
            menuElements.add(new Button_HRE_Reform(Game.lang.get("Reform" + InGame_AllianceSpecialReformHRE.reformID), Game.lang.get("Reform" + InGame_AllianceSpecialReformHRE.reformID + ".d"), paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, InGame_AllianceSpecialReformHRE.reformID, InGame_AllianceSpecialReformHRE.reformID) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35 && InGame_AllianceSpecial.allianceID == InGame_AllianceSpecialReformHRE.allianceID) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_AllianceSpecial(InGame_AllianceSpecialReformHRE.allianceID);
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = HREManager.getHoverReform(InGame_AllianceSpecialReformHRE.allianceID, this.reformID);
                }
            });
        }
        else {
            menuElements.add(new Button_HRE_Reform_Red(Game.lang.get("Reform" + InGame_AllianceSpecialReformHRE.reformID), Game.lang.get("Reform" + InGame_AllianceSpecialReformHRE.reformID + ".d"), paddingLeft + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING * 2, InGame_AllianceSpecialReformHRE.reformID, InGame_AllianceSpecialReformHRE.reformID) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35 && InGame_AllianceSpecial.allianceID == InGame_AllianceSpecialReformHRE.allianceID) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_AllianceSpecial(InGame_AllianceSpecialReformHRE.allianceID);
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = HREManager.getHoverReform(InGame_AllianceSpecialReformHRE.allianceID, this.reformID);
                }
            });
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("ReformRequirements"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        if (GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[InGame_AllianceSpecialReformHRE.reformID] >= 0) {
            menuElements.add(new TextBonus(Game.lang.get("RequiredTechnology") + ": ", "" + TechnologyTree.lTechnology.get(GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[InGame_AllianceSpecialReformHRE.reformID]).Name, Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                        Game.menuManager.setVisibleInGame_TechnologyTree(false);
                    }
                    else {
                        Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                        InGame_TechnologyTree.centerToTechID = GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[InGame_AllianceSpecialReformHRE.reformID];
                        Game.menuManager.rebuildInGame_TechnologyTree(false, true);
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (GameValues.hre.HRE_REFORM_COST_GOLD[InGame_AllianceSpecialReformHRE.reformID] > 0.0f) {
            menuElements.add(new TextBonus(Game.lang.get("Gold") + ": ", "" + CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_GOLD[InGame_AllianceSpecialReformHRE.reformID], 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {});
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new TextBonus("", "" + CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_DIPLOMACY[InGame_AllianceSpecialReformHRE.reformID], 10), Images.diplomacy, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("DiplomacyPoints") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(this.sText2, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(this.imageID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextBonus("", "" + CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_LEGACY[InGame_AllianceSpecialReformHRE.reformID], 10), Images.legacy, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(this.sText2, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(this.imageID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35 && InGame_AllianceSpecial.allianceID == InGame_AllianceSpecialReformHRE.allianceID) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_AllianceSpecial(InGame_AllianceSpecialReformHRE.allianceID);
                }
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("PassReform"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, !HREManager.passReform_ReformsPassed(InGame_AllianceSpecialReformHRE.allianceID, InGame_AllianceSpecialReformHRE.reformID)) {
            @Override
            public void actionElement() {
                InGame_AllianceSpecialReformHRE.confirm();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = HREManager.getHoverReform(InGame_AllianceSpecialReformHRE.allianceID, InGame_AllianceSpecialReformHRE.reformID);
            }
            
            @Override
            public boolean getClickable() {
                return super.getClickable() && Game.player.iCivID == Game.alliancesSpecial.get(InGame_AllianceSpecialReformHRE.allianceID).iLeaderCivID;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter_HRE(InGame_AllianceSpecialReformHRE.allianceID, Game.lang.get("Reform" + InGame_AllianceSpecialReformHRE.reformID), Game.lang.get("Reform"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_AllianceSpecialReformHRE.lTime;
            }
            
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_AllianceSpecialReformHRE.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_AllianceSpecialReformHRE.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_AllianceSpecialReformHRE.lTime = CFG.currentTimeMillis;
    }
    
    public static void confirm() {
        if (Game.alliancesSpecial.get(InGame_AllianceSpecialReformHRE.allianceID).iLeaderCivID == Game.player.iCivID) {
            if (HREManager.passReform_Legacy(InGame_AllianceSpecialReformHRE.allianceID, InGame_AllianceSpecialReformHRE.reformID)) {
                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_LEGACY[InGame_AllianceSpecialReformHRE.reformID], 100), Images.legacy);
            }
            else if (HREManager.passReform_Diplomacy(InGame_AllianceSpecialReformHRE.allianceID, InGame_AllianceSpecialReformHRE.reformID)) {
                Game.menuManager.addToastInsufficient(Game.lang.get("DiplomacyPoints") + ": ", CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_DIPLOMACY[InGame_AllianceSpecialReformHRE.reformID], 100), Images.diplomacy);
            }
            else if (HREManager.passReform_RequiredTech(InGame_AllianceSpecialReformHRE.allianceID, InGame_AllianceSpecialReformHRE.reformID)) {
                Game.menuManager.addToastInsufficient(Game.lang.get("RequiredTechnology") + ": ", TechnologyTree.lTechnology.get(GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[InGame_AllianceSpecialReformHRE.reformID]).Name, Game_Calendar.IMG_TECHNOLOGY);
            }
            else if (HREManager.passReform(InGame_AllianceSpecialReformHRE.allianceID, InGame_AllianceSpecialReformHRE.reformID)) {
                Game.menuManager.rebuildInGame_AllianceSpecial(InGame_AllianceSpecialReformHRE.allianceID);
                InGame_Info.iCivID = Game.player.iCivID;
                InGame_Info.iCivID2 = 0;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("ReformPassed"), Game.lang.get("Reform" + InGame_AllianceSpecialReformHRE.reformID));
                InGame_Info.imgID = Images.infoCrown;
            }
        }
    }
    
    static {
        InGame_AllianceSpecialReformHRE.allianceID = 0;
        InGame_AllianceSpecialReformHRE.reformID = 0;
        InGame_AllianceSpecialReformHRE.lTime = 0L;
    }
}
