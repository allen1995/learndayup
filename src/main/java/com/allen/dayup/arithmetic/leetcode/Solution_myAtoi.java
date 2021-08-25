package com.allen.dayup.arithmetic.leetcode;

class Solution_myAtoi {

    public static void main(String[] args) {
        System.out.println(myAtoi1(" abcd-5"));
    }

    public static int myAtoi1(String str) {
        str = str.trim();//去掉前后的空格
        //如果为空，直接返回0
        if (str.length() == 0)
            return 0;
        int index = 0;//遍历字符串中字符的位置
        int res = 0;//最终结果
        int sign = 1;//符号，1是正数，-1是负数，默认为正数
        int length = str.length();
        //判断符号
        if (str.charAt(index) == '-' || str.charAt(index) == '+')
            sign = str.charAt(index++) == '+' ? 1 : -1;
        for (; index < length; ++index) {
            //取出字符串中字符，然后转化为数字
            int digit = str.charAt(index) - '0';
            //按照题中的要求，读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。
            //字符串的其余部分将被忽略。如果读取了非数字，后面的都要忽略
            if (digit < 0 || digit > 9)
                break;
            //越界处理
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            else
                res = res * 10 + digit;
        }
        return sign * res;
    }

    public static int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int res = 0;
        int sign = 0;
        int i = 0;
        boolean breakOffFlag = false;

        int j = 0;
        while (true) {
            if (s.charAt(j) == ' ') {
                if (++j >= s.length()) {
                    return 0;
                }
                continue;
            } else {
                if (s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    sign = 1;
                }
                break;
            }
        }


        for (; i < s.length(); i++) {
            if (sign != 0 && !breakOffFlag  && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                return 0;
            }

            if ( sign == 0 && s.charAt(i) == '-') {
                sign = -1;
            }

            if ( sign == 0 && s.charAt(i) == '+') {
                sign = 1;
            }

            int digit = s.charAt(i) - '0';

            if (digit < 0 || digit > 9) {
                if (breakOffFlag) {
                    break;
                } else {
                    continue;
                }
            }

            if (res > Integer.MAX_VALUE / 10 ||
                    (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign > 0 ? Integer.MAX_VALUE : res < 0 ? 0 : Integer.MIN_VALUE;
            } else {
                if (!breakOffFlag) {
                    breakOffFlag = true;
                }
                res = res * 10 + digit;
            }


        }

        return sign * res;
    }
}