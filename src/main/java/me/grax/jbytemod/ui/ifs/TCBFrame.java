package me.grax.jbytemod.ui.ifs;

import me.grax.jbytemod.ui.NoBorderSP;
import de.xbrowniecodez.jbytemod.ui.lists.TCBList;

import java.awt.*;

public class TCBFrame extends MyInternalFrame {
    /**
     * Save position
     */
    private static Rectangle bounds = new Rectangle(340, 10, 1280 / 4, 720 / 4);

    public TCBFrame(TCBList tcb) {
        super("Try Catch Blocks");
        this.add(new NoBorderSP(tcb));
        this.setBounds(bounds);
        this.show();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (!aFlag && !(getLocation().getY() == 0 && getLocation().getX() == 0)) {
            bounds = getBounds();
        }
        super.setVisible(aFlag);
    }
}
