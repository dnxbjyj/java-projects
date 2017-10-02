package com.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class TestSWT {

	public static void main(String[] args) {
		// 创建Display对象
		Display display = new Display();

		// shell为程序主窗体
		Shell shell = new Shell(display);
		// 设置shell布局样式
		// shell.setLayout(null);
		// 设置主窗体标题
		shell.setText("测试按钮及点击事件");

		// 在shell上创建默认按钮
		Button btn1 = new Button(shell, SWT.NULL);
		// 设置按钮的文字
		btn1.setText("按钮1");
		// 设置按钮的坐标位置及宽高
		btn1.setBounds(10, 10, 75, 30);

		// 创建第2个按钮
		Button btn2 = new Button(shell, SWT.PUSH | SWT.BORDER);
		btn2.setText("按钮2");
		btn2.setBounds(90, 10, 75, 30);

		// 为按钮2定义监听器
		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				// 创建一个消息提示框
				MessageBox msgBox = new MessageBox(shell, SWT.OK
						| SWT.ICON_INFORMATION | SWT.APPLICATION_MODAL);

				// 设置消息提示框标题
				msgBox.setText("按钮点击事件");
				// 设置消息提示框提示内容
				msgBox.setMessage("按下了：(" + btn2.getText() + ")按钮");
				// 打开消息提示框
				msgBox.open();
			}
		});

		// 自动调整主窗体大小
		shell.pack();
		// 打开主窗体
		shell.open();

		// 如果主窗体没有关闭
		while (!shell.isDisposed()) {
			// 如果display不忙
			if (!display.readAndDispatch()) {
				// 让display休眠
				display.sleep();
			}
		}

		// 释放display对象
		display.dispose();
	}

}
