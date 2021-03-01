/**
 *
 */
package ml.ytooo.file;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 *
 * @author wangxulu
 * @date 2013-7-25 
 * @version 1.0
 */
public class ZipUtil {
    static final int BUFFER = 8192;

    private static File zipFile;

    public ZipUtil(String pathName) {
        zipFile = new File(pathName);
    }

    public static void compress(String srcPathName, String zipPath) {
        File file = new File(srcPathName);
        zipFile = new File(zipPath);
        if (!file.exists()) {
            throw new RuntimeException(srcPathName + "不存在！");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
                    new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compress(file, out, basedir);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void compress(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            System.out.println("压缩：" + basedir + file.getName());
            compressDirectory(file, out, basedir);
        } else {
            System.out.println("压缩：" + basedir + file.getName());
            compressFile(file, out, basedir);
        }
    }

    /** 压缩一个目录 */
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".zip")) {
                continue;
            }
            /* 递归 */
            compress(file, out, basedir);
        }
    }

    /** 压缩一个文件 */
    private static void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte[] data = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解压缩 
     */
    public static void deCompress(String sourceFile, String destDir) throws Exception {
        //保证文件夹路径最后是"/"或者"\"  
        char lastChar = destDir.charAt(destDir.length() - 1);
        if (lastChar != '/' && lastChar != '\\') {
            destDir += File.separator;
        }
        //根据类型，进行相应的解压缩  
        String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
        if ("zip".equals(type)) {
            ZipUtil.unzip(sourceFile, destDir);
        } else {
            throw new Exception("只支持zip");
        }
    }

    /**
     * 解压zip格式压缩包 
     * 对应的是ant.jar 
     */
    private static void unzip(String sourceZip, String destDir) {
        Project p = new Project();
        Expand e = new Expand();
        e.setProject(p);
        e.setSrc(new File(sourceZip));
        e.setOverwrite(false);
        e.setDest(new File(destDir));
        e.execute();
    }

}
