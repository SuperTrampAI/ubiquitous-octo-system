package com.github.supertrampai.util;

import com.sun.crypto.provider.SunJCE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.MessageDigest;

@Component
public class SecurityUtil {
    //public static String DEFAULTKEY;//="XmLyaRt";
    public static String DEFAULTKEY ="XmLyaRt";

    public SecurityUtil() {
        java.security.Security.addProvider(new SunJCE());
    }

    public static String getMD5(String s) {
        return getMD5(s, -1, DEFAULTKEY);
    }

    private static String getMD5(String s, int i, String key) {
        if (i == -1) {
            return DigestUtils.md5DigestAsHex((key + s).getBytes());
        } else if (i == 0) {
            return DigestUtils.md5DigestAsHex((s).getBytes());
        } else if (i == 1) {
            return DigestUtils.md5DigestAsHex((s + key).getBytes());
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(new SecurityUtil().des_encrypt("155|201812031459"));
    }

    public String des_encrypt(String strIn) {
        try {
            Key key = getKey(DEFAULTKEY.getBytes());
            Cipher encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            return byteArr2HexStr(encryptCipher.doFinal(strIn.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String des_decrypt(String strIn) {
        try {
            Key key = getKey(DEFAULTKEY.getBytes());
            Cipher decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            return new String(decryptCipher.doFinal(hexStr2ByteArr(strIn)));
        } catch (Exception e) {
            System.out.println("无效token");
            return null;
        }
    }

    private String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    private byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
    }
    

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
                           '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
 
    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
 
    public static String encodeSHA1(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}