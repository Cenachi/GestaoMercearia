package Menu;

import controler.ProdutoEstoqueControler;
import controler.VendaControle;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;
import model.ProdutoEstoque;
import model.Venda;
import model.dao.ClienteDao;
import model.dao.ProdutoEstoqueDao;

public class SecaoVenda {

    public static Scanner teclado = new Scanner(System.in);

    public static void fazerVenda() {

        Venda novaVenda = new Venda(null);

        ArrayList<ProdutoEstoque> carrinho = new ArrayList<>();
        ArrayList<Integer> quant = new ArrayList<>();

        System.out.println("\nÉ cliente cadastrado?\n1-SIM\n2-NÂO");
        int cadastrado = teclado.nextInt();

        while (true) {
            if ((cadastrado == 1 != true) && (cadastrado == 2 != true)) {
                System.out.println("\nEntrada invalida!! Informe novamente:");
                System.out.println("É cliente cadastrado?\n1-SIM\n2-NÂO");
                cadastrado = teclado.nextInt();
            } else {
                break;
            }
        }

        int idCliente = 0;
        int idTrue = 0;

        if (cadastrado == 1) {
            System.out.println("\nInfome o ID do cliente:");
            idCliente = teclado.nextInt();

            if (ClienteDao.pegaCliente(idCliente) != null) {
                idTrue = idCliente;

            } else {
                System.err.println("\nEsse cliente não existe!");
                cadastrado = 2;
            }
        }

        if (cadastrado == 2) {
            System.out.println("\nDeseja se cadastrar?\n1-SIM\n2-NÂO");
            int opcao = teclado.nextInt();

            boolean valida = true;
            while (valida == true) {
                if ((opcao == 1 != true) && (opcao == 2 != true)) {
                    System.out.println("\nEntrada invalida!! Informe novamente:");

                    System.out.println("Deseja se cadastrar?\n1-SIM\n2-NÃO");
                    opcao = teclado.nextInt();
                } else {
                    valida = false;
                }
            }

            if (opcao == 1) {
                SecaoCliente.cadastrar();
            }
        }

        Cliente cl = ClienteDao.pegaCliente(idTrue);

        while (true) {

            System.out.println("\nInforme o ID do produto. (Valor negativo para encerrar)");
            int id = teclado.nextInt();

            if (id < 0) {
                VendaControle.emiteNota(carrinho, quant, cadastrado, cl);
                break;
            }

            ProdutoEstoque pegaProduto = ProdutoEstoqueDao.pegaProdutoCodigo(id);

            carrinho.add(ProdutoEstoqueDao.pegaProdutoCodigo(id));

            if (ProdutoEstoqueDao.pegaProdutoCodigo(id) != null) {
                System.out.println("\nInforme a quantidade:");
                int quantidade = teclado.nextInt();

                while (true) {
                    if (pegaProduto.getQuantidade() < quantidade) {
                        System.err.println("Quantidade em estoque insuficiente");
                        System.out.println("\nInforme a quantidade novamente:");
                        quantidade = teclado.nextInt();

                    } else {

                        quant.add(quantidade);

                        novaVenda.addProdutoCompra(ProdutoEstoqueControler.buscaProdutoId(id), quantidade, cadastrado);

                        break;
                    }
                }

            } else {
                System.err.println("Esse produto não existe no sistema!");
            }
        }

        VendaControle.cadastroVenda(novaVenda, idTrue);
    }
}
