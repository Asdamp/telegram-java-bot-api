

import java.io.File;

import org.junit.*;


import com.asdamp.telegram.botapi.requests.ApiRequestExecutor;
import com.asdamp.telegram.botapi.requests.SendAudioRequest;
import com.asdamp.telegram.botapi.requests.TelegramApi;
import com.asdamp.telegram.botapi.types.Message;

public class audiotest {
	protected final static String TOKEN = "124217141:AAFMLhB5MWvHWaFRi9xpyVBIAgBkSEanEbM";
    protected TelegramApi api;
    protected ApiRequestExecutor requestExecutor;

    @Before
    public void setUp() throws Exception {
        api = new TelegramApi(TOKEN);
        requestExecutor = ApiRequestExecutor.getSynchronousExecutor();
    }
    
    @Test
    public void audioSend(){
    	SendAudioRequest sar=new SendAudioRequest(14827206,new File("C:/Users/Antonio/Music/JovaBot/Sabato-ballare.mp3"));
    	Message msg=requestExecutor.execute(api, sar).getResult();
    	System.out.println(msg.getAudio().getFileId());
    }
   /* @Test
    public void audioSenduzzo(){
    	SendAudioRequest sar=new SendAudioRequest(14827206,"AwADBAADAQADNWdnB_gMYEQNHn_wAg");
    	Message msg=requestExecutor.execute(api, sar).getResult();
    	System.out.println(msg.getAudio().getFileId());
    }*/
}
