package helloosgi;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");

		// 注册OSGi Service
		context.registerService(OSGiService.class.getName(), new OSGiService(),
				new Hashtable<String, String>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");

		// 生成Service Tracker
		ServiceTracker tracker = new ServiceTracker(context,
				OSGiService.class.getName(), null);

		tracker.open();
		// 取得Service
		OSGiService service = (OSGiService) tracker.getService();
		// 执行Service
		service.doSomething();
	}

}
