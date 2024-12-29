// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

public class Text_StaticBG_RulerTitle_General extends Text_StaticBG_RulerTitle
{
    public String key;
    public int iProvinceID;
    public int iCivID;
    
    public Text_StaticBG_RulerTitle_General(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int iProvinceID, final int iCivID) {
        super(sText, iPosX, iPosY, iWidth, iHeight);
        this.iProvinceID = iProvinceID;
        this.iCivID = iCivID;
    }
    
    public Text_StaticBG_RulerTitle_General(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final String key, final int iProvinceID, final int iCivID) {
        super(sText, iPosX, iPosY, iWidth, iHeight);
        this.key = key;
        this.iProvinceID = iProvinceID;
        this.iCivID = iCivID;
    }
}
