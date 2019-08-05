package com.xjxspace.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class Common {
	
	public static Map<String,Object> getReqParamMap(HttpServletRequest req){
		Map<String,Object> param = new HashMap<String,Object>();	
		Enumeration<?> enumeration = req.getParameterNames();
		while(enumeration.hasMoreElements()){
			String name = (String)enumeration.nextElement();
			String value = req.getParameter(name);
			param.put(name, value);
		}
		return param;
	}
	public static String  getLoginFlagFromSession(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginFlag =(String) session.getAttribute("loginFlag");
		return loginFlag;
	}
	public static boolean isLogin(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginFlag =(String) session.getAttribute("loginFlag");
		if(loginFlag!=null&&!"".equals(loginFlag)){
		return true;	
		}
		return false;
	}
	
	public static void putLoginFlagIntoSession(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.setAttribute("loginFlag", "true");
	}
	
	public static <T> String listToJsonStning (List<T> list,Class<?> clazz){
		if(list==null||list.size()== 0 ){
			return "";
		}
		JSONArray array = new JSONArray();
		for(int i= 0 ;i<list.size();i++){
		array.add(list.get(i));
		}
		if(array.size()!= 0 ){
		return array.toJSONString();
		}else{
			return "";
		}
	}
	
	public static <T> List<Map<String,Object>> listToMapList (List<T> list,Class<?> clazz){
		if(list==null||list.size()== 0 ){
			return null;
		}
		List<Map<String,Object>> array = new ArrayList<Map<String,Object>>();;
		for(int i= 0 ;i<list.size();i++){
		String json = JSONObject.toJSONString(list.get(i));
		JSONObject map =JSONObject.parseObject(json);
		array.add(map);
		}
		if(array.size()!= 0 ){
		return array;
		}else{
			return null;
		}
	}
	
	// 国标码和区位码转换常量
		private static final int GB_SP_DIFF = 160;

		// 存放国标一级汉字不同读音的起始区位码
		private static final int[] secPosvalueList = { 1601, 1637, 1833, 2078,
				2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730,
				3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

		// 存放国标一级汉字不同读音的起始区位码对应读音
		private static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'w', 'x', 'y', 'z' };

		// 获取一个字符串的拼音码
		public static String getFirstLetter(String oriStr) {
			if (isEmpty(oriStr))
				return "";

			String str = oriStr.toLowerCase();
			StringBuffer buffer = new StringBuffer();
			char ch;
			char[] temp;
			for (int i = 0; i < str.length(); i++) { // 依次处理str中每个字符
				ch = str.charAt(i);
				temp = new char[] { ch };
				byte[] uniCode = new String(temp).getBytes();
				if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
					buffer.append(temp);
				} else {
					buffer.append(convert(uniCode));
				}
			}
			return buffer.toString();
		}

		/**
		 * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
		 * 例如汉字"你"的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
		 * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n'
		 */
		private static char convert(byte[] bytes) {
			char result = '-';
			int secPosvalue = 0;
			int i;
			for (i = 0; i < bytes.length; i++) {
				bytes[i] -= GB_SP_DIFF;
			}
			secPosvalue = bytes[0] * 100 + bytes[1];
			for (i = 0; i < 23; i++) {
				if (secPosvalue >= secPosvalueList[i]
						&& secPosvalue < secPosvalueList[i + 1]) {
					result = firstLetter[i];
					break;
				}
			}
			return result;
		}

		public static String getPropertyByKey(String key) {
			try {
				Properties p = new Properties();
				InputStream in = Common.class
						.getResourceAsStream("../../../prop.properties");
				p.load(in);
				return p.getProperty(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		/**
		 * 数字排列方法,例如床号从小到大，加床的排在最后
		 * 
		 * @param strA
		 * @param strB
		 * @return
		 */
		public static int compareNum(String strA, String strB) {
			int numA, numB;
			try {
				strA = strA.trim();
				numA = Integer.parseInt(strA);
			} catch (Exception e) {
				int specialNum = getNonNumStr(strA).hashCode() / 10000000 * 100000;
				numA = 100000000 + specialNum + getNumberFromString(strA);
			}

			try {
				strB = strB.trim();
				numB = Integer.parseInt(strB);
			} catch (Exception e) {
				int specialNum = getNonNumStr(strA).hashCode() / 10000000 * 100000;
				numB = 100000000 + specialNum + getNumberFromString(strB);
			}
			return numA - numB;
		}

		/**
		 * 从数字及其他字符混合的串中取出数字
		 * 
		 * @param str
		 * @return
		 */
		public static int getNumberFromString(String str) {
			if (str == null)
				return -1;
			String numberList = "0123456789";
			int num = 0;
			if (str != null) {
				for (int i = 0; i < str.length(); i++) {
					if (numberList.indexOf(str.substring(i, i + 1)) >= 0)
						num = num * 10 + Integer.parseInt(str.substring(i, i + 1));
				}
			}
			return num;
		}

		/**
		 * 取出字符
		 * 
		 * @param str
		 * @return
		 */
		public static String getNonNumStr(String str) {
			if (str == null)
				return "";
			String numberList = "0123456789";
			StringBuilder builder = new StringBuilder();
			if (str != null) {
				for (int i = 0; i < str.length(); i++) {
					if (numberList.indexOf(str.substring(i, i + 1)) < 0)
						builder.append(str.substring(i, i + 1));
				}
			}
			return builder.toString();
		}

		/**
		 * 判断一个集合是否为null或size=0
		 * 
		 * @param list
		 * @return 如果不为空,则返回false
		 */
		@SuppressWarnings("rawtypes")
		public static boolean isEmpty(List<?> list) {
			if (list != null && !list.isEmpty())
				return false;
			return true;
		}

		/**
		 * 判断一个字符串是否为空
		 * 
		 * @param str
		 * @return 如果不为空,则返回false
		 */
		public static boolean isEmpty(String str) {
			if (str != null && !"".equals(str) && str.trim().length() > 0)
				return false;
			return true;
		}

		/**
		 * 判断一个集合是否为null或size=0
		 * 
		 * @param list
		 * @return 如果不为空,则返回false
		 */
		@SuppressWarnings("rawtypes")
		public static boolean isEmpty(Set<?> list) {
			if (list != null && !list.isEmpty())
				return false;
			return true;
		}

		/**
		 * 判断一个字符串是不是数字型
		 * 
		 * @param str
		 * @return 如果是数字型,则返回true
		 */
		public static boolean isNumber(String str) {
			if (!isEmpty(str)) {
				char[] cArr = str.toCharArray();
				String pp = "";
				for (int j = 0; j < cArr.length; j++) {
					if (cArr[j] >= 48 && cArr[j] <= 57)
						pp += cArr[j];
				}
				if (pp.length() > 0 && pp.length() == cArr.length)
					return true;
			}
			return false;
		}

		/**
		 * 获取系统环境变量
		 * 
		 * @param name
		 *            环境变量名称
		 * @return 环境变量的值
		 */
		public static String getEnv(String name) {
			StringBuffer sb = new StringBuffer();
			try {
				Process p = Runtime.getRuntime().exec(
						"cmd /c echo " + "%" + name + "%");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String strLine = null;
				while ((strLine = br.readLine()) != null) {
					sb.append(strLine);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sb.toString();
		}

		/**
		 * 返回所有的系统环境变量
		 * 
		 * @return
		 */
		public static Map<String, String> getEnv() {
			Map<String, String> map = new HashMap<String, String>();
			Process p = null;
			try {
				p = Runtime.getRuntime().exec("cmd /c set");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					String[] str = line.split("=");
					map.put(str[0], str[1]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return map;
		}

		public static String checkNull(String str) {
			if (str == null) {
				str = "";
			}
			return str;
		}

		public static String checkNull(Object obj) {
			String str = "";
			if (obj != null) {
				str = obj.toString();
			}
			return str;
		}

		public static Integer[] convertArray(int[] intArray) {
			if (intArray == null)
				return null;
			Integer[] array = new Integer[intArray.length];
			for (int i = 0; i < intArray.length; i++) {
				array[i] = intArray[i];
			}
			return array;
		}

		public static Long[] convertArray(long[] intArray) {
			if (intArray == null)
				return null;
			Long[] array = new Long[intArray.length];
			for (int i = 0; i < intArray.length; i++) {
				array[i] = intArray[i];
			}
			return array;

		}

		/**
		 * 字符串补前位
		 * 
		 * @param str
		 * @param len
		 * @param replaceStr
		 * @return
		 */
		public static String enoughStr(String str, int len, String replaceStr) {
			while (str.length() < len) {
				str = replaceStr + str;
			}
			return str;
		}

		/**
		 * 字符串补后位
		 * 
		 * @param str
		 * @param len
		 * @param replaceStr
		 * @return
		 */
		public static String enoughNextStr(String str, int len, String replaceStr) {
			while (str.length() < len) {
				str = str + replaceStr;
			}
			return str;
		}

		/**
		 * 主要用于sql 语句
		 * 
		 * @param o
		 * @return
		 */
		public static String CheckNullForSql(Object o) {
			String str = "''";
			if (o != null) {
				str = "'" + o.toString() + "'";
			}
			return str;
		}

		/**
		 * 若参数为null则返回null，否则返回构造的Timestamp类型
		 * 
		 * @param date
		 * @return
		 */
		public static Timestamp getTimestamp(Date date) {
			if (date != null) {
				return new Timestamp(date.getTime());
			}
			return null;
		}

		/**
		 * 
		 * 把字符串转换为传入的枚举类型，若转换失败，则返回null。
		 * 
		 * @param <T>
		 * @param enumClass
		 * @param value
		 * @return 转换后的枚举类型
		 */
		public static <T extends Enum<T>> T enumValue(Class<T> enumClass,
				String value) {
			T returnValue = null;
			if (enumClass != null && value != null) {
				try {
					if (Enum.valueOf(enumClass, value) != null) {
						returnValue = Enum.valueOf(enumClass, value);
					}
				} catch (IllegalArgumentException e) {
					returnValue = null;
				}
			}
			return returnValue;
		}
		
		/**
		 * 枚举数组转换为字符串数组
		 * @param enums
		 * @return
		 */
		public static <T extends Enum<T>> String[] enumArrayToStringArray(T[] enums){
			List<String> list = new ArrayList<String>();
			if(enums!=null){
				for(T enumObject:enums){
					list.add(enumObject.toString());
				}
			}
			return list.toArray(new String[list.size()]);
		}

		/**
		 * 
		 * 把字符串转换为传入的枚举类型，若转换失败，则返回default。
		 * 
		 * @param <T>
		 * @param enumClass
		 * @param value
		 * @return 转换后的枚举类型
		 */
		public static <T extends Enum<T>> T enumValue(Class<T> enumClass,
				String value, T defaut) {
			T returnValue = defaut;
			if (enumClass != null && value != null) {
				try {
					if (Enum.valueOf(enumClass, value) != null) {
						returnValue = Enum.valueOf(enumClass, value);
					}
				} catch (IllegalArgumentException e) {
					returnValue = defaut;
				}
			}
			return returnValue;
		}

		/**
		 * 
		 * 把数组转换成字符串 separator 分隔符 wrapper 数组元素的包围字符如'和"等
		 * <p>
		 * Join each element in the array with the separator and wrapper e.g.
		 * [1,2,3] => ('1','2','3')
		 * 
		 * @param array
		 * @param separator
		 * @param wrapper
		 * @return 转换后的字符串
		 */
		public static String arrayJoin(String[] array, String separator,
				String wrapper) {
			String arrayString = "";
			if (null == wrapper) {
				wrapper = "";
			}
			if (array == null) {
				arrayString = "";
			} else if (array.length == 1) {
				arrayString = wrapper + array[0] + wrapper;
			} else if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					arrayString += wrapper + array[i] + wrapper + separator;
				}
				arrayString += wrapper + array[array.length - 1] + wrapper;
			}
			return arrayString;
		}

		public static String arrayJoin(int[] array, String separator, String wrapper) {
			String arrayString = "";
			if (null == wrapper) {
				wrapper = "";
			}
			if (array == null) {
				arrayString = "";
			} else if (array.length == 1) {
				arrayString = wrapper + array[0] + wrapper;
			} else if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					arrayString += wrapper + array[i] + wrapper + separator;
				}
				arrayString += wrapper + array[array.length - 1] + wrapper;
			}
			return arrayString;
		}

		public static String arrayJoin(long[] array, String separator,
				String wrapper) {
			String arrayString = "";
			if (null == wrapper) {
				wrapper = "";
			}
			if (array == null) {
				arrayString = "";
			} else if (array.length == 1) {
				arrayString = wrapper + array[0] + wrapper;
			} else if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					arrayString += wrapper + array[i] + wrapper + separator;
				}
				arrayString += wrapper + array[array.length - 1] + wrapper;
			}
			return arrayString;
		}

		public static String arrayJoin(Object[] array, String separator,
				String wrapper) {
			String arrayString = "";
			if (null == wrapper) {
				wrapper = "";
			}
			if (array == null) {
				arrayString = "";
			} else if (array.length == 1) {
				arrayString = wrapper + array[0] + wrapper;
			} else if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					arrayString += wrapper + array[i] + wrapper + separator;
				}
				arrayString += wrapper + array[array.length - 1] + wrapper;
			}
			return arrayString;
		}

		
		public static String listJoinInteger(List<Integer> list, String separator,
				String wrapper) {
			Integer[] array = list.toArray(new Integer[list.size()]);
			String arrayString = "";
			if (wrapper == null) {
				wrapper = "";
			}
			if (array == null) {
				arrayString = "";
			} else if (array.length == 1) {
				arrayString = wrapper + array[0].intValue() + wrapper;
			} else if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					arrayString += wrapper + array[i].intValue() + wrapper
							+ separator;
				}
				arrayString += wrapper + array[array.length - 1].intValue()
						+ wrapper;
			}
			return arrayString;
		}

		

		/**
		 * 判断某对象是否在指定的数组中
		 * 
		 * @param element
		 * @param array
		 * @return
		 */
		public static boolean isInArray(Object element, Object[] array) {
			boolean is = false;
			for (Object value : array) {
				if (element.equals(value)) {
					is = true;
					break;
				}
			}
			return is;
		}

		public static boolean isInArray(int element, int[] array) {
			boolean is = false;
			for (int value : array) {
				if (element == value) {
					is = true;
					break;
				}
			}
			return is;
		}

		public static Map<Integer, Integer> arrayToMap(int[] array) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < array.length; i++) {
				map.put(array[i], i);
			}
			return map;
		}

		

		

		/**
		 * 从ascii码的数据库取数时转码
		 * 
		 * @param virgin
		 * @return
		 */
		private static String convertCharSetFromAscToGBK(String virgin,
				boolean isAscii) {
			if (!isAscii)
				return Common.isEmpty(virgin) ? "" : virgin.trim();
			try {
				if (virgin == null)
					return "";
				return new String(virgin.getBytes("ISO8859-1"), "GBK");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		/**
		 * 写入ascii码的数据库时转码
		 * 
		 * @param str
		 * @return
		 */
		private static String changeCharset(String str, boolean isAscii) {
			if (!isAscii)
				return Common.isEmpty(str) ? "" : str;
			try {
				if (str != null) {
					// 用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
					byte[] bs = str.getBytes();
					return new String(bs, "ISO-8859-1");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		

		

		public static String[] replaceAll(String[] arr, String regex,
				String replacement) {
			if (arr == null || arr.length == 0)
				return null;
			for (int i = 0; i < arr.length; i++) {
				arr[i] = arr[i].replaceAll(regex, replacement);
			}
			return arr;
		}

		public static boolean isChineseAll(String str) {
			String regEx = "[^\u4e00-\u9fa5]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			if (m.find()) {
				return false;
			}
			return true;
		}

		public static boolean isCharacter(String str) {
			Pattern pattern = Pattern.compile("[a-zA-Z]*");
			Matcher m = pattern.matcher(str);
			if (m.find()) {
				return true;
			}
			return false;
		}

		public static boolean isCharacterAndNumber(String str) {
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			Matcher m = pattern.matcher(str);
			if (!m.matches()) {
				return false;
			}
			return true;
		}

		/**
		 * 是否包含数字
		 * 
		 * @param str
		 * @return
		 */
		public static boolean isNumeric(String str) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (isNum.find()) {
				return true;
			}
			return false;
		}

		/**
		 * 把字符串里面的数字字符替换成""
		 * 
		 * @param str
		 * @return
		 */
		public static String removeNumber(String str) {
			String v = "";
			if (str == null)
				return "";
			v = str.replaceAll("[0-9]", "").trim();
			if (v.trim().equals(""))
				v = "";
			return v;
		}

		/**
		 * 是否全部为数字
		 * 
		 * @param str
		 * @return
		 */
		public static boolean isNumericAll(String str) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches()) {
				return false;
			}
			return true;
		}

		/**
		 * 把字符串里面的非数字字符替换成""
		 * 
		 * @param str
		 * @return
		 */
		public static String removeNonNumber(String str) {
			String v = "";
			if (str == null)
				return "";
			v = str.replaceAll("[^0-9]", "").trim();
			if (v.trim().equals(""))
				v = "0";
			return v;
		}

		/**
		 * 把字符串里面的非数字字符替换成方法参数里面指定的substitution
		 * 
		 * @param str
		 * @param substitution
		 * @return
		 */
		public static String replaceNonNumber(String str, String substitution) {
			String v = "";
			if (str == null)
				return "";
			v = str.replaceAll("[^0-9]", substitution).trim();// 非数字的用substitution代替
			if (v.trim().equals(""))
				v = "0";
			return v;
		}
		
		/**
		 * 日期格式化
		 * @param date
		 * @return
		 */
		public static String dateFormat(Date date) {
			if (date == null){
				return "";
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		}
		
		/**
		 * 日期格式化(传入格式化字符串)
		 * @param date
		 * @return
		 */
		public static String dateFormat(Date date, String pattern) {
			if (date == null) {
				return "";
			}
			try {
				DateFormat format = new SimpleDateFormat(pattern);
				return format.format(date);
			} catch (Exception e) {
				return "";
			}
		}
		
		/**
		 * 将时间转换为中文表达
		 * 比如：10点24分 --> 十时二十四分
		 * @param time
		 * @return 转换后的中文表达
		 */
		public static String castTimeToChinese(Date time) {
			StringBuffer timeStr = new StringBuffer();
			String[] times = new SimpleDateFormat("HH:mm:ss").format(time).split(
					":");
			int shi = Integer.parseInt(times[0]);
			int fen = Integer.parseInt(times[1]);
			// int miao = Integer.parseInt(times[2]);
			if (shi == 0) {
				timeStr.append("零").append("时");
			} else if (shi < 10) {
				timeStr.append(castNumberToChinese(shi)).append("时");
			} else if (shi >= 10 && shi < 20) {
				timeStr.append("十")
						.append((shi % 10) == 0 ? ""
								: castNumberToChinese(shi % 10)).append("时");
			} else {
				timeStr.append(castNumberToChinese(shi / 10))
						.append("十")
						.append((shi % 10) == 0 ? ""
								: castNumberToChinese(shi % 10)).append("时");
			}
			if (fen == 0) {
				timeStr.append("零").append("分");
			} else if (fen < 10) {
				timeStr.append(castNumberToChinese(fen)).append("分");
			} else if (fen >= 10 && fen < 20) {
				timeStr.append("十")
						.append((fen % 10) == 0 ? ""
								: castNumberToChinese(fen % 10)).append("分");
			} else {
				timeStr.append(castNumberToChinese(fen / 10))
						.append("十")
						.append((fen % 10) == 0 ? ""
								: castNumberToChinese(fen % 10)).append("分");
			}
			return timeStr.toString();
		}

		/**
		 * 将数字转成中文汉的形式
		 * @param number
		 * @return 转换后的中文
		 */
		public static String castNumberToChinese(int number) {
			String numberStr = null;
			switch (number) {
			case 0:
				numberStr = "零";
				break;
			case 1:
				numberStr = "一";
				break;
			case 2:
				numberStr = "二";
				break;
			case 3:
				numberStr = "三";
				break;
			case 4:
				numberStr = "四";
				break;
			case 5:
				numberStr = "五";
				break;
			case 6:
				numberStr = "六";
				break;
			case 7:
				numberStr = "七";
				break;
			case 8:
				numberStr = "八";
				break;
			case 9:
				numberStr = "九";
				break;
			}
			return numberStr;
		}
		
		

		/**
		 * 数字转罗马数字
		 * @param n
		 * @return 罗马数字
		 */
		public static String castNumberToRoman(int n) {
			int[] arabic = new int[13];
			String[] roman = new String[13];
			int i = 0;
			String o = "";
			arabic[0] = 1000;
			arabic[1] = 900;
			arabic[2] = 500;
			arabic[3] = 400;
			arabic[4] = 100;
			arabic[5] = 90;
			arabic[6] = 50;
			arabic[7] = 40;
			arabic[8] = 10;
			arabic[9] = 9;
			arabic[10] = 5;
			arabic[11] = 4;
			arabic[12] = 1;

			roman[0] = "M";
			roman[1] = "CM";
			roman[2] = "D";
			roman[3] = "CD";
			roman[4] = "C";
			roman[5] = "XC";
			roman[6] = "L";
			roman[7] = "XL";
			roman[8] = "X";
			roman[9] = "IX";
			roman[10] = "V";
			roman[11] = "IV";
			roman[12] = "I";

			while (n > 0) {
				while (n >= arabic[i]) {
					n = n - arabic[i];
					o = o + roman[i];
				}
				i++;
			}
			return o;
		}
		
		/**
		 * 对传入的日期对象，去掉秒和微秒。
		 * @param date
		 * @return
		 */
		public static Date removeSecond(Date date){
			//去掉秒和微秒
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		}
		
		public static Date removeMillisecond(Date date){
			//去掉毫秒
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		}
		
		/**
		 * 去除字符串中的十六进制字符 某些字段的值里面有些特殊的不可见的字符导致输入到SOAP的时候XML报错
		 * 
		 * @param s
		 * @return
		 */
		public static String removeSpecialCodePoint(String s) {
			if(s==null) return "";
			Integer[] _16Arr = new Integer[] { 31 };
			List<Integer> _16List = new ArrayList<Integer>(_16Arr.length);
			for (int i = 0; i < _16Arr.length; i++)
				_16List.add(_16Arr[i]);
			if (isEmpty(s))
				return null;
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				if (!_16List.contains(s.codePointAt(i))) {
					builder.append(s.charAt(i));
				}
			}
			return builder.toString().trim();
		}
		
		
		
		public static String getUUID(){
			return UUID.randomUUID().toString().replace("-", "");
		}
		
		public static Integer[] convertArrayIntegers(Integer[] integers) {
			if (integers == null)
				return null;
			Integer[] array = new Integer[integers.length];
			for (int i = 0; i < integers.length; i++) {
				array[i] = integers[i];
			}
			return array;
		}
		
		/**
		 * his使用的转码
		 * 
		 * @param str
		 * @param isAscii
		 * @return
		 */
		public static String convertFromHisByte(String str) {
			return convertCharSetFromByteToGBK(str);
		}
		
		  public  static int daysBetween(Date date1,Date date2)  
	    {  
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(date1);  
	        long time1 = cal.getTimeInMillis();               
	        cal.setTime(date2);  
	        long time2 = cal.getTimeInMillis();       
	        long between_days=(time2-time1)/(1000*3600*24);  
	          
	       return Integer.parseInt(String.valueOf(between_days));         
	    }  
		
		/**
		 * 从ascii码的数据库取数时转码
		 * 
		 * @param virgin
		 * @return
		 */
		private static String convertCharSetFromByteToGBK(String virgin) {
			if (virgin == null)
				return "";
			try {
				Pattern p = Pattern.compile("[10]{8}");		
				// 定义匹配器，与源字符串关连上	
				Matcher m = p.matcher(virgin);		
				// 安放匹配结果		
				List<Byte> list = new ArrayList<Byte>();		
				// 开始搜寻pattern		
				// 先将8位的字符串按2进制扫描为Integer		
				// 由于后面的须求数字再强制转成byte		
				// 存入list中		
				while (m.find()) {			
					list.add((byte) Integer.parseInt(m.group(), 2));		
				}		
				// 准备将list转为byte数组		
				// 由于String构造器参数类型的限制		
				byte[] b = new byte[list.size()];		
				// 开始转换		
				for (int j = 0; j < b.length; j++) {			
					b[j] = list.remove(0);		
				}		
				// 将数组转换为String输出		
				// 故意不指定字符集(GBK)，让编绎器按系统默认打印		
				return new String(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
		
		 /** 
	     * 使用java正则表达式去掉多余的.与0 
	     * @param s 
	     * @return  
	     */  
	    public static String subZeroAndDot(String s){  
	        if(s.indexOf(".") > 0){  
	            s = s.replaceAll("0+?$", "");//去掉多余的0  
	            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
	        }  
	        return s;  
	    }
	    
	    public static void main(String[] args) {
	    	for(int i=0;i<100;i++){
	    		System.out.println(Common.getUUID());
	    	}
			
		}
}
