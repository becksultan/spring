package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView addStudent(@ModelAttribute AddStudent addStudent) {
        Student student = new Student();
        student.setName(addStudent.getName());
        student.setEmail(addStudent.getEmail());
        studentRepository.save(student);
        return new RedirectView("/");
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