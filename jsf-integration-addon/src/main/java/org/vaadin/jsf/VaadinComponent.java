package org.vaadin.jsf;

import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Version;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

@FacesComponent("org.vaadin.jsf.VaadinComponent")
public class VaadinComponent extends UIComponentBase {

    @Override
    public String getFamily() {
        return "org.vaadin.jsf";
    }

    @Override
    public void encodeBegin(FacesContext facesContext) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        try {
            String template = getTemplate();
            String html = setVariables(template);
            writer.write(html);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTemplate() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("template.html");
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(line).append("\n");
        }

        scanner.close();

        return stringBuilder.toString();
    }

    private String setVariables(String template) {
        Map<String, Object> attributes = getAttributes();
        String url = (String) attributes.get("url");
        String theme = (String) attributes.get("theme");
        String widgetset = (String) attributes.get("widgetset");
        String vaadindir = (String) attributes.get("vaadindir");

        String divId = url.substring(1).replaceAll("/", "-");
        String vaadinVersion = Version.getFullVersion();
        String context = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        return template
                .replace("${divId}", divId)
                .replace("${theme}", theme != null ? theme : "valo")
                .replace("${vaadinVersion}", vaadinVersion)
                .replace("${widgetset}", widgetset != null ? widgetset : VaadinServlet.DEFAULT_WIDGETSET)
                .replace("${vaadinDir}", vaadindir != null ? vaadindir : context + "/VAADIN/")
                .replace("${browserDetailsUrl}", context + url)
                .replace("${serviceUrl}", context + url);
    }

}
