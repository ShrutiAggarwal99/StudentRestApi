package com.tsf.tsf_demo.controller;

import com.tsf.tsf_demo.modal.Student;
import com.tsf.tsf_demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("getallstudent")
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @PostMapping("addstudent")
    public String addStudent(@Valid @RequestBody Student student){
        try{
            studentRepository.save(student);
            return "Record for " + student.getName() + " is inserted!";
        }
        catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
