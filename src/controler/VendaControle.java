package controler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Cliente;
import model.ProdutoEstoque;
import model.ProdutoVendido;
import model.Venda;
import model.dao.VendaDao;

public class VendaControle {

    public static boolean cadastroVenda(Venda nova, int id) {
        //para cada produto que esta sndo comprado, teremos que avaliar as quantidade que estão sendo compradas
        //checagem das quantidades
        for (ProdutoVendido prod : nova.getProdutosComprados()) {
            if (prod.getQuantidadeVendida() > prod.getProdutoLoja().getQuantidade()) {
                return false;
            }
        }
        //se tem quantidade no estoque, aqui é debitado dos produtos armazenados no estoque
        for (ProdutoVendido prod : nova.getProdutosComprados()) {
            //pega o produto da loja e modifica a quantidade dele
            // é a quantidade que temos na loja subtraido pela quantidade que ta sendo comprada
            prod.getProdutoLoja().setQuantidade(prod.getProdutoLoja().getQuantidade() - prod.getQuantidadeVendida());
            if (!ProdutoEstoqueControler.atualizaProduto(prod.getProdutoLoja())) {
                return false;
            }
        }
        //cadastro da nova venda para o banco
        if (VendaDao.cadastroVeenda(nova, id)) {
            //cadastrando todos os produtos vendidos
            if (!ProdutoVendidoControler.cadastroProdutosVenda(nova)) {
                return false;
            }
        }
        return true;
    }

    public static void emiteNota(ArrayList<ProdutoEstoque> carrinho, ArrayList<Integer> quant, int cliente, Cliente cl) {

        int cont = 0;
        double total = 0;
        double totalFinal = 0;

        DecimalFormat df = new DecimalFormat("0.##");

        System.out.println("\n----------------------------------------");
        System.out.println("              CUPOM FISCAL                  \n");

        if (cliente == 1) {
            System.out.println("Nome cliente: " + cl.getNome() + "     ID:" + cl.getId());
            System.out.println("Endereço:" + cl.getEndereco());
            System.out.println("Tel:" + cl.getTel());
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Nome cliente:                 ID:");
            System.out.println("Endereço:");
            System.out.println("Tel:");
            System.out.println("----------------------------------------");
        }

        System.out.println("ID   |  Nome Produto |   Quant.   |   Preço\n");

        for (ProdutoEstoque i : carrinho) {

            if (cliente == 1) {
                total = quant.get(cont) * (carrinho.get(cont).getPreco()) * 0.95;
                totalFinal += quant.get(cont) * (carrinho.get(cont).getPreco()) * 0.95;
            } else {
                total = quant.get(cont) * (carrinho.get(cont).getPreco());
                totalFinal += quant.get(cont) * (carrinho.get(cont).getPreco());
            }

            System.out.println(carrinho.get(cont).getId() + " | " + carrinho.get(cont).getNome() + " | " + quant.get(cont) + "UN. x R$" + carrinho.get(cont).getPreco() + " | " + df.format(total));
            cont++;
        }
        System.out.println("----------------------------------------");
        System.out.println("TOTAL R$                        " + df.format(totalFinal));
    }
}
