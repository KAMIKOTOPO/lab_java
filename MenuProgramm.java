package lab_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuProgramm {
	public static void main(String[] args) throws IndexOutOfBoundsException {
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
						11) Завершить работу программы. /""");
				System.out.print("Выберите одну из предложенных операций: ");
				int x = scanner.nextInt();
				switch (x) {
				case 1:
					int sizeArray = (int) inputDouble("Введите размер массива: ");
					creatRandomArray(sizeArray, myArraysList);
					break;
				case 2:
					saveArrayInFile(myArraysList);
					break;
				case 3:
					readArrayFromFile(myArraysList);
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
					if (indexValue == -1) {
						System.out.println("Такого значения нет в массиве");
					} else {
						System.out.printf("Первое вхождение значение произошло под индексом - %d\n", indexValue);
					}
					break;
				case 8:
					int indexExtremum = findIndexExtremum(myArraysList, (int) inputDouble("введите к: "));
					System.out.printf("Значение K-го экстремума в списке: %3f\n", indexExtremum);
					break;
				case 9:
					sequence(myArraysList.getArray());
				case 10:
					swapLastEvenAndMinimumPositiveNumbers(myArraysList);
					break;	
				case 11:
					System.out.println("Программа завершила работу...");
					return;
				default:
					System.out.println("\nТакой команды нет\n");
					break;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("\nВы ввели некорректные данные\n");
			}
		}
	}

	public static void menuAdd(MyArrayList myArraysList) throws IndexOutOfBoundsException {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("""
					1)Добавить число в конец списка
					2)Добавить число по заданому индексу
					""");
			int x = (int) inputDouble("Выберите одну из предложенных операций: ");
			if (x == 1) {
				double number1 = inputDouble("Введите число которое хотите добавить в конец списка: ");
				myArraysList.add(number1);
				return;
			} else if (x == 2) {
				if (myArraysList.size() == 0) {
					System.out.println("Массив пока что не создан");
				} else {
					int index = (int) checkIndex("Введите номер индекс по которому хотите добавить число: ",
							myArraysList);
					if (index < 0) {
						throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");
					} else {
						double number2 = inputDouble("Введите число которое хотите добавить: ");
						myArraysList.insert(index, number2);
					}
				}
			return;
			} else {
				System.out.println("\nТакой команды нет\n");
			}
		}
	}

	public static void creatRandomArray(int arraySize, MyArrayList myArraysList) throws IndexOutOfBoundsException {
		if (arraySize <= 0) {
			throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");
		} else {
			double[] newArray = new double[arraySize];
			for (int i = 0; i < arraySize; i++) {
				newArray[i] = Math.round(Math.random() * 10) * (Math.pow((-1), Math.round(Math.random() * 10)));
			}
			myArraysList.setArray(newArray);
			myArraysList.setSize(arraySize);
		}
	}

	public static int findIndexExtremum(MyArrayList myArrayList, int k) {
		for (int i = 1; i < myArrayList.size() - 1; i++) {
			if ((myArrayList.get(i) > myArrayList.get(i - 1)
					&& myArrayList.get(i) > myArrayList.get(i + 1))
					|| (myArrayList.get(i)< myArrayList.get(i - 1)
							&& myArrayList.get(i) < myArrayList.get(i + 1)) && (--k == 0)) {
				return i;
			}
		}
		return -1;
	}

	public static void saveArrayInFile(MyArrayList myArrayList) {
		try (FileWriter fw = new FileWriter("./arraysFile.txt", false)) {
			PrintWriter out = new PrintWriter(new BufferedWriter(fw));
			for (int i = 0; i < myArrayList.size(); i++) {
				out.print(String.format("%.1f\n", myArrayList.get(i)));
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("Возникла ошибка во время записи, проверьте данные.");
		}
	}

	public static void readArrayFromFile(MyArrayList myArrayList) {
		try (FileReader fileReader = new FileReader("./arraysFile.txt")) {
			BufferedReader br = new BufferedReader(fileReader);
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				myArrayList.add(Double.parseDouble(line.replace(",", ".")));
				count++;
			}
			myArrayList.setSize(count);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Возникла ошибка во время записи, проверьте данные.");
		}catch (IOException e) {
			System.out.println("Возникла ошибка во время записи, проверьте данные.");
		}
	}

	public static void swapLastEvenAndMinimumPositiveNumbers(MyArrayList myArrayList) {
		int indexEvenLastNumber = -1;
		double minPositive = Integer.MAX_VALUE;
		int indexMinPositiv = -1;
		for (int i = 0; i < myArrayList.size(); i++) {
			double num = myArrayList.get(i);
			if (num % 2 == 0) {
				indexEvenLastNumber = i;
			}
			if (num < minPositive && num > 0) {
				minPositive = myArrayList.get(i);
				indexMinPositiv = i;
			}
		}
		if (indexEvenLastNumber == -1) {
			System.out.println("Нет четных чисел в массиве");
		} else if (indexMinPositiv == -1) {
			System.out.println("Нет положительных чисел в массиве");
		} else {
			double temp = myArrayList.get(indexEvenLastNumber);
            myArrayList.set(indexEvenLastNumber,  myArrayList.get(indexMinPositiv));
            myArrayList.set(indexMinPositiv, temp);
			System.out.println("Значения поменялись местами");
		}
	}

	public static boolean isPrime(double num) {
		if(Math.abs(num) < 2) return false;
        for(int i = 2; i < Math.abs(num) / 2; i++) {
            if(Math.abs(num) % i == 0) {
                return false;
            }
        }
        return true;
	}
	
	public static void sequence(double[] arr) {
		int maxLen = 0;
		int maxIndex = -1;
		int len = 0;
		for (int i = 1; i < arr.length; i++) {
			if(isPrime(arr[i]) && arr[i]<0) {
				len++;
				if(len>1) {
					if(arr[i]<arr[i-1]) {
						len = 0;
					}
				}
			}else {
				if(len>maxLen) {
					maxLen = len;
					maxIndex = i-len;
				}
				len = 0;
			}
		}
		System.out.println("Начальный индекс последовательности: " + maxIndex);
		System.out.println("Длинна последовательности: " + maxLen);
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
