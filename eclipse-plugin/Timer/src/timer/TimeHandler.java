package timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class TimeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Date date = new Date(System.currentTimeMillis());
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date);

		MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
				"Time Helper", "Current time is: " + dateStr);

		return null;
	}

}
