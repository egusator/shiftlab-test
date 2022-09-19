package com.example.shiftlabtest.controller;


import com.example.shiftlabtest.repository.model.*;
import com.example.shiftlabtest.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping ("api")
public class ShopController {

    private ShopService shopService;
    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("desktop/get-all")
    public List<Desktop> getAllDesktops() {

        return shopService.getAllDesktops();
    }

    @GetMapping("monitor/get-all")
    public List<Monitor> getAllMonitors() {
        return shopService.getAllMonitors();
    }

    @GetMapping("hard-disk/get-all")
    public List<HardDisk> getAllHardDisks() {
        return shopService.getAllHardDisks();
    }

    @GetMapping("laptop/get-all")
    public List<Laptop> getAllLaptops() {
        return shopService.getAllLaptops();
    }

    @PostMapping("desktop/add-new")
    public void addDesktop (@RequestBody Desktop desktop) {
        shopService.addDesktop(desktop.getSerialNumber(), desktop.getPrice(), desktop.getQuantityInStock(),
                        desktop.getManufacturerName(),desktop.getFormFactor());
    }

    @PostMapping("laptop/add-new")
    public void addLaptop (@RequestBody Laptop laptop) {
        shopService.addLaptop(laptop.getSerialNumber(), laptop.getPrice(), laptop.getQuantityInStock(),
                laptop.getManufacturerName(),laptop.getSize());
    }

    @PostMapping("monitor/add-new")
    public void addMonitor (@RequestBody Monitor monitor) {
        shopService.addMonitor(monitor.getSerialNumber(), monitor.getPrice(), monitor.getQuantityInStock(),
                monitor.getManufacturerName(),monitor.getDiagonal());
    }

    @PostMapping("hard-disk/add-new")
    public void addHardDisk (@RequestBody HardDisk hardDisk) {
        shopService.addHardDisk(hardDisk.getSerialNumber(), hardDisk.getPrice(), hardDisk.getQuantityInStock(),
                hardDisk.getManufacturerName(),hardDisk.getCapacity());
    }

    @PostMapping("common/delete")
    public void deleteDevice (@RequestParam int deviceId) {
        shopService.deleteDevice(deviceId);
    }

    @PostMapping ("common/set-quantity")
    public void setDeviceQuantity (@RequestParam int id, @RequestParam int quantity) {
        shopService.setDeviceQuantity(id, quantity);
    }


    @PostMapping ("common/increase-quantity")
    public void increaseDeviceQuantity (@RequestParam int id, @RequestParam int amount) {
        shopService.increaseDeviceQuantity(id, amount);
    }
    @PostMapping ("common/decrease-quantity")
    public void decreaseDeviceQuantity (@RequestParam int id, @RequestParam int amount) {
        shopService.decreaseDeviceQuantity(id, amount);
    }
    @PostMapping("common/set-price")
    public void setDevicePrice(@RequestParam int id, @RequestParam BigDecimal price) {
        shopService.setDevicePrice(id, price);
    }

    @PostMapping("common/set-serial-number")
    public void setDeviceSerialNumber(@RequestParam int id, @RequestParam String serialNumber) {
        shopService.setDeviceSerialNumber(id, serialNumber);
    }

    @GetMapping ("common/get-by-id")
    public AbstractDevice getDeviceById(@RequestParam int id) {
        return shopService.getDevice(id);
    }
}
