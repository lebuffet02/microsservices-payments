package api.pedidos.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtils {

    public static String getAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "Can't possible generate ip address.";
        }
    }
}
