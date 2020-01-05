/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * 日志输出流
 */
public class LogOutputStream extends OutputStream {

    private Logger log;

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private ByteBuffer buf = ByteBuffer.allocate(1024);

    /**
     * 创建一个日志输出流
     *
     * @param clazz 被日志的类型
     */
    public LogOutputStream(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void write(int b) throws IOException {
        if (buf.hasRemaining()) {
            buf.putInt(b);
        } else {
            if (buf.hasArray()) {
                outputStream.write(buf.array());
            }
            buf.flip();
        }
    }

    @Override
    public void flush() throws IOException {
        if (buf.hasArray()) {
            outputStream.write(buf.array());
        }
        buf.flip();
        outputStream.flush();
        outputStream.close();
        log.error(outputStream.toString());
    }
}
