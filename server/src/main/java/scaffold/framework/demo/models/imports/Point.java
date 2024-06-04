package scaffold.framework.demo.models.imports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import orm.DynamicORM;
import orm.annotations.Ignore;

public class Point extends DynamicORM<Point> {

    @Ignore
    @CsvBindByName(column = "classement")
    String classement;

    @Ignore
    @CsvBindByName(column = "points")
    String points;

    String id;

    Integer rank;

    Integer point;

    public static void insertAll(Connection connection, List<Point> importedDataList) throws Exception {

        for (Point point : importedDataList) {
            point.setAll();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into point (rank,point) values(?,?) on conflict do nothing");
            preparedStatement.setInt(1, point.getRank());
            preparedStatement.setInt(2, point.getPoint());

            preparedStatement.executeUpdate();
        }
    }

    public void setAll() {
        setRank(Integer.parseInt(getClassement()));
        setPoint(Integer.parseInt(getPoints()));
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

}
