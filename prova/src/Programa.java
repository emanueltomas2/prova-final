

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import javax.lang.model.util.ElementScanner6;

import Classes.Produtos;
import Classes.Vendas;


public class Programa {
    public static void main(String[] args) {
        List<Produtos> produtos = new ArrayList<>();
        List<Vendas> vendas = new ArrayList<>();
        DateTimeFormatter formatarData  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao = -1;
        Scanner ler = new Scanner(System.in);

        do {
            System.out.println("------------------------------------");
            System.out.println("--------- SEJAM BEM VINDOS ---------");
            System.out.println("------------------------------------ \n");
            System.out.println("1 - Cadastro de produtos");
            System.out.println("2 - Relatórios");
            System.out.println("3 - Realizar vendas");
            System.out.println("0 - Sair.");
            System.out.println("------------------------------------ \n");
            opcao = ler.nextInt();
            ler.nextLine();

            switch(opcao) {

                case 1:
                System.out.println("------------ CADASTROS ------------- \n");
                int op;
                do {
                System.out.println("1 - Consultar \n 2 - Incluir \n 0 - Voltar ao menu");
                op = ler.nextInt();
                ler.nextLine();
                switch(op){
                    case 1:
                        if(produtos.isEmpty()){
                            System.out.println("Não existem produtos cadastrados. \n");
                        }else {
                            System.out.println("Produtos em estoque: \n");
                            System.out.println( "              Nome              |       Código       |        Quantidade        |     Valor");
                            System.out.println("--------------------------------------------------------------------------------------------------");
                            produtos.stream()
                            .sorted(Comparator.comparing(Produtos::getNome))
                            .forEach(System.out::println);
                            System.out.println("-------------------------------------------------------------------------------------------------- \n");
                        }
                        
                        break;
                    case 2:
                        System.out.println("\n" );
                        System.out.println("Qual produto deseja cadastrar:");
                        String nvProduto = ler.nextLine();
                        System.out.println("Qual código deseja incluir ao produto:");
                        int cod = ler.nextInt();
                        System.out.println("Qual o valor do produto:");
                        Double nvValor = ler.nextDouble();
                        System.out.println("Qual a quantidade em estoque:");
                        int nvQuant = ler.nextInt();
                        produtos.add(new Produtos(nvProduto, cod, nvValor, nvQuant));
                        break;
                    case 0:
                        System.out.println("Voltando ao menu... \n");
                        break;
                    default:
                        System.out.println("*OPÇÃO INVALIDA* \n");
                        break;
                }
               
                } while (op != 0);
                
                break;

                case 2:
                System.out.println("------------ RELÁTORIOS ------------ \n");
                int op2;
                do {
                System.out.println("1 - Produtos \n 2 - Vendas por periodo - detalhado");
                System.out.println("3 - Vendas por periodo - consolidado \n 0-Voltar ao menu");
                op2 = ler.nextInt();
                ler.nextLine();
                switch(op2){
                    case 1:
                    if(produtos.isEmpty()){
                        System.out.println("Não existem produtos cadastrados. \n");
                    }else {
                        System.out.println("Produtos em estoque: \n");
                        System.out.println( "              Nome              |       Código       |        Quantidade        |     Valor");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        produtos.stream()
                        .sorted(Comparator.comparing(Produtos::getNome))
                        .forEach(System.out::println);
                        System.out.println("-------------------------------------------------------------------------------------------------- \n");
                        DoubleSummaryStatistics dados = produtos.stream()
                        .collect(Collectors.summarizingDouble(Produtos::getValor));
                        System.out.println("O valor minimo: " + dados.getMin() + "\nA valor medio: " + dados.getAverage() + "\nO valor maximo:"+ dados.getMax() + "\n");
                    }
                        break;
                    case 2: 
                    System.out.println("*** VENDAS POR PERIODOS - DETALHADO *** \n");
                    if(vendas.isEmpty()){
                        System.out.println("Ainda não foi efetuada nenhuma venda.");
                    }else{
                        System.out.println("Data de inicio:");
                        String dataInicial = ler.nextLine();
                        System.out.println("Data final:");
                        String dataFinal = ler.nextLine();

                        List<Vendas> filtrarVendas = vendas.stream().filter(p -> {
                            Vendas v = (Vendas)p;
                            return (v.getDatadeVenda().isEqual(LocalDate.parse(dataInicial, formatarData)) || v.getDatadeVenda().isEqual(LocalDate.parse(dataFinal, formatarData)))|| (v.getDatadeVenda().isBefore(LocalDate.parse(dataFinal, formatarData)) && (v.getDatadeVenda().isAfter(LocalDate.parse(dataInicial, formatarData))));
                        }).collect(Collectors.toList());

                        for (Vendas f : filtrarVendas) {
                            System.out.println("Produtos em estoque: \n");
                            System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\t%-20.15s\n", "DATA", "NOME", "QUANTIDADE", "VALOR UNITARIO", "VALOR TOTAL");
                            System.out.println("----------------------------------------------------------------------------------------------------------------");
                            System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\t%-20.15s\n", f.getDatadeVenda(), f.getProdutos().getNome(), f.getQuantVendida(), f.getProdutos().getValor(), f.Calcular());
                            System.out.println("----------------------------------------------------------------------------------------------------------------");
                            
                        }
                        
                    }   
            
                        break;
                    case 3:
                        System.out.println("VENDAS POR PERÍODOS - CONSOLIDADO *** \n");
                        if (vendas.isEmpty()) {
                            System.out.println("Ainda não foi feita nenhuma venda");
                        }else {
                            System.out.println("Data de inicio: ");
                            String data = ler.nextLine();
                            System.out.println("Data final: ");
                            String dataFinal = ler.nextLine();
                            List<Vendas> filtrarVendas = vendas.stream().filter(p -> {
                                Vendas fv = (Vendas)p;
                                return (fv.getDatadeVenda().isEqual(LocalDate.parse(data, formatarData)) || fv.getDatadeVenda().isEqual(LocalDate.parse(dataFinal, formatarData)))|| (fv.getDatadeVenda().isBefore(LocalDate.parse(dataFinal, formatarData)) && (fv.getDatadeVenda().isAfter(LocalDate.parse(data, formatarData))));
                            }).collect(Collectors.toList());

                            for (Vendas f : filtrarVendas) {
                                System.out.println("Produtos em estoque: \n");
                                System.out.printf("%-16.15s\t%-18.20s\t%-20.20s\n", "DATA DE VENDA", "QUANT.TOTAL-VENDA", "VALOR TOTAL-VENDIDO");
                                System.out.println("-------------------------------------------------------------------------------");
                                System.out.printf("%-16.15s\t%-18.20s\t%-20.20s\n", f.getDatadeVenda(), f.CalcularTotal(), f.ValorTotal());
                                System.out.println("------------------------------------------------------------------------------- \n");
                            }
                        }
                        break;

                    case 0:
                    System.out.println("Voltando ao menu... \n");
                        break;

                        
                    default:
                        System.out.println("*OPÇÃO INVALIDA* \n");
                        break;
                }
                    
                } while (op2 != 0);
                
                break;

                case 3:
                System.out.println("-------------- VENDAS -------------- \n");
                if(produtos.isEmpty()){
                    System.out.println("Ainda não há produto registrado.");
                }else{
                System.out.println("Qual produto deseja comprar:");
                String prod = ler.nextLine();
                boolean acheiProduto = false;
                for (Produtos p : produtos) {
                    if(p.getNome().equalsIgnoreCase(prod)){
                        acheiProduto = true;
                        System.out.println("Insira a data nesse formato - dd/mm/aaaa");
                        String data = ler.nextLine();
                        System.out.println("Qual a quantidade:");
                        int quantidade = ler.nextInt();
                        ler.nextLine();
                        if(p.getQuantEmEstoque() < quantidade){
                            System.out.println("Não há quantidade suficiente em estoque. \n");
                            break;
                        }else {  
                            p.removerQuant(quantidade);
                            vendas.add(new Vendas(quantidade, p, LocalDate.parse(data, formatarData)));
                            System.out.println("*** COMPRA REALIZADA *** \n");
                            break;   
                        }
                    }
                }
                if(acheiProduto == false){
                    System.out.println("Produto não cadastrado.");
                }

                }
                    break;

                case 0:
                System.out.println("Saindo... \n Espero ter ajudado! \n Muito Obrigado e volte sempre!");
                return;

                default:
                System.out.println("*OPÇÃO INVALIDA* \n");
                return;

             }
        } while (opcao != 0);
        ler.close();
    }
}
