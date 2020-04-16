package ml.ytooo.string;

import ml.ytooo.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class IMGUtil {
    private static final String [] allowExtension = { ".GIF", ".PNG",".gif",".png" };
    private static Logger logger = LoggerFactory.getLogger(IMGUtil.class);

    public static MultipartFile storage (MultipartFile file) {

        if (null == file) {
            logger.info("上传了空文件");
            throw new ServiceException(1, null, "上传了空文件！");
        }
        boolean flag = false;
        for (String surrfix : allowExtension) {
            if (file.getOriginalFilename().endsWith(surrfix)) {
                flag = true;
            }
        }
        if (!flag) {
            logger.info("错的文件格式");
            throw new ServiceException(1, null, "错的文件格式！格式："
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        }
        return file;
    }
}
