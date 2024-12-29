// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonReorganize;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import aoh.kingdoms.history.menu_element.button.ButtonArmy;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyDivision;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Disband extends Menu
{
    public static boolean restartAnimation;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static ArmyDivision armyLeft;
    public static ArmyDivision armyRight;
    
    public InGame_Disband() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 4;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        int buttonX = menuWidth / 4 - ButtonArmy.getButtonWidth() / 2;
        int numOfUnits = 0;
        for (int i = 0; i < InGame_Disband.armyLeft.iArmyRegimentSize; ++i) {
            numOfUnits += InGame_Disband.armyLeft.lArmyRegiment.get(i).num;
        }
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                InGame_Disband.this.setVisible(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonReorganize(Game.lang.get("Army"), "" + numOfUnits, 0, buttonY, menuWidth / 2) {
            @Override
            public void actionElement() {
                for (int i = InGame_Disband.armyLeft.iArmyRegimentSize - 1; i >= 0; --i) {
                    InGame_Disband.armyRight.addRegiment(InGame_Disband.armyLeft.lArmyRegiment.get(i));
                    InGame_Disband.armyLeft.removeRegiment(i);
                }
                Game.menuManager.rebuildInGame_DisbandUnits();
                InGame_Disband.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                int numOfUnits = 0;
                for (int i = 0; i < InGame_Disband.armyLeft.iArmyRegimentSize; ++i) {
                    numOfUnits += InGame_Disband.armyLeft.lArmyRegiment.get(i).num;
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("DisbandUnits") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + numOfUnits, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getManpowerRecoveryFromADisbandedArmy(Game.player.iCivID) * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_DISBAND, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < InGame_Disband.armyLeft.iArmyRegimentSize; ++i) {
            menuElements.add(new ButtonArmy("" + InGame_Disband.armyLeft.lArmyRegiment.get(i).num, 1, InGame_Disband.armyLeft.civID, buttonX, buttonY, InGame_Disband.armyLeft.lArmyRegiment.get(i).uID, InGame_Disband.armyLeft.lArmyRegiment.get(i).aID, i, -1) {
                @Override
                public void actionElement() {
                    InGame_Disband.armyRight.addRegiment(InGame_Disband.armyLeft.lArmyRegiment.get(this.getCurrent()));
                    InGame_Disband.armyLeft.removeRegiment(this.getCurrent());
                    Game.menuManager.rebuildInGame_DisbandUnits();
                    InGame_Disband.lTime = 0L;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        buttonX = menuWidth / 2 + menuWidth / 4 - ButtonArmy.getButtonWidth() / 2;
        buttonY = buttonYPadding;
        numOfUnits = 0;
        for (int i = 0; i < InGame_Disband.armyRight.iArmyRegimentSize; ++i) {
            numOfUnits += InGame_Disband.armyRight.lArmyRegiment.get(i).num;
        }
        menuElements.add(new ButtonGame(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                if (InGame_Disband.armyRight.iArmyRegimentSize > 0) {
                    if (!Game.getProvince(InGame_Disband.armyLeft.provinceID).disbandRegiment(InGame_Disband.armyLeft.key, InGame_Disband.armyRight.lArmyRegiment)) {
                        Game.menuManager.addToast_Error("ArmyNotFound");
                    }
                    else if (InGame_Disband.armyLeft.key != null) {
                        Game.setActiveArmy(InGame_Disband.armyLeft.provinceID, InGame_Disband.armyLeft.key);
                    }
                    else {
                        Game.clearActiveArmy();
                    }
                    InGame_Disband.this.setVisible(false);
                    if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                        Game.menuManager.rebuildInGame_ProvinceArmy();
                    }
                    if (Game.menuManager.getVisibleInGame_Generals()) {
                        Game.menuManager.rebuildInGame_Generals();
                        Game.menuManager.setVisibleInGame_Generals(true);
                        InGame_Generals.lTime = 0L;
                    }
                    if (Game.menuManager.getVisibleInGame_Armies()) {
                        Game.menuManager.rebuildInGame_Armies(false, false);
                        Game.menuManager.setVisibleInGame_Armies(true);
                        InGame_Armies.lTime = 0L;
                    }
                }
            }
            
            @Override
            public boolean getClickable() {
                return super.getClickable() && InGame_Disband.armyRight.iArmyRegimentSize > 0;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                int numOfUnits = 0;
                for (int i = 0; i < InGame_Disband.armyLeft.iArmyRegimentSize; ++i) {
                    numOfUnits += InGame_Disband.armyLeft.lArmyRegiment.get(i).num;
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(Game.getManpowerRecoveryFromADisbandedArmy(Game.player.iCivID) * 100.0f, 10) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER_DISBAND, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonReorganize(Game.lang.get("Disband"), "" + numOfUnits, menuWidth / 2, buttonY, menuWidth / 2) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
            }
            
            @Override
            public void actionElement() {
                for (int i = InGame_Disband.armyRight.iArmyRegimentSize - 1; i >= 0; --i) {
                    InGame_Disband.armyLeft.addRegiment(InGame_Disband.armyRight.lArmyRegiment.get(i));
                    InGame_Disband.armyRight.removeRegiment(i);
                }
                Game.menuManager.rebuildInGame_DisbandUnits();
                InGame_Disband.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                int numOfUnits = 0;
                for (int i = 0; i < InGame_Disband.armyRight.iArmyRegimentSize; ++i) {
                    numOfUnits += InGame_Disband.armyRight.lArmyRegiment.get(i).num;
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Cancel") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + numOfUnits, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < InGame_Disband.armyRight.iArmyRegimentSize; ++i) {
            menuElements.add(new ButtonArmy("" + InGame_Disband.armyRight.lArmyRegiment.get(i).num, 1, InGame_Disband.armyRight.civID, buttonX, buttonY, InGame_Disband.armyRight.lArmyRegiment.get(i).uID, InGame_Disband.armyRight.lArmyRegiment.get(i).aID, i, -1) {
                @Override
                public void actionElement() {
                    InGame_Disband.armyLeft.addRegiment(InGame_Disband.armyRight.lArmyRegiment.get(this.getCurrent()));
                    InGame_Disband.armyRight.removeRegiment(this.getCurrent());
                    Game.menuManager.rebuildInGame_DisbandUnits();
                    InGame_Disband.lTime = 0L;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        buttonY += buttonYPadding;
        final int menuHeight = Math.min(Math.max(ImageManager.getImage(Images.recruitArmyOver).getHeight(), buttonY), CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter("", Game.getProvince(InGame_Disband.armyLeft.provinceID).getProvinceName(), false, false, Images.title1Red) {
            @Override
            public int getFlagCivID() {
                return InGame_Disband.armyLeft.civID;
            }
            
            @Override
            public long getTime() {
                return InGame_Disband.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Disband.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * 4 / 5 * ((CFG.currentTimeMillis - InGame_Disband.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.recruitArmyOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.recruitArmyOver).getHeight()));
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() / 2, this.getHeight());
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("DisbandUnits"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (InGame_Disband.restartAnimation) {
            InGame_Disband.lTime = CFG.currentTimeMillis;
        }
    }
    
    public static void setArmyLeft(final ArmyDivision nArmyLeft) {
        InGame_Disband.armyLeft = null;
        InGame_Disband.armyRight = null;
        final List<ArmyRegiment> tempArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int i = 0, iSize = nArmyLeft.lArmyRegiment.size(); i < iSize; ++i) {
            tempArmyRegiment.add(nArmyLeft.lArmyRegiment.get(i));
        }
        InGame_Disband.armyLeft = new ArmyDivision(nArmyLeft.civID, nArmyLeft.provinceID, tempArmyRegiment);
        InGame_Disband.armyLeft.key = nArmyLeft.key;
        InGame_Disband.armyRight = new ArmyDivision(nArmyLeft.civID, nArmyLeft.provinceID, new ArrayList<ArmyRegiment>());
    }
    
    static {
        InGame_Disband.restartAnimation = true;
        InGame_Disband.lTime = 0L;
        InGame_Disband.armyLeft = new ArmyDivision(0, 0, new ArrayList<ArmyRegiment>());
        InGame_Disband.armyRight = new ArmyDivision(0, 0, new ArrayList<ArmyRegiment>());
    }
}
