// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.save;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class CivData3Serializer implements Json.Serializer<CivData3>
{
    public void write(final Json json, final CivData3 data, final Class knownType) {
        json.writeObjectStart();
        if (data.a != 0) {
            json.writeValue("a", (Object)data.a);
        }
        if (data.w != 0.0f) {
            json.writeValue("w", (Object)data.w);
        }
        if (data.e != 0.0f) {
            json.writeValue("e", (Object)data.e);
        }
        if (data.t != 0.0f) {
            json.writeValue("t", (Object)data.t);
        }
        if (data.c != 0.0f) {
            json.writeValue("c", (Object)data.c);
        }
        json.writeObjectEnd();
    }
    
    public CivData3 read(final Json json, final JsonValue jsonData, final Class type) {
        final CivData3 out = new CivData3();
        return out;
    }
}
