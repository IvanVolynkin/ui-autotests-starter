package ru.vanjo.qa.uiautotestsstarter.helpers.database.postgresql;

import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresqlDatabaseClient {

    private final ConnectionSettings connectionSettings;

    public PostgresqlDatabaseClient(ConnectionSettings connectionSettings) {
        this.connectionSettings = connectionSettings;
    }

    public List<Map<String, String>> executeSql(String query, int maxRows) {
        var result = executeQuery(query, maxRows);
        var resultList = new ArrayList<Map<String, String>>();
        try {
            var resultMeta = result.getMetaData();
            while (result.next()) {
                var valuesMap = new HashMap<String, String>();
                for (int i = 1; i < resultMeta.getColumnCount() + 1; i++) {
                    valuesMap.put(resultMeta.getColumnName(i), result.getString(i));
                }
                resultList.add(valuesMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(result);
        }
        return resultList;
    }

    private ResultSet executeQuery(String query, int maxRows) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(connectionSettings.url(),
                    connectionSettings.userName(),
                    connectionSettings.userPassword());
            statement = connection.createStatement();
            statement.setMaxRows(maxRows);
            return statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return null;
    }
}
