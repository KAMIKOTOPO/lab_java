package lab_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class MyArrayList {
	private double[] array;
	private int realLength;

	public MyArrayList() {
		this.array = new double[1];
		realLength = 0;
	}

	public double get(int i) {
		return array[i];
	}
	
	public double[] getArray() {
		return array;
	}
	
	public void setArray(double[] array) {
		this.array = array;
	}
	public int getRealLength() {
		return realLength;
	}
	
	public void setRealLength(int realLength) {
		this.realLength = realLength;
	}
	
	private void grow() {
		double[] newArray = new double[array.length * 2];
		System.arraycopy(array, 0, newArray, array.length, array.length);
		array = newArray;
	}
	public void add(double number) {
		if (realLength != array.length) {
		grow();
		}
		array[realLength++] = number;
	}

	public void insert(int index, double number) {
		array[index] = number;
	}

	public void remove(int index) {
		int arrayLength = array.length;
		for (int i = index; i < arrayLength-1; i++) {
			array[i] = array[i+1];
		}
		realLength = arrayLength-1;
	}
	
	public int indexOf(double value) {
		int index = -1;
		for (int i = 0; i < realLength; i++) {
			if(array[i] == value) {
				index = i;
			}
		}
		return index;
	}

	public String toString() {
		StringBuilder stringArray = new StringBuilder("[");
		for (int i = 0; i < realLength; i++) {
			if (i == realLength - 1) {
				stringArray.append(array[i]);		
			}else {
				stringArray.append(array[i]);
				stringArray.append(", ");
			}
		}
		stringArray.append("]");
		return stringArray.toString();
	}
	
	public void findLongSeries() {
		int longSeries = 0;
		double minNum = Double.MAX_VALUE;
		double[] sereis = new double[1];
		for (int i = 0; i < realLength; i++) {
			if(array[i] < 0) {
				int count = 0;
				for (int j = 1; j <= array[i]; j++) {
					if(array[i]%2 == 0) {
						count++;
					}
				}
				if(count == 2) {
					longSeries++;
				}else {
				
				}
			}else {
				longSeries = 0;
			}
		}
	}
	
	public int size() {
		return realLength;
	}
}
