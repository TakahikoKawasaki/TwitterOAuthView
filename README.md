TwitterOAuthView
================

Overview
--------

A WebView subclass dedicated to Twitter OAuth on Android, using twitter4j.

As this class is implemented as a subclass of View, it can be integrated
into the Android layout system seamlessly. This fact makes this class an
easily-reusable UI component.

To use this class, it is not necessary to review the flow of OAuth handshake.
Just implement TwitterOAuthView.Listener and call start() method. The result
of OAuth handshake is reported via either of the listener's methods,
onSuccess() or onFailure().


License
-------

Apache License, Version 2.0


Download
--------

    git clone git://github.com/TakahikoKawasaki/TwitterOAuthView.git


Javadoc
-------

[TwitterOAuthView API](http://takahikokawasaki.github.com/TwitterOAuthView/index.html)


Example
-------

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


See Also
--------

* [Twitter4J](http://twitter4j.org/)


Author
------

Takahiko Kawasaki, Neo Visionaries Inc.
