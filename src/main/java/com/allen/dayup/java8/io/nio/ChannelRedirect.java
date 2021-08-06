package com.allen.dayup.java8.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @Auther: allen
 * @Date: 2020-09-26 21:47
 * @Description:
 */
public class ChannelRedirect {

    public static void main(String[] args) throws IOException {

        //copy方法
        //copy(System.in, System.out);

        //nio channel
        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        copy(readableByteChannel, writableByteChannel);

    }

    private static void copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4*1024);

        while ( readableByteChannel.read(buffer) != -1){
            //复位： limit移到有效位
            buffer.flip();

            if( buffer.hasRemaining() ){
                writableByteChannel.write(buffer);
            }

            //清空buffer
            buffer.clear();
        }

    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[4*1024];

        int readLength = -1;

        while ( (readLength = in.read(buffer)) != -1 ){
            out.write(buffer,0, readLength);
        }

    }
}
