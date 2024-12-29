// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement;

public class Button extends MenuElement
{
    protected static final Color COLOR_BUTTON_MENU_HOVER_BG;
    protected Checkbox oCheckbox;
    protected String sText;
    protected int iTextWidth;
    protected int iTextHeight;
    protected int iTextPositionX;
    protected TextPosition textPosition;
    protected boolean checkbox;
    private boolean checkboxState;
    
    public Button() {
        this.sText = null;
        this.iTextWidth = -1;
        this.checkbox = false;
        this.checkboxState = false;
    }
    
    public Button(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        this.sText = null;
        this.iTextWidth = -1;
        this.checkbox = false;
        this.checkboxState = false;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, false);
    }
    
    public Button(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final boolean isClickable) {
        this.sText = null;
        this.iTextWidth = -1;
        this.checkbox = false;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable, true, this.checkboxState = false, false);
    }
    
    public final void init(final String sText, final int fontID, final int nTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final boolean isClickable, final boolean isVisible, final boolean checkbox, final boolean checkboxState) {
        this.typeOfElement = MenuElement_Type.BUTTON;
        this.fontID = fontID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setText(sText);
        this.iTextPositionX = nTextPositionX;
        if (nTextPositionX < 0) {
            this.textPosition = new TextPosition() {
                @Override
                public int getTextPosition() {
                    return Button.this.getWidth() / 2 - Button.this.getTextWidth() / 2;
                }
            };
        }
        else {
            this.textPosition = new TextPosition() {
                @Override
                public int getTextPosition() {
                    return Button.this.iTextPositionX;
                }
            };
        }
        this.checkbox = checkbox;
        this.checkboxState = checkboxState;
        this.oCheckbox = this.buildCheckbox();
        this.setClickable(isClickable);
        this.setVisible(isVisible);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (this.getClickable()) {
            this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        }
        else {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.45f);
            this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
            oSB.setColor(Color.WHITE);
        }
        this.oCheckbox.drawCheckBox(oSB, iTranslateX, iTranslateY, scrollableY);
        this.drawText(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Checkbox() {
                @Override
                public void drawCheckBox(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean scrollableY) {
                    if (Button.this.getCheckboxState()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.25f));
                    }
                    else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.25f));
                    }
                    ImageManager.getImage(Images.gradientHorizontal).draw(oSB, Button.this.getPosX() + iTranslateX, Button.this.getPosY() + 1 + iTranslateY, Button.this.getWidth() / 4, Button.this.getHeight() - 2, false, false);
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, Button.this.getPosX() + iTranslateX, Button.this.getPosY() + 1 + iTranslateY, Button.this.getWidth(), Button.this.getHeight() / 4, false, false);
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, Button.this.getPosX() + iTranslateX, Button.this.getPosY() + Button.this.getHeight() - 1 + iTranslateY - Button.this.getHeight() / 4, Button.this.getWidth(), Button.this.getHeight() / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
            };
        }
        return new Checkbox() {
            @Override
            public void drawCheckBox(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean scrollableY) {
            }
        };
    }
    
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (isActive) {
            ImageManager.getImage(Images.buttonMenuH).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
        }
        else if (this.getIsHovered() && this.getClickable()) {
            oSB.setColor(Button.COLOR_BUTTON_MENU_HOVER_BG);
            ImageManager.getImage(Images.buttonMenuH).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
            oSB.setColor(Color.WHITE);
        }
        else {
            ImageManager.getImage(Images.buttonMenu).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.buttonMenu).getWidth());
            ImageManager.getImage(Images.buttonMenu).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.buttonMenu).getWidth() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buttonMenu).getWidth(), ImageManager.getImage(Images.buttonMenu).getHeight(), true, false);
            oSB.setColor(new Color(Colors.COLOR_BOX_FRAME2.r, Colors.COLOR_BOX_FRAME2.g, Colors.COLOR_BOX_FRAME2.b, Colors.COLOR_BOX_FRAME2.a));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - 2, 1);
            oSB.setColor(Color.WHITE);
        }
    }
    
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
    }
    
    @Override
    public String getText() {
        return this.sText;
    }
    
    @Override
    public String getTextToDraw() {
        return this.sText;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            if (sText != null) {
                final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.getText());
                this.iTextWidth = (int)glyphLayout.width;
                this.iTextHeight = (int)glyphLayout.height;
            }
            else {
                final int n = 0;
                this.iTextHeight = n;
                this.iTextWidth = n;
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        catch (final IllegalArgumentException ex3) {
            CFG.exceptionStack(ex3);
        }
    }
    
    public final void setCheckbox(final boolean checkbox) {
        this.checkbox = checkbox;
    }
    
    @Override
    public boolean getCheckboxState() {
        return this.checkboxState;
    }
    
    @Override
    public void setCheckboxState(final boolean checkboxState) {
        this.checkboxState = checkboxState;
    }
    
    @Override
    public int getTextWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getTextPos() {
        return this.iTextPositionX;
    }
    
    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }
    
    static {
        COLOR_BUTTON_MENU_HOVER_BG = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public interface TextPosition
    {
        int getTextPosition();
    }
    
    interface Checkbox
    {
        void drawCheckBox(final SpriteBatch p0, final int p1, final int p2, final boolean p3);
    }
}
