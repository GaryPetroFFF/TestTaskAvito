import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;
import requests.CustomHttpServletRequest;
import responses.CustomHttpServletResponse;

public class ServiceTest extends Assert{
    private Service service_ = new Service();

    @Test
    public void createMeetingTest() {
        CustomHttpServletRequest request = new CustomHttpServletRequest();
        CustomHttpServletResponse response = new CustomHttpServletResponse();
        request.setParameter("action", "createMeeting");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode JSON = objectMapper.createObjectNode();
        JSON.put("date", "20.2.2020");
        JSON.put("time", "23:22");
        request.setParameter("parameters", JSON.toString());
        try {
            service_.doPost(request, response);
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue((Boolean) response.getParameter("answer"));
    }
}
