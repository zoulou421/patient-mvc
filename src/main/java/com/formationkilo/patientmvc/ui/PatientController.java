package com.formationkilo.patientmvc.ui;


import com.formationkilo.patientmvc.dao.PatientRepository;
import com.formationkilo.patientmvc.dto.Patient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
  // @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/")
    public String patients(){
        return "patients";
    }

  @GetMapping(path = "/home")
  public String listPatients(Model model){
      List<Patient>listePatients = patientRepository.findAll();
      model.addAttribute("listePatients",listePatients);
    return "patients";
  }
  //the same method with pagination
  @GetMapping(path = "/index")
  public String paginationPatients(Model model,
                                   @RequestParam(name="page", defaultValue = "0") int page,
                                   @RequestParam(name="size", defaultValue = "5") int size){
      Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
      model.addAttribute("pagePatients",pagePatients.getContent());
     //total number of pages
      model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
      model.addAttribute("currentPage", page);
      return "patients";
  }

}
