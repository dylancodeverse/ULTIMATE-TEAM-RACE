package scaffold.framework.demo.models.course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Classementparequipetous extends Classementparequipeavecpointparcategorie {

    @Override
    public Classementparequipeavecpointparcategorie[] select(Connection connection, boolean isTransactional)
            throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Classementparequipetous");
        ArrayList<Classementparequipeavecpointparcategorie> list = new ArrayList<>();
        while (resultSet.next()) {
            Classementparequipeavecpointparcategorie c = new Classementparequipetous();
            c.setCategorie("tous");
            c.setEquipe(resultSet.getString("equipe"));
            c.setPoint(resultSet.getBigDecimal("point"));
            c.setRank(resultSet.getLong("rank"));
            System.out.println(c.getPoint());

            list.add(c);
        }
        return list.toArray(new Classementparequipeavecpointparcategorie[list.size()]);

    }

}
