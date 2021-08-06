package com.allen.dayup.java8.io.nio;

import java.nio.ByteBuffer;

/**
 * @Auther: allen
 * @Date: 2020-09-26 21:15
 * @Description:
 */
public class BufferDemo {

    public static void main(String[] args) {

        //初始化ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        // mark[-1] position[0] limit[8] capacity[8]

        byteBuffer.put((byte)1);
        displayByteBufferMetadata(byteBuffer);

        // rewind() position倒回0
        byteBuffer.rewind();

        displayByteBufferMetadata(byteBuffer);

        byteBuffer.put((byte) 'a');
        byteBuffer.put((byte) 'b');

        displayByteBufferMetadata(byteBuffer);
        // position->0,limit->有效位【写入了多少位】, capacity->length
        byteBuffer.flip();

        displayByteBufferMetadata(byteBuffer);

    }

    private static void displayByteBufferMetadata(ByteBuffer byteBuffer) {
        System.out.printf("当前 Buffer[type %s], position：%d, limit: %d, capacity:%d \n",
                byteBuffer.getClass().getName(), byteBuffer.position(), byteBuffer.limit(),
                byteBuffer.capacity());

    }
}
