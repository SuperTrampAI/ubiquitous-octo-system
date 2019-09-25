package com.supertrampai.myutils.util;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class NumberUtil {

    public static Long[] splitArray(long args) {
        List<Long> a = split(args);
        return a.toArray(new Long[a.size()]);
    }

    // 分解
    public static List<Long> split(long i) {
        List<Long> a = new ArrayList();
        for (long j = 1; j <= i; j = j * 2) {
            if ((i & j) > 0) {
                a.add(j);
            }
        }
        return a;
    }

    // 是否包含
    public static boolean ifExist(long comp, long single) {
        return single==0 || (comp & single) > 0;
    }

    private final static BigInteger ZERO = new BigInteger("0");

    private final static BigInteger TWO = new BigInteger("2");

    public static String[] splitArray(String args) {
        List<String> a = split(args);
        return a.toArray(new String[a.size()]);
    }

    public static List<String> split(String str) {
        List<String> a = new ArrayList<String>();
        BigInteger i = new BigInteger(str);
        BigInteger j = new BigInteger("1");
        while (j.compareTo(i) <= 0) {
            if (ifExist(i.toString(), j.toString())) {
                a.add(j.toString());
            }
            j = j.multiply(TWO);
        }
        return a;
    }

    public static boolean ifExist(String comp, String single) {
        BigInteger bi = new BigInteger(comp);
        BigInteger bi2 = new BigInteger(single);
        if (bi.compareTo(bi2) < 0) {
            return false;
        }
        String str1 = bi.toString(2);
        String str2 = bi2.toString(2);
        int length = str1.length() - str2.length();
        if (length > 0) {
            str1 = str1.substring(length);
        } else if (length < 0) {
            str2 = str2.substring(Math.abs(length));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '1' && str1.charAt(i) == str2.charAt(i)) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        bi = new BigInteger(sb.toString());
        return bi.compareTo(ZERO) > 0;
    }

    /**
     * 转换成保留2位小数的格式.
     *
     * @param number
     * @return
     */
    public static Float formatNumber(Float number) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(number);// format 返回的是字符串
        return Float.parseFloat(p);
    }

    /**
     * 计算百分比
     *
     * @param num
     * @param total
     * @return
     */
    public static String getPercent(double num, double total) {
        String result = "";// 接受百分比的值
        double percent = num / total;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2); //保留到小数点后几位
        result = nf.format(percent);
        return result;
    }
}