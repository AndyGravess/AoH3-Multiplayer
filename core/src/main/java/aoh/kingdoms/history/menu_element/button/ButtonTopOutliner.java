// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menusInGame.Right.InGame_Right;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Touch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menusInGame.InGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonTopOutliner extends Button
{
    public int imageID;
    
    public ButtonTopOutliner(final int iPosY, final int nHeight) {
        this.imageID = Images.outliner;
        this.init(this.sText, 0, this.iTextPositionX, 0, iPosY, ImageManager.getImage(this.imageID).getWidth() + getPaddingX(), nHeight, true, true, false, false);
    }
    
    public ButtonTopOutliner(final int iPosX, final int iPosY, final int nHeight, final int imageID) {
        this.imageID = imageID;
        this.init(this.sText, 0, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(imageID).getWidth() + getPaddingX(), nHeight, true, true, false, false);
    }
    
    public ButtonTopOutliner(final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int imageID) {
        this.imageID = imageID;
        this.init(this.sText, 0, this.iTextPositionX, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        Images.gradientFull.draw(oSB, this.getPosX() + InGame.outlinerExtraX + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), this.getHeight() - 2);
        Images.gradientXY.draw(oSB, this.getPosX() + InGame.outlinerExtraX + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), this.getHeight() - 2);
        Images.gradientXY.draw(oSB, this.getPosX() + InGame.outlinerExtraX + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), this.getHeight() - 2, false, true);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.5f));
            Images.gradientXY.draw(oSB, this.getPosX() + InGame.outlinerExtraX + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
            Images.gradientXY.draw(oSB, this.getPosX() + InGame.outlinerExtraX + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING);
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (!CFG.isDesktop()) {
            if (Touch.selectArmiesMode) {
                oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.15f));
            }
            else {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.075f));
            }
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY, ImageManager.getImage(this.imageID).getHeight(), ImageManager.getImage(this.imageID).getHeight());
            if (Touch.selectArmiesMode) {
                oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.375f));
            }
            else {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.275f));
            }
            Renderer.drawBox2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY, ImageManager.getImage(this.imageID).getHeight(), ImageManager.getImage(this.imageID).getHeight(), 1.0f);
        }
        else {
            oSB.setColor(this.getColor(isActive));
            ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 + InGame.outlinerExtraX - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static int getPaddingX() {
        return CFG.PADDING * 4;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats_Outliner(isActive, this.getIsHovered());
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (!CFG.isDesktop()) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectMoreThanOneArmy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(InGame_Right.outlinerInView ? Game.lang.get("HidePinned") : Game.lang.get("ShowPinned"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements, true);
    }
}
