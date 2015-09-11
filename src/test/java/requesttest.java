import com.antonioaltieri.telegram.botapi.requests.SetWebHookRequest;
import org.junit.Before;
import org.junit.Test;

import com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor;
import com.antonioaltieri.telegram.botapi.requests.GetMeRequest;
import com.antonioaltieri.telegram.botapi.requests.TelegramApi;
import com.antonioaltieri.telegram.botapi.types.User;

public class requesttest {
	    protected final static String TOKEN = TestToken.TOKEN;
	    protected TelegramApi api;
	    protected ApiRequestExecutor requestExecutor;

	    @Before
	    public void setUp() throws Exception {
	        api = new TelegramApi(TOKEN);
	        requestExecutor = ApiRequestExecutor.getInstance();
	    }
	    
	    @Test
	    public void getMeTest() throws Exception{
	 
	        GetMeRequest request = new GetMeRequest();

	        User user = requestExecutor.execute(api, request);
	        System.out.println(user);
	    }
	    
	  /*  @Test
	    public void setWebhookTest() throws Exception{
	 
	        SetWebHookRequest request = new SetWebHookRequest("Your servlet address");

	        Boolean user = requestExecutor.execute(api, request);
	        System.out.println(user);
	    }*/
}
