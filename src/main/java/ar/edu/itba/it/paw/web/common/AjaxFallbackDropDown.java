package ar.edu.itba.it.paw.web.common;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.ajax.*;
import org.apache.wicket.ajax.calldecorator.*;
import org.apache.wicket.ajax.form.*;
import org.apache.wicket.markup.*;
import org.apache.wicket.markup.html.form.*;

// Código sacado de "Pro Wicket"
@SuppressWarnings({ "serial", "unchecked" })
public abstract class AjaxFallbackDropDown<T> extends DropDownChoice<T> {
	@SuppressWarnings("rawtypes")
	public AjaxFallbackDropDown(final String id, final List<T> choices,
			final EnumChoiceRenderer renderer) {
		super(id, choices, renderer);
		this.setOutputMarkupId(true);
		this.add(new DropDownAjaxUpdatingBehavior());
	}

	private class DropDownAjaxUpdatingBehavior extends
			AjaxFormComponentUpdatingBehavior {
		// The original onchange script added by the
		// component. It may or may not be preset depending upon
		// the return value of wantOnSelectionChangedNotifications().
		String prevScript;

		DropDownAjaxUpdatingBehavior() {
			// Ajax call configured for onchange event.
			super("onchange");
		}

		// onUpdate call the onSelectionChanged method passing in the
		// AjaxRequestTarget.

		@Override
		protected void onUpdate(final AjaxRequestTarget target) {
			final AjaxFallbackDropDown<T> dropDownChoice = (AjaxFallbackDropDown<T>) this
					.getFormComponent();
			dropDownChoice.onSelectionChanged(target,
					dropDownChoice.getModelObject());
		}

		// Since the above method internally calls
		// onSelectionChanged, make sure that the behavior
		// is being bound to a DropDownChoice.
		@Override
		protected void onBind() {
			super.onBind();
			if (!(this.getComponent() instanceof DropDownChoice)) {
				throw new WicketRuntimeException(
						"Behavior "
								+ this.getClass().getName()
								+ " can only be added to an isntance of a DropDownChoice");
			}
		}

		// Retrieve the original onchange script added by the component
		// if present.
		@Override
		protected void onComponentTag(final ComponentTag tag) {
			if (tag.getAttributes().containsKey("onchange")) {
				this.prevScript = tag.getAttributes().get("onchange")
						.toString();
			} else {
				this.prevScript = null;
			}
			// Get the AjaxFormComponentUpdatingBehavior
			// to add its onchange event script.
			super.onComponentTag(tag);
		}

		@Override
		protected IAjaxCallDecorator getAjaxCallDecorator() {
			return new AjaxPostprocessingCallDecorator(null) {
				// On Ajax failure, execute the original onchange script added
				// by the component. This would trigger the normal
				// request.
				public CharSequence postDecorateOnFailureScript(
						final CharSequence script) {
					if (DropDownAjaxUpdatingBehavior.this.prevScript != null) {
						return script + ";"
								+ DropDownAjaxUpdatingBehavior.this.prevScript;
					} else {
						return script + "";
					}
				}
			};
		}
	}

	public abstract void onSelectionChanged(AjaxRequestTarget target,
			T modelObject);
}