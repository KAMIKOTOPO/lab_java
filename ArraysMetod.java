package lab_06;

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
						7) Найти число
						8) Найти значение K-го экстремума в списке.
						9) Найти наиболее длинную непрерывную возрастающую
						последовательность отрицательных чисел, модуль которых является
						простым числом.
						10) Поменять местами последний чётный и минимальный положительный.
											""");
				System.out.print("Выберите одну из предложенных операций: ");
				int x = scanner.nextInt();
				switch (x) {
				case 1:
					int sizeArray = (int)inputDouble("Введите размер массива: ");
					arraysList.creatRandomArrays(sizeArray);
					break;
				case 4:
					System.out.println(arraysList.toString());
					break;
				case 5:
					menuAdd(arraysList);
					break;
				case 6:
					int index = (int) inputDouble("Введите индекс числа, которое будет удалено: ");
					arraysList.remove(index);
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
				int index = (int) inputDouble("Введите номер индекс по которому хотите добавить число: ");
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

	public void add(double number) {
		int lengthArray = array.length;
		if (realLength != lengthArray) {
			for (int i = 0; i < lengthArray; i++) {
				array[i] = array[i];
			}
			array[realLength] = number;
		} else {
			double[] newArray = new double[lengthArray * 2];
			for (int i = 0; i < lengthArray; i++) {
				newArray[i] = array[i];
			}
			newArray[realLength] = number;
			array = newArray;
		}
		realLength++;
	}

	public void insert(int index, double number) {
		int lengthArray = array.length;
//		if (realLength != lengthArray) {
//			for (int i = lengthArray - 1; i >= 0; i--) {
//				array[i] = array[i];
//				if(i == index) {
//					array[i] = number;
//				}
//			}
//			array[index] = number;
//		} else {
		double[] newArray = new double[lengthArray * 2];
		for (int i = lengthArray - 1; i >= 0; i--) {
			newArray[i] = array[i];
			if (i == index) {
				newArray[i] = number;
			}
		}
		array = newArray;
	}

	public void remove(int index) {
		int lengthArray = array.length;
		int lengthNewArray = lengthArray - 1;
		double[] newArray = new double[lengthNewArray];
//		for (int i = 0; i < lengthArray; i++) {
//			if(i == index) {
//				
//				continue;
//			}else {
//			newArray[i] = array[i];
//			}
//		}
		int i = 0;
		while (i > lengthArray) {
			if(i!=index){
				newArray[i] = array[i];
				i++;
			}else {
				continue;
			}
		}
		array = newArray;
		realLength = lengthNewArray;
	}

	public void creatRandomArrays(int sizeArrays) {
		double[] newArray = new double[sizeArrays];
		for (int i = 0; i < sizeArrays; i++) {
			newArray[i] = Math.round(Math.random() * 10);
		}
		array = newArray;
		realLength = sizeArrays;
	}
	
	public String toString() {
		StringBuilder prnArray = new StringBuilder("[");
		for (int i = 0; i < realLength; i++) {
			if (i == realLength - 1) {
				prnArray.append(array[i] + "");
			} else {
				prnArray.append(array[i] + ", ");
			}
		}
		prnArray.append("]");
		return prnArray.toString();
	}
	
	public int size() {
		return realLength;
	}
}
