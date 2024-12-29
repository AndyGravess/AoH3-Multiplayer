// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class MenuTitle
{
    private String sText;
    public float fontScale;
    public int iTextWidth;
    private int iTextHeight;
    private int iHeight;
    private boolean moveable;
    private boolean resizable;
    public int fontID;
    protected static final int ANIMATION_TIME = 325;
    public static Color colorDefault;
    public static Color colorHovered;
    public static Color colorActive;
    
    public MenuTitle(final String sText, final int iHeight, final boolean moveable, final boolean resizable) {
        this.fontScale = 1.0f;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.moveable = false;
        this.resizable = false;
        this.fontID = 0;
        this.initTitle(sText, 1.0f, iHeight, moveable, resizable);
    }
    
    public MenuTitle(final String sText, final int iHeight, final int fontID, final boolean moveable, final boolean resizable) {
        this.fontScale = 1.0f;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.moveable = false;
        this.resizable = false;
        this.fontID = 0;
        this.fontID = fontID;
        this.initTitle(sText, 1.0f, iHeight, moveable, resizable);
    }
    
    public MenuTitle(final String sText, final float fontScale, final int iHeight, final boolean moveable, final boolean resizable) {
        this.fontScale = 1.0f;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.moveable = false;
        this.resizable = false;
        this.fontID = 0;
        this.initTitle(sText, fontScale, iHeight, moveable, resizable);
    }
    
    private final void initTitle(final String sText, final float fontScale, final int iHeight, final boolean moveable, final boolean resizable) {
        this.fontScale = fontScale;
        this.iHeight = iHeight;
        this.moveable = moveable;
        this.resizable = resizable;
        this.setText(sText);
    }
    
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.boxTitle, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawGradient(oSB, nPosX, nPosY, nWidth, titleStatus);
        oSB.setColor(new Color(0.57254905f, 0.49411765f, 0.29411766f, 1.0f));
        Images.gradientFull.draw(oSB, nPosX, nPosY - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
    
    public void drawGradient(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        oSB.setColor(new Color(Colors.COLOR_TITLE.r, Colors.COLOR_TITLE.g, Colors.COLOR_TITLE.b, 0.225f));
        Images.gradientXY.draw(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        if (this.getTime() + 325L >= CFG.currentTimeMillis) {
            final float tAlpha = 0.25f - 0.25f * ((CFG.currentTimeMillis - this.getTime()) / 325.0f);
            oSB.setColor(new Color(Colors.COLOR_TITLE.r, Colors.COLOR_TITLE.g, Colors.COLOR_TITLE.b, tAlpha));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY - CFG.PADDING * 2, nWidth, CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, nPosX, nPosY - 2, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        Renderer.drawText(oSB, this.fontID, this.getText(), this.fontScale, nPosX + (int)(nWidth / 2.0f - this.getTextWidth() / 2.0f), 1 + nPosY + (int)(-this.getHeight() + this.getHeight() / 2.0f - this.getTextHeight() / 2.0f), this.getColorText(titleStatus));
    }
    
    public Color getColorText(final Status titleStatus) {
        switch (titleStatus) {
            case ACTIVE: {
                return MenuTitle.colorActive;
            }
            case HOVERED: {
                return MenuTitle.colorHovered;
            }
            default: {
                return MenuTitle.colorDefault;
            }
        }
    }
    
    public String getText() {
        return this.sText;
    }
    
    public void setText(final String sText) {
        this.sText = sText;
        this.setTextWidth(-1);
        if (sText != null && this.getTextWidth() < 0) {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.setTextWidth((int)(glyphLayout.width * this.fontScale));
            this.setTextHeight((int)(glyphLayout.height * this.fontScale));
        }
    }
    
    public final int getHeight() {
        return this.iHeight;
    }
    
    public final boolean getMoveable() {
        return this.moveable;
    }
    
    public final int getTextWidth() {
        return this.iTextWidth;
    }
    
    public final int getTextHeight() {
        return this.iTextHeight;
    }
    
    public final void setTextWidth(final int iTextWidth) {
        this.iTextWidth = iTextWidth;
    }
    
    public final void setTextHeight(final int iTextHeight) {
        this.iTextHeight = iTextHeight;
    }
    
    public final boolean getResizable() {
        return this.resizable;
    }
    
    public long getTime() {
        return 0L;
    }
    
    public void action() {
    }
    
    public void onHovered() {
    }
    
    static {
        MenuTitle.colorDefault = new Color(0.92941177f, 0.99607843f, 1.0f, 1.0f);
        MenuTitle.colorHovered = new Color(0.78431374f, 0.78431374f, 0.7647059f, 1.0f);
        MenuTitle.colorActive = new Color(0.54901963f, 0.5882353f, 0.60784316f, 1.0f);
    }
}
