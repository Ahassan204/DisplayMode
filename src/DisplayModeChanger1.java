import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class DisplayModeChanger1 extends JFrame {

    private static GraphicsDevice device;
    private static JButton BtnChangeResolution = new JButton("Dummy Btn");
    private static boolean isFullScreenSupported = false;
    static JFrame f = new JFrame();
    static int vcHight = 0;		
    static int vcWeight = 0;
    static int vcRate = 0;
    static int vcBitDepth = 0;

    public DisplayModeChanger1(final GraphicsDevice device) {
        DisplayModeChanger1.device = device;
            
        
    }
    

    public static void main(String[] args) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = env.getDefaultScreenDevice();
        DisplayModeChanger1 changer = new DisplayModeChanger1(defaultScreen);
        DisplayMode dm = null;
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice display = ge.getDefaultScreenDevice();
		DisplayMode[] availableModes = display.getDisplayModes();
		
		for (DisplayMode DV : availableModes) {
			
			vcHight = DV.getWidth();
			vcWeight = DV.getHeight();
			vcRate = DV.getRefreshRate();
			vcBitDepth = DV.getBitDepth();  

			//change screen resolution if desired resolution found
            if(DV.getWidth() == 800 && DV.getHeight() == 600){
            	vcHight = DV.getWidth();
    			vcWeight = DV.getHeight();
    			vcRate = DV.getRefreshRate();
    			vcBitDepth = DV.getBitDepth(); 
    			
    			//Assigning Display Mode 
    			dm = new DisplayMode(vcHight, vcWeight, vcBitDepth, vcRate);
    			 
    			System.out.println(vcHight);
    	        System.out.println(vcWeight);
    	        System.out.println(vcRate);
    	        System.out.println(vcBitDepth);
    	        System.out.println("----------------------------------------------------------\n");
    			
    		}                                 
        }    
		
        isFullScreenSupported = device.isFullScreenSupported();       
        if (isFullScreenSupported) {
        	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        	f.setAlwaysOnTop(true);
            device.setFullScreenWindow(f);
            f.validate();
        } else {
        	System.out.println("NO");
            f.pack();
            f.setVisible(true);
        }
        
        try {
     		 
             device.setDisplayMode(dm);
             f.setSize(new Dimension(dm.getWidth(), dm.getHeight()));  
             System.out.println("Screen height resolution was changed to [ " + dm.getHeight() + " ]");
             System.out.println("Screen width  resolution was changed to [ " +dm.getWidth() + " ]");
             f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             f.setResizable(false);
             f.add(BtnChangeResolution);
             //f.validate();
  		} catch (Exception e2) {
  			JOptionPane.showMessageDialog(null,  "ERROR--->" + e2);
  		}      

       
    }//END OF MAIN------------------------------------------------------
}