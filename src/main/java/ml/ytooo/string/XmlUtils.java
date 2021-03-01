package ml.ytooo.string;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtils {
    /**
     * 将xml实例化为对象
     * @param xml String型的xml
     * @param types Class数组
     * @return 对象
     */
    public static Object fromXml(String xml, Class[] types){
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(types);
        return xstream.fromXML(xml);
    }

    /**
     * 将对象序列化为xml
     * @param obj 实体对象
     * @param types Class数组
     * @return String型的xml
     */
    public static String toXml(Object obj, Class[] types){
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(types);
        return xstream.toXML(obj);
    }
}
