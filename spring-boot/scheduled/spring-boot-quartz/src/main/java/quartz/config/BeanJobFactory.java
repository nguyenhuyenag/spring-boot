//package quartz.config;
//
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//
//public final class BeanJobFactory extends SpringBeanJobFactory { // implements ApplicationContextAware {
//
//	// private transient AutowireCapableBeanFactory factory;
//
//	//	@Override
//	//	public void setApplicationContext(final ApplicationContext context) {
//	//		this.factory = context.getAutowireCapableBeanFactory();
//	//	}
//
////	@Override
////	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
////		// final Object job = super.createJobInstance(bundle);
////		// this.factory.autowireBean(job);
////		// return job;
////		return super.createJobInstance(bundle);
////	}
//
//}