package util;

import java.math.*;
public class Decimal extends BigDecimal {

	private static final long serialVersionUID = 1L;
	public static final MathContext DEFAULT_CONTEXT = new MathContext(120, RoundingMode.HALF_UP);
    
	
    public Decimal(BigInteger val) {
		super(val, DEFAULT_CONTEXT);
	}
    
    public Decimal(String s) {
    	super(s, DEFAULT_CONTEXT);
    }
    
    public Decimal(long l) {
    	super(l, DEFAULT_CONTEXT);
    }
    
    public Decimal(int i) {
    	super(i, DEFAULT_CONTEXT);	
    }
    
    public Decimal(double d) {
    	super(d, DEFAULT_CONTEXT);
    }

    public Decimal(BigDecimal bd) {
        this(bd.toString());
    }
    
    public Decimal div(BigDecimal divisor) {
        return new Decimal(super.divide(divisor, DEFAULT_CONTEXT).stripTrailingZeros());
    }


    public Decimal divToIntegralValue(BigDecimal divisor) {
        return new Decimal(super.divideToIntegralValue(divisor));
    }

    public Decimal divToIntegralValue(BigDecimal divisor, MathContext mc) {
        return new Decimal(super.divideToIntegralValue(divisor, mc));
    }

        
    public Decimal mod(BigDecimal divisor) {
        return new Decimal(super.remainder(divisor));
    }

    
    public Decimal mod(BigDecimal divisor, MathContext mc) {
        return new Decimal(super.remainder(divisor, mc));
    }

    
    public Decimal pow(int n) {
        return new Decimal(super.pow(n));
    }

    
    public Decimal pow(int n, MathContext mc) {
        return new Decimal(super.pow(n, mc));
    }

    
    public Decimal abs() {
        return new Decimal(super.abs());
    }

    
    public Decimal abs(MathContext mc) {
        return new Decimal(super.abs(mc));
    }

    
    public Decimal negate() {
        return new Decimal(super.negate());
    }

    
    public Decimal negate(MathContext mc) {
        return new Decimal(super.negate(mc));
    }

    
    public Decimal plus() {
        return new Decimal(super.plus());
    }

    
    public Decimal plus(MathContext mc) {
        return new Decimal(super.plus(mc));
    }

    
    public Decimal round(MathContext mc) {
        return new Decimal(super.round(mc));
    }

    
    public Decimal setScale(int newScale, RoundingMode roundingMode) {
        return new Decimal(super.setScale(newScale, roundingMode));
    }

    
    public Decimal setScale(int newScale) {
        return new Decimal(super.setScale(newScale));
    }

    
    public Decimal movePointLeft(int n) {
        return new Decimal(super.movePointLeft(n));
    }

    
    public Decimal movePointRight(int n) {
        return new Decimal(super.movePointRight(n));
    }

    
    public Decimal scaleByPowerOfTen(int n) {
        return new Decimal(super.scaleByPowerOfTen(n));
    }

    
    public Decimal stripTrailingZeros() {
        return new Decimal(super.stripTrailingZeros());
    }

    
    public Decimal min(BigDecimal val) {
        return new Decimal(super.min(val));
    }

    
    public Decimal max(BigDecimal val) {
        return new Decimal(super.max(val));
    }

    
    public Decimal ulp() {
        return new Decimal(super.ulp());
    }

    
    public Decimal add(BigDecimal augend, MathContext mc) {
        return new Decimal(super.add(augend, mc));
    }

    
    public Decimal sub(BigDecimal subtrahend) {
        return new Decimal(super.subtract(subtrahend));
    }

    
    public Decimal sub(BigDecimal subtrahend, MathContext mc) {
        return new Decimal(super.subtract(subtrahend, mc));
    }

    
    public Decimal mul(BigDecimal multiplicand) {
        return new Decimal(super.multiply(multiplicand));
    }

    
    public Decimal mul(BigDecimal multiplicand, MathContext mc) {
        return new Decimal(super.multiply(multiplicand, mc));
    }

    
    public Decimal div(BigDecimal divisor, int scale, RoundingMode roundingMode) {
        return new Decimal(super.divide(divisor, scale, roundingMode));
    }

    
    public Decimal div(BigDecimal divisor, RoundingMode roundingMode) {
        return new Decimal(super.divide(divisor, roundingMode));
    }

    
    public Decimal add(BigDecimal augend) {
        return new Decimal(super.add(augend));
    }

    

}