package model.dao;

import Menu.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.Conexao;
import model.ProdutoEstoque;

public class ProdutoEstoqueDao {

    public static boolean insereProdutoEstoque(ArrayList<ProdutoEstoque> listaProduto) {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "INSERT INTO produto(nome,preco,quantidade) VALUES(?,?,?)";

            for (ProdutoEstoque p : listaProduto) {

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, p.getNome());
                stmt.setDouble(2, p.getPreco());
                stmt.setInt(3, p.getQuantidade());

                stmt.execute();
            }

            System.out.println("\nProdutos carregados com sucesso!!");

            Menu.listSecao();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean insereProdutoManual(ProdutoEstoque novoProduto) {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "INSERT INTO produto(nome,preco,quantidade) VALUES(?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, novoProduto.getNome());
            stmt.setDouble(2, novoProduto.getPreco());
            stmt.setInt(3, novoProduto.getQuantidade());
            //stmt.setInt(4, novoProduto.getId()); 

            stmt.execute();
            System.out.println("\nProduto adicionado com sucesso!!");

            Menu.listSecao();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deletaProduto(int id) {
        try ( Connection con = Conexao.getConexaoMySQL()) {

            
            String sql1 = "DELETE FROM produtovendido WHERE id_produto ="+id;
            PreparedStatement stmt1 = con.prepareStatement(sql1);            
            stmt1.execute();
            
            
            String sql = "DELETE FROM produto WHERE id =" + id;
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            
            System.out.println("\nProduto deletado com sucesso!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Menu.listSecao();
    }

    public static void atualizaProduto(int opcaoAtt, double preco, int quantidade, int id) {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            if (opcaoAtt == 1) {
                String sql = "UPDATE produto SET preco = " + preco + " WHERE id = " + id;
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.execute();
                System.out.println("\nPreço atualizado com sucesso!!");
            } else if (opcaoAtt == 2) {

                String sql = "UPDATE produto SET quantidade = " + quantidade + " WHERE id = " + id;
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.execute();
                System.out.println("\nQuantidade atualizada com sucesso!!");
            } else if (opcaoAtt == 3) {

                String sql = "UPDATE produto SET preco = " + preco + " WHERE id = " + id;
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.execute();

                sql = "UPDATE produto SET quantidade = " + quantidade + " WHERE id = " + id;
                stmt = con.prepareStatement(sql);

                stmt.execute();
                System.out.println("\nPreço e quantidade atualizado com sucesso!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Menu.listSecao();
    }

    public static void listaProduto() {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "SELECT * FROM produto";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                int id = rs.getInt("id");

                System.out.format("%s | R$ %s | %s un. |%s \n", id, nome, preco, quantidade);
            }

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Menu.listSecao();
    }

    public static ProdutoEstoque pegaProdutoCodigo(int id) {

        ProdutoEstoque estoque = null;

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "SELECT * FROM produto WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estoque = new ProdutoEstoque(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("preco"));
            }

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estoque;
    }

    public static boolean updateProduto(ProdutoEstoque prod) {
        try ( Connection con = Conexao.getConexaoMySQL()) {
            String sql = "UPDATE produto SET nome=?, preco=?, quantidade=? WHERE id=?";
            PreparedStatement trans = con.prepareStatement(sql);
            trans.setString(1, prod.getNome());
            trans.setDouble(2, prod.getPreco());
            trans.setInt(3, prod.getQuantidade());
            trans.setInt(4, prod.getId());
            return trans.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ProdutoEstoque consultaProduto(int id) {
        ProdutoEstoque resultado = null;

        try ( Connection con = Conexao.getConexaoMySQL()) {
            String sql = "SELECT * FROM produto WHERE id=?";

            PreparedStatement trans = con.prepareStatement(sql);
            trans.setInt(1, id);

            ResultSet tuplas = trans.executeQuery();

            if (tuplas.next()) {
                resultado = new ProdutoEstoque(id, tuplas.getString("nome"),
                        tuplas.getInt("quantidade"), tuplas.getDouble("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static ProdutoEstoque consultaNomeProduto(String nome) {

        return null;
    }
}
