// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.List;
import aoh.kingdoms.history.menu.Hover;

public class Toast implements Hover
{
    public static final float TEXT_SCALE = 0.75f;
    private List<MenuElement_HoverElement> lElements;
    private int iElementsSize;
    public static final int TIME_INVIEW_VERY_SHORT = 1000;
    public static final int TIME_INVIEW_SHORT = 1750;
    public static final int TIME_INVIEW_STANDARD = 2500;
    public static final int TIME_INVIEW_LONG = 3500;
    public static final int TIME_INVIEW_VERY_LONG = 6000;
    public static final int TIME_INVIEW_VERY_VERY_LONG = 10000;
    private static final float TIME_START_OPACITY_PERCENTAGE = 0.4f;
    public boolean inView;
    private int iTimeInView;
    private long lTime;
    private float fAlpha;
    public int iPosX;
    public int iPosY;
    public int iFontID;
    public int iHeight;
    public int iWidth;
    public int iMaxWidth;
    private int iWidthOver;
    private int iScrollPosX;
    private boolean backAnimation;
    
    public Toast(final String sText) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(this.initText(sText), 2500, 0);
    }
    
    public Toast(final String sText, final int iFontID) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(this.initText(sText), 2500, iFontID);
    }
    
    public Toast(final String sText, final Color nColor) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(this.initText(sText, nColor), 2500, this.iFontID);
    }
    
    public Toast(final String sText, final int iFontID, final int nTimeInView) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(this.initText(sText), nTimeInView, iFontID);
    }
    
    public Toast(final String sText, final int iFontID, final int nTimeInView, final Color nColor) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(this.initText(sText, nColor), nTimeInView, iFontID);
    }
    
    public Toast(final String sText, final int iFontID, final int nTimeInView, final Color nColor, final int nPosX, final int nPosY) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.iPosX = nPosX;
        this.iPosY = nPosY;
        this.init(this.initText(sText, nColor), nTimeInView, iFontID);
    }
    
    public Toast(final String sText, final int iFontID, final boolean visible) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(this.initText(sText), 2500, iFontID);
        this.inView = visible;
    }
    
    public Toast(final List<MenuElement_HoverElement> nElements) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(nElements, 2500, 0);
    }
    
    public Toast(final List<MenuElement_HoverElement> nElements, final int iFontID) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(nElements, 2500, iFontID);
    }
    
    public Toast(final List<MenuElement_HoverElement> nElements, final int iFontID, final int nTimeInView) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.init(nElements, nTimeInView, iFontID);
    }
    
    public Toast(final List<MenuElement_HoverElement> nElements, final int iFontID, final int nTimeInView, final int nPosX, final int nPosY) {
        this.iElementsSize = 0;
        this.inView = false;
        this.iTimeInView = 2500;
        this.lTime = 0L;
        this.fAlpha = 1.0f;
        this.iPosX = -1;
        this.iPosY = -1;
        this.iFontID = 0;
        this.iHeight = 0;
        this.iWidth = 0;
        this.iMaxWidth = 0;
        this.iWidthOver = 0;
        this.iScrollPosX = 0;
        this.backAnimation = true;
        this.iPosX = nPosX;
        this.iPosY = nPosY;
        this.init(nElements, nTimeInView, iFontID);
    }
    
    private final List<MenuElement_HoverElement> initText(final String sText) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(sText));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return nElements;
    }
    
    private final List<MenuElement_HoverElement> initText(final String sText, final Color nColor) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(sText, nColor));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return nElements;
    }
    
    private final void init(final List<MenuElement_HoverElement> nElements, final int nTimeInView, final int iFontID) {
        this.lElements = nElements;
        this.iElementsSize = this.lElements.size();
        this.iWidth = 0;
        this.iFontID = iFontID;
        for (int i = 0; i < this.iElementsSize; ++i) {
            if (this.lElements.get(i).getWidth() > CFG.GAME_WIDTH - CFG.PADDING * 2 && this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2 > this.iWidthOver) {
                this.iWidthOver = this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2;
            }
        }
        if (this.iWidthOver > 0) {
            this.iScrollPosX = this.iWidthOver + CFG.PADDING * 10;
            this.lTime = CFG.currentTimeMillis;
        }
        for (int i = 0; i < this.iElementsSize; ++i) {
            if (this.lElements.get(i).getWidth() > this.iWidth) {
                this.iWidth = this.lElements.get(i).getWidth();
                this.iMaxWidth = this.lElements.get(i).getWidth();
            }
        }
        this.iWidth += CFG.PADDING * 6;
        this.iHeight = CFG.TEXT_HEIGHT * this.iElementsSize + CFG.PADDING * (this.iElementsSize - 1) + CFG.PADDING * 4;
        this.inView = true;
        this.fAlpha = 1.0f;
        this.lTime = CFG.currentTimeMillis;
        this.iTimeInView = nTimeInView;
    }
    
    @Override
    public final void draw(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    @Override
    public final void drawAlwaysOver(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    @Override
    public final void drawAlwaysOver_Mobile(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    @Override
    public final void drawAlwaysBelow(final SpriteBatch oSB, final int nPosX, final int nPosY) {
        this.draw_Hover(oSB, nPosX, nPosY);
    }
    
    protected final int getScrollPosX() {
        if (this.iWidthOver > 0) {
            if (this.backAnimation) {
                if (this.lTime + 1500L < CFG.currentTimeMillis && this.iScrollPosX-- < -CFG.PADDING) {
                    this.backAnimation = !this.backAnimation;
                    this.lTime = CFG.currentTimeMillis;
                }
            }
            else if (this.lTime + 1000L < CFG.currentTimeMillis && this.iScrollPosX++ > this.iWidthOver + CFG.PADDING * 10) {
                this.backAnimation = !this.backAnimation;
                this.lTime = CFG.currentTimeMillis;
            }
            return this.iScrollPosX;
        }
        return 0;
    }
    
    @Override
    public void drawProvinceInfo(final SpriteBatch oSB, final int nPosX, final int nPosY) {
    }
    
    @Override
    public final void draw_Hover(final SpriteBatch oSB, int nPosX, int nPosY) {
        final int tempScrollX = this.getScrollPosX();
        if (this.iPosX != -1) {
            nPosX = this.iPosX;
            nPosY = this.iPosY;
            if (this.iPosX + this.iWidth > CFG.GAME_WIDTH) {
                this.iPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING * 2;
            }
        }
        else {
            nPosX = CFG.GAME_WIDTH / 2 - this.iWidth / 2;
            nPosY = CFG.GAME_HEIGHT - CFG.PADDING * 2 - this.iHeight - nPosY;
        }
        if (this.lTime + this.iTimeInView < CFG.currentTimeMillis) {
            this.inView = false;
        }
        else if (this.lTime + (int)(this.iTimeInView * 0.4f) < CFG.currentTimeMillis) {
            this.fAlpha = Renderer.getColorStep(255, 0, (int)(CFG.currentTimeMillis - this.lTime - (int)(this.iTimeInView * 0.4f)), this.iTimeInView - (int)(this.iTimeInView * 0.4f));
            if (this.fAlpha < 0.0f) {
                this.fAlpha = 0.0f;
            }
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.fAlpha));
        Renderer.drawBox(oSB, Images.buttonGame, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight, this.fAlpha);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.75f * this.fAlpha));
        Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + 2, this.iWidth, CFG.PADDING, false, true);
        Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 2 - CFG.PADDING, this.iWidth, CFG.PADDING);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.fAlpha));
        for (int i = 0; i < this.iElementsSize; ++i) {
            this.lElements.get(i).draw(oSB, nPosX + tempScrollX + CFG.PADDING * 3, nPosY + CFG.PADDING - CFG.PADDING / 2 + CFG.TEXT_HEIGHT * i + CFG.PADDING * i, this.fAlpha, this.iMaxWidth);
        }
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public final void draw_HoverWithoutAnimation(final SpriteBatch oSB, final int nPosX, int nPosY) {
        final int tempScrollX = this.getScrollPosX();
        if (this.lTime + this.iTimeInView < CFG.currentTimeMillis) {
            this.inView = false;
        }
        else if (this.lTime + (int)(this.iTimeInView * 0.4f) < CFG.currentTimeMillis) {
            this.fAlpha = Renderer.getColorStep(255, 0, (int)(CFG.currentTimeMillis - this.lTime - (int)(this.iTimeInView * 0.4f)), this.iTimeInView - (int)(this.iTimeInView * 0.4f));
            if (this.fAlpha < 0.0f) {
                this.fAlpha = 0.0f;
            }
        }
        if (nPosY + this.iHeight > CFG.GAME_HEIGHT - CFG.PADDING * 2) {
            nPosY = CFG.GAME_HEIGHT - CFG.PADDING * 2 - this.iHeight;
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.fAlpha));
        Renderer.drawBox(oSB, Images.buttonGame, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight, this.fAlpha);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.75f * this.fAlpha));
        Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + 2, this.iWidth, CFG.PADDING, false, true);
        Images.gradientXY.draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 2 - CFG.PADDING, this.iWidth, CFG.PADDING);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        Renderer.setFontScale(0.9f);
        for (int i = 0; i < this.iElementsSize; ++i) {
            this.lElements.get(i).draw(oSB, nPosX + tempScrollX + CFG.PADDING * 3, nPosY + CFG.PADDING + CFG.TEXT_HEIGHT * i + CFG.PADDING * i, this.fAlpha, this.iMaxWidth);
        }
        Renderer.resetFontScale();
        oSB.setColor(Color.WHITE);
    }
    
    public final void setInView(final boolean inView) {
        this.inView = inView;
    }
    
    public final void setInView(final String sText) {
        this.setInView(sText, 0);
    }
    
    public final void setInView(final String sText, final int fontID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(sText));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.init(nElements, 2500, fontID);
        this.setInView(true);
    }
}
