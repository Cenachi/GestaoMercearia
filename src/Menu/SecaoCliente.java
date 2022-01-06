
package Menu;

import controler.ClienteControler;
import java.util.Scanner;
import model.Cliente;
import model.dao.ClienteDao;

public class SecaoCliente {
    
    public static Scanner teclado = new Scanner(System.in,"ISO-8859-1"); //teclado global
        
    public static void cadastrar(){
        
        Cliente novoCliente = new Cliente();
        
        System.out.println("Informe o nome:");
        novoCliente.setNome(teclado.nextLine().trim());
                       
        System.out.println("Informe o endereço:");
        novoCliente.setEndereco(teclado.nextLine().trim());
        
        System.out.println("Informe o telefone:");
        novoCliente.setTel(teclado.nextLine().trim());
        
        ClienteControler.cadastroCliente(novoCliente);        
    }
    
    public static void deletar(){
                
        System.out.println("\nInforme o ID do cliente:");
        int id = teclado.nextInt();
                
        ClienteControler.deletaCliente(id);      
    }
    
    public static void atualizar(){
              
        System.out.println("\nInforme o ID do cliente:");
        int id = teclado.nextInt(); 
        
        System.out.println("\nO que deseja atualizar:\n1-Telefone\n2-Endereço\n3-Tudo");
        int opcaoAtt = teclado.nextInt();
                
        ClienteControler.atualizaCliente(id,opcaoAtt);               
    }
    
    public static void listar(){
        
        System.out.println("\n------------- LISTANDO CLIENTES ---------------");
        System.out.println("Id | Nome | Endereço | Telefone\n");
       
        ClienteDao.listaCliente();
    }    
}