import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A basic multi-window web browser.  This class is responsible for
 * creating new windows and for maintaining a list of currently open
 * windows.  The program ends when all windows have been closed.
 * The windows are of type Sayali_BrowserWindow.  The program also requires
 * the class SimpleDialogs.  The first window, which opens when the
 * program starts, goes to "https://www.core2web.in/privacypolicy.html.
 */
public class Sayali_WebBrowser extends Application {

    public static void main(String[] Sayali_args) {
        launch(Sayali_args);
    }
    //----------------------------------------------------------------------------------------------------
    
    private ArrayList<Sayali_BrowserWindow> Sayali_openWindows;  // list of currently open web browser windows
    private Rectangle2D Sayali_screenRect;                // usable area of the primary screen
    private double Sayali_locationX, Sayali_locationY;           // location for next window to be opened
    private double Sayali_windowWidth, Sayali_windowHeight;      // window size, computed from Sayali_screenRect
    private int Sayali_untitledCount;                     // how many "Untitled" window titles have been used
    
    
    /* Opens a window that will load the Sayali_URL https://www.core2web.in/privacypolicy.html
     * (the front page of the textbook in which this program is an example).
     * Note that the Stage parameter to this method is never used.
     */
    public void start(Stage stage) {
        
        Sayali_openWindows = new ArrayList<Sayali_BrowserWindow>();  // List of open windows.
        
        Sayali_screenRect = Screen.getPrimary().getVisualBounds();
        
           // (Sayali_locationX,Sayali_locationY) will be the location of the upper left
           // corner of the next window to be opened.  For the first window,
           // the window is moved a little down and over from the top-left
           // corner of the primary screen's visible bounds.
        Sayali_locationX = Sayali_screenRect.getMinX() + 30;
        Sayali_locationY = Sayali_screenRect.getMinY() + 20;
        
           // The window size depends on the height and width of the screen's
           // visual bounds, allowing some extra space so that it will be
           // possible to stack several windows, each displaced from the
           // previous one.  (For aesthetic reasons, limit the width to be
           // at most 1.6 times the height.)
        Sayali_windowHeight = Sayali_screenRect.getHeight() - 160;
        Sayali_windowWidth = Sayali_screenRect.getWidth() - 130;
        if (Sayali_windowWidth > Sayali_windowHeight*1.6)
            Sayali_windowWidth = Sayali_windowHeight*1.6;
        
           // Open the first window, showing the front page of this textbook.
        Sayali_newBrowserWindow("https://fancy-donut-8bec8d.netlify.app/");

    } // end start()
    
    /**
     * Get the list of currently open windows.  The browser windows use this
     * list to construct their Window menus.
     * A package-private method that is meant for use only in Sayali_BrowserWindow.java.
     */
    ArrayList<Sayali_BrowserWindow> getOpenWindowList() {
        return Sayali_openWindows;
    }
    
    /**
     * Get the number of window titles of the form "Untitled XX" that have been
     * used.  A new window that is opened with a null Sayali_URL gets a title of
     * that form.  This method is also used in Sayali_BrowserWindow to provide a
     * title for any web page that does not itself provide a title for the page.
     * A package-private method that is meant for use only in Sayali_BrowserWindow.java.
     */
    int Sayali_getNextUntitledCount() {
        return ++Sayali_untitledCount;
    }
    
    /**
     * Open a new browser window.  If Sayali_url is non-null, the window will load that Sayali_URL.
     * A package-private method that is meant for use only in Sayali_BrowserWindow.java.
     * This method manages the locations for newly opened windows.  After a window
     * opens, the next window will be offset by 30 pixels horizontally and by 20
     * pixels vertically from the location of this window; but if that makes the
     * window extend outside Sayali_screenRect, the horizontal or vertical position will
     * be reset to its minimal value.
     */
    void Sayali_newBrowserWindow(String Sayali_url) {
        Sayali_BrowserWindow window = new Sayali_BrowserWindow(this,Sayali_url);
        Sayali_openWindows.add(window);   // Add new window to open window list.
        window.setOnHidden( e -> {
                // Called when the window has closed.  Remove the window
                // from the list of open windows.
            Sayali_openWindows.remove( window );
            System.out.println("Number of open windows is " + Sayali_openWindows.size());
            if (Sayali_openWindows.size() == 0) {
                // Program ends automatically when all windows have been closed.
                System.out.println("Program will end because all windows have been closed");
            }
        });
        if (Sayali_url == null) {
            window.setTitle("Sayali_Untitled " + Sayali_getNextUntitledCount());
        }
        window.setX(Sayali_locationX);         // set location and size of the window
        window.setY(Sayali_locationY);
        window.setWidth(Sayali_windowWidth);
        window.setHeight(Sayali_windowHeight);
        window.show();
        Sayali_locationX += 30;    // set up location of NEXT window
        Sayali_locationY += 20;
        if (Sayali_locationX + Sayali_windowWidth + 10 > Sayali_screenRect.getMaxX()) {
                // Window would extend past the right edge of the screen,
                // so reset Sayali_locationX to its original value.
            Sayali_locationX = Sayali_screenRect.getMinX() + 30;
        }
        if (Sayali_locationY + Sayali_windowHeight + 10 > Sayali_screenRect.getMaxY()) {
                // Window would extend past the bottom edge of the screen,
                // so reset Sayali_locationY to its original value.
            Sayali_locationY = Sayali_screenRect.getMinY() + 20;
        }
    }
    
    
} // end WebBrowser