package com.romao.demo.model;

import com.j256.ormlite.support.ConnectionSource;
import com.romao.demo.model.entities.Location;
import com.romao.demo.network.entities.LocationResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDao extends BaseDao<Location, Integer> {
    public LocationDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Location.class);
    }

    private static final String INSERT_LOCATION_QUERY = "INSERT INTO " + Location.TABLE_NAME + " ("
            + Location.ID_COLUMN + ", "
            + Location.NAME_COLUMN + ") "
            + "VALUES (?, ?)";

    public void saveLocation(LocationResponse locationResponse) throws SQLException {
        deleteById(locationResponse.id);
        updateRaw(INSERT_LOCATION_QUERY,
                String.valueOf(locationResponse.id),
                locationResponse.name);
    }

    public void saveAll(List<LocationResponse> locations) {
        try {
            callBatchTasks(() -> {
                cleanData();
                for (LocationResponse locationResponse : locations) {
                    saveLocation(locationResponse);
                }

                return null;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getAll() {
        try {
            return queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
