package com.allen.dayup.arithmetic;

import com.google.common.base.Splitter;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 20190598
 * @Date: 2020/1/9 09:42
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws IOException {
        //System.out.println(test("160721102613"));
        isPalindrome("0P");

    }

    public static boolean isPalindrome(String s) {
        if(countEffectiveAlphabet(s) < 2) {
            return true;
        }

        s = s.toLowerCase();
        for(int i = 0, j = s.length()-1; i < j;) {
            while( !isLowerAlphabet(s.charAt(i)) ) {
                i++;
            }

            while( !isLowerAlphabet(s.charAt(j))) {
                j--;
            }

            if( s.charAt(i) != s.charAt(j) ) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private static boolean isLowerAlphabet(char chr) {
        return (chr <= 122 && chr >= 97) || (chr >= 48 && chr <= 57);
    }

    private static   int countEffectiveAlphabet(String s) {
        int result = 0;

        for(int i = 0 ; i < s.length(); i++) {
            if( isLowerAlphabet(s.charAt(i))) {
                result++;
            }
        }

        return result;
    }
    

    public static LocalDateTime test(String opentimeHex) {
        if( opentimeHex != null && opentimeHex.length() > 0 && (new BigInteger(opentimeHex, 16).intValue() > 0)) {
            List<String> opentimeHexList = Splitter.fixedLength(2).splitToList(opentimeHex);
            String opentime = opentimeHexList.stream()
                    //16进制转换成十进制
                    .map( s -> {
                        String result = "";

                        BigInteger bigInteger = new BigInteger(s, 16);
                        int value = bigInteger.intValue();
                        //if( value/10 == 0 ){
                        //    result = "0" + value;
                        //} else {
                        //    result = String.valueOf(value);
                        //}

                        result = String.valueOf(value);
                        return result;
                    })
                    .reduce("", (a,b) -> a+b);

            LocalDateTime opentimeDate = LocalDateTime.parse(opentime, DateTimeFormatter.ofPattern("yyMMddHHmmss"));
            return opentimeDate;
        }

        return null;
    }

    private static boolean compareVersion(String version1, String version2) {
        if ( version1 == null || version2 == null ) {
            return false;
        }

        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length, n2 = nums2.length;

        // compare versions
        int i1, i2;
        for (int i = 0; i < Math.max(n1, n2); ++i) {
            i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            if (i1 != i2) {
                return i1 > i2 ? true : false;
            }
        }
        // the versions are equal
        return true;
    }

    private static void count(int a){
        a++;
    }
}
