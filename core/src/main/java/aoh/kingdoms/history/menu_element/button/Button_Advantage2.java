// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.AdvantagesManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.InGame_CivilizationAdvantages;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;

public class Button_Advantage2 extends Button
{
    public int advantageID;
    public int iLevel;
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public String sTextHover;
    public boolean haveAdvantage;
    public boolean canUnlock;
    
    public Button_Advantage2(final int iPosX, final int iPosY, final int advantageID, final int iLevel, final String sTextHover, final String sText, final int imageID) {
        this.haveAdvantage = false;
        this.canUnlock = false;
        this.advantageID = advantageID;
        this.iLevel = iLevel;
        this.sTextHover = sTextHover;
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.0f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        this.init(sText, CFG.FONT_BOLD_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.advantagesOver).getWidth(), getButtonHeight(), true, true, false, false);
        if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(advantageID, iLevel)) {
            this.haveAdvantage = true;
        }
        else if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).canUnlockAdvantage(advantageID, iLevel)) {
            this.canUnlock = true;
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.advantagesOver).getWidth(), ImageManager.getImage(Images.advantagesOver).getHeight());
        }
        try {
            if (this.haveAdvantage) {
                AdvantagesManager.advantagesImages.get(AdvantagesManager.advantages.get(this.advantageID).ImageID[this.iLevel]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
            }
            else {
                oSB.setShader(Renderer.shaderBlackWhite);
                try {
                    AdvantagesManager.advantagesImages.get(AdvantagesManager.advantages.get(this.advantageID).ImageID[this.iLevel]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                oSB.setShader(Renderer.shaderDefault);
            }
            if (this.getIsHovered() || isActive) {
                AdvantagesManager.advantagesImages.get(AdvantagesManager.advantages.get(this.advantageID).ImageID[this.iLevel]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
            }
            else if (this.canUnlock) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
                AdvantagesManager.advantagesImages.get(AdvantagesManager.advantages.get(this.advantageID).ImageID[this.iLevel]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.8f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getBonusH() + iTranslateY, this.getWidth(), this.getBonusH());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getBonusH() + iTranslateY, this.getWidth(), this.getBonusH());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getBonusH() + iTranslateY, this.getWidth(), this.getBonusH(), false, true);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getBonusH() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && Button_Advantage2.animationState >= 0) {
            if (Button_Advantage2.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_Advantage2.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.advantagesOver).getHeight() - 2 - CFG.PADDING + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING);
                if (Button_Advantage2.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++Button_Advantage2.animationState;
                    Button_Advantage2.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_Advantage2.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + ImageManager.getImage(Images.advantagesOver).getHeight() - 2 - CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING);
                if (Button_Advantage2.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    Button_Advantage2.animationState = 0;
                    Button_Advantage2.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iconWidth + CFG.PADDING) / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.getBonusH() / 2 - this.iTextHeight / 2 + iTranslateY, this.haveAdvantage ? this.getColor(isActive) : this.getColor2(isActive));
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iconWidth + CFG.PADDING) / 2 + this.iTextWidth + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - this.getBonusH() / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        ImageManager.getImage(Images.advantagesOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.advantagesOver).getHeight();
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.advantagesOver).getHeight();
    }
    
    public int getBonusH() {
        return CFG.PADDING * 2 + CFG.TEXT_HEIGHT;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (InGame_CivilizationAdvantages.iActiveCivID == Game.player.iCivID) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UnlockAdvantage"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.sTextHover + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (InGame_CivilizationAdvantages.iActiveCivID == Game.player.iCivID) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (AdvantagesManager.advantages.get(this.advantageID).RequiredTechID >= 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnlockedTechnologiesRequired") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(TechnologyTree.lTechnology.get(AdvantagesManager.advantages.get(this.advantageID).RequiredTechID).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("1", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.advantages, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        Button_Advantage2.lTimeAnimation = CFG.currentTimeMillis;
        Button_Advantage2.animationState = 0;
    }
    
    @Override
    public String getTextToDraw() {
        return this.sTextHover;
    }
    
    @Override
    public int getValue1() {
        return this.advantageID;
    }
    
    @Override
    public int getValue2() {
        return this.iLevel;
    }
    
    public final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        }
        return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
    }
    
    protected Color getColor2(final boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }
    
    @Override
    public int getSFX() {
        return -1;
    }
    
    static {
        Button_Advantage2.lTimeAnimation = 0L;
        Button_Advantage2.animationState = 0;
    }
}
