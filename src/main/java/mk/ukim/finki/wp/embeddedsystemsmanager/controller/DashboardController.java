package mk.ukim.finki.wp.embeddedsystemsmanager.controller;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.repository.PlantCareDeviceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class DashboardController {

    private final PlantCareDeviceRepository plantCareDeviceRepository;

    public DashboardController(PlantCareDeviceRepository plantCareDeviceRepository) {
        this.plantCareDeviceRepository = plantCareDeviceRepository;
    }

    @GetMapping("/hello")
    String mainMenu(Model model){

        List<PlantCareDevice> devices = plantCareDeviceRepository.findAll();

        model.addAttribute("devices", devices);

        return "main_menu";
    }

    @PostMapping("/delete")
    String deleteAll(Model model){

        plantCareDeviceRepository.deleteAll();

        List<PlantCareDevice> devices = plantCareDeviceRepository.findAll();

        model.addAttribute("devices", devices);

        return "main_menu";
    }

    @PostMapping("/add")
    String add(Model model){

        PlantCareDevice plantCareDevice = new PlantCareDevice();

        plantCareDeviceRepository.save(plantCareDevice);

        List<PlantCareDevice> devices = plantCareDeviceRepository.findAll();

        model.addAttribute("devices", devices);
        return "main_menu";
    }

}