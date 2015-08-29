import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.asdamp.telegram.botapi.requests.ApiRequestExecutor;
import com.asdamp.telegram.botapi.requests.GetMeRequest;
import com.asdamp.telegram.botapi.requests.TelegramApi;
import com.asdamp.telegram.botapi.types.User;

public class requesttest {
	    protected final static String TOKEN = "124217141:AAFMLhB5MWvHWaFRi9xpyVBIAgBkSEanEbM";
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
	    
	  /*  @Test
	    public void setWebhookTest() throws Exception{
	 
	        SetWebHookRequest request = new SetWebHookRequest("https://jovanottitelegrambot.appspot.com/AAFMLhB5MWvHWaFRi9xpyVBIAgBkSEanEbM");

	        Boolean user = requestExecutor.execute(api, request).getResult();
	        System.out.println(user);
	    }*/
}
