package org.samsu.youdaofanyi.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;

public class OpenDialogHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// 这里打开了一个窗口，那么插件的主要业务逻辑都写在了QueryDialog类中
		QueryDialog dialog = new QueryDialog(Display.getCurrent()
				.getActiveShell());
		dialog.open();
		return null;
	}

}
