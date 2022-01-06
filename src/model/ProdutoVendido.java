package model;

public class ProdutoVendido {
  ProdutoEstoque produtoLoja;
  private int quantidadeVendida;
  private double precoPraticado;

  public ProdutoVendido() {
  
  }
  //instanciado a primeira vez
  public ProdutoVendido(ProdutoEstoque produtoLoja, int quantidadeVendida) {
    this.produtoLoja = produtoLoja;
    this.setQuantidadeVendida(quantidadeVendida);
    this.setPrecoPraticado(produtoLoja.getPreco());
  }
  //reecuperar do banco
  public ProdutoVendido(ProdutoEstoque produtoLoja, int quantidadeVendida, double precoPraticado) {
    this.produtoLoja = produtoLoja;
    this.setQuantidadeVendida(quantidadeVendida);
    this.setPrecoPraticado(precoPraticado);
  }
  public int getQuantidadeVendida() {
    return quantidadeVendida;
  }
  public void setQuantidadeVendida(int quantidadeVendida) {
    this.quantidadeVendida = quantidadeVendida;
  }
  public double getPrecoPraticado() {
    return precoPraticado;
  }
  public void setPrecoPraticado(double precoPraticado) {
    this.precoPraticado = precoPraticado;
  }
  public ProdutoEstoque getProdutoLoja(){
    return produtoLoja;
  }
  public void setProdutoLoja(ProdutoEstoque produtoLoja){
    this.produtoLoja = produtoLoja;
  }
}
