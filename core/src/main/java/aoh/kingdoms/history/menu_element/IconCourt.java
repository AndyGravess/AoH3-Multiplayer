// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class IconCourt extends Button
{
    public int imageID;
    public int id;
    public int widthDraw;
    
    public IconCourt(final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int id, final int widthDraw) {
        this.imageID = imageID;
        this.id = id;
        this.widthDraw = widthDraw;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        int posX = this.getPosX() + iTranslateX + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
        final int posY = this.getPosY() + iTranslateY;
        if (InGame_CourtOptions2.isOptionHovered) {
            int extraX = 0;
            float perc = 1.0f;
            if (InGame_CourtOptions2.TEXT_TIME + InGame_CourtOptions2.TEXT_ANIMATION_TIME >= CFG.currentTimeMillis) {
                perc = (CFG.currentTimeMillis - InGame_CourtOptions2.TEXT_TIME) / (float)InGame_CourtOptions2.TEXT_ANIMATION_TIME;
                extraX = -InGame_CourtOptions2.textMaxWidth + (int)(InGame_CourtOptions2.textMaxWidth * perc);
            }
            posX += extraX + 1 + this.widthDraw;
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 1.0f * perc));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, posX, posY, InGame_CourtOptions2.textMaxWidth, this.getHeight() + 1);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.9f * perc));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, posX, posY, InGame_CourtOptions2.textMaxWidth, this.getHeight() + 1);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.75f * perc));
            Images.gradientFull.draw(oSB, posX, posY, InGame_CourtOptions2.textMaxWidth, this.getHeight() + 1);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE3.r, Colors.COLOR_GRADIENT_OVER_BLUE3.g, Colors.COLOR_GRADIENT_OVER_BLUE3.b, 0.15f * perc));
            ImageManager.getImage(Images.gradientXYVertical).draw(oSB, posX, posY, this.getTextWidth(), this.getHeight() + 1);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f * perc));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, posX, posY + 1, InGame_CourtOptions2.textMaxWidth, 1);
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, posX, posY + this.getHeight() + 1 - 2, InGame_CourtOptions2.textMaxWidth, 1);
            posX -= this.widthDraw;
            oSB.setColor(Color.WHITE);
            if (extraX != 0) {
                Color nColor = this.getColor(isActive);
                nColor = new Color(nColor.r, nColor.g, nColor.b, Math.min(1.0f, nColor.a * perc));
                Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), posX + super.getWidth() + CFG.PADDING, posY + this.getHeight() / 2 - this.iTextHeight / 2, nColor);
            }
            else {
                Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), posX + super.getWidth() + CFG.PADDING, posY + this.getHeight() / 2 - this.iTextHeight / 2, this.getColor(isActive));
            }
            posX -= extraX + 1;
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.4f));
        Images.gradientXY.draw(oSB, posX, posY, this.widthDraw, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        Images.gradientFull.draw(oSB, posX, posY, this.widthDraw, 1);
        Images.gradientFull.draw(oSB, posX, posY + this.getHeight() - 1, this.widthDraw, 1);
        oSB.setColor(Color.WHITE);
        if (InGame_CourtOptions.iActiveID == this.id) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 1.0f));
            ImageManager.getImage(Images.gradientXYVertical).draw(oSB, posX, posY, this.widthDraw, this.getHeight());
            ImageManager.getImage(Images.gradientXYVertical).draw(oSB, posX, posY + this.getHeight() / 2, this.widthDraw, this.getHeight() / 2);
        }
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65f));
            ImageManager.getImage(Images.gradientXYVertical).draw(oSB, posX, posY, this.widthDraw, this.getHeight());
        }
        if (InGame_CourtOptions.iActiveID == this.id) {
            oSB.setColor(MainMenu.sparksColors);
            MenuManager.sparksAnimationSidebarActive.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        else if (this.getIsHovered() || isActive) {
            oSB.setColor(MainMenu.sparksColors);
            MenuManager.sparksAnimationSidebar.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static Color getColor_gradientXY() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.7f);
    }
    
    public static Color getColor_gradientFull() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.45f);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (InGame_CourtOptions.iActiveID == this.id) {
            return Colors.HOVER_GOLD;
        }
        return super.getColor(isActive);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            final int imgW = (int)(ImageManager.getImage(this.getImageID()).getWidth() * 1.1f);
            final int imgH = (int)(ImageManager.getImage(this.getImageID()).getHeight() * 1.1f);
            ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + iTranslateX + this.widthDraw / 2 - imgW / 2, this.getPosY() + iTranslateY + this.getHeight() / 2 - imgH / 2, imgW, imgH);
        }
        else {
            ImageManager.getImage(this.getImageID()).draw(oSB, this.getPosX() + iTranslateX + this.widthDraw / 2 - ImageManager.getImage(this.getImageID()).getWidth() / 2, this.getPosY() + iTranslateY + this.getHeight() / 2 - ImageManager.getImage(this.getImageID()).getHeight() / 2);
        }
    }
    
    @Override
    public int getWidth() {
        if (InGame_CourtOptions2.isOptionHovered) {
            return super.getWidth() + InGame_CourtOptions2.textMaxWidth / 2;
        }
        return super.getWidth();
    }
    
    public int getImageID() {
        return this.imageID;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
}
