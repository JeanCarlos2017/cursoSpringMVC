package com.globallabs.spring.web.mvc.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.globallabs.spring.web.mvc.model.Lado;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class Jackson {

    @Bean
    public ObjectMapper ObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        //PROPRIEDADES MAPEADAS NAO QUEBRAM
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //FALHA SE ALGUMA PROPRIEDADE ESTIVER VAZIA
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //SERVE PARA A COMPATIBILIDADE DE ARRAYS, QUANDO TEM UM ARRAY COM UM ITEM,  CASO NAO TENHA ESSA CONFIG ELE SE PERDE
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        //Serialize datas
        objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.registerModule(ladoModuleMapper);
        return objectMapper;
    }

    public SimpleModule ladoModuleMapper(){
        SimpleModule dateModule = new SimpleModule("JSONLadoModule", PackageVersion.VERSION);
        dateModule.addSerializer(Lado.class, new LadoCerialize());
        dateModule.addDeserializer(Lado.class, new LadoDescerialize());
        return dateModule;
    }

    class LadoCerialize extends StdSerializer<Lado>{
        public LadoCerialize(){
            super(Lado.class);
        }

        @Override
        public void serialize(Lado value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.getValue());
        }
    }

    class LadoDescerialize extends StdDeserializer<Lado> {
        public LadoDescerialize(){
            super(Lado.class);
        }

        @Override
        public Lado deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {


            return Lado.of(p.getText());
        }


    }

}
