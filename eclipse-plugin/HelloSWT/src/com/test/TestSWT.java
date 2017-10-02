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
		// ����Display����
		Display display = new Display();

		// shellΪ����������
		Shell shell = new Shell(display);
		// ����shell������ʽ
		// shell.setLayout(null);
		// �������������
		shell.setText("���԰�ť������¼�");

		// ��shell�ϴ���Ĭ�ϰ�ť
		Button btn1 = new Button(shell, SWT.NULL);
		// ���ð�ť������
		btn1.setText("��ť1");
		// ���ð�ť������λ�ü����
		btn1.setBounds(10, 10, 75, 30);

		// ������2����ť
		Button btn2 = new Button(shell, SWT.PUSH | SWT.BORDER);
		btn2.setText("��ť2");
		btn2.setBounds(90, 10, 75, 30);

		// Ϊ��ť2���������
		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				// ����һ����Ϣ��ʾ��
				MessageBox msgBox = new MessageBox(shell, SWT.OK
						| SWT.ICON_INFORMATION | SWT.APPLICATION_MODAL);

				// ������Ϣ��ʾ�����
				msgBox.setText("��ť����¼�");
				// ������Ϣ��ʾ����ʾ����
				msgBox.setMessage("�����ˣ�(" + btn2.getText() + ")��ť");
				// ����Ϣ��ʾ��
				msgBox.open();
			}
		});

		// �Զ������������С
		shell.pack();
		// ��������
		shell.open();

		// ���������û�йر�
		while (!shell.isDisposed()) {
			// ���display��æ
			if (!display.readAndDispatch()) {
				// ��display����
				display.sleep();
			}
		}

		// �ͷ�display����
		display.dispose();
	}

}
