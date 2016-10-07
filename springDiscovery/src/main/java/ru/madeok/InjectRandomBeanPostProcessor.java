package ru.madeok;

import java.lang.reflect.Field;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

public class InjectRandomBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			if(annotation!=null){
				int min = annotation.min();
				int max = annotation.max();
				field.setAccessible(true);
				Random random = new Random();
				ReflectionUtils.setField(field, bean, min + random.nextInt(max-min));
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

}
