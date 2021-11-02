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

    private DozerBeanMapper dozer;
    private DozerBeanMapper dozerIgonrenull;

    private BeanMapper() {
        dozer = new DozerBeanMapper();
        dozerIgonrenull = new DozerBeanMapper();
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

    public <T> T map(Object source, Class<T> destinationClass) {
        return source == null ? null : dozer.map(source, destinationClass);
    }

    public <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List destinationList = new ArrayList();
        if (sourceList == null) {
            return destinationList;
        } else {
            sourceList.forEach(source -> destinationList.add(dozer.map(source, destinationClass)));
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
        synchronized (dozerIgonrenull){
            dozerIgonrenull.addMapping(beanMappingBuilder);
            dozerIgonrenull.map(source, destinationObject);
            dozerIgonrenull = new DozerBeanMapper();
        }
    }
}
