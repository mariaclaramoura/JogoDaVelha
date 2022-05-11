import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JogoDaVelha {
	
	static ArrayList<Integer> jogador = new ArrayList<Integer>();
	static ArrayList<Integer> cpu = new ArrayList<Integer>();
	static boolean condicaoFinal = true;
	static int jogadas = 0;

	
	public static void main (String [] args) {
		
		Random random = new Random();
		int posicao, num;
		String condicao = " ";
		
		char[][] tabela = {{' ', '|', ' ', '|', ' '},
				{' ', '+', ' ', '+', ' '},
				{' ', '|', ' ', '|', ' '},
				{' ', '+', ' ', '+', ' '},
				{' ', '|', ' ', '|', ' '}
		};
		
		imprimirTabela(tabela);
		
	while(condicaoFinal==true) {
	
		System.out.println("Digite a sua posição (1-9): ");
		Scanner sc = new Scanner(System.in);
		posicao = sc.nextInt();
		
		while(jogador.contains(posicao) || cpu.contains(posicao)) {
	
		System.out.println("Você digitou uma posição já usada :( Digite outra posição!");
		posicao = sc.nextInt();
		}
		
		posicionar(tabela, posicao, "jogador");
		imprimirTabela(tabela);
		
		System.out.println("Vez do computador.");
		num = random.nextInt(9) + 1;
		
		while(cpu.contains(num) || jogador.contains(num)) {
			System.out.println("Você digitou uma posição já usada :( Digite outra posição!");
			num = random.nextInt(9) + 1;
		}
		posicionar(tabela, num, "cpu");
		cpu.add(num);
		imprimirTabela(tabela);
		
		jogadas++;
		condicao = ganhador();

	}
	
	System.out.println(condicao);
	
}
	
	public static void imprimirTabela(char[][] tab) {
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(tab[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void posicionar(char [][] tabela, int posicao, String jogadores) {
		
		char simbolo;
		if(jogadores=="jogador") {
			simbolo = 'X';
			jogador.add(posicao);
		} else {
			simbolo = 'O';
			cpu.add(posicao);
		}
		
		switch(posicao) {
		
		case 1:
			tabela[0][0] = simbolo;
			break;
		case 2:
			tabela[0][2] = simbolo;
			break;
		case 3:
			tabela[0][4] = simbolo;
			break;
		case 4:
			tabela[2][0] = simbolo;
			break;
		case 5:
			tabela[2][2] = simbolo;
			break;
		case  6:
			tabela[2][4] = simbolo;
			break;
		case 7:
			tabela[4][0] = simbolo;
			break;
		case 8:
			tabela[4][2] = simbolo;
			break;
		case 9:
			tabela[4][4] = simbolo;
			break;
		}
	}
	
	public static String ganhador() {
		
		List linha_1 = Arrays.asList(1,2,3);
		List linha_2 = Arrays.asList(4,5,6);
		List linha_3 = Arrays.asList(7,8,9);
		
		List col_1 = Arrays.asList(1,4,7);
		List col_2 = Arrays.asList(2,5,8);
		List col_3 = Arrays.asList(3,6,9);
		
		List diagonal_1 = Arrays.asList(1,5,9);
		List diagonal_2 = Arrays.asList(3,5,7);
		
		List<List> ganhador = new ArrayList<List>();
		ganhador.add(linha_1);
		ganhador.add(linha_2);
		ganhador.add(linha_3);
		ganhador.add(col_1);
		ganhador.add(col_2);
		ganhador.add(col_3);
		ganhador.add(diagonal_1);
		ganhador.add(diagonal_2);
		
		for(List lista: ganhador) {
			if(jogador.containsAll(lista)) {
				condicaoFinal =false;
				return "Parabéns, você ganhou!";
			} 
			
			if(cpu.containsAll(lista)) {
				condicaoFinal = false;
				return ":( Não foi dessa vez. O computador ganhou.";
			}
			
				if(jogadas == 9) {
				condicaoFinal = false;
				return "Deu velha!";
			}
		
		}
		return " ";
	}
}
	
