package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author maher
 */
public class DeleteLibrarianDB {

    public static int delete(int librarain_id) {//,String name
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from librarian where librarain_id=?");
            ps.setInt(1, librarain_id);
            //ps.setString(2, name);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
