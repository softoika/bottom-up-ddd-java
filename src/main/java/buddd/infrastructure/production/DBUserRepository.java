package buddd.infrastructure.production;

import buddd.domain.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Profile("production")
public class DBUserRepository implements UserRepository {
  private final Config config;

  @Autowired
  public DBUserRepository(Config config) {
    this.config = config;
  }

  @Override
  public User find(UserId id) {
    var sql = "SELECT * FROM t_user WHERE id = ?";
    try (Connection con =
            DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setString(1, id.getValue());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        var userName = rs.getString("username");
        var firstName = rs.getString("firstname");
        var familyName = rs.getString("familyname");
        return new User(id, new UserName(userName), new FullName(firstName, familyName));
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public User find(UserName userName) {
    var sql = "SELECT * FROM t_user WHERE username = ?";
    try (Connection con =
            DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
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
  public List<User> findAll() {
    var sql = "SELECT * FROM t_user";
    try (Connection con =
            DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        Statement stmt = con.createStatement()) {
      ResultSet rs = stmt.executeQuery(sql);
      List<User> results = new ArrayList<>();
      while (rs.next()) {
        var id = rs.getString("id");
        var firstName = rs.getString("firstname");
        var familyName = rs.getString("familyname");
        var userName = rs.getString("username");
        var user =
            new User(new UserId(id), new UserName(userName), new FullName(firstName, familyName));
        results.add(user);
      }
      rs.close();
      return results;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public void save(User user) {
    try (Connection con =
        DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword())) {
      var exists = false;
      var sql = "SELECT * FROM t_user WHERE id = ?";
      try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, user.getId().getValue());
        ResultSet rs = stmt.executeQuery();
        exists = rs.next();
        rs.close();
      }
      if (exists) {
        sql = "UPDATE t_user SET username = ?, firstname = ?, familyname = ? WHERE id = ?";
      } else {
        sql = "INSERT INTO t_user (username, firstname, familyname, id) VALUES(?, ?, ?, ?)";
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

  @Override
  public void remove(User user) {
    var sql = "DELETE FROM t_user WHERE id = ?";
    try (Connection con =
            DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setString(1, user.getId().getValue());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
