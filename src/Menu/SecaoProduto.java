package Menu;

import controler.ProdutoEstoqueControler;
import java.io.File;
import java.util.Scanner;
import model.ProdutoEstoque;
import model.dao.ProdutoEstoqueDao;

public class SecaoProduto {

    public static Scanner teclado = new Scanner(System.in); //teclado global
    public static File arquivo = null;

    public static void cadastrar() {

        System.out.println("\nComo deseja cadastrar?\n1-Lista de produtos\n2-Unico produto");
        String opcaoProduto = teclado.nextLine();

        while (true) {

            if (opcaoProduto.equals("1") != true && opcaoProduto.equals("2") != true) {
                System.out.println("\nEntrada invalida!! Informe novamente:");
                System.out.println("Como deseja cadastrar?\n1-Lista de produtos\n2-Unico produto");

                opcaoProduto = teclado.nextLine();
            } else {
                break;
            }
        }

        if (opcaoProduto.equals("1")) {
            //Arquivo dentro do diretorio do projeto;
            File arquivo = new File("ListaProdutos.csv");

            ProdutoEstoqueControler.carregaArquivo(arquivo);

        } else if (opcaoProduto.equals("2")) {

            ProdutoEstoque novoProduto = new ProdutoEstoque();

            System.out.println("\nInserindo produto manualmente...");

            System.out.println("Informe o nome do produto:");
            novoProduto.setNome(teclado.nextLine().trim());

            System.out.println("Informe o preço do produto:");
            novoProduto.setPreco(teclado.nextDouble());

            System.out.println("Informe a quantidade de produto:");
            novoProduto.setQuantidade(teclado.nextInt());

            ProdutoEstoqueDao.insereProdutoManual(novoProduto);
        }
    }

    public static void deletar() {
        System.out.println("\nInforme o ID do produto:");
        int id = teclado.nextInt();

        ProdutoEstoqueControler.deletaProduto(id);
    }

    public static void atualizar() {
        System.out.println("\nInforme o ID do produto:");
        int id = teclado.nextInt();

        System.out.println("\nO que deseja atualizar:\n1-Preço\n2-Quantidade\n3-Tudo");
        int opcaoAtt = teclado.nextInt();

        ProdutoEstoqueControler.atualizaProdutoC(id, opcaoAtt);
    }

    public static void listar() {
        System.out.println("------------- LISTANDO PRODUTOS ---------------");
        System.out.println("ID | Nome | Preço | Quant.\n");

        ProdutoEstoqueDao.listaProduto();
    }
}
