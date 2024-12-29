// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import aoh.kingdoms.history.mainGame.Player.MessageTypes.PMessage;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu_element.button.Button;

public class MessageButton extends Button
{
    public static final int ANIMATION_TIME = 350;
    public long lTime;
    public static int messageMaskY;
    private int iCivID;
    public String key;
    public int imageID;
    public int expiresTurnID;
    public int imgW;
    public int imgH;
    
    public MessageButton(final String key, final int iCivID, final int imageID, final int expiresTurnID, final long time, final int iPosX, final int iPosY, final boolean isClickable) {
        this.lTime = 0L;
        this.iCivID = iCivID;
        this.key = key;
        this.imageID = imageID;
        this.expiresTurnID = expiresTurnID;
        this.imgW = (int)(ImageManager.getImage(imageID).getWidth() * 0.75f);
        this.imgH = (int)(ImageManager.getImage(imageID).getHeight() * 0.75f);
        this.lTime = time;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.messageBG).getWidth() + this.getExtraWidth(), ImageManager.getImage(Images.messageBG).getHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean isActive) {
        if (this.lTime + 350L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - this.getHeight() + (int)(this.getHeight() * ((CFG.currentTimeMillis - this.lTime) / 350.0f));
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 1.0f));
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + ImageManager.getImage(Images.messageBG).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.messageBG).getWidth() / 2, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.5f));
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + ImageManager.getImage(Images.messageBG).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getWidth() - ImageManager.getImage(Images.messageBG).getWidth() / 2, this.getHeight() - CFG.PADDING * 2);
        if (this.getIsHovered()) {
            ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + ImageManager.getImage(Images.messageBG).getWidth() / 2 + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getWidth() - ImageManager.getImage(Images.messageBG).getWidth() / 2, this.getHeight() - CFG.PADDING * 2);
        }
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + ImageManager.getImage(Images.messageBG).getWidth() + this.getExtraWidth() / 2 - this.imgW / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.imgH / 2 + iTranslateY, this.imgW, this.imgH);
        ImageManager.getImage(Images.messageBG).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setShader(Renderer.shaderAlpha);
        Game.getCiv(this.iCivID).getFlag().getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.messageMask).draw(oSB, this.getPosX() + ImageManager.getImage(Images.messageBG).getWidth() / 2 - ImageManager.getImage(Images.messageMask).getWidth() / 2 + iTranslateX, this.getPosY() + MessageButton.messageMaskY + ImageManager.getImage(Images.messageBG).getHeight() / 2 - ImageManager.getImage(Images.messageMask).getHeight() / 2 + iTranslateY);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.messageOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
            ImageManager.getImage(Images.messageOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public int getExtraWidth() {
        return this.imgW + CFG.PADDING;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.iCivID;
        }
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = Game.player.getMessage_Hover(this.key);
    }
    
    @Override
    public void actionElement() {
        final PMessage pMessage = Game.player.getMessage(this.key);
        if (pMessage != null) {
            pMessage.actionClick();
        }
        else {
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_MessagesSavePos") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_MessagesSavePos();
                }
            });
        }
    }
    
    @Override
    public void actionElementPPM() {
        final PMessage pMessage = Game.player.getMessage(this.key);
        if (pMessage != null) {
            pMessage.actionClick();
        }
        else {
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_MessagesSavePos") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_MessagesSavePos();
                }
            });
        }
    }
}
