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
		// ȡ�õ�ǰ���ҳ��
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();

		// ����ͼ
		page.showView("view");

		// ȷ�ϴ򿪵���ͼ���ڼ���״̬
		IWorkbenchPart part = page.getActivePart();
		assertTrue(part instanceof IViewPart);
		assertEquals("view", part.getSite().getId());
	}

}
