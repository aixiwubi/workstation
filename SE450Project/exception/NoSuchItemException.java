package exception;

public class NoSuchItemException extends Exception {
	public NoSuchItemException(){
		super();
	}
	public NoSuchItemException(String msg){
		super(msg);
	}
}
