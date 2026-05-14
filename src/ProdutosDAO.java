import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    
    //autoincrement de id faz  a contagem começar de 2 e 2 é 1
    public void cadastrarProduto(ProdutosDTO p) {
        
        conn = new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos (id, nome, valor, status) VALUES (DEFAULT, ?, ?, ?)";

        try {
           
            prep = conn.prepareStatement(sql);

       
          
            prep.setString(1, p.getNome());
            prep.setInt(2, p.getValor());
            prep.setString(3, p.getStatus());

          
            prep.execute();
            prep.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
               String sql = "SELECT * FROM produtos";
               conn = new conectaDAO().connectDB();
    
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
               
               
         try {
           
        
        

        prep=conn.prepareStatement(sql);
        
        ResultSet rs=prep.executeQuery();
        
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            
       
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            
            listagem.add(produto);
        }
        
        
         } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
        }
        
         return listagem;
    
    }
    
    
    
   // Operação de venderProduto() em ProdutosDAO 
       //     – a função deve atualizar o status de um produto para “Vendido”.
    public void venderProduto(int id){
     conn = new conectaDAO().connectDB();
    
     String sql = "UPDATE  produtos  SET status='Vendido' WHERE id=?";
     
    try {
    
            prep = conn.prepareStatement(sql);
    prep.setInt(1, id);
       
     

          
            prep.execute();
            prep.close();
            
            JOptionPane.showMessageDialog(null, "Produto vendido  !");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + erro.getMessage());
        }
     
     
    }
    
    
    
    
    
}