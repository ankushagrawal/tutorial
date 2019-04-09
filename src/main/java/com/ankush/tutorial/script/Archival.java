package com.ankush.tutorial.script;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Archival {
//    static Database tripDb = new Database("10.85.249.255:3306", "root", "taaptrip",
//            "ekl-transport-trips");
//    static Database loadDb = new Database("10.85.227.147:3306", "root", "taaptrip",
//            "load_manager");
//    static Database containerDb = new Database("10.85.227.147:3306", "root", "taaptrip",
//            "container");
//    static Database requestDb = new Database("10.85.249.255:3306", "root", "taaptrip",
//            "request_manager");

    //prod-slave
    static Database tripDb = new Database("10.85.177.43:3306", "trips_ro", "v42-0cn1-0n-02",
            "ekl_transport_trips_prod");
    static Database loadDb = new Database("10.85.148.188:3306", "load_ro", "loadro2018",
            "load_manager");
//    static Database containerDb = new Database("10.85.227.147:3306", "root", "taaptrip",
//            "container");
    static Database requestDb = new Database("10.85.141.149:3306", "request_ro", "v42-0cn1-0n-02",
            "ekl_transport_request_manager");

    static int maxOlderDays = 16;
    static int minOlderDays = 15;

    public static void main(String[] args) throws Exception {


        printDateRange();
        List<Integer> potentialTripsForDeletion = getEligibleTripsForDeletionFromTripService();
        System.out.println("potentialTripsForDeletion size = " + potentialTripsForDeletion.size());

        //query load to filter trips which cannot be deleted
        if (potentialTripsForDeletion != null && potentialTripsForDeletion.size() > 0) {
            //remove trips by querying trli
            List<Integer> tripsCannotBeDeleted = getTripsCannotBeDeletedForTrli(potentialTripsForDeletion);

            potentialTripsForDeletion.removeAll(tripsCannotBeDeleted);
//            System.out.println("potentialTripsForDeletion after removing trips from trli conditions = " + potentialTripsForDeletion);
            System.out.println("potentialTripsForDeletion size = " + potentialTripsForDeletion.size());


            tripsCannotBeDeleted = getTripsCannotBeDeletedFromLoad(potentialTripsForDeletion);

            potentialTripsForDeletion.removeAll(tripsCannotBeDeleted);
//            System.out.println("potentialTripsForDeletion after removing trips from load conditions = " + potentialTripsForDeletion);
            System.out.println("potentialTripsForDeletion size = " + potentialTripsForDeletion.size());

            List<LoadData> loadDataList = getPotentialLoadDataForDeletion(potentialTripsForDeletion);

            //check SR's if currentLocation == destination For all loads
            if (loadDataList != null && loadDataList.size() > 0) {
                List<String> loadTrackingIds = loadDataList.stream().map(loadData -> loadData.loadTrackingId).collect(Collectors.toList());
                List<String> loadTrackingIdsCannotBeDeleted = getLoadTrackingIdsCannotBeDeletedForNotReachingDestinationInSR(loadTrackingIds);

                System.out.println("loadDataList size = " + loadDataList.size());

                if (loadTrackingIdsCannotBeDeleted != null && loadTrackingIdsCannotBeDeleted.size() > 0) {
                    Iterator<LoadData> it = loadDataList.iterator();
                    while (it.hasNext()) {
                        LoadData loadData = it.next();
                        if (loadTrackingIdsCannotBeDeleted.contains(loadData.loadTrackingId)) {
                            tripsCannotBeDeleted.add(loadData.tripId.intValue());
                            it.remove();
                        }
                    }
                }
                System.out.println("tripsCannotBeDeleted from sr conditions = " + tripsCannotBeDeleted);
                potentialTripsForDeletion.removeAll(tripsCannotBeDeleted);
                System.out.println("potentialTripsForDeletion size = " + potentialTripsForDeletion.size());
                System.out.println("loadDataList size = " + loadDataList.size());

                loadDataList = getPotentialLoadDataForDeletion(potentialTripsForDeletion);

                //get sr id which can be removed
                System.out.println("loadDataList size = " + loadDataList.size());
                loadTrackingIds = loadDataList.stream().map(loadData -> loadData.loadTrackingId).collect(Collectors.toList());
                List<SRData> srDataList = getEligibleSRIdsForDeletion(loadTrackingIds); //this will give container Ids also
                List<Long> eligibleTrlisForDeletion = getEligibleTrLisForDeletion(potentialTripsForDeletion);
            }

        }

    }

    private static void printDateRange() throws Exception {
        String query = "select DATE_SUB(now(), INTERVAL ? DAY) , DATE_SUB(now(), INTERVAL ? DAY)";
        Connection connection = getConnection(tripDb);
        PreparedStatement preparedStatement = createPreparedStatement(query, connection);
        preparedStatement.setInt(1, minOlderDays);
        preparedStatement.setInt(2, maxOlderDays);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        while (resultSet.next()) {
            System.out.println("max date = "+ resultSet.getObject(1));
            System.out.println("min date = "+ resultSet.getObject(2));
        }
    }

    private static List<Long> getEligibleTrLisForDeletion(List<Integer> potentialTripsForDeletion) throws Exception {
        Connection connection = getConnection(requestDb);
        String requestQuery = "select trm.trli_id from trli_trip_mapping trm where trm.trip_id in ";
        StringBuilder parameterBuilder = buildParameterForInClause(potentialTripsForDeletion);
        PreparedStatement preparedStatement = createPreparedStatement(requestQuery + parameterBuilder, connection);
        setParameterForInClause(preparedStatement, potentialTripsForDeletion);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        List<Long> eligibleTrlisForDeletion = new ArrayList<>();
        while (resultSet.next()) {
            eligibleTrlisForDeletion.add(resultSet.getLong(1));
        }
        close(resultSet, preparedStatement, connection);
        System.out.println("eligibleTrlisForDeletion = " + eligibleTrlisForDeletion);
        return eligibleTrlisForDeletion;

    }

    private static List<SRData> getEligibleSRIdsForDeletion(List<String> loadTrackingIds) throws Exception {
        Connection connection = getConnection(requestDb);
        String requestQuery = "select id, container_id from service_request " +
                " where external_id in ";
        StringBuilder parameterBuilder = buildParameterForInClause(loadTrackingIds);
        PreparedStatement preparedStatement = createPreparedStatement(requestQuery + parameterBuilder, connection);
        setParameterForInStringClause(preparedStatement, loadTrackingIds);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        List<SRData> srDataList = new ArrayList<>();
        while (resultSet.next()) {
            SRData srData = new SRData();
            srData.setSrId(resultSet.getLong(1));
            srData.setContainerId(resultSet.getLong(2));
            srDataList.add(srData);
        }
//        System.out.println("srIds = " + srDataList.toString());
        System.out.println("srIds size = " + srDataList.size());
        close(resultSet, preparedStatement, connection);
        return srDataList;
    }

    private static List<String> getLoadTrackingIdsCannotBeDeletedForNotReachingDestinationInSR(List<String> loadTrackingIds) throws Exception {
        Connection connection = getConnection(requestDb);
        String requestQuery = "select external_id from service_request " +
                " where current_location_id != destination_id and external_id in ";
        StringBuilder parameterBuilder = buildParameterForInClause(loadTrackingIds);
        PreparedStatement preparedStatement = createPreparedStatement(requestQuery + parameterBuilder, connection);
        setParameterForInStringClause(preparedStatement, loadTrackingIds);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        List<String> loadTrackingIdsCannotBeDeleted = new ArrayList<>();
        while (resultSet.next()) {
            loadTrackingIdsCannotBeDeleted.add(resultSet.getString(1));
        }
        System.out.println("loadTrackingIdsCannotBeDeleted = " + loadTrackingIdsCannotBeDeleted);
        close(resultSet, preparedStatement, connection);
        return loadTrackingIdsCannotBeDeleted;
    }

    private static List<LoadData> getPotentialLoadDataForDeletion(List<Integer> potentialTripsForDeletion) throws Exception {
        //now get all trips whose load can be potentially deleted - find such S
        Connection connection = getConnection(loadDb);
        String loadQuery = "select lu.load_unit_ext_id, c.id, lu.id, c.trip_id from consignment c, load_unit lu where c.id = lu.consignment_id " +
                " and c.trip_id in ";
        StringBuilder parameterBuilder = buildParameterForInClause(potentialTripsForDeletion);
        PreparedStatement preparedStatement = createPreparedStatement(loadQuery + parameterBuilder, connection);
        setParameterForInClause(preparedStatement, potentialTripsForDeletion);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        List<LoadData> loadDataList = new ArrayList<>();
        while (resultSet.next()) {
            LoadData loadData = new LoadData();
            loadData.setLoadTrackingId(resultSet.getString(1));
            loadData.setConsignmentId(resultSet.getLong(2));
            loadData.setLoadId(resultSet.getLong(3));
            loadData.setTripId(resultSet.getLong(4));
            loadDataList.add(loadData);
        }
        close(resultSet, preparedStatement, connection);
//        System.out.println("loadDataList = " + loadDataList.toString());
        System.out.println("loadDataList size = " + loadDataList.size());
        return loadDataList;
    }

    private static List<Integer> getTripsCannotBeDeletedFromLoad(List<Integer> potentialTripsForDeletion) throws Exception {
        String loadQuery = "select trip_id from consignment c, load_unit lu where c.id = lu.consignment_id and " +
                " (lu.status in ('PICKED') OR c.status in ('OPENED','IN_TRANSIT','CLOSED')) AND c.trip_id in ";
        Connection connection = getConnection(loadDb);
        StringBuilder parameterBuilder = buildParameterForInClause(potentialTripsForDeletion);
        PreparedStatement preparedStatement = createPreparedStatement(loadQuery + parameterBuilder, connection);
        setParameterForInClause(preparedStatement, potentialTripsForDeletion);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        List<Integer> tripsCannotBeDeleted = new ArrayList<>();
        while (resultSet.next()) {
            tripsCannotBeDeleted.add(resultSet.getInt(1));
        }
        close(resultSet, preparedStatement, connection);
        System.out.println("tripsCannotBeDeleted from load conditions = " + tripsCannotBeDeleted);
        return tripsCannotBeDeleted;
    }

    private static List<Integer> getTripsCannotBeDeletedForTrli(List<Integer> potentialTripsForDeletion) throws Exception {
        Connection connection = getConnection(requestDb);
        String requestQuery = "select trm.trip_id from trli_trip_mapping trm, transport_request_line_item trli " +
                " where trm.trli_id = trli.id " +
                " and trli.status in ('CREATED','APPROVED','DISPATCHED','READY_TO_DISPATCH') and trm.trip_id in ";
        StringBuilder parameterBuilder = buildParameterForInClause(potentialTripsForDeletion);
        PreparedStatement preparedStatement = createPreparedStatement(requestQuery + parameterBuilder, connection);
        setParameterForInClause(preparedStatement, potentialTripsForDeletion);
        ResultSet resultSet = null;
        resultSet = executeQuery(preparedStatement, resultSet);
        List<Integer> tripsCannotBeDeleted = new ArrayList<>();
        while (resultSet.next()) {
            tripsCannotBeDeleted.add(resultSet.getInt(1));
        }
        close(resultSet, preparedStatement, connection);
        System.out.println("tripsCannotBeDeleted from request conditions = " + tripsCannotBeDeleted);
        return tripsCannotBeDeleted;
    }

    private static List<Integer> getEligibleTripsForDeletionFromTripService() throws Exception {
        String tripQuery = "select id from trip where status in ('COMPLETED','ABORTED','CANCELLED') AND created_at < " +
                " DATE_SUB(now(), INTERVAL ? DAY) and created_at > DATE_SUB(now(), INTERVAL ? DAY)";
        Connection connection = getConnection(tripDb);
        PreparedStatement preparedStatement = createPreparedStatement(tripQuery, connection);
        preparedStatement.setInt(1, minOlderDays);
        preparedStatement.setInt(2, maxOlderDays);
        ResultSet resultSet = null;
        List<Integer> potentialTripsForDeletion = new ArrayList<>();
        resultSet = executeQuery(preparedStatement, resultSet);
        while (resultSet.next()) {
            potentialTripsForDeletion.add(resultSet.getInt(1));
        }
        close(resultSet, preparedStatement, connection);
        System.out.println("potentialTripsForDeletion = "+ potentialTripsForDeletion);
        return potentialTripsForDeletion;
    }

    private static void setParameterForInClause(PreparedStatement preparedStatement, List<Integer> input) throws Exception {
        for (int i = 1; i < input.size() + 1; i++) {
            preparedStatement.setInt(i, (int) input.get(i - 1));
        }
    }

    private static void setParameterForInStringClause(PreparedStatement preparedStatement, List<String> input) throws Exception {
        for (int i = 1; i < input.size() + 1; i++) {
            preparedStatement.setString(i, (String) input.get(i - 1));
        }
    }

    private static StringBuilder buildParameterForInClause(List input) {
        StringBuilder parameterBuilder = new StringBuilder();
        parameterBuilder.append(" (");
        for (int i = 0; i < input.size(); i++) {
            parameterBuilder.append("?");
            if (input.size() > i + 1) {
                parameterBuilder.append(",");
            }
        }
        parameterBuilder.append(")");
        return parameterBuilder;
    }

    private static PreparedStatement createPreparedStatement(String sql, Connection connection) throws Exception{
        return connection.prepareStatement(sql);
    }

    private static Connection getConnection(Database database) throws Exception {

        String url = "jdbc:mysql://" + database.host + "/";
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url+database.dbName, database.username, database.password);

    }



    private static ResultSet executeQuery(PreparedStatement preparedStatement, ResultSet resultSet) throws Exception {
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    private static void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }

    public static class Database {
        public String host;
        public String username;
        public String password;
        public String dbName;

        public Database(String host, String username, String password, String dbName) {
            this.host = host;
            this.username = username;
            this.password = password;
            this.dbName = dbName;
        }
    }

    public static class LoadData {
        public String loadTrackingId;
        public Long loadId;
        public Long consignmentId;
        public Long tripId;

        public LoadData(){

        }


        public void setLoadTrackingId(String loadTrackingId) {
            this.loadTrackingId = loadTrackingId;
        }

        public void setLoadId(Long loadId) {
            this.loadId = loadId;
        }

        public void setConsignmentId(Long consignmentId) {
            this.consignmentId = consignmentId;
        }

        public void setTripId(Long tripId) {
            this.tripId = tripId;
        }

        public String toString(){
            StringBuilder stringBuilder = new StringBuilder("{ ");
            stringBuilder.append(" loadTrackingId = " + loadTrackingId);
            stringBuilder.append(", consignmentId = " + consignmentId);
            stringBuilder.append(", loadId = " + loadId);
            stringBuilder.append(", tripId = " + tripId);
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    public static class SRData {
        public void setSrId(Long srId) {
            this.srId = srId;
        }

        public void setContainerId(Long containerId) {
            this.containerId = containerId;
        }

        public Long srId;
        public Long containerId;

        public SRData(){}

        public String toString(){
            StringBuilder stringBuilder = new StringBuilder(" { ");
            stringBuilder.append(" srId = " + srId);
            stringBuilder.append(", containerId = " + containerId);
            stringBuilder.append(" } ");
            return stringBuilder.toString();
        }
    }
}
