package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Maher
 */
public class IssueBookDB {

    public static boolean checkBook(String book_name) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();

            PreparedStatement ps = con.prepareStatement("select * from books where book_name=?");
            ps.setString(1, book_name);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int save(String book_name, int book_id, int student_id, String s_name, String mobile) {
        int status = 0;
        try {

            Connection con = DB.getConnection();
            status = updatebook(book_name);
            if (status > 0) {
                PreparedStatement ps = con.prepareStatement("insert into issue_book(book_name, book_id, student_id, s_name, mobile) values(?,?,?,?,?)");
                ps.setString(1, book_name);
                ps.setInt(2, book_id);
                ps.setInt(3, student_id);
                ps.setString(4, s_name);
                ps.setString(5, mobile);

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

            if (quentity > 0) {
                PreparedStatement ps2 = con.prepareStatement("update books set quentity=?,issued=? where book_name=?");
                ps2.setInt(1, quentity - 1);
                ps2.setInt(2, issued + 1);
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
