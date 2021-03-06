
package Menu;

import java.util.Scanner;

public class Menu {
    
    public static Scanner teclado = new Scanner(System.in); //teclado global
    
    public static void listMenu(String opcao){        
        switch(opcao){            
            case "1":
                   
                System.out.println("\n-----------------------------------------------\n");
                System.out.println("Selecione uma opção:");            
                System.out.println("\n1-Cadastrar Cliente\n2-Deletar Cliente\n3-Atualizar Cliente\n4-Listar Clientes");  
                
                String opcaoCliente = teclado.nextLine();
                
                while(true){         
            
                    if(opcaoCliente.equals("1")!= true && opcaoCliente.equals("2")!= true && opcaoCliente.equals("3")!= true && opcaoCliente.equals("4")!= true){
                        System.out.println("Entrada invalida!! Informe novamente:");
                        System.out.println("1-Cadastrar Cliente\n2-Deletar Cliente\n3-Atualizar Cliente\n4-Listar Cliente");  
                        opcaoCliente = teclado.nextLine();
                    }else{
                        break;
                    }
                }                   
                
                if(opcaoCliente.equals("1")){                   
                    System.out.println("\n-----------------------------------------------");
                    SecaoCliente.cadastrar();
                }
                
                if(opcaoCliente.equals("2")){
                    System.out.println("\n-----------------------------------------------");
                    SecaoCliente.deletar();
                }
                
                if(opcaoCliente.equals("3")){
                    System.out.println("\n-----------------------------------------------");
                    SecaoCliente.atualizar();
                }
                
                if(opcaoCliente.equals("4")){
                    SecaoCliente.listar();
                }     
            break;
            
            case "2":
                System.out.println("\n-----------------------------------------------\n");
                System.out.println("Selecione uma opção:");            
                System.out.println("\n1-Cadastrar Produto\n2-Deletar Produto\n3-Atualizar Produto\n4-Listar Produtos");  
                
                String opcaoProduto = teclado.nextLine();
                                
                boolean validaProduto= true;                
                while(validaProduto == true){         
            
                    if(opcaoProduto.equals("1")!= true && opcaoProduto.equals("2")!= true && opcaoProduto.equals("3")!= true && opcaoProduto.equals("4")!= true){
                        System.out.println("Entrada invalida!! Informe novamente:");
                        System.out.println("1-Cadastrar Produto\n2-Deletar Produto\n3-Atualizar Produto\n4-Listar Produto");  
                        opcaoCliente = teclado.nextLine();
                    }else{
                        validaProduto = false;
                    }
                }        
                                                
                if(opcaoProduto.equals("1")){                   
                    System.out.println("\n-----------------------------------------------");
                    SecaoProduto.cadastrar();
                }
                
                if(opcaoProduto.equals("2")){
                    System.out.println("\n-----------------------------------------------");
                    SecaoProduto.deletar();
                }
                
                if(opcaoProduto.equals("3")){
                    System.out.println("\n-----------------------------------------------");
                    SecaoProduto.atualizar();
                }
                
                if(opcaoProduto.equals("4")){
                    SecaoProduto.listar();
                }                         
                
            break;    
                     
            case "3":
                SecaoVenda.fazerVenda();                
            break;
                
            
            
            case "0":
                System.out.println("\n-----------------------------------------------");
                System.out.println("Finalizando...\n");
            break;
        }          
    }   
    
    
    public static void listSecao(){
         
        System.out.println("\n-----------------------------------------------\n");
        System.out.println("Selecione a Seção:");
        System.out.println("\n1-Cliente\n2-Produto\n3-Venda\n0-SAIR");
        
        String opcao = teclado.nextLine();
                   
        while(true){         
            
            if(opcao.equals("1")!= true && opcao.equals("2")!= true && opcao.equals("3")!= true && opcao.equals("0")!= true){                
                System.out.println("\nEntrada invalida!! Informe novamente:");
                System.out.println("\n1-Cliente\n2-Produto\n3-Venda\n0-SAIR");
                opcao = teclado.nextLine();
            }else{
              break;
            }
        }
        
        listMenu(opcao);         
    }
}