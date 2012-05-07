import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LayoutInterceptor extends HandlerInterceptorAdapter {
	private static final String NO_LAYOUT = "noLayout:";

	private String layoutView;

	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

		final String originalView = modelAndView.getViewName();

		if (originalView != null && !originalView.startsWith("redirect:")) {
			this.includeLayout(modelAndView, originalView);
		}
	}

	private void includeLayout(final ModelAndView modelAndView,
			final String originalView) {
		final boolean noLayout = originalView.startsWith(NO_LAYOUT);

		final String realViewName = (noLayout) ? originalView
				.substring(NO_LAYOUT.length()) : originalView;

		if (noLayout) {
			modelAndView.setViewName(realViewName);
		} else {
			modelAndView.addObject("view", realViewName);
			modelAndView.setViewName(this.layoutView);
		}
	}

	public void setLayoutView(final String layoutView) {
		this.layoutView = layoutView;
	}
}