package lab_07;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
		ExactNumber exactNumber = new ExactNumber();
		ExactNumber exactNumber2 = new ExactNumber();
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
					System.out.println(exactNumber.toString());
					break;
				}
				case 2:
					System.out.println();
					break;
				case 3:
					inputNumber(exactNumber);
					exactNumber.toString();
					inputNumber(exactNumber2);
					System.out.println(exactNumber.toString());
					System.out.println(exactNumber2.toString());
					exactNumber.addition(exactNumber2);
					System.out.println(exactNumber2.toString());
					break;
				case 4:
					inputNumber(exactNumber);
					exactNumber.toString();
					inputNumber(exactNumber2);
					System.out.println(exactNumber.toString());
					System.out.println(exactNumber2.toString());
					exactNumber.subtraction(exactNumber2);
					System.out.println(exactNumber2.toString());
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
		System.out.print("Введите число: ");
		String stringNumber = scanner.nextLine();
		int count =  0;
		int countSign = 0;
		countSign += stringNumber.length() - stringNumber.replace(String.valueOf('+'), "").length();
		countSign += stringNumber.length() - stringNumber.replace(String.valueOf('-'), "").length();
		count += stringNumber.length() - stringNumber.replace(String.valueOf('.'), "").length();
		count += stringNumber.length() - stringNumber.replace(String.valueOf('e'), "").length();
		if(stringNumber.matches("[0-9, . , \\+ , \\-, e]+")&&count<=2&&countSign <=2){
			exactNumber.addNumber(stringNumber);
		}else {
			System.out.println("Программа не работает с числами такого формата");
		}
	}

}
