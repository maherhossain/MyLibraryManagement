package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Maher
 */
public class BookRequestDB {

    public static int save(String book_name, String author, String categories, String message, String request_by) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into book_request(book_name, author, categories, message, request_by) values(?,?,?,?,?)");
            ps.setString(1, book_name);
            ps.setString(2, author);
            ps.setString(3, categories);
            ps.setString(4, message);
            ps.setString(5, request_by);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
