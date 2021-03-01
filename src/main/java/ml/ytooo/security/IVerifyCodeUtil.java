package ml.ytooo.security;

import ml.ytooo.security.bean.VerifyCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Random;

/**
 * 验证码生成工具
 */
@Service
public class IVerifyCodeUtil {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(IVerifyCodeUtil.class);

    /**
     * fontTypes
     */
    private static final String[] FONT_TYPES = {"\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66"};

    /**
     * VALICATE_CODE_LENGTH
     */
    private static final int VALICATE_CODE_LENGTH = 4;

    private static final String RANDOM_STRING_BASE = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789"; //不包括 0 o O 1 i I l

    private static String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomStringUtils.random(VALICATE_CODE_LENGTH, RANDOM_STRING_BASE);
        createCharacter(graphics, randomStr);
        graphics.dispose();
        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }

    /**
     * generate
     *
     * @param width
     * @param height
     * @return
     */
    public static VerifyCode generate(int width, int height) {
        VerifyCode verifyCode = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String code = generate(width, height, baos);
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
            verifyCode.setBase64("data:image/jpg;base64," + Base64.getEncoder().encodeToString(verifyCode.getImgBytes()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            verifyCode = null;
        }
        return verifyCode;
    }

    /**
     * fillBackground
     *
     * @param graphics
     * @param width
     * @param height
     */
    private static void fillBackground(Graphics graphics, int width, int height) {
        // 填充背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            graphics.setColor(randomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x, y, x1, y1);
        }
    }

    /**
     * createCharacter
     *
     * @param g
     * @param randomStr
     */
    private static void createCharacter(Graphics g, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            g.setColor(randomColor(50, 100));
            g.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(0, FONT_TYPES.length)], Font.BOLD, 26));
            g.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(0, 8));
        }
    }

    /**
     * 随机颜色
     *
     * @param fc
     * @param bc
     * @return
     * @author Youdmeng
     * Date 2019-08-05
     **/
    private static Color randomColor(int fc, int bc) {

        return new Color(fc + RandomUtils.nextInt(0, bc), fc + RandomUtils.nextInt(0, bc), fc + RandomUtils.nextInt(0, bc));

    }
}
