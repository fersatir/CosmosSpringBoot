package com.cos.controller;

import com.cos.domain.Teacher;
import com.cos.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

   @Autowired
   private TeacherService teacherService;

   @PostMapping("/create")
   public ResponseEntity<Map<String,String>> teacherCreate(@Valid @RequestBody Teacher teacher){
      teacherService.teacherCreate(teacher);
      Map<String,String> map= new HashMap<>();
      map.put("message","create successfuly");
      map.put("status","true");
      return new ResponseEntity<>(map, HttpStatus.CREATED);
   }

   @GetMapping
   public String hello(){
      return "hello cosmos team";
   }

   @GetMapping("/getallteacher")
   public ResponseEntity<List<Teacher>> getAllTeacher(){
      return ResponseEntity.ok(teacherService.getAllTeacher());
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Map<String,String>> deleteTeacherById(@PathVariable("id") Long id){
      teacherService.deleteTeacher(id);
      Map<String,String>map = new HashMap<>();
      map.put("message","delete success");
      map.put("status","true");
      return new ResponseEntity(map,HttpStatus.OK);
   }

   @PutMapping("/updateteacher/{id}")
   public ResponseEntity<Map<String,String>> updateTeacher(@PathVariable Long id,@Valid @RequestBody Teacher teacher){
      teacherService.updateTeacher(id,teacher);
      Map<String,String> map = new HashMap<>();
      map.put("message","update success");
      map.put("status","true");
      return new ResponseEntity<>(map, HttpStatus.OK);
   }

   @GetMapping("/branch")
   public ResponseEntity<List<Teacher>> getTeacherBranch(@RequestParam("branch") String branch){
      return ResponseEntity.ok(teacherService.findTeacherBranch(branch));
   }

   @GetMapping("/pageging")
   public ResponseEntity<Page<Teacher>> getAllTeacherPages(@RequestParam("page") int page, @RequestParam("size")int size,
                                                           @RequestParam("sort") String sort, @RequestParam("direction") Direction direction){
      Pageable pageable = PageRequest.of(page, size,Sort.by(direction,sort));
      Page<Teacher>teacherPage = teacherService.findTeacherPage(pageable);
      return ResponseEntity.ok(teacherPage);
   }






}
