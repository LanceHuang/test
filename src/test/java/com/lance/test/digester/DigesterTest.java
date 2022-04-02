//package com.lance.test.digester;
//
//import org.apache.commons.digester.Digester;
//import org.junit.Test;
//import org.xml.sax.SAXException;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Generate bean by parsing .xml
// *
// * @author Lance
// * @since 2019/1/21 14:44
// */
//public class DigesterTest {
//
//    @Test
//    public void test() {
//        InputStream inputStream = null;
//        try {
//            inputStream = this.getClass().getClassLoader().getResourceAsStream("player.xml");
//
//            Digester digester = createPlayerDigester();
//            Player player = new Player();
//            digester.push(player);
//            digester.parse(inputStream);
//            System.out.println(player);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != inputStream) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    //Do nothing
//                }
//            }
//        }
//    }
//
//    private Digester createPlayerDigester() {
//        Digester digester = new Digester();
//
//        digester.addSetProperties("player");
//
//        digester.addObjectCreate("player/role", "com.lance.test.digester.Role", "className");
//        digester.addSetProperties("player/role");
//        digester.addSetNext("player/role", "setRole", "com.lance.test.digester.Role");
//
//        return digester;
//    }
//}
