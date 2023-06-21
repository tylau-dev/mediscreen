import com.alert.client.INoteClient;
import com.alert.client.IPatientClient;
import com.alert.model.Alert;
import com.alert.model.Note;
import com.alert.model.Patient;
import com.alert.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AlertServiceTest {
    @Mock
    private INoteClient noteClient;
    @Mock
    private IPatientClient patientClient;

    private AlertService alertService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        alertService = new AlertService(noteClient, patientClient);
    }

    @Test
    public void testGenerateAlertById() {
        int patientId = 1;
        LocalDate currentDate = LocalDate.now();
        LocalDate dateMinuteThirtyYears = currentDate.plusYears(-30);
        Instant instantDateMinuteThirtyYears = dateMinuteThirtyYears.atStartOfDay(ZoneId.systemDefault()).toInstant();

        Patient patient = new Patient(patientId, "John", "Doe", Date.from(instantDateMinuteThirtyYears), "M", "address", "telephone");
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("Note 1", 1, "Test Note 1", new Date()));
        notes.add(new Note("Note 2", 1, "Test Note 2", new Date()));
        when(patientClient.getPatientById(patientId)).thenReturn(patient);
        when(noteClient.getNoteByPatientId(patientId)).thenReturn(notes);

        Alert alert = alertService.generateAlertById(patientId);

        assertEquals("Doe John", alert.getPatientFullName());
        assertEquals(30, alert.getPatientAge());
        assertEquals("None", alert.getAssessment());
        verify(patientClient).getPatientById(patientId);
        verify(noteClient).getNoteByPatientId(patientId);
    }

    @Test
    public void testGenerateAlertByFamilyName() {
        String lastName = "Doe";
        LocalDate currentDate = LocalDate.now();
        LocalDate dateMinuteThirtyYears = currentDate.plusYears(-30);
        Instant instantDateMinuteThirtyYears = dateMinuteThirtyYears.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Patient patient = new Patient(1, "John", "Doe", Date.from(instantDateMinuteThirtyYears), "M", "address", "telephone");

        List<Note> notes = new ArrayList<>();
        notes.add(new Note("Note 1", 1, "Test Note 1", new Date()));
        notes.add(new Note("Note 2", 1, "Test Note 2", new Date()));
        when(patientClient.getPatientByFamilyName(lastName)).thenReturn(patient);
        when(noteClient.getNoteByPatientId(patient.getPatientId())).thenReturn(notes);

        Alert alert = alertService.generateAlertByFamilyName(lastName);

        assertEquals("Doe John", alert.getPatientFullName());
        assertEquals(30, alert.getPatientAge());
        assertEquals("None", alert.getAssessment());
        verify(patientClient).getPatientByFamilyName(lastName);
        verify(noteClient).getNoteByPatientId(patient.getPatientId());
    }

    @Test
    public void testAssessRisk() {
        assertEquals(AlertService.RiskLevel.None, alertService.assessRisk(false, 0, "M"));
        assertEquals(AlertService.RiskLevel.Borderline, alertService.assessRisk(true, 2, "M"));
        assertEquals(AlertService.RiskLevel.Danger, alertService.assessRisk(false, 3, "M"));
        assertEquals(AlertService.RiskLevel.EarlyOnset, alertService.assessRisk(false, 5, "M"));
        assertEquals(AlertService.RiskLevel.EarlyOnset, alertService.assessRisk(true, 8, "M"));
    }
}
