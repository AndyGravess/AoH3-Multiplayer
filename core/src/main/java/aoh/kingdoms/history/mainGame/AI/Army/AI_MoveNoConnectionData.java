// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Army;

import java.util.Objects;

public class AI_MoveNoConnectionData
{
    public int fromProvince;
    public int toProvince;
    
    public AI_MoveNoConnectionData(final int fromProvince, final int toProvince) {
        this.fromProvince = fromProvince;
        this.toProvince = toProvince;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final AI_MoveNoConnectionData that = (AI_MoveNoConnectionData)o;
        return this.fromProvince == that.fromProvince && this.toProvince == that.toProvince;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.fromProvince, this.toProvince);
    }
}
