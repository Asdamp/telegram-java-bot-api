

import java.io.File;
import java.net.URL;

import com.antonioaltieri.telegram.botapi.requests.ApiRequestExecutor;
import com.antonioaltieri.telegram.botapi.requests.SendVoiceRequest;
import com.antonioaltieri.telegram.botapi.requests.TelegramApi;
import com.antonioaltieri.telegram.botapi.types.Message;
import org.junit.*;
import static org.junit.Assert.*;


public class SendVoiceTest {
    protected final static String TOKEN = TestToken.TOKEN;
    protected TelegramApi api;
    protected ApiRequestExecutor requestExecutor;
    File audioFile;

    @Before
    public void setUp() throws Exception {
        api = new TelegramApi(TOKEN);
        assertNotNull(api);
        requestExecutor = ApiRequestExecutor.getInstance();
        assertNotNull(requestExecutor);
        URL url=this.getClass().getResource("TestAudioFile.mp3");
        audioFile=new File(url.getFile());
        assertTrue(audioFile.exists());
    }

    @Test
    public void voiceSend(){
        SendVoiceRequest sar=new SendVoiceRequest(TestToken.chat_id,audioFile);
        Message msg=requestExecutor.execute(api, sar);
        assertTrue(msg.getType()== Message.Type.VOICE);
        assertTrue(msg.getVoice()!=null);
    }

}
