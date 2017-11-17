package rider.lib;

import java.io.IOException;
public class GetNumber {
    public static String mCmdEchoTpComm = "adb shell cd /sys/class/ms-touchscreen-msg20xx/device/ echo 1 >> upgrade_node_self_apk";
    public static void main(String args[]){
      try{
            updateTp();
          }catch (IOException e){
              e.printStackTrace();
          }
    }
    public static void updateTp() throws IOException{
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(mCmdEchoTpComm);
        action(sBuilder.toString());
    }
    private static void action(String s) throws IOException{
        Runtime rtRuntime = Runtime.getRuntime();
        Process process = rtRuntime.exec(s);
    }
}
