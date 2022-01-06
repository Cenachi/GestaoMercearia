package model.dao;

import Menu.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import model.Cliente;
import model.Venda;
import tools.Conexao;

public class VendaDao {

    public static boolean cadastroVeenda(Venda nova, int id) {
        try ( Connection con = Conexao.getConexaoMySQL()) {
            String sql = "INSERT INTO venda(dia, total, id_usuario) VALUES(?, ?, ?)";

            PreparedStatement trans = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            trans.setTimestamp(1, new Timestamp(nova.getDiaCompra().getTime()));
            trans.setDouble(2, nova.getTotalCompra());

            nova.setId(id);

            trans.setInt(3, nova.getId());

            trans.execute();

            Menu.listSecao();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Venda buscaVendaId(int id) {
        Venda resultado = null;

        try ( Connection con = Conexao.getConexaoMySQL()) {
            String sql = "SELECT * FROM venda WHERE id=?";

            PreparedStatement trans = con.prepareStatement(sql);
            trans.setInt(1, id);

            ResultSet tupla = trans.executeQuery();

            if (tupla.next()) {
                Cliente temp = new Cliente();
                temp.setId(tupla.getInt("id_usuario"));
                resultado = new Venda(id, null, new Date(tupla.getTimestamp("dia").getTime()),
                        temp, tupla.getDouble("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
