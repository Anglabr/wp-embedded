package mk.ukim.finki.wp.embeddedsystemsmanager.controller;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.LightBulbDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.PlantCareDevice;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.LightBulbDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.data_entry.PlantCareDataEntry;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.LightBulbDeviceService;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.PlantCareDeviceService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Controller
public class DashboardController {
    private final PlantCareDeviceService plantCareDeviceService;
    private final LightBulbDeviceService lightBulbDeviceService;

    private String lbd_subscription = "";

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

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/logout")
    String logout() {
        return "redirect:/login";
    }

    @PostMapping("/access_denied")
    String accessDenied() {
        return "access_denied";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String deleteAll(){
        plantCareDeviceService.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String add(){
        plantCareDeviceService.createPlantCareDevice();
        return "redirect:/";
    }

    @PostMapping("/addEntry/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String addDataEntry(@PathVariable Long id, @RequestParam Long temperature, @RequestParam Long humidity, @RequestParam Long soilMoisture){
        plantCareDeviceService.addDataEntryById(id, temperature, humidity, soilMoisture);
        return "redirect:/details/plantcare/" + id;
    }

    @PostMapping("/addLightBulb")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String addLightBulbDevice(@RequestParam(required = false) String location){
        lightBulbDeviceService.saveLightBulbDevice(location);
        return "redirect:/";
    }

    @PostMapping("/deleteLightBulb")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String deleteAllLightBulbDevices() {
        lightBulbDeviceService.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    String toggleLightBulb(@PathVariable Long id){
        if (lightBulbDeviceService.findLightBulbDeviceById(id).isPresent()) {
            lightBulbDeviceService.turnLightBulbOnOff(id);
            lbd_subscription = String.format("%d %s", id, lightBulbDeviceService.findLightBulbDeviceById(id).get().getTurnedOn() ? "On" : "Off");
            return "redirect:/details/lightbulbs/" + id;
        }
        return "redirect:/devices?error=DeviceNotFound";
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

    @GetMapping(value="/lightbulb/stream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> lightBulbStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> lbd_subscription)
                .distinctUntilChanged();
    }

    @GetMapping("/details/plantcare/{id}")
    String deviceDetails(@PathVariable Long id, Model model) {
        if (this.plantCareDeviceService.findById(id).isPresent()) {
            PlantCareDevice plantCareDevice = this.plantCareDeviceService.findById(id).get();
            model.addAttribute("device", plantCareDevice);

            List<PlantCareDataEntry> plantCareDataEntries = plantCareDeviceService.getAllDataEntriesById(id);
            model.addAttribute("entries", plantCareDataEntries);
            return "device_details";
        }
        return "redirect:/devices?error=DeviceNotFound";
    }

    @GetMapping("/details/lightbulbs/{id}")
    String lightbulbDetails(@PathVariable Long id, Model model){
        if (this.lightBulbDeviceService.findLightBulbDeviceById(id).isPresent()){
            LightBulbDevice lightBulbDevice = this.lightBulbDeviceService.findLightBulbDeviceById(id).get();

            model.addAttribute("device", lightBulbDevice);

            List<LightBulbDataEntry> lightBulbDataEntries = lightBulbDeviceService.findAllDataForLightBulb(id);

            model.addAttribute("entries", lightBulbDataEntries);
            return "lightbulb_details";
        }
        return "redirect:/devices?error=DeviceNotFound";
    }

}
