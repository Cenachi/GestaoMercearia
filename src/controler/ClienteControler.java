package controler;

import Menu.Menu;
import java.util.Scanner;
import java.util.Vector;

import model.Cliente;
import model.dao.ClienteDao;

public class ClienteControler {
    
    public static Scanner teclado = new Scanner(System.in,"ISO-8859-1"); //teclado global
    
    public static Cliente validaEntrada(Cliente cliente){
            
        String regex = "^\\([1-9]{2}\\)[0-9]{5}\\-[0-9]{4}$";  
        
        while(true){            
            
            if(cliente.getNome().length()<=3 && cliente.getEndereco().length()<=3){
                System.out.println("Nome ou endereço invalido, informe-o novamente!");
                System.out.println("\nNome:");
                cliente.setNome(teclado.nextLine().trim());
                System.out.println("Endereço:");
                cliente.setEndereco(teclado.nextLine().trim());
                
            }else if(cliente.getTel().matches(regex)!=true){
                System.out.println("\nTelefone invalido, informe novamente o telefone:");
                cliente.setTel(teclado.nextLine().trim());
            }else{
                break;
            }                     
        }             
        
        return cliente;
    }
    
    public static String validaTellAtt(String telefone){
        
        String regex = "^\\([1-9]{2}\\)[0-9]{5}\\-[0-9]{4}$";  
        
        while(true){                        
            if(telefone.matches(regex)!=true){
                System.out.println("\nTelefone invalido, informe novamente o telefone:");
                telefone = teclado.nextLine();
            }else{
                break;
            }                     
        }                        
        return telefone;
    }
    
  
  public static boolean cadastroCliente(Cliente novo){    
      validaEntrada(novo);     
      
      ClienteDao.insereCliente(novo);  
      
      return true;
  }
    
  public static void deletaCliente(int id){
       
      System.out.println("\nDeseja realmente deletar?\n1-SIM\n2-NÃO");
      String opcaoDeleta = teclado.nextLine();
        
      while(true){ 
      
          if(opcaoDeleta.equals("1")!= true && opcaoDeleta.equals("2")!= true){          
              System.out.println("\nEntrada invalida!! Informe novamente:");              
              System.out.println("Deseja realmente deletar?\n1-SIM\n2-NÃO");
              
              opcaoDeleta = teclado.nextLine().trim();            
          }else{          
              break;            
          }        
      } 
        
        if(opcaoDeleta.equals("1")){
          ClienteDao.deletaCliente(id);
        }else{
            Menu.listSecao();  
        }      
  }
  
  
  public static void atualizaCliente(int id, int opcaoAtt){      
      while(true){         
            
            if(opcaoAtt == 1 != true && opcaoAtt == 2 != true && opcaoAtt == 3 != true){
                System.out.println("\nEntrada invalida!! Informe novamente:");
                System.out.println("O que deseja atualizar:\n1-Telefone\n2-Endereço\n3-Tudo");
                opcaoAtt = teclado.nextInt();
            }else{
                break;
            }
        }        
        String telefone="";
        String address="";               
        
        if(opcaoAtt == 1){
            System.out.println("Novo telefone:");     
            telefone = teclado.nextLine().trim();
            
            telefone = validaTellAtt(telefone);                 
            ClienteDao.atualizaCliente(id, opcaoAtt, telefone,address); 
                
        }else if(opcaoAtt == 2){            
            System.out.println("Novo endereço");
            address = teclado.nextLine().trim();
                      
            ClienteDao.atualizaCliente(id, opcaoAtt, telefone,address); 
            
        }else if(opcaoAtt == 3){            
            System.out.println("Novo telefone:");     
            telefone = teclado.nextLine().trim();
            
            telefone = validaTellAtt(telefone);
            
            System.out.println("Novo endereço");
            address = teclado.nextLine().trim();            
      
            ClienteDao.atualizaCliente(id, opcaoAtt, telefone,address); 
        } 
  }  
}
