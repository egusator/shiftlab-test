package com.example.shiftlabtest.repository;

import com.example.shiftlabtest.repository.model.*;

import java.util.List;

public interface DesktopRepository {

    public void addDesktop();

    public void deleteDesktopById();

    public void setDesktopQuantityById();

    public void addToDesktopQuantityById();

    public void takeFromDesktopQuantityById();

    public void setDesktopPriceById();

    public void setDesktopSerialNumberById();

    public void setHardDriveCapacityById();

    public void setLaptopSizeById();

    public void setMonitorDiagonalById();

    public void setDesktopFormFactor();

    public List<Desktop> getAllDesktops();

    public List<Laptop> getAllLaptops();

    public List<HardDisk> getAllHardDisks();

    public List<Monitor> getAllMonitors();

}
