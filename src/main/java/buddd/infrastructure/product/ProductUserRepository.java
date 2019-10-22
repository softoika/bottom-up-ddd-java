package buddd.infrastructure.product;

import buddd.domain.users.FullName;
import buddd.domain.users.User;
import buddd.domain.users.UserId;
import buddd.domain.users.UserName;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductUserRepository {
  public User find(UserName userName) {
    try (var con =
        DriverManager.getConnection(Config.CONNECTION_URL, Config.USER, Config.PASSWORD)) {
      String sql = "SELECT * FROM t_user WHERE username = ?;";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, userName.getValue());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        var id = rs.getString("id");
        var firstName = rs.getString("firstname");
        var familyName = rs.getString("familyname");
        return new User(new UserId(id), userName, new FullName(firstName, familyName));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void save(User user) {
    try (var con =
        DriverManager.getConnection(Config.CONNECTION_URL, Config.USER, Config.PASSWORD)) {
      String sql = "SELECT * FROM t_user WHERE id = ?;";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, user.getId().getValue());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        sql = "UPDATE t_user SET username = ?, firstname = ?, familyname = ? WHERE id = ?;";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, user.getUserName().getValue());
        stmt.setString(2, user.getName().getFirstName());
        stmt.setString(3, user.getName().getFamilyName());
        stmt.setString(4, user.getId().getValue());
      } else {
        sql = "INSERT INTO t_user VALUES(?, ?, ?, ?);";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, user.getId().getValue());
        stmt.setString(2, user.getUserName().getValue());
        stmt.setString(3, user.getName().getFirstName());
        stmt.setString(4, user.getName().getFamilyName());
      }
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
