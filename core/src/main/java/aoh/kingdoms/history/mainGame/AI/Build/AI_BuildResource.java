// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Build;

import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_BuildResource
{
    public static final boolean buildBuilding(final int civID, final int limitOfBuildings) {
        for (int i = 0, iSize = Math.min(Game.aiValuesBuild.BUILD_RESOURCE_PROVINCES, Game.getCiv(civID).getNumOfProvinces()); i < iSize; ++i) {
            final int randProvince = Game.getCiv(civID).getProvinceID(Game.oR.nextInt(Game.getCiv(civID).getNumOfProvinces()));
            if (Game.getProvince(randProvince).getResourceID() >= 0 && ResourcesManager.lResources.get(Game.getProvince(randProvince).getResourceID()).uniqueBuildingID >= 0 && !Game.getProvince(randProvince).buildingBuilt(ResourcesManager.lResources.get(Game.getProvince(randProvince).getResourceID()).uniqueBuildingID, 0) && Game.getCiv(civID).isBuildingResearched(ResourcesManager.lResources.get(Game.getProvince(randProvince).getResourceID()).uniqueBuildingID, 0) && Game.getProvince(randProvince).addBuildingConstruction(ResourcesManager.lResources.get(Game.getProvince(randProvince).getResourceID()).uniqueBuildingID, 0) && Game.getCiv(civID).fGold < AI_Build.averageCostOfBuilding) {
                return true;
            }
        }
        return false;
    }
}
