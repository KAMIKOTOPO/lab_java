package lab_07;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
		ExactNumber exactNumber = new ExactNumber();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("""
						1) Умножение
						2) Деление
						3) Сложение
						4) Вычитание
						5)Завершить работы программы
						""");
				System.out.print("Выберите операцию: ");
				int x = scanner.nextInt();
				switch (x) {
				case 1: {
					inputNumber(exactNumber);
					exactNumber.getArray();
					System.out.println(exactNumber.getSignMantissa());
//					inputNumberWithDecimalPointWhithoutSign(exactNumber, 0);
//					System.out.println(exactNumber.getOneNumber());
					break;
				}
				case 2:
					System.out.println();
					break;
				case 3:
					System.out.println(Math.pow(-1, 0));
					break;
				case 4:
					break;
				case 5:
					System.out.println("Завершение работы программы...");
					return;
				default:
					System.out.println("Такой команды нет");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("\nВы ввели некорректные данные\n");
				scanner.nextLine();
			} catch (java.lang.NumberFormatException e) {
				System.out.println("\nВы ввели некорректные данные\n");
				scanner.nextLine();
			}
		}
	}

	public static void inputNumber(ExactNumber exactNumber) {
		Scanner scanner = new Scanner(System.in);
		int minimumAllowedValue = -99999;
		int maxAllowedValue = 99999;
		System.out.print("Введите число: ");
		String stringNumber = scanner.nextLine();
		char[] charNumber = stringNumber.toCharArray();
		System.out.println(Arrays.toString(charNumber));
		exactNumber.creatArrayMantissa(charNumber.length);
		System.out.println(stringNumber);
		int index = 0;
		int order = 0;
		for (int i = 0; i < charNumber.length; i++) {
			if (Character.isDigit(charNumber[i])) {
				exactNumber.setFigureNumber(index, (byte) charNumber[i]);
				index++;
			} else if (charNumber[0] == 0 && charNumber[0] == '-') {
				exactNumber.setSignMantissa(0);
			} else if (charNumber[0] == 0 && (charNumber[0] == '+' || Character.isDigit(charNumber[0]))) {
				exactNumber.setSignMantissa(1);
			} else if (i < 5 && Character.isDigit(charNumber[i])) {
				order++;
			} else if (i < 5 && '.' == charNumber[i]) {
				exactNumber.setOrder(order);
			} else if (i > 4 && !Character.isDigit(charNumber[i]) && charNumber[i] != 'e') {
				System.out.println("Программа не работает с числами такого формата");
			}

		}
	}

	public static void chooseNumberFormat(ExactNumber exactNumber) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(
					"""
							1)Формат числа с десятичной точкой с знаком или без знака. Пример: 123.13213, -1233.4213, +12323.32131
							2)Экспоненциа́льный формат записи: 123123e21, 123232e-23, -12323e21
							3)Экспоненциа́льный формат записи c десятичной точкой: 1232.3121e21
							""");
			int x = scanner.nextInt();
			System.out.println("Выберите формат числа: ");
			switch (x) {
			case 1:
//					inputNumberWithDecimalPointWhithoutSign(exactNumber, 0);
				break;

			case 2:
				System.out.println();
				break;
			case 3:

			default:
				System.out.println("Такого формата чисел нет");
			}
		}
	}

}
