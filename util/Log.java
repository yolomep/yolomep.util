package util;

public class Log {
	int base, argument;
	
	public Log(int b, int a) {
		base = b;
		argument = a;
		
	}
	
	/*
	public Log multiply(Log other) {
		
	}
	*/
	
	public Log add(Log other) throws UndefinedLogarithmException {
		if(other.base == base) {
			return new Log(other.argument * argument, base);
		}
		throw new UndefinedLogarithmException();
	}
	
	public Log subtract(Log other) throws UndefinedLogarithmException {
		if(other.base == base) {
			return new Log(other.argument / argument, base);
		}
		throw new UndefinedLogarithmException();
	}
	
	public double eval() {
		return MathEx.logBase(argument, base);
	}
	
	@Override
	public String toString() {
		//find perfect power
		int power = MathEx.isPower(argument);
		if(power > 1) {
			int first = (int) (MathEx.logBase(argument, power) + 0.0001000001);
			return "" + first + "log_" + base + "(" + power + ")";
		}
		return "log_" + base + "(" + argument + ")";
	}
}

class UndefinedLogarithmException extends Exception {

	private static final long serialVersionUID = 1L;
	public UndefinedLogarithmException() {

	}
	public UndefinedLogarithmException(String errorMessage) {
        super(errorMessage);
    }
}

