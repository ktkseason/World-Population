package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ReRankWorldRankTestIT {
    Connection conn;
    ReRankWorldRank reRankWorldRank;

    @BeforeEach
    void setUp() {
        conn = DBConnect.getConn();
    }

    @Test
    void operate() throws SQLException {
        LinkedList<Integer> before = new LinkedList<>();
        PreparedStatement selectStatement = conn.prepareStatement("SELECT world_rank FROM population");
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            before.add(resultSet.getInt("world_rank"));
        }
        new ReRankWorldRank();
        LinkedList<Integer> after = new LinkedList<>();
        selectStatement = conn.prepareStatement("SELECT world_rank FROM population");
        resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            after.add(resultSet.getInt("world_rank"));
        }
        assertFalse(before == after);
    }
}