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
import model.Wallet;
import static perfumeshopDB.DatabaseInfo.DBURL;
import static perfumeshopDB.DatabaseInfo.DRIVERNAME;
import static perfumeshopDB.DatabaseInfo.PASSDB;
import static perfumeshopDB.DatabaseInfo.USERDB;

/**
 *
 * @author Sekiro
 */
public class WalletDAO {
    
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
    
    public int getNumberWallets() {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Wallets";
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
    //
    public void addWallet(Wallet wallet) {
        try (Connection con = getConnect()) {
            String sql = "INSERT INTO [dbo].[Wallets]\n"
                    + "           ([UserName]\n"
                    + "           ,[Balance])\n"
                    + "     VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, wallet.getUserName());
            st.setDouble(2, wallet.getBalance());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Wallet> getWalletBySearchName(String txtSearch) {
        List<Wallet> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Wallets] where UserName LIKE ?";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            //set ?
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Wallet(rs.getString("UserName"), rs.getDouble("Balance")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    // 
    public Wallet getWalletByUserName(String name) {
        try (Connection con = getConnect()) {
            String sql = "SELECT * FROM Wallets WHERE UserName = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                int id = rs.getInt(1);
                String userName = rs.getString(2);
                double balance = rs.getDouble(3);
                Wallet w = new Wallet(userName, balance);
                return w;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    // 
    public  void inputMoney(String userName, double value) {
        try (Connection con = getConnect()) {
            String sql = "UPDATE [dbo].[Wallets] SET [Balance] = (Balance + ?) WHERE UserName = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, value);
            st.setString(2, userName);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    // 
    public void decuctionMoney(String userName, double value ){
        try (Connection con = getConnect()) {
            String sql = "UPDATE [dbo].[Wallets] SET [Balance] = (Balance - ?) WHERE UserName = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, value);
            st.setString(2, userName);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<Wallet> getAll() {
        List<Wallet> list = new ArrayList<>();
        String sql = "select * from Wallets order by Balance desc";
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Wallet(rs.getString("UserName"), rs.getDouble("Balance")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
//    public double getBallance (String useName){
//        
//    }
    
}
