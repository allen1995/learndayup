package com.allen.dayup.thinkinjava.cahp13;

import java.util.Arrays;

/**
 * @Auther: 20190598
 * @Date: 2019/11/6 17:05
 * @Description:
 */
public class Splitting {

    public static String knights = "Then, when you have found the shurubbery , you must " +
            "cut down the mightiest  in the forest..." + " with ... a herring!";

    public static String split(String regex){
        return Arrays.toString(knights.split(regex));
    }

    public static void main(String[] args) {
        //System.out.println(split("the|you"));

        //String regex = "^\\d";
        //Pattern pattern = Pattern.compile(regex);
        //Matcher matcher = pattern.matcher("12AASDDFdfdsf.");
        //boolean flag = matcher.matches();
        //System.out.println(flag);

        //替换所有的元音字母
        String result = knights.replaceAll("e|o|u", "_");
        System.out.println(result);
    }
}
