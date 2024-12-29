// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu_element.MessageWar;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonFlag_DiplomacyWar extends ButtonFlag_Diplomacy
{
    public String warKey;
    public Color textColor;
    
    public ButtonFlag_DiplomacyWar(final int iCivID, final int iPosX, final int iPosY, final boolean isClickable, final String warScore, final Color textColor, final String warKey) {
        super(iCivID, iPosX, iPosY, isClickable);
        this.textColor = textColor;
        this.warKey = warKey;
        this.setHeight(this.getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2);
        this.fontID = CFG.FONT_REGULAR_SMALL;
        this.setText(warScore);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.75f));
        Images.gradientFull.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + CFG.PADDING / 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.iTextHeight + CFG.PADDING);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.iTextWidth / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + CFG.PADDING + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.getIsHovered()) {
            return Colors.getColorTopStats3(isActive, this.getIsHovered());
        }
        return this.textColor;
    }
    
    @Override
    public void actionElement() {
        if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(this.warKey)) {
            Game.menuManager.setVisibleInGame_War(false);
        }
        else {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.clearActiveArmy();
            Game.menuManager.showInGame_Battle_HideMenus();
            InGame_War.key = this.warKey;
            Game.menuManager.rebuildInGame_War();
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_WAR_VIEW) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_WAR_VIEW);
            }
            else {
                Game.mapModes.updateWarView(InGame_War.key);
            }
        }
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = MessageWar.getHoverWar(this.warKey, this.iCivID);
    }
}
