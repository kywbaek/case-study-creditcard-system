package resource;

public class NoResultException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoResultException() {
	}
	
	public NoResultException(String msg) {
		super(msg);
	}
}
