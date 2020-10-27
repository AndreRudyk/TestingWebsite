package ua.training.controller.listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;

/**
 * This listener starts the time when the user starts taking a test.
 *
 */
public class TestTimeListener implements HttpSessionAttributeListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if (Constants.TEST_IN_PROGRESS.equals(se.getName())) {
			scheduler = Executors.newSingleThreadScheduledExecutor();
			Test test = (Test) se.getSession().getAttribute(Constants.TEST_IN_PROGRESS);
			int delay = test.getTime();
			scheduler.schedule(new TestTimeTracker(se), delay, TimeUnit.MINUTES);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	private class TestTimeTracker implements Runnable {

		private HttpSessionBindingEvent event;

		public TestTimeTracker(HttpSessionBindingEvent event) {
			this.event = event;
		}

		@Override
		public void run() {
			HttpSession session = event.getSession();
			Test test = (Test) session.getAttribute(Constants.TEST_IN_PROGRESS);
			
			if (test != null) {
				int oversize = test.getSize() + 1;
				session.setAttribute(Constants.INDEX, oversize);
			}
		}
	}

}
