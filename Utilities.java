package Project3_6680081;

import java.awt.Image;
import javax.swing.ImageIcon;


// Interface for keeping constant values
interface MyConstants
{
    //----- Resource files
    static final String PATH             = "src/main/java/Project3_6680081/resources/";
    static final String FILE_BG          = PATH + "background.jpg";
    
    //----- Sizes and locations
    static final int MM_FRAME_WIDTH     =   1280;
    static final int MM_FRAME_HEIGHT    =   980;
    static final int GAME_FRAME_WIDTH   =   750;
    static final int GAME_FRAME_HEIGHT  =   600;
}


// Auxiliary class to resize image
class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}