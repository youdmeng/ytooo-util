//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ml.ytooo.bean;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.TypeMappingOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanMapper {

    private static volatile BeanMapper instance;

    private Mapper dozer;

    private BeanMapper() {
        dozer = DozerBeanMapperBuilder.buildDefault();
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
        List<T> destinationList = new ArrayList<T>();
        if (sourceList != null) {
            sourceList.forEach(source -> destinationList.add(dozer.map(source, destinationClass)));
        }
        return destinationList;
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
        final Mapper dozerIgonrenull = DozerBeanMapperBuilder.create().withMappingBuilder(beanMappingBuilder).build();
        dozerIgonrenull.map(source, destinationObject);

    }
}
