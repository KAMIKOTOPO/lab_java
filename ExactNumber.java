package lab_07;

import java.util.Arrays;


class ExactNumber {
	private byte[] mantissa;
	private int order;
	private int signMantissa;
	private int numberLength;
	private final int numberZeroInBytes = 48;

	public ExactNumber() {
		this.mantissa = new byte[30];
	}

	public boolean checkOrder() {
		if (-5 > order && 5 < order) {
			System.out.println("Порядок числа меньше либо больше допустимо");
			return false;
		} else {
			return true;
		}
	}

	public void addNumber(String stringNumber) {
		order = 0;
		numberLength = 0;
		char[] charNumber = stringNumber.toCharArray();
		int mantissaLength = charNumber.length;
		int index = 0;
		if (charNumber[0] == '-') {
			signMantissa = -1;
		} else if (charNumber[0] == '+' || Character.isDigit(charNumber[0])) {
			signMantissa = 1;
		}
		if (stringNumber.contains(".")) {
			int indexPoint = stringNumber.indexOf(".");
			if (Integer.parseInt(stringNumber.substring(0, indexPoint)) == 0) {
				order = -1;
				for (int i = indexPoint + 1; i < mantissaLength; i++) {
					if (charNumber[i] != '0') {
						break;
					}
					order--;
				}
			} else {
				for (int i = 0; i < charNumber.length; i++) {
					if (charNumber[i] == '.') {
						break;
					} else if (Character.isDigit(charNumber[i])) {
						order++;
					}
				}
			}
		}
		if (stringNumber.contains("e")) {
			mantissaLength = stringNumber.indexOf("e");
			if (mantissaLength < charNumber.length) {
				order += Integer.parseInt(stringNumber.substring(mantissaLength + 1, charNumber.length));
			}
		}
		for (int i = 0; i < mantissaLength; i++) {
			if (Character.isDigit(charNumber[i])) {
				mantissa[index] = (byte) charNumber[i];
				index++;
				numberLength++;
			}
		}
	}

	private byte getElementArrayMantisa(int i) {
		return mantissa[i];
	}

	public void addition(ExactNumber secondExactNumber) {
		int differentOrder = Math.abs(order - secondExactNumber.order);
		int ten = 0;
		for (int i = mantissa.length - 1; i >= 0; i--) {
			byte sum = 0;
			if (i >= differentOrder) {
				if (order < secondExactNumber.order) {
					sum = (byte) (signMantissa * mantissa[i - differentOrder] + (secondExactNumber.signMantissa * secondExactNumber.mantissa[i] + ten));
				} else if (order > secondExactNumber.order) {
					sum = (byte) (signMantissa*mantissa[i] + (secondExactNumber.signMantissa *secondExactNumber.mantissa[i - differentOrder] + ten));
				} else if (order == secondExactNumber.order) {
					sum = (byte) (signMantissa *mantissa[i] + (secondExactNumber.signMantissa *secondExactNumber.mantissa[i] + ten));
				}
				if (sum >= 110) {
					ten++;
				}
				secondExactNumber.mantissa[i] = sum;
			} else {
				if (order < secondExactNumber.order) {
					secondExactNumber.mantissa[i] = (byte) (secondExactNumber.mantissa[i] + ten);
				} else if (order > secondExactNumber.order) {
					secondExactNumber.mantissa[i] = (byte) (mantissa[i] + ten);
				}
			}
			ten = 0;
		}
		secondExactNumber.order = Math.max(order, secondExactNumber.order);
	}
	
	public void subtraction(ExactNumber secondExactNumber) {
		int differentOrder = Math.abs(order - secondExactNumber.order);
		int minusOrder = 0;
		for (int i = mantissa.length - 1; i >= 0; i--) {
			int sum = 0;
			if (i >= differentOrder) {
				if (order < secondExactNumber.order) {
					int firstNumber = (signMantissa* (mantissa[i - differentOrder]-numberZeroInBytes));
					int secondNumber = (secondExactNumber.signMantissa * (secondExactNumber.mantissa[i]-numberZeroInBytes));
					if((signMantissa == 1 && secondExactNumber.signMantissa == 1&& mantissa[i]<secondExactNumber.mantissa[i])) {
						firstNumber += 10;
					}
					if(signMantissa == -1 && secondExactNumber.signMantissa == -1&& mantissa[i]>secondExactNumber.mantissa[i]){
						secondNumber += 10;
					}
					sum = firstNumber - secondNumber;
				} else if (order > secondExactNumber.order) {
					sum = (signMantissa * (mantissa[i]-numberZeroInBytes) ) -(secondExactNumber.signMantissa * (secondExactNumber.mantissa[i - differentOrder]-numberZeroInBytes));
				} else if (order == secondExactNumber.order) {
					sum = (signMantissa*(mantissa[i ]-numberZeroInBytes)) - (secondExactNumber.signMantissa * (secondExactNumber.mantissa[i]-numberZeroInBytes));
				}
				System.out.println(sum);
				if(sum > 10 ) {
					sum--;
					mantissa[i-differentOrder-1]++;
				}
				secondExactNumber.mantissa[i] = (byte) ((char)(sum)+'0');
			} else {
				if (order < secondExactNumber.order) {
					secondExactNumber.mantissa[i] = (byte) (secondExactNumber.mantissa[i]-numberZeroInBytes);
				} else if (order > secondExactNumber.order) {
					secondExactNumber.mantissa[i] = (byte) (mantissa[i]-numberZeroInBytes);
				}
			}
		}
		secondExactNumber.order = Math.max(order, secondExactNumber.order)+minusOrder;
	}
	
	public void multiply(ExactNumber secondExactNumber){
		byte[] middleValue = new byte[30]; 
		for (int i = mantissa.length; i >= 0 ; i--) {
			for (int j = mantissa.length; j >= 0; j--) {
				int productNumber = (mantissa[i]-numberZeroInBytes)*(secondExactNumber.mantissa[j]-numberZeroInBytes);
				if(productNumber < 10 ) {
					middleValue[i] =(byte) ((char)productNumber+'0');
				}else if(productNumber >= 10) {
					
				}
			}
		}
	}
	
	public String toString() {
		String number = signMantissa + Arrays.toString(mantissa) + "e" + order;
		return number;
	}
}
