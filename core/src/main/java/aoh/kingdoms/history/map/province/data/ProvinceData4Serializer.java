// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class ProvinceData4Serializer implements Json.Serializer<ProvinceData4>
{
    public void write(final Json json, final ProvinceData4 data, final Class knownType) {
        json.writeObjectStart();
        if (data.u) {
            json.writeValue("u", (Object)data.u);
        }
        if (data.s != 0.0f) {
            json.writeValue("s", (Object)data.s);
        }
        json.writeObjectEnd();
    }
    
    public ProvinceData4 read(final Json json, final JsonValue jsonData, final Class type) {
        final ProvinceData4 out = new ProvinceData4();
        return out;
    }
}
