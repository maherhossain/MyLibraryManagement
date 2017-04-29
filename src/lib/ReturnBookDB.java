package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Maher
 */
public class ReturnBookDB {
    
    
    public static int save(int book_id, String book_name, int student_id) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into return_info(book_id, book_name, student_id) values(?,?,?)");
            ps.setInt(1, book_id);
            ps.setString(2, book_name);
            ps.setInt(3, student_id);
           
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    } 
     
    public static int delete(String book_name, int student_id) {
        int status = 0;
        try {
            Connection con = DB.getConnection();

            status = updatebook(book_name);//updating quantity and issue

            if (status > 0) {
                PreparedStatement ps = con.prepareStatement("delete from issue_book where book_name=? and student_id=?");
                ps.setString(1, book_name);
                // ps.setString(2, book_name);
                ps.setInt(2, student_id);

                status = ps.executeUpdate();
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int updatebook(String book_name) {
        int status = 0;
        int quentity = 0, issued = 0;
        try {
            Connection con = DB.getConnection();

            PreparedStatement ps = con.prepareStatement("select quentity,issued from books where book_name=?");
            ps.setString(1, book_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quentity = rs.getInt("quentity");
                issued = rs.getInt("issued");
            }

            if (issued > 0) {
                PreparedStatement ps2 = con.prepareStatement("update books set quentity=?,issued=? where book_name=?");
                ps2.setInt(1, quentity + 1);
                ps2.setInt(2, issued - 1);
                ps2.setString(3, book_name);

                status = ps2.executeUpdate();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
