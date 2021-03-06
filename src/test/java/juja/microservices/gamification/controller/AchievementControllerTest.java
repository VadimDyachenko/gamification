package juja.microservices.gamification.controller;

import juja.microservices.gamification.entity.*;
import juja.microservices.gamification.service.AchievementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AchievementController.class)
public class AchievementControllerTest {

    private static final String FIRST_ACHIEVEMENT_ID = "1";
    private static final String SECOND_ACHIEVEMENT_ID = "2";
    private static final String THIRD_ACHIEVEMENT_ID = "3";
    private static final String ONE_ID = "[\"1\"]";
    private static final String TWO_ID = "[\"1\",\"2\"]";
    private static final String THREE_ID = "[\"1\",\"2\",\"3\"]";

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private AchievementService service;

    @Test
    public void addDaily() throws Exception {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(FIRST_ACHIEVEMENT_ID);
        when(service.addDaily(any(DailyRequest.class))).thenReturn(expectedList);
        String jsonContentRequest =
                "{\"from\":\"max\",\"description\":\"Daily report\"}";
        String result = getResult("/achieve/daily", jsonContentRequest);
        assertEquals(ONE_ID, result);
    }

    @Test
    public void addDailyWithEmptyFrom() throws Exception {
        //given
        String jsonContentRequest = "{\"from\":\"\",\"description\":\"Daily report\"}";
        //then
        checkBadRequest("/achieve/daily", jsonContentRequest);
    }


    @Test
    public void addDailyWithEmptyDescription() throws Exception {
        //given
        String jsonContentRequest = "{\"from\":\"sasha\",\"description\":\"\"}";
        //then
        checkBadRequest("/achieve/daily", jsonContentRequest);
    }

    @Test
    public void addFirstThanks() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add(FIRST_ACHIEVEMENT_ID);
        when(service.addThanks(any(ThanksRequest.class))).thenReturn(ids);
        String jsonContentRequest =
                "{\"from\":\"max\",\"to\":\"john\",\"description\":\"thanks\"}";
        String result = getResult("/achieve/thanks", jsonContentRequest);
        assertEquals(ONE_ID, result);
    }

    @Test
    public void addSecondThanks() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add(FIRST_ACHIEVEMENT_ID);
        ids.add(SECOND_ACHIEVEMENT_ID);
        when(service.addThanks(any(ThanksRequest.class))).thenReturn(ids);
        String jsonContentRequest =
                "{\"from\":\"max\",\"to\":\"john\",\"description\":\"thanks\"}";
        String result = getResult("/achieve/thanks", jsonContentRequest);
        assertEquals(TWO_ID, result);
    }

    @Test
    public void addThanksWithEmptyFrom() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"\",\"to\":\"john\",\"description\":\"thanks\"}";
        //then
        checkBadRequest("/achieve/thanks", jsonContentRequest);
    }

    @Test
    public void addThanksWithEmptyTo() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"max\",\"to\":\"\",\"description\":\"thanks\"}";
        //then
        checkBadRequest("/achieve/thanks", jsonContentRequest);
    }

    @Test
    public void addThanksWithEmptyDescription() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"max\",\"to\":\"john\",\"description\":\"\"}";
        //then
        checkBadRequest("/achieve/thanks", jsonContentRequest);
    }

    @Test
    public void addThanksKeeper() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add(FIRST_ACHIEVEMENT_ID);
        when(service.addThanksKeeper()).thenReturn(ids);
        String result = getResult("/achieve/keepers/thanks", "");
        assertEquals(ONE_ID, result);
    }

    @Test
    public void addCodenjoy() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add(FIRST_ACHIEVEMENT_ID);
        ids.add(SECOND_ACHIEVEMENT_ID);
        ids.add(THIRD_ACHIEVEMENT_ID);
        when(service.addCodenjoy(any(CodenjoyRequest.class))).thenReturn(ids);
        String jsonContentRequest =
                "{\"from\":\"max\",\"firstPlace\":\"alex\",\"secondPlace\":\"jack\",\"thirdPlace\":\"tomas\"}";
        String result = getResult("/achieve/codenjoy", jsonContentRequest);
        assertEquals(THREE_ID, result);
    }

    @Test
    public void addCodenjoyWithEmptyFrom() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"\",\"firstPlace\":\"alex\",\"secondPlace\":\"jack\",\"thirdPlace\":\"tomas\"}";
        //then
        checkBadRequest("/achieve/codenjoy", jsonContentRequest);
    }

    @Test
    public void addCodenjoyWithEmptyFirstPlace() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"max\",\"firstPlace\":\"\",\"secondPlace\":\"jack\",\"thirdPlace\":\"tomas\"}";
        //then
        checkBadRequest("/achieve/codenjoy", jsonContentRequest);
    }

    @Test
    public void addCodenjoyWithEmptySecondPlace() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"max\",\"firstPlace\":\"alex\",\"secondPlace\":\"\",\"thirdPlace\":\"tomas\"}";
        //then
        checkBadRequest("/achieve/codenjoy", jsonContentRequest);
    }

    @Test
    public void addCodenjoyWithEmptyThirdPlace() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"max\",\"firstPlace\":\"alex\",\"secondPlace\":\"jack\",\"thirdPlace\":\"\"}";
        //then
        checkBadRequest("/achieve/codenjoy", jsonContentRequest);
    }

    @Test
    public void addInterview() throws Exception {
        List<String> expectedList = new ArrayList<>();
        expectedList.add(FIRST_ACHIEVEMENT_ID);
        when(service.addInterview((any(InterviewRequest.class)))).thenReturn(expectedList);
        String jsonContentRequest =
                "{\"from\":\"max\",\"description\":\"Interview report\"}";
        String result = getResult("/achieve/interview", jsonContentRequest);
        assertEquals(ONE_ID, result);
    }

    @Test
    public void addInterviewWithEmptyFrom() throws Exception {
        //given
        String jsonContentRequest = "{\"from\":\"\",\"description\":\"Interview report\"}";
        //then
        checkBadRequest("/achieve/interview", jsonContentRequest);
    }

    @Test
    public void addInterviewWithEmptyDescription() throws Exception {
        //given
        String jsonContentRequest = "{\"from\":\"max\",\"description\":\"\"}";
        //then
        checkBadRequest("/achieve/interview", jsonContentRequest);
    }

    @Test
    public void addWelcome() throws Exception {
        //given
        List<String> ids = new ArrayList<>();
        ids.add(FIRST_ACHIEVEMENT_ID);
        String jsonContentRequest =
                "{\"from\":\"max\",\"to\":\"john\"}";

        //when
        when(service.addWelcome(any(WelcomeRequest.class))).thenReturn(ids);
        String result = getResult("/achieve/welcome", jsonContentRequest);

        //then
        assertEquals(ONE_ID, result);
    }

    @Test
    public void addWelcomeWithEmptyTo() throws Exception {
        //given
        String jsonContentRequest =
                "{\"from\":\"max\",\"to\":\"\"}";
        //then
        checkBadRequest("/achieve/welcome", jsonContentRequest);
    }

    private String getResult(String uri, String jsonContentRequest) throws Exception {
        return mockMvc.perform(post(uri)
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonContentRequest))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private void checkBadRequest(String uri, String jsonContentRequest) throws Exception {
        mockMvc.perform(post(uri)
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonContentRequest))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }
}