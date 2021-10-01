
package Model;

import java.util.ArrayList;
import java.util.Date;
import Model.Estoque;
import Model.Vendido;


public class Venda {
    
    //Atributos
    
    private int idVenda;
    private ArrayList<Vendido> carrinho;
    private Date dataVenda;
    private Cliente cliente;
    private double valorTotal;
    
    //Constructor

    public Venda() {
        this.carrinho = new ArrayList<>();
        this.dataVenda = new Date();
        this.valorTotal = 0;
    }

    public Venda(int idVenda, ArrayList<Vendido> carrinho, Date dataVenda, Cliente cliente, double valorTotal) {
        this.idVenda = idVenda;
        this.carrinho = carrinho;
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    /*
    public ArrayList<Vendido> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Vendido> carrinho) {
        this.carrinho = carrinho;
    }
    */
    
    
    //Metodos
    
    public void adicionaProduto(Estoque prodLoja, int quant){
        
    Vendido prodVenda = new Vendido(quant, prodLoja);
        
    this.carrinho.add(prodVenda);
  
    }
    
    
}
