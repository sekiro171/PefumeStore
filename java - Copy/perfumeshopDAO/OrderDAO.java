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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Order;
import model.User;
import static perfumeshopDAO.ProductDAO.getConnect;
import static perfumeshopDB.DatabaseInfo.DBURL;
import static perfumeshopDB.DatabaseInfo.DRIVERNAME;
import static perfumeshopDB.DatabaseInfo.PASSDB;
import static perfumeshopDB.DatabaseInfo.USERDB;

/**
 *
 * @author Sekiro
 */
public class OrderDAO {
    
    public static Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
    
    public int getNumberOrders() {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Orders";
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

    public void addOrder(User cus, Cart cart) {
        ProductDAO pd = new ProductDAO();
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try (Connection con = getConnect()) {

            // add vao bang Order
            String sql1 = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([Date]\n"
                    + "           ,[UserName]\n"
                    + "           ,[TotalMoney]\n"
                    + "           ,[status])\n"
                    + "     VALUES (?,?,?,?)";
            PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setString(1, date);
            st1.setString(2, cus.getUserName());
            st1.setDouble(3, cart.getTotalMoney());
            st1.setInt(4, 0);
            st1.executeUpdate();

            // Lay ra orderID cua Order vua tao
            String sql2 = "SELECT Top 1 [OrderID] FROM [dbo].[Orders] ORDER BY [OrderID] DESC";
            PreparedStatement st2 = con.prepareStatement(sql2);
            ResultSet rs = st2.executeQuery();

            // add thong tin vao bang OrderDetails
            if (rs.next()) {
                int oID = rs.getInt(1);
                for (Item item : cart.getListItems()) {
                    String sql3 = "INSERT INTO [dbo].[OrderDetails]\n"
                            + "           ([OrderID]\n"
                            + "           ,[ProductID]\n"
                            + "           ,[Quantity]\n"
                            + "           ,[UnitPrice]\n"
                            + "           ,[Discount])\n"
                            + "     VALUES (?,?,?,?,?)";
                    PreparedStatement st3 = con.prepareStatement(sql3);
                    st3.setInt(1, oID);
                    st3.setInt(2, item.getProduct().getId());
                    st3.setInt(3, item.getQuantity());
                    st3.setDouble(4, item.getProduct().getPrice());
                    st3.setDouble(5, item.getProduct().getDiscount());
                    st3.executeUpdate();
                    // update quantiy sp
                    pd.updateValueProduct(item.getProduct(), item.getProduct().getId());
                }
            }

            // add vao bang orderDetail
        } catch (Exception e) {
        }
    }

    public double totalMoneyMonth(int month, int year) {
        String sql = "select SUM([TotalMoney]) from [Orders]\r\n"
                + "where MONTH([Date])=? and year([Date])=?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, month);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double totalMoneyWeek(int day, int from, int to, int year, int month) {
        String sql = "";
        if (from > to) {
            sql = " SELECT SUM(TotalMoney)\n"
                    + "				FROM Orders\n"
                    + "				WHERE ((DAY([Date]) >= ? AND MONTH([Date]) = ?) OR (DAY([Date]) <= ? AND MONTH([Date]) = ?)) AND YEAR([Date]) = ? and DATEPART(dw,[Date]) = ?";
        } else {
            sql = "select \n"
                    + "               	SUM(TotalMoney)\n"
                    + "                  from Orders\n"
                    + "               where day([Date]) between ? and ? and month([Date]) = ? and year([Date])= ?  and DATEPART(dw,[Date]) = ?";
        }
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            if (from > to) {
                st.setInt(1, from);
                st.setInt(2, month);
                st.setInt(3, to);
                st.setInt(4, (month + 1));
                st.setInt(5, year);
                st.setInt(6, day);
            } else {
                st.setInt(1, from);
                st.setInt(2, to);
                st.setInt(3, month);
                st.setInt(4, year);
                st.setInt(5, day);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double sumAllMoneyOrder() {
        String sql = "select SUM([TotalMoney]) from Orders";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from Orders order by status asc";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        double a = dao.totalMoneyWeek(6, 27, 3, 2023, 11);
        System.out.println(a);
    }

    public void updateStatus(int id) {
        List<Order> list = new ArrayList<>();
        String sql = "UPDATE [dbo].[Orders] SET [status] = 1 WHERE [OrderID] = ?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
}
