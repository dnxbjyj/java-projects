package hello.actions;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

public class SampleActionTest extends TestCase {

	public SampleActionTest(String name) throws Exception {
		// 取得当前活动的页面
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();

		// 打开视图
		page.showView("view");

		// 确认打开的视图处于激活状态
		IWorkbenchPart part = page.getActivePart();
		assertTrue(part instanceof IViewPart);
		assertEquals("view", part.getSite().getId());
	}

}
