// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.InGame_Armies;
import aoh.kingdoms.history.map.army.ArmyGeneral;
import aoh.kingdoms.history.menusInGame.InGame_Generals;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.GeneralManager;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.mainGame.FileManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Image;

public class ButtonArmyGeneral_Assign extends Button
{
    public int iCivID;
    public int imageID;
    public String sAttack;
    public int iAttackWidth;
    public String sDefense;
    public int iDefenseWidth;
    public static float iconScale;
    public static int attackIconWidth;
    public static int attackIconHeight;
    public static int defenseIconWidth;
    public static int defenseIconHeight;
    public int iDay;
    public int iMonth;
    public int iYear;
    public int statH;
    public boolean recruitMode;
    public Image generalImage;
    public String generalKey;
    public float combatExperience;
    
    public ButtonArmyGeneral_Assign(final int iPosX, final int iPosY, final int iWidth, final String sName, final int iCivID, final int iAttack, final int iDefense, final int imageID, final int iDay, final int iMonth, final int iYear, final boolean recruitMode, final String generalKey, final String sIMG, final int combatExperience) {
        this.statH = 0;
        this.init(sName, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, Math.max(ImageManager.getImage(Images.generalFrame).getWidth(), iWidth), getButtonHeight(), true, true, false, false);
        this.iCivID = iCivID;
        this.iDay = iDay;
        this.iMonth = iMonth;
        this.iYear = iYear;
        this.imageID = imageID;
        this.sAttack = "" + iAttack;
        this.sDefense = "" + iDefense;
        this.combatExperience = Math.min(1.0f, Math.max(0.0f, combatExperience / (float)GameValues.generals.COMBAT_EXPERIENCE_MAX));
        this.recruitMode = recruitMode;
        this.generalKey = generalKey;
        this.statH = (ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING * 3) / 2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), this.sAttack);
        this.iAttackWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), this.sDefense);
        this.iDefenseWidth = (int)Renderer.glyphLayout.width;
        ButtonArmyGeneral_Assign.iconScale = this.getImageScale(Images.attack);
        ButtonArmyGeneral_Assign.attackIconWidth = (int)(ImageManager.getImage(Images.attack).getWidth() * ButtonArmyGeneral_Assign.iconScale);
        ButtonArmyGeneral_Assign.attackIconHeight = (int)(ImageManager.getImage(Images.attack).getHeight() * ButtonArmyGeneral_Assign.iconScale);
        ButtonArmyGeneral_Assign.defenseIconWidth = (int)(ImageManager.getImage(Images.defense).getWidth() * ButtonArmyGeneral_Assign.iconScale);
        ButtonArmyGeneral_Assign.defenseIconHeight = (int)(ImageManager.getImage(Images.defense).getHeight() * ButtonArmyGeneral_Assign.iconScale);
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
        try {
            if (sIMG != null) {
                if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png").exists()) {
                    this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                else if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png").exists()) {
                    this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
            }
            if (this.generalImage == null) {
                if (FileManager.loadFile("game/generals/" + GeneralManager.getGeneralsImgPath(iCivID) + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + imageID + ".png").exists()) {
                    this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/generals/" + GeneralManager.getGeneralsImgPath(iCivID) + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + imageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                else {
                    this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/generals/" + GeneralManager.getGeneralsImgPath(iCivID) + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/0.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.65f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.5f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        oSB.setColor(new Color(Colors.BUTTON_BUILDING_COLORS_STATS.r, Colors.BUTTON_BUILDING_COLORS_STATS.g, Colors.BUTTON_BUILDING_COLORS_STATS.b, 0.65f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - 1 + iTranslateY, this.getInnerWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCornerEmpty(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }
        try {
            this.generalImage.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }
        catch (final Exception ex) {}
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), CFG.PADDING);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(ProvinceDraw.progressBar);
        }
        else {
            oSB.setColor(Colors.COLOR_BOX_GOLD);
        }
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING + iTranslateY, (int)(ImageManager.getImage(Images.generalFrame).getWidth() * this.combatExperience), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), 1);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.generalFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() + iTranslateY, this.getWidth(), getStatsHeight());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() + iTranslateY, this.getWidth(), getStatsHeight(), false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() + iTranslateY, 1, getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() + iTranslateY, 1, getStatsHeight());
        oSB.setColor(Color.WHITE);
        this.drawStats(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() + CFG.PADDING * 2 + iTranslateY, this.getColor(isActive));
    }
    
    public final void drawStats(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + this.statH - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING * 2 + this.statH + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING * 2 + this.statH * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2, 1.0f);
        ImageManager.getImage(Images.attack).draw(oSB, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - ButtonArmyGeneral_Assign.attackIconWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING + (this.statH - CFG.TEXT_HEIGHT - CFG.PADDING * 2) / 2 - ButtonArmyGeneral_Assign.attackIconHeight / 2 + iTranslateY, ButtonArmyGeneral_Assign.attackIconWidth, ButtonArmyGeneral_Assign.attackIconHeight);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR, this.sAttack, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - this.iAttackWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING + this.statH - CFG.TEXT_HEIGHT - CFG.PADDING + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
        ImageManager.getImage(Images.defense).draw(oSB, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - ButtonArmyGeneral_Assign.defenseIconWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING + (this.statH - CFG.TEXT_HEIGHT - CFG.PADDING * 2) / 2 - ButtonArmyGeneral_Assign.defenseIconHeight / 2 + CFG.PADDING + this.statH + iTranslateY, ButtonArmyGeneral_Assign.defenseIconWidth, ButtonArmyGeneral_Assign.defenseIconHeight);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR, this.sDefense, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - this.iDefenseWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING + this.statH - CFG.TEXT_HEIGHT - CFG.PADDING + CFG.PADDING + this.statH + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 4;
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.generalFrame).getHeight() + getStatsHeight();
    }
    
    private final float getImageScale(final int iImageID) {
        return (this.statH - CFG.TEXT_HEIGHT - CFG.PADDING * 4) / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    public int getInnerPosX() {
        return ImageManager.getImage(Images.generalFrame).getWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - ImageManager.getImage(Images.generalFrame).getWidth();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, this.getText()));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Attack") + ": ", this.sAttack, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Defense") + ": ", this.sDefense, Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CombatExperience") + ": ", CFG.getNumberWithSpaces("" + (int)(this.combatExperience * GameValues.generals.COMBAT_EXPERIENCE_MAX)) + " / " + GameValues.generals.COMBAT_EXPERIENCE_MAX, Images.general, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("CombatExperienceDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DateOfBirth") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + this.iDay + " " + Game_Calendar.getMonthName(this.iMonth) + " " + this.iYear, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - this.iYear)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (!this.recruitMode && this.iCivID == Game.player.iCivID && Game.menuManager.getVisibleInGame_ProvinceArmy()) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AssignGeneralToThisUnit"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements, true);
        }
        else {
            this.menuElementHover = new MenuElement_Hover(nElements);
        }
    }
    
    @Override
    public void dispose() {
        if (this.generalImage != null) {
            this.generalImage.dispose();
            this.generalImage = null;
        }
    }
    
    @Override
    public void actionElement() {
        assignGeneral(this.iCivID, this.generalKey, true);
    }
    
    public static final void assignGeneral(final int iCivID, final String generalKey, final boolean swapGenerals) {
        if (Game.menuManager.getVisibleInGame_ProvinceArmy() && InGame_ProvinceArmy.iCivFlagID == Game.player.iCivID) {
            InGame_Generals.assignProvinceID = InGame_ProvinceArmy.iProvinceID;
            InGame_Generals.assignArmyKey = InGame_ProvinceArmy.sActiveKEY;
        }
        if (generalKey != null && InGame_Generals.assignProvinceID >= 0) {
            int nArmyID = Game.getProvince(InGame_Generals.assignProvinceID).getArmyKeyID(InGame_Generals.assignArmyKey);
            if (nArmyID < 0) {
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    final int outID = Game.getProvince(i).getArmyKeyID(InGame_Generals.assignArmyKey, iCivID);
                    if (outID >= 0) {
                        InGame_Generals.assignProvinceID = i;
                        nArmyID = outID;
                        break;
                    }
                }
            }
            if (nArmyID >= 0 && Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).civID == Game.player.iCivID) {
                if (!swapGenerals && Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).armyGeneral != null) {
                    return;
                }
                if (Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).armyGeneral != null) {
                    if (Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).armyGeneral.key.equals(generalKey)) {
                        Game.getCiv(Game.player.iCivID).addGeneral(Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).armyGeneral);
                        Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).setArmyGeneral(null);
                        rebuildMenus();
                        return;
                    }
                    for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                        for (int j = 0; j < Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                            if (Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).civID == Game.player.iCivID && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral != null && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral.key.equals(generalKey)) {
                                final ArmyGeneral tempArmyGeneral = Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).armyGeneral;
                                Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).setArmyGeneral(Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral);
                                Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).setArmyGeneral(tempArmyGeneral);
                                rebuildMenus();
                                return;
                            }
                        }
                    }
                }
                if (!Game.getCiv(Game.player.iCivID).assignGeneralToArmy(InGame_Generals.assignProvinceID, nArmyID, generalKey)) {
                    for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                        for (int j = 0; j < Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                            if (Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).civID == Game.player.iCivID && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral != null && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral.key.equals(generalKey)) {
                                final ArmyGeneral tempArmyGeneral = Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).armyGeneral;
                                Game.getProvince(InGame_Generals.assignProvinceID).getArmy(nArmyID).setArmyGeneral(Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral);
                                Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).setArmyGeneral(tempArmyGeneral);
                                rebuildMenus();
                                return;
                            }
                        }
                    }
                }
                rebuildMenus();
            }
        }
    }
    
    public static final void rebuildMenus() {
        Game.menuManager.setVisibleInGame_Generals(false);
        if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
            Game.menuManager.rebuildInGame_ProvinceArmy(true, true);
        }
        if (Game.menuManager.getVisibleInGame_Armies()) {
            Game.menuManager.rebuildInGame_Armies(false, true);
            Game.menuManager.setVisibleInGame_Armies(true);
            InGame_Armies.lTime = 0L;
        }
    }
    
    static {
        ButtonArmyGeneral_Assign.iconScale = 1.0f;
    }
}
