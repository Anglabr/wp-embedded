package mk.ukim.finki.wp.embeddedsystemsmanager.controller;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.LightBulbDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.LightBulbDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.LightBulbDeviceService;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.PlantCareDeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashboardController {
    private final PlantCareDeviceService plantCareDeviceService;
    private final LightBulbDeviceService lightBulbDeviceService;

    public DashboardController(PlantCareDeviceService plantCareDeviceService, LightBulbDeviceService lightBulbDeviceService) {
        this.plantCareDeviceService = plantCareDeviceService;
        this.lightBulbDeviceService = lightBulbDeviceService;
    }

    @GetMapping({"/hello", "/"})
    String mainMenu(Model model){

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        if(devices != null)
            model.addAttribute("devices", devices);

        List<LightBulbDevice> lightBulbDevices = lightBulbDeviceService.findAll();

        if (lightBulbDevices != null)
            model.addAttribute("lightBulbDevices", lightBulbDevices);

        return "main_menu";
    }

    @PostMapping("/delete")
    String deleteAll(Model model){

        plantCareDeviceService.deleteAll();

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        model.addAttribute("devices", devices);

        return "redirect:/";
    }

    @PostMapping("/add")
    String add(Model model){
        plantCareDeviceService.createPlantCareDevice();

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        model.addAttribute("devices", devices);
        return "redirect:/";
    }

    @PostMapping("/addEntry/{id}")
    String addDataEntry(@PathVariable Long id, @RequestParam Long temperature, @RequestParam Long humidity, @RequestParam Long soilMoisture){
        plantCareDeviceService.addDataEntryById(id, new PlantCareDataEntry(temperature, humidity, soilMoisture));
        return "redirect:/hello";
    }

    @PostMapping("/addLightBulb")
    String addLightBulbDevice(@RequestParam(required = false) String location){
        lightBulbDeviceService.saveLightBulbDevice(location);

        return "redirect:/hello";
    }

    @PostMapping("/toggle/{id}")
    String toggleLightBulb(@PathVariable Long id){
        lightBulbDeviceService.turnLightBulbOnOff(id);
        return "redirect:/";
    }

    @GetMapping("/view/lightBulb/{id}")
    String viewLightBulb(@PathVariable Long id, Model model){

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        if(devices != null)
            model.addAttribute("devices", devices);

        List<LightBulbDevice> lightBulbDevices = lightBulbDeviceService.findAll();

        if (lightBulbDevices != null)
            model.addAttribute("lightBulbDevices", lightBulbDevices);

        List<LightBulbDataEntry> lightBulbDataEntries = lightBulbDeviceService.findAllDataForLightBulb(id);

        if (lightBulbDataEntries != null)
            model.addAttribute("lightBulbDataEntries", lightBulbDataEntries);

        return "main_menu";
    }

    @RequestMapping("/devices")
    String deviceDetails(@RequestParam Long id, Model model) {
        if (this.plantCareDeviceService.findById(id).isPresent()) {
            PlantCareDevice plantCareDevice = this.plantCareDeviceService.findById(id).get();
            model.addAttribute("device", plantCareDevice);
            return "device_details";
        }
        return "redirect:/devices?error=DeviceNotFound";
    }
}
