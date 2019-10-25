package buddd.infrastructure.product;

import buddd.domain.users.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static buddd.infrastructure.product.Config.*;

public class ProductUserRepository implements UserRepository {
  @Override
  public User find(UserName userName) {
    var sql = "SELECT * FROM t_user WHERE username = ?;";
    try (var con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setString(1, userName.getValue());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        var id = rs.getString("id");
        var firstName = rs.getString("firstname");
        var familyName = rs.getString("familyname");
        return new User(new UserId(id), userName, new FullName(firstName, familyName));
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void save(User user) {
    try (var con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
      var exists = false;
      var sql = "SELECT * FROM t_user WHERE id = ?;";
      try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, user.getId().getValue());
        ResultSet rs = stmt.executeQuery();
        exists = rs.next();
        rs.close();
      }
      if (exists) {
        sql = "UPDATE t_user SET username = ?, firstname = ?, familyname = ? WHERE id = ?;";
      } else {
        sql = "INSERT INTO t_user (username, firstname, familyname, id) VALUES(?, ?, ?, ?);";
      }
      try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, user.getUserName().getValue());
        stmt.setString(2, user.getName().getFirstName());
        stmt.setString(3, user.getName().getFamilyName());
        stmt.setString(4, user.getId().getValue());
        stmt.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
