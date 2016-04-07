# JSF Integration Add-on for Vaadin 7
JSF Integration is a Vaadin 7 add-on that allows you to render Vaadin UIs in Facelets-based JSF applications.

## Usage
Add the XML namespace and use the `ui` tag to point to the URL mapped to the `VaadinServlet` serving your UI:
```xhtml
<html ...
    xmlns:vaadin="http://vaadin.com/jsf">

    <vaadin:ui url="/myui"/>

</html>
```

You can optionally specify a custom widgetset and a theme:
```xhtml
<vaadin:ui url="/myui" widgetset="com.example.MyUiWidgetset" theme="mytheme"/>
```

## Licence
The code is licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.txt).
