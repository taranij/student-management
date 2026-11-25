package com.student_management.student_management_system.service;
 
import com.student_management.student_management_system.entity.Student;
import com.student_management.student_management_system.repository.StudentRepository;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
 
    @Mock
    private StudentRepository studentRepository;
 
    @InjectMocks
    private StudentService studentService;
 
    private Student student;
 
    @BeforeEach
    void setUp() {
        student = new Student();
 
        student.setId(1L);
        student.setName("Rahul");
        student.setEmail("rahul@gmail.com");
        student.setCourse("Java");
    }
 
    // Test 1: Save Student
    @Test
    void testSaveStudent() {
 
        when(studentRepository.save(student))
                .thenReturn(student);
 
        Student result = studentService.saveStudent(student);
 
        assertNotNull(result);
        assertEquals("Rahul", result.getName());
 
        verify(studentRepository, times(1)).save(student);
    }
 
    // Test 2: Get All Students
    @Test
    void testGetAllStudents() {
 
        Student student2 = new Student();
 
        student2.setId(2L);
        student2.setName("Priya");
        student2.setEmail("priya@gmail.com");
        student2.setCourse("Spring Boot");
 
        List<Student> students = Arrays.asList(student, student2);
 
        when(studentRepository.findAll()).thenReturn(students);
 
        List<Student> result = studentService.getAllStudents();
 
        assertEquals(2, result.size());
        assertEquals("Rahul", result.get(0).getName());
        assertEquals("Priya", result.get(1).getName());
 
        verify(studentRepository, times(1)).findAll();
    }
 
    // Test 3: Get All Students - Empty List
    @Test
    void testGetAllStudentsWhenEmpty() {
 
        when(studentRepository.findAll()).thenReturn(List.of());
 
        List<Student> result = studentService.getAllStudents();
 
        assertNotNull(result);
        assertTrue(result.isEmpty());
 
        verify(studentRepository, times(1)).findAll();
    }
 
    // Test 4: Delete Student
    @Test
    void testDeleteStudent() {
 
        Long studentId = 1L;
 
        studentService.deleteStudent(studentId);
 
        verify(studentRepository, times(1))
                .deleteById(studentId);
    }
}
has context menu