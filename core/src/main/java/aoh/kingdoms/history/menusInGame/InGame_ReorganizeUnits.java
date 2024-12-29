// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
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

public class InGame_ReorganizeUnits extends Menu
{
    public static boolean restartAnimation;
    protected static final int ANIMATION_TIME = 60;
    private long lTime;
    public static ArmyDivision armyLeft;
    public static ArmyDivision armyRight;
    
    public InGame_ReorganizeUnits() {
        this.lTime = 0L;
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
        for (int i = 0; i < InGame_ReorganizeUnits.armyLeft.iArmyRegimentSize; ++i) {
            numOfUnits += InGame_ReorganizeUnits.armyLeft.lArmyRegiment.get(i).num;
        }
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                InGame_ReorganizeUnits.this.setVisible(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonReorganize(Game.lang.get("Army"), "" + numOfUnits, 0, buttonY, menuWidth / 2));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < InGame_ReorganizeUnits.armyLeft.iArmyRegimentSize; ++i) {
            menuElements.add(new ButtonArmy("" + InGame_ReorganizeUnits.armyLeft.lArmyRegiment.get(i).num, 1, InGame_ReorganizeUnits.armyLeft.civID, buttonX, buttonY, InGame_ReorganizeUnits.armyLeft.lArmyRegiment.get(i).uID, InGame_ReorganizeUnits.armyLeft.lArmyRegiment.get(i).aID, i, -1) {
                @Override
                public void actionElement() {
                    InGame_ReorganizeUnits.armyRight.addRegiment(InGame_ReorganizeUnits.armyLeft.lArmyRegiment.get(this.getCurrent()));
                    InGame_ReorganizeUnits.armyLeft.removeRegiment(this.getCurrent());
                    Game.menuManager.rebuildInGame_ReorganizeUnits();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        buttonX = menuWidth / 2 + menuWidth / 4 - ButtonArmy.getButtonWidth() / 2;
        buttonY = buttonYPadding;
        numOfUnits = 0;
        for (int i = 0; i < InGame_ReorganizeUnits.armyRight.iArmyRegimentSize; ++i) {
            numOfUnits += InGame_ReorganizeUnits.armyRight.lArmyRegiment.get(i).num;
        }
        menuElements.add(new ButtonGame(Game.lang.get("Save"), CFG.FONT_REGULAR, -1, paddingLeft + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                final int tArmYID = Game.getProvince(InGame_ReorganizeUnits.armyLeft.provinceID).getArmyKeyID(InGame_ReorganizeUnits.armyLeft.key);
                if (tArmYID >= 0) {
                    if (InGame_ReorganizeUnits.armyLeft.iArmyRegimentSize > 0 && InGame_ReorganizeUnits.armyRight.iArmyRegimentSize > 0) {
                        Game.getProvince(InGame_ReorganizeUnits.armyLeft.provinceID).updateRegiment(tArmYID, InGame_ReorganizeUnits.armyLeft.lArmyRegiment);
                        Game.getProvince(InGame_ReorganizeUnits.armyLeft.provinceID).addArmy(new ArmyDivision(InGame_ReorganizeUnits.armyLeft.civID, InGame_ReorganizeUnits.armyLeft.provinceID, InGame_ReorganizeUnits.armyRight.lArmyRegiment));
                        InGame_ReorganizeUnits.this.setVisible(false);
                        if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                            Game.menuManager.rebuildInGame_ProvinceArmy();
                        }
                        if (Game.menuManager.getVisibleInGame_Armies()) {
                            Game.menuManager.rebuildInGame_Armies(false, false);
                            Game.menuManager.setVisibleInGame_Armies(true);
                            InGame_Armies.lTime = 0L;
                        }
                    }
                }
                else {
                    Game.menuManager.addToast_Error("ArmyNotFound");
                }
            }
            
            @Override
            public boolean getClickable() {
                return super.getClickable() && InGame_ReorganizeUnits.armyLeft.iArmyRegimentSize > 0 && InGame_ReorganizeUnits.armyRight.iArmyRegimentSize > 0;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonReorganize(Game.lang.get("NewArmy"), "" + numOfUnits, menuWidth / 2, buttonY, menuWidth / 2));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < InGame_ReorganizeUnits.armyRight.iArmyRegimentSize; ++i) {
            menuElements.add(new ButtonArmy("" + InGame_ReorganizeUnits.armyRight.lArmyRegiment.get(i).num, 1, InGame_ReorganizeUnits.armyRight.civID, buttonX, buttonY, InGame_ReorganizeUnits.armyRight.lArmyRegiment.get(i).uID, InGame_ReorganizeUnits.armyRight.lArmyRegiment.get(i).aID, i, -1) {
                @Override
                public void actionElement() {
                    InGame_ReorganizeUnits.armyLeft.addRegiment(InGame_ReorganizeUnits.armyRight.lArmyRegiment.get(this.getCurrent()));
                    InGame_ReorganizeUnits.armyRight.removeRegiment(this.getCurrent());
                    Game.menuManager.rebuildInGame_ReorganizeUnits();
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
        this.initMenu(new MenuTitleIMG_FlagCenter("", Game.getProvince(InGame_ReorganizeUnits.armyLeft.provinceID).getProvinceName(), false, false, Images.title1Red) {
            @Override
            public int getFlagCivID() {
                return InGame_ReorganizeUnits.armyLeft.civID;
            }
            
            @Override
            public long getTime() {
                return InGame_ReorganizeUnits.this.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (this.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * 4 / 5 * ((CFG.currentTimeMillis - this.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.recruitArmyOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.recruitArmyOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.recruitArmyOver).getHeight()));
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() / 2, this.getHeight());
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() / 2, this.getHeight(), true, false);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("ReorganizeUnits"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (InGame_ReorganizeUnits.restartAnimation) {
            this.lTime = CFG.currentTimeMillis;
        }
    }
    
    public static void setArmyLeft(final ArmyDivision nArmyLeft) {
        InGame_ReorganizeUnits.armyLeft = null;
        InGame_ReorganizeUnits.armyRight = null;
        final List<ArmyRegiment> tempArmyRegiment = new ArrayList<ArmyRegiment>();
        for (int i = 0, iSize = nArmyLeft.lArmyRegiment.size(); i < iSize; ++i) {
            tempArmyRegiment.add(nArmyLeft.lArmyRegiment.get(i));
        }
        InGame_ReorganizeUnits.armyLeft = new ArmyDivision(nArmyLeft.civID, nArmyLeft.provinceID, tempArmyRegiment);
        InGame_ReorganizeUnits.armyLeft.key = nArmyLeft.key;
        InGame_ReorganizeUnits.armyRight = new ArmyDivision(nArmyLeft.civID, nArmyLeft.provinceID, new ArrayList<ArmyRegiment>());
    }
    
    static {
        InGame_ReorganizeUnits.restartAnimation = true;
        InGame_ReorganizeUnits.armyLeft = new ArmyDivision(0, 0, new ArrayList<ArmyRegiment>());
        InGame_ReorganizeUnits.armyRight = new ArmyDivision(0, 0, new ArrayList<ArmyRegiment>());
    }
}
