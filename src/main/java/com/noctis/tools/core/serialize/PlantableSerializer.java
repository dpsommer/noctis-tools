package com.noctis.tools.core.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.noctis.tools.farm.Plantable;

public class PlantableSerializer extends JsonSerializer<Plantable> {

  @Override
  public void serialize(Plantable value, JsonGenerator jgen,
      SerializerProvider provider) throws IOException, JsonProcessingException {
    jgen.writeStartObject();
    jgen.writeFieldName("name");
    jgen.writeString(value.toString());
    jgen.writeFieldName("type");
    jgen.writeString(value.getType());
    jgen.writeFieldName("growthTime");
    jgen.writeNumber(value.getGrowthTime());
    jgen.writeFieldName("climate");
    jgen.writeString(value.getClimate().toString());
    jgen.writeEndObject();
  }

}
