package br.com.almaviva.clone_conteudo_web.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriaArquivo {

	private static final String diretorio = "/home/almaviva-linux/opt/dev/Projetos/clone-conteudo-web/evidencias";
	
	public static void salvarHtml(String conteudo, String nomeArquivo) {
		File arquivo = new File(diretorio, nomeArquivo);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
			writer.write(conteudo);
			System.out.println("Arquivo salvo! " + arquivo);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar o arquivo... " + e);
		}
	}
}
