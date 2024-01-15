package Controller;

import com.project.GraduationProcessManagementApplication;
import com.project.controller.ThesisController;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
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

    private Thesis sampleThesis;

    private ThesisReview thesisReview;

    @BeforeEach
    public void setup() throws ParseException {
        mockMvc = MockMvcBuilders.standaloneSetup(thesisController).build();


        sampleThesis = new Thesis();
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
    @WithMockUser(username = "student", roles = {"STUDENT"})
    public void whenUploadThesis_thenReturnsThesis() throws Exception {
        given(thesisService.uploadThesis(any(Thesis.class))).willReturn(sampleThesis);

        mockMvc.perform(post("/api/thesis/upload-thesis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sampleThesis)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Thesis Title"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"TEACHER"})
    public void whenProcessThesis_thenSuccess() throws Exception {
        Long thesisId = 1L;
        Thesis thesis = new Thesis();
        thesis.setId(thesisId);
        thesisReview.setThesis(thesis);

        doNothing().when(thesisService).processThesis(eq(thesisId), any(ThesisReview.class));

        mockMvc.perform(post("/api/thesis/process-thesis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(thesisReview)))
                .andExpect(status().isOk());

        verify(thesisService).processThesis(eq(thesisId), any(ThesisReview.class));
    }




}
