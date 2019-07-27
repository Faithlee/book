package net;

import java.net.InetAddress;

/**
 * 网络基础
 */
public class NetBase {

    public static void main(String[] args) throws Exception{
        // 1.测试ip地址
        testIP();
    }

    /**
     * IP地址测试
     */
    public static void testIP() throws Exception {
        InetAddress address = InetAddress.getByName("www.crazyit.org");
        System.out.println("是否可达：" + address.isReachable(2000));
        System.out.println(address.getHostAddress());

        InetAddress ip = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println("是否可达:" + ip.isReachable(5000));
        System.out.println(ip.getCanonicalHostName());
    }
}
