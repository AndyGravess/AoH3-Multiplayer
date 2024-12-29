// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

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
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.graphics.Color;
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

public class ButtonArmyGeneral extends Button
{
    public String sFullName;
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
    public Image generalImage;
    public float combatExperience;
    
    public ButtonArmyGeneral(final String sName, final int iCivID, final int iAttack, final int iDefense, final int iPosX, final int iPosY, final int imageID, final int iDay, final int iMonth, final int iYear, final String sIMG, final int combatExperience) {
        this.init(sName, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.generalFrame).getWidth(), getButtonHeight(), true, true, false, false);
        this.sFullName = sName;
        this.iCivID = iCivID;
        this.iDay = iDay;
        this.iMonth = iMonth;
        this.iYear = iYear;
        this.imageID = imageID;
        this.sAttack = "" + iAttack;
        this.sDefense = "" + iDefense;
        this.combatExperience = Math.min(1.0f, Math.max(0.0f, combatExperience / (float)GameValues.generals.COMBAT_EXPERIENCE_MAX));
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), this.sAttack);
        this.iAttackWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), this.sDefense);
        this.iDefenseWidth = (int)Renderer.glyphLayout.width;
        ButtonArmyGeneral.iconScale = this.getImageScale(Images.attack) * 1.1f;
        ButtonArmyGeneral.attackIconWidth = (int)(ImageManager.getImage(Images.attack).getWidth() * ButtonArmyGeneral.iconScale);
        ButtonArmyGeneral.attackIconHeight = (int)(ImageManager.getImage(Images.attack).getHeight() * ButtonArmyGeneral.iconScale);
        ButtonArmyGeneral.defenseIconWidth = (int)(ImageManager.getImage(Images.defense).getWidth() * ButtonArmyGeneral.iconScale);
        ButtonArmyGeneral.defenseIconHeight = (int)(ImageManager.getImage(Images.defense).getHeight() * ButtonArmyGeneral.iconScale);
        if (sIMG != null) {
            if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png").exists()) {
                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            }
            else if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png").exists()) {
                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            }
        }
        if (this.generalImage == null) {
            final int loadCivID = Math.abs(iCivID);
            if (FileManager.loadFile("game/generals/" + GeneralManager.getGeneralsImgPath(loadCivID) + CFG.getRescouresPath_Short() + Game.getCiv(loadCivID).iGroupID + "/" + imageID + ".png").exists()) {
                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/generals/" + GeneralManager.getGeneralsImgPath(loadCivID) + CFG.getRescouresPath_Short() + Game.getCiv(loadCivID).iGroupID + "/" + imageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            }
            else {
                this.generalImage = new Image(ImageManager.loadTexture_RGB888("game/generals/" + GeneralManager.getGeneralsImgPath(loadCivID) + CFG.getRescouresPath_Short() + Game.getCiv(loadCivID).iGroupID + "/0.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            }
        }
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }
        try {
            this.generalImage.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }
        catch (final Exception ex) {}
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getStatsHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(ProvinceDraw.progressBar);
        }
        else {
            oSB.setColor(Colors.COLOR_BOX_GOLD);
        }
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getStatsHeight() - CFG.PADDING + iTranslateY, (int)(this.getWidth() * this.combatExperience), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getStatsHeight() - CFG.PADDING + iTranslateY, this.getWidth(), 1);
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
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - getStatsHeight() - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) + iTranslateY, this.iAttackWidth + ButtonArmyGeneral.attackIconWidth + CFG.PADDING * 3, CFG.TEXT_HEIGHT + CFG.PADDING * 2, 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getWidth() - (this.iDefenseWidth + ButtonArmyGeneral.defenseIconWidth + CFG.PADDING * 3) - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - getStatsHeight() - CFG.PADDING * 2 - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) + iTranslateY, this.iDefenseWidth + ButtonArmyGeneral.defenseIconWidth + CFG.PADDING * 3, CFG.TEXT_HEIGHT + CFG.PADDING * 2, 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() + CFG.PADDING + iTranslateY, this.getColor(isActive));
        ImageManager.getImage(Images.attack).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING * 3 - ButtonArmyGeneral.attackIconHeight + iTranslateY, ButtonArmyGeneral.attackIconWidth, ButtonArmyGeneral.attackIconHeight);
        ImageManager.getImage(Images.defense).draw(oSB, this.getPosX() + this.getWidth() - ButtonArmyGeneral.defenseIconWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING * 3 - ButtonArmyGeneral.defenseIconHeight + iTranslateY, ButtonArmyGeneral.defenseIconWidth, ButtonArmyGeneral.defenseIconHeight);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR, this.sAttack, this.getPosX() + ButtonArmyGeneral.attackIconWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING * 3 - CFG.TEXT_HEIGHT_SMALL + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR, this.sDefense, this.getPosX() + this.getWidth() - CFG.PADDING * 3 - ButtonArmyGeneral.defenseIconWidth - this.iDefenseWidth + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrame).getHeight() - CFG.PADDING * 3 - CFG.TEXT_HEIGHT_SMALL + iTranslateY, Colors.BUTTON_TEXT_HOVERED);
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 2;
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.generalFrame).getHeight() + getStatsHeight();
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, this.sFullName));
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
        if (this.iCivID == Game.player.iCivID) {
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
    
    public void buildElementHover2() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, this.sFullName));
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
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public void dispose() {
        if (this.generalImage != null) {
            this.generalImage.dispose();
            this.generalImage = null;
        }
    }
    
    static {
        ButtonArmyGeneral.iconScale = 1.0f;
    }
}
