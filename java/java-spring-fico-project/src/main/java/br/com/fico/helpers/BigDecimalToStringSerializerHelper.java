package br.com.fico.helpers;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
 
public class BigDecimalToStringSerializerHelper extends JsonSerializer<BigDecimal> {
 
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (value != null) {        
            Locale ptBR = new Locale("pt", "BR");
            NumberFormat moedaFormat = NumberFormat.getNumberInstance(ptBR);  //para moedas
            String bigDecimalFormatted = moedaFormat.format(value);
            gen.writeString(bigDecimalFormatted);
        }
    }
 
}
