package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();
		Product p = null;

		System.out.print("Digite o caminho da pasta: ");
		
		String caminhoPastaStr = sc.nextLine();
		System.out.print("Digite o nome do arquivo: ");
		
		String nomeArquivoStr = sc.nextLine();
		String caminhoCompleto = caminhoPastaStr.concat(nomeArquivoStr);
		
		System.out.println();
		System.out.println("Caminho completo: " + caminhoCompleto);

		File caminho = new File(caminhoCompleto);

		System.out.println();

		String name;
		double price;
		int qtd;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
			System.out.print("Quantos produtos foram vendidos?: ");
			int n = sc.nextInt();

			for (int i = 0; i < n; i++) {
				System.out.println();
				sc.nextLine();
				System.out.print("Nome: ");
				name = sc.nextLine();
				System.out.print("Preço: ");
				price = sc.nextDouble();
				System.out.print("Quantidade: ");
				qtd = sc.nextInt();
				p = new Product(name, price, qtd);

				list.add(p);
			}

			for (Product pr : list) {
				bw.write(pr.getName() + "," + pr.getPrice() + "," + pr.getQtd());
				bw.newLine();
			}

			System.out.println();
			System.out.println("Arquivo criado!");

		} catch (IOException e) {
			System.out.println("Erro na criação do arquivo: " + e.getMessage());
		}

		boolean sucess = new File(caminhoPastaStr + "\\subPasta").mkdir();
		System.out.println("Pasta Criada: " + sucess);
		String pastaResultado = caminhoPastaStr + "\\subPasta\\sumário.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String linha = br.readLine();

			while (linha != null) {
				String[] campo = linha.split(","); 
				name = campo[0]; 
				price = Double.parseDouble(campo[1]); 
				qtd = Integer.parseInt(campo[2]);
				
				linha = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pastaResultado))) {

				for (Product pr1 : list) {
					bw.write(pr1.getName() + "," + String.format("%.2f", pr1.total()));
					bw.newLine();
				}
				
				System.out.println(pastaResultado + " Criada!");

			} catch (IOException e) {
				System.out.println("Erro na escrita no novo arquivo: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo: " + e.getMessage());
		}

		sc.close();
	}

}
