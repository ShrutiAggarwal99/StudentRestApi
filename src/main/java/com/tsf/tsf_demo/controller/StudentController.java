package com.tsf.tsf_demo.controller;

import com.tsf.tsf_demo.model.Student;
import com.tsf.tsf_demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    //CRUD OPERATIONS

    //CREATE
    @RequestMapping(method = RequestMethod.POST, value = "/addStudent")
    public String addStudent(@RequestBody Student student){
        try{
            studentRepository.save(student);
            return "Record for " + student.getName() + " is inserted!";
        }
        catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


    //RETRIEVE
    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET, produces = {"application/json"})
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/updateStudent")
    public String updateStudentName(@RequestParam(name = "id") Long studentID, @RequestParam(name = "newName", defaultValue = "false") String newName, @RequestParam(name = "newBranch", defaultValue = "false") String newBranch){
        try{
            Student student = studentRepository.getOne(studentID);
            if(newName.compareTo("false")!=0) student.setName(newName);
            if(newBranch.compareTo("false")!=0) student.setBranch(newBranch);
            studentRepository.save(student);
            return "Record of student with ID " + studentID + " updated!";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


    //DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteStudent")
    public String deleteStudent(@RequestParam(name = "id") Long studentID){
        try{
            Student student = studentRepository.getOne(studentID);
            studentRepository.delete(student);
            return "Record for " + student.getName() + " deleted!";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
