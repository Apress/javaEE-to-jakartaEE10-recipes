package org.jakartaeerecipe.chapter01.recipe01_18;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcmeReadListenerImpl implements ReadListener {

    private final Logger LOGGER = Logger.getLogger(AcmeReadListenerImpl.class.getName());

    private ServletInputStream is;
    private AsyncContext async;

    public AcmeReadListenerImpl(ServletInputStream in, AsyncContext ac) {
        this.is = in;
        this.async = ac;
        System.out.println("read listener initialized");
    }

    @Override
    public void onDataAvailable() throws IOException {
        System.out.println("onDataAvailable");
        try {
            StringBuilder sb = new StringBuilder();
            int len = -1;
            byte b[] = new byte[1024];
            while (is.isReady()
                    && (len = is.read(b)) != -1) {
                String data = new String(b, 0, len);
                System.out.println(data);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void onAllDataRead() throws IOException {
        System.out.println("onAllDataRead");
        async.complete();
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error: " + throwable);
        async.complete();
    }
}
