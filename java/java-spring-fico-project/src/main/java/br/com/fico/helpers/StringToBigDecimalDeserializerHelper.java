package br.com.fico.helpers;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
 
public class StringToBigDecimalDeserializerHelper extends JsonDeserializer<BigDecimal> {
 
    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            String formatted = p.getValueAsString();
             
            Locale ptBr = new Locale("pt", "BR");
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance( ptBr );
            df.setParseBigDecimal(true);
            BigDecimal bigDecimal = (BigDecimal) df.parseObject(formatted);
            
            return bigDecimal;
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
 
}