package UTIL;/*created by Jing Li*/

import entity.Conf;

import javax.swing.*;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Properties;

public class FileOpen {
    private static String fileName="data/userInformation.txt";
    private static Properties pps;
    static {
        pps=new Properties();
        FileReader reader=null;
        try {
            reader=new FileReader(fileName);
            pps.load(reader);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"File error",
            	    "Inane error",
            	    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        finally {
            try {
                reader.close();
            }
            catch (Exception ex) {}
        }
    }
    private static void listInfo() {
        PrintStream ps=null;
        try {
            ps=new PrintStream(fileName);
            pps.list(ps);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"File error",
            	    "Inane error",
            	    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        finally {
            try {
                ps.close();
            }
            catch (Exception ex) {}
        }
    }
    public static void getInfoByAccount(String account) {
        String cusInfo=pps.getProperty(account);
        if(cusInfo!=null) {
            String[] infos=cusInfo.split("#");
            Conf.account=account;
            Conf.password=infos[0];
            Conf.name=infos[1];
            Conf.phone=infos[2];
        }
    }
    public static void updateCustomer(String account,String password,
                                      String name,String phone) {
        pps.setProperty(account,password+"#"+name+"#"+phone);
        listInfo();
    }
}