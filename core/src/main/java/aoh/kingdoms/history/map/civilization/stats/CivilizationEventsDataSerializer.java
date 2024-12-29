// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.stats;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class CivilizationEventsDataSerializer implements Json.Serializer<CivilizationEventsData>
{
    public void write(final Json json, final CivilizationEventsData object, final Class knownType) {
        json.writeObjectStart();
        if (object.e != 0) {
            json.writeValue("e", (Object)object.e);
        }
        if (object.g != 0) {
            json.writeValue("g", (Object)object.g);
        }
        if (object.t != 0) {
            json.writeValue("t", (Object)object.t);
        }
        if (object.m != 0) {
            json.writeValue("m", (Object)object.m);
        }
        if (object.d != 0) {
            json.writeValue("d", (Object)object.d);
        }
        json.writeObjectEnd();
    }
    
    public CivilizationEventsData read(final Json json, final JsonValue jsonData, final Class type) {
        final CivilizationEventsData data = new CivilizationEventsData();
        data.e = jsonData.getInt("e", 0);
        data.g = jsonData.getInt("g", 0);
        data.t = jsonData.getInt("t", 0);
        data.m = jsonData.getInt("m", 0);
        data.d = jsonData.getInt("d", 0);
        return data;
    }
}
