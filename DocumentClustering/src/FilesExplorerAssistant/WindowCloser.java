/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesExplorerAssistant;

import java.awt.event.*;
import java.awt.*;
/**
 *
 * @author paradise lost
 */class WindowCloser extends WindowAdapter {
    @Override
  public void windowClosing(WindowEvent e) {
    Window win = e.getWindow();
    win.setVisible(false);
    System.exit(0);
    }
  }