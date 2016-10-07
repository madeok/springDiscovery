package ru.madeok;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TerminatorQuoterTest {
 
	@Test
	public void testSayQuote() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		//При этой строке не найдет: context.getBean(TerminatorQuoter.class).sayQuote();
		//org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [ru.madeok.TerminatorQuoter] is defined
		//Так как postProcessAfterInitialization возвращает прокси
		
		context.getBean(Quoter.class).sayQuote();

	}
}
