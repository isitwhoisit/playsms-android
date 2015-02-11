package com.artivisi.android.playsms.service.impl;

import android.util.Log;

import com.artivisi.android.playsms.helper.LoginHelper;
import com.artivisi.android.playsms.helper.MessageHelper;
import com.artivisi.android.playsms.service.AndroidMasterService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by opaw on 2/5/15.
 */
public class AndroidMasterServiceImpl implements AndroidMasterService {
    private static final String BASE_URI = "http://my.textng.com/index.php?app=ws&";
    RestTemplate restTemplate = new RestTemplate(true);

    public AndroidMasterServiceImpl() {
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Override
    public LoginHelper getToken(String username, String password) {
        String url = BASE_URI + "u=" + username + "&p=" + password + "&op=get_token&format=json";
        ResponseEntity<LoginHelper> responseEntity = restTemplate.getForEntity(url, LoginHelper.class);
        return responseEntity.getBody();
    }

    @Override
    public MessageHelper getSentMessage(String username, String token) {
        String url = BASE_URI + "u=" + username + "&h=" + token + "&op=ds&format=json";
        ResponseEntity<MessageHelper> responseEntity = restTemplate.getForEntity(url, MessageHelper.class);
        return responseEntity.getBody();
    }

    @Override
    public MessageHelper getInbox(String username, String token) {
        String url = BASE_URI + "u=" + username + "&h=" + token + "&op=ix&format=json";
        ResponseEntity<MessageHelper> responseEntity = restTemplate.getForEntity(url, MessageHelper.class);
        return responseEntity.getBody();
    }

    @Override
    public MessageHelper sendMessage(String username, String token, String to, String msg) {
        String url = BASE_URI +
                "u=" + username + "&h=" + token + "&op=pv&to=" + to + "&msg=" + msg + "&format=json";
        ResponseEntity<MessageHelper> responseEntity = restTemplate.getForEntity(url, MessageHelper.class);
        return responseEntity.getBody();
    }
}
