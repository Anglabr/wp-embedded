package mk.ukim.finki.wp.embeddedsystemsmanager.controller;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.PlantCareDeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {
    private final PlantCareDeviceService plantCareDeviceService;

    public DashboardController(PlantCareDeviceService plantCareDeviceService) {
        this.plantCareDeviceService = plantCareDeviceService;
    }

    @GetMapping("/hello")
    String mainMenu(Model model){

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        if(devices != null)
            model.addAttribute("devices", devices);

        return "main_menu";
    }

    @PostMapping("/delete")
    String deleteAll(Model model){

        plantCareDeviceService.deleteAll();

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        model.addAttribute("devices", devices);

        return "main_menu";
    }

    @PostMapping("/add")
    String add(Model model){
        plantCareDeviceService.createPlantCareDevice();

        List<PlantCareDevice> devices = plantCareDeviceService.findAll();

        model.addAttribute("devices", devices);
        return "main_menu";
    }

    @PostMapping("/addEntry/{id}")
    String addDataEntry(@PathVariable Long id, @RequestParam Long temperature, @RequestParam Long humidity, @RequestParam Long soilMoisture){
        plantCareDeviceService.addDataEntryById(id, new PlantCareDataEntry(temperature, humidity, soilMoisture));
        return "redirect:/hello";
    }

}
