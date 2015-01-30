package com.system.prg.util;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	static Logger log = LoggerFactory.getLogger(StringUtils.class);
	private static final String ADD_MESSAGE = "...";

	public static String gbToUTF8(String str) {
		if (str == null)
			return "";
		String hs = "";
		try {
			byte[] b = str.getBytes("UTF-16");

			for (int n = 0; n < b.length; n++) {
				str = Integer.toHexString(b[n] & 0xFF);
				if (str.length() == 1)
					hs = hs + "0" + str;
				else
					hs = hs + str;
				if (n < b.length - 1) {
					hs = hs;
				}
			}
			str = hs.toUpperCase().substring(4);

			char[] chs = str.toCharArray();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < chs.length; i += 4) {
				buf.append("&#x" + chs[i] + chs[(i + 1)] + chs[(i + 2)]
						+ chs[(i + 3)] + ";");
			}
			return buf.toString();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return "";
	}

	public static String getFavour(String s) {
		if ((s == null) || (s.equals("")))
			return "";
		StringTokenizer st = new StringTokenizer(s, "$");
		int cnt = 0;
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken().trim();
			int i = tmp.indexOf(",");
			if (i != -1)
				try {
					cnt += Integer.parseInt(tmp.substring(i + 1));
				} catch (Exception localException) {
				}
		}
		return String.valueOf(cnt);
	}

	public static String getFirstUpper(String str) {
		String tmp = "";
		str = trim(str);
		if (!str.equals("")) {
			if (str.length() > 1)
				tmp = toUpperCase(str.substring(0, 1))
						+ str.substring(1, str.length());
			else {
				tmp = toUpperCase(str);
			}
		}
		return tmp;
	}

	public static String toUpperCase(String str) {
		return trim(str).toUpperCase();
	}

	public static String toLowerCase(String str) {
		return trim(str).toLowerCase();
	}

	public static String trim(String s) {
		return getNotNullString(s);
	}

	public static String None2Null(String s) {
		if (s != null) {
			if ("None".equals(s)) {
				return "";
			}
			return s.trim();
		}

		return "";
	}

	public static String NullString2Null(String s) throws Exception {
		if (s != null) {
			if ("null".equalsIgnoreCase(s)) {
				return "";
			}
			return s.trim();
		}

		return "";
	}

	public static String blankToBracket(String str) {
		if ((str == null) || (str.equals("")))
			return "{}";
		return str;
	}

	public static String blankToZero(String str) {
		if ((str == null) || (str.equals("")))
			return "0";
		return str;
	}

	public static String getTrimString(String src, int n, String var) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < n; i++) {
			buf.append(var);
		}
		buf.append(src);

		return buf.toString();
	}

	public static String getTrimString(String conzt, String var) {
		String ret = conzt + var;
		return ret.substring(var.length());
	}

	public static String getTrimRight(String src, int n) {
		StringBuffer buf = new StringBuffer();

		int lngth = src != null ? src.length() : 0;
		buf.append(src);
		for (int i = 0; i < n - lngth; i++) {
			buf.append(" ");
		}

		return buf.toString();
	}

	public static String getTrimString(String conzt, int var) {
		String ret = conzt + var;
		return ret.substring(ret.length() - conzt.length());
	}

	public static String getTrimString(String src) {
		if (src == null) {
			return "";
		}
		byte[] bytes = src.getBytes();
		ByteList bList = new ByteList(bytes.length);

		for (int i = 0; i < bytes.length; i++) {
			byte word;
			switch (word = bytes[i]) {
			case 13:
				if (bytes[(i + 1)] == 10) {
					bList.addAll("\\n".getBytes());
					i++;
				}
				break;
			case 10:
				bList.addAll("\\n".getBytes());
				break;
			case 124:
				bList.addAll(" ".getBytes());
				break;
			default:
				bList.add(word);
			}
		}

		return bList.toString();
	}

	public static String getNotNullString(String s, String sdefault) {
		return s != null ? s.trim() : sdefault;
	}

	public static String getNotNullString(String s) {
		return s != null ? s.trim() : "";
	}

	public static String getDoubleString(String s) {
		if (s == null)
			s = "0";
		if (s.trim().equals(""))
			s = "0";
		return s;
	}

	public static String getInt2String(int iData, int iNull) {
		return iData != iNull ? String.valueOf(iData) : "";
	}

	public static String getLong2String(long lData, long lNull) {
		return lData != lNull ? String.valueOf(lData) : "";
	}

	public static String getFloat2String(float fData, float fNull) {
		return fData != fNull ? String.valueOf(fData) : "";
	}

	public static String getDouble2String(double dData, double dNull) {
		return dData != dNull ? String.valueOf(dData) : "";
	}

	public static int getString2Int(String str) {
		int t = -1;
		try {
			t = Integer.parseInt(str);
		} catch (Exception e) {
			t = -1;
		}
		return t;
	}

	public static String doubleRemove0(double dData) {
		String tmp = Double.toString(dData);
		if (tmp.length() >= 3) {
			String a = tmp.substring(tmp.length() - 2, tmp.length());
			if (".0".equals(a))
				tmp = tmp.substring(0, tmp.length() - 2);
		}
		if ("0".equals(tmp)) {
			tmp = "";
		}
		return tmp;
	}

	public static String toFormatDate(String str) {
		String sRet;
		if (str != null) {
			str = str.trim();
			if (str.trim().length() == 8) {
				sRet = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
						+ str.substring(6, 8);
			} else {
				if (str.trim().length() == 14)
					sRet = str.substring(0, 4) + "-" + str.substring(4, 6)
							+ "-" + str.substring(6, 8) + " "
							+ str.substring(8, 10) + ":"
							+ str.substring(10, 12) + ":"
							+ str.substring(12, 14);
				else
					sRet = str;
			}
		} else {
			sRet = " ";
		}

		return sRet;
	}

	public static String getFormatInt(int i, int length) {
		String sRet = Integer.toString(i);
		String sI = "";
		sI = Integer.toString(i);
		if (sI.length() < length) {
			for (int k = 0; k < length - sI.length(); k++) {
				sRet = "0" + sRet;
			}
		}

		return sRet;
	}

	public static String replace(String strSource, String strFrom, String strTo) {
		StringBuffer strDest = new StringBuffer();
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest.append(strSource.substring(0, intPos)).append(
					strTo);
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest.append(strSource);

		return strDest.toString();
	}

	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str;
		}
		return padding(pads, padChar).concat(str);
	}

	private static String padding(int repeat, char padChar)
			throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException(
					"Cannot pad a negative amount: " + repeat);
		}
		char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf);
	}

	public static String getStrByLen(String strParameter, int limitLength) {
		String return_str = strParameter;
		int temp_int = 0;
		int cut_int = 0;
		byte[] b = strParameter.getBytes();

		for (int i = 0; i < b.length; i++) {
			if (b[i] >= 0) {
				temp_int++;
			} else {
				temp_int += 2;
				i++;
			}
			cut_int++;

			if (temp_int >= limitLength) {
				if ((temp_int % 2 != 0) && (b[(temp_int - 1)] < 0)) {
					cut_int--;
				}
				return_str = return_str.substring(0, cut_int);
				break;
			}
		}
		return return_str;
	}

	public static String substring(String in, int start, int end) {
		String out = substrNoAppend(in, start, end);

		if (in.length() > end) {
			out = out + "...";
		}
		return out;
	}

	public static String substrNoAppend(String in, int start, int end) {
		if (in == null) {
			return "";
		}
		String out = org.apache.commons.lang3.StringUtils.substring(in, start,
				end);

		return out;
	}

	public static boolean isEmpty(String in) {
		if (in == null)
			return true;
		if ((in.trim().equals("")) || (in.trim().equalsIgnoreCase("null")))
			return true;
		return false;
	}

	public static String[] split(String inSTR, String splitChar) {
		String temp = inSTR.replaceAll(splitChar, " " + splitChar + " ");
		return temp.split(String.valueOf(splitChar));
	}

	// / 转全角的函数(SBC case) ///
	// /任意字符串 /// 全角字符串 ///
	// /全角空格为12288,半角空格为32
	// /其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///
	public static String toSBC(String input) {
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	// / /// 转半角的函数(DBC case) ///
	// /任意字符串
	// / 半角字符串 ///
	// /全角空格为12288，半角空格为32
	// /其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}
}