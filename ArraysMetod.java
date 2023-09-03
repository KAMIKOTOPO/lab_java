package lab_06;

import java.util.Scanner;

import lab_05.CalculatingSumSeries.Checker;

public class ArraysMetod {
	public static void main(String[] args) {
		while (true) {
			try {
			Scanner scanner = new Scanner(System.in);
			ArraysList arraysList = new ArraysList();
			System.out.println("""
					1) Задать размер списка и заполнить его случайными числами
					2) Сохранить список в текстовый файл
					3) Считать список из текстового файла
					4) Вывести список
					5) Добавить число
					6) Удалить число
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
				case 4:
					System.out.println(arraysList.printArray());
					break;
				case 5:
//					menuAdd(arraysList);
					arraysList.addEndAlgorithm(1);
					break;
				case 6:
					arraysList.getArray(0);
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
				arraysList.addEndAlgorithm(number1);
				return;
//			case 2:
//				int index = (int) inputDouble("Введите номер индекс по которому хотите добавить число");
//				double number2 = inputDouble("Введите число которое хотите добавить: ");
//				arraysList.addByIndex(index, number2);
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
		String essMsg = "Для этого значения не может использоваться отрицательное или равное нулю число";
		return inputDouble(prompt, x -> x > 0, essMsg);
	}
	public static double inputDouble(String prompt) {
		return inputDouble(prompt, x -> true, "");
	}
}
class ArraysList{
	private double[] array;
	
	public ArraysList() {
		this.array = new double[1];
	}
	public double getArray(int i) {
		return array[i];
	}
	public void addEndAlgorithm(double number) {
		int lengthArray = array.length;
		double[] newArray = new double[lengthArray+1];
		for (int i = 0; i < lengthArray; i++) {
		    newArray[i] = array[i];
		}
		newArray[lengthArray] = number;
		array = newArray;
	}
	public void addByIndex(int index, double number) {
		int lengthArray = array.length;
		double[] newArray = new double[lengthArray];
		for (int i = lengthArray-1; i >= 0; i--) {
		    newArray[i] = array[i];
		    if(i==index) {
		    	newArray[i] = number;
		    }
		}
		array = newArray;
	}
	public String printArray() {
		StringBuilder prnArray = new StringBuilder("[ ");
		for (int i = 0; i < array.length; i++) {
			prnArray.append(array[i]+" ");
		}
		prnArray.append("]");
		return prnArray.toString();
	}
	public int size() {
		return array.length;
	}
}
