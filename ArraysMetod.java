package lab_06;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import lab_05.CalculatingSumSeries.Checker;

public class ArraysMetod {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArraysList arraysList = new ArraysList();
		while (true) {
			try {
				System.out.println("""
						1) Задать размер списка и заполнить его случайными числами /
						2) Сохранить список в текстовый файл
						3) Считать список из текстового файла
						4) Вывести список /
						5) Добавить число /
						6) Удалить число /
						7) Найти число /
						8) Найти значение K-го экстремума в списке. /
						9) Найти наиболее длинную непрерывную возрастающую
						последовательность отрицательных чисел, модуль которых является
						простым числом.
						10) Поменять местами последний чётный и минимальный положительный. /
						11) Завершить работу программы. /
											""");
				System.out.print("Выберите одну из предложенных операций: ");
				int x = scanner.nextInt();
				switch (x) {
				case 1:
					int sizeArray = (int)inputDouble("Введите размер массива: ");
					arraysList.creatRandomArrays(sizeArray);
					break;
				case 2:
					arraysList.saveArraysInFile();
					System.out.println("Массив сохранен в файл");
					break;
				case 4:
					System.out.println(arraysList.toString());
					break;
				case 5:
					menuAdd(arraysList);
					break;
				case 6:
					int index = (int) checkIndex("Введите индекс числа, которое будет удалено: ", arraysList);
					arraysList.remove(index);
					break;
				case 7:
					double value = inputDouble("Введите значение индекс которого вы хотите найти: ");
					System.out.println(arraysList.indexOf(value));
					break;
				case 8:
					System.out.println(arraysList.extremum());
					break;
				case 11: 
					System.out.println("Программа завершила работу...");
					return;
				case 10:
					arraysList.changeEvenLastAndMinimumPositivNumbers();
					break;
				default:
					System.out.println("\nТакой команды нет\n");
					break;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("\nВы ввели некорректные данные\n");
			}
		}
	}
	
	public static void menuAdd(ArraysList arraysList) {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("""
					1)Добавить число в конец списка
					2)Добавить число по заданому индексу
					""");
			int x = (int) inputDouble("Выберите одну из предложенных операций: ");
			switch (x) {
			case 1:
				double number1 = inputDouble("Введите число которое хотите добавить в конец списка: ");
				arraysList.add(number1);
				return;
			case 2:
				int index = (int) checkIndex("Введите номер индекс по которому хотите добавить число: ", arraysList);
				double number2 = inputDouble("Введите число которое хотите добавить: ");
				arraysList.insert(index, number2);
				return;
			default:
				System.out.println("\nТакой команды нет\n");
			}
		}
	}

	public interface Checker {
		boolean check(double val);
	}

	public static double inputDouble(String prompt, Checker checker, String errMsg) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.print(prompt);
				double x = scanner.nextDouble();
				if (checker.check(x)) {
					return x;
				}
				System.out.println(errMsg);
			} catch (java.util.InputMismatchException e) {
				System.out.println("Вы ввели некорректные данные");
				scanner.nextLine();
			}
		}
	}

	public static double checkNegativ(String prompt) {
		String errMsg = "Для этого значения не может использоваться отрицательное или равное нулю число";
		return inputDouble(prompt, x -> x > 0, errMsg);
	}

	public static double inputDouble(String prompt) {
		return inputDouble(prompt, x -> true, "");
	}
	public static double checkIndex(String prompt, ArraysList arraysList) {
		String errMsg = "Нет такого индекса";
		return inputDouble(prompt, x -> x > 0 && x < arraysList.size(), errMsg);
	}
}

class ArraysList {
	private double[] array;
	private int realLength;

	public ArraysList() {
		this.array = new double[1];
		this.realLength = array.length;
	}

	public double getArray(int i) {
		return array[i];
	}
	public double[] creatNewArrays(int lengthArray) {
		double[] newArray = new double[lengthArray * 2];
		for (int i = 0; i < lengthArray; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}
	public void add(double number) {
		int lengthArray = array.length;
		if (realLength != lengthArray) {
			array[realLength] = number;
		} else {
			double[] newArray = creatNewArrays(lengthArray);
			newArray[realLength] = number;
			array = newArray;
		}
		realLength++;
	}

	public void insert(int index, double number) {
		int lengthArray = array.length;
		for (int i = 0; i < lengthArray; i++) {
			if (i == index) {
				array[i] = number;
			}
		}
	}

	public void remove(int index) {
		int lengthArray = array.length;
		for (int i = index; i < lengthArray-1; i++) {
			double step = array[i];
			array[i] = array[i+1];
			array[i+1] = step;
		}
		realLength = lengthArray-1;
	}

	public void creatRandomArrays(int arraysSize) {
		double[] newArray = new double[arraysSize];
		for (int i = 0; i < arraysSize; i++) {
			newArray[i] = Math.round(Math.random() * 10);
		}
		array = newArray;
		realLength = arraysSize;
	}
	
	public String indexOf(double value) {
		int index = -1;
		StringBuilder findIndex = new StringBuilder();
		for (int i = 0; i < realLength; i++) {
			if(array[i] == value) {
				index = i;
				break;
			}
		}
		if(index == -1) {
			findIndex.append("Такого значения нет в массиве");
		}else {
			findIndex.append(String.format("Первое вхождение значение произошло под индексом - %d", index));
		}
		return findIndex.toString();
	}
	
	public void changeEvenLastAndMinimumPositivNumbers() {
		int lenghtArray = array.length;
		int indexEvenLastNumber = -1;
		double minPositiv = Integer.MAX_VALUE;
		int indexMinPositiv = -1;
		for (int i = 0; i < lenghtArray; i++) {
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
			double[] newArray = creatNewArrays(lenghtArray);
			double step = newArray[indexEvenLastNumber];
			newArray[indexEvenLastNumber] = newArray[indexMinPositiv];
			newArray[indexMinPositiv] = step;
			array = newArray;
			System.out.println("Значения поменялись местами");
		}
	}
	
	public String extremum() {
		double valueMinExtremum  = 0;
		int indexMinExtremum = -1;
		double valueMaxExtremum  = 0;
		int indexMaxExtremum = -1;
		StringBuilder strExtremum = new StringBuilder();
		for (int i = 1; i < array.length - 1; i++) {
			if (array[i] > array[i - 1] && array[i] > array[i + 1]) {
				valueMaxExtremum = array[i];
				indexMaxExtremum = i;
			} else if (array[i] < array[i - 1] && array[i] < array[i + 1]) {
				valueMinExtremum = array[i];
				indexMinExtremum = i;
			}
		}
		if (indexMaxExtremum != -1 && indexMinExtremum != -1) {
			strExtremum.append(
					String.format("Максимальный экстремум - %f, индекс - %d. Минимальный экстремум - %f, индекс - %d.",
							valueMaxExtremum, indexMaxExtremum, valueMinExtremum, indexMinExtremum));
		} else {
			if (indexMaxExtremum == -1) {
				strExtremum.append("Максимального экстремума нет");
			}else {
				strExtremum.append(String.format("Максимальный экстремум - %f, индекс - %d.", valueMaxExtremum, indexMaxExtremum));
			}
			if (indexMinExtremum == -1) {
				strExtremum.append("Минимального экстремума нет");
			}else {
				strExtremum.append(String.format("Минимальный экстремум - %f, индекс - %d.", valueMinExtremum, indexMinExtremum));
			}
		}
		return strExtremum.toString();
	}
	
	public void saveArraysInFile() {
		try {
			   File file = new File("arraysFile.txt");
			   PrintWriter pw = new PrintWriter(file);
			   for (int i = 0; i < array.length; i++) {
				   if(i == realLength - 1) {
				   pw.print(array[i]);
				   }else {
					   pw.print(array[i]);
					   pw.print(", ");
				   }
			   }
			   pw.close();

			} catch (IOException e) {
			   System.out.println("Возникла ошибка во время записи, проверьте данные.");
			}
	}
	
	public String toString() {
		StringBuilder prnArray = new StringBuilder("[");
		for (int i = 0; i < realLength; i++) {
			if (i == realLength - 1) {
				prnArray.append(array[i]);
			} else {
				prnArray.append(array[i]);
				prnArray.append(", ");
			}
		}
		prnArray.append("]");
		return prnArray.toString();
	}
	
	public int size() {
		return realLength;
	}
}
