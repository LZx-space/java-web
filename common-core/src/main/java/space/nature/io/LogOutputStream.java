/*
 * Copyright (c) 2019, LZx
 */

package space.nature.io;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * 日志输出流
 */
@Slf4j
public class LogOutputStream extends OutputStream {

    private String logName;

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private ByteBuffer buf = ByteBuffer.allocate(1024);

    /**
     * 创建一个日志输出流
     *
     * @param logName 日志标记
     */
    public LogOutputStream(String logName) {
        this.logName = logName;
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
        log.error(logName + ": " + outputStream.toString());
    }
}
