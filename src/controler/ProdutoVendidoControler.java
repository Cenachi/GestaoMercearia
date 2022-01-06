package controler;

import model.Venda;
import model.dao.ProdutoVendidoDao;

public class ProdutoVendidoControler {
  
  public static boolean cadastroProdutosVenda(Venda vendaProdutos){

    if(vendaProdutos != null && vendaProdutos.getId() > 0){
      if(!vendaProdutos.getProdutosComprados().isEmpty()){
        return ProdutoVendidoDao.cadastraProdutoVenda(vendaProdutos);
      }
      return true;
    }else{
      return false;
    }
  }
}