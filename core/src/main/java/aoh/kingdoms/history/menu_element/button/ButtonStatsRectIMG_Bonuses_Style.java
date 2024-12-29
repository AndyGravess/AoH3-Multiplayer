// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonStatsRectIMG_Bonuses_Style extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public String sText2;
    public int iTextBonusW;
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int maxIconWidth;
    
    public ButtonStatsRectIMG_Bonuses_Style(final String sText, final String sText2, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.maxIconWidth = maxIconWidth;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), sText2);
        this.iTextBonusW = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING * 2 + this.maxIconWidth, 1);
        oSB.setColor(Color.WHITE);
        this.drawButtonBG_Animation(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    protected void drawButtonBG_Animation(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getClickable() && this.getIsHovered() && ButtonStatsRectIMG_Bonuses_Style.animationState >= 0) {
            if (ButtonStatsRectIMG_Bonuses_Style.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonStatsRectIMG_Bonuses_Style.animationState;
                    ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonStatsRectIMG_Bonuses_Style.animationState = 0;
                    ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + CFG.PADDING + this.maxIconWidth / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 2 + this.maxIconWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD_SMALL, this.sText2, this.getPosX() + CFG.PADDING * 2 + this.maxIconWidth + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColorBonus());
    }
    
    public Color getColorBonus() {
        return Colors.COLOR_TEXT_MODIFIER_POSITIVE;
    }
    
    public final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_Text(this.sText2, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(this.imageID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    static {
        ButtonStatsRectIMG_Bonuses_Style.lTimeAnimation = 0L;
        ButtonStatsRectIMG_Bonuses_Style.animationState = 0;
    }
}
