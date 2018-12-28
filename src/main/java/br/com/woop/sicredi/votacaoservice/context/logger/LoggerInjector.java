package br.com.woop.sicredi.votacaoservice.context.logger;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;


public class LoggerInjector implements BeanPostProcessor {

	/**
	 * Return the bean itself.
	 */
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		return bean;
	}

	/**
	 * For all beans before initialization, inject the logger using slf4j.
	 * 
	 * @param bean
	 * @param beanName
	 * @return returns same bean by injecting logger.
	 */
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				if(field.getAnnotation(Log.class) != null) {
					if(field.get(bean) == null) {
						final Logger logger = Logger.get(bean.getClass());
						field.set(bean, logger);
					}
				}
			}
		});
		return bean;
	}
}