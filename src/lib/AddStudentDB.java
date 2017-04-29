package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
//student_id, s_name, email, department, mobile,address

public class AddStudentDB {

    public static int save(int student_id, String s_name, String email, String department, String mobile, String address,String image) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into student_info(student_id,s_name,email,department, mobile,address,image) values(?,?,?,?,?,?,?)");
            ps.setInt(1, student_id);
            ps.setString(2, s_name);
            ps.setString(3, email);
            ps.setString(4, department);
            ps.setString(5, mobile);
            ps.setString(6, address);
            ps.setString(7, image);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
