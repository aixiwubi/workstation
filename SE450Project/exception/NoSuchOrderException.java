package exception;

public class NoSuchOrderException extends Exception {
	
		public NoSuchOrderException(){
			super();
		}
		public NoSuchOrderException(String msg){
			super(msg);
		}
	}

