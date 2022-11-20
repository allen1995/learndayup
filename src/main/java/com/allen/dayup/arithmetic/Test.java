package com.allen.dayup.arithmetic;

import com.allen.dayup.arithmetic.二分查找.Jackson2Util;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.RandomUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 20190598
 * @Date: 2020/1/9 09:42
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ParseException {

        String json = "{\n" +
                "\t\"appversion\": \"2.3.0\",\n" +
                "\t\"sysversion\": 1,\n" +
                "\t\"imei\": \"a1ojaGmf3va\",\n" +
                "\t\"userid\": \"230243d345a34138af70bd920a769bb2\",\n" +
                "\t\"traceid\": \"8af70bd920a769bb2\",\n" +
                "\t\"param\": {\n" +
                "\t\t\"userid\": \"50e9si834d4039a6918f3ee902746c1c54022820\",\n" +
                "\t\t\"iotid\": \"test\",\n" +
                "\t\t\"userName\": \"alun\",\n" +
                "\t\t\"commodityId\": 1,\n" +
                "\t\t\"timestamp\": 1659424953673,\n" +
                "\t\t\"sign\": \"Xvjv+gZE3Mq8zIcObugTf27cifey5WSRWDGepzDcdlR5vHWZNU0BVvxxqsJ4YJXT3FM0Mx2Aj12ZH58DPS+QsjnRdEI/vFQJeFlUMK9TXBfWeTaDPUwKCLdjUWAEfS0Oy0NGHjzdhZIdX3w93CVybgdHXDnQgue8kACNykNlu1Qm/jMg5GbJ3YQaIbC7rJEkf9JpcWJmj84s1NfLw/Ytzr8pAOiafpNIa5CzRXmGIYyIHqyj6fBhSEFe63GuUXUSwBAstxPArLfag8quPXurOWIFcPNkAlb9LUP0zchAXeEDQCGHq/V5lFRtGR1sgDsqyRLrRMlZYk1r5BELI8tDfg==\"\n" +
                "\t}\n" +
                "}";
        //
        //String t = "{\n\t\t\"userid\": \"50e9si834d4039a6918f3ee902746c1c54022820\",\n\t\t\"iotid\": \"test\",\n\t\t\"userName\": \"alun\",\n\t\t\"commodityId\": 1,\n\t\t\"timestamp\": 1659424953673,\n\t\t\"sign\": \"Xvjv+gZE3Mq8zIcObugTf27cifey5WSRWDGepzDcdlR5vHWZNU0BVvxxqsJ4YJXT3FM0Mx2Aj12ZH58DPS+QsjnRdEI/vFQJeFlUMK9TXBfWeTaDPUwKCLdjUWAEfS0Oy0NGHjzdhZIdX3w93CVybgdHXDnQgue8kACNykNlu1Qm/jMg5GbJ3YQaIbC7rJEkf9JpcWJmj84s1NfLw/Ytzr8pAOiafpNIa5CzRXmGIYyIHqyj6fBhSEFe63GuUXUSwBAstxPArLfag8quPXurOWIFcPNkAlb9LUP0zchAXeEDQCGHq/V5lFRtGR1sgDsqyRLrRMlZYk1r5BELI8tDfg==\"\n\t}";
        //
        //System.out.println(t);
        //
        LinkedHashMap<String,String> map2 = Jackson2Util.converJsonToObj(json, LinkedHashMap.class);
        //System.out.println(Jackson2Util.converJsonToObj(json, LinkedHashMap.class));

        //System.out.println(Objects.hash("18565105139"));
        //
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS")));

        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.parse("2022-09-08 18:03:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) ).toMinutes() * 60);
    }

    public static String getTimeWithOffset(String format,int offset) {
        Date date = new Date();
        Date newDate = new Date(date.getTime()+ TimeUnit.MINUTES.toMillis(offset));
        return DateTimeUtils.parseDateToString(format,newDate);
    }

    public static long getTimediff() throws ParseException {
        Integer endTime = null;
        try {
            endTime = DateTimeUtils.getTimeWithOffset("0830", "HHmm", -0);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        Integer endTimegaphour = endTime/100;
        Integer endTimegapmin = endTime-(endTimegaphour*100);
        Integer startTimegaphour = 800/100;
        Integer startTimegapmin = 800-(startTimegaphour*100);

        //String currentTime = DateTimeUtils.getCurrentTimeWithoutDayTimeUnit();
        Date date = new Date();
        String time="2022-6-10 08:29:04";

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = formatter.parse(time);
        String currentTime = DateTimeUtils.parseDateToString("HHmm", date);;
        Integer currenthour = Integer.valueOf(currentTime)/100;
        Integer currentmin = Integer.valueOf(currentTime) - currenthour*100;
        //当前时间和结束时间之间的时间差
        Integer nextProcessSecond = 3600*(endTimegaphour-currenthour)+60*(endTimegapmin-currentmin);
        Integer gapBetweenStartTimeAndEndTime = 3600*(endTimegaphour-startTimegaphour)+60*(endTimegapmin-startTimegapmin);
        return nextProcessSecond;
    }

    private static void switchTest() {
        String cmd = null;

        switch (cmd) {
            case "aa":
                System.out.println("aa");
                break;
            case "bb":
                System.out.println("bb");
                break;
            default:
                System.out.println("default");
                break;
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
