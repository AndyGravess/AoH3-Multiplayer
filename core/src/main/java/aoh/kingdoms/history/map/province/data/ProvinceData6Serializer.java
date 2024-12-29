// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;

public class ProvinceData6Serializer implements Json.Serializer<ProvinceData6>
{
    public void write(final Json json, final ProvinceData6 data, final Class knownType) {
        json.writeObjectStart();
        json.writeValue("e", (Object)data.e);
        if (data.n != 0) {
            json.writeValue("n", (Object)data.n);
        }
        json.writeObjectEnd();
    }
    
    public ProvinceData6 read(final Json json, final JsonValue jsonData, final Class type) {
        final ProvinceData6 out = new ProvinceData6();
        return out;
    }
}
