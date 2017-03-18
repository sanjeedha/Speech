package com.example.sanjeedha.speech;

/**
 * Created by sanjeedha on 11/14/16.
 */

public class Buildings {
    private int _id;
    private String _buildingname;

    public Buildings(String buildingName) {
        this._buildingname = buildingName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_buildingname() {
        return _buildingname;
    }

    public void set_buildingname(String _buildingname) {
        this._buildingname = _buildingname;
    }
}
