package org.jakartaee10recipe.chapter04.recipe04_10;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "ajaxBean")
@SessionScoped
public class AjaxBean implements Serializable {

    private Part file = null;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * Creates a new instance of AjaxBean
     */
    public AjaxBean() {
    }


    public void uploadFile() {

        try (InputStream is = file.getInputStream();) {
            byte[] b = new byte[1024];
            is.read(b);
            String fileName = file.getName();
            FileOutputStream os = new FileOutputStream("/Java_Dev/" + fileName);

        } catch (IOException ex) {
            Logger.getLogger(AjaxBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


