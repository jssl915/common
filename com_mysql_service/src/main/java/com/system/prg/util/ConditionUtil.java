package com.system.prg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ConditionUtil {
	public static Map<String, Object> getMapByString(String str) {
		Map<String, Object> result = new HashMap<String, Object>();

		String[] propertys = str.split("\\[*\\]");
		for (int i = 0; i < propertys.length; i++) {
			String[] property = propertys[i].substring(1).split(":");
			if ("ARRAY".equals(property[1])) {
				result.put(property[0], toArrayByString(property[2]));
			} else if ("java.lang.String".equals(property[1])) {
				result.put(property[0], unesc(property[2]));
			} else if ("java.lang.Integer".equals(property[1])) {
				result.put(property[0], Integer.valueOf(unesc(property[2])));
			} else if ("java.lang.Long".equals(property[1])) {
				result.put(property[0], Long.valueOf(unesc(property[2])));
			} else if ("java.util.Date".equals(property[1])) {
				SimpleDateFormat df = new SimpleDateFormat(
						"E MMM dd hh:mm:ss z yyyy", Locale.US);
				try {
					String temp = unesc(property[2]);
					Date date = df.parse(temp);
					result.put(property[0], date);
				} catch (ParseException e) {
					throw new SystemException("日期格式转换错误！", e);
				}
			} else if ("java.lang.Boolean".equals(property[1])) {
				result.put(property[0], Boolean.valueOf(property[2]));
			}
		}
		return result;
	}

	public static String getStringByMap(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer(100);

		for (Map.Entry<String, Object> m : map.entrySet()) {
			String key = m.getKey();
			Object value = m.getValue();
			if (value != null) {
				String clsName = value.getClass().getName();
				if (value.getClass().isArray())
					sb.append("[").append(key).append(":").append("ARRAY")
							.append(":")
							.append(toStringByArray((Object[]) value))
							.append("]");
				else if ((("java.lang.String".equals(clsName))
						|| ("java.lang.Integer".equals(clsName))
						|| ("java.lang.Long".equals(clsName))
						|| ("java.lang.Boolean".equals(clsName)) || ("java.util.Date"
							.equals(clsName)))
						&& (!"".equals(value.toString()))) {
					sb.append("[").append(key).append(":")
							.append(value.getClass().getName()).append(":")
							.append(esc(value.toString())).append("]");
				}
			}
		}
		return sb.toString();
	}

	private static String toStringByArray(Object[] value) {
		StringBuffer sb = new StringBuffer(100);
		String type = value[0].getClass().getName();
		sb.append("{").append(type).append("@");
		for (int i = 0; i < value.length; i++) {
			sb.append(esc(value[i].toString())).append(",");
		}
		return sb.substring(0, sb.length() - 1) + "}";
	}

	private static Object[] toArrayByString(String str) {
		List<Object> result = new ArrayList<Object>();
		str = str.substring(1, str.length() - 1);
		String type = str.substring(0, str.indexOf("@"));
		String[] values = str.substring(str.indexOf("@") + 1).split(",");
		for (int i = 0; i < values.length; i++) {
			String value = unesc(values[i]);
			if ("java.lang.Long".equals(type))
				result.add(Long.valueOf(value));
			else if ("java.lang.Integer".equals(type))
				result.add(Integer.valueOf(value));
			else {
				result.add(value);
			}
		}
		return result.toArray();
	}

	private static String unesc(String str) {
		return str.replaceAll("&&&", ":").replaceAll("&!&", "[")
				.replaceAll("!&!", "]").replaceAll("!!!", ",")
				.replaceAll("!!&", "{").replaceAll("&&!", "}")
				.replaceAll("###", "@");
	}

	private static String esc(String str) {
		return str.replaceAll(":", "&&&").replaceAll("\\[", "&!&")
				.replaceAll("\\]", "!&!").replaceAll(",", "!!!")
				.replaceAll("\\{", "!!&").replaceAll("\\}", "&&!")
				.replaceAll("@", "###");
	}

	public static PageObject getPageObjectByString(String condition)
			throws BusinessException {
		Map<String, Object> mpCondition = getMapByString(condition);
		PageObject po = new PageObject();
		po.setCountStatement((String) mpCondition.get("countStatement"));
		po.setSelectStatement((String) mpCondition.get("selectStatement"));
		po.setExeQuery(Boolean.valueOf(mpCondition.get("exeQuery").toString())
				.booleanValue());
		po.setSelectFirst(Boolean.valueOf(
				mpCondition.get("selectFirst").toString()).booleanValue());
		po.setPageIndex(Integer
				.valueOf(mpCondition.get("pageIndex").toString()).intValue());
		po.setPageSize(Integer.valueOf(mpCondition.get("pageSize").toString())
				.intValue());
		if (mpCondition.get("url") != null) {
			po.setUrl(mpCondition.get("url").toString());
		}
		mpCondition.remove("countStatement");
		mpCondition.remove("selectStatement");
		mpCondition.remove("exeQuery");
		mpCondition.remove("selectFirst");
		mpCondition.remove("pageIndex");
		mpCondition.remove("pageSize");
		mpCondition.remove("url");
		po.getCondition().putAll(mpCondition);
		return po;
	}
}