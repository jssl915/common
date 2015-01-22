package com.system.util;

import java.lang.reflect.Field;

public class ReflectUtil {
	
	public static Object getClassField(Object obj, String name) throws Exception {
		Field field = getField(obj.getClass(), name);
		return field.get(obj);
	}
	
	public static void setClassField(Object obj, String name, Object value) throws Exception {
		Field field = getField(obj.getClass(), name);
		field.set(obj, value);
	}
	
	public static Object getSuperClassField(Object obj, String name) throws Exception {
		Field field = getField(obj.getClass().getSuperclass(), name);
		return field.get(obj);
	}
	
	private static Field getField(Class<?> clazz, String name) throws Exception {
		Field field = null;
		for(Field f : clazz.getDeclaredFields()) {
			if(f.getName().equals(name)) {
				f.setAccessible(true);
				field = f;
			}
		}
		return field;
	}
}