package algoritmos_elementares;

import java.util.Arrays;
import java.util.Scanner;

public class MoveImpostor {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] entrada = sc.nextLine().split(" ");
		int[] entradaNum = new int[entrada.length];
		
		for (int i = 0; i < entrada.length; i++) {
			entradaNum[i] = Integer.parseInt(entrada[i]);
		}
		
		int i = 1;
		
		while (entradaNum[i] >= entradaNum[i-1]) {
			i++;
		}
		
		while (i > 0 && entradaNum[i] < entradaNum[i-1]) {
			int aux = entradaNum[i];
			entradaNum[i] = entradaNum[i-1];
			entradaNum[i-1] = aux;
			
			i--;
		}
		
		System.out.println(Arrays.toString(entradaNum));
		
	}

}
