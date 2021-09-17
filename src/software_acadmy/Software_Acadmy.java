package software_acadmy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Software_Acadmy {
    public static void main(String[] args) {
        Software_Acadmy_Main_Frame mf = new Software_Acadmy_Main_Frame();

        frm_SplashScreen sc = new frm_SplashScreen();
        
        sc.setVisible(true);

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(frm_SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sc.setVisible(false);
        mf.setVisible(true);
        sc.dispose();
    }
    
}
