// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_Title_Diplomacy extends Text_Static
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 2000;
    public boolean isActiveButton;
    
    public Text_Title_Diplomacy(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final boolean isActiveButton) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = CFG.FONT_BOLD;
        this.isActiveButton = isActiveButton;
        this.iTextPositionX = -1;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
        this.updateTextPosition();
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.9f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        if (this.isActiveButton) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.55f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.95f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        else {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.4f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        if (this.getIsHovered()) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE3.r, Colors.COLOR_GRADIENT_OVER_BLUE3.g, Colors.COLOR_GRADIENT_OVER_BLUE3.b, 0.75f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + iTranslateY, this.getWidth(), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
        if (Text_Title_Diplomacy.animationState >= 0) {
            if (Text_Title_Diplomacy.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Text_Title_Diplomacy.lTimeAnimation) / 2000.0f, 1.0f);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.125f));
                Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX - MenuElement_Hover.getDrawExtraXPos(), this.getPosY() + iTranslateY + this.getHeight() - 2, (int)((this.getWidth() + MenuElement_Hover.getDrawExtraXPos() * 2) * drawPerc), 1);
                if (Text_Title_Diplomacy.lTimeAnimation < CFG.currentTimeMillis - 2000L) {
                    ++Text_Title_Diplomacy.animationState;
                    Text_Title_Diplomacy.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Text_Title_Diplomacy.lTimeAnimation) / 2000.0f, 1.0f);
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.125f));
                Images.line_32_off1.draw(oSB, this.getPosX() + iTranslateX - MenuElement_Hover.getDrawExtraXPos() + (int)((this.getWidth() + MenuElement_Hover.getDrawExtraXPos() * 2) * drawPerc), this.getPosY() + iTranslateY + this.getHeight() - 2, this.getWidth() + MenuElement_Hover.getDrawExtraXPos() * 2 - (int)((this.getWidth() + MenuElement_Hover.getDrawExtraXPos() * 2) * drawPerc), 1);
                if (Text_Title_Diplomacy.lTimeAnimation < CFG.currentTimeMillis - 2000L) {
                    Text_Title_Diplomacy.animationState = 0;
                    Text_Title_Diplomacy.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextHeight()) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.isActiveButton) {
            return Colors.getColorTopStats2(isActive, this.getIsHovered());
        }
        return Colors.getColorTopStats5(isActive, this.getIsHovered());
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements, true);
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)glyphLayout.width;
            this.iTextHeight = (int)glyphLayout.height;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        Text_Title_Diplomacy.lTimeAnimation = 0L;
        Text_Title_Diplomacy.animationState = 0;
    }
}
