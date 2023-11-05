package com.formationkilo.patientmvc.ui;


import com.formationkilo.patientmvc.dao.PatientRepository;
import com.formationkilo.patientmvc.dto.Patient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
  // @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String patients(){
        return "patients";
    }

  @GetMapping(path = "/")
  public String listPatients(Model model){
      List<Patient>listePatients = patientRepository.findAll();
      model.addAttribute("listePatients",listePatients);
    return "patients";
  }

}
