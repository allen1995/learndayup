package com.allen.dayup.guava;

/**
 * @Auther: 20190598
 * @Date: 2021/9/2 14:12
 * @Description:
 */
public class UserDto {

    private String nickName;

    private String age;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "nickName='" + nickName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
