package example.day03._과제3.service;

import example.day03._과제3.model.dto.CourseDto;
import example.day03._과제3.model.dto.StudentDto;
import example.day03._과제3.model.entity.CourseEntity;
import example.day03._과제3.model.entity.StudentEntity;
import example.day03._과제3.model.repository.CourseRepository;
import example.day03._과제3.model.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    /** 과정 등록 */
    public boolean courseSave(CourseDto courseDto) {
        System.out.println("StudentService.courseSave");
        System.out.println("courseDto = " + courseDto);
        CourseEntity entity = courseDto.toEntity();
        courseRepository.save(entity);
        return true;
    }

    /** 과정 전체 조회 */
    public List<CourseDto> courseFindAll() {
        System.out.println("StudentService.courseFindAll");
        List<CourseEntity> entityList = courseRepository.findAll();
        List<CourseDto> dtoList = new ArrayList<>();
        for(int index = 0; index < entityList.size(); index++) {
            CourseEntity entity = entityList.get(index);
            CourseDto dto = entity.toDto();
            dtoList.add(dto);
        }
        return dtoList;
    }

    /** 특정 과정에 수강생 등록 */
    public boolean studentSave(StudentDto studentDto) {
        System.out.println("StudentService.studentSave");
        System.out.println("studentDto = " + studentDto);
        StudentEntity entity = studentDto.toEntity();
        // CourseEntity courseEntity = courseRepository.findById(studentDto.getCourseEntityId()).orElse(null);
        // if(courseEntity == null) { return false; }
        // entity.setCourseEntity(courseEntity);
        Optional<CourseEntity> courseEntity = courseRepository.findById(studentDto.getCourseEntityId());
        if(courseEntity.isPresent()) {
            CourseEntity course = courseEntity.get();
            entity.setCourseEntity(course);
        } else {
            return false;
        }
        System.out.println("entity = " + entity);
        studentRepository.save(entity);
        return true;
    }
    /** 특정 과정에 수강생 전체 조회 */
    public List<StudentDto> studentFindById(int id) {
        System.out.println("StudentService.studentFindById");
        System.out.println("id = " + id);
        CourseEntity entity = null;
        Optional<CourseEntity> courseEntity = courseRepository.findById(id);
        if(courseEntity.isPresent()) {
            entity = courseEntity.get();
        } else {
            return null;
        }
        List<StudentEntity> studentEntityList = entity.getStudentEntityList();
        List<StudentDto> result = new ArrayList<>();
        for(int index = 0; index < studentEntityList.size(); index++) {
            StudentEntity student = studentEntityList.get(index);
            StudentDto studentDto = student.toDto();
            result.add(studentDto);
        }
        return result;
    }

    /** 수강생 전체 조회 */
    public List<StudentDto> studentFindAll() {
        System.out.println("StudentService.studentFindAll");
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for(int index = 0; index < studentEntityList.size(); index++) {
            StudentEntity entity = studentEntityList.get(index);
            StudentDto dto = entity.toDto();
            studentDtoList.add(dto);
        }
        return studentDtoList;
    }


}
