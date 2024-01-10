package Controller;

import com.project.GraduationProcessManagementApplication;
import com.project.controller.ManagementSystemController;
import com.project.entity.Thesis;
import com.project.service.ManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GraduationProcessManagementApplication.class)
public class ManagementSystemControllerTest {

    @Mock
    private ManagementService managementService;

    @InjectMocks
    private ManagementSystemController managementSystemController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Thesis sampleThesis;

    private final String thesisJson = "{"
            + "\"title\": \"Sample Thesis Title\","
            + "\"objective\": \"Objective of the thesis\","
            + "\"tasks\": \"List of tasks\","
            + "\"technologies\": \"Used technologies\","
            + "\"student\": {\"id\": 1},"
            + "\"teacher\": {\"id\": 1},"
            + "\"submissionDate\": \"2023-01-01T00:00:00\""
            + "}";;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(managementSystemController).build();

        sampleThesis = new Thesis();
        // Initialize the properties of sampleThesis as needed for testing
    }

    @Test
    public void whenUploadThesis_thenReturnsThesis() throws Exception {
        given(managementService.uploadThesis(sampleThesis)).willReturn(sampleThesis);

        mockMvc.perform(post("/api/management-system/upload-thesis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(thesisJson)) // Using the thesisJson variable here
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Thesis Title"));
    }


    // Additional tests...
}
