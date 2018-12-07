
package autopark.DAO;

import autopark.Business.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsuario implements CRUD<Usuario>{
    private static Conexion objConn = Conexion.InstanciaConn();
    ResultSet rs;
    
    
    public boolean insert(Usuario x) {
        String query = "INSERT INTO USUARIO(USERNAME, PASSWORD) VALUES (?,?);";
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(query);
            ps.setString(1, x.getUsername());
            ps.setString(2, x.getPassword());
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    public boolean update(Usuario x) {
        String query = "UPDATE USUARIO SET PASSWORD = ? WHERE IDUSUARIO = ?;";
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(query);
            ps.setString(1, x.getPassword());
            ps.setInt(2, x.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    public boolean delete(int x) {
        String query = "DELETE FROM USUARIO WHERE IDUSUARIO = ?;";
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(query);
            ps.setInt(1, x);

            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    public ArrayList<Usuario> select() {
        String query = "SELECT * FROM USUARIO;";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement ps = objConn.getConn().prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuarios.add(new Usuario(rs.getInt("idUsuario"), rs.getString("username"), rs.getString("password")));
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
