package example.day03._과제3.controller;

import example.day03._과제3.model.dto.CourseDto;
import example.day03._과제3.model.dto.StudentDto;
import example.day03._과제3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/day03/task3")
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;
    
    /** 과정 등록 */
    @PostMapping("/course")
    public boolean courseSave(@RequestBody() CourseDto courseDto) {
        System.out.println("StudentController.courseSave");
        System.out.println("courseDto = " + courseDto);
        boolean result = studentService.courseSave(courseDto);
        return result;
    }
    
    /** 과정 전체 조회 */
    @GetMapping("/course")
    public List<CourseDto> courseFindAll() {
        System.out.println("StudentController.courseFindAll");
        List<CourseDto> result = studentService.courseFindAll();
        return result;
    }
    
    /** 특정 과정에 수강생 등록 */
    @PostMapping("/student")
    public boolean studentSave(@RequestBody() StudentDto studentDto) {
        System.out.println("StudentController.studentSave");
        System.out.println("studentDto = " + studentDto);
        boolean result = studentService.studentSave(studentDto);
        return result;
    }
    
    /** 특정 과정에 수강생 전체 조회 */
    @GetMapping("/student")
    public List<StudentDto> studentFindAll() {
        System.out.println("StudentController.studentFindAll");
        List<StudentDto> result = studentService.studentFindAll();
        return result;
    }
    
}
