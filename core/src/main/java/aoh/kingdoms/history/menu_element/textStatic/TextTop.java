// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Game_Calendar;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menusInGame.InGame;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.Renderer.SparksAnimation;
import aoh.kingdoms.history.menu_element.MenuElement;

public class TextTop extends MenuElement
{
    public static final int EXTRA_WIDTH_BOX_PADDING;
    public String sText;
    public String sText2;
    public int iTextWidth;
    public int iTextHeight;
    public int iTextPosX;
    public int fontID2;
    public int iTextWidth2;
    public int iTextHeight2;
    public int imageID;
    public int textPosY;
    protected static long lTimeAnimation;
    protected static int animationState;
    public static final int ANIMATION_T = 1000;
    public SparksAnimation sparksAnimationTop;
    public float lastValue;
    public int WIDTH_LAST_TURN_UPDATE;
    
    public TextTop(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        this.sText = null;
        this.sText2 = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.fontID2 = 1;
        this.iTextWidth2 = -1;
        this.iTextHeight2 = -1;
        this.sparksAnimationTop = new SparksAnimation();
        this.lastValue = -997654.3f;
        this.WIDTH_LAST_TURN_UPDATE = 0;
        this.typeOfElement = MenuElement_Type.TEXT;
        this.imageID = imageID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(InGame.topStatsHeight - CFG.PADDING * 2);
        this.setText(sText);
        this.setText2(sText2);
        this.iTextPosX = TextTop.EXTRA_WIDTH_BOX_PADDING + ImageManager.getImage(imageID).getWidth() + CFG.PADDING * 2;
        this.fontID = CFG.FONT_REGULAR;
        this.fontID2 = CFG.FONT_REGULAR_SMALL;
        this.textPosY = (this.getHeight() - (this.iTextHeight + this.iTextHeight2 + CFG.PADDING)) / 2;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, int iTranslateY, final boolean isActive, final boolean scrollableY) {
        iTranslateX += this.getPosX();
        iTranslateY += this.getPosY();
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        oSB.getColor().a = 0.65f;
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.getColor().a = 0.3f;
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - CFG.PADDING * 2, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        oSB.getColor().a = 0.85f;
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        oSB.getColor().a = 0.9f;
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive || this.getIsActiveButton()) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.8f));
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(MainMenu.sparksColors);
            this.sparksAnimationTop.draw2(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        if (this.getClickable() && this.getIsHovered() && TextTop.animationState >= 0) {
            if (TextTop.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextTop.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + 1, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + this.getHeight() - 2, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextTop.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++TextTop.animationState;
                    TextTop.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextTop.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + 1, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + this.getHeight() - 2, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextTop.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    TextTop.animationState = 0;
                    TextTop.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        ImageManager.getImage(this.getImageID()).draw(oSB, iTranslateX + TextTop.EXTRA_WIDTH_BOX_PADDING, iTranslateY + (this.getHeight() - ImageManager.getImage(this.getImageID()).getHeight()) / 2);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), iTranslateX + this.iTextPosX, iTranslateY + this.textPosY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID2, this.sText2, iTranslateX + this.iTextPosX, iTranslateY + this.textPosY + this.iTextHeight + CFG.PADDING, Colors.TEXT_TOP_BOT);
    }
    
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
    
    protected Color getColor2(final boolean isActive) {
        return this.getColor(isActive);
    }
    
    public int getImageID() {
        return this.imageID;
    }
    
    @Override
    public String getTextToDraw() {
        return this.sText;
    }
    
    @Override
    public final String getText() {
        return this.sText;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            this.iTextHeight = (int)Renderer.glyphLayout.height;
            if (super.getWidth() < this.iTextWidth + this.extraWidth()) {
                this.setWidth(this.iTextWidth + this.extraWidth());
            }
            if (Game_Calendar.TURN_ID > this.WIDTH_LAST_TURN_UPDATE + 365) {
                this.setWidth(Math.max(this.iTextWidth, this.iTextWidth2) + this.extraWidth());
                this.WIDTH_LAST_TURN_UPDATE = Game_Calendar.TURN_ID;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void setText2(final String sText2) {
        this.sText2 = sText2;
        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID2), sText2);
            this.iTextWidth2 = (int)Renderer.glyphLayout.width;
            this.iTextHeight2 = (int)Renderer.glyphLayout.height;
            if (super.getWidth() < this.iTextWidth2 + this.extraWidth()) {
                this.setWidth(this.iTextWidth2 + this.extraWidth());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setWidthOfButton() {
        this.setWidth(this.extraWidth());
    }
    
    public final int extraWidth() {
        return ImageManager.getImage(this.imageID).getWidth() + CFG.PADDING * 2 + TextTop.EXTRA_WIDTH_BOX_PADDING * 2;
    }
    
    @Override
    public int getTextWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        TextTop.lTimeAnimation = CFG.currentTimeMillis;
        TextTop.animationState = 0;
    }
    
    public boolean getIsActiveButton() {
        return false;
    }
    
    static {
        EXTRA_WIDTH_BOX_PADDING = CFG.PADDING * 3;
        TextTop.lTimeAnimation = 0L;
        TextTop.animationState = 0;
    }
}
