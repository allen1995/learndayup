package com.allen.dayup.arithmetic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Auther: 20190598
 * @Date: 2020/1/9 09:42
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //System.out.println(test("160721102613"));
        //System.out.println( Boolean.TRUE);
        //byte[] bytes = {48,48,48};
        //System.out.println(new String(bytes, "utf-8"));
        Map<String,Object> data = new HashMap<>();
        data.put("test", "[1234]");

        String result = data.get("test").toString();
        System.out.println(result);

        System.out.println(data.get("test").toString().matches("(^\\[.*\\]$)"));

        Map<String,Object> map = new HashMap();
        map.put("test","test");

        for( Map.Entry<String,Object> entry : map.entrySet() ) {

        }

    }

    public static  void rotate(int[][] matrix) {
        int length = matrix.length;

        //先上下交换
        for( int i = 0; i < length/2; i++ ) {
            int[] temp = matrix[i];
            matrix[i] = matrix[length-i-1];
            matrix[length-i-1] = temp;
        }

        //对角线交换
        for( int line = 0; line < length; line++) {
            for( int col = line; col < length; col++) {
                int temp = matrix[line][col];
                matrix[line][col] = matrix[col][line];
                matrix[col][line] = temp;
            }
        }
    }

    public static void printArr(int[][] matrix) {
        for ( int i = 0; i < matrix.length; i++) {
            for( int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if(i<nums.length && nums[i] != 0) {
                nums[j++] = nums[i];
            }

            if(i++ >= nums.length) {
                nums[j++] = 0;
            }
        }
    }

    public static void testFinallyexception() throws FileNotFoundException {
        try {
            int a = 12;
            throw new FileNotFoundException("try exception log.");
        }  finally {
            throw new FileNotFoundException("finally exception log.");
        }
    }

    public static void rightFinallyexceptio2() throws FileNotFoundException {

        FileNotFoundException e = new FileNotFoundException();

        try {
            int a = 12;
            throw new FileNotFoundException("try exception log.");
        } catch ( IOException ex ) {
            e.addSuppressed(ex);
        } finally {
            try {
                throw new FileNotFoundException("finally exception log.");
            } catch (FileNotFoundException ex) {
                e.addSuppressed(ex);
            }

            throw e;
        }
    }

    public static void rotate(int[] nums, int k) {
        k = k % 7;

        if( nums.length == 1 || k == 0 ) {
            return;
        }

        int[] temp = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int j = (i+k) % 7;
            temp[j] = nums[i];
        }

        for (int i = 0; i < temp.length; i++) {

        }
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
