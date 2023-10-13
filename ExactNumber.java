package lab_07;

import java.util.Arrays;

class ExactNumber {
	private byte[] mantissa;
	private int order;
	private int signMantissa;
	private int signOrder;
	
	public void readNumber() {
	}
	
	public void setFigureNumber(int i, byte figure){
		this.mantissa[i] = figure;
	}
	
	public byte getFigureNumber(int i) {
		return mantissa[i];
	}
	
	public void setSignMantissa(int sign) {
		this.signMantissa = sign;
	}
	
	public byte[] getMantissa() {
		return mantissa;
	}
	
	public int getSignMantissa() {
		return signMantissa;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public void creatArrayMantissa(int lenght) {
		this.mantissa = new byte[lenght];
	}
	
	public void getArray() {
		System.out.println(Arrays.toString(mantissa));
	}
	
	public void getFirstFigareMantissa() {
		System.out.println();
	}
	
	public int setLenght() {
		return mantissa.length;
	}
}
