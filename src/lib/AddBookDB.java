package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBookDB {

    public static int save(String book_id, String book_name, String author, String publisher, int quentity, String catagories) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into books(book_id,book_name,author,publisher,quentity,catagories) values(?,?,?,?,?,?)");
            ps.setString(1, book_id);
            ps.setString(2, book_name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quentity);
            ps.setString(6, catagories);
           // ps.setString(7, store_date);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
