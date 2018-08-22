package com.megagao.production.ssm.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * Bean属性拷贝
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年4月7日 下午3:28:39,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年4月7日 下午3:28:39
 * @since
 * @version
 */
public class BeanUtils {

	public static class CopyPropertiesConfig {

		private boolean ignoreNull;

		private String[] ignoreProperties;

		public String[] getIgnoreProperties() {
			return ignoreProperties;
		}

		public boolean isIgnoreNull() {
			return ignoreNull;
		}

		public void setIgnoreNull(boolean ignoreNull) {
			this.ignoreNull = ignoreNull;
		}

		public void setIgnoreProperties(String[] ignoreProperties) {
			this.ignoreProperties = ignoreProperties;
		}

	}

	static class ImmutableCopyPropertiesConfig extends CopyPropertiesConfig {

		public void setIgnoreNull(boolean ignoreNull) {
			throw new UnsupportedOperationException();
		}

	}

	public static final CopyPropertiesConfig COPY_IGNORE_NULL;

	static final CopyPropertiesConfig COPY_DEFAULT;

	static final ConcurrentMap<Class<?>, Map<String, PropertyDescriptor>> descriptorCache = new ConcurrentHashMap<Class<?>, Map<String, PropertyDescriptor>>();

	static final ConcurrentMap<Class<?>, PropertyDescriptor[]> descriptorsCache = new ConcurrentHashMap<Class<?>, PropertyDescriptor[]>();

	static {
		COPY_DEFAULT = new ImmutableCopyPropertiesConfig();

		COPY_IGNORE_NULL = new ImmutableCopyPropertiesConfig();
		COPY_IGNORE_NULL.ignoreNull = true;
	}

	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, COPY_DEFAULT);
	}

	public static void copyProperties(Object source, Object target,
			CopyPropertiesConfig config) {
		if (source == null)
			throw new IllegalArgumentException("源对象不能为null");
		if (target == null)
			throw new IllegalArgumentException("目标对象不能为null");

		PropertyDescriptor[] targetDescriptors = getPropertyDescriptors(target
				.getClass());

		Class<?> sourceClass = source.getClass();
		L: for (PropertyDescriptor targetDescriptor : targetDescriptors) {
			if (targetDescriptor.getWriteMethod() == null)
				continue;

			final String name = targetDescriptor.getName();

			if (config.getIgnoreProperties() != null) {
				for (String ignoreProperty : config.getIgnoreProperties()) {
					if (name.equals(ignoreProperty))
						continue L;
				}
			}

			PropertyDescriptor sourceDescriptor = getPropertyDescriptor(
					sourceClass, name);
			if (sourceDescriptor != null
					&& sourceDescriptor.getReadMethod() != null) {
				try {
					Method readMethod = sourceDescriptor.getReadMethod();
					readMethod.setAccessible(true);
					Object value = readMethod.invoke(source);

					if (value == null && config.isIgnoreNull())
						continue;

					Method writeMethod = targetDescriptor.getWriteMethod();
					writeMethod.setAccessible(true);
					writeMethod.invoke(target, value);
				} catch (Exception e) {
					throw new RuntimeException("复制Bean属性时出错,属性值为" + name, e);
				}
			}
		}
	}

	private static PropertyDescriptor getPropertyDescriptor(Class<?> beanClass,
			String propertyName) {
		Map<String, PropertyDescriptor> descriptorMap = descriptorCache
				.get(beanClass);
		if (descriptorMap == null) {
			descriptorMap = new HashMap<String, PropertyDescriptor>();
			PropertyDescriptor[] descriptors = getPropertyDescriptors(beanClass);
			for (PropertyDescriptor descriptor : descriptors) {
				descriptorMap.put(descriptor.getName(), descriptor);
			}
			descriptorCache.put(beanClass, descriptorMap);
		}
		return descriptorMap.get(propertyName);
	}

	private static PropertyDescriptor[] getPropertyDescriptors(
			Class<?> beanClass) {
		PropertyDescriptor[] descriptors = descriptorsCache.get(beanClass);
		if (descriptors == null) {
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
				descriptors = beanInfo.getPropertyDescriptors();
				descriptorsCache.put(beanClass, descriptors);
			} catch (IntrospectionException e) {
				throw new RuntimeException("获取Bean信息时出错", e);
			}
		}
		return descriptors;
	}

}
