package provider;

/**
 * Created by Administrator on 2017/12/26.
 */
public class MybabyTaoServiceProvider {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(20000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ProtocolConfig.destroyAll();
//            }
//        }).start();
        com.alibaba.dubbo.container.Main.main(args);
    }
}
