package escritorio.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Conexao {
    String url="jdbc:mysql://localhost:3306/cadastro";
    String user="root",pass="";    
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro","root","");
        } catch (Exception e) {            
        }
        return con;
    }
    public static void main (String[] args){
        Conexao cn=new Conexao();
        Statement st;
        ResultSet sr;
        
        try{
            
            st=cn.con.createStatement();
            ResultSet rs = st.executeQuery("select * from cadastro ");
            while(rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("user")+" "+rs.getString("dni"));
            }
        cn.con.close();
            }catch (Exception e){
        
    }
    }
}

