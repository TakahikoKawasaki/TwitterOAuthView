/*
 * Copyright (C) 2012-2013 Neo Visionaries Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package twitteroauthview.sample;


import twitter4j.auth.AccessToken;
import com.neovisionaries.android.twitter.TwitterOAuthView;
import com.neovisionaries.android.twitter.TwitterOAuthView.Result;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


/**
 * An example Activity implementation using {@link TwitterOAuthView}.
 *
 * @author Takahiko Kawasaki
 */
public class TwitterOAuthActivity extends Activity implements TwitterOAuthView.Listener
{
    // Replace values of the parameters below with your own.
    private static final String CONSUMER_KEY = "YOUR CONSUMER KEY HERE";
    private static final String CONSUMER_SECRET = "YOUR CONSUMER SECRET HERE";
    private static final String CALLBACK_URL = "YOUR CALLBACK URL HERE";
    private static final boolean DUMMY_CALLBACK_URL = true;


    private TwitterOAuthView view;
    private boolean oauthStarted;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Create an instance of TwitterOAuthView.
        view = new TwitterOAuthView(this);
        view.setDebugEnabled(true);

        setContentView(view);

        oauthStarted = false;
    }


    @Override
    protected void onResume()
    {
        super.onResume();

        if (oauthStarted)
        {
            return;
        }

        oauthStarted = true;

        // Start Twitter OAuth process. Its result will be notified via
        // TwitterOAuthView.Listener interface.
        view.start(CONSUMER_KEY, CONSUMER_SECRET, CALLBACK_URL, DUMMY_CALLBACK_URL, this);
    }


    public void onSuccess(TwitterOAuthView view, AccessToken accessToken)
    {
        // The application has been authorized and an access token
        // has been obtained successfully. Save the access token
        // for later use.
        showMessage("Authorized by " + accessToken.getScreenName());
    }


    public void onFailure(TwitterOAuthView view, Result result)
    {
        // Failed to get an access token.
        showMessage("Failed due to " + result);
    }


    private void showMessage(String message)
    {
        // Show a popup message.
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}