package br.com.almaviva.clone_conteudo_web.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClonaConteudo {

    private WebDriver driver;
    private String url;
    private int capitulos = 10;
    private int contaArquivos = 1;

    public ClonaConteudo(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void clonarPagina() {
        try {
            while (contaArquivos <= capitulos) {
                System.out.println("Clonando URL: " + url);
                driver.get(url);

                String paginaFonte = driver.getPageSource();
                String arquivoNome = "capítulo-" + contaArquivos + ".html";

                paginaFonte = configurarBtnProx(paginaFonte);
                paginaFonte = configurarBtnAnt(paginaFonte);

                CriaArquivo.salvarHtml(paginaFonte, arquivoNome);
                contaArquivos++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar clonar URL: " + e);
            e.printStackTrace();
        }
    }

    private String configurarBtnProx(String paginaFonte) {
        WebElement botaoProximo = null;
        try {
            botaoProximo = driver.findElement(By.cssSelector("a.btn.next_page"));
            String href = botaoProximo.getAttribute("href");
            if (href != null) {
                String linkLocal = "./capítulo-" + (contaArquivos + 1) + ".html";
                paginaFonte = paginaFonte.replace(href, linkLocal);
                url = href; //para a prox pag
            }
        } catch (Exception e) {
            System.out.println("Botão 'Próximo' não encontrado: " + e);
        }
        return paginaFonte;
    }

    private String configurarBtnAnt(String paginaFonte) {
        if (contaArquivos > 1) {
            WebElement botaoAnterior = null;
            try {
                botaoAnterior = driver.findElement(By.cssSelector("a.btn.prev_page"));
                String hrefAnterior = botaoAnterior.getAttribute("href");
                if (contaArquivos > 1) {
                    String linkLocalAnterior = "./capítulo-" + (contaArquivos - 1) + ".html";
                    paginaFonte = paginaFonte.replace(hrefAnterior, linkLocalAnterior);
                }
            } catch (Exception e) {
                System.out.println("Botão 'Anterior' não encontrado: " + e);
            }
        }
        return paginaFonte;
    }
}
