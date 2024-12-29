// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement;

public class ButtonBuilding_Special extends MenuElement
{
    public int building;
    public int buildingID;
    public String sText;
    public int iTextWidth;
    public int iTextHeight;
    public boolean built;
    public static float iconScale;
    public Color colorBG;
    public Color colorMain;
    public Color colorOver;
    public String sConstructed;
    public int iConstructedWidth;
    public int iConstructedHeight;
    public int iconMaxW;
    public List<InnerStat> innerStats;
    public static final Color COLOR_STATS;
    
    public ButtonBuilding_Special(final boolean built, final int building, final int buildingID, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean isResearched, final String sConstructed) {
        this.iTextWidth = 0;
        this.iTextHeight = 0;
        this.built = false;
        this.innerStats = new ArrayList<InnerStat>();
        this.typeOfElement = MenuElement_Type.BUTTON;
        this.built = built;
        this.building = building;
        this.buildingID = buildingID;
        this.fontID = CFG.FONT_REGULAR;
        this.sConstructed = sConstructed;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sConstructed);
        this.iConstructedWidth = (int)Renderer.glyphLayout.width;
        this.iConstructedHeight = (int)Renderer.glyphLayout.height;
        ButtonBuilding_Special.iconScale = this.getImageScale(Images.gold);
        this.iconMaxW = (int)(ImageManager.getImage(Images.gold).getWidth() * ButtonBuilding_Special.iconScale) + CFG.PADDING * 4;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(nWidth);
        this.setHeight(this.getTopH());
        this.setText(BuildingsManager.buildings.get(building).Name[buildingID]);
        this.setClickable(isClickable);
        this.setVisible(true);
        int tStatsW;
        if (BuildingsManager.buildings.get(building).MaintenanceCost[buildingID] > 0.0f) {
            tStatsW = (this.getWidth() - (this.getInnerPosX() + CFG.PADDING * 2) - CFG.PADDING * 2) / 3;
        }
        else {
            tStatsW = (this.getWidth() - (this.getInnerPosX() + CFG.PADDING * 2) - CFG.PADDING) / 2;
        }
        this.innerStats.add(new InnerStat("" + Game.getBuildingConstructionCost(Game.player.iCivID, -1, building, buildingID), Images.gold, this.getInnerPosX() + CFG.PADDING, this.getTitleHeight() + CFG.PADDING * 2, tStatsW, this.getTopH() - (this.getTitleHeight() + CFG.PADDING * 3)));
        this.innerStats.add(new InnerStat(Game.lang.get("XDays", Game.getBuildingConstructionTime(Game.player.iCivID, -1, building, buildingID)), Images.buildTime, this.getInnerPosX() + CFG.PADDING * 2 + tStatsW, this.getTitleHeight() + CFG.PADDING * 2, tStatsW, this.getTopH() - (this.getTitleHeight() + CFG.PADDING * 3)));
        if (BuildingsManager.buildings.get(building).MaintenanceCost[buildingID] > 0.0f) {
            this.innerStats.add(new InnerStat("" + CFG.getPrecision2(BuildingsManager.buildings.get(building).MaintenanceCost[buildingID], 1000), Images.goldNegative, this.getInnerPosX() + CFG.PADDING * 3 + tStatsW * 2, this.getTitleHeight() + CFG.PADDING * 2, tStatsW, this.getTopH() - (this.getTitleHeight() + CFG.PADDING * 3)));
        }
        if (isResearched) {
            this.colorBG = Colors.COLOR_GRADIENT_BG_BLUE;
            this.colorMain = Colors.COLOR_NOTIFICATION_BG;
            this.colorOver = Colors.COLOR_NOTIFICATION_OVER;
        }
        else {
            this.colorBG = Colors.COLOR_GRADIENT_BG;
            this.colorMain = Colors.COLOR_NOTIFICATION_BG_RED;
            this.colorOver = Colors.COLOR_NOTIFICATION_OVER_RED;
        }
        int tWMax = 0;
        while (this.iTextWidth > nWidth && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public final void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        this.drawText(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    public int getButtonWidth() {
        return ImageManager.getImage(Images.buildingsFrame).getWidth() + getPaddingIMG() * 2;
    }
    
    public static int getPaddingIMG() {
        return CFG.PADDING + CFG.PADDING / 2;
    }
    
    public int getInnerPosX() {
        return this.getButtonWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - this.getButtonWidth();
    }
    
    public int getTitleHeight() {
        return (this.getTopH() - CFG.PADDING) / 2;
    }
    
    public int getTopH() {
        return ImageManager.getImage(Images.buildingsFrame).getHeight() + getPaddingIMG() * 2;
    }
    
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(this.colorMain.r, this.colorMain.g, this.colorMain.b, (this.getIsHovered() || isActive) ? 0.5f : 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(this.colorBG.r, this.colorBG.g, this.colorBG.b, (this.getIsHovered() || isActive) ? 1.0f : 0.75f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getButtonWidth(), this.getTopH(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(this.colorOver.r, this.colorOver.g, this.colorOver.b, 0.3f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(this.colorOver.r, this.colorOver.g, this.colorOver.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(this.colorOver.r, this.colorOver.g, this.colorOver.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(this.colorMain.r, this.colorMain.g, this.colorMain.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(this.colorOver.r, this.colorOver.g, this.colorOver.b, 0.85f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(this.colorOver.r, this.colorOver.g, this.colorOver.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(this.colorBG.r, this.colorBG.g, this.colorBG.b, (this.getIsHovered() || isActive) ? 0.5f : 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, this.getTitleHeight(), 1.0f);
        for (int i = 0; i < this.innerStats.size(); ++i) {
            this.innerStats.get(i).draw(oSB, iTranslateX, iTranslateY, isActive);
        }
        oSB.setColor(Color.WHITE);
        if (this.built || this.getIsHovered() || isActive) {
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight());
        }
        else {
            Renderer.setBlackWhite(oSB);
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight());
            Renderer.setShaderDefault(oSB);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight());
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
        if (BuildingsManager.buildings.get(this.building).RequiredResource >= 0) {
            ResourcesManager.resourceImages.get(BuildingsManager.buildings.get(this.building).RequiredResource).draw(oSB, this.getPosX() + getPaddingIMG() + ImageManager.getImage(Images.buildingsFrame).getWidth() - ResourcesManager.resourceImages.get(BuildingsManager.buildings.get(this.building).RequiredResource).getWidth() - CFG.PADDING + iTranslateX, this.getPosY() + getPaddingIMG() + ImageManager.getImage(Images.buildingsFrame).getHeight() - ResourcesManager.resourceImages.get(BuildingsManager.buildings.get(this.building).RequiredResource).getHeight() - CFG.PADDING + iTranslateY);
        }
        ImageManager.getImage(Images.buildingsFrame).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
        if (isActive) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight(), Colors.COLOR_BOX_ACTIVE);
        }
        else if (this.getIsHovered()) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight(), Colors.COLOR_BOX_HOVER);
        }
    }
    
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.sConstructed, this.getWidth() - CFG.PADDING * 3 - this.iConstructedWidth + iTranslateX, this.getPosY() + CFG.PADDING + this.getTitleHeight() / 2 - this.iConstructedHeight / 2 + iTranslateY, Colors.HOVER_RIGHT3);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getText(), this.getPosX() + this.getInnerPosX() + CFG.PADDING * 3 + iTranslateX, this.getPosY() + CFG.PADDING + this.getTitleHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.getText());
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iTextHeight = (int)Renderer.glyphLayout.height;
    }
    
    @Override
    public String getText() {
        return this.sText;
    }
    
    private final float getImageScale(final int iImageID) {
        return Math.min(1.0f, CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight() * 1.15f);
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = ButtonBuilding.getHoverBuilding(this.building, this.buildingID, false);
    }
    
    @Override
    public int getValue1() {
        return this.building;
    }
    
    @Override
    public int getValue2() {
        return this.buildingID;
    }
    
    static {
        ButtonBuilding_Special.iconScale = 1.0f;
        COLOR_STATS = new Color(0.039215688f, 0.05882353f, 0.09803922f, 0.6f);
    }
    
    public class InnerStat
    {
        public int iX;
        public int iY;
        public int iW;
        public int iH;
        public int img;
        public int imgW;
        public int imgH;
        public String text;
        public int iTextW;
        
        public InnerStat(final String text, final int img, final int iX, final int iY, final int iW, final int iH) {
            this.iX = iX;
            this.iY = iY;
            this.iW = iW;
            this.iH = iH;
            this.img = img;
            this.imgW = (int)(ImageManager.getImage(img).getWidth() * ButtonBuilding_Special.iconScale);
            this.imgH = (int)(ImageManager.getImage(img).getHeight() * ButtonBuilding_Special.iconScale);
            this.text = text;
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), text);
            this.iTextW = (int)Renderer.glyphLayout.width;
        }
        
        protected void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
            oSB.setColor(new Color(ButtonBuilding_Special.this.colorBG.r, ButtonBuilding_Special.this.colorBG.g, ButtonBuilding_Special.this.colorBG.b, 0.25f));
            Renderer.drawBox(oSB, Images.statsRectBG, ButtonBuilding_Special.this.getPosX() + this.iX + iTranslateX, ButtonBuilding_Special.this.getPosY() + this.iY + iTranslateY, this.iW, this.iH, 1.0f);
            oSB.setColor(new Color(ButtonBuilding_Special.this.colorBG.r, ButtonBuilding_Special.this.colorBG.g, ButtonBuilding_Special.this.colorBG.b, 0.2f));
            Renderer.drawBox(oSB, Images.statsRectBG, ButtonBuilding_Special.this.getPosX() + this.iX + iTranslateX, ButtonBuilding_Special.this.getPosY() + this.iY + iTranslateY, ButtonBuilding_Special.this.iconMaxW, this.iH, 1.0f);
            ImageManager.getImage(this.img).draw(oSB, ButtonBuilding_Special.this.getPosX() + this.iX + ButtonBuilding_Special.this.iconMaxW / 2 - this.imgW / 2 + iTranslateX, ButtonBuilding_Special.this.getPosY() + this.iY + this.iH / 2 - this.imgH / 2 + iTranslateY, this.imgW, this.imgH);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.text, ButtonBuilding_Special.this.getPosX() + this.iX + ButtonBuilding_Special.this.iconMaxW + (this.iW - ButtonBuilding_Special.this.iconMaxW) / 2 - this.iTextW / 2 + iTranslateX, ButtonBuilding_Special.this.getPosY() + this.iY + this.iH / 2 - ButtonBuilding_Special.this.iTextHeight / 2 + iTranslateY, Colors.getColorButtonHover(isActive, ButtonBuilding_Special.this.getIsHovered()));
        }
    }
}
