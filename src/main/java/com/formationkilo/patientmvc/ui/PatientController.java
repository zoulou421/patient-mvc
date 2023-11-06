package com.formationkilo.patientmvc.ui;


import com.formationkilo.patientmvc.dao.PatientRepository;
import com.formationkilo.patientmvc.dto.Patient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
  // @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/")
    public String patients(){

        //return "patients";
        return "redirect:/index";
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
                                   @RequestParam(name="size", defaultValue = "5") int size,
                                   @RequestParam(name="keyword", defaultValue = "") String keyword ){
    //  Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
      Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
      model.addAttribute("pagePatients",pagePatients.getContent());
     //total number of pages
      model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
      model.addAttribute("currentPage", page);
      model.addAttribute("keyword", keyword);
      return "patients";
  }

    @GetMapping(path = "/delete")
  public String delete(Long id, int page, String keyword){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
  }

    @GetMapping(path = "/patients")
    @ResponseBody
  public  List<Patient> listPatients(){
        return patientRepository.findAll();
  }

    @GetMapping(path = "/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/saveWitoutValidation")
    public String savesaveWitoutValidation(Model model, Patient patient){
        patientRepository.save(patient);
        return"formPatients";
    }

    //save plus validation
    @PostMapping(path = "/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"formPatients";
        }
        patientRepository.save(patient);
        return"redirect:/formPatients";
    }






}
