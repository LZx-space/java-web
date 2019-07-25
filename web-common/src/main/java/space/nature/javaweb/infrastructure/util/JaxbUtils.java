package space.nature.javaweb.infrastructure.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JAXB工具类，用于类对象与XML之间的转换
 */
public abstract class JaxbUtils {

    /**
     * 如果该工具类用于处理请求的某些步骤，由于软引用在服务器内存压力大的时候将被回收，此时反过来将提高请求处理的耗时约20ms
     */
    private static final Map<Class<?>, SoftReference<JAXBContext>> contextMap = new HashMap<>(16);

    private static final Lock lock = new ReentrantLock();

    /**
     * 转换为字符串
     *
     * @param obj      绑定的类对象
     * @param encoding 输出的编码方式
     * @param fragment 是否包含XML头片段
     * @return
     * @throws JAXBException
     */
    public static String toXml(Object obj, String encoding, boolean fragment) throws JAXBException {
        StringWriter sw = new StringWriter();
        marshaller(obj.getClass(), encoding, fragment).marshal(obj, sw);
        return sw.toString();
    }

    /**
     * 转换为对象
     *
     * @param xml   XML字符串
     * @param clazz 绑定的类型
     * @param <T>   具体类型
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String xml, Class<T> clazz) throws JAXBException {
        StringReader sr = new StringReader(xml);
        return (T) unmarshaller(clazz).unmarshal(sr);
    }

    /**
     * 从缓存获取JAXBContext对象，如缓存中不存在则更新缓存
     *
     * @param clazz 绑定的类型
     * @return
     * @throws JAXBException
     */
    private static JAXBContext getJaxbContext(Class<?> clazz) throws JAXBException {
        SoftReference<JAXBContext> ref = contextMap.get(clazz);
        if (ref == null) {
            return createAndCacheJaxbContext(clazz);
        }
        if (ref.get() == null) {
            return createAndCacheJaxbContext(clazz);
        }
        return ref.get();
    }

    /**
     * 创建并缓存JAXBContext对象
     *
     * @param clazz 绑定的类型
     * @return
     * @throws JAXBException
     */
    private static JAXBContext createAndCacheJaxbContext(Class<?> clazz) throws JAXBException {
        lock.lock();
        try {
            SoftReference<JAXBContext> reference = contextMap.get(clazz);
            JAXBContext context;
            if (reference == null || (context = reference.get()) == null) {
                context = JAXBContext.newInstance(clazz);
                contextMap.put(clazz, new SoftReference<>(context));
            }
            return context;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取编排对象
     *
     * @param clazz    绑定的类型
     * @param encoding 输出字符串编码类型
     * @param fragment 是否包含XML头部信息
     * @return
     * @throws JAXBException
     */
    private static Marshaller marshaller(Class<?> clazz, String encoding, boolean fragment) throws JAXBException {
        Marshaller marshaller = getJaxbContext(clazz).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);
        return marshaller;
    }

    /**
     * 获取反编排对象
     *
     * @param clazz 绑定的类型
     * @return
     * @throws JAXBException
     */
    private static Unmarshaller unmarshaller(Class<?> clazz) throws JAXBException {
        return getJaxbContext(clazz).createUnmarshaller();
    }

}
