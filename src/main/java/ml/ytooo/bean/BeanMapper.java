//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ml.ytooo.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public class BeanMapper {
    private static BeanMapper instance;

    private BeanMapper() {
    }

    public static BeanMapper getInstance() {
        if (null == instance) {
            synchronized (BeanMapper.class) {
                if (null == instance) {
                    instance = new BeanMapper();
                }
            }
        }
        return instance;
    }

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public <T> T map(Object source, Class<T> destinationClass) {
        return source == null ? null : dozer.map(source, destinationClass);
    }

    public <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List destinationList = new ArrayList();
        if (sourceList == null) {
            return destinationList;
        } else {
            Iterator iterator = sourceList.iterator();
            while (iterator.hasNext()) {
                Object sourceObject = iterator.next();
                Object destinationObject = dozer.map(sourceObject, destinationClass);
                destinationList.add(destinationObject);
            }
            return destinationList;
        }
    }

    public void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    public void copyIgnoreNull(Object source, Object destinationObject) {
        //Bean mapping
        BeanMappingBuilder beanMappingBuilder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(source.getClass(),
                        destinationObject.getClass(), TypeMappingOptions.mapNull(false));
            }
        };
        dozer.addMapping(beanMappingBuilder);
        dozer.map(source, destinationObject);
        dozer = new DozerBeanMapper();
    }
}
