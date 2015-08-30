import static org.junit.Assert.*;

import com.asdamp.telegram.botapi.requests.SetWebHookRequest;
import org.junit.Before;
import org.junit.Test;

import com.asdamp.telegram.botapi.requests.ApiRequestExecutor;
import com.asdamp.telegram.botapi.requests.GetMeRequest;
import com.asdamp.telegram.botapi.requests.TelegramApi;
import com.asdamp.telegram.botapi.types.User;

public class requesttest {
	    protected final static String TOKEN = TestToken.TOKEN;
	    protected TelegramApi api;
	    protected ApiRequestExecutor requestExecutor;

	    @Before
	    public void setUp() throws Exception {
	        api = new TelegramApi(TOKEN);
	        requestExecutor = ApiRequestExecutor.getSynchronousExecutor();
	    }
	    
	    @Test
	    public void getMeTest() throws Exception{
	 
	        GetMeRequest request = new GetMeRequest();

	        User user = requestExecutor.execute(api, request).getResult();
	        System.out.println(user);
	    }
	    
	    @Test
	    public void setWebhookTest() throws Exception{
	 
	        SetWebHookRequest request = new SetWebHookRequest("Your servlet address");

	        Boolean user = requestExecutor.execute(api, request).getResult();
	        System.out.println(user);
	    }
}
