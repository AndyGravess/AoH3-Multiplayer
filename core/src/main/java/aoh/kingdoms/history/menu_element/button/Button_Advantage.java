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
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.AdvantagesManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class Button_Advantage extends Button
{
    public int imgID;
    public int advantageID;
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    
    public Button_Advantage(final int iPosX, final int iPosY, final int imgID, final int advantageID) {
        this.imgID = imgID;
        this.advantageID = advantageID;
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.advantagesOver).getWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.advantagesOver).getWidth(), ImageManager.getImage(Images.advantagesOver).getHeight());
        }
        try {
            AdvantagesManager.advantagesImages.get(this.imgID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 3);
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 3 + iTranslateY, this.getWidth(), CFG.PADDING * 3, false, true);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.5f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.advantagesOver).getWidth(), ImageManager.getImage(Images.advantagesOver).getHeight() / 2);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.advantagesOver).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.advantagesOver).getWidth(), ImageManager.getImage(Images.advantagesOver).getHeight() / 2, false, true);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.advantages).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.advantages).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.advantages).getHeight() / 2 + iTranslateY);
        }
        if (this.getClickable() && this.getIsHovered() && Button_Advantage.animationState >= 0) {
            if (Button_Advantage.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_Advantage.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 - CFG.PADDING + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING);
                if (Button_Advantage.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++Button_Advantage.animationState;
                    Button_Advantage.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button_Advantage.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING, false, true);
                Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 - CFG.PADDING + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), CFG.PADDING);
                if (Button_Advantage.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    Button_Advantage.animationState = 0;
                    Button_Advantage.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(Images.advantagesOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.advantagesOver).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UnlockAdvantage"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (AdvantagesManager.advantages.get(this.advantageID).RequiredTechID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
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
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        Button_Advantage.lTimeAnimation = CFG.currentTimeMillis;
        Button_Advantage.animationState = 0;
    }
    
    @Override
    public int getCurrent() {
        return this.advantageID;
    }
    
    static {
        Button_Advantage.lTimeAnimation = 0L;
        Button_Advantage.animationState = 0;
    }
}
