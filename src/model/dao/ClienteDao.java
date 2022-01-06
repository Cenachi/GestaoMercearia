package model.dao;

import Menu.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.Conexao;
import model.Cliente;

public class ClienteDao {

    public static boolean insereCliente(Cliente novo) {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "INSERT INTO cliente(nome,endereco,telefone) VALUES(?,?,?)";

            PreparedStatement trans = con.prepareStatement(sql);
            trans.setString(1, novo.getNome());
            trans.setString(2, novo.getEndereco());
            trans.setString(3, novo.getTel());

            trans.execute();
            System.out.println("\nCliente adicionado com sucesso!!");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Menu.listSecao();
        return false;
    }

    public static void deletaCliente(int id) {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "DELETE FROM cliente WHERE id = " + id;

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.execute();

            System.out.println("\nCliente deletado com sucessso!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Menu.listSecao();
    }

    public static void atualizaCliente(int id, int opcaoAtt, String telefone, String address) {

        try ( Connection con = Conexao.getConexaoMySQL()) {
            if (opcaoAtt == 1) {
                String sql = "UPDATE cliente SET telefone = '" + telefone + "' WHERE id = " + id;
                PreparedStatement stmt = con.prepareStatement(sql);

                System.out.println("\nTelefone atualizado com sucessso!!");
                stmt.execute();

            } else if (opcaoAtt == 2) {

                String sql = "UPDATE cliente SET endereco = '" + address + "' WHERE id = " + id;
                PreparedStatement stmt = con.prepareStatement(sql);

                System.out.println("\nEndereço atualizado com sucessso!!");
                stmt.execute();
            } else if (opcaoAtt == 3) {

                String sql = "UPDATE cliente SET telefone = '" + telefone + "' WHERE id = " + id;
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.execute();

                sql = "UPDATE cliente SET endereco = '" + address + "' WHERE id = " + id;
                stmt = con.prepareStatement(sql);

                System.out.println("\nTelefone e endereço atualizado com sucessso!!");
                stmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Menu.listSecao();
    }

    public static void listaCliente() {

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "SELECT * FROM cliente";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");

                System.out.format("%s | %s | %s | %s\n", id, name, endereco, telefone);
            }

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Menu.listSecao();
    }

    public static Cliente pegaCliente(int id) {

        Cliente cliente = null;

        try ( Connection con = Conexao.getConexaoMySQL()) {

            String sql = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("telefone"));
            }
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
