package com.allen.dayup.面经手册.chap2.lock;

/**
 * @Auther: allen
 * @Date: 2021-07-03 09:01
 * @Description:
 */
public class ApiTest {

    private static VT vt = new VT();

    public static void main(String[] args) {
        Thread thread = new Thread(vt);
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                vt.sign = true;
                System.out.printf("线程【%s】通知【%s】循环结束！",
                        Thread.currentThread().getName(), thread.getName());
            }
        }).start();

        try {
            Thread.currentThread().join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
