package annotation14;

import annotation14.apt.ActionListenerProcess;
import annotation14.annotation.ActionListenerFor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 通过注解简化编程
 */
public class ActionListenerDemo {

    private JFrame mainWin = new JFrame("使用注解绑定事件监听器");

    @ActionListenerFor(listener = OkListener.class)
    public JButton ok = new JButton("确定");

    @ActionListenerFor(listener = NoListener.class)
    public JButton no = new JButton("取消");

    public void init()
    {
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(no);

        mainWin.add(jp);

        ActionListenerProcess.processAnnotations(this);

        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args)
    {
        new ActionListenerDemo().init();
    }
}

class OkListener implements java.awt.event.ActionListener
{
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("点击确定");
        JOptionPane.showMessageDialog(null, "单击了确定按钮");
    }
}

class NoListener implements java.awt.event.ActionListener
{
    public void actionPerformed(ActionEvent evt)
    {
        JOptionPane.showMessageDialog(null, "单击了取消按钮");
    }
}

