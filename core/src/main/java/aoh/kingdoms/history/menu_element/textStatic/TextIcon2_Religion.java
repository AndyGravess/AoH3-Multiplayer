// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ReligionTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon2_Religion extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int religionID;
    private int imgWidth;
    private int imgHeight;
    public int iProvinceID;
    
    public TextIcon2_Religion(final String sText, final int religionID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int iProvinceID) {
        this.imgWidth = 0;
        this.imgHeight = 0;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.religionID = religionID;
        this.iProvinceID = iProvinceID;
        this.imgWidth = (int)(Game.religionManager.religionImages.get(religionID).getWidth() * this.getImageScale());
        this.imgHeight = (int)(Game.religionManager.religionImages.get(religionID).getHeight() * this.getImageScale());
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - CFG.PADDING && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static Color getColor_gradientXY() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.7f);
    }
    
    public static Color getColor_gradientFull() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.45f);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH(), 1.0f);
        oSB.setColor(Color.WHITE);
        if (Game.getProvince(this.iProvinceID).religionConversion != null) {
            oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH(), 1.0f);
        }
        oSB.setColor(Color.WHITE);
        oSB.setColor(getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH());
        oSB.setColor(getColor_gradientFull());
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        try {
            if (Game.getProvince(this.iProvinceID).religionConversion != null) {
                oSB.setColor(new Color(ProvinceDraw.progressBar));
                Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, (int)(this.getWidth() * (1.0f - Game.getProvince(this.iProvinceID).religionConversion.daysLeft / (float)Game.getProvince(this.iProvinceID).religionConversion.investTime)), this.getTextH(), this.getWidth());
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (this.getClickable() && this.getIsHovered() && TextIcon2_Religion.animationState >= 0) {
            if (TextIcon2_Religion.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_Religion.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_Religion.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++TextIcon2_Religion.animationState;
                    TextIcon2_Religion.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - TextIcon2_Religion.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (TextIcon2_Religion.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    TextIcon2_Religion.animationState = 0;
                    TextIcon2_Religion.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        Game.religionManager.religionImages.get(this.religionID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.imgWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - this.imgHeight / 2 + iTranslateY, this.imgWidth, this.imgHeight);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getTextHeight() + iTranslateY, this.getColor(isActive));
    }
    
    public int getTextH() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (Game.getCiv(Game.getProvince(this.iProvinceID).getCivID()).getReligionID() == Game.getProvince(this.iProvinceID).getReligion()) {
            return Colors.getColorButtonHover(isActive, this.getIsHovered());
        }
        if (isActive) {
            return Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
        }
        return Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Religion") + ": ", CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ReligionTitle(this.religionID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(this.iProvinceID).getReligion() != Game.getCiv(Game.getProvince(this.iProvinceID).getCivID()).getReligionID()) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements) {
            @Override
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }
            
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        };
    }
    
    private final float getImageScale() {
        return ImageManager.getImage(Images.gold).getHeight() / (float)Game.religionManager.religionImages.get(this.religionID).getHeight();
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        TextIcon2_Religion.lTimeAnimation = CFG.currentTimeMillis;
        TextIcon2_Religion.animationState = 0;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.religionID != Game.getProvince(this.iProvinceID).getReligion()) {
            this.setText("" + Game.religionManager.getReligion(Game.getProvince(this.iProvinceID).getReligion()).Name);
            this.religionID = Game.getProvince(this.iProvinceID).getReligion();
            this.imgWidth = (int)(Game.religionManager.religionImages.get(this.religionID).getWidth() * this.getImageScale());
            this.imgHeight = (int)(Game.religionManager.religionImages.get(this.religionID).getHeight() * this.getImageScale());
        }
        return super.getTextToDraw();
    }
    
    static {
        TextIcon2_Religion.lTimeAnimation = 0L;
        TextIcon2_Religion.animationState = 0;
    }
}
