package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tools.Conexao;

import model.ProdutoVendido;
import model.Venda;


public class ProdutoVendidoDao {
  
  public static boolean cadastraProdutoVenda(Venda vendaProdutos){
    try (Connection con = Conexao.getConexaoMySQL()) {
      String sql = "INSERT INTO produtovendido(id_venda, id_produto, quantidade, precodia) VALUES(?, ?, ?, ?)";
      
      for (ProdutoVendido prod : vendaProdutos.getProdutosComprados()) {
        PreparedStatement trans = con.prepareStatement(sql);
        trans.setInt(1, vendaProdutos.getId());
        trans.setInt(2, prod.getProdutoLoja().getId());
        trans.setInt(3, prod.getQuantidadeVendida());
        trans.setDouble(4, prod.getPrecoPraticado());

        trans.execute();
      }
      return true;
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }  
}
