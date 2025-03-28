/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfumeshopDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Spending;
import model.User;
import static perfumeshopDAO.SupplierDAO.getConnect;
import static perfumeshopDB.DatabaseInfo.DBURL;
import static perfumeshopDB.DatabaseInfo.DRIVERNAME;
import static perfumeshopDB.DatabaseInfo.PASSDB;
import static perfumeshopDB.DatabaseInfo.USERDB;

/**
 *
 * @author Sekiro
 */
public class UserDAO {

    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public User check(String username, String password) {
        String sql = "SELECT * FROM Users WHERE userName = ? and password = ? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("userName"), rs.getString("fullName"), rs.getString("password"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("Image"), rs.getString("BirthDay"), rs.getInt("roleID"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int checkAccountAdmin(String userName) {
        String sql = "select  from Users where [userName]=? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "select * from Users WHERE [status] = 1 order by roleId asc";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("userName"), rs.getString("fullName"), rs.getString("password"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("Image"), rs.getString("BirthDay"), rs.getInt("roleID")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    

    public boolean checkUserNameDuplicate(String username) {
        String sql = "SELECT * FROM Users WHERE userName = ? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("userName"), rs.getString("fullName"), rs.getString("password"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("Image"), rs.getString("BirthDay"), rs.getInt("roleID"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
   /**
 * Checks if an email already exists in the database among active users
 * @param email The email to check
 * @return true if the email exists, false otherwise
 */
public boolean isEmailExist(String email) {
    String sql = "SELECT 1 FROM Users WHERE Email = ? AND [status] = 1";
    try (Connection con = getConnect()) {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        return rs.next(); // Returns true if email exists
    } catch (SQLException e) {
        System.out.println("Error checking email existence: " + e);
    }
    return false;
}

    public void updateImage(String image, String userName) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET \n"
                + "      [Image] = ?\n"
                + " WHERE userName = ? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, image);
            st.setString(2, userName);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(String name, String address, String phone, String email, String dob, String userName) {
        String sql = "UPDATE [dbo].[Users] SET \n";
        if (name != null) {
            sql += " [fullName] = " + "?";
        }
        if (address != null) {
            sql += ", [address] =" + "?";
        }
        sql += ", [phone] =" + "?";
        sql += ", [email] =" + "?";
        sql += ", [BirthDay] =" + "?";
        sql += " WHERE userName = ?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, address);
            st.setString(3, phone);
            st.setString(4, email);
            st.setString(5, dob);
            st.setString(6, userName);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public User getUserByUserName(String userName) {
        String sql = "SELECT * FROM [dbo].[Users] where UserName=? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            //set ?
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            //1
            if (rs.next()) {
                User u = new User(rs.getString("userName"), rs.getString("fullName"), rs.getString("password"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("Image"), rs.getString("BirthDay"), rs.getInt("roleID"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // 
    public int getNumberUsers() {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Users";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (Exception e) {
        }
        return 1;
    }

    public void insert(User c) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([UserName]\n"
                + "           ,[FullName]\n"
                + "           ,[Password]\n"
                + "           ,[RoleID]\n"
                + "           ,[Image]\n"
                + "           ,[Email]\n"
                + "           ,[BirthDay]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[status])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, c.getUserName());
            st.setString(2, c.getFullName());
            st.setString(3, c.getPassword());
            st.setInt(4, c.getRoleID());
            st.setString(5, "images/users/user.png");
            st.setString(6, c.getEmail());
            st.setString(7, c.getBirthdate());
            st.setString(8, "No information");
            st.setString(9, c.getPhone());
            st.setInt(10, 1);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int countAllUser() {
        String sql = "select count(*) from Users where [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void insertUser(String UserName, String FullName, String Password,
            int RoleID, String Email, String BirthDay, String Phone) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([UserName]\n"
                + "           ,[FullName]\n"
                + "           ,[Password]\n"
                + "           ,[Image]\n"
                + "           ,[RoleID]\n"
                + "           ,[Email]\n"
                + "           ,[BirthDay]\n"
                + "           ,[Phone]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, UserName);
            st.setString(2, FullName);
            st.setString(3, Password);
            st.setString(4, "images/users/user.png");
            st.setInt(5, RoleID);
            st.setString(6, Email);
            st.setString(7, BirthDay);
            st.setString(8, Phone);
            st.setInt(9, 1);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteUser(String username) {
        String sql = "update Users set status = 0 where UserName= ?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        UserDAO p = new UserDAO();
        p.insertUser("duc", "thanh", "123",
                2, "thanh@gmail.com", "2003-09-08", "4012412341");
        List<User> list = p.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getFullName());
        }
    }

    public List<Spending> getTop5Customers() {
        List<Spending> list = new ArrayList<>();
        String sql = "select top(5) u.*, sum(TotalMoney) as TotalMoney from Orders o inner join Users u on o.UserName = u.UserName and u.status = 1 group by u.Address, "
                + "u.BirthDay, u.Email, u.FullName, u.UserName, u.Password, u.Image, u.RoleID, u.UserID, u.Phone, u.status\n" + " order by TotalMoney desc";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("userName"), rs.getString("fullName"), rs.getString("password"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("Image"),
                        rs.getString("BirthDay"), rs.getInt("roleID"));
                list.add(new Spending(user, rs.getDouble("TotalMoney")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<User> getUsersBySearchName(String txtSearch) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Users] where UserName LIKE ? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            //set ?
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("userName"), rs.getString("fullName"), rs.getString("password"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("Image"), rs.getString("BirthDay"), rs.getInt("roleID")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void changePassword(User s) {
        String sql = "Update Users set password = ? where username = ? and [status] = 1";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s.getPassword());
            st.setString(2, s.getUserName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    

    // 
    public String getUserNameByEmail(String email) {
        String sql = "SELECT Top 1 UserName FROM [dbo].[Users] WHERE Email = ?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            //set ?
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                return name;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public boolean isPhoneExist(String phone) {
    String sql = "SELECT 1 FROM Users WHERE Phone = ? AND [status] = 1";
    try (Connection con = getConnect()) {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, phone);
        ResultSet rs = st.executeQuery();
        return rs.next(); // Returns true if phone exists
    } catch (SQLException e) {
        System.out.println("Error checking phone existence: " + e);
    }
    return false;
}

    //
    public void updatePassByUserName(String pass, String username) {
        String sql = "update Users set Password = ? where UserName= ?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, username);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
}
