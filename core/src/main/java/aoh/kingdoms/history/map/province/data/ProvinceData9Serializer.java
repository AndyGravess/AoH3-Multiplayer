// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class ProvinceData9Serializer implements Json.Serializer<ProvinceData9>
{
    public void write(final Json json, final ProvinceData9 data, final Class knownType) {
        json.writeObjectStart();
        if (data.s != -1) {
            json.writeValue("s", (Object)data.s);
        }
        if (data.e != 0) {
            json.writeValue("e", (Object)data.e);
        }
        json.writeObjectEnd();
    }
    
    public ProvinceData9 read(final Json json, final JsonValue jsonData, final Class type) {
        final ProvinceData9 out = new ProvinceData9();
        return out;
    }
}
