// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.map.AdvantagesManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;

public class Button_Advantage3 extends Button
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
    
    public Button_Advantage3(final int iPosX, final int iPosY, final int advantageID, final int iLevel, final String sTextHover, final String sText, final int imageID) {
        this.advantageID = advantageID;
        this.iLevel = iLevel;
        this.sTextHover = sTextHover;
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.0f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        this.init(sText, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.advantagesSmallOver).getWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setShader(Renderer.shaderAlpha);
        try {
            AdvantagesManager.advantagesImages.get(AdvantagesManager.advantages.get(this.advantageID).ImageID[this.iLevel]).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.advantagesSmallMask).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.advantagesSmallMask).getWidth(), ImageManager.getImage(Images.advantagesSmallMask).getHeight());
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        if (this.getIsHovered() && Button_Advantage3.animationState >= 0) {
            if (Button_Advantage3.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_Advantage3.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.advantagesSmallOver).getHeight() - 2 - CFG.PADDING + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING);
                if (Button_Advantage3.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++Button_Advantage3.animationState;
                    Button_Advantage3.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_Advantage3.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + ImageManager.getImage(Images.advantagesSmallOver).getHeight() - 2 - CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING);
                if (Button_Advantage3.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    Button_Advantage3.animationState = 0;
                    Button_Advantage3.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(Images.advantagesSmallOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.advantagesSmallOver).getHeight();
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.advantagesSmallOver).getHeight();
    }
    
    public int getBonusH() {
        return CFG.PADDING * 2 + CFG.TEXT_HEIGHT;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.sTextHover + ": ", "" + this.getText(), this.imageID, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationAdvantage"), CFG.FONT_BOLD_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.advantages, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        Button_Advantage3.lTimeAnimation = CFG.currentTimeMillis;
        Button_Advantage3.animationState = 0;
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
    
    static {
        Button_Advantage3.lTimeAnimation = 0L;
        Button_Advantage3.animationState = 0;
    }
}
