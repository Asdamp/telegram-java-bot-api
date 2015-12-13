import com.antonioaltieri.telegram.botapi.CommandHandler;
import com.antonioaltieri.telegram.botapi.HandlerNotifier;
import com.antonioaltieri.telegram.botapi.Properties;
import com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor;
import com.antonioaltieri.telegram.botapi.requests.GetMeRequest;
import com.antonioaltieri.telegram.botapi.requests.SendVoiceRequest;
import com.antonioaltieri.telegram.botapi.requests.TelegramApi;
import com.antonioaltieri.telegram.botapi.types.Message;
import com.antonioaltieri.telegram.botapi.types.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Antonio on 11/09/2015.
 */
public class ValidationTest {
    protected final static String TOKEN = TestToken.TOKEN;
    protected TelegramApi api;
    protected ApiRequestExecutor requestExecutor;
    User user;

    @Before
    public void setUp() throws Exception {
        api = new TelegramApi(TOKEN);
        assertNotNull(api);
        requestExecutor = ApiRequestExecutor.getInstance();
        assertNotNull(requestExecutor);
        GetMeRequest request = new GetMeRequest();
        user = requestExecutor.execute(api, request);
    }

    @Test
    public void voiceSend(){
        String cmd="help";
        String extCmd=extractCommand("/"+cmd+"@"+user.getUsername());
        System.out.println("/"+cmd+"@"+user.getUsername());
        assertTrue(cmd.equalsIgnoreCase(extCmd));
    }

    private String extractCommand(String text) {
            String cmdTrg[]=text.split(" ")[0].split("@");
            String cmd=cmdTrg[0].substring(1);
            String target=null;
            if(cmdTrg.length>=2) target=cmdTrg[1];
            if(target==null || target.equalsIgnoreCase(user.getUsername()))
                return cmd;

        return null;
    }
}
