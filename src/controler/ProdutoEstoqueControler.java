package controler;

import Menu.Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.ProdutoEstoque;
import model.dao.ProdutoEstoqueDao;

public class ProdutoEstoqueControler {

    public static Scanner teclado = new Scanner(System.in); //teclado global

    public static void carregaArquivo(File arquivo) {
        ArrayList<ProdutoEstoque> listaProduto = new ArrayList<>();

        if (arquivo != null && arquivo.exists() && arquivo.isFile()) {
            try {
                FileReader marcaLeitura = new FileReader(arquivo);
                BufferedReader leitor = new BufferedReader(marcaLeitura);

                //leitura das linhas dos produtos                  
                String linha = leitor.readLine();

                while (linha != null) {
                    String infos[] = linha.split(";");

                    if (infos.length == 3) {
                        String nome = infos[0];

                        try {
                            double preco = Double.parseDouble(infos[1]);
                            int quantidade = Integer.parseInt(infos[2]);

                            ProdutoEstoque novoProduto = new ProdutoEstoque(nome, quantidade, preco);
                            listaProduto.add(novoProduto);
                        } catch (NumberFormatException ex) {
                            System.err.println("Erro nos valores da linha: " + linha);
                        }
                    }
                    linha = leitor.readLine();
                }
                leitor.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (!listaProduto.isEmpty()) {
            //chamada ao ProdutoEstoqueDao para armazenar os valores em Banco de Dados      
            ProdutoEstoqueDao.insereProdutoEstoque(listaProduto);
        }
    }

    public static void deletaProduto(int id) {

        System.out.println("\nDeseja realmente deletar?\n1-SIM\n2-NÃO");
        int opcaoDeleta = teclado.nextInt();

        while (true) {
            if ((opcaoDeleta == 1 != true) && (opcaoDeleta == 2 != true)) {
                System.out.println("\nEntrada invalida!! Informe novamente:");
                System.out.println("Deseja realmente deletar?\n1-SIM\n2-NÃO");
                opcaoDeleta = teclado.nextInt();
            } else {
                break;
            }
        }

        if (opcaoDeleta == 1) {
            ProdutoEstoqueDao.deletaProduto(id);
        } else {
            Menu.listSecao();
        }
    }

    public static void atualizaProdutoC(int id, int opcaoAtt) {

        while (true) {
            if ((opcaoAtt == 1 != true) && (opcaoAtt == 2 != true) && (opcaoAtt == 3 != true)) {
                System.out.println("\nEntrada invalida!! Informe novamente:");
                System.out.println("O que deseja atualizar:\n1-Preço\n2-Quantidade\n3-Tudo");
                opcaoAtt = teclado.nextInt();
            } else {
                break;
            }
        }

        double preco = 0;
        int quantidade = 0;

        if (opcaoAtt == 1) {
            System.out.println("\nNovo preço:");
            preco = teclado.nextDouble();

            ProdutoEstoqueDao.atualizaProduto(opcaoAtt, preco, quantidade, id);
        } else if (opcaoAtt == 2) {
            System.out.println("\nNova quantidade:");
            quantidade = teclado.nextInt();

            ProdutoEstoqueDao.atualizaProduto(opcaoAtt, preco, quantidade, id);
        } else if (opcaoAtt == 3) {
            System.out.println("\nNovo preço:");
            preco = teclado.nextDouble();

            System.out.println("\nNova quantidade:");
            quantidade = teclado.nextInt();

            ProdutoEstoqueDao.atualizaProduto(opcaoAtt, preco, quantidade, id);
        }
    }

    public static boolean atualizaProduto(ProdutoEstoque prod) {
        if (prod != null) {
            return ProdutoEstoqueDao.updateProduto(prod);
        } else {
            return false;
        }
    }

    public static ProdutoEstoque buscaProdutoId(int id) {

        if (id > 0) {
            return ProdutoEstoqueDao.consultaProduto(id);
        } else {
            System.err.println("Erro no valor do ID, deve ser maior ou igual a 0");
            return null;
        }
    }
}
