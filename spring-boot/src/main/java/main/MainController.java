package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path="/add")
    public String addStudentForm(Model model) {
        model.addAttribute("form", new AddStudent());
        return "add";
    }

    @PostMapping(path="/add")
    public String addStudent(@ModelAttribute @Valid AddStudent addStudent, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", addStudent);
            return "add";
        }
        Student student = new Student();
        student.setName(addStudent.getName());
        student.setEmail(addStudent.getEmail());
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping(path="/delete")
    public RedirectView deleteStudent(@RequestParam Integer id) {
        studentRepository.deleteById(id);
        return new RedirectView("/");
    }

    @GetMapping(path="/")
    public String all(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "all";
    }
}