package ml.ytooo.file;

import lombok.extern.slf4j.Slf4j;
import ml.ytooo.exception.ServiceException;

import java.io.File;
import java.nio.file.*;


/**
 * @author you.minda
 * @date 2021/7/15 17:45
 */
@Slf4j
public class FileUtil {
    public static void deleteFile(String path) {

        try {
            if (Files.exists(Paths.get(path))) {
                if (Files.isDirectory(Paths.get(path))) {
                    for (File file : new File(path).listFiles()) {
                        deleteFile(file.getPath());
                    }
                }
                Files.delete(Paths.get(path));
            }
        } catch (Exception e) {
            log.error("无法删除的路径 :" + path);
            throw new ServiceException("无法删除的路径 :" + path);
        }
    }
}
