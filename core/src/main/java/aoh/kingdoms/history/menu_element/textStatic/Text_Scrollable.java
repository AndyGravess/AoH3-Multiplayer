// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement;

public class Text_Scrollable extends MenuElement
{
    protected String sText;
    protected int iTextWidth;
    protected int iTextHeight;
    private float fTextScale;
    private Color textColor;
    private boolean center;
    protected TextPosition textPosition;
    private boolean scrollable;
    private int iScrollPosX;
    private boolean scrollInRightDirection;
    private long lTime;
    private DrawText drawText;
    
    public Text_Scrollable(final String sText, final int iPosX, final int iPosY, final int iWidth, final Color textColor) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.fTextScale = 1.0f;
        this.center = false;
        this.scrollable = false;
        this.iScrollPosX = 0;
        this.scrollInRightDirection = true;
        this.init(sText, iPosX, iPosY, iWidth, 0, textColor, 1.0f, 0);
    }
    
    public Text_Scrollable(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final Color textColor) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.fTextScale = 1.0f;
        this.center = false;
        this.scrollable = false;
        this.iScrollPosX = 0;
        this.scrollInRightDirection = true;
        this.init(sText, iPosX, iPosY, iWidth, iHeight, textColor, 1.0f, 0);
    }
    
    public Text_Scrollable(final String sText, final int iPosX, final int iPosY, final int iWidth, final Color textColor, final float nTextScale) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.fTextScale = 1.0f;
        this.center = false;
        this.scrollable = false;
        this.iScrollPosX = 0;
        this.scrollInRightDirection = true;
        this.init(sText, iPosX, iPosY, iWidth, 0, textColor, nTextScale, 0);
    }
    
    public Text_Scrollable(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final Color textColor, final float nTextScale) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.fTextScale = 1.0f;
        this.center = false;
        this.scrollable = false;
        this.iScrollPosX = 0;
        this.scrollInRightDirection = true;
        this.init(sText, iPosX, iPosY, iWidth, iHeight, textColor, nTextScale, 0);
    }
    
    public Text_Scrollable(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final Color textColor, final float nTextScale, final int iTextPosition) {
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.fTextScale = 1.0f;
        this.center = false;
        this.scrollable = false;
        this.iScrollPosX = 0;
        this.scrollInRightDirection = true;
        this.init(sText, iPosX, iPosY, iWidth, iHeight, textColor, nTextScale, iTextPosition);
    }
    
    private final void init(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final Color textColor, final float nTextScale, final int iTextPosition) {
        this.typeOfElement = MenuElement_Type.TEXT_SCROLLABLE;
        this.iScrollPosX = iTextPosition;
        this.fTextScale = nTextScale;
        this.center = (iTextPosition < 0);
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        if (iHeight > 0) {
            this.setHeight(iHeight);
        }
        this.setText(sText);
        this.textColor = textColor;
        this.updateTextPosition();
        if (this.fTextScale != 1.0f) {
            this.drawText = new DrawText() {
                @Override
                public void draw_Element(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                    Renderer.setFontScale(Text_Scrollable.this.fTextScale);
                    Renderer.drawTextWithShadow(oSB, Text_Scrollable.this.fontID, Text_Scrollable.this.getText(), Text_Scrollable.this.getPosX() + Text_Scrollable.this.textPosition.getTextPosition(isActive) + iTranslateX, Text_Scrollable.this.getPosY() + Text_Scrollable.this.getHeight() / 2 - Text_Scrollable.this.iTextHeight / 2 + iTranslateY, Text_Scrollable.this.getColor(isActive));
                    Renderer.resetFontScale();
                }
            };
        }
        else {
            this.drawText = new DrawText() {
                @Override
                public void draw_Element(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                    Renderer.drawTextWithShadow(oSB, Text_Scrollable.this.fontID, Text_Scrollable.this.getText(), Text_Scrollable.this.getPosX() + Text_Scrollable.this.textPosition.getTextPosition(isActive) + iTranslateX, Text_Scrollable.this.getPosY() + Text_Scrollable.this.getHeight() / 2 - Text_Scrollable.this.iTextHeight / 2 + iTranslateY, Text_Scrollable.this.getColor(isActive));
                }
            };
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (!Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight() - CFG.PADDING * 2)) {
            return;
        }
        this.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        this.draw_EndClip(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }
    
    protected void draw_Element(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        try {
            this.drawText.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        }
        catch (final NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void draw_EndClip(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.clipView_End(oSB);
    }
    
    public Color getColor(final boolean isActive) {
        return isActive ? Colors.BUTTON_TEXT_ACTIVE : (this.getIsHovered() ? Colors.BUTTON_TEXT_HOVERED : (this.getClickable() ? this.textColor : new Color(0.78f, 0.78f, 0.78f, 0.7f)));
    }
    
    @Override
    public final String getText() {
        return this.sText;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            Renderer.setFontScale(this.fTextScale);
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            this.iTextHeight = (int)Renderer.glyphLayout.height;
            Renderer.resetFontScale();
            this.updateTextPosition();
            if (this.getHeight() < this.iTextHeight) {
                this.setHeight(this.iTextHeight);
            }
        }
        catch (final NullPointerException ex) {}
    }
    
    protected final void updateTextPosition() {
        if (this.getTextWidth() > this.getWidth() + CFG.PADDING) {
            this.textPosition = new TextPosition() {
                @Override
                public int getTextPosition(final boolean isActive) {
                    if (isActive) {
                        return Text_Scrollable.this.iScrollPosX;
                    }
                    if (Text_Scrollable.this.lTime + 30L <= CFG.currentTimeMillis) {
                        if (Text_Scrollable.this.scrollInRightDirection) {
                            --Text_Scrollable.this.iScrollPosX;
                            if (Text_Scrollable.this.getWidth() - Text_Scrollable.this.iScrollPosX >= Text_Scrollable.this.getTextWidth() + CFG.PADDING) {
                                Text_Scrollable.this.scrollInRightDirection = !Text_Scrollable.this.scrollInRightDirection;
                            }
                        }
                        else {
                            ++Text_Scrollable.this.iScrollPosX;
                            if (Text_Scrollable.this.iScrollPosX == CFG.PADDING) {
                                Text_Scrollable.this.scrollInRightDirection = !Text_Scrollable.this.scrollInRightDirection;
                            }
                        }
                        Text_Scrollable.this.lTime = CFG.currentTimeMillis;
                    }
                    return Text_Scrollable.this.iScrollPosX;
                }
            };
            this.scrollable = true;
        }
        else {
            if (this.center) {
                this.textPosition = new TextPosition() {
                    @Override
                    public int getTextPosition(final boolean isActive) {
                        return Text_Scrollable.this.getWidth() / 2 - Text_Scrollable.this.getTextWidth() / 2;
                    }
                };
            }
            else {
                this.textPosition = new TextPosition() {
                    @Override
                    public int getTextPosition(final boolean isActive) {
                        return 0;
                    }
                };
            }
            this.scrollable = false;
        }
        this.iScrollPosX = 0;
        this.scrollInRightDirection = true;
    }
    
    @Override
    public void setScrollPosX(final int scrollPosX) {
        this.iScrollPosX = scrollPosX;
        if (this.iScrollPosX > CFG.PADDING) {
            this.iScrollPosX = CFG.PADDING;
            this.scrollInRightDirection = true;
        }
        else if (this.getTextWidth() + CFG.PADDING - this.getWidth() < -this.iScrollPosX) {
            this.iScrollPosX = -(this.getTextWidth() + CFG.PADDING - this.getWidth());
            this.scrollInRightDirection = false;
        }
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
    public int getScrollPosX() {
        return this.iScrollPosX;
    }
    
    @Override
    public boolean getScrollable() {
        return this.scrollable;
    }
    
    @Override
    public void scrollByWheel(final int nScoll) {
        this.scrollInRightDirection = (nScoll < 0);
        this.setScrollPosX(this.getScrollPosX() + nScoll);
        this.lTime = CFG.currentTimeMillis + 375L;
    }
    
    private interface DrawText
    {
        void draw_Element(final SpriteBatch p0, final int p1, final int p2, final boolean p3, final boolean p4);
    }
    
    interface TextPosition
    {
        int getTextPosition(final boolean p0);
    }
}
