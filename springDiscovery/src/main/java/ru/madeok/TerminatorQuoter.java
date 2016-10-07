package ru.madeok;



@Transactional
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {
	@InjectRandomInt(min = 4, max = 7)
	private int repeat;
	
	private String message;
	
	

	public TerminatorQuoter() {
		//System.out.println("отработал конструктор ---старт");
		sayQuote();
		//System.out.println("отработал конструктор ---стоп");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void sayQuote() {
		for (int i = 0; i < repeat; i++) {
			System.out.println("message" + message);	
		}	
	}
	
}
