/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.util;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.FileSystemException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public abstract class ZipUtils {

    public static final String ZIP_FORMAT_SUFFIX = ".zip";

    /**
     * 压缩指定文件到当前文件夹
     *
     * @param src 源文件名
     */
    public static void zip(String src) throws IOException {
        Objects.requireNonNull(src, "源文件参数不能为空");
        File srcFile = new File(src);
        if (!srcFile.exists()) {
            throw new FileNotFoundException("源文件不存在");
        }
        String zipName;
        String srcFileAbsolutePath = srcFile.getAbsolutePath();
        if (srcFile.isDirectory()) {
            zipName = srcFileAbsolutePath + ZIP_FORMAT_SUFFIX;
        } else {
            int dotIdx = srcFileAbsolutePath.lastIndexOf(".");
            if (dotIdx == -1) {
                zipName = srcFileAbsolutePath + ZIP_FORMAT_SUFFIX;
            } else {
                zipName = srcFileAbsolutePath.substring(0, dotIdx) + ZIP_FORMAT_SUFFIX;
            }
        }
        zip(srcFile, zipName);
    }

    /**
     * 压缩文件到指定文件
     *
     * @param src 源文件名
     * @param des 压缩文件名
     * @throws IOException
     */
    public static void zip(String src, String des) throws IOException {
        Objects.requireNonNull(src, "源文件参数不能为空");
        File srcFile = new File(src);
        if (!srcFile.exists()) {
            throw new FileNotFoundException("源文件不存在");
        }
        zip(srcFile, des);
    }

    /**
     * 压缩到指定文件
     *
     * @param src 源文件
     * @param des 压缩文件名
     * @throws IOException
     */
    public static void zip(File src, String des) throws IOException {
        Objects.requireNonNull(src, "源文件参数不能为空");
        Objects.requireNonNull(des, "压缩文件的名字不能为空");
        if (!des.endsWith(".zip")) {
            throw new IllegalArgumentException("压缩文件不是zip格式");
        }
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(des))) {
            String srcFilename = src.getName();
            String srcAbsolutePath = src.getAbsolutePath();
            if (src.isDirectory()) {
                recursiveFindFileAndPutEntry(src, zos, srcFilename, srcAbsolutePath);
            } else {
                putEntry(src, srcFilename, zos);
            }
            zos.flush();
        }
    }

    /**
     * 递归查找文件并将其作为ZipOutputStream对象的Entry对象
     *
     * @param currentDir          当前文件夹
     * @param zos                 目标ZIP文件的输出流
     * @param srcFilename         原文件的文件名，用于指定entry的路径
     * @param srcFileAbsolutePath 原文件的文件绝对路径，用于指定entry的路径
     * @throws IOException
     */
    private static void recursiveFindFileAndPutEntry(File currentDir, ZipOutputStream zos, String srcFilename, String srcFileAbsolutePath) throws IOException {
        @NotNull File[] files = currentDir.listFiles();
        for (File currentFile : files) {
            if (currentFile.isDirectory()) {
                recursiveFindFileAndPutEntry(currentFile, zos, srcFilename, srcFileAbsolutePath);
            } else {
                String entryName = srcFilename + currentFile.getAbsolutePath().replace(srcFileAbsolutePath, "");
                putEntry(currentFile, entryName, zos);
            }
        }
    }

    /**
     * 为ZIP文件添加一个ZipEntry
     *
     * @param src       待压缩文件
     * @param entryName ZipEntry对象文件名（压缩文件内的文件路径）
     * @param zos       Zip文件的输出流，添加任何文件即向该流添加新的ZipEntry
     * @throws IOException
     */
    private static void putEntry(File src, String entryName, ZipOutputStream zos) throws IOException {
        ZipEntry zipEntry = new ZipEntry(entryName);
        // TODO zipEntry conf param Object
        zos.putNextEntry(zipEntry);
        try (FileInputStream fis = new FileInputStream(src)) {
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
        }
        zos.flush();
        zos.closeEntry();
    }

    /**
     * 解压到当前文件夹
     *
     * @param zip zip文件
     * @throws IOException
     */
    public static void upZip(File zip) throws IOException {
        Objects.requireNonNull(zip, "源文件不能为空");
        String desDir = zip.getParent();
        unZip(zip, desDir);
    }

    /**
     * 解压文件到指定目录
     *
     * @param zip    zip文件
     * @param desDir 目标目录
     */
    public static void unZip(File zip, String desDir) throws IOException {
        Objects.requireNonNull(zip, "源文件不能为空");
        File desDirFile = new File(desDir);
        if (!desDirFile.exists()) {
            if (!desDirFile.mkdirs()) {
                throw new FileSystemException(desDir);
            }
        }
        desDir = desDir.replace("\\", "/");
        if (!desDir.endsWith("/")) {
            desDir = desDir + "/";
        }
        final String baseDir = desDir;
        ZipFile zipFile = new ZipFile(zip);
        zipFile.stream().forEach(entry -> {
            String entryName = baseDir + entry.getName();
            File file = new File(entryName);
            File dir = file.getParentFile();
            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("创建文件夹异常");
            }
            try (InputStream is = zipFile.getInputStream(entry);
                 FileOutputStream fos = new FileOutputStream(file)) {
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
