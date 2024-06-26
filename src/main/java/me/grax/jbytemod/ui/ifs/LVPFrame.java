package me.grax.jbytemod.ui.ifs;

import me.grax.jbytemod.ui.NoBorderSP;
import de.xbrowniecodez.jbytemod.ui.lists.LVPList;

import java.awt.*;

public class LVPFrame extends MyInternalFrame {
    /**
     * Save position
     */
    private static Rectangle bounds = new Rectangle(10, 10, 1280 / 4, 720 / 4);

    public LVPFrame(LVPList lvp) {
        super("Local Variables");
        this.add(new NoBorderSP(lvp));
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
