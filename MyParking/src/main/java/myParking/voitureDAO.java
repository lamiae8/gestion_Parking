package myParking;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class voitureDAO {
    protected Connection cnx;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    private String url = "jdbc:mysql://127.0.0.1:3306/gparking";
    private String user = "root";
    private String password = "";
    protected ResultSet resultSet;

    public voitureDAO() throws SQLException {
        this.cnx = DriverManager.getConnection(this.url, this.user, this.password);
    }

    public  void save(AccesVoiture v) throws SQLException {
        String rq = "insert into voiture (tempsEntrer ,Matricule,idVoiture) values (?,?,?)";
        this.preparedStatement = this.cnx.prepareStatement(rq);
        this.preparedStatement.setTime (1, v.getDateEntree());
        this.preparedStatement.setNString (2, v.getMat());
        this.preparedStatement.setInt(3, v.getId());
        this.preparedStatement.execute();
    }

    public  void update(AccesVoiture v) throws SQLException{
        String rq = "UPDATE voiture SET tempsSorti = ? WHERE idVoiture=" + v.getId();
        this.preparedStatement = this.cnx.prepareStatement(rq);
        this.preparedStatement.setTime (1, v.getDateSortie());
        this.preparedStatement.execute();

    }

}
