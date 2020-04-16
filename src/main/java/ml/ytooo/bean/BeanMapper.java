//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ml.ytooo.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

@Component("beanMapper")
public class BeanMapper {
    @Resource
    private Mapper dozer;

    public BeanMapper() {
    }

    public <T> T map(Object source, Class<T> destinationClass) {
        return source == null ? null : this.dozer.map(source, destinationClass);
    }

    public <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List destinationList = new ArrayList();
        if (sourceList == null) {
            return destinationList;
        } else {
            Iterator i$ = sourceList.iterator();

            while(i$.hasNext()) {
                Object sourceObject = i$.next();
                Object destinationObject = this.dozer.map(sourceObject, destinationClass);
                destinationList.add(destinationObject);
            }

            return destinationList;
        }
    }

    public void copy(Object source, Object destinationObject) {
        this.dozer.map(source, destinationObject);
    }

}
