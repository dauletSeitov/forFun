package just.fo.fun.utils.serializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;


public class ConstantsDeserializer extends JsonDeserializer<Byte> {


    @Override
    public Byte deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        if (jsonParser == null)
            return 0;

        String value = jsonParser.getText();

        if (value == null || value.length() == 0)
            return 0;

        switch (value.toLowerCase()) {
            case "true":
                return 1;
            case "false":
                return 0;
            default:
                return 0;
        }
    }


    @Override
    public Class<Byte> handledType() {
        return Byte.class;
    }
}