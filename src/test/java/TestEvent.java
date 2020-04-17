import com.starkbank.*;
import com.starkbank.User;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class TestEvent {

    @Test
    public void testEventGet() throws Exception{
        User.defaultUser = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Event> events = Event.query(params);

        int i = 0;
        for (Event event : events) {
            i += 1;
            System.out.println(event.subscription);
        }
        Assert.assertTrue(i > 0);
    }
}
