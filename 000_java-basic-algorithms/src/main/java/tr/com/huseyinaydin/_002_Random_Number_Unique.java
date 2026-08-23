package tr.com.huseyinaydin;

import java.util.Random;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java.
 *
 */

public class _002_Random_Number_Unique {

	private final static Random RANDOM = new Random();
	private static boolean isFirstLine = false;

	public static void main(String[] args) throws InterruptedException {
		endLine();
		
		System.out.println("Sıralanmamış rastele benzersiz sayılar: ");
		double[] randomNumbers = generateRandomUniqueNumber((byte) 11);
		for (var number : randomNumbers) {
			System.out.print("|" + number + "|");
		}

		endLine();
		
		System.out.println("Sıralanmış rastele benzersiz sayılar: ");
		double[] sortedRandomNumbers = sortNumbers(randomNumbers, false);
		for (var number : sortedRandomNumbers) {
			System.out.print("|" + number + "|");
		}
		
		endLine();
	}

    public static void endLine() {
		if(!isFirstLine) {
			isFirstLine = true;
			System.err.println("----------------------");
		}
		else {
			System.out.println("");
			System.err.println("----------------------");
		}
	}

    public static double[] generateRandomUniqueNumber(byte arraySize) throws InterruptedException {
		byte number = 0;
		double[] numbersArray = new double[arraySize];
		boolean state = false;
		for (byte i = 0; i < numbersArray.length; i++) { // hazırlık ve üretilenleri atama döngüsü
			state = false; //hazırlık
			number = 0; //initialization
			while (!state) { //sayı üretme döngüsü
				number = (byte) RANDOM.nextInt(numbersArray.length);
				for (byte j = 0; j < numbersArray.length; j++) { //kontrol döngüsü
					if (numbersArray[j] == number) //dizide varmı kontrol et varsa çık tekrar üret anlamında
						break;
					if (j == numbersArray.length - 2) //bittiği anlamına geliyor bittiyse zaten dizide yeni üretilen yoktur o zaman while üretim döngüsünden de çık
						state = true;
				}
			}
			numbersArray[i] = number; //üretileni atama kısmı
			//System.out.println("Üretilen: " + numbersArray[i]);
		}
		return numbersArray;
	}

	public static double[] sortNumbers(double[] numbers, boolean isMaxToMin) {
		double temp = 0;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				if(isMaxToMin) {
					if (numbers[i] > numbers[j]) {
						temp = numbers[i];
						numbers[i] = numbers[j];
						numbers[j] = temp;
					}
				}
				else {
					if (numbers[i] < numbers[j]) {
						temp = numbers[j];
						numbers[j] = numbers[i];
						numbers[i] = temp;
					}
				}
			}
		}
		return numbers;
	}
}