package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(path="/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("form", new AddStudent());
        model.addAttribute("classes", classRepository.findAll());
        return "addStudent";
    }

    @PostMapping(path="/addStudent")
    public String addStudent(@ModelAttribute @Valid AddStudent addStudent, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", addStudent);
            return "addStudent";
        }
        Student student = new Student();
        student.setName(addStudent.getName());
        student.setAge(addStudent.getAge());
        student.setaClass(classRepository.findById(addStudent.getClass_id()).get());
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping(path="/addClass")
    public String addClassForm(Model model) {
        model.addAttribute("form", new AddClass());
        model.addAttribute("schools", schoolRepository.findAll());
        return "addClass";
    }

    @PostMapping(path="/addClass")
    public String addClass(@ModelAttribute @Valid AddClass addClass, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", addClass);
            return "addClass";
        }
        Class aClass = new Class();
        aClass.setName(addClass.getNumber(), addClass.getLitera());
        Optional<School> school = schoolRepository.findById(addClass.getSchool_id());
        aClass.setSchool(school.get());
        classRepository.save(aClass);
        return "redirect:/";
    }

    @GetMapping(path="/addSchool")
    public String addSchoolForm(Model model) {
        model.addAttribute("form", new AddSchool());
        return "addSchool";
    }

    @PostMapping(path="/addSchool")
    public String addSchool(@ModelAttribute @Valid AddSchool addSchool, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", addSchool);
            return "addSchool";
        }
        School school = new School();
        school.setDescription(addSchool.getDescription());
        schoolRepository.save(school);
        return "redirect:/";
    }

    @GetMapping(path="/deleteStudent")
    public RedirectView deleteStudent(@RequestParam Integer id) {
        studentRepository.deleteById(id);
        return new RedirectView("/allStudents");
    }

    @GetMapping(path="/deleteClass")
    public RedirectView deleteClass(@RequestParam Integer id) {
        classRepository.deleteById(id);
        return new RedirectView("/allClasses");
    }

    @GetMapping(path="/deleteSchool")
    public RedirectView deleteSchool(@RequestParam Integer id) {
        schoolRepository.deleteById(id);
        return new RedirectView("/allSchools");
    }

    @GetMapping(path="/allStudents")
    public String allStudents(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "allStudents";
    }

    @GetMapping(path="/allClasses")
    public String allClasses(Model model) {
        Iterable<Class> classes = classRepository.findAll();
        model.addAttribute("classes", classes);
        return "allClasses";
    }

    @GetMapping(path="/allSchools")
    public String allSchools(Model model) {
        Iterable<School> schools = schoolRepository.findAll();
        model.addAttribute("schools", schools);
        return "allSchools";
    }
}