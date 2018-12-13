package time;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 自定义的事件处理类
 * 
 * @author Administrator
 *
 */
public class TimeHandler extends AbstractHandler implements IHandler {

	Date date = new Date(System.currentTimeMillis());
	String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MessageDialog.openInformation(
				HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				"Time Helper", "Current time is: " + str);
		return null;
	}

}
