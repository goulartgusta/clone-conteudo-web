package br.com.almaviva.clone_conteudo_web;

import java.util.Collections;
import java.util.Scanner;

import br.com.almaviva.clone_conteudo_web.config.WebDriverConfig;
import br.com.almaviva.clone_conteudo_web.repository.MangasRepository;
import br.com.almaviva.clone_conteudo_web.service.ClonaConteudo;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static String[] mangas = MangasRepository.getMangasRepository();
	private static String url;

	public static void main(String[] args) {
		listarMangasDisponiveis();
		iniciarCloneManga();
	}

	static void listarMangasDisponiveis() {
		System.out.println("Olá usuário! Bem-vindo(a) ao Clona Mangás! Segue opções de mangás disponíveis: \n");
		
		for (String manga : mangas) {
			System.out.println(manga);
		}
	
		System.out.println();
		System.out.println(
				"Por favor, insira o nome do manga para clonar os 10 priemiros capítulos no site 'leitordemanga': ");
	}

	static void iniciarCloneManga() {
		for (String manga : mangas) {
			url = sc.nextLine();
			if (url.equalsIgnoreCase(manga)) {
				System.out.println("Mangá válido! Clonando capítulos.");
				String nomeMangaTratado = url.replace(" ", "-").toLowerCase();
				url = "https://leitordemanga.com/ler-manga/" + nomeMangaTratado	+ "/portugues-pt-br/capitulo-01/?style=list";
				WebDriverConfig driver = new WebDriverConfig();
				ClonaConteudo clonaConteudo = new ClonaConteudo(driver.getDriver(), url);

				clonaConteudo.clonarPagina();
				driver.getDriver().quit();
			}
		}
		sc.close();
	}
}
