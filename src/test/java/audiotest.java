

import java.io.File;
import java.net.URL;

import com.asdamp.telegram.botapi.requests.OptionalArgs;
import com.asdamp.telegram.botapi.requests.SendAudioRequest;
import com.asdamp.telegram.botapi.types.Message;
import org.junit.*;
import static org.junit.Assert.*;



import com.asdamp.telegram.botapi.requests.ApiRequestExecutor;
import com.asdamp.telegram.botapi.requests.TelegramApi;


public class audiotest {
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
    public void audioSend(){
        OptionalArgs option=new OptionalArgs();
        option.performer("Asdamp");
        option.title("TestSong");
    	SendAudioRequest sar=new SendAudioRequest(TestToken.chat_id,audioFile,option);
    	Message msg=requestExecutor.execute(api, sar);
        assertTrue(msg.getAudio()!=null);
    }

}
