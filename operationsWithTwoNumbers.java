package lab_07;

import java.util.ArrayList;
import java.util.Iterator;

public class operationsWithTwoNumbers {
	private ExactNumber[] twoNumber;
	private byte[] finalNumber;
	private byte[] largeNumber;
	private byte[] smallNumber;
	
	public operationsWithTwoNumbers() {
		this.twoNumber = new ExactNumber[2];
	}
	
	public void add(int index, ExactNumber exactNumber) {
		this.twoNumber[index] = exactNumber;
	}
	
	public void addition() {
		int largeLength = Math.max(twoNumber[0].setLenght(), twoNumber[1].setLenght());
		int smallerLength = Math.min(twoNumber[0].setLenght(), twoNumber[1].setLenght());
		if(twoNumber[0].setLenght() == largeLength) {
			largeNumber = twoNumber[0].getMantissa();
			smallNumber = twoNumber[1].getMantissa();
		}else {
			largeNumber = twoNumber[1].getMantissa();
			smallNumber = twoNumber[0].getMantissa();
		}
		
		int ten = 0;
		this.finalNumber = new byte[largeLength];
		for (int i = largeLength; i >= smallerLength; i--) {
				int sumFigureNumber = (largeNumber[i]-48)+(smallNumber[smallerLength]-48)+ten;
				ten = 0;
				if(sumFigureNumber>9) {
					sumFigureNumber = sumFigureNumber-10;
				}
				finalNumber[i] = ((byte)sumFigureNumber);
				smallerLength--;
		}
	}
	
}
