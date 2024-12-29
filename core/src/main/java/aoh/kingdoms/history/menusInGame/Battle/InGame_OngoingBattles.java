// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Battle;

import aoh.kingdoms.history.map.battles.Battle;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.GameValues;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InGame_OngoingBattles
{
    public static void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY) {
        if (GameValues.battle.DRAW_BATTLE_NOT_IN_VIEW) {
            for (int i = 0, iSize = Game.getCiv(Game.player.iCivID).inBattles.size(); i < iSize; ++i) {
                final Battle battle = Game.battleManager.getBattle(Game.getCiv(Game.player.iCivID).inBattles.get(i));
                if (battle != null && !Game.getProvince(battle.provinceID).getDrawProvince()) {
                    final int posX = Math.max(0, Math.min(CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT4, Game.getProvince(battle.provinceID).iCenterShiftX + Game.mapCoords.getPosX()));
                    final int posY = Math.max(0, Math.min(CFG.GAME_HEIGHT - CFG.PADDING * 2, Game.getProvince(battle.provinceID).iCenterShiftY + Game.mapCoords.getPosY()));
                    oSB.setColor(DiplomacyManager.COLOR_BATTLE);
                    if (posY == 0) {
                        Images.gradientXY.draw(oSB, iTranslateX + posX, iTranslateY + posY, CFG.BUTTON_HEIGHT4, CFG.PADDING * 2, false, true);
                    }
                    else if (posY == CFG.GAME_HEIGHT - CFG.PADDING * 2) {
                        Images.gradientXY.draw(oSB, iTranslateX + posX, iTranslateY + posY, CFG.BUTTON_HEIGHT4, CFG.PADDING * 2, false, false);
                    }
                    else if (posX == 0) {
                        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, iTranslateX + posX, iTranslateY + posY, CFG.PADDING * 2, CFG.BUTTON_HEIGHT4, false, false);
                    }
                    else {
                        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, iTranslateX + CFG.GAME_WIDTH - CFG.PADDING * 2, iTranslateY + posY, CFG.PADDING * 2, CFG.BUTTON_HEIGHT4, true, false);
                    }
                    oSB.setColor(Color.WHITE);
                }
            }
        }
    }
}
