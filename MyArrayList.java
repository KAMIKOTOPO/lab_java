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
		this.realLength = array.length;
		realLength = 0;
	}

	public double get(int i) {
		return array[i];
	}
	public double[] createNewArray(int lengthArray) {
		double[] newArray = new double[lengthArray * 2];
		for (int i = 0; i < lengthArray; i++) {
			System.arraycopy(array, i, newArray, i, 1);
		}
		return newArray;
	}
	public void add(double number) {
		int arrayLength = array.length;
		if (realLength != arrayLength) {
			array[realLength] = number;
		} else {
			double[] newArray = createNewArray(arrayLength);
			newArray[realLength] = number;
			array = newArray;
		}
		realLength++;
	}

	public void insert(int index, double number) {
		int arrayLength = array.length;
		for (int i = 0; i < arrayLength; i++) {
			if (i == index) {
				array[i] = number;
			}
		}
	}

	public void remove(int index) {
		int arrayLength = array.length;
		for (int i = index; i < arrayLength-1; i++) {
			array[i] = array[i+1];
		}
		realLength = arrayLength-1;
	}

	public void creatRandomArray(int arraySize) {
		double[] newArray = new double[arraySize];
		for (int i = 0; i < arraySize; i++) {
			newArray[i] = Math.round(Math.random() * 10);
		}
		array = newArray;
		realLength = arraySize;
	}
	
	public int indexOf(double value) {
		int index = -1;
		for (int i = 0; i < realLength; i++) {
			if(array[i] == value) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public void swapLastEvenAndMinimumPositiveNumbers() {
		int arrayLength = array.length;
		int indexEvenLastNumber = -1;
		double minPositiv = Integer.MAX_VALUE;
		int indexMinPositiv = -1;
		for (int i = 0; i < arrayLength; i++) {
			if(array[i]%2==0) {
				indexEvenLastNumber = i;
			}
			if(array[i]<minPositiv && array[i]>0) {
				minPositiv = array[i];
				indexMinPositiv = i;
			}
		}
		if(indexEvenLastNumber == -1) {
			System.out.println("Нет четных чисел в массиве");
		}
		else if(indexMinPositiv == -1) {
			System.out.println("Нет положительных чисел в массиве");
		}else {
			double[] newArray = createNewArray(arrayLength);
			double step = newArray[indexEvenLastNumber];
			newArray[indexEvenLastNumber] = newArray[indexMinPositiv];
			newArray[indexMinPositiv] = step;
			array = newArray;
			System.out.println("Значения поменялись местами");
		}
	}
	
	public int findIndexExtremum() {
		int indexExtremum = -1;
		for (int i = 1; i < array.length - 1; i++) {
			if ((array[i] > array[i - 1] && array[i] > array[i + 1])
					|| (array[i] < array[i - 1] && array[i] < array[i + 1])) {
				indexExtremum = i;
			}
		}
		return indexExtremum;
	}
	
	public void saveArrayInFile(MyArrayList myArrayList) {
		try (FileWriter fw = new FileWriter("C:\\Users\\danii\\eclipse-workspace\\lab_java\\src\\lab_06\\arraysFile.txt", false)) {
			BufferedWriter bf = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bf);
			for (int i = 0; i < array.length; i++) {
				out.print(String.format("%.1f\n",array[i]));
			}
			 out.flush();
	         out.close();
		} catch (IOException e) {
			System.out.println("Возникла ошибка во время записи, проверьте данные.");
		}
	}

	public void readArrayFromFile() {
		try(FileReader fileReader = new FileReader("C:\\Users\\danii\\eclipse-workspace\\lab_java\\src\\lab_06\\arraysFile.txt")) {
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		int count = 0;
        while ((line = br.readLine()) != null) {
        	add(Double.parseDouble(line.replaceAll(",", ".")));
        	count++;
        }
		realLength = count;
 		}catch(IOException e){
			System.out.println("Возникла ошибка во время записи, проверьте данные.");
		}catch (java.lang.NumberFormatException e) {
			System.out.println("Возникла ошибка во время записи, проверьте данные.");
		}
	}

	public String toString() {
		if (realLength == 0) {
			return "В массиве нет данных\n";
		} else {
			StringBuilder stringArray = new StringBuilder("[");
			for (int i = 0; i < realLength; i++) {
				if (i != realLength - 1) {
					stringArray.append(array[i]);
					stringArray.append(", ");
				}
			}
			stringArray.append(array[realLength - 1]);
			stringArray.append("]");
			return stringArray.toString();
		}
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
