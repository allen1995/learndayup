package com.allen.dayup.guava;

import com.google.common.base.*;
import com.google.common.collect.*;

import java.util.Objects;

/**
 * @Auther: allen
 * @Date: 2019-12-15 16:52
 * @Description:
 */
public class GuavaDemo {

    public static void main(String[] args) {
        GuavaDemo guavaDemo = new GuavaDemo();
        System.out.println(guavaDemo.optionalMethodOrnull(null));

        //Objects 是对java Object的扩展
        User user = new User("allen",15);
        User user1 = new User("allen",15);

        System.out.println(Objects.equals(user,user1));
        System.out.println(Objects.hashCode(user));

        //BeanUtils

        System.out.println(MoreObjects.toStringHelper(user).add("name","testName").toString());

        user = null;
        System.out.println(MoreObjects.firstNonNull(user, new User("allen",12)));

        //Strings,Joiner,Splitter,CaseFormat
        //输入为空时转换成""
        System.out.println(Strings.nullToEmpty(null).equals(""));
        //复制字符串
        System.out.println(Strings.repeat("abc",3));

        //拼接字符串
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("1",null,"2","3"));

        //分割字符串
        String splitStr = Splitter.on(';')
                .trimResults()
                .omitEmptyStrings()
                .split("1 ;2;3;4;;;").toString();
        System.out.println(splitStr);

        //格式转化
        String userName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "user_name");
        System.out.printf("userName: %s \n", userName);

        //ImmutableList,Multiset,Multimap
        ImmutableList<String> list = ImmutableList.of("1","2","3");
        list.stream().forEach(System.out::print);

        System.out.println();

        Multiset<String> set = HashMultiset.create();
        set.add("1");
        set.add("1");
        set.stream().forEach(System.out::print);

        System.out.println();

        //ArrayListMultimap允许重复的key
        Multimap<String,String> map = ArrayListMultimap.create();
        map.put("Test","1");
        map.put("Test","2");
        System.out.println(map.get("Test"));


    }


    //Preconditions对参数做前置检查,经常用在方法的最前面，对参数进行校验，不符合则抛出异常
    /**
     * Guava参数无效检查
     * @param user
     */
    public void checkArg(User user){
        Preconditions.checkArgument( user != null, "user null error");
    }

    /**
     * 参数非空检查
     * @param user
     */
    public void checkArgNotNull(User user){
        Preconditions.checkNotNull(user);
    }

    /**
     * or() 传入空时，返回自定义的默认值
     * @param s
     * 参数为空时，赋默认值
     * @return
     */
    public String optionalMethod(String s){
        String result = null;
        Optional<String> optional = Optional.fromNullable(s);
        if( optional.isPresent() ){
            return optional.get();
        }

        return optional.or("default string");
    }

    /**
     * or() 参数为空时，赋默认值，默认值通过lambda表达式覆盖方法获取
     * @param s
     * @return
     */
    public String optionalMethodSupplier(String s){
        String result = null;
        Optional<String> optional = Optional.fromNullable(s);
        if( optional.isPresent() ){
            return optional.get();
        }

        result = optional.or( ()-> {
                return "default string";
            }
        );
        return  result;
    }

    /**
     * orNull()传入空时返回null
     * @param s
     * @return
     */
    public String optionalMethodOrnull(String s){
        String result = "";
        Optional<String> optional = Optional.fromNullable(s);

        result = optional.orNull();
        return result;
    }

    /**
     * transform() 对传入的字符串做转化
     * @param s
     * @return
     */
    public String optionalTranform(String s){
        Optional<String> optional = Optional.fromNullable(s);
        optional.transform( input -> {
            if( input.equals("Test") ){
                return "transform string";
            }
            return null;
        });
        return optional.orNull();
    }

    /**
     * ComparisonChain 提供两个Object的链式比较
     * @param user
     * @param user2
     * @return
     */
    public boolean comparisonChain(User user, User user2){

        return ComparisonChain.start()
                .compare(user.getName(), user2.getName())
                .compare(user.getAge(), user2.getAge())
                .result() == 0 ? true : false;
    }
}
