// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;

public class ButtonStatsBudget_Right2 extends Button
{
    public float lastValue;
    public Color nColor;
    public Color nColorH;
    public Color nColorA;
    public int id;
    
    public ButtonStatsBudget_Right2(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.lastValue = -997654.3f;
        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        this.init(sText, CFG.FONT_BOLD_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
    }
    
    public ButtonStatsBudget_Right2(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int id) {
        this.lastValue = -997654.3f;
        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        this.init(sText, CFG.FONT_BOLD_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.id = id;
    }
    
    public ButtonStatsBudget_Right2(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final float fValue) {
        this.lastValue = -997654.3f;
        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        this.init(sText, CFG.FONT_BOLD_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        if (fValue < 0.0f) {
            this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
            this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
            this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2, this.getHeight(), true, false);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1);
        Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1, false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_TITLE_BLUE.r, Colors.COLOR_GRADIENT_TITLE_BLUE.g, Colors.COLOR_GRADIENT_TITLE_BLUE.b, 0.35f));
        Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1);
        Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - Images.line_32_off1.getHeight() + iTranslateY, this.getWidth(), 1, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return this.nColorA;
        }
        if (this.getIsHovered()) {
            return this.nColorH;
        }
        return this.getClickable() ? this.nColor : Colors.BUTTON_TEXT_DISABLED;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", this.getText(), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
