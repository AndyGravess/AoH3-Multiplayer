// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.province.ProvinceHover;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;

public class MapMode
{
    public ProvinceDraw.DrawProvinces drawProvinces;
    public ProvinceHover.ProvinceHoverBuild provinceHoverBuild;
    
    public MapMode(final ProvinceDraw.DrawProvinces drawProvinces, final ProvinceHover.ProvinceHoverBuild provinceHoverBuild) {
        this.drawProvinces = drawProvinces;
        this.provinceHoverBuild = provinceHoverBuild;
    }
    
    public void enableViewAction() {
    }
    
    public void disableViewAction() {
    }
    
    public void playSFX_ProvinceClick() {
        Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
    }
}
