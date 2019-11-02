/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import thaaht.heroSightings.daos.CharacterDao;
import thaaht.heroSightings.daos.SuperPowerDao;
import thaaht.heroSightings.daos.sightingDAO;
import thaaht.heroSightings.dtos.Character;
import thaaht.heroSightings.dtos.Location;
import thaaht.heroSightings.dtos.Organization;
import thaaht.heroSightings.dtos.Sighting;
import thaaht.heroSightings.dtos.SuperPower;

/**
 *
 * @author Bill Gates
 */
@Controller
public class CharacterController {
    @Autowired
    CharacterDao characterDao;
    
    @Autowired
    SuperPowerDao superPowerDao;
    
    @Autowired
    LocationDao locationDao;
    
    //@Autowired
    //OrganizationDao organizationDao;
    
    @Autowired
    sightingDAO sightingDao;
    
    @GetMapping("characters")
    public String displayCharacters (Model model){
        List<Character> characters = characterDao.getAllCharacters();
        model.addAttribute("characters", characters);
        
        List<SuperPower> superpowers = superPowerDao.getAllSuperPowers();
        model.addAttribute("superpowers", superpowers);
        
        //List<Location> locations = locationDao.getAllLocations();
        //model.addAttribute("locations", locations);
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("sightings", sightings);
        
        return "characters";
    }
    
    @PostMapping("addCharacter")
    public String addCharacter(Character character, HttpServletRequest request) {
        String [] superPowerIDs = request.getParameterValues ("superPowerID");
        String [] organizationIDs = request.getParameterValues("organizationID");
       
        List<SuperPower> superPowers = new ArrayList<>();
        for(String superPowerID: superPowerIDs){
            superPowers.add(superPowerDao.getSuperPowerByID (Integer.parseInt(superPowerID)));
        }
        /*
        List<Organization> organizations = new ArrayList<>();
        for(String organizationID: organizationIDs){
            organizations.add(organizationDao.getOrganizationByID (Integer.parseInt(organizationID)));
        }
        */
        character.setSuperPowers(superPowers);
        //character.setOrganizations(organizations);
        characterDao.addCharacter(character);
        
        return "redirect:/courses";
    }
    
    @GetMapping("characterDetail")
    public String characterDetail (Integer id, Model model){
        Character character = characterDao.getCharacterById(id);
        model.addAttribute("character", character);
        return "characterDetail";
    }
    
    @GetMapping ("deleteCharacter")
    public String deleteCharacter (Integer id){
        characterDao.deleteCharacterById (id);
        return "redirect:/characters";
    }
    
    @GetMapping ("editCharacter")
    public String editCharacter(Integer id, Model model){
        Character character = characterDao.getCharacterById(id);
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers ();
        //List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute ("character", character);
        model.addAttribute("superPowers", superPowers);
        //model.addAttribute("organizations", organizations);
        return "editCharacter";
    }
    
    @PostMapping ("editCharacter")
    public String performEditCharacter (Character character, HttpServletRequest request){
        String [] superPowerIDs = request.getParameterValues ("superPowerID");
        String [] organizationIDs = request.getParameterValues("organizationID");
       
        List<SuperPower> superPowers = new ArrayList<>();
        for(String superPowerID: superPowerIDs){
            superPowers.add(superPowerDao.getSuperPowerByID (Integer.parseInt(superPowerID)));
        }
        /*
        List<Organization> organizations = new ArrayList<>();
        for(String organizationID: organizationIDs){
            organizations.add(organizationDao.getOrganizationByID (Integer.parseInt(organizationID)));
        }
        */
        character.setSuperPowers(superPowers);
        //character.setOrganizations(organizations);
        characterDao.updateCharacter(character);
        
        return "redirect:/courses"; 
    }
}
