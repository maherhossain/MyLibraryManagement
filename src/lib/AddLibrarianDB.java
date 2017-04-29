package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author maher
 */
public class AddLibrarianDB {

    public static int save(int librarain_id, String name, String email, String password, String address, String city, String mobile) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into librarian(librarain_id,name,email,password,address,city,mobile) values(?,?,?,?,?,?,?)");
            ps.setInt(1, librarain_id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setString(6, city);
            ps.setString(7, mobile);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }  
    
}
