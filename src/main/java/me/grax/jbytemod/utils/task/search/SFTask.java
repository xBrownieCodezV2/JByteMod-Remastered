package me.grax.jbytemod.utils.task.search;

import de.xbrowniecodez.jbytemod.Main;
import de.xbrowniecodez.jbytemod.JByteMod;
import me.grax.jbytemod.ui.PageEndPanel;
import de.xbrowniecodez.jbytemod.ui.lists.SearchList;
import me.grax.jbytemod.ui.lists.entries.SearchEntry;
import me.grax.jbytemod.utils.InstrUtils;
import me.grax.jbytemod.utils.TextUtils;
import me.grax.jbytemod.utils.list.LazyListModel;
import org.objectweb.asm.tree.ClassNode;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class SFTask extends SwingWorker<Void, Integer> {

    private PageEndPanel jpb;
    private JByteMod jbm;
    private String sf;
    private SearchList sl;

    public SFTask(SearchList sl, JByteMod jbm, String sf) {
        this.sl = sl;
        this.jbm = jbm;
        this.jpb = jbm.getPageEndPanel();
        this.sf = sf;
    }

    @Override
    protected Void doInBackground() throws Exception {
        LazyListModel<SearchEntry> model = new LazyListModel<>();
        Collection<ClassNode> values = jbm.getJarArchive().getClasses().values();
        double size = values.size();
        double i = 0;
        for (ClassNode cn : values) {
            if (cn.sourceFile != null && cn.sourceFile.contains(sf)) {
                SearchEntry se = new SearchEntry(cn, cn.methods.get(0), TextUtils.escape(TextUtils.max(cn.sourceFile, 100)));
                se.setText(TextUtils.toHtml(InstrUtils.getDisplayClass(cn.name) + " - " + cn.sourceFile));
                model.addElement(se);
            }

            publish(Math.min((int) ((i++ / size) * 100d) + 1, 100));
        }
        sl.setModel(model);
        publish(100);
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int i = chunks.get(chunks.size() - 1);
        jpb.setValue(i);
        super.process(chunks);
    }

    @Override
    protected void done() {
         Main.INSTANCE.getLogger().log("Search finished!");
    }
}