package algoritmos_elementares;

import java.util.Scanner;

public class PotenciaRescursiva {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		System.out.println(potenciaRecursiva(num1, num1, num2));
	}
	
	private static int potenciaRecursiva(int num1, int numAtual, int num2) {
		if (num2 == 0) {
			return 1;
		} else if (num2 <= 1) {
			return numAtual;
		} else {
			numAtual = num1 * numAtual;
			num2 = num2 - 1;
			return potenciaRecursiva(num1, numAtual, num2);
		}
	}

}
