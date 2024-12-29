// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.textures.ImageManager;

public class MenuTitleIMG_War extends MenuTitle
{
    public int imageID;
    public String sTextLeft;
    public int iTextLeftWidth;
    public String sTextRight;
    public int iTextRightWidth;
    public String sTextLeft2;
    public int iTextLeftWidth2;
    public String sTextRight2;
    public int iTextRightWidth2;
    public String sText2;
    public int iText2Width;
    public int iLastTurnID;
    
    public MenuTitleIMG_War(final String sText, final String sTextLeft, final String sTextRight, final String sTextLeft2, final String sTextRight2, final boolean movable, final boolean resizable, final int imageID) {
        super(sText, ImageManager.getImage(imageID).getHeight(), movable, resizable);
        this.iTextLeftWidth = 0;
        this.iTextRightWidth = 0;
        this.iTextLeftWidth2 = 0;
        this.iTextRightWidth2 = 0;
        this.iText2Width = 0;
        this.iLastTurnID = 0;
        this.imageID = imageID;
        this.sTextLeft = sTextLeft;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sTextLeft);
        this.iTextLeftWidth = (int)glyphLayout.width;
        this.sTextRight = sTextRight;
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sTextRight);
        this.iTextRightWidth = (int)glyphLayout.width;
        this.sTextLeft2 = sTextLeft2;
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), sTextLeft2);
        this.iTextLeftWidth2 = (int)glyphLayout.width;
        this.sTextRight2 = sTextRight2;
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), sTextRight2);
        this.iTextRightWidth2 = (int)glyphLayout.width;
        try {
            this.sText2 = Game_Calendar.getNumOfDates_ByTurnID(WarManager.lWars.get(InGame_War.key).iWarTurnID);
            glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sText2);
            this.iText2Width = (int)glyphLayout.width;
        }
        catch (final Exception ex) {
            Game.menuManager.setVisibleInGame_War(false);
        }
        this.iLastTurnID = Game_Calendar.TURN_ID;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        ImageManager.getImage(this.imageID).draw(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawGradient(oSB, nPosX, nPosY, nWidth, titleStatus);
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        if (this.iLastTurnID != Game_Calendar.TURN_ID) {
            try {
                if (WarManager.lWars.containsKey(InGame_War.key)) {
                    this.sText2 = Game_Calendar.getNumOfDates_ByTurnID(WarManager.lWars.get(InGame_War.key).iWarTurnID);
                    Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sText2);
                    this.iText2Width = (int)Renderer.glyphLayout.width;
                    this.iLastTurnID = Game_Calendar.TURN_ID;
                }
            }
            catch (final Exception ex) {
                if (!WarManager.lWars.containsKey(InGame_War.key)) {
                    Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Wars();
                        }
                    });
                }
                CFG.exceptionStack(ex);
            }
        }
        Renderer.drawText(oSB, this.fontID, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), this.getColorText(titleStatus));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText2, nPosX + nWidth / 2 - this.iText2Width / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sTextLeft2, nPosX + CFG.PADDING + ImageManager.getImage(Images.rulerFrame).getWidth() / 2 - this.iTextLeftWidth2 / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorDefault);
        Renderer.drawText(oSB, CFG.FONT_BOLD_SMALL, this.sTextRight2, nPosX + nWidth - Images.boxTitleBORDERWIDTH - CFG.PADDING - ImageManager.getImage(Images.rulerFrame).getWidth() / 2 - this.iTextRightWidth2 / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorDefault);
        if (titleStatus == Status.HOVERED || titleStatus == Status.ACTIVE) {
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sTextLeft, nPosX + CFG.PADDING + ImageManager.getImage(Images.rulerFrame).getWidth() / 2 - this.iTextLeftWidth / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), MenuTitle.colorDefault);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sTextRight, nPosX + nWidth - Images.boxTitleBORDERWIDTH - CFG.PADDING - ImageManager.getImage(Images.rulerFrame).getWidth() / 2 - this.iTextRightWidth / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), MenuTitle.colorDefault);
        }
    }
}
