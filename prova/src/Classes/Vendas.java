package Classes;
import java.time.LocalDate;

public class Vendas {
   private int quantVendida;
   private Produtos produtos;
   private LocalDate datadeVenda;
   private int quantTotal;
   private double valorTotal;

//--------------------------------------------------------------------------------------------------------------------//
   public double ValorTotal() {
       return valorTotal = valorTotal + Calcular();
   }
//-------------------------------------------------------------------------------------------------------------------//
   public int CalcularTotal() {
       return quantTotal = quantTotal + quantVendida ;
   }

   public double Calcular() {
    return quantVendida * produtos.getValor();
}


//--------------------------------------------------------------------------------------------
public Vendas( int quantVendida, Produtos produtos , LocalDate datadeVenda) {
    this.datadeVenda = datadeVenda;
    this.quantVendida = quantVendida;
    this.produtos = produtos;
}
//--------------------------------------------------------------------------------------------//
@Override
public String toString() {
    return "Data de Venda: " + datadeVenda + "- Produto Vendido:" + produtos + "- Quantidade Vendida:" + quantVendida;
}
//--------------------------------------------------------------------------------------------//
public LocalDate getDatadeVenda() {
    return datadeVenda;
}
public void setDatadeVenda(LocalDate datadeVenda) {
    this.datadeVenda = datadeVenda;
}
//--------------------------------------------------------------------------------------------//
public int getQuantVendida() {
    return quantVendida;
}
public void setQuantVendida(int quantVendida) {
    this.quantVendida = quantVendida;
}
//--------------------------------------------------------------------------------------------//
public Produtos getProdutos() {
    return produtos;
}
public void setProdutos(Produtos produtos) {
    this.produtos = produtos;
}
}
