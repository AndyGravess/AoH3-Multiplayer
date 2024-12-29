// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.stats;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class CivilizationEventsData2Serializer implements Json.Serializer<CivilizationEventsData2>
{
    public void write(final Json json, final CivilizationEventsData2 object, final Class knownType) {
        json.writeObjectStart();
        if (object.b != 0) {
            json.writeValue("b", (Object)object.b);
        }
        if (object.a != 0) {
            json.writeValue("a", (Object)object.a);
        }
        if (object.e != 0) {
            json.writeValue("e", (Object)object.e);
        }
        if (object.m != 0) {
            json.writeValue("m", (Object)object.m);
        }
        if (object.c != 0) {
            json.writeValue("c", (Object)object.c);
        }
        json.writeObjectEnd();
    }
    
    public CivilizationEventsData2 read(final Json json, final JsonValue jsonData, final Class type) {
        final CivilizationEventsData2 data = new CivilizationEventsData2();
        data.b = jsonData.getInt("b", 0);
        data.a = jsonData.getInt("a", 0);
        data.e = jsonData.getInt("e", 0);
        data.m = jsonData.getInt("m", 0);
        data.c = jsonData.getInt("c", 0);
        return data;
    }
}
