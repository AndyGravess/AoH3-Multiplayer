// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextFlags extends Button
{
    public List<Integer> lCivID;
    public int iCivsSize;
    public int flagWidth;
    public int flagHeight;
    
    public TextFlags(final String sText, final List<Integer> tCivs, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        this.lCivID = new ArrayList<Integer>();
        this.iCivsSize = 0;
        this.init(sText, CFG.FONT_REGULAR_SMALL, -1, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.lCivID = tCivs;
        this.iCivsSize = this.lCivID.size();
        this.flagWidth = (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale());
        this.flagHeight = (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale());
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        for (int i = 0; i < this.iCivsSize; ++i) {
            if (this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + this.flagWidth + 2 < this.getWidth()) {
                try {
                    if (this.lCivID.get(i) >= 0) {
                        Game.getCiv(this.lCivID.get(i)).getFlag().draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
                    }
                    else {
                        ImageManager.getImage(Images.rebelsFlag).draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
                    }
                }
                catch (final IndexOutOfBoundsException e) {
                    ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
                }
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + (this.flagWidth + CFG.PADDING) * i + CFG.PADDING * 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f) + iTranslateY, this.flagWidth, this.flagHeight);
            }
        }
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStatsHover(isActive, this.getIsHovered());
    }
    
    private final float getImageScale() {
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), Colors.HOVER_GOLD));
        for (int i = 0; i < this.lCivID.size(); ++i) {
            if (this.lCivID.get(i) < 0) {
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, CFG.PADDING, 0));
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Flag(this.lCivID.get(i), CFG.PADDING, 0));
            }
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        for (int i = 0; i < this.lCivID.size(); ++i) {
            if (this.lCivID.get(i) < 0) {
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, 0, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Rebels")));
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Flag(this.lCivID.get(i), 0, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(this.lCivID.get(i)).getCivName()));
            }
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
