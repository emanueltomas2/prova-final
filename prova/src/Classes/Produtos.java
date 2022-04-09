package Classes;

    import java.util.InputMismatchException;

public class Produtos {
    private String nome;
    private int codigo;
    private double valor;
    private int quantEmEstoque;

    public void removerQuant(int quant) {
     quantEmEstoque-= quant;
    }
   
    @Override
    public String toString() {
        return "Produto-  nome: " + nome + "       | codigo: " + codigo + "        | quantEmEstoque: " + quantEmEstoque + "       | valor: "
                + valor;
    }

 //---------------------------------------------------------------------------------------------------//
    public Produtos(String nome, int codigo, double valor, int quantEmEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.quantEmEstoque = quantEmEstoque;
    }
//---------------------------------------------------------------------------------------------------//
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if(nome.matches(".*\\d.*")){
            throw new InputMismatchException("Não pode conter numeros no nome do produto");
        }else{
            this.nome = nome;
        }
    }
//---------------------------------------------------------------------------------------------------//
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
//---------------------------------------------------------------------------------------------------//
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
//---------------------------------------------------------------------------------------------------//
    public int getQuantEmEstoque() {
        return quantEmEstoque;
    }
    public void setQuantEmEstoque(int quantEmEstoque) {
        this.quantEmEstoque = quantEmEstoque;
    }
    

}

