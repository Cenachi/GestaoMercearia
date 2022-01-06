package model;

import java.util.Date;
import java.util.Vector;

public class Venda {

    private int id;
    private Vector<ProdutoVendido> produtosComprados;
    private Date diaCompra;
    private Cliente usuario;
    private double totalCompra;

    public Venda() {
    }

    //recuperando do banco
    public Venda(int id, Vector<ProdutoVendido> produtosComprados, Date diaCompra, Cliente usuario, double totalCompra) {
        this.setId(id);
        this.produtosComprados = produtosComprados;
        this.setDiaCompra(diaCompra);
        this.setUsuario(usuario);
        this.totalCompra = totalCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda(Cliente usuario) {
        this.setUsuario(usuario);
        setDiaCompra(new Date());
        produtosComprados = new Vector<>();
    }

    public Date getDiaCompra() {
        return diaCompra;
    }

    public void setDiaCompra(Date diaCompra) {
        this.diaCompra = diaCompra;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public Vector<ProdutoVendido> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(Vector<ProdutoVendido> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

    //adição dos produtos na venda
    public void addProdutoCompra(ProdutoEstoque prodLoja, int quant, int cliente) {

        ProdutoVendido prodVenda = new ProdutoVendido(prodLoja, quant);
        this.produtosComprados.add(prodVenda);

        if (cliente == 1) {
            this.totalCompra += quant * prodLoja.getPreco() * 0.95;
        } else {
            this.totalCompra += quant * prodLoja.getPreco();
        }

    }
}
