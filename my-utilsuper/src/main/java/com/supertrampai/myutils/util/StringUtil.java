package com.supertrampai.myutils.util;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	//生成 j 位随机数字
	public static String getRndNum(int j){
		char c[] = {'1','2','3','4','5','6','7','8','9','0'};
		StringBuilder sRand = new StringBuilder();
		for (int i = 0; i < j; i++){
			Random random = new Random();
			int index = random.nextInt(10);
			String rand = String.valueOf(c[index]);
			sRand.append(rand);
		}
		return sRand.toString();
	}
	
	public static String getStrByArray(Integer[] arr){
		StringBuffer result = new StringBuffer();
		if(arr != null && arr.length > 0){
			result.append(",");
			for(Integer str:arr){
				result.append(str).append(",");
			}
		}
		return result.toString();
	}

	//返回前后无逗号的字符串
	public static String getStrByArray(String[] arr){
		StringBuffer result = new StringBuffer();
		if(arr != null && arr.length > 0){
			for(String str:arr){
				if(StringUtils.hasText(str)) {
					result.append(str).append(",");
				}
			}
			return result.substring(0,result.length()-1);
		}
		return "";
	}
	
	public static Integer[] getArrayByStr(String str){
		Integer[] arr = null;
		if(str != null && str.length() > 0){
			if(str.startsWith(",")){
				str = str.substring(1);
			}
			if(str.endsWith(",")){
				str = str.substring(0,str.length()-1);
			}
			String[] strarr = str.split(",");
			arr = new Integer[strarr.length];
			for(int i = 0; i<arr.length; i++){
				arr[i] = Integer.valueOf(StringUtils.trimAllWhitespace(strarr[i]));
			}
		}
		return arr;
	}
	
	public static Set<Integer> getSetByStr(String str){
		Set<Integer> arr = new HashSet<Integer>();
		if(str != null && str.length() > 0){
			if(str.startsWith(",")){
				str = str.substring(1);
			}
			if(str.endsWith(",")){
				str = str.substring(0,str.length()-1);
			}
			String[] strarr = str.split(",");
			for(int i = 0; i<strarr.length; i++){
				arr.add(Integer.valueOf(strarr[i]));
			}
		}
		return arr;
	}
	
	/**验证邮箱*/
	public static boolean validEmail(String email){
		email = voidNull(email);
        String pattern1 = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";  
        Pattern pattern = Pattern.compile(pattern1);  
        Matcher mat = pattern.matcher(email);
        return mat.matches();
	}
	
//	public static void main(String[] args){
//		int a = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
//		System.out.println(a+"13918187318".substring(6));
//	}
	
	public static String voidNull(String args){
		if(args==null){
			//return Constants.BLANKSTRING;
		}
		return args;
	}
	
//	/**验证密码位数*/
//	public static boolean validPasswd(String passwd){
//		Pattern p = Pattern.compile("^(1[3,4,5,7,8])\\d{9}$");
//        Matcher m = p.matcher(mobile);
//        return m.matches(); 
//	}
	
	/**验证手机号码*/
	public static boolean validMobile(String mobile){
		mobile = voidNull(mobile);
		Pattern p = Pattern.compile("^(1[3,4,5,7,8,9])\\d{9}$");
        Matcher m = p.matcher(mobile);
        return m.matches();  
	}
	/**验证邮编*/
	public static boolean validPostCode(String code){
		code = voidNull(code);
		Pattern p = Pattern.compile("^[0-9]{6}$");     
        Matcher m = p.matcher(code);     
        return m.matches();  
	}
	/**验证固定电话号码*/
	public static boolean validPhone(String phone){
		phone = voidNull(phone);
		Pattern p = Pattern.compile("^[0-9\\-]{0,}$");
        Matcher m = p.matcher(phone);
        return m.matches();  
	}
	/**验证中文名*/
	public static boolean validChinese(String str){
		str = voidNull(str);
		Pattern p=Pattern.compile("^[\u4E00-\u9FA5]*$");
		Matcher m =p.matcher(str);
		return m.matches();
	}
	/**验证字符串 数字字母6-16位*/
	public static boolean validString(String str){
		str = voidNull(str);
		Pattern p=Pattern.compile("^\\w{6,16}$");
		Matcher m =p.matcher(str);
		return m.matches();
	}
	
	public static String escapeHtml(String str){
		if(str!=null){
			return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
		return "";
	}
	
	public static boolean isNumeric(String str) {
		if(str!=null && str.length()>0) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (isNum.matches()) {
				return true;
			}
		}
		return false;
	}
	
	/* 截取为最大为10个字符的字符串 */
	public static String reduceTo10Char(String str) {
		if(str.length()>10) {
			str =str.substring(0, 9)+"…";
		}
		return str;
	}
}
