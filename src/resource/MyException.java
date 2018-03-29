package resource;

public class MyException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException() {
	}
	
	public MyException(String msg) {
		super(msg);
	}
}
