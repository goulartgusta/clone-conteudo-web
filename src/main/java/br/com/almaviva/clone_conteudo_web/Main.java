package br.com.almaviva.clone_conteudo_web;

import java.util.Scanner;

import br.com.almaviva.clone_conteudo_web.config.WebDriverConfig;
import br.com.almaviva.clone_conteudo_web.repository.MangasRepository;
import br.com.almaviva.clone_conteudo_web.service.ClonaConteudo;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] mangas = MangasRepository.getMangasRepository();
		System.out.println("Olá, usuário! por favor, insira o nome do manga para buscar no site 'leitordemanga': ");
		String url;

		for (String manga : mangas) {
			url = sc.nextLine();
			if (url.equalsIgnoreCase(manga)) {
				System.out.println("Mangá válido! Clonando capítulos.");
				url = "https://leitordemanga.com/ler-manga/" + url.replace(" ", "-").toLowerCase()
						+ "/portugues-pt-br/capitulo-01/?style=list";
				WebDriverConfig driver = new WebDriverConfig();
				ClonaConteudo clonaConteudo = new ClonaConteudo(driver.getDriver(), url);

				clonaConteudo.clonarPagina();
				driver.getDriver().quit();
			}
		}

		sc.close();
	}
}
