package ml.ytooo.freemarker.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author youdmeng
 * @date 2021/4/22 17:19
 */
public enum DataType {

    /**
     * json
     */
    Json,

    /**
     * xml
     */
    Xml,

    /**
     * object
     */
    Object;

    @JsonCreator
    public static DataType create(String name) {
        try {
            return DataType.valueOf(name);
        } catch (Exception e) {
            return Json;
        }
    }

}
