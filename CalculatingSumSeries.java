package lab_05;

import java.util.Scanner;

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
					tableMenu(series, x);
					break;
				case 2:
					tableMenu(series, x);
					break;
				case 3:
					tableMenu(series, x);
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

	public static void tableMenu(SeriesNumbers numbers, int x) {
		double accaracy = checkNegativ("Введите точность: ");
		int step = (int) checkNegativ("Введите шаг: ");
		int numberIterations = (int) checkNegativ("Введите количество итераций: ");
		if(x==1) {
			System.out.println(numbers.creatTableValueSeriesOne(accaracy, step, numberIterations));
		}
		else if(x==2) {
			int argument = (int) inputInt("Введит аргумент");
			System.out.println(numbers.creatTableValueSeriesTwo(accaracy, step, numberIterations, argument));
		}
		else if(x==3) {
			System.out.println(numbers.creatTableValueSeriesThree(accaracy, step, numberIterations));
		}
		
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
	public String creatTableValueSeriesOne(double accusracy, int step, int numberIterations) {
		StringBuilder tableValue = new StringBuilder();
		String sep = "+----------------------------+\n";
		tableValue.append(sep);
		tableValue.append(String.format("|%10s|%8s|%8s|\n", "№ итерации", "t", "s"));
		tableValue.append(sep);
		String msgSum = "";
		int stepPrint = 0;
		double sum = 0;
		for (int x = 1; x < numberIterations; x++) {
			double value = (double) 1 / x;
			sum += value;
			if (x == stepPrint * step) {
				tableValue.append(String.format("|%10d|%8.3f|%8.3f|\n", x, value, sum));
				stepPrint++;
			}
			if (Math.abs(value) < accusracy) {
				msgSum = String.format("\nСумма бесконечного ряда - %.3f, вычислена за %d итерации", sum, x);
				break;
			}
		}
		tableValue.append(sep);
		tableValue.append(msgSum);
		return tableValue.toString();
	}
	public String creatTableValueSeriesTwo(double accusracy, int step, int numberIterations, double argument) {
		StringBuilder tableValue = new StringBuilder();
		String sep = "+----------------------------+\n";
		Scanner scanner = new Scanner(System.in);
		tableValue.append(sep);
		tableValue.append(String.format("|%10s|%8s|%8s|\n", "№ итерации", "t", "s"));
		tableValue.append(sep);
		String msgSum = "";
		int stepPrint = 0;
		double sum = 0;
		double value = 1;
		int factorial = 1;
		for (int x = 0; x < numberIterations; x++) {
			if (x > 0) {
				factorial *= x;
				value *= argument / x;
//				argument *= argument;
			} 
			sum += value;
			if (x == stepPrint * step) {
				tableValue.append(String.format("|%10d|%8.3f|%8.3f|\n", x + 1, value, sum));
				stepPrint++;
			}
			if (Math.abs(value) < accusracy) {
				msgSum = String.format("\nСумма бесконечного ряда - %.3f, вычислена за %d итерации", sum, x);
				break;
			}
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
		int stepPrint = 0;
		double sum = 0;
		double value = 1;
		for (int x = 0; x < numberIterations; x++) {
			if(x > 0) {
				value /= -2;
			}
			sum += value;
			if(x == stepPrint * step) {
			tableValue.append(String.format("|%10d|%8.3f|%8.3f|\n", x+1, value, sum));
			stepPrint++;
			}
			if(Math.abs(value) < accusracy) {
				msgSum = String.format("\nСумма бесконечного ряда - %.3f, вычислена за %d итерации", sum, x);
				break;
			}
		}
		tableValue.append(sep);
		tableValue.append(msgSum);
	return tableValue.toString();
	}
}
