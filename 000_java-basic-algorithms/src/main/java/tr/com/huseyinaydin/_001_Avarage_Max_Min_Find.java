package tr.com.huseyinaydin;

import java.util.ArrayList;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java.
 *
 */

public class _001_Avarage_Max_Min_Find {

	public static void main(String[] args) {
		double[] array = new double[] { 10, 20, 30, 2, 8, 100, 3, 5, 7, 9, 101 };
		System.out.println("Dizi ortalaması: " + avarage(array));
		System.out.println("Dizideki en küçük sayı: " + findMinFromArray(array));
		System.out.println("Dizideki en büyük sayı: " + findMaxFromArray(10, 20, 30, 2, 8, 100));
		System.out.print("Sıfıra tam bölünenler: ");
		twoDivideFromArray(array).forEach(i -> System.out.print(" - " + i));
		System.out.println("");
		// System.out.println("Sıfıra tam bölünenler: " +
		// twoDivideFromArray(array).toArray());
		// System.out.println("\nSıfıra tam bölünemeyenler: " +
		// noneTwoDivideFromArray(array).toArray());
		System.out.print("Sıfıra tam bölünemeyenler: ");
		noneTwoDivideFromArray(array).forEach(i -> System.out.print(" - " + i));
	}

    public static double avarage(double... array) {
		if (array.length <= 0)
			throw new IllegalArgumentException("Geçersiz argüman...");
		double avarage = 0;
		for (double number : array) {
			avarage += number;
		}
		avarage = avarage / array.length;
		return avarage;
	}

    public static double findMinFromArray(double... array) {
		if (array.length <= 0)
			throw new IllegalArgumentException("Geçersiz argüman...");
		double min = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

    public static double findMaxFromArray(double... array) {
		if (array.length <= 0)
			throw new IllegalArgumentException("Geçersiz argüman...");
		var min = array[0];
		for (var i = 0; i < array.length; i++) {
			if (array[i] > min) {
				min = array[i];
			}
		}
		return min;
	}

    public static List<Double> twoDivideFromArray(double... array) {
		// double[] twoDivide = new double[] {};
		List<Double> twoDivide = new ArrayList<Double>();
		// byte twoDivideIndex = 0;
		// byte noneTwoDivideIndex = 0;
		for (var number : array) {
			if (number % 2 == 0) { // çift
				twoDivide.add(number);
				// twoDivideIndex++;
			}
			/*
			 * else if(number % 2 != 0) { twoDivide[noneTwoDivideIndex] = number;
			 * noneTwoDivideIndex++; }
			 */
		}
		return twoDivide;
	}

	public static List<Double> noneTwoDivideFromArray(double... array) {
		List<Double> twoDivide = new ArrayList<Double>();
		for (var number : array) {
			if (number % 2 != 0) { // tek
				twoDivide.add(number);
			}
			/*
			 * else if(number % 2 != 0) { twoDivide[noneTwoDivideIndex] = number;
			 * noneTwoDivideIndex++; }
			 */
		}
		return twoDivide;
	}
}