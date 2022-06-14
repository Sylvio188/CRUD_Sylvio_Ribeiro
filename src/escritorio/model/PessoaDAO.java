package escritorio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexao conectar = new Conexao();
    Pessoa p = new Pessoa();

    public List listar() {
        List<Pessoa> dados = new ArrayList<>();
        String sql="select * from pessoas";
        
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("select * from pessoas");
            rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setTel(rs.getString(4));
                dados.add(p);
            }
        } catch (Exception e) {
        }
        return dados;
    }
    public int Adicionar(Pessoa per) {  
        int r=0;
        String sql="insert into pessoas(Nome,Email,Telefone)values(?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("insert into pessoas(Nome,Email,Telefone)values(?,?,?)");            
            ps.setString(1,per.getNom());
            ps.setString(2,per.getEmail());
            ps.setString(3,per.getTel());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Atualizar(Pessoa per) {  
        int r=0;
        String sql=" update pessoas set Nome=?,Email=?,Telefone=? where Id=?";        
        try {
            con = conectar.getConnection();
            ps = con.prepareCall(" update pessoas set Nome=?,Email=?,Telefone=? where Id=?");            
            ps.setString(1,per.getNom());
            ps.setString(2,per.getEmail());
            ps.setString(3,per.getTel());
            ps.setInt(4,per.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Excluir(int id){
        int r=0;
        String sql="delete from pessoas where Id="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement("delete from pessoas where Id="+id);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
