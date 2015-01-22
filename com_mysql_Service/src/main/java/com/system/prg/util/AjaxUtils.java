package com.system.prg.util;
public class AjaxUtils{
  public static String toJson(Object src){
    return JsonMapper.buildNonDefaultMapper().toJson(src);
  }
  public static String makeJsonResponse(boolean result){
    if (result) {
      return "{\"result\":\"success\",\"message\":\"操作成功\"}";
    }
    return "{\"result\":\"faild\",\"message\":\"操作失败\"}";
  }

  public static String makeJsonResponse(boolean result, String message){
    if (result) {
      return "{\"result\":\"success\",\"message\":\"" + message + "\"}";
    }
    return "{\"result\":\"faild\",\"message\":\"" + message + "\"}";
  }
}