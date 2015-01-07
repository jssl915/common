package com.system.prg.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonMapper{
  private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
  private ObjectMapper mapper;

  public JsonMapper(JsonSerialize.Inclusion inclusion){
    this.mapper = new ObjectMapper();

    this.mapper.setSerializationInclusion(inclusion);

    this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
  }

  public static JsonMapper buildNormalMapper(){
    return new JsonMapper(JsonSerialize.Inclusion.ALWAYS);
  }

  public static JsonMapper buildNonNullMapper(){
    return new JsonMapper(JsonSerialize.Inclusion.NON_NULL);
  }

  public static JsonMapper buildNonDefaultMapper(){
    return new JsonMapper(JsonSerialize.Inclusion.NON_DEFAULT);
  }

  public static JsonMapper buildNonEmptyMapper(){
    return new JsonMapper(JsonSerialize.Inclusion.NON_EMPTY);
  }

  public String toJson(Object object){
    try
    {
      return this.mapper.writeValueAsString(object);
    } catch (IOException e) {
      logger.warn("write to json string error:" + object, e);
    }return null;
  }

  public <T> T fromJson(String jsonString, Class<T> clazz){
    if (StringUtils.isEmpty(jsonString)) {
      return null;
    }
    try{
      return this.mapper.readValue(jsonString, clazz);
    } catch (IOException e) {
      logger.warn("parse json string error:" + jsonString, e);
    }return null;
  }

  public <T> T fromJson(String jsonString, JavaType javaType){
    if (StringUtils.isEmpty(jsonString)) {
      return null;
    }
    try{
      return this.mapper.readValue(jsonString, javaType);
    } catch (IOException e) {
      logger.warn("parse json string error:" + jsonString, e);
    }return null;
  }

  public JavaType constructParametricType(Class<?> parametrized, Class<?>[] parameterClasses){
    return this.mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
  }

  public <T> T update(T object, String jsonString){
    try{
      return this.mapper.readerForUpdating(object).readValue(jsonString);
    } catch (JsonProcessingException e) {
      logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
    } catch (IOException e) {
      logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
    }
    return null;
  }

  public String toJsonP(String functionName, Object object){
    return toJson(new JSONPObject(functionName, object));
  }

  public void setEnumUseToString(boolean value){
    this.mapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);
    this.mapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, value);
  }

  public ObjectMapper getMapper(){
    return this.mapper;
  }
}