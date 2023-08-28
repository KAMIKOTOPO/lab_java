package lab_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class CalculatingSumSeries {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SeriesNumbers series = new SeriesNumbers();
		while (true) {
			try {
				System.out.println();
				System.out.println("""
						Меню:
						1)Вывести таблицу промежуточных вычислений ряда 1
						2)Вывести таблицу промежуточных вычислений ряда 2
						3)Вывести таблицу промежуточных вычислений ряда 3
						""");
				System.out.print("Выберите одну из предложенных операций: ");
				int x = scanner.nextInt();
				switch (x) {
				case 1:
					tableMenu(series);
					break;
				case 2:
					double accaracy = checkNegativ("Введите точность: ");
					int step = (int) checkNegativ("Введите шаг: ");
					int numberIterations = (int) checkNegativ("Введите количество итераций: ");
					int argument = (int) inputInt("Введит аргумент");
					System.out.println(series.creatTableValueSeriesTwo(accaracy, step, numberIterations, argument));
					break;
				case 3:
					accaracy = checkNegativ("Введите точность: ");
					step = (int) checkNegativ("Введите шаг: ");
					numberIterations = (int) checkNegativ("Введите количество итераций: ");
					System.out.println(series.creatTableValueSeriesThree(accaracy, step, numberIterations));
					break;
				default:
					System.out.println("\nНет такой команды!!!");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("\nВы ввели некорректные данные");
				scanner.nextLine();
			}
		}
	}

	public static void tableMenu(SeriesNumbers numbers) {
		double accaracy = checkNegativ("Введите точность: ");
		int step = (int) checkNegativ("Введите шаг: ");
		int numberIterations = (int) checkNegativ("Введите количество итераций: ");
		System.out.println(numbers.creatTableValueSeriesOne(accaracy, step, numberIterations));
	}

	public interface Checker {
		boolean check(double val);
	}

	public static double inputInt(String prompt, Checker checker, String errMsg) {
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
		return inputInt(prompt, x -> x > 0, essMsg);
	}

	public static double inputInt(String prompt) {
		return inputInt(prompt, x -> true, "");
	}

	public static double getFactorial(double x) {
		if (x <= 1) {
			return 1;
		} else {
			return x * getFactorial(x - 1);
		}
	}
}

class SeriesNumbers {
	private int argument;

	public SeriesNumbers() {
		this.argument = 1;
	}

	public void setArgument(int argument) {
		this.argument = argument;
	}

	public String creatTableValueSeriesOne(double accusracy, int step, int numberIterations) {
		StringBuilder tableValue = new StringBuilder();
		String sep = "+----------------------------+\n";
		tableValue.append(sep);
		tableValue.append(String.format("|%10s|%8s|%8s|\n", "№ итерации", "t", "s"));
		tableValue.append(sep);
		String msgSum = "";
		int number = 0;
		double sum = 0;
		double previousValue = 1;
		for (int x = 1; x < numberIterations; x++) {
			double value = (double) 1 / x;
			sum += value;
			if (x - (number * step) == 1) {
				tableValue.append(String.format("|%10d|%8.3f|%8.3f|\n", x, value, sum));
				number++;
			}
			if (Math.abs(value - previousValue) > accusracy) {
				msgSum = String.format("\nСумма бесконечного ряда - %.3f, вычислена за %d итерации", sum, x);
				break;
			}
			previousValue = value;
		}
		tableValue.append(sep);
		tableValue.append(msgSum);
		return tableValue.toString();
	}

	public String creatTableValueSeriesTwo(double accusracy, int step, int numberIterations, int argument) {
		StringBuilder tableValue = new StringBuilder();
		String sep = "+----------------------------+\n";
		Scanner scanner = new Scanner(System.in);
		tableValue.append(sep);
		tableValue.append(String.format("|%10s|%8s|%8s|\n", "№ итерации", "t", "s"));
		tableValue.append(sep);
		int arg = argument;
		int y = arg;
		String msgSum = "";
		int number = 0;
		double sum = 0;
		double previousValue = 1;
		int factorial = 1;
		for (int x = 0; x < numberIterations; x++) {
			double value;
			if (x == 0) {
				value = 1;
			} else {
				factorial *= x;
				value = (double) y / factorial;
				y = y * arg;
			}
			sum += value;
			if (x - (number * step) == 0) {
				tableValue.append(String.format("|%10d|%8.3f|%8.3f|\n", x + 1, value, sum));
				number++;
			}
			if (Math.abs(value - previousValue) > accusracy) {
				msgSum = String.format("\nСумма бесконечного ряда - %.3f, вычислена за %d итерации", sum, x);
				break;
			}
			previousValue = value;
		}
		tableValue.append(sep);
		tableValue.append(msgSum);
		return tableValue.toString();
	}

	public String creatTableValueSeriesThree(double accusracy, int step,  int numberIterations) {
		StringBuilder tableValue = new StringBuilder();
		String sep = "+----------------------------+\n";
		tableValue.append(sep);
		tableValue.append(String.format("|%10s|%8s|%8s|\n", "№ итерации", "t", "s"));
		tableValue.append(sep);
		String msgSum = "";
		int number = 0;
		double sum = 0;
		int y = 1;
		double previousValue = 1;
		for (int x = 0; x < numberIterations; x++) {
			double value;
			if(x == 0) {
				value = 1;
			}else {
				y *= 2;
			 value = Math.pow((-1),x)*(double)1/y;
			}
			sum += value;
			if(x-(number*step)==0) {
			tableValue.append(String.format("|%10d|%8.3f|%8.3f|\n", x+1, value, sum));
			number++;
			}
			if(Math.abs(value-previousValue)>accusracy) {
				msgSum = String.format("\nСумма бесконечного ряда - %.3f, вычислена за %d итерации", sum, x);
				break;
			}
			previousValue = value;
		}
		tableValue.append(sep);
		tableValue.append(msgSum);
	return tableValue.toString();
	}
}
