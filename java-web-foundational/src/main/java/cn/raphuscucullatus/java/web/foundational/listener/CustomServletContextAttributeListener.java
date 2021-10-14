package cn.raphuscucullatus.java.web.foundational.listener;

import javax.servlet.*;

import javax.servlet.annotation.*;

/**
 * @author pw
 */
@WebListener
public class CustomServletContextAttributeListener implements ServletContextAttributeListener {
    /**
     * 监听ServletContext 添加属性
     *
     * @param event
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("CustomServletContextAttributeListener监听到了ServletContext添加了属性：" + event.getName()
                + ",添加了属性的值：" + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("CustomServletContextAttributeListener监听到了ServletContext修改了属性：" + event.getName()
                + ",被修改的属性的值：" + event.getValue());
    }
}
