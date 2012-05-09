package ar.edu.itba.it.paw.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ar.edu.itba.it.paw.web.command.LoginForm;

public class LayoutInterceptor extends HandlerInterceptorAdapter {
	private static final String NO_LAYOUT = "noLayout:";

	private String layoutView;
	private String prefix;
	private String suffix;
	private String basePath;
	private String assetPath;

	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

		final String originalView = modelAndView.getViewName();

		if (originalView != null) {
			if (!originalView.startsWith("redirect:")
					&& !originalView.startsWith("forward:")) {
				this.includeLayout(modelAndView, originalView, request);
			} else if (originalView.startsWith("redirect:/")) {
				final String realViewName = this.basePath
						+ originalView.substring("redirect:".length());

				modelAndView.setViewName("redirect:" + realViewName);
			} else if (originalView.startsWith("forward:/")) {
				final String realViewName = this.basePath
						+ originalView.substring("forward:".length());
				modelAndView.setViewName("forward:" + realViewName);
			}
		}

		if (!modelAndView.getModel().containsKey("loginForm")) {
			modelAndView.getModel().put("loginForm", new LoginForm());
		}
	}

	private void includeLayout(final ModelAndView modelAndView,
			final String originalView, final HttpServletRequest request) {
		final boolean noLayout = originalView.startsWith(NO_LAYOUT);

		final String realViewName = (noLayout) ? originalView
				.substring(NO_LAYOUT.length()) : originalView;

		if (noLayout) {
			modelAndView.setViewName(realViewName);
		} else {
			modelAndView.addObject("page", this.prefix + realViewName
					+ this.suffix);
			modelAndView.addObject("assetPath", request.getContextPath()
					+ this.assetPath);
			modelAndView.addObject("basePath", request.getContextPath()
					+ this.basePath);
			modelAndView.setViewName(this.layoutView);
		}
	}

	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(final String postfix) {
		this.suffix = postfix;
	}

	public void setBasePath(final String basePath) {
		this.basePath = basePath;
	}

	public void setLayoutView(final String layoutView) {
		this.layoutView = layoutView;
	}

	public void setAssetPath(final String assetPath) {
		this.assetPath = assetPath;
	}
}