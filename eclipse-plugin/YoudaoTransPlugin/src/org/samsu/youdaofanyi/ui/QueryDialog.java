package org.samsu.youdaofanyi.ui;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jdom.Document;
import org.samsu.youdaofanyi.Activator;
import org.samsu.youdaofanyi.httpclient.HttpClientUtil;
import org.samsu.youdaofanyi.model.ResultModel;

public class QueryDialog extends Dialog {

	// 查询输入框
	private Text queryText;
	// 翻译结果文本框
	private Text resultTextText;
	// 查询按钮
	private Button queryButton;

	private Group infoGroup;

	protected QueryDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		// 创建一个窗口，并设置窗口大小为固定大小
		Shell shell = this.getShell();
		shell.setSize(400, 300);

		// 设置窗口的标题
		shell.setText("翻译小助手");

		// 设置窗口的图标，图标大小建议为16*16像素。如果不设置，将会显示Eclipse默认图标
		shell.setImage(Activator.getImageDescriptor("/icon/menu.png")
				.createImage());

		// 计算并设置窗口的坐标位置
		Monitor primary = shell.getMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2 - 50;
		shell.setLocation(x, y);

		// 布局开始
		Composite composite = new Composite(parent, SWT.NONE);
		// 创建一个有三列、每列不等宽的网格布局
		GridLayout layout = new GridLayout(3, false);

		// 上下左右的边距
		layout.marginBottom = 10;
		layout.marginTop = 10;
		layout.marginLeft = 10;
		layout.marginRight = -30;
		layout.horizontalSpacing = 30;
		layout.verticalSpacing = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// 头部布局
		Composite headerComposite = new Composite(composite, SWT.NONE);
		headerComposite.setLayout(new GridLayout(3, false));
		headerComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// 创建一个标签
		new Label(headerComposite, SWT.NONE).setText("请输入:");
		// 创建一个多行输入框
		queryText = new Text(headerComposite, SWT.BORDER | SWT.MULTI);
		// 设置选中的文字
		queryText.setText(DialogUtil.getSelecedTextFromEditor());
		queryText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// 查询按钮
		queryButton = new Button(headerComposite, SWT.NONE);
		queryButton.setText("查询");

		// 给查询按钮添加事件
		addListenerToButton();

		// 分组开始
		Composite infoComposite = new Composite(parent, SWT.NONE);
		infoComposite.setLayout(new GridLayout(1, true));
		infoComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		infoGroup = new Group(infoComposite, SWT.NONE);
		infoGroup.setText("查询结果");
		GridLayout groupLayout = new GridLayout(1, false);
		groupLayout.marginBottom = 5;
		groupLayout.marginTop = 5;
		groupLayout.marginLeft = 10;
		groupLayout.marginRight = 10;
		infoGroup.setLayout(groupLayout);
		infoGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		infoGroup.pack();
		resultTextText = new Text(infoGroup, SWT.MULTI | SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL | SWT.READ_ONLY);
		resultTextText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// 填入多个换行符，预占高度
		resultTextText.setText("" + System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") +
				// ""+ System.getProperty("line.separator") +
				"");
		return super.createContents(parent);
	}

	@Override
	protected Button createButton(Composite parent, int id, String label,
			boolean defaultButton) {
		if (id == IDialogConstants.CANCEL_ID || id == IDialogConstants.OK_ID) {
			return null;
		}
		return super.createButton(parent, id, label, defaultButton);
	}

	public void addListenerToButton() {
		// 为查询按钮添加鼠标点击事件
		queryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// 获取查询输入框的输入字符串
				String qtext = queryText.getText();

				// 如果输入为空输出提示信息
				if (StringUtils.isBlank(qtext)) {
					MessageDialog.openInformation(Display.getCurrent()
							.getActiveShell(), "提示", "请输入文本进行查询");
				}
				// 否则调用有道翻译接口进行查询，并将查询结果显示在结果文本框中
				else {
					Document doc = HttpClientUtil.getDocumentByQuery(qtext);
					if (doc != null) {
						ResultModel rm = HttpClientUtil.convertDocToModel(doc);
						resultTextText.setText(ResultModel
								.getFormattedDisplatString(rm));
					}
				}

				// 调用父类方法
				super.mouseDown(e);
			}
		});
	}
}
