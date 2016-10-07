package ru.madeok;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Transactional;


public class TransactionBPP implements BeanPostProcessor {
	private Map<String, Class> map = new HashMap<>();
	

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class beanClass = map.get(beanName);
		if(beanClass != null){
			return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("***TRANS OPEN***");
					Object retVal = method.invoke(bean, args);
					System.out.println("***TRANS CLOSE***");
					return retVal;
				}
			});
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = bean.getClass();
		if(beanClass.isAnnotationPresent(Transactional.class)){
			map.put(beanName, beanClass);
		}
		return bean;
	}

}
