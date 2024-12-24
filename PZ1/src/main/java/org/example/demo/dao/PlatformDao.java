package org.example.demo.dao;


import org.example.demo.entity.Platform;

import java.util.List;

public interface PlatformDao {
    List<Platform> selectAll();

    Platform selectPlatformById(int platformId);

    void insertIntoPlatform(String platformName);

    void updateNameById(Platform platform);

    void deleteFromPlatformById(int platformId);
}
