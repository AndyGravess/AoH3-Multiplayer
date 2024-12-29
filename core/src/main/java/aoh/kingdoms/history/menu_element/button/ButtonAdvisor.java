// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.advisors.AdvisorManager;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Image;

public class ButtonAdvisor extends Button
{
    public int iCivID;
    public int imageID;
    public Image advisorImage;
    public int iHireModeID;
    public int iAdvisorType;
    
    public ButtonAdvisor(final int iPosX, final int iPosY, final String sName, final int imageID, final int iCivID, final int iHireModeID, final int iAdvisorType, final String sIMG) {
        this.iHireModeID = -1;
        this.init(sName, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.generalFrame).getWidth(), getButtonHeight(), true, true, false, false);
        this.iCivID = iCivID;
        this.imageID = imageID;
        this.iHireModeID = iHireModeID;
        this.iAdvisorType = iAdvisorType;
        try {
            if (sIMG != null) {
                if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png").exists()) {
                    this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                else if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png").exists()) {
                    this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
            }
            if (this.advisorImage == null) {
                if (FileManager.loadFile("game/advisors/" + AdvisorManager.getAdvisorsImgPath() + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + imageID + ".png").exists()) {
                    this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/advisors/" + AdvisorManager.getAdvisorsImgPath() + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + imageID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                }
                else {
                    try {
                        this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/advisors/" + AdvisorManager.getAdvisorsImgPath() + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/0.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                    }
                    catch (final Exception ex) {
                        this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/advisors/advisors/" + CFG.getRescouresPath_Short() + "noAdvisor.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }
        try {
            this.advisorImage.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }
        catch (final Exception ex) {}
        ImageManager.getImage(Images.generalFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.generalFrame).getHeight();
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.generalFrame).getWidth();
    }
    
    @Override
    public void dispose() {
        if (this.advisorImage != null) {
            this.advisorImage.dispose();
            this.advisorImage = null;
        }
    }
    
    @Override
    public int getCurrent() {
        return this.iHireModeID;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (this.iHireModeID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("HireAdvisor") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            final List<MenuElement_HoverElement_Type> list = nData;
            final AdvisorManager advisorManager = Game.advisorManager;
            list.add(new MenuElement_HoverElement_Type_Text(AdvisorManager.getAdvisorGroupName(this.iAdvisorType), CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + AdvisorManager.getRecruitGoldCost(Game.player.iCivID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + AdvisorManager.getRecruitCostLegacy(Game.player.iCivID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements);
        }
        else if (AdvisorManager.getAdvisor(this.iAdvisorType).sName == null) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD, Colors.HOVER_LEFT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(AdvisorManager.getAdvisor(this.iAdvisorType).sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            final List<MenuElement_HoverElement_Type> list2 = nData;
            final AdvisorManager advisorManager2 = Game.advisorManager;
            list2.add(new MenuElement_HoverElement_Type_Text(AdvisorManager.getAdvisorGroupName(this.iAdvisorType), CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + AdvisorManager.getAdvisor(this.iAdvisorType).iLevel, Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Born") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + AdvisorManager.getAdvisor(this.iAdvisorType).iDayOfBirth + " " + Game_Calendar.getMonthName(AdvisorManager.getAdvisor(this.iAdvisorType).iMonthOfBirth) + " " + AdvisorManager.getAdvisor(this.iAdvisorType).iYearOfBirth, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - AdvisorManager.getAdvisor(this.iAdvisorType).iYearOfBirth)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (this.iCivID == Game.player.iCivID) {
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToFireAnAdvisor"), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            else {
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        }
    }
}
