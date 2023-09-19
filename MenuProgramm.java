package lab_06;

import java.util.Scanner;

public class MenuProgramm {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MyArrayList myArraysList = new MyArrayList();
		while (true) {
			try {
				System.out.println("""
						1) Задать размер списка и заполнить его случайными числами /
						2) Сохранить список в текстовый файл /
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
					myArraysList.creatRandomArray(sizeArray);
					break;
				case 2:
					myArraysList.saveArrayInFile(myArraysList);
					break;
				case 3:
					myArraysList.readArrayFromFile();
					break;
				case 4:
					System.out.println(myArraysList.toString());
					break;
				case 5:
					menuAdd(myArraysList);
					break;
				case 6:
					int index = (int) checkIndex("Введите индекс числа, которое будет удалено: ", myArraysList);
					myArraysList.remove(index);
					break;
				case 7:
					double value = inputDouble("Введите значение индекс которого вы хотите найти: ");
					int indexValue = myArraysList.indexOf(value);
					if(indexValue == -1) {
						System.out.println("Такого значения нет в массиве");
					}else {
						System.out.printf("Первое вхождение значение произошло под индексом - %d\n", indexValue);
					}
					break;
				case 8:
					int indexExtremum = myArraysList.findIndexExtremum();
					System.out.printf("Значение K-го экстремума в списке: %.3f\n",myArraysList.get(indexExtremum));
					break;
				case 11: 
					System.out.println("Программа завершила работу...");
					return;
				case 10:
					myArraysList.swapLastEvenAndMinimumPositiveNumbers();
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
	
	public static void menuAdd(MyArrayList myArraysList) {
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
				myArraysList.add(number1);
				return;
			case 2:
				if (myArraysList.size() == 0) {
					System.out.println("Массив пока что не создан");
					return;
				} else {
					int index = (int) checkIndex("Введите номер индекс по которому хотите добавить число: ",
							myArraysList);
					double number2 = inputDouble("Введите число которое хотите добавить: ");
					myArraysList.insert(index, number2);
					return;
				}
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

	public static double checkNegative(String prompt) {
		String errMsg = "Для этого значения не может использоваться отрицательное или равное нулю число";
		return inputDouble(prompt, x -> x > 0, errMsg);
	}

	public static double inputDouble(String prompt) {
		return inputDouble(prompt, x -> true, "");
	}
	public static double checkIndex(String prompt, MyArrayList arraysList) {
		String errMsg = "Нет такого индекса";
		return inputDouble(prompt, x -> x > 0 && x < arraysList.size(), errMsg);
	}
}
