// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;

public class ButtonBuilding2_NotAvailable extends ButtonBuilding2
{
    public String sTitle;
    public int iTitleWidth;
    public int iTitleHeight;
    public String sTitle2;
    public int iTitleWidth2;
    
    public ButtonBuilding2_NotAvailable(final int nProvinceID, final boolean built, final int building, final int buildingID, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean dueToTech, final boolean dueToReligion, final boolean dueToGovernment) {
        super(nProvinceID, built, building, buildingID, iPosX, iPosY, nWidth, isClickable);
        this.sTitle2 = "";
        try {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            if (dueToTech) {
                this.sTitle = Game.lang.get("RequiredTechnology");
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle);
                this.iTitleWidth = (int)glyphLayout.width;
                this.iTitleHeight = (int)glyphLayout.height;
                this.sTitle2 = TechnologyTree.lTechnology.get(BuildingsManager.buildings.get(building).RequiredTechID[buildingID]).Name;
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle2);
                this.iTitleWidth2 = (int)glyphLayout.width;
            }
            else if (dueToReligion && BuildingsManager.buildings.get(building).RequiredReligionID >= 0) {
                this.sTitle = Game.lang.get("Religion");
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle);
                this.iTitleWidth = (int)glyphLayout.width;
                this.iTitleHeight = (int)glyphLayout.height;
                this.sTitle2 = Game.religionManager.getReligion(BuildingsManager.buildings.get(building).RequiredReligionID).Name;
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle2);
                this.iTitleWidth2 = (int)glyphLayout.width;
            }
            else if (dueToReligion && BuildingsManager.buildings.get(building).RequiredGovernmentID >= 0) {
                this.sTitle = Game.lang.get("Government");
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle);
                this.iTitleWidth = (int)glyphLayout.width;
                this.iTitleHeight = (int)glyphLayout.height;
                this.sTitle2 = Game.ideologiesManager.getIdeology(BuildingsManager.buildings.get(building).RequiredGovernmentID).Name;
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle2);
                this.iTitleWidth2 = (int)glyphLayout.width;
            }
            else {
                this.sTitle = Game.lang.get("RequiredBuilding");
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle);
                this.iTitleWidth = (int)glyphLayout.width;
                this.iTitleHeight = (int)glyphLayout.height;
                this.sTitle2 = BuildingsManager.buildings.get(building).Name[Math.max(0, buildingID - 1)];
                glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sTitle2);
                this.iTitleWidth2 = (int)glyphLayout.width;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.6f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.9f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() / 2);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, this.getWidth(), this.getHeight() / 2, false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sTitle, this.getPosX() + this.getWidth() / 2 - this.iTitleWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTitleHeight - CFG.PADDING + iTranslateY, Colors.HOVER_RIGHT);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sTitle2, this.getPosX() + this.getWidth() / 2 - this.iTitleWidth2 / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + iTranslateY, Colors.HOVER_RIGHT2);
    }
}
