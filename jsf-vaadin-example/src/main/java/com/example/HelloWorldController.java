package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "helloWorld")
@RequestScoped
public class HelloWorldController {

    private String name;

    public String send() {
        return "page2.xhtml";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
