package Controller;

import com.project.GraduationProcessManagementApplication;
import com.project.controller.ThesisController;
import com.project.dto.ThesisDto;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;
import com.project.service.ThesisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GraduationProcessManagementApplication.class)
public class ThesisControllerTest {

    @Mock
    private ThesisService thesisService;

    @InjectMocks
    private ThesisController thesisController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ThesisDto sampleThesis;

    private ThesisReview thesisReview;

    @BeforeEach
    public void setup() throws ParseException {
        mockMvc = MockMvcBuilders.standaloneSetup(thesisController).build();


        sampleThesis = new ThesisDto();
        sampleThesis.setTitle("Sample Thesis Title");
        sampleThesis.setObjective("Objective of the thesis");
        sampleThesis.setTasks("List of tasks");
        sampleThesis.setTechnologies("Used technologies");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date submissionDate = dateFormat.parse("2023-01-01");
        sampleThesis.setSubmissionDate(submissionDate);

        thesisReview = new ThesisReview();
        thesisReview.setSubmissionDate(new Date());
        thesisReview.setText("Sample review text");
        thesisReview.setConclusion(true);
    }

    @Test
    public void whenUploadThesis_thenReturnsThesis() throws Exception {
        given(thesisService.uploadThesis(any(ThesisDto.class))).willReturn(sampleThesis);

        mockMvc.perform(post("/api/thesis/upload-thesis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sampleThesis)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Thesis Title"));
    }

//    @Test
//    @WithMockUser(username = "teacher", roles = {"TEACHER"})
//    public void whenProcessThesis_thenSuccess() throws Exception {
//        Long thesisId = 1L;
//        Thesis thesis = new Thesis();
//        thesis.setId(thesisId);
//        thesisReview.setThesis(thesis);
//        thesisReview.setConclusion(Boolean.TRUE);
//
//        doNothing().when(thesisService).processThesis(thesisReview);
//
//        mockMvc.perform(post("/api/thesis/process-thesis")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(thesisReview)))
//                .andExpect(status().isOk());
//
//        verify(thesisService).processThesis(any(ThesisReview.class));
//    }

//    @Test
//    public void whenAssignStudent_thenSuccess() throws Exception {
//        Long studentId = 1L;
//        Long thesisId = 1L;
//
//        doNothing().when(thesisService).assignStudentToThesis(studentId, thesisId);
//
//        mockMvc.perform(post("/api/thesis/assign-student")
//                        .param("studentId", studentId.toString())
//                        .param("thesisId", thesisId.toString()))
//                .andExpect(status().isOk());
//
//        verify(thesisService).assignStudentToThesis(studentId, thesisId);
//    }
//
//    @Test
//    public void whenAssignTeacher_thenSuccess() throws Exception {
//        Long teacherId = 1L;
//        Long thesisId = 1L;
//
//        doNothing().when(thesisService).assignTeacherToThesis(teacherId, thesisId);
//
//        mockMvc.perform(post("/api/thesis/assign-teacher")
//                        .param("teacherId", teacherId.toString())
//                        .param("thesisId", thesisId.toString()))
//                .andExpect(status().isOk());
//
//        verify(thesisService).assignTeacherToThesis(teacherId, thesisId);
//    }

}
